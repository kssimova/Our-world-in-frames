package com.ourwif.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ourwif.DAO.PostDAO;
import com.ourwif.model.Album;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.Post;
import com.ourwif.model.User;

@RestController
@RequestMapping(value= "/post")
public class PostController {
	ApplicationContext context =
    		new ClassPathXmlApplicationContext("Spring-Module.xml");
	PostDAO postDAO = (PostDAO) context.getBean("PostDAO");

	
	@RequestMapping(value="/get/{post_id}",method = RequestMethod.GET)
	public Post getPost(Model model, @PathVariable("post_id") String postId){
		Post post = null;
		//if we receive a get request with id of the post
		//we should return this post :)
		if(CachedObjects.getInstance().containsPost(postId)){
			post = CachedObjects.getInstance().getOnePost(postId); 
		}else{
			try {
				postDAO.getAllPosts();
			} catch (ValidationException | SQLException e) {
				System.out.println("Validation fail");
			}
			post = CachedObjects.getInstance().getOnePost(postId);
		}
		if(post != null){
		return  post;
		}
		return post;
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addPost(@ModelAttribute Post post, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		// this should add new post to the database and in imgur 
		//this should return JSON with the id and name of this new post
		//working Request is OK
		if(session.getAttribute("logged")!= null){
			Path folder = Paths.get(System.getProperty("catalina.base"));
			String folder1 = folder.toString().replace("\\", "/");
			String data = "";
			File ff = null;
			
		    if (!file.isEmpty()) {
		    	 BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
		    	 ff = new File(folder1);
		    	 ImageIO.write(src, "png", ff);
		    }  		
			//encode file into base64 string
			BufferedImage image = null;
			//read image
			image = ImageIO.read(ff);
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			ImageIO.write(image, "png", byteArray);
			byte[] byteImage = byteArray.toByteArray();	
			String dataImage = Base64.getEncoder().encodeToString(byteImage);
			System.out.println(dataImage);
			data = URLEncoder.encode("image", "UTF-8") + "="
				     + URLEncoder.encode(dataImage, "UTF-8");	
				
			//connect to imgur
			URL url = new URL("https://api.imgur.com/3/upload.json");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					
			//set request
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("authorization", "Bearer ef5590f1e88e136817e6a544f174c567fec38215");
			connection.setRequestMethod("POST");
			connection.connect();
				   
			//send base64 String
			final OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
			osw.write(data);
			osw.flush();
			connection.connect();
			System.out.println(connection.getResponseMessage());	
			
			//get response
			StringBuilder sb = new StringBuilder();		
			Scanner sc = new Scanner(connection.getInputStream());
			while(sc.hasNextLine()){
				  sb.append(sc.nextLine());
			}
			sc.close();	
			
			//TODO get photo id and photo link and deleteHash !!!!	
			//TODO get all other request Attributes AND THE ALBUM THAT THE USER WANTS TO PUT IT IN!!!
			//TODO call DAO makePost method
			//TODO make basic JSON response with this photo id		
			//TODO if something fails make basic JSON with fail response
		
		}else{
			return "login";
		}
		return null;
	}	
	
	
	@RequestMapping(value="/change",method = RequestMethod.PUT)
	public String changePost(Model model, HttpServletRequest request, HttpSession session) {
		//if we get a put request with JSON with name/ description and id of one post we will change what's in the database accordingly
		if(session.getAttribute("logged")!= null){
			Post post = CachedObjects.getInstance().getOnePost(request.getParameter("post_id"));
			User user = CachedObjects.getInstance().getOneUser( request.getParameter("user_id"));
			
			String title = request.getParameter("tittle");
			String description = request.getParameter("description");
			if(!title.isEmpty() && title != null){
				try {
					postDAO.editPostName(post, user, title);
				} catch (ValidationException e) {
					System.out.println("Validation fail");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if(!description.isEmpty() && description != null){
				try {
					postDAO.editPostInfo(post, user, description);
				} catch (ValidationException e) {
					System.out.println("Validation fail");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			return "PostView";
			
		}else{
			return "login";
		}
	}
	
	
	
	@RequestMapping(value="/delete/{post_id}",method = RequestMethod.DELETE)
	public String deletePost(Model model, @PathVariable("post_id") String postId,
										@PathVariable("album_id") Integer albumId,
										HttpSession session){
		if(session.getAttribute("logged")!= null){
			Post post = CachedObjects.getInstance().getOnePost(postId);
			Album album = CachedObjects.getInstance().getOneAlbum(albumId);
			User user = CachedObjects.getInstance().getOneUser((long)session.getAttribute("user_id"));
			String deletehash = post.getDeleteHash();
			
			CachedObjects.getInstance().removePost(post, album);
			try {
				postDAO.deletePost(post, user, album);
			} catch (ValidationException | SQLException e) {
				System.out.println("something went wrong");
			}
			//this will delete one album the request should contain album id					
			URL url = null;
			HttpURLConnection connection = null;
			try {
				url = new URL("https://api.imgur.com/3/image/" + deletehash);
				connection = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				System.out.println("something went wrong");
			}
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("authorization", "Bearer ef5590f1e88e136817e6a544f174c567fec38215");
			try {
				connection.setRequestMethod("DELETE");
				connection.connect();
				System.out.println(connection.getResponseMessage());
			} catch (IOException e) {
				System.out.println("something went wrong");
			}
			return "album/get/" + album.getAlbumId() ;
		}else{	
			return "login";
		}
	}	
	
	
	@RequestMapping(value="/like",method = RequestMethod.POST)
	public String likePost(HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("logged")!= null){
			User user = CachedObjects.getInstance().getOneUser((long)session.getAttribute("user_id"));
			Post post = CachedObjects.getInstance().getOnePost(request.getParameter("post_id"));
			try {
				postDAO.addLike(post, user);
			} catch (ValidationException e) {
				System.out.println("Error in validation");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}	
			return "PageView";
		}else{
			return "login";
		}
	}
	
	
	@RequestMapping(value="/unlike",method = RequestMethod.DELETE)
	public String unlikePost(HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("logged")!= null){
			User user = CachedObjects.getInstance().getOneUser((long)session.getAttribute("user_id"));
			Post post = CachedObjects.getInstance().getOnePost(request.getParameter("post_id"));
			try {
				postDAO.removeLike(post, user);
			} catch (ValidationException | SQLException e) {
				System.out.println("Error in validation");
			}	
			return "PageView";
		}else{
			return "login";
		}
	}	
}

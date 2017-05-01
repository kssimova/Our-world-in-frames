package com.ourwif.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.ourwif.DAO.PostDAO;
import com.ourwif.DAO.UserDAO;
import com.ourwif.model.Album;
import com.ourwif.model.Basic;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.Post;
import com.ourwif.model.User;


@RestController
@RequestMapping(value= "/post")
public class PostController {
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
	UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
	private static final String ACCESS_TOKEN = "bc232b87907ff074efdffbbdaaaaa53aada282a2";

	
	@RequestMapping(value="/get",method = RequestMethod.GET)
	public Post getPost(HttpSession session, HttpServletRequest request){
		String postId = request.getParameter("postId");		
		Post post = null;
		if(session.getAttribute("logged")!= null){
			if(postId != null){
				if(CachedObjects.getInstance().getAllUsers().isEmpty()){
					try {
						userDAO.getAllUsers();

					} catch (ValidationException | SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				post = CachedObjects.getInstance().getOnePost(postId);
			}
		}
		return post;
	}
	
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public Basic addPost(HttpServletRequest request, HttpSession session){
		Basic basic = null;
		if(validImageInfo(request)){
			TreeSet<String> tagsForRec = new TreeSet<>();
			TreeSet<String> tags = new TreeSet<>();
			String tagStr = request.getParameter("tags");
			if(!tagStr.isEmpty() && tagStr != null){
				tags = addTags(tagStr, tagsForRec);
			}		
			String name = request.getParameter("name");
			String description = request.getParameter("description");		
			String albumName = request.getParameter("album");
			name.trim();
			description.trim();
			Album album = null;
	
			User u = (User)session.getAttribute("user");
			for(Album albums : u.getAlbums().values()){
				if (albums.getName().equals(albumName)){
					album = albums;
				}
			}
			String picturePath = "";
			String postId = "";
			String deleteHash = "";		
			String file = request.getParameter("file");
			file = file.substring(file.indexOf(",")+1);	
			//working Request is OK
			if(session.getAttribute("logged")!= null){
				//connect to imgur
				URL url;
				HttpURLConnection connection = null;
				try {
					url = new URL("https://api.imgur.com/3/image.json");
					connection = (HttpURLConnection) url.openConnection();
				} catch (IOException e2) {
					System.out.println(e2.getMessage());
				}				
				//set request
	
				// String expires_in="2419200";
				// String token_type = "bearer";
				// String refresh_token = "412b9ea1f4dfefb804e5746e8f6590311b2e0e26"; 
				// String account_username = "werewolfgirl";
				// String account_id = "335056";
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestProperty("authorization", "Bearer " + ACCESS_TOKEN);
				try {
					connection.setRequestMethod("POST");
					connection.connect();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}		   
				//send base64 String
				OutputStreamWriter osw = null;
				try {
					osw = new OutputStreamWriter(connection.getOutputStream());
					osw.write(file);
					osw.flush();
					connection.connect();
					System.out.println(connection.getResponseMessage());
					if(connection.getResponseCode() != 200){
						basic = new Basic();
						basic.addError("ImageToBigError", "This image is too big!");
						return basic;
					}
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}		
				//get response
				try {
					if(connection.getResponseCode() == 200){
						StringBuilder sb = new StringBuilder();		
						Scanner sc = new Scanner(connection.getInputStream());
						while(sc.hasNextLine()){
							  sb.append(sc.nextLine());
						}
						sc.close();	
						//response to JSON object
						String responseData = sb.toString();
						JsonParser parser = new JsonParser();
						JsonObject jsonObj = parser.parse(responseData).getAsJsonObject();
						JsonObject obj2 = jsonObj.getAsJsonObject("data");
						postId = obj2.get("id").getAsString();
						postId.replaceAll("\"", " ").trim();
						picturePath = obj2.get("link").getAsString();
						picturePath.replaceAll("\"", " ").trim();
						deleteHash = obj2.get("deletehash").getAsString();
						deleteHash.replaceAll("\"", " ").trim();	
						try {
							Post post = postDAO.createPost((User)session.getAttribute("user"), name, description, LocalDate.now(), picturePath, tags, album, postId, deleteHash);
							u.addPhoto(album, post);
							session.setAttribute("user", u);
						} catch (ValidationException e) {
							System.out.println(e.getMessage());
						} catch (SQLException e) {
							System.out.println(e.getMessage());
						}	
					}
				} catch (JsonSyntaxException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}	
			}else{
				basic = new Basic(true, "login");
				basic.setStrId(postId);
			}
			return basic;
		}
		basic = getError(request);
		return basic;
	}		

	@RequestMapping(value="/like",method = RequestMethod.POST)
	public void likePost(HttpServletRequest request, HttpSession session) {
		User user = (User)session.getAttribute("user");
		Post post = CachedObjects.getInstance().getOnePost(request.getParameter("postId"));
		try {
			postDAO.addLike(post, user);
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	
	@RequestMapping(value="/unlike",method = RequestMethod.POST)
	public void unlikePost(HttpServletRequest request, HttpSession session) {
		User user = (User)session.getAttribute("user");
		Post post = CachedObjects.getInstance().getOnePost(request.getParameter("postId"));
		try {
			postDAO.removeLike(post, user);
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}	
	}	
	
	@RequestMapping(value="/getLike",method = RequestMethod.GET)
	public  TreeSet<Post> getLikedPosts(HttpSession session){
		User user = (User)session.getAttribute("user");
		TreeSet<Post> posts = new TreeSet<>();
		if(session.getAttribute("logged")!= null){
			if(CachedObjects.getInstance().getAllUsers().isEmpty()){
				try {
					userDAO.getAllUsers();
				} catch (ValidationException | SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			try {
				posts = postDAO.getAllLikedPosts(user);
			} catch (SQLException | ValidationException e) {
				System.out.println(e.getMessage());
			}
		}
		return posts;
	}
	
	@RequestMapping(value="/tag",method = RequestMethod.POST)
	public TreeSet<Post> getTags(HttpSession session, HttpServletRequest request){
		CachedObjects cachedObj = CachedObjects.getInstance();
		TreeSet<Post> posts = new TreeSet<>();
		TreeSet<String> tagsForRec = new TreeSet<>();
		if(request.getParameter("tagche") != null){
			TreeSet<String> tags = addTags(request.getParameter("tagche"), tagsForRec);
			if(session.getAttribute("logged")!= null){
				if(tags.size() > 0){
					if(CachedObjects.getInstance().getAllUsers().isEmpty()){
						try {
							userDAO.getAllUsers();
						} catch (ValidationException | SQLException e) {
							System.out.println(e.getMessage());
						}
					}
					posts.addAll(cachedObj.getPhotosWithTag(tags));
					posts.addAll(cachedObj.getPhotosWithName(tags));
				}
			}
		}
		return posts;
	}
 	
	@RequestMapping(value="/getPhotos",method = RequestMethod.POST)
	public  TreeSet<Post> getPosts(HttpSession session, HttpServletRequest request){
		CachedObjects cachedObj = CachedObjects.getInstance();
		if(cachedObj.getAllUsers().isEmpty()){
			try {
				userDAO.getAllUsers();
			} catch (ValidationException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		TreeSet<Post> ordered = null;
		TreeSet<Post> posts = new TreeSet<>();
		TreeSet<User> following = new TreeSet<>();
		boolean followers = (request.getParameter("followers").equals("true"));
		User user = (User) session.getAttribute("user");
		//get all posts from followers or all users
		if(followers){
			for(User followe: user.following()){
				following.add(followe);
			}
			for(User users : cachedObj.getAllUsers()){
				if(following.contains(users)){
					for(Entry<Long, Album> albums : users.getAlbums().entrySet()){
						posts.addAll(albums.getValue().getPhotos());
					}	
				}
			}	
		}else{
			for(User users : cachedObj.getAllUsers()){
				if(!users.getAlbums().isEmpty() || users.getAlbums().size() > 0 || users.getAlbums() != null ){
					for(Entry<Long, Album> albums : users.getAlbums().entrySet()){
						posts.addAll(albums.getValue().getPhotos());		
					}
				}
			}		
		}	
		//order them
		if(request.getParameter("orderBy").equals("time")){	
			ordered = new TreeSet<Post>(CachedObjects.dateCreatedComparator);
			ordered.addAll(posts);
		}else{
			ordered = new TreeSet<Post>(CachedObjects.mostLikesComparator);
			ordered.addAll(posts);
		}
		return ordered;
	}
		
	public TreeSet<String> addTags(String tag, TreeSet<String> tags) {
		if (tag.length() == 0) {
			return tags;
		}
		if(!tag.contains(",")){
			tags.add(tag);
			return tags;
		}
		tags.add(tag.substring(0, tag.indexOf(',')));
		tag = tag.substring(tag.indexOf(',') + 1).trim();
	return addTags(tag, tags);
	}
	
	private boolean validImageInfo(HttpServletRequest request) {
		String name = request.getParameter("name");
		name.trim();
		if(name.isEmpty() || name.length() > 50){
			return false;
		}
		String desc = request.getParameter("description");
		desc.trim();
		if(desc.length() > 200 ){
			return false;
		}
		String tags = request.getParameter("tags");
		tags.trim();
		if(tags.length() > 200 ){
			return false;
		}
		return true;
	}

	
	@RequestMapping(value="/valid",method = RequestMethod.POST)
	private Basic getError(HttpServletRequest request) {
		Basic basic = new Basic();
		basic.setStatus(false);
		if(!validImageInfo(request)){
			String name = request.getParameter("name");
			name.trim();
			if(name.isEmpty() && name.length() < 2){
				basic.addError("NameError", "Image name must be at least 3 character long!");	
			}
			if(name.length() > 50){
				basic.addError("NameLength", "Image name must be less than 50 character!");
			}
			String desc = request.getParameter("description");
			desc.trim();
			if(desc.length() > 200 ){
				basic.addError("DescriptionLength", "Image description must be less than 200 character!");
			}
			String tags = request.getParameter("description");
			tags.trim();
			if(tags.length() > 200 ){;
				basic.addError("TagLength", "All image tags combined must be less than 200 character!");
			}
			String album = request.getParameter("album");
			album.trim();
			if(album.isEmpty() || album.length() < 2){;
				basic.addError("AlbumLength", "All images have to be in albums!");
			}
			return basic;
		}
		basic.setStatus(true);
		return basic;
	}	
}

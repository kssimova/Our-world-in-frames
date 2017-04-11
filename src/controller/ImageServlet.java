package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if we receive a get request with id of the post we should return the JSON of that post with all stuff in it
		//TODO check if this image is in cachedObjects
		//TODO if it is get it from there if it is not call getAllPosts in PostDAO and then get it from cache obj
		//TODO make this object into JSON object
		//TODO send this JSON object
		//TODO if something fails sent error in basic response
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if we get a put request with JSON with name/ description and id of one post we will change what's in the database accordingly
		//TODO check if title is empty if it's not empty call PostDAO changeName method
		//TODO check if description is empty if it's empty call PostDAO cahngeDescription method
		//TODO send basic JSON with success response and this post id
		//TODO if something fails send basic JSON with fail response
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//if we get an delete request with image id this should delete this image in imgur and our database
		//TODO call cached objects to get this image
		//TODO delete this image in imgur using it's delete hash
		//TODO call PostDAO deletePost method
		//TODO make basic JSON success response
		//TODO if something fails make basic JSON with fail message	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// this should add new post to the database and in imgur 
		//this should return JSON with the id and name of this new post
		
		//working 
		//Request is OK
		//JSON received
		String saveFile = "";
		String contentType = req.getContentType();
		String data = "";
				
		//get request file
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
			DataInputStream in = new DataInputStream(req.getInputStream());
			int formDataLength = req.getContentLength();
			//make byte array from  this file
			byte dataBytes[] = new byte[formDataLength];
			int byteRead = 0;
			int totalBytesRead = 0;
			while (totalBytesRead < formDataLength) {
					byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
					totalBytesRead += byteRead;
			}
			String file = new String(dataBytes);
			
			//get file name
			saveFile = file.substring(file.indexOf("filename=\"") + 10);
			saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
			saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
			System.out.println(saveFile);
	
			//get the starting and ending points of this image
			int lastIndex = contentType.lastIndexOf("=");
			String boundary = contentType.substring(lastIndex + 1, contentType.length());
			int pos;
			pos = file.indexOf("filename=\"");
			pos = file.indexOf("\n", pos) + 1;
			pos = file.indexOf("\n", pos) + 1;
			pos = file.indexOf("\n", pos) + 1;
			int boundaryLocation = file.indexOf(boundary, pos) - 4;
			int startPos = ((file.substring(0, pos)).getBytes()).length;
			int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
			
			//Choose path to place it in
			Path folder = Paths.get(System.getProperty("catalina.base"));
			String folder1 = folder.toString().replace("\\", "/");
			System.out.println(folder1);
			
			//save file
			saveFile = folder1.toString() +"/" +  saveFile;
			File ff = new File(saveFile);
			ff.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(ff);
			fileOut.write(dataBytes, startPos, (endPos - startPos));
			fileOut.flush();
			fileOut.close(); 
				
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
		}
			
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
		
		//response to JSON object
		String responseData = sb.toString();
		JsonParser parser = new JsonParser();
		JsonObject jsonObj = parser.parse(responseData).getAsJsonObject();
		JsonObject obj2 = jsonObj.getAsJsonObject("data");
		System.out.println(obj2.toString());
		
		//TODO get photo id and photo link and deleteHash !!!!	
		//TODO get all other request Attributes AND THE ALBUM THAT THE USER WANTS TO PUT IT IN!!!
		//TODO call DAO makePost method
		//TODO make basic JSON response with this photo id

		req.getRequestDispatcher("/JSP/index.jsp").forward(req, resp);	
		
		//TODO if something fails make basic JSON with fail response
	}
}

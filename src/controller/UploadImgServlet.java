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

@WebServlet("/uploadImg")
public class UploadImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//working 
		//Request is OK
		//JSON received
		
		String saveFile = "";
		String contentType = request.getContentType();
		String data = "";
		
		//get request file
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
			DataInputStream in = new DataInputStream(request.getInputStream());
			int formDataLength = request.getContentLength();
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
		
		//TODO change DB id for this image-s to be the same as imgur id so it's not the auto increment one
		
		//TODO call DAO makePost method
		
		if(!obj2.get("id").isJsonNull()){
			String imgID = obj2.get("id").getAsString();
			request.getSession().setAttribute("id", imgID);	
			System.out.println(imgID);
		}
		if(!obj2.get("link").isJsonNull()){
			String imgLink = obj2.get("link").getAsString();
			request.getSession().setAttribute("link", imgLink);
			System.out.println(imgLink);
		}
	request.getRequestDispatcher("/JSP/index.jsp").forward(request, response);				
	}
}

package controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

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
		// not working 
		//Bad Request
		
		
		String contentType = request.getContentType();
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
			String file = "";
					DataInputStream in = new DataInputStream(request.getInputStream());
				int formDataLength = request.getContentLength();
					byte dataBytes[] = new byte[formDataLength];
					int byteRead = 0;
				int totalBytesRead = 0;
				while (totalBytesRead < formDataLength) {
		      		byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
		      		totalBytesRead += byteRead;
				}
			
			URL url = new URL("https://api.imgur.com/3/upload");
			
			String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(Base64.getEncoder().encode(dataBytes).toString(), "UTF-8");
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("authorization", "Bearer ef5590f1e88e136817e6a544f174c567fec38215");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("image", file);
			connection.connect();
			System.out.println(connection.getResponseMessage());			
			StringBuilder sb = new StringBuilder();
			Scanner sc = new Scanner(connection.getInputStream());
			while(sc.hasNextLine()){
				sb.append(sc.nextLine());
			}
			sc.close();
			
			String responseData = sb.toString();
			JsonParser parser = new JsonParser();
			JsonObject jsonObj = parser.parse(responseData).getAsJsonObject();
			System.out.println(jsonObj.toString());
			JsonObject obj2 = jsonObj.getAsJsonObject("data");
			System.out.println(obj2.toString());
			
	//		if(!obj2.get("id").isJsonNull()){
	//			String imgID = obj2.get("id").getAsString();
	//			request.getSession().setAttribute("id", imgID);
	//			
	//		}
	//		if(!obj2.get("title").isJsonNull()){
	//			String imgTitle = obj2.get("title").getAsString();
	//			request.getSession().setAttribute("title", imgTitle);
	//		}
	//		if(!obj2.get("description").isJsonNull()){
	//			String imgDesc = obj2.get("description").getAsString();
	//			request.getSession().setAttribute("description", imgDesc);
	//		}
	//		if(!obj2.get("datetime").isJsonNull()){
	//			String imgDate = obj2.get("datetime").getAsString();
	//			request.getSession().setAttribute("datetime", imgDate);
	//		}
	//		if(!obj2.get("name").isJsonNull()){
	//			String imgName = obj2.get("name").getAsString();
	//			request.getSession().setAttribute("name", imgName);
	//		}
	//		if(!obj2.get("link").isJsonNull()){
	//			String imgLink = obj2.get("link").getAsString();
	//			request.getSession().setAttribute("link", imgLink);
	//		}
		request.getRequestDispatcher("index.jsp").forward(request, response);				
		}
	}
}

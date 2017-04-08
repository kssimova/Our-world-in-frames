package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/getImg")

//actually this  will only view images from imgur :D 
public class GetImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		URL url = new URL("	https://api.imgur.com/3/image/wBCmqov");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("authorization", "Bearer ef5590f1e88e136817e6a544f174c567fec38215");
		connection.setRequestMethod("GET");
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
		if(!obj2.get("id").isJsonNull()){
			String imgID = obj2.get("id").getAsString();
			request.getSession().setAttribute("id", imgID);
			
		}
		if(!obj2.get("title").isJsonNull()){
			String imgTitle = obj2.get("title").getAsString();
			request.getSession().setAttribute("title", imgTitle);
		}
		if(!obj2.get("description").isJsonNull()){
			String imgDesc = obj2.get("description").getAsString();
			request.getSession().setAttribute("description", imgDesc);
		}
		if(!obj2.get("datetime").isJsonNull()){
			String imgDate = obj2.get("datetime").getAsString();
			request.getSession().setAttribute("datetime", imgDate);
		}
		if(!obj2.get("name").isJsonNull()){
			String imgName = obj2.get("name").getAsString();
			request.getSession().setAttribute("name", imgName);
		}
		if(!obj2.get("link").isJsonNull()){
			String imgLink = obj2.get("link").getAsString();
			request.getSession().setAttribute("link", imgLink);
		}
	request.getRequestDispatcher("index.jsp").forward(request, response);				
	}

}

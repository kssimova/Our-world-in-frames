package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/deleteImg")
public class DeleteImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO get real image delete hash
		//TODO call delete image DAO method
		String imageId = "OpBK2nwGhPD10l2";
		
		URL url = new URL("https://api.imgur.com/3/image/" + imageId);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("authorization", "Bearer ef5590f1e88e136817e6a544f174c567fec38215");
		connection.setRequestMethod("DELETE");
		connection.connect();
		System.out.println(connection.getResponseMessage());			

	request.getRequestDispatcher("/JSP/index.jsp").forward(request, response);				
	}

}

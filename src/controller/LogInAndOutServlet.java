package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import com.google.gson.JsonObject;

import DAO.UserDAO;
import model.CachedObjects;
import model.User;

@WebServlet("/login")
public class LogInAndOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("JSP/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject obj2 = new JsonObject();
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		
		//TODO create login.jsp
		String fileName = "JSP/Login.jsp";
		User u = null;
			if(UserDAO.getInstance().validLogin(username, password)){
				if(CachedObjects.getInstance().containsUser(username)){
					//TODO create profile.jsp
					fileName = "JSP/Profile.jsp";
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("user", u);
					session.setAttribute("logged", true);
					response.setStatus(200);
					obj2.addProperty("success", "Login successful");
				}else{
					try {
						UserDAO.getInstance().getAllUsers();
					} catch (ValidationException e) {
						obj2.addProperty("error", "Invalid userename or password");
					}
					u = CachedObjects.getInstance().getOneUser(username);
				}
			}else{
				obj2.addProperty("error", "User does not exist");
			}	
		response.setContentType("application/json");  
		response.setCharacterEncoding("UTF-8");     
		response.getWriter().write(obj2.toString());
		response.sendRedirect(fileName);	

	}
}

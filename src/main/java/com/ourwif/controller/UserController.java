package com.ourwif.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ourwif.DAO.UserDAO;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.User;

@RestController
@RequestMapping(value= "/user")
public class UserController {
    
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(Model model, HttpSession session, HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		
		//TODO create login.jsp
		String fileName = "JSP/Login.jsp";
		User u = null;
			if(UserDAO.getInstance().validLogin(username, password)){
				if(CachedObjects.getInstance().containsUser(username)){
					session.setAttribute("username", username);
					session.setAttribute("user", u);
					session.setAttribute("logged", true);
				}else{
					try {
						UserDAO.getInstance().getAllUsers();
					} catch (ValidationException e) {
						System.out.println("ops cant log in");
					}{
					u = CachedObjects.getInstance().getOneUser(username);
				}
			}	
		}
		//this will delete one comment.. request should contain the id of this comment 
		return "redirect:index.html";
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// for viewing one profile
		return null;
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String register(Model model, HttpSession session, HttpServletRequest request) {

		//this will delete one comment.. request should contain the id of this comment 
		return "redirect:index.html";
	}
	

	@RequestMapping(value="/{user_id}",method = RequestMethod.GET)
	public User getUser(Model model, @PathVariable("user_id") String productId) {
		// for viewing one profile
		return null;
	}
	
	@RequestMapping(value="/change",method = RequestMethod.PUT)
	public String changeUser(Model model, HttpServletRequest request) {
		//this will change the content of the post this request should contain the comment id and the new values
		return "user";
	}
	
	@RequestMapping(value="/follow",method = RequestMethod.POST)
	public String follow(Model model, HttpServletRequest request) {

		//this will delete one comment.. request should contain the id of this comment 
		return "user";
	}
	
	@RequestMapping(value="/follow",method = RequestMethod.DELETE)
	public String unfollow(Model model, HttpServletRequest request) {
		//this will delete one comment.. request should contain the id of this comment 
		return "user";
	}

}

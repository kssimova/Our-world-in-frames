package com.ourwif.controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ourwif.DAO.PostDAO;
import com.ourwif.DAO.UserDAO;
import com.ourwif.model.Basic;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.User;


@RestController
@RequestMapping(value= "/user")
public class UserController {
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
	PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
	
	
	@RequestMapping(value="/api", method=RequestMethod.GET)
	public void getApi(HttpServletResponse response) {
	    try {
			response.sendRedirect("apiJSP");
		} catch (IOException e) {
			System.out.println("ops");
		}
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public Basic login(Model model, HttpSession session, HttpServletRequest request) {
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");	
		System.out.println(password);
		CachedObjects cachedObj =  CachedObjects.getInstance();
		Basic basic = null;
		User u = null;
			try {
				if(userDAO.validLogin(username, password)){
					if(!cachedObj.containsUser(username)){
						try {
							userDAO.getAllUsers();
						} catch (ValidationException e) {
							System.out.println("ops cant log in");
						}
					}
					u = cachedObj.getOneUser(username);
					session.setAttribute("username", username);
					session.setAttribute("user", u);
					session.setAttribute("logged", true);
					
				}
			} catch (ValidationException e) {
				System.out.println(e.getMessage());
			}
		basic = new Basic(true, "/ourwif/index");
		basic.setId(u.getUserId());
		return basic;
	}
	

	@RequestMapping(value="/register",method = RequestMethod.POST)
	public Basic register(Model model, HttpSession session, HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		String email = request.getParameter("email");	
		String confirmPassword = request.getParameter("confirmPassword");
		String gender = request.getParameter("gender");
		Basic basic = null;
		User user = null;
		boolean validRegistration = true;
		
		try {
			user = new User(username);
		} catch (ValidationException e) {
			validRegistration = false;
			basic.addError("#usernameError", e.getMessage());
		}
		
		try {
			user.changeEmail(email);
		} catch (ValidationException e) {
			validRegistration = false;
			basic.addError("#emailError", e.getMessage());
		}
		
		try {
			user.changePassword(password);
		} catch (ValidationException e) {
			validRegistration = false;
			basic.addError("#passwordError", e.getMessage());
		}
		
		System.out.println("************");
		System.out.println(username);
		System.out.println(email);
		System.out.println(email);
		System.out.println(password);
		System.out.println(gender.toString());
		System.out.println("***********");
		
		user.changeGender(Enum.valueOf(User.Gender.class, gender.toUpperCase()));
		
		if(validRegistration){
			basic.addError("#registration", "Registration successful! You may now log in!");
		}
		
		return basic;
	}

	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public void logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		try {
			response.sendRedirect("../index");
		} catch (IOException e) {
			System.out.println("ops");
		}
	}
	
	//get user from post id
	@RequestMapping(value="/{post_id}",method = RequestMethod.GET)
	public User getUser(Model model, @PathVariable("post_id") String postId) {
		System.out.println(postId);
		CachedObjects cachedObj = CachedObjects.getInstance();
		User user = null;
		if(cachedObj.getAllPosts().isEmpty()){
			try {
				postDAO.getAllPosts();
			} catch (ValidationException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		user = cachedObj.getOnePost(postId).getUser();		
		return user;
	}
	
	@RequestMapping(value="/change",method = RequestMethod.PUT)
	public String changeUser(Model model, HttpServletRequest request) {
		//this will change the content of the post this request should contain the comment id and the new values
		return "user";
	}
	
	@RequestMapping(value="/follow",method = RequestMethod.POST)
	public void follow(HttpSession session,  HttpServletRequest request) {
		String postId = request.getParameter("postId");
		CachedObjects cachedObj = CachedObjects.getInstance();
		User follower = (User) session.getAttribute("user");
		if(cachedObj.getAllPosts().isEmpty()){
			try {
				postDAO.getAllPosts();
			} catch (ValidationException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		User user = cachedObj.getOnePost(postId).getUser();
		try {
			userDAO.followUser(follower, user);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@RequestMapping(value="/unfollow",method = RequestMethod.POST)
	public String unfollow(Model model, HttpServletRequest request) {
		//this will delete one comment.. request should contain the id of this comment 
		return "user";
	}

	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public User getProfilePage(HttpSession session, Model m) {
		User user = null;
		if(session.getAttribute("logged")!= null){
			user = (User)session.getAttribute("user");
		}
		return user;
	}
}

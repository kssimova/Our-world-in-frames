package com.ourwif.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Set;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ourwif.DAO.PostDAO;
import com.ourwif.DAO.UserDAO;
import com.ourwif.model.Basic;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.User;


@RestController
@RequestMapping(value= "/user")
public class UserController {
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
	UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
	
	@RequestMapping(value="/api", method=RequestMethod.GET)
	public void getApi(HttpServletResponse response) {
	    try {
			response.sendRedirect("apiJSP");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@RequestMapping(value="/contactUs", method=RequestMethod.POST)
	public Basic contact(Model model, HttpServletRequest request) {
	   String name = request.getParameter("name");
	   String email = request.getParameter("email");
	   String mobileNumber = request.getParameter("mobile");
	   String message = request.getParameter("message");
	   Basic basic = new Basic();
	   boolean validContact = true;
	   
	   System.out.println(message + "-------------------------------------------------");
	   
	   if(message.trim().length() > 0  && message.length() <= 500){
		   try{
			   User user = new User(name);
			   user.changeEmail(email);
			   user.changeMobileNumber(mobileNumber);
		   }
		   catch(ValidationException e1){
			   validContact = false;
			   basic.addError("#contact-error", e1.getMessage());
		   }
		   if(validContact){
			   try {
				   userDAO.contactUs(name, email, message, mobileNumber);
				   basic.addError("#contact-error", "* Message sent successfully! Thank you for your time! :) ");
			   } catch (SQLException e) {
				   System.out.println(e.getMessage());
			       basic.addError("#contact-error", "* Something went wrong! It's not you, it's us :( ");
			    }
			}
	   }
	   else{
		   basic.addError("#contact-error", "* Please enter a valid message! ");
	   }
	   
	   return basic;
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public Basic login(Model model, HttpSession session, HttpServletRequest request) {
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");	
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		CachedObjects cachedObj =  CachedObjects.getInstance();
		Basic basic = null;
		User u = null;
			try {
				if(userDAO.validLogin(username, password)){
					if(!cachedObj.getAllUsers().isEmpty()){
						try {
							userDAO.getAllUsers();
						} catch (ValidationException | SQLException e) {
							System.out.println("ops cant log in");
						}
					}
					u = cachedObj.getOneUser(username);
					session.setAttribute("username", username);
					session.setAttribute("user", u);
					session.setAttribute("logged", true);
				}
			} catch (ValidationException | SQLException e) {
				System.out.println(e.getMessage());
			}
		basic = new Basic(true, "/ourwif/index");
		basic.setId(u.getUserId());
		return basic;
	}
	

	@RequestMapping(value="/register",method = RequestMethod.POST)
	public Basic register(HttpSession session, HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		String confirmPassword = request.getParameter("confirmPassword");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		String email = request.getParameter("email");	
		String gender = request.getParameter("gender");
		Basic basic = new Basic();
		User user = null;
		boolean validRegistration = true;
		
		try {
			if(userDAO.isUsernameTaken(username)){
				validRegistration = false;
				basic.addError("#usernameError", " * Username is already taken! Try another one! :) ");
			}
		} catch (ValidationException | SQLException e) {
			validRegistration = false;
			basic.addError("#usernameError", e.getMessage());
			return basic;
		}
		
		try {
			user = new User(username);
		} catch (ValidationException e) {
			validRegistration = false;
			basic.addError("#usernameError", e.getMessage());
			return basic;
		}
		
		try {
			if(userDAO.isEmailTaken(email)){
				basic.addError("#emailError", " * Email is already taken! Try another one! :) ");
				validRegistration = false;
			}
			user.changeEmail(email);
		} catch (ValidationException | SQLException e) {
			validRegistration = false;
			basic.addError("#emailError", e.getMessage());
			return basic;
		}
		
		if(!password.equals(confirmPassword)){
			validRegistration = false;
			basic.addError("#checkPasswordMatch", " * Passwords do not match!");
			return basic;
		}
		
		try {
			user.changePassword(hashedPassword);
		} catch (ValidationException e) {
			validRegistration = false;
			basic.addError("#passwordError", e.getMessage());
		}
		
		if(gender.equals("0")){
			validRegistration = false;
			basic.addError("#selectGender", " * Please select gender! ");
		}
		else{
			user.changeGender(Enum.valueOf(User.Gender.class, (gender.toUpperCase())));
		}
<<<<<<< HEAD
=======
		
>>>>>>> 74d77df22eadb727a1a6978ad9e067bff582c91a
		if(validRegistration){
			basic.addError("#registration", "  * Registration successful! Log in!");
			try {
				userDAO.addUser(user);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return basic;
	}

	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public void logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		try {
			response.sendRedirect("../index");
		} catch (IOException e) {
			System.out.println("Can't log out");
		}
	}
	
	//get user from post id
	@RequestMapping(value="/{post_id}",method = RequestMethod.GET)
	public User getUser(Model model, @PathVariable("post_id") String postId) {
		CachedObjects cachedObj = CachedObjects.getInstance();
		User user = null;
		if(cachedObj.getAllUsers().isEmpty()){
			try {
				userDAO.getAllUsers();
			} catch (ValidationException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		user = cachedObj.getOnePost(postId).getUser();	
		return user;
	}
	
	@RequestMapping(value="/follow",method = RequestMethod.POST)
	public User follow(HttpSession session,  HttpServletRequest request) {
		String postId = request.getParameter("postId");
		CachedObjects cachedObj = CachedObjects.getInstance();
		User follower = (User) session.getAttribute("user");
		User user = null;
		if(cachedObj.getAllUsers().isEmpty()){
			try {
				userDAO.getAllUsers();
			} catch (ValidationException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		user = cachedObj.getOnePost(postId).getUser();
		try {
			userDAO.followUser(follower, user);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	@RequestMapping(value="/unfollow",method = RequestMethod.POST)
	public User unfollow(HttpSession session,  HttpServletRequest request) {
		String postId = request.getParameter("postId");
		CachedObjects cachedObj = CachedObjects.getInstance();
		User follower = (User) session.getAttribute("user");
		User user = null;
		if(cachedObj.getAllUsers().isEmpty()){
			try {
				userDAO.getAllUsers();
			} catch (ValidationException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		user = cachedObj.getOnePost(postId).getUser();
		try {
			userDAO.unfollowUser(follower, user);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	//check if this user is the same
	@RequestMapping(value="/get/{user_id}", method=RequestMethod.GET)
	public Basic getSameUser(HttpSession session,  @PathVariable("user_id") Long userId) {
		Basic basic = new Basic(false, "url");
		User user = (User) session.getAttribute("user");
		CachedObjects cachedObj = CachedObjects.getInstance();
		if(cachedObj.getAllUsers().isEmpty()){
			try {
				userDAO.getAllUsers();
			} catch (ValidationException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		Set<Long> userToCheck = cachedObj.getOneUser(userId).getFollowers();
		for(Long users : userToCheck){
			if(user.getUserId() == users){
				basic.setStatus(true);
			}	
		}
		return basic;
	}

	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public User getProfilePage(HttpSession session){
		User fromSession = (User) session.getAttribute("user");
		CachedObjects cachedObj = CachedObjects.getInstance();
		User user = null;
		if(cachedObj.getAllUsers().isEmpty()){
			try {
				userDAO.getAllUsers();
			} catch (ValidationException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		user = cachedObj.getOneUser(fromSession.getUserId());
		session.setAttribute("user", user);
		return user;
	}
}

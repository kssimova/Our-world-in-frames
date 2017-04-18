package com.ourwif.controller;


import java.io.IOException;

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

import com.ourwif.DAO.UserDAO;
import com.ourwif.model.Basic;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.User;


@RestController
@RequestMapping(value= "/user")
public class UserController {
	ApplicationContext context =
    		new ClassPathXmlApplicationContext("Spring-Module.xml");
	UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
	
	
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
		Basic basic = null;
		User u = null;
			if(userDAO.validLogin(username, password)){
				if(CachedObjects.getInstance().containsUser(username)){
					u = CachedObjects.getInstance().getOneUser(username);
					session.setAttribute("username", username);
					session.setAttribute("userID", u.getUserId());
					session.setAttribute("logged", true);
				}else{
					try {
						userDAO.getAllUsers();
					} catch (ValidationException e) {
						System.out.println("ops cant log in");
					}{
					u = CachedObjects.getInstance().getOneUser(username);
					session.setAttribute("username", username);
					session.setAttribute("userID", u.getUserId());
					session.setAttribute("logged", true);
				}
			}	
		}
		basic = new Basic(true, "/ourwif/index", u.getUserId());
		//this will delete one comment.. request should contain the id of this comment 
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
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String register(Model model, HttpSession session, HttpServletRequest request) {

		//this will delete one comment.. request should contain the id of this comment 
		return "UnlogedLogin";
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

	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public User getProfilePage(HttpSession session, Model m) {
		User u = null;
		if(session.getAttribute("logged")!= null){
			if(CachedObjects.getInstance().getAllUsers().size() == 0){
				try {
					userDAO.getAllUsers();
				} catch (ValidationException e) {
					System.out.println("can't get all users");
				}
			}
			long l = (long)session.getAttribute("userID");
			System.out.println(l);
			u = CachedObjects.getInstance().getOneUser(l);
		}
		return u;
	}
}

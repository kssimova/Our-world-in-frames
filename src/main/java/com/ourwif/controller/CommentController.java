package com.ourwif.controller;

import java.sql.SQLException;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ourwif.DAO.CommentDAO;
import com.ourwif.DAO.UserDAO;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.Comment;
import com.ourwif.model.Post;
import com.ourwif.model.User;

@RestController
@RequestMapping(value= "/comment")
public class CommentController {
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	CommentDAO commentDAO = (CommentDAO) context.getBean("CommentDAO");
	UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
	
	@RequestMapping(value="/{post_id}",method = RequestMethod.GET)
	public TreeMap<Long, Comment> getComments(Model model, @PathVariable("post_id") String postId) {
		TreeMap<Long, Comment> comments = new TreeMap<>();
		CachedObjects cachedObj = CachedObjects.getInstance();
		Post post = null;
		if(cachedObj.getAllUsers().isEmpty()){
			cachedObj.getAllUsers();
		}
		post = cachedObj.getOnePost(postId);
		try {
			comments = commentDAO.getAllComments(post);
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return comments;
	}

	@RequestMapping(value="/add",method = RequestMethod.POST)
	public Comment addComment(HttpServletRequest request, HttpSession session) {
		String postId = request.getParameter("postId");
		String commentStr = request.getParameter("comment");
		User user = (User) session.getAttribute("user");
		Comment comment = null;
		if(CachedObjects.getInstance().getAllUsers().isEmpty()){
			try {
				userDAO.getAllUsers();
			} catch (ValidationException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		Post post = CachedObjects.getInstance().getOnePost(postId);
		try {
			comment = commentDAO.createComment(post, user, commentStr);
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}	
		return comment;
	}	
	
	//not ready
	@RequestMapping(value="/change",method = RequestMethod.PUT)
	public String changeComment(Model model, HttpServletRequest request) {
		//this will change the content of the post this request should contain the comment id and the new values
		return "post";
	}

	
	@RequestMapping(value="/{comment_id}",method = RequestMethod.DELETE)
	public String deleteComment(Model model, @PathVariable("comment_id") Integer productId) {
		//this will delete one comment.. request should contain the id of this comment 
		return "redirect:index.html";
	}
}

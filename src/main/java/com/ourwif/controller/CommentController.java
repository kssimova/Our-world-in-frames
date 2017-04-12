package com.ourwif.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ourwif.model.Comment;

@RestController
@RequestMapping(value= "/comment")
public class CommentController {

	@RequestMapping(value="/{post_id}",method = RequestMethod.GET)
	public ArrayList<Comment> getComments(Model model, @PathVariable("post_id") String productId) {
		ArrayList<Comment> comments = new ArrayList<>();
		//this should get all the comments form one image the JSON should contain the image id 	
		return comments;
	}

	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addComment(@ModelAttribute Comment comment) {
		//this will make new post in the JSON we should have the user id, String with the content and the post id
		return "basic resp with 200 or smth";
	}
	

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

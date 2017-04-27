package com.ourwif.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class StartController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String sayHi(HttpSession session) {
		String url = "index";
		if(session.getAttribute("logged")!= null){
			url = "Discover";
		}
		return url;
	}
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String getIndexPage(HttpSession session) {
		String url = "index";
		if(session.getAttribute("logged")!= null){
			url = "Discover";
		}
		return url;
	}
		
	@RequestMapping(value="/api", method=RequestMethod.POST)
	public String getApiPage(HttpSession session) {
		String url = "index";
		if(session.getAttribute("logged")!= null){
			url = "API";
		}
		return url;
	}	
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String getHomePage(HttpSession session) {
		String url = "index";
		if(session.getAttribute("logged")!= null){
			url = "HomePage";
		}
		return url;
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String getProfilePage(HttpSession session) {
		String url = "index";
		if(session.getAttribute("logged")!= null){
			url = "UserPage";
		}
		return url;
	}
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String getUploadPage(HttpSession session) {
		String url = "index";
		if(session.getAttribute("logged")!= null){
			url = "Upload";
		}
		return url;
	}
	
	@RequestMapping(value="/postView", method=RequestMethod.GET)
	public String getPostPage(HttpSession session) {
		String url = "index";
		if(session.getAttribute("logged")!= null){
			url = "PostView";
		}
		return url;
	}
	
	@RequestMapping(value="/album", method=RequestMethod.GET)
	public String getAlbumPage(HttpSession session) {
		String url = "index";
		if(session.getAttribute("logged")!= null){
			url = "Albums";
		}
		return url;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String getSearch(HttpSession session) {
		String url = "index";
		if(session.getAttribute("logged")!= null){
			url = "SearchResult";
		}
		return url;
	}
}


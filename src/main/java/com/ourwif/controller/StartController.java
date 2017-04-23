package com.ourwif.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class StartController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String sayHi(HttpSession session) {
		String url = "UnlogedIndex";
		if(session.getAttribute("logged")!= null){
			System.out.println(session.getAttribute("logged").toString());
			url = "LogedIndex";
		}
		return url;
	}
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String getIndexPage(HttpSession session) {
		String url = "UnlogedIndex";
		if(session.getAttribute("logged")!= null){
			url = "LogedIndex";
		}
		return url;
	}
		
	@RequestMapping(value="/api", method=RequestMethod.POST)
	public String getApiPage(HttpSession session) {
		String url = "UnlogedApi";
		if(session.getAttribute("logged")!= null){
			url = "LogedApi";
		}
		return url;
	}
	//@RequestMapping(value="/loginPage", method=RequestMethod.POST)
	//public String getLoginPage() {
	//	return "index";
	//}
	//
	//just for tests
	
	@RequestMapping(value="/loginPage", method=RequestMethod.POST)
  	public String getLoginPage() {
 		return "UnlogedLoginPage";
  	}
	
	
	@RequestMapping(value="/registerPage", method=RequestMethod.POST)
	public String getRegisterPage() {
		return "UnlogedRegister";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String getHomePage(HttpSession session) {
		String url = "UnlogedHomePage";
		if(session.getAttribute("logged")!= null){
			url = "LogedHomePage";
		}
		return url;
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String getProfilePage(HttpSession session) {
		String url = "UnlogedIndex";
		if(session.getAttribute("logged")!= null){
			url = "LogedUserPage";
		}
		return url;
	}
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String getUploadPage(HttpSession session) {
		String url = "UnlogedHomePage";
		if(session.getAttribute("logged")!= null){
			url = "LogedUpload";
		}
		return url;
	}
	
	@RequestMapping(value="/postView", method=RequestMethod.GET)
	public String getPostPage(HttpSession session) {
		String url = "UnlogedIndex";
		if(session.getAttribute("logged")!= null){
			url = "LogedPostView";
		}
		return url;
	}
	
	@RequestMapping(value="/album", method=RequestMethod.GET)
	public String getAlbumPage(HttpSession session) {
		String url = "UnlogedIndex";
		if(session.getAttribute("logged")!= null){
			url = "LogedAlbums";
		}
		return url;
	}
}


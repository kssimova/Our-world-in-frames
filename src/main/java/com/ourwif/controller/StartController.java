package com.ourwif.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String sayHi() {
		return "index";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String getIndexPage() {
		return "index";
	}
		
	@RequestMapping(value="/api", method=RequestMethod.POST)
	public String getApiPage(HttpSession session, Model model) {
		return "apiJsp";
	}
	@RequestMapping(value="/loginPage", method=RequestMethod.POST)
	public String getLoginPage(HttpSession session, Model model) {
		return "loginPage";
	}
	
	@RequestMapping(value="/registerPage", method=RequestMethod.POST)
	public String getRegisterPage(HttpSession session, Model model) {
		return "register";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String getHomePage(HttpSession session, Model model) {
		return "HomePage";
	}
}

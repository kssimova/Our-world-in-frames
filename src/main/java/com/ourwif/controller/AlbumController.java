package com.ourwif.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ourwif.model.Album;

@RestController
@RequestMapping(value= "/album")
public class AlbumController {
	
	@RequestMapping(value="/{album_id}",method = RequestMethod.GET)
	public String getAlbum(Model model, @PathVariable("album_id") Integer productId) {
		//this should get one album the JSON should contain the album id	
		return "Albums";
	}


	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addAlbum(@ModelAttribute Album album) {
		//this will make new album in the JSON we should have the String with the description and name + user_id	
		return "basic response + redirect to creatAlbum page";
	}
	
	
	@RequestMapping(value="/change",method = RequestMethod.PUT)
	public String changeAlbum(Model model, HttpServletRequest request) {
		//this will change the description or name  of the album this request should contain the album_id and the new values			
		return "album";
	}

	@RequestMapping(value="/{album_id}",method = RequestMethod.DELETE)
	public String deleteAlbum(Model model, @PathVariable("album_id") Integer productId) {
		//this will delete one album the request should contain album id		
		return "redirect:index.html";
	}	
}

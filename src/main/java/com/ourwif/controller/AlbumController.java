package com.ourwif.controller;

import java.sql.SQLException;

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

import com.ourwif.DAO.AlbumDAO;
import com.ourwif.model.Album;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.User;

@RestController
@RequestMapping(value= "/album")
public class AlbumController {
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
	
	@RequestMapping(value="/{album_id}",method = RequestMethod.GET)
	public Album getAlbum(Model model, @PathVariable("album_id") Integer albumId) {
		Album album = null;
		//find this album in cached object if it dosen't exist try finding it in DB
		if(CachedObjects.getInstance().containsAlbum(albumId)){
			album = CachedObjects.getInstance().getOneAlbum(albumId);
		}else{
			try {
				albumDAO.getAllAlbums();
			} catch (ValidationException e) {
				System.out.println("Validation error");
			} catch (SQLException e) {
				System.out.println("Can't connect to DB");
			}
			//try again to find it
			if(CachedObjects.getInstance().containsAlbum(albumId)){
				album = CachedObjects.getInstance().getOneAlbum(albumId);
			}
		}
		//return this object 
		return album;
	}


	@RequestMapping(value="/add",method = RequestMethod.POST)
	public void addAlbum(HttpServletRequest request, HttpSession session) {
		String name = request.getParameter("name");
		System.out.println(name);
		String description = request.getParameter("description");
		System.out.println(description);
		User user = (User)session.getAttribute("user");
		System.out.println(user.toString());
		try {
			albumDAO.createAlbum(user, name, description);
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@RequestMapping(value="/change",method = RequestMethod.PUT)
	public String changeAlbum(Model model, HttpServletRequest request) {
		//get the parameters we want to change
		//change them in our DB and cached obj
		//return this album id		
		return "album";
	}

	@RequestMapping(value="/{album_id}",method = RequestMethod.DELETE)
	public String deleteAlbum(Model model, @PathVariable("album_id") Integer productId) {
		//get this album from cached objects
		//delete it from the DB and then from the cached obj
		//redirect to profile page	
		return "redirect:index.html";
	}	
}

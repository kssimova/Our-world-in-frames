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
	
	@RequestMapping(value="/get",method = RequestMethod.GET)
	public Album getAlbum(Model model, HttpServletRequest request, HttpSession session) {
		Long albumId = Long.parseLong(request.getParameter("albumId"));		
		Album album = null;
		if(session.getAttribute("logged")!= null){
			if(albumId != null){
				if(CachedObjects.getInstance().getAllAlbums().isEmpty()){
					try {
						albumDAO.getAllAlbums();

					} catch (ValidationException | SQLException e) {
						System.out.println("i cant get all posts");
					}
				}
				album = CachedObjects.getInstance().getOneAlbum(albumId);
				System.out.println(album.toString());
			}
		}
		return album;
	}


	@RequestMapping(value="/add",method = RequestMethod.POST)
	public void addAlbum(HttpServletRequest request, HttpSession session) {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		User user = (User)session.getAttribute("user");
		try {
			albumDAO.createAlbum(user, name, description);
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//not ready
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

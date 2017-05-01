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
import com.ourwif.DAO.UserDAO;
import com.ourwif.model.Album;
import com.ourwif.model.Basic;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.User;

@RestController
@RequestMapping(value= "/album")
public class AlbumController {
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
	UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
	
	@RequestMapping(value="/get",method = RequestMethod.POST)
	public Album getAlbum(HttpServletRequest request, HttpSession session) {
		String s = request.getParameter("albumId");
		if(s.contains("albumId=")){
			s = s.substring(s.indexOf("albumId=")+8);
		}
		Long albumId = Long.parseLong(s);
		Album album = null;
		if(session.getAttribute("logged")!= null){
			if(albumId != null){
				if(CachedObjects.getInstance().getAllUsers().isEmpty()){
					try {
						userDAO.getAllUsers();

					} catch (ValidationException | SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				album = CachedObjects.getInstance().getOneAlbum(albumId);
			}
		}
		return album;
	}


	@RequestMapping(value="/add",method = RequestMethod.POST)
	public void addAlbum(HttpServletRequest request, HttpSession session) {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		User user = (User)session.getAttribute("user");
		if(validInfo(request)){
			try {
	
				Album album = albumDAO.createAlbum(user, name, description);
				user.putAlbum(album);
			} catch (ValidationException e) {
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	private boolean validInfo(HttpServletRequest request) {
		String name = request.getParameter("name");
		name.trim();
		if(name.isEmpty() || name.length() > 50){
			return false;
		}
		String desc = request.getParameter("description");
		desc.trim();
		if(desc.length() > 200 ){
			return false;
		}
		return true;
	}

	
	@RequestMapping(value="/valid",method = RequestMethod.POST)
	private Basic getError(HttpServletRequest request) {
		Basic basic = new Basic();
		basic.setStatus(false);
		if(!validInfo(request)){
			String name = request.getParameter("name");
			name.trim();
			if(name.isEmpty() && name.length() < 2){
				basic.addError("NameError", "Album name must be at least 3 character long!");	
			}
			if(name.length() > 50){
				basic.addError("NameLength", "Album name must be less than 50 character!");
			}
			String desc = request.getParameter("description");
			desc.trim();
			if(desc.length() > 200 ){
				basic.addError("DescriptionLength", "Album description must be less than 200 character!");
			}
			return basic;
		}
		basic.setStatus(true);
		return basic;
	}	
	
}

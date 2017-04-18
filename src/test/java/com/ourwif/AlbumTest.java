package com.ourwif;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ourwif.DAO.AlbumDAO;
import com.ourwif.DAO.PostDAO;
import com.ourwif.DAO.UserDAO;
import com.ourwif.model.Album;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.Post;
import com.ourwif.model.User;


public class AlbumTest {

	public static void main(String[] args) {
		
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		
		System.out.println("---------------------TEST 1 ---------------------");
		System.out.println("------------------CREATE NEW ALBUM-------------------");	
		
		User u = null;
		Album a = null;
		
		try {
			userDAO.getAllUsers();
		} catch (ValidationException e2) {
			System.out.println("ops");
		}	
			u = CachedObjects.getInstance().getOneUser("user123");
		
		System.out.println("This user have : " + u.getAlbums().size() + " albums");
		
		try {
			a = albumDAO.createAlbum(u, "new album", "default album");
		} catch (ValidationException  | SQLException e) {
			System.out.println("ops");
		}
		System.out.println("This user have : " + u.getAlbums().size() + " albums");
		
		
		System.out.println("---------------------TEST 2 ---------------------");
		System.out.println("------------------CHANGE DESCRIPTION -------------------");
		
		System.out.print("Before... ");
		System.out.println("Album description: " + a.getDescription());
		
		try {
			albumDAO.editAlbumInfo(a, u, "new description ");
		} catch (ValidationException e) {
			System.out.println("ops");
		}
		
		System.out.print("After... ");
		System.out.println("Album description: " + a.getDescription());
		//WOrking
		
		System.out.println("---------------------TEST 3 ---------------------");
		System.out.println("------------------CHANGE NAME -------------------");
		
		System.out.print("Before... ");
		System.out.println("Album name: " + a.getName());
		
		try {
			albumDAO.editAlbumName(a, u, "newwwwwww name");
		} catch (ValidationException e) {
			System.out.println("ops");
		}
		
		System.out.print("After... ");
		System.out.println("Album name: " + a.getName());
		//working
		
		System.out.println("---------------------TEST 4 ---------------------");
		System.out.println("------------------DELETE ALBUM -------------------");
		
//		System.out.println("This user have : " + u.getAlbums().size() + " albums");
//		
//		try {
//			AlbumDAO.getInstance().deleteAlbum(u, a);
//		} catch (ValidationException | SQLException  e) {
//			System.out.println("ops");
//		}
//		
//		System.out.println("This user have : " + u.getAlbums().size() + " albums");
		//working
		
		System.out.println("---------------------TEST 5 ---------------------");
		System.out.println("------------------GET ALBUM FROM DB -------------------");
		
		Album newAlbum = null;
		CachedObjects.getInstance().addUser(u);
		
		System.out.println("Do we have ne album: " + (newAlbum != null));
		
		try {
			newAlbum = albumDAO.getAlbum(u, a.getAlbumId());
		} catch (ValidationException e) {
			System.out.println("ops");
		}
		System.out.println("Do we have ne album now: " + (newAlbum != null));
		//working
		
		System.out.println("---------------------TEST 6 ---------------------");
		System.out.println("------------------GET ALL ALBUM FROM DB -------------------");
		
		System.out.println("First delete all albums:");
		CachedObjects.getInstance().clearAlbums();
		System.out.println("All albums: " + CachedObjects.getInstance().getAllAlbums().toString());
		
		try {
			albumDAO.getAllAlbums();
		} catch (ValidationException e) {
			System.out.println("ops");
		}
		
		System.out.println("Try getting all albums");
		System.out.println("All albums: " + CachedObjects.getInstance().getAllAlbums().toString());
	
		//working
		
		
		
		System.out.println("---------------------TEST 6 ---------------------");
		System.out.println("------------------GET ONE USER ALBUM FROM DB -------------------");
		
		System.out.println("First delete all albums:");
		CachedObjects.getInstance().clearAlbums();
		System.out.println("All albums: " + CachedObjects.getInstance().getAllAlbums().toString());
		
		try {
			albumDAO.getUserAlbums(u);
		} catch (ValidationException e) {
			System.out.println("ops");
		}
		
		System.out.println("Try getting all albums");
		System.out.println("All albums: " + CachedObjects.getInstance().getAllAlbums().toString());
	
		//working
		
		
		
	}
}

package com.ourwif;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.xml.bind.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ourwif.DAO.AlbumDAO;
import com.ourwif.DAO.UserDAO;
import com.ourwif.model.Album;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.User;

public class AlbumDAOTest {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	User u = null;
	Album a = null;
	Album newAlbum = null;
	
	@Before
	public void setUp(){
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			userDAO.getAllUsers();
		} catch (ValidationException e2) {
			System.out.println("ops");
		}
		u = CachedObjects.getInstance().getOneUser("user123");
	}

	@Test
	public void testCreateAlbum() {	
		AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
		
		try {
			assertSame(new Album("", "", LocalDate.now(), u), albumDAO.createAlbum(u, "new album", "default album"));
		} catch (ValidationException | SQLException e1) {
			System.out.println("ops");
		}
		
		fail("CreateAlbum test failed");
	}

	@Test
	public void testDeleteAlbum() {
		AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
		System.out.println("This user have : " + u.getAlbums().size() + " albums");
		
		try {
			albumDAO.deleteAlbum(u, a);
		} catch (ValidationException | SQLException  e) {
			System.out.println("ops");
		}
		
		System.out.println("This user have : " + u.getAlbums().size() + " albums");
		fail("Not yet implemented");
	}

	@Test
	public void testEditAlbumName() {
		AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
		System.out.print("Before... ");
		System.out.println("Album name: " + a.getName());
		
		try {
			albumDAO.editAlbumName(a, u, "newwwwwww name");
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.print("After... ");
		System.out.println("Album name: " + a.getName());
		
		fail("Not yet implemented");
	}

	@Test
	public void testEditAlbumInfo() {
		AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
		System.out.print("Before... ");
		System.out.println("Album description: " + a.getDescription());
		
		try {
			albumDAO.editAlbumInfo(a, u, "new description ");
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.print("After... ");
		System.out.println("Album description: " + a.getDescription());
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllAlbums() {
		AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
		
		System.out.println("First delete all albums:");
		System.out.println("All albums: " + CachedObjects.getInstance().getAllAlbums().toString());
		
		try {
			albumDAO.getAllAlbums();
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Try getting all albums");
		System.out.println("All albums: " + CachedObjects.getInstance().getAllAlbums().toString());
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserAlbums() {
		AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
		
		System.out.println("First delete all albums:");
		System.out.println("All albums: " + CachedObjects.getInstance().getAllAlbums().toString());
		
		try {
			albumDAO.getUserAlbums(u);
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Try getting all albums");
		System.out.println("All albums: " + CachedObjects.getInstance().getAllAlbums().toString());
		
		fail("Not yet implemented");
	}

}

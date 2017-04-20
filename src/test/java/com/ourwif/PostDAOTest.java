package com.ourwif;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ourwif.DAO.PostDAO;
import com.ourwif.model.Album;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.Post;
import com.ourwif.model.User;

public class PostDAOTest {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	User u = null;
	Post p = null;
	Album a = null;
	TreeSet<String> tags = new TreeSet<>();
	
	@Before
	public void setUp(){
		tags.add("cute");
		tags.add("cat");
		try {
			u = new User("werewolf", "werewolfss@abv.bg", "12345", 4L);
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}
		a = u.getAlbums().get(0);
		try {
			p = new Post(u, "cat", "one cat", LocalDate.now(), "http://i.imgur.com/EOdEWqM.png", tags);
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}
		p.setPostId("EOdEWqM");
	}

	@Test
	public void testGetPost() {
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		Post postchence = null;
		System.out.println("Get the post... ");
		try {
			postchence = postDAO.getPost("EOdEWqM", "sasdafs");
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		fail("Not yet implemented");
	}

	@Test
	public void testCreatePost() {	
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		try {
			postDAO.createPost(u, p.getName(), p.getDescription(), p.getDateCreated(), p.getPicturePath(), tags, a, "EOdEWqM", "sasdafs");
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("All albums in cached objects : " + CachedObjects.getInstance().getAllPosts().size());
		Post post4e  = CachedObjects.getInstance().getOnePost(p.getPostId(), a.getAlbumId());
		
		System.out.println("Name: " + post4e.getName());
		System.out.println("Link: " + post4e.getPicturePath());
		System.out.println("ID: " + post4e.getPostId());
		
		fail("Not yet implemented");
	}

	@Test
	public void testEditPostName() {
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		System.out.println("Old name: " + p.getName());
		try {
			p = postDAO.editPostName(p, u, "Whaaat?");
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("New name: " + p.getName());
		fail("Not yet implemented");
	}

	@Test
	public void testEditPostInfo() {
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		System.out.println("Old description: " + p.getDescription());	
		try {
			p = postDAO.editPostInfo(p, u, "Cats cats cat1");
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("New description: " + p.getDescription());
		fail("Not yet implemented");
	}

	@Test
	public void testEditTags() {	
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");

		
		TreeSet<String> newTags = new TreeSet<>();
		newTags.add("nope");
		newTags.add("lol");
		
		System.out.println("Old tags: " + p.getTags().toString());
		try {
			p = postDAO.editTags(p, u, newTags);
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("New tags: " + p.getTags().toString());
		
		
		
		fail("Not yet implemented");
	}

	@Test
	public void testAddLike() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLike() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePost() {
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		System.out.println("Post exists: " + CachedObjects.getInstance().getAllPosts().get(a.getAlbumId()).containsKey(p.getPostId()));
		
		try {
			postDAO.deletePost(p, u, a);
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Post exists: " + CachedObjects.getInstance().getAllPosts().get(a.getAlbumId()).containsKey(p.getPostId()));
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllPosts() {
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		
		CachedObjects.getInstance().clearPosts();
		System.out.println(CachedObjects.getInstance().getAllPosts().size());
		
		try {
			postDAO.getAllPosts();
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(CachedObjects.getInstance().getAllPosts().size());
		
		fail("Not yet implemented");
	}

}

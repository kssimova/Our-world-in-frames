package com.ourwif;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ourwif.DAO.CommentDAO;
import com.ourwif.DAO.PostDAO;
import com.ourwif.model.Album;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.Comment;
import com.ourwif.model.Post;
import com.ourwif.model.User;

public class CommentDAOTest {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
	CommentDAO commentDAO = (CommentDAO) context.getBean("CommentDAO");
	User u = null;
	Post p = null;
	Album a = null;
	Comment c = null;
	TreeSet<String> tags = new TreeSet<>();
	
	@Before
	public void setUp(){
		tags.add("cute");
		tags.add("cat");	
		try {
			u = new User("werewolf", "werewolfss@abv.bg", "12345", 4L);
		} catch (ValidationException e) {
			System.out.println("cant get this user");
		}
		a = u.getAlbums().get(0);
		try {
			p = new Post(u, "cat", "one cat", LocalDate.now(), "http://i.imgur.com/EOdEWqM.png", tags);
		} catch (ValidationException e) {
			System.out.println("cant create post");
		}	
		p.setPostId("EOdEWqM");
		try {
			postDAO.createPost(u, p.getName(), p.getDescription(), p.getDateCreated(), p.getPicturePath(), tags, a, "EOdEWqM", "sasdafs");
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void testGetAllComments() {
		CachedObjects.getInstance().addUser(u);	
		TreeMap<Long, Comment> comments = null;
		
		try {
			comments = commentDAO.getAllComments(p);
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		fail("Not yet implemented");
	}

	@Test
	public void testCreateComment() {
		System.out.println("Comments before: " + p.getComments().size());
		try {
			c = commentDAO.createComment(p, u, "Sooo cute");
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Comments after: " + p.getComments().size());
		fail("Not yet implemented");
	}

	@Test
	public void testEditComment() {
		
		for(Comment comm : p.getComments()){
			System.out.println("Comment before:" + comm.getContent());
		}
		
		try {
			commentDAO.editComment(p, c, "this i my new comment");
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		for(Comment comm : p.getComments()){
			System.out.println("Comment before:" + comm.getContent());
		}
		
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteComment() {
		
		System.out.println(p.getComments().toString());
		
		try {
			commentDAO.deleteComment(p, u, c);
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(p.getComments().toString());
		
		fail("Not yet implemented");
	}

}

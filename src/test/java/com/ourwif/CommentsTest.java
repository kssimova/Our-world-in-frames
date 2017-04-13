package com.ourwif;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ourwif.DAO.CommentDAO;
import com.ourwif.DAO.PostDAO;
import com.ourwif.model.Album;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.Comment;
import com.ourwif.model.Post;
import com.ourwif.model.User;


public class CommentsTest {
	public static void main(String[] args) throws SQLException {
		
    	ApplicationContext context =
        		new ClassPathXmlApplicationContext("Spring-Module.xml");
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		CommentDAO commentDAO = (CommentDAO) context.getBean("CommentDAO");
		
		System.out.println("---------------------TEST 1 ---------------------");
		System.out.println("------------------CREATE NEW COMMENT-------------------");
		
		System.out.println("Creating post to test on... ");
		
		User u = null;
		Post p = null;
		Album a = null;
		Comment c = null;
		TreeSet<String> tags = new TreeSet<>();
		tags.add("cute");
		tags.add("cat");	
		try {
			u = new User("werewolf", "werewolfss@abv.bg", "12345", 4L);
		} catch (ValidationException e) {
			System.out.println("ops" + e.getMessage());
		}				
		try {
			a = new Album("default", "def",  LocalDate.now(), u);
			a.setAlbumId(45);
			
			p = new Post(u, "cat", "one cat", LocalDate.now(), "http://i.imgur.com/EOdEWqM.png", tags);
			
			TreeSet<String> tagsss = new TreeSet<>();
			
			tagsss.addAll(p.getTags());
			
			//Don't forget to set the ID
			p.setPostId("EOdEWqM");
	
			postDAO.createPost(u, p.getName(), p.getDescription(), p.getDateCreated(), p.getPicturePath(), tagsss, a, "EOdEWqM", "sasdafs");
			
		} catch (ValidationException e) {
			System.out.println("ops" + e.getMessage());
		}
		System.out.println("Post created");
		System.out.println("Lets make 1 comment.");
		System.out.println("Comments before: " + p.getComments().size());
		
		try {
			c = commentDAO.createComment(p, u, "Sooo cute");
		} catch (ValidationException e) {
			System.err.println("ops!");
		}
		
		System.out.println("Comments after: " + p.getComments().size());
		//WORKING
		
		System.out.println("---------------------TEST 2 ---------------------");
		System.out.println("------------------CHANGE CONTENTt-------------------");
		
		for(Comment comm : p.getComments()){
			System.out.println("Comment before:" + comm.getContent());
		}
		
		try {
			commentDAO.editComment(p, c, "this i my new comment");
		} catch (ValidationException e) {
			System.out.println("ops!!");
		}
		
		for(Comment comm : p.getComments()){
			System.out.println("Comment before:" + comm.getContent());
		}
		//working
		
	
		
		System.out.println("---------------------TEST 3 ---------------------");
		System.out.println("------------------DELETE COMMENT-------------------");
		
//		System.out.println(p.getComments().toString());
//		
//		try {
//			CommentDAO.getInstance().deleteComment(p, u, c);
//		} catch (ValidationException e1) {
//			System.out.println("ops");
//		}
//		
//		System.out.println(p.getComments().toString());
		
		//working
		
		System.out.println("---------------------TEST 4 ---------------------");
		System.out.println("------------------GET ALL COMMENTS FROM DB-------------------");
		
		CachedObjects.getInstance().addUser(u);	
		TreeMap<Long, Comment> comments = null;
		try {
			comments = commentDAO.getAllComments(p);
		} catch (ValidationException e) {
			System.out.println("ops");
		}
		
		//WORKING
	}
}

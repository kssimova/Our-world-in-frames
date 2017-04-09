package test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import DAO.CommentDAO;
import DAO.PostDAO;
import model.Album;
import model.CachedObjects;
import model.Comment;
import model.Post;
import model.User;

public class CommentsTest {
	public static void main(String[] args) throws SQLException {
		
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
			u = new User("werewolf", "werewolfss@abv.bg", "12345", 1L);
		} catch (ValidationException e) {
			System.out.println("ops" + e.getMessage());
		}				
		try {
			a = new Album("default", "def",  LocalDate.now(), u);
			a.setAlbumId(1);
			
			p = new Post(u, "cat", "one cat", LocalDate.now(), "http://i.imgur.com/EOdEWqM.png", tags);
			
			TreeSet<String> tagsss = new TreeSet<>();
			
			tagsss.addAll(p.getTags());
			
			//Don't forget to set the ID
			p.setPostId("EOdEWqM");
	
			PostDAO.getInstance().makePost(u, p.getName(), p.getDescription(), p.getDateCreated(), p.getPicturePath(), tagsss, a, "EOdEWqM", "sasdafs");
			
		} catch (ValidationException e) {
			System.out.println("ops" + e.getMessage());
		}
		System.out.println("Post created");
		System.out.println("Lets make 1 comment.");
		System.out.println("Comments before: " + p.getComments().size());
		
		try {
			c = CommentDAO.getInstance().makeComment(p, u, null , "Sooo cute");
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
			CommentDAO.getInstance().editComment(p, c, "this i my new comment");
		} catch (ValidationException e) {
			System.out.println("ops!!");
		}
		
		for(Comment comm : p.getComments()){
			System.out.println("Comment before:" + comm.getContent());
		}
		//working
		
		System.out.println("---------------------TEST 3 ---------------------");
		System.out.println("------------------ADD SUBCOMMENT-------------------");
		
		
		System.out.println("Before..");
		for(Comment comm1 : p.getComments()){
			System.out.println("Comments before:" + comm1.getContent());
			if(comm1.getComment() != null){
				System.out.println("My Sup comment is: " + comm1.getComment().getContent());
			}
		}
		
		try {
			CommentDAO.getInstance().makeComment(p, u, c, "I'm a subcomment");
		} catch (ValidationException e) {
			System.out.println("Ops");
		}	
		
		System.out.println("After..");
		for(Comment comm1 : p.getComments()){
			System.out.println("Comments after: " + comm1.getContent());
			System.out.println("My Sup comment is: " + comm1.getComment().getContent());
		}
		//working
		
		System.out.println("---------------------TEST 5 ---------------------");
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
		
		System.out.println("---------------------TEST 5 ---------------------");
		System.out.println("------------------GET ALL COMMENTS FROM DB-------------------");
		
		CachedObjects.getInstance().addUser(u);	
		TreeMap<Long, Comment> comments = null;
		try {
			comments = CommentDAO.getInstance().getAllComments("EOdEWqM");
		} catch (ValidationException e) {
			System.out.println("ops");
		}
		
		for(Entry <Long, Comment> comm : comments.entrySet()){
				System.out.println(comm.getValue().getAsJSON());
		}
		//WORKING
	}
}

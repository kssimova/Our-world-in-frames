package test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import DAO.PostDAO;
import model.Album;
import model.Post;
import model.User;

public class Main {
	public static void main(String [] args) throws SQLException{
		
		User u = null;
		TreeSet<String> tags = new TreeSet<>();
		tags.add("cute");
		tags.add("cat");
		
		try {
			u = new User("werewolf", "werewolfss@abv.bg", "12345a", 1L);
		} catch (ValidationException e) {
			System.out.println("ops" + e.getMessage());
		}				
		try {
			Album a = new Album("default", "def",  LocalDate.now(), u);
			a.setAlbumId(1);
			
			Post p = new Post(u, "cat", "one cat", LocalDate.now(), "http://i.imgur.com/EOdEWqM.png", tags);
			TreeSet<String> tagsss = new TreeSet<>();
			tagsss.addAll(p.getTags());
			PostDAO.getInstance().makePost(u, p.getName(), p.getDescription(), p.getDateCreated(), p.getPicturePath(), tagsss, a, "EOdEWqM", "sasdafs");
		} catch (ValidationException e) {
			System.out.println("ops" + e.getMessage());
		}
		
		//WORKING 
		//add tag to database 
        //add post too
		
		//TODO test DAO editing post and deletion of posts
	}
}

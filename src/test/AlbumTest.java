package test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import DAO.AlbumDAO;
import DAO.PostDAO;
import model.Album;
import model.Post;
import model.User;

public class AlbumTest {

	public static void main(String[] args) {
		
		System.out.println("---------------------TEST 1 ---------------------");
		System.out.println("------------------CREATE NEW ALBUM-------------------");	
		
		User u = null;
		Album a = null;
		Post p = null;
		
		try {
			u = new User("werewolf", "werewolfss@abv.bg", "12345", 1L);
		} catch (ValidationException e) {
			System.out.println("ops" + e.getMessage());
		}		
		
		System.out.println("This user have : " + u.getAlbums().size() + " albums");
		
		try {
			a = AlbumDAO.getInstance().createAlbum(u, "new album", "default album");
		} catch (ValidationException  | SQLException e) {
			System.out.println("ops");
		}
		System.out.println("This user have : " + u.getAlbums().size() + " albums");
		//working
		
		//put 1 post in this album
		TreeSet<String> tags = new TreeSet<>();
		tags.add("cute");
		tags.add("cat");
		try {
			p = new Post(u, "cat", "one cat", LocalDate.now(), "http://i.imgur.com/EOdEWqM.png", tags);
			TreeSet<String> tagsss = new TreeSet<>();		
			tagsss.addAll(p.getTags());
			p.setPostId("EOdEWqM");
			PostDAO.getInstance().createPost(u, p.getName(), p.getDescription(), p.getDateCreated(), p.getPicturePath(), tagsss, a, "EOdEWqM", "sasdafs");
		} catch (ValidationException | SQLException e1) {
			System.out.println("ops");
		}		
	
		
		System.out.println("---------------------TEST 2 ---------------------");
		System.out.println("------------------CHANGE DESCRIPTION -------------------");
		
		System.out.print("Before... ");
		System.out.println("Album description: " + a.getDescription());
		
		try {
			AlbumDAO.getInstance().editAlbumInfo(a, u, "new description ");
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
			AlbumDAO.getInstance().editAlbumName(a, u, "newwwwwww name");
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
		System.out.println("------------------GET POST FROM DB -------------------");
		
		Album newAlbum = null;
		
		System.out.println("Do we have ne album: " + (newAlbum != null));
		
		try {
			newAlbum = AlbumDAO.getInstance().getAlbum(u, a.getAlbumId());
		} catch (ValidationException e) {
			System.out.println("ops");
		}
		
		System.out.println("Do we have ne album now: " + (newAlbum != null));
		
	}
}

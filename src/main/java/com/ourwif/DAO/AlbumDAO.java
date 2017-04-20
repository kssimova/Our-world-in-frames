package com.ourwif.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.sql.DataSource;
import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.jdbc.Connection;
import com.ourwif.model.Album;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.Post;
import com.ourwif.model.User;


public class AlbumDAO {

	private DataSource dataSource;
	private ApplicationContext context = null;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// create new album
	public Album createAlbum(User user, String name, String description) throws ValidationException, SQLException{
 		Album album = null;
		String sql = "INSERT INTO albums (name, description, date_created, user_id) " +
 					"VALUES (?, ?, ?, ?)";
		Connection conn = null;
 		try {
			conn = (Connection) dataSource.getConnection();
	 		PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	 		st.setString(1, name);
	 		st.setString(2, description);
	 		st.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
	 		st.setLong(4, user.getUserId());
	 		st.execute();
	 		ResultSet res = st.getGeneratedKeys();
	 		res.next();
	 		long albumId = res.getLong(1);
	 		album = user.createAlbum(name, description, albumId);
		}finally {
			conn.close();
		}
 		return album;
 	}
	
	//delete album	
	public void deleteAlbum(User user, Album album) throws ValidationException, SQLException{
    	context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		Connection conn = null;
 		try {
 			conn = (Connection) dataSource.getConnection();
			conn.setAutoCommit(false);		
			//delete all photos in this album
			for(Post p : album.getPhotos()){
				postDAO.deletePost(p, user, album);
			}			
			//delete album
			String sql = "DELETE FROM albums WHERE album_id = ? ";
	 		PreparedStatement st = conn.prepareStatement(sql);
	 		st.setLong(1, album.getAlbumId());
	 		st.execute();
		} catch (SQLException e) {
				try {
				conn.rollback();
 				System.out.println("Error#1 in AlbumDAO. Error message: " + e.getMessage());
 				throw e;
			} catch (SQLException e1) {
				System.out.println("Error#2 in AlbumDAO. Error message: " + e1.getMessage());
				throw e1;
			}
		}finally{
			conn.close();
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("Error#3 in AlbumDAO. Error message: " + e.getMessage());
				throw e;
			}
		}
 		user.deleteAlbum(album);
 	}
	
	// change album name
	public Album editAlbumName(Album album, User user, String str) throws ValidationException, SQLException{
		String sql = "UPDATE albums SET name = ? WHERE album_id = ? ";
 		PreparedStatement st;
 		Connection conn = null;
	 	conn = (Connection) dataSource.getConnection();
	 	try{
	 		conn.setAutoCommit(false);
	 		st = conn.prepareStatement(sql);
	 		st.setString(1, str);
	 		st.setLong(2, album.getAlbumId());
	 		st.execute();
	 		album.changeName(str);
	 	}catch(SQLException e){
			try {
			conn.rollback();
				System.out.println("Error#1 in AlbumDAO. Error message: " + e.getMessage());
				throw e;
			} catch (SQLException e1) {
				System.out.println("Error#2 in AlbumDAO. Error message: " + e1.getMessage());
				throw e1;
			}
		}finally{
			conn.close();
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("Error#3 in AlbumDAO. Error message: " + e.getMessage());
				throw e;
			}
		}
		return album;
		
 	}
		
	//change album description
 	public Album editAlbumInfo(Album album, User user, String str) throws ValidationException, SQLException{
		String sql = "UPDATE albums SET description = ? WHERE album_id = ? ";
 		PreparedStatement st;
 		Connection conn = null;
	 	try {
			conn = (Connection) dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, str);
			st.setLong(2, album.getAlbumId());
		 	st.execute();
			album.changeDescription(str);
		}finally{
			conn.close();
		}
		return album;
 	}
 		
	//create album from database
 	public Album getAlbum(User user, long albumId) throws ValidationException, SQLException {
  		TreeSet<Post> posts = new TreeSet<>();
  		Album album = null;
 		PreparedStatement st = null;
  		String sql = "";
 		ResultSet result = null;
    	context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		Connection conn = null;
		try{
			conn = (Connection) dataSource.getConnection();
			conn.setAutoCommit(false);
			//get posts
			sql = "SELECT p.user_id, p.post_id, p.name, p.description, p.delete_hash, p.date_created, p.picture_path "
					+ "FROM posts p JOIN albums_posts ap ON p.post_id = ap.post_id WHERE ap.album_id = ? ;";
 			st = conn.prepareStatement(sql);
 			st.setLong(1, albumId);
 			st.execute();
 			result = st.getResultSet();
 			while(result.next()){
 				Post p = postDAO.getPost(result.getString("post_id"), result.getString("delete_hash"));
 				posts.add(p);
 			}
 			sql = "SELECT a.name, a.description, a.date_created, a.user_id FROM albums a wHERE a.album_id = ? ;";
 			//create the album
 			st = conn.prepareStatement(sql);
 			st.setLong(1, albumId);
 			st.execute();
 			//get result
 			result = st.getResultSet();
 			while(result.next()){
 				album = new Album(result.getString("name"), result.getString("description"), result.getDate("date_created").toLocalDate(), user);
 			}
 			//fill this album
 			for(Post p : posts){
 				album.addPosts(p);
 			}	
 			album.setAlbumId(albumId);
 			CachedObjects.getInstance().addAlbums(album);
 		}finally{
 	 		conn.close();
 		}
 		return album;
	}
 	
 	//get all albums From DB
 	public void getAllAlbums() throws ValidationException, SQLException {
 		TreeSet<Long> allAlbumIDs = new TreeSet<>();
  		TreeSet<Post> posts = new TreeSet<>();
  		Album album = null;
 		PreparedStatement st = null;
  		String sql = "";
 		ResultSet result = null;
    	context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		Connection conn = null;
		try{
			conn = (Connection) dataSource.getConnection();
			//select all albums
			sql = "SELECT album_id from albums";
			st = conn.prepareStatement(sql);
			st.execute();
			result = st.getResultSet();
			while(result.next()){
				allAlbumIDs.add(result.getLong("album_id"));
			}		
			//get posts
			for(Long albumId : allAlbumIDs){
				sql = "SELECT p.user_id, p.post_id, p.name, p.description, p.delete_hash, p.date_created, p.picture_path "
						+ "FROM posts p JOIN albums_posts ap ON p.post_id = ap.post_id WHERE ap.album_id = ? ;";
				st = conn.prepareStatement(sql);
				st.setLong(1, albumId);
				st.execute();
				result = st.getResultSet();
				while(result.next()){
					Post p = postDAO.getPost(result.getString("post_id"), result.getString("delete_hash"));
					posts.add(p);
				}
				sql = "SELECT a.name, a.description, a.date_created, a.user_id FROM albums a wHERE a.album_id = ? ;";
				//create the album
				st = conn.prepareStatement(sql);
				st.setLong(1, albumId);
				st.execute();
				//get result
				result = st.getResultSet();
				while(result.next()){
					User user = CachedObjects.getInstance().getOneUser(result.getLong("user_id"));
					album = new Album(result.getString("name"), result.getString("description"), result.getDate("date_created").toLocalDate(), user);
					album.setAlbumId(albumId);
					CachedObjects.getInstance().addAlbums(album);
				}
				//fill this album
				for(Post p : posts){
					album.addPosts(p);
				}	
			}
		}finally {
	 		conn.close();
		}
	}
 	
	//get all albums From DB
 	public TreeMap <Long, Album> getUserAlbums(User user) throws ValidationException, SQLException {
 		TreeMap <Long, Album> albums = new TreeMap<>();
		TreeMap <String, String> postIds = new TreeMap<>();
 		TreeSet <Long> allAlbumIDs = new TreeSet<>();
  		Album album = null;
  		String sql = "";
 		PreparedStatement st = null;
 		ResultSet result = null;
    	context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		CommentDAO commentDAO = (CommentDAO) context.getBean("CommentDAO");
		Connection conn = null;
		try{
			conn = (Connection) dataSource.getConnection();
			//select all album id-s
			sql = "SELECT album_id from albums where user_id = ?";
			st = conn.prepareStatement(sql);
			st.setLong(1, user.getUserId());
			st.execute();
			result = st.getResultSet();
			while(result.next()){
				//store them
				allAlbumIDs.add(result.getLong("album_id"));
			}		
			//get albums from there id-s
			for(Long albumId : allAlbumIDs){
				sql = "SELECT a.name, a.description, a.date_created, a.user_id FROM albums a wHERE a.album_id = ? ;";
				//create the album
				st = conn.prepareStatement(sql);
				st.setLong(1, albumId);
				st.execute();
				//get result
				result = st.getResultSet();
				while(result.next()){
					//store them
					album = new Album(result.getString("name"), result.getString("description"), result.getDate("date_created").toLocalDate(), user);
					album.setAlbumId(albumId);
					albums.put(albumId, album);
				}
			}
			//select all posts id-s and delete hash-s from one album with specific id
			for(Long albumId : allAlbumIDs){
				sql = "SELECT p.post_id, p.delete_hash "
						+ "FROM posts p JOIN albums_posts ap ON p.post_id = ap.post_id WHERE ap.album_id = ? ;";
				st = conn.prepareStatement(sql);
				st.setLong(1, albumId);
				st.execute();
				result = st.getResultSet();
				while(result.next()){
					postIds.put(result.getString("post_id"), result.getString("delete_hash"));
				}
			}
			//get full posts
			for(Entry <String, String> e : postIds.entrySet() ){
				//get all post-s 			
				TreeSet<String> tags = new TreeSet<>();
				Post post = null;
				//get tags
				sql = "SELECT t.name FROM tags t "
						+ "JOIN tags_posts tp ON t.tag_id = tp.tag_id "
						+ "WHERE tp.post_id = ? ";
				st = conn.prepareStatement(sql);
				st.setString(1, e.getKey());
				st.execute();
				result = st.getResultSet();
				while(result.next()){
					tags.add(result.getString("name"));
				}
				//create post
				sql = "SELECT name, user_id, description, album_id, date_created, picture_path "
						+ "FROM posts WHERE post_id = ? ";
				//initialization
				st = conn.prepareStatement(sql);
				st.setString(1, e.getKey());
				st.execute();
				//get result
				result = st.getResultSet();
				while(result.next()){
					post = new Post(user, result.getString("name"), result.getString("description"), result.getDate("date_created").toLocalDate(), result.getString("picture_path"), tags, e.getKey(), e.getValue());
					commentDAO.getAllComments(post);	
					album = albums.get(result.getLong("album_id"));
					album.addPosts(post);
				}						
			}
		}finally{
	 		conn.close();
		}
		return albums;	
	} 	
}
package com.ourwif.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

public class PostDAO {
	
	private DataSource dataSource;
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//get Post from DB
	public Post getPost(String postId, String deleteHash) throws ValidationException, SQLException {
		TreeSet<String> tags = new TreeSet<>();
  		PreparedStatement st = null;
  		Post post = null;
  		String sql = "";
 		ResultSet result = null;
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		CommentDAO commentDAO = (CommentDAO) context.getBean("CommentDAO");
		Connection conn = null;
 		conn = (Connection) dataSource.getConnection();
 		conn.setAutoCommit(false);
 		//get tags
 		sql = "SELECT t.name FROM tags t "
 			+ "JOIN tags_posts tp ON t.tag_id = tp.tag_id "
 			+ "WHERE tp.post_id = ? ";
 		st = conn.prepareStatement(sql);
 		st.setString(1, postId);
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
	 	st.setString(1, postId);
	 	st.execute();
	 	//get result
	 	result = st.getResultSet();
	 	while(result.next()){
		 	User user = CachedObjects.getInstance().getOneUser(result.getLong("user_id"));
		 	post = new Post(user, result.getString("name"), result.getString("description"), result.getDate("date_created").toLocalDate(), result.getString("picture_path"), tags, postId, deleteHash);
		 	commentDAO.getAllComments(post);
 		}
 		return post;
	}
	
	// create new Post
	public void createPost(User user, String name, String description, LocalDate dateCreated, String picturePath, TreeSet<String> tags, Album album, String postId, String deleteHash) throws SQLException, ValidationException{
		Post post = null;
		String sql = null;
		PreparedStatement st = null;
		Connection conn = null;
 		try {
 			conn = (Connection) dataSource.getConnection();
			conn.setAutoCommit(false);
		 	try {
				sql ="INSERT INTO posts (user_id, name, description, album_id, date_created, picture_path, post_id, delete_hash)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
				st = conn.prepareStatement(sql);	
			 	st.setLong(1, user.getUserId());
			 	st.setString(2, name);
				st.setString(3, description);
				st.setLong(4, album.getAlbumId());
			 	st.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			 	st.setString(6, picturePath); 
			 	st.setString(7, postId);
			 	st.setString(8, deleteHash);
				st.execute();
			} catch (SQLException e2) {
				System.out.println("Error#1 in PostDAO. Error message: " + e2.getMessage());
				throw e2;
			}
		 	try {
			 	post = new Post(user, name, description, dateCreated, picturePath, tags, postId, deleteHash);
			 	editTags(post, user, tags);
				post.addTags(tags);
			} catch (ValidationException e1) {
				System.out.println("Error#2 in PostDAO. Error message: " + e1.getMessage());
				throw e1;
			}
		 	try {	
		 		sql ="INSERT INTO albums_posts (album_id, post_id) VALUES (?, ?);";
			 	st = conn.prepareStatement(sql);	
			 	st.setLong(1, album.getAlbumId());;
			 	st.setString(2, postId);
				st.execute();
			} catch (SQLException e) {
				System.out.println("Error#3 in PostDAO. Error message: " + e.getMessage());
				throw e;
			}
		} catch (SQLException e3) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				System.out.println("Error#4 in PostDAO. Error message: " + e3.getMessage());
				throw e;
			}
			System.out.println("Error#5 in PostDAO. Error message: " + e3.getMessage());
			throw e3;
		}finally{
	 		try {
	 			conn.setAutoCommit(true);
	 		} catch (SQLException e) {
	 			System.out.println("Error#6 in PostDAO. Error message: " + e.getMessage());
				throw e;
	 		}
	 	}				
 		CachedObjects.getInstance().addPost(post, album);
	}
		
	// change name
	public Post editPostName(Post post, User user, String str) throws ValidationException, SQLException{
		String sql = "UPDATE posts SET name = ? WHERE post_id = ? ";
 		PreparedStatement st;
		Connection conn = null;
 		try {
 			conn = (Connection) dataSource.getConnection();
			conn.setAutoCommit(false);
			conn = (Connection) dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, str);
			st.setString(2, post.getPostId());
			st.execute();
			post.changeName(str);
			return post;
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
 			try {
 				conn.setAutoCommit(true);
 			} catch (SQLException e) {
 				System.out.println("Error#3 in AlbumDAO. Error message: " + e.getMessage());
 				throw e;
 			}
 		}
 	}
	
	// change description
 	public Post editPostInfo(Post post, User user, String str) throws ValidationException, SQLException{
		String sql = "UPDATE posts SET description = ? WHERE post_id = ? ";
 		PreparedStatement st;
		Connection conn = null;
 		try {
 			conn = (Connection) dataSource.getConnection();
			conn.setAutoCommit(false);
			conn = (Connection) dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, str);
			st.setString(2, post.getPostId());
			st.execute();
			post.changeDescription(str);
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
 			try {
 				conn.setAutoCommit(true);
 			} catch (SQLException e) {
 				System.out.println("Error#3 in AlbumDAO. Error message: " + e.getMessage());
 				throw e;
 			}
 		}
		return post;
 	}
	
	//method that edits and puts tags, can be used when we are creating new posts 
 	public Post editTags(Post post, User user, TreeSet<String> tags) throws ValidationException, SQLException{
 		PreparedStatement st = null;
		ResultSet result = null;
	 	ArrayList<Long> lonelyTags = new ArrayList<>();
	 	String sql = "";
		Connection conn = null;
		try {
			conn = (Connection) dataSource.getConnection();
 			conn.setAutoCommit(false);
 			//if this post is new then don't clean old photo tags 'cuz they don't exist :)
 			if(!post.getTags().isEmpty()){
 	 			//delete all tag and post connections 
	 			sql = "DELETE FROM tags_posts WHERE post_id = ? ";
	 			st = conn.prepareStatement(sql);
	 			st.setString(1, post.getPostId());
	 			st.execute();	 			
	 			//select all tags with no post connections
	 			sql = "SELECT t.tag_id FROM tags t LEFT JOIN tags_posts tp ON t.tag_id = tp.tag_id WHERE tp.tag_id IS NULL";
	 			st = conn.prepareStatement(sql);
	 			result = st.executeQuery();
	 			while(result.next()){
	 				lonelyTags.add(result.getLong("tag_id"));		
	 			}
	 			//delete them
	 			for(Long tagId: lonelyTags){
		 			sql = "DELETE FROM tags WHERE tag_id = ? ";
		 			st = conn.prepareStatement(sql);
		 			st.setLong(1, tagId);
		 			st.execute();
	 			}
 			}
 			//add new tags in tag table if needed! tags are unique they won't be added in if they are already in it
 			for(String tag_name: tags){
 				sql = "INSERT INTO tags (name) value (?)";
	 			st = conn.prepareStatement(sql);
	 			st.setString(1, tag_name);
	 			st.execute();
 			}
 			//select all new tag id-s
 			lonelyTags = new ArrayList<>();
 			for(String tag_name : tags){
 				sql = "SELECT tag_id FROM tags where name = ?";
 	 			st = conn.prepareStatement(sql);
	 			st.setString(1, tag_name);
 	 			result = st.executeQuery();
 	 			while(result.next()){
 	 				lonelyTags.add(result.getLong("tag_id"));		
 	 			}		
 			}
 	 		//insert them
 	 		for(Long tagId: lonelyTags){
				sql = "INSERT INTO tags_posts (tag_id, post_id) VALUES (?, ?) ";
 				st = conn.prepareStatement(sql);
 				st.setLong(1, tagId);
 				st.setString(2, post.getPostId());
 				st.execute();
 	 		}
 	 	}catch (SQLException e1) {
 			try {
				conn.rollback();
	 			System.out.println("Error#1 in PostDAO. Error message: " + e1.getMessage());
	 			throw e1;
			} catch (SQLException e) {
	 			System.out.println("Error#2 in PostDAO. Error message: " + e.getMessage());
	 			throw e;
			}
 	 	}finally{
 			try {
 				conn.setAutoCommit(true);
 			} catch (SQLException e) {
 				System.out.println("Error#3 in PostDAO. Error message: " + e.getMessage());
	 			throw e;
 			}
 		}
 		post.addTags(tags);
 		return post;
 	}
 	
 	
	//add Like
 	public void addLike(Post post, User user) throws ValidationException, SQLException{
		String sql = "INSERT INTO post_likes (user_id, post_id) VALUES (?, ?)";
 		PreparedStatement st;
		Connection conn = null;
		try {
			conn = (Connection) dataSource.getConnection();
 			conn.setAutoCommit(false);
 			conn = (Connection) dataSource.getConnection();
 			st = conn.prepareStatement(sql);
 			st.setLong(1, user.getUserId());
 			st.setString(1, post.getPostId());
 			st.execute();
 			CachedObjects.getInstance().getOnePost(post.getPostId()).addLike(user);
 	 	}catch (SQLException e1) {
 			try {
				conn.rollback();
	 			System.out.println("Error#1 in PostDAO. Error message: " + e1.getMessage());
	 			throw e1;
			} catch (SQLException e) {
	 			System.out.println("Error#2 in PostDAO. Error message: " + e.getMessage());
	 			throw e;
			}
 	 	}finally{
 			try {
 				conn.setAutoCommit(true);
 			} catch (SQLException e) {
 				System.out.println("Error#3 in PostDAO. Error message: " + e.getMessage());
	 			throw e;
 			}
 		}
 	}
 	
	//remove like	
 	public void removeLike(Post post, User user) throws ValidationException, SQLException{
		String sql = "DELETE FROM post_likes WHERE user_id = " + user.getUserId() + " and post_id " + post.getPostId();
 		PreparedStatement st;
		Connection conn = null;
		try {
			conn = (Connection) dataSource.getConnection();
 			conn.setAutoCommit(false);
 			conn = (Connection) dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.execute();
			CachedObjects.getInstance().getOnePost(post.getPostId()).removeLike(user);
 	 	}catch (SQLException e1) {
 			try {
				conn.rollback();
	 			System.out.println("Error#1 in PostDAO. Error message: " + e1.getMessage());
	 			throw e1;
			} catch (SQLException e) {
	 			System.out.println("Error#2 in PostDAO. Error message: " + e.getMessage());
	 			throw e;
			}
 	 	}finally{
 			try {
 				conn.setAutoCommit(true);
 			} catch (SQLException e) {
 				System.out.println("Error#3 in PostDAO. Error message: " + e.getMessage());
	 			throw e;
 			}
 		}
 	}
 	
	//delete post
 	public void deletePost(Post post, User user, Album album) throws ValidationException, SQLException{
		Connection conn = null;
			try {
			conn = (Connection) dataSource.getConnection();
 			//first delete form album and post connecting table
			conn.setAutoCommit(false);
			String sql = "DELETE FROM albums_posts WHERE post_id =  ?; ";
	 		PreparedStatement st;
			st = conn.prepareStatement(sql);
			st.setString(1, post.getPostId());
		 	st.execute();
 			
			//second delete from post and like connecting table
			conn.setAutoCommit(false);
			sql = "DELETE FROM post_likes WHERE post_id = ?; ";
			st = conn.prepareStatement(sql);
			st.setString(1, post.getPostId());
		 	st.execute();	
			
			//third delete from tag and posts connecting table
			conn.setAutoCommit(false);
			sql = "DELETE FROM tags_posts WHERE post_id =  ?; ";
			st = conn.prepareStatement(sql);
			st.setString(1, post.getPostId());
		 	st.execute();	
			
			//fourth delete all tags with no posts 			
			//4.1 select all tags with no post connections
 			sql = "SELECT t.tag_id FROM tags t LEFT JOIN tags_posts tp ON t.tag_id = tp.tag_id WHERE tp.post_id IS NULL";
 			st = conn.prepareStatement(sql);
 			ResultSet result;
 		 	ArrayList<Long> lonelyTags = new ArrayList<>();
 			result = st.executeQuery();
 			while(result.next()){
 				lonelyTags.add(result.getLong("tag_id"));		
 			}
 			//4.2 delete them
 			for(Long tagId: lonelyTags){
	 			sql = "DELETE FROM tags WHERE tag_id = ? ";
	 			st = conn.prepareStatement(sql);
	 			st.setLong(1, tagId);
	 			st.execute();
 			}
					
			//AND fifth delete post 
			conn.setAutoCommit(false);
			sql = "DELETE FROM posts WHERE post_id = ?; ";
			st = conn.prepareStatement(sql);
			st.setString(1, post.getPostId());
		 	st.execute();
	
 		}catch (SQLException e1) {
 			try {
				conn.rollback();
	 			System.out.println("Error#5 in PostDAO. Error message: " + e1.getMessage());
				throw e1;
			} catch (SQLException e) {
	 			System.out.println("Error#6 in PostDAO. Error message: " + e.getMessage());
				throw e;
			}
 	 	}finally{
 			try {
 				conn.setAutoCommit(true);
 			} catch (SQLException e) {
 				System.out.println("Error#7 in PostDAO. Error message: " + e.getMessage());
				throw e;
 			}
 		}	
		CachedObjects.getInstance().removePost(post, album);
 	}
 	
 	//getAllPost
	public void getAllPosts() throws ValidationException, SQLException {
		if(CachedObjects.getInstance().getAllPosts().isEmpty()){
			TreeSet<String> tags = new TreeSet<>();
			TreeMap<Long, TreeSet<String>> postIds = new TreeMap<>();
	  		PreparedStatement st = null;
	  		String sql = "";
	 		ResultSet result = null;
	    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
			CommentDAO commentDAO = (CommentDAO) context.getBean("CommentDAO");
			Connection conn = null;
 			conn = (Connection) dataSource.getConnection();
	 		conn.setAutoCommit(false);
	 		//select all post_id
	 		sql = "SELECT post_id, album_id FROM albums_posts";
	 		st = conn.prepareStatement(sql);
	 		st.execute();
	 		result = st.getResultSet();
	 		while(result.next()){
	 			if(!postIds.containsKey(result.getLong("album_id"))){
	 				postIds.put(result.getLong("album_id"), new TreeSet<String>());
	 			}
	 			postIds.get(result.getLong("album_id")).add(result.getString("post_id"));
	 		}
	 		for(Entry <Long, TreeSet<String>> entry: postIds.entrySet()){
	 			Long albumId = entry.getKey();
	 			for(String str: entry.getValue()){
		 		//get tags
			 		sql = "SELECT t.name FROM tags t "
			 			+ "JOIN tags_posts tp ON t.tag_id = tp.tag_id "
			 			+ "WHERE tp.post_id = ? ";
			 		st = conn.prepareStatement(sql);
			 		st.setString(1, str);
			 		st.execute();
			 		result = st.getResultSet();
			 		while(result.next()){
			 			tags.add(result.getString("name"));
			 		}
			 		//create post
			 		sql = "SELECT name, user_id, description, album_id, date_created, picture_path, delete_hash "
			 				+ "FROM posts WHERE post_id = ? ";
			 		//initialization
			 		st = conn.prepareStatement(sql);
			 		st.setString(1, str);
			 		st.execute();
			 		//get result
			 		result = st.getResultSet();
			 		while(result.next()){
			 			User user = CachedObjects.getInstance().getOneUser(result.getLong("user_id"));
			 			Post post = new Post(user, result.getString("name"), result.getString("description"), result.getDate("date_created").toLocalDate(), result.getString("picture_path"), tags, str, result.getString("delete_hash"));
			 			commentDAO.getAllComments(post);
			 			//add all post in right albums
			 			CachedObjects.getInstance().addPost(post, albumId);
			 		}
	 			}
	 		} 		
		}
	}	
}
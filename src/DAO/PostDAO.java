package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import model.Album;
import model.CachedObjects;
import model.Post;
import model.User;

public class PostDAO {
	
	private static PostDAO instance;

	private PostDAO() {

	}
	public static synchronized PostDAO getInstance() {
		if (instance == null) {
			instance = new PostDAO();
		}
		return instance;
	}
	
	//getPost
	public static Post createPost(long postId) throws ValidationException {
		TreeSet<String> tags = new TreeSet<>();
  		Statement st = null;
  		Post post = null;
  		String sql = "";
 		ResultSet result = null;
 		try {
 			DBManager.getInstance().getConnection().setAutoCommit(false);
 			//get tags
 			sql = "SELECT t.name FROM tags t "
 				+ "JOIN tags_posts tp ON t.tag_id = tp.tag_id "
 				+ "WHERE tp.post_id = " + postId;
 			st = DBManager.getInstance().getConnection().createStatement();
 			result = st.executeQuery(sql);
 			while(result.next()){
 				tags.add(result.getString("name"));
 			}
			//create post
	 		sql = "SELECT name, user_id, description, album_id, date_created, picture_path "
	 			+ "FROM posts WHERE post_id = " + postId;
	 		//initialization
	 		try {
	 			st = DBManager.getInstance().getConnection().createStatement();
	 		} catch (SQLException e2) {
	 			System.out.println("Error#1 in create post. Error message: " + e2.getMessage());
	 		}
	 		//get result
	 		try {
	 			result = st.executeQuery(sql);
	 		} catch (SQLException e) {
	 			System.out.println("Error#1 in PostDAO. Eroor message: " + e.getMessage());
	 		}
	 		try {
	 			while(result.next()){
	 				try {
		 				User user = CachedObjects.getInstance().getOneUser(result.getLong("user_id"));
	 					post = new Post(user, result.getString("name"), result.getString("description"), result.getDate("date_created").toLocalDate(), result.getString("picture_path"), tags, postId);
	 				} catch (SQLException e) {
	 					System.out.println("Error#2 in PostDAO. Eroor message: " + e.getMessage());
	 				}
	 			}
	 		} catch (SQLException e1) {
	 			System.out.println("Error#3 in PostDAO. Eroor message: " + e1.getMessage());
	 		}		
 		}catch (SQLException e1) {
 			try {
				DBManager.getInstance().getConnection().rollback();
	 			System.out.println("Error#4 in PostDAO. Eroor message: " + e1.getMessage());
			} catch (SQLException e) {
	 			System.out.println("Error#5 in PostDAO. Eroor message: " + e.getMessage());
			}
 	 	}finally{
 			try {
 				DBManager.getInstance().getConnection().setAutoCommit(true);
 			} catch (SQLException e) {
 				System.out.println("Error#6 in PostDAO. Eroor message: " + e.getMessage());
 			}
 		}
 	return post;
	}
	
	// makePost
	public void makePost(User user, String name, String description, LocalDate dateCreated, String picturePath, TreeSet<String> tags, Album album) throws SQLException, ValidationException{
 		try {
 			DBManager.getInstance().getConnection().setAutoCommit(false);
			String sql ="INSERT INTO posts (user_id, name, descriptiopn, album_id, date_created, picture_path)"
						+ "VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);	
	 		st.setLong(1, user.getUserId());
	 		st.setString(2, name);
	 		st.setString(3, description);
	 		st.setLong(4, album.getAlbumId());
	 		st.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
	 		st.setString(6, picturePath); 		
	 		st.execute();
	 		ResultSet res = st.getGeneratedKeys();
	 		Post post = new Post(user, name, description, dateCreated, picturePath, tags, res.getLong("post_id"));
	 		editTags(post, user, tags);
 		}catch (SQLException e1) {
 			try {
				DBManager.getInstance().getConnection().rollback();
	 			System.out.println("Error#1 in PostDAO. Eroor message: " + e1.getMessage());
			} catch (SQLException e) {
	 			System.out.println("Error#2 in PostDAO. Eroor message: " + e.getMessage());
			}
 	 	}finally{
 			try {
 				DBManager.getInstance().getConnection().setAutoCommit(true);
 			} catch (SQLException e) {
 				System.out.println("Error#3 in PostDAO. Eroor message: " + e.getMessage());
 			}
 		}
	}
		
	// change name
	public void editPhotoName(Post post, User user, String str) throws ValidationException{
		String sql = "UPDATE posts SET name = ? WHERE post_id = " + post.getPostId();
 		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			st.setString(1, str);
	 		st.execute();
		} catch (SQLException e) {
			System.out.println("Error#1 in PostDAO. Eroor message: " + e.getMessage());
		}
		post.changeName(str);
 	}
	
	// change description
 	public void editPhotoInfo(Post post, User user, String str) throws ValidationException{
		String sql = "UPDATE posts SET description = ? WHERE post_id = " + post.getPostId();
 		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			st.setString(1, str);
	 		st.execute();
		} catch (SQLException e) {
			System.out.println("Error#1 in PostDAO. Eroor message: " + e.getMessage());
		}
		post.changeDescription(str);
 	}
	
	//method that edits and puts tags, can be used in creating new post
 	public void editTags(Post post, User user, TreeSet<String> tags){
 		PreparedStatement st = null;
		ResultSet result = null;
	 	ArrayList<Long> lonelyTags = new ArrayList<>();
	 	String sql = "";
 		try {
 			DBManager.getInstance().getConnection().setAutoCommit(false);
 			//if this post is new then don't clean old photo tags 'cuz they don't exist :)
 			if(!post.getTags().isEmpty()){
 	 			//delete all tag and post connections 
	 			sql = "DELETE FROM tags_posts WHERE post_id = ? ";
	 			st = DBManager.getInstance().getConnection().prepareStatement(sql);
	 			st.setLong(1, post.getPostId());
	 			st.execute();
	 			//select all tags with no post connections
	 			sql = "SELECT t.tag_id FROM tags t LEFT JOIN tags_posts tp ON t.tag_id = tp.tag_id WHERE tp.tag_id IS NULL";
	 			st = DBManager.getInstance().getConnection().prepareStatement(sql);
	 			result = st.executeQuery();
	 			while(result.next()){
	 				lonelyTags.add(result.getLong("tag_id"));		
	 			}
	 			//delete them
	 			for(Long tagId: lonelyTags){
		 			sql = "DELETE FROM tags WHERE tag_id = ? ";
		 			st = DBManager.getInstance().getConnection().prepareStatement(sql);
		 			st.setLong(1, tagId);
		 			st.execute();
	 			}
 			}
 			//add new tags in tag table if needed! tags are unique they won't be added in if they are already in it
 			for(String tag_name: tags){
 				sql = "INSERT INTO tags (name) value (?)";
	 			st = DBManager.getInstance().getConnection().prepareStatement(sql);
	 			st.setString(1, tag_name);
	 			st.execute();
 			}
 			//select all new tag id-s
 			lonelyTags = new ArrayList<>();
 			for(String tag_name : tags){
 				sql = "SELECT tag_id t FROM t.tags where name = ?";
 	 			st = DBManager.getInstance().getConnection().prepareStatement(sql);
	 			st.setString(1, tag_name);
 	 			result = st.executeQuery();
 	 			while(result.next()){
 	 				lonelyTags.add(result.getLong("tag_id"));		
 	 			}		
 			}
 	 		//insert them
 	 		for(Long tagId: lonelyTags){
				sql = "INSER INTO tags_posts (tag_id, post_id) value (?, ?) ";
 				st = DBManager.getInstance().getConnection().prepareStatement(sql);
 				st.setLong(1, tagId);
 				st.setLong(2, post.getPostId());
 				st.execute();
 	 		}
 	 	}catch (SQLException e1) {
 			try {
				DBManager.getInstance().getConnection().rollback();
	 			System.out.println("Error#1 in PostDAO. Eroor message: " + e1.getMessage());
			} catch (SQLException e) {
	 			System.out.println("Error#2 in PostDAO. Eroor message: " + e.getMessage());
			}
 	 	}finally{
 			try {
 				DBManager.getInstance().getConnection().setAutoCommit(true);
 			} catch (SQLException e) {
 				System.out.println("Error#3 in PostDAO. Eroor message: " + e.getMessage());
 			}
 		}
 	}		
 	
	//TODO addLike
	//TODO remove like
	//TODO delete post
 	//TODO connect to imgur for photo path
	
}

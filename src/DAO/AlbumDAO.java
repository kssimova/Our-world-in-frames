package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import model.Album;
import model.Post;
import model.User;

public class AlbumDAO {

	private static AlbumDAO instance;

	private AlbumDAO() {

	}
	public static synchronized AlbumDAO getInstance() {
		if (instance == null) {
			instance = new AlbumDAO();
		}
		return instance;
	}
	
	// create new album
	public Album createAlbum(User user, String name, String description) throws ValidationException, SQLException{
 		Album album = null;
		String sql = "INSERT INTO albums (name, description, date_created, user_id) " +
 					"VALUES (?, ?, ?, ?)";
 		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
 		st.setString(1, name);
 		st.setString(2, description);
 		st.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
 		st.setLong(4, user.getUserId());
 		st.execute();
 		ResultSet res = st.getGeneratedKeys();
 		res.next();
 		long albumId = res.getLong(1);
 		album = user.createAlbum(name, description, albumId);
 		return album;
 	}
	
	//delete album	
	public void deleteAlbum(User user, Album album) throws ValidationException, SQLException{
		try {
			DBManager.getInstance().getConnection().setAutoCommit(false);		
			//delete all photos in this album
			for(Post p : album.getPhotos()){
				PostDAO.getInstance().deletePost(p, user, album);
			}			
			//delete album
			String sql = "DELETE FROM albums WHERE album_id = ? ";
	 		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
	 		st.setLong(1, album.getAlbumId());
	 		st.execute();
		} catch (SQLException e) {
				try {
				DBManager.getInstance().getConnection().rollback();
 				System.out.println("Error#1 in AlbumDAO. Error message: " + e.getMessage());
			} catch (SQLException e1) {
				System.out.println("Error#2 in AlbumDAO. Error message: " + e1.getMessage());
			}
		}finally{
				try {
				DBManager.getInstance().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("Error#3 in AlbumDAO. Error message: " + e.getMessage());
			}
		}
 		user.deleteAlbum(album);
 	}
	
	// change album name
	public Album editAlbumName(Album album, User user, String str) throws ValidationException{
		String sql = "UPDATE albums SET name = ? WHERE album_id = ? ";
 		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			st.setString(1, str);
			st.setLong(2, album.getAlbumId());
	 		st.execute();
		} catch (SQLException e) {
			System.out.println("Error#1 in AlbumDAO. Error message: " + e.getMessage());
		}
		album.changeName(str);
		return album;
 	}
		
	//change album description
 	public Album editAlbumInfo(Album album, User user, String str) throws ValidationException{
		String sql = "UPDATE albums SET description = ? WHERE album_id = ? ";
 		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			st.setString(1, str);
			st.setLong(2, album.getAlbumId());
	 		st.execute();
		} catch (SQLException e) {
			System.out.println("Error#1 in AlbumDAO. Error message: " + e.getMessage());
		}
		album.changeDescription(str);;
		return album;
 	}
 		
	//create album from database
 	public Album getAlbum(User user, long albumId) throws ValidationException {
  		TreeSet<Post> posts = new TreeSet<>();
  		Album album = null;
 		PreparedStatement st = null;
  		String sql = "";
 		ResultSet result = null;
 		try {
 			DBManager.getInstance().getConnection().setAutoCommit(false);
 			//get posts
 			sql = "SELECT p.user_id, p.post_id, p.name, p.description, p.delete_hash, p.date_created, p.picture_path "
 				+ "FROM posts p JOIN albums_posts ap ON p.post_id = ap.post_id WHERE ap.album_id = ? ;";
 		 	st = DBManager.getInstance().getConnection().prepareStatement(sql);
 		 	st.setLong(1, albumId);
 		 	st.execute();
 		 	result = st.getResultSet();
 			while(result.next()){
 				Post p = PostDAO.getInstance().getPost(result.getString("post_id"), result.getString("delete_hash"));
 				posts.add(p);
 			}
 			
 			sql = "SELECT a.name, a.description, a.date_created, a.user_id FROM albums a wHERE a.album_id = ? ;";
	 		//create the album
	 		try {
	 		 	st = DBManager.getInstance().getConnection().prepareStatement(sql);
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
	 		} catch (SQLException e1) {
	 			System.out.println("Error#1 in AlbumDAO. Error message: " + e1.getMessage());
	 		}		
 		}catch (SQLException e1) {
 			try {
				DBManager.getInstance().getConnection().rollback();
	 			System.out.println("Error#2 in AlbumDAO. Error message: " + e1.getMessage());
			} catch (SQLException e) {
	 			System.out.println("Error#3 in AlbumDAO. Error message: " + e.getMessage());
			}
 	 	}finally{
 			try {
 				DBManager.getInstance().getConnection().setAutoCommit(true);
 			} catch (SQLException e) {
 				System.out.println("Error#4 in AlbumDAO. Error message: " + e.getMessage());
 			}
 		}
 	return album;
	}
}

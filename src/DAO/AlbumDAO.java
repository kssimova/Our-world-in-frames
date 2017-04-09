package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	public Album makeAlbum(User user, String name, String description) throws ValidationException, SQLException{
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
	 		user.deleteAlbum(album);
		} catch (SQLException e) {
				try {
				DBManager.getInstance().getConnection().rollback();
 				System.out.println("Error#2 in delete commetnt. Error message: " + e.getMessage());
			} catch (SQLException e1) {
				System.out.println("Error#1 in delete commetnt. Error message: " + e1.getMessage());
			}
		}finally{
				try {
				DBManager.getInstance().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("Error#3 in delete commetnt. Error message: " + e.getMessage());
			}
		}		
 	}
	
	// change album name
	public Album editAlbumName(Album album, User user, String str) throws ValidationException{
		String sql = "UPDATE albums SET name = ? WHERE post_id = ? ";
 		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			st.setString(1, str);
			st.setLong(2, album.getAlbumId());
	 		st.execute();
		} catch (SQLException e) {
			System.out.println("Error#1 in PostDAO. Error message: " + e.getMessage());
		}
		album.changeName(str);
		return album;
 	}
		
	//change album description
 	public Album editAlbumInfo(Album album, User user, String str) throws ValidationException{
		String sql = "UPDATE posts SET description = ? WHERE post_id = ? ";
 		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			st.setString(1, str);
			st.setLong(2, album.getAlbumId());
	 		st.execute();
		} catch (SQLException e) {
			System.out.println("Error#1 in PostDAO. Error message: " + e.getMessage());
		}
		album.changeDescription(str);;
		return album;
 	}
 		
	//TODO create album from database
	
}

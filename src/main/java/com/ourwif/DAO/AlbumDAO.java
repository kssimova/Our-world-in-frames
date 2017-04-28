package com.ourwif.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.sql.DataSource;
import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.jdbc.Connection;
import com.ourwif.model.Album;
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
			conn.rollback();
		}finally{
			conn.setAutoCommit(true);
 			conn.close();
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
	 		st = conn.prepareStatement(sql);
	 		st.setString(1, str);
	 		st.setLong(2, album.getAlbumId());
	 		st.execute();
	 		album.changeName(str);
		}finally{
 			conn.close();
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
 	
 	
	//get all albums From DB
 	public TreeMap <Long, Album> getUserAlbums(User user) throws ValidationException, SQLException {
 		TreeMap <Long, Album> albums = new TreeMap<>();
 		TreeSet <Long> allAlbumIDs = new TreeSet<>();
  		Album album = null;
  		String sql = "";
 		PreparedStatement st = null;
 		ResultSet result = null;
    	context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
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
					Post p = postDAO.getPost(result.getString("post_id"), result.getString("delete_hash"), albumId);
					albums.get(albumId).addPosts(p);
				}
			}
		}finally{
	 		conn.close();
		}
		return albums;	
	} 	
}
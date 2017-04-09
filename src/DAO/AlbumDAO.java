package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import model.CachedObjects;
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
	
	//TODO create new album
	//TODO create album from database
	//TODO delete album
	//TODO change album name
	//TODO change album description
	
	
}

package DAO;

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
	
	//create new  album
	//create album from database
	//delete album
	//change album name
	//change album description
	
	
}

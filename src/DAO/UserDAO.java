package DAO;

public class UserDAO {

	private static UserDAO instance;

	private UserDAO() {

	}
	public static synchronized UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
	//create new user with default album and insert into cachedObjects
	//make user from database with all his albums, photos and other connections
	//change email
	//change mobile number
	//change password
	//change first name
	//change last name
	//change county
	//change city
	//change description
	//change birthday
	//change profile photo
	
	
	
}

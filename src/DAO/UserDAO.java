package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CachedObjects;
import model.User;

public class UserDAO {

	private static final String INSERT_USER = "INSERT INTO ourwif.users (first_name, last_name, username, email, password, birthdate, description, gender, profilephoto_path, city_id, country_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String CHANGE_FIRSTNAME = "UPDATE ourwif.users ";
	private static UserDAO instance;
	private Connection connection = DBManager.getInstance().getConnection();

	private UserDAO() {

	}
	
	public static synchronized UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
	//create new user with default album and insert into cachedObjects
	public synchronized void addUser(User user){
		
		// get city_id and country_id 
		String sql = "SELECT (city_id, country_id) FROM ourwif.cities WHERE name = " + user.getCity();
		PreparedStatement preparedStatement = null;
		long city_id = 0, country_id = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()){
				city_id = result.getLong("city_id");
				country_id = result.getLong("country_id");
				break;
			}
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method addUser() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method addUser() - " + e2.getMessage());
				}
			}
		}
		
		// add new user to database
		PreparedStatement prepStatement = null;
		try {
			prepStatement = connection.prepareStatement(INSERT_USER, java.sql.Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, user.getFirstName());
			prepStatement.setString(2, user.getLastName());
			prepStatement.setString(3, user.getUsername());
			prepStatement.setString(4, user.getEmail());
			prepStatement.setString(5, user.getPassword());
			prepStatement.setDate(6, Date.valueOf(user.getBirthdate()));
			prepStatement.setString(7, user.getDescriprion());
			prepStatement.setString(8, user.getGender().toString());
			prepStatement.setString(9, user.getProfilePhotoPath());
			prepStatement.setLong(10, city_id);
			prepStatement.setLong(11, country_id);
			prepStatement.executeUpdate(INSERT_USER);
			ResultSet result = prepStatement.getGeneratedKeys();
			if(result.next()){
				System.out.println("in UserDAO - addUser(), new user's id is " + result.getLong("user_id"));
			}
		} catch (SQLException e3) {
			System.out.println("Error in 2nd catch block in UserDAO method addUser() - " + e3.getMessage());
		}
		finally{
			if(prepStatement != null){
				try {
					prepStatement.close();
				} catch (SQLException e4) {
					System.out.println("Error when closing statement in 2nd catch block in UserDAO method addUser() - " + e4.getMessage());
				}
			}
		}
		
		CachedObjects.getInstance().addUser(user);
		
	}
	
	//select user from database with all his albums, photos and other connections
	
	//change first name
	public void changeFirstName(String first_name){
		
	}
	
	//change last name
	//change email
	//change mobile number
	//change password
	//change country
	//change city
	//change description
	//change birthday
	//change profile photo
	
}

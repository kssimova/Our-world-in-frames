package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.xml.bind.ValidationException;

import com.sun.corba.se.impl.orb.PrefixParserAction;

import model.CachedObjects;
import model.User;

public class UserDAO {

	private static final String INSERT_USER = "INSERT INTO ourwif.users (first_name, last_name, username, email, password, birthdate, description, gender, profilephoto_path, city_id, country_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String CHANGE_FIRSTNAME = "UPDATE ourwif.users SET first_name = ? WHERE user_id = ?";
	private static final String CHANGE_LASTNAME = "UPDATE ourwif.users SET last_name = ? WHERE user_id = ?";
	private static final String CHANGE_EMAIL = "UPDATE ourwif.users SET email = ? WHERE user_id = ?";
	private static final String CHANGE_MOBILENUMBER = "UPDATE ourwif.users SET mobile_number = ? WHERE user_id = ?";
	private static final String CHANGE_PASSWORD = "UPDATE ourwif.users SET password = ? WHERE user_id = ?";
	private static final String CHANGE_CITYANDCOUNTRY = "UPDATE ourwif.users SET city_id = ?, country_id = ? WHERE user_id = ?";
	private static final String CHANGE_DESCRIPTION = "UPDATE ourwif.users SET description = ? WHERE user_id = ?";
	private static final String CHANGE_BIRTHDATE = "UPDATE ourwif.users SET birthdate = ? WHERE user_id = ?";
	private static final String CHANGE_PROFILEPHOTO = "UPDATE ourwif.users SET profilephoto_path = ? WHERE user_id = ?";
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
	public void changeFirstName(User user, String first_name) throws ValidationException{
		user.changeFirstName(first_name);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(CHANGE_FIRSTNAME);
			preparedStatement.setString(1, first_name);
			preparedStatement.setLong(2, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method changeFirstName() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method changeFirstName() - " + e2.getMessage());
				}
			}
		}
	}
	
	//change last name
	public void changeLastName(User user, String last_name) throws ValidationException{
		user.changeLastName(last_name);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(CHANGE_LASTNAME);
			preparedStatement.setString(1, last_name);
			preparedStatement.setLong(2, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method changeLastName() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method changeLastName() - " + e2.getMessage());
				}
			}
		}
	}
	
	//change email
	public void changeEmail(User user, String email) throws ValidationException{
		user.changeEmail(email);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(CHANGE_EMAIL);
			preparedStatement.setString(1, email);
			preparedStatement.setLong(2, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method changeEmail() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method changeEmail() - " + e2.getMessage());
				}
			}
		}
	}
	
	//change mobile number
	public void changeMobileNumber(User user, String mobile_number) throws ValidationException{
		user.changeMobileNumber(mobile_number);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(CHANGE_MOBILENUMBER);
			preparedStatement.setString(1, mobile_number);
			preparedStatement.setLong(2, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method changeMobileNumber() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method changeMobileNumber() - " + e2.getMessage());
				}
			}
		}
	}
	
	//change password
	public void changePassword(User user, String password) throws ValidationException{
		user.changePassword(password);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(CHANGE_PASSWORD);
			preparedStatement.setString(1, password);
			preparedStatement.setLong(2, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method changePassword() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method changePassword() - " + e2.getMessage());
				}
			}
		}
	}
	
	//change country and city - not sure if correct
	public void changeCityCountry(User user, String country_name, String city_name) throws ValidationException{
		user.changeCity(city_name);
		user.changeCountry(country_name);
		String sql1 = "SELECT country_id FROM ourwif.countries WHERE name = ?";
		PreparedStatement preparedStatement = null;
		long country_id = 0;
		try {
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setString(1, country_name);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				country_id = resultSet.getLong("country_id");
			}
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method changeCityCountry() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method changeCityCountry() - " + e2.getMessage());
				}
			}
		}
		String sql2 = "SELECT city_id FROM ourwif.cities WHERE country_id = " + country_id + " AND name = ?";
		PreparedStatement prepStatement = null;
		long city_id = 0;
		try {
			prepStatement = connection.prepareStatement(sql2);
			prepStatement.setString(1, city_name);
			ResultSet resultSet = prepStatement.executeQuery();
			if(resultSet.next()){
				city_id = resultSet.getLong("city_id");
			}
		} catch (SQLException e3) {
			System.out.println("Error in 2nd catch block in UserDAO method changeCityCountry() - " + e3.getMessage());
		}
		finally{
			if(prepStatement != null){
				try {
					prepStatement.close();
				} catch (SQLException e4) {
					System.out.println("Error when closing statement in 2nd catch block in UserDAO method changeCityCountry() - " + e4.getMessage());
				}
			}
		}
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(CHANGE_CITYANDCOUNTRY);
			statement.setLong(1, city_id);
			statement.setLong(2, country_id);
			statement.setLong(3, user.getUserId());
			statement.executeUpdate();
		} catch (SQLException e5) {
			System.out.println("Error in 3rd catch block in UserDAO method changeCityCountry() - " + e5.getMessage());
		}
		finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e6) {
					System.out.println("Error when closing statement in 3rd catch block in UserDAO method changeCityCountry() - " + e6.getMessage());
				}
			}
		}
	}
	
	//change description
	public void changeDescription(User user, String description) throws ValidationException{
		user.changeDescription(description);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(CHANGE_DESCRIPTION);
			preparedStatement.setString(1, description);
			preparedStatement.setLong(2, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method changeDescription() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method changeDescription() - " + e2.getMessage());
				}
			}
		}
	}
	
	//change birthdate
	public void changeBirthdate(User user, LocalDate date) throws ValidationException{
		user.changeBirthDate(date);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(CHANGE_BIRTHDATE);
			preparedStatement.setDate(1, Date.valueOf(date));
			preparedStatement.setLong(2, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method changeBirthdate() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method changeBirthdate() - " + e2.getMessage());
				}
			}
		}
	}
	
	//change profile photo
	public void changeProfilePhoto(User user, String profilephoto_path) throws ValidationException{
		user.changeProfilePhoto(profilephoto_path);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(CHANGE_PROFILEPHOTO);
			preparedStatement.setString(1, profilephoto_path);
			preparedStatement.setLong(2, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method changeProfilePhoto() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method changeProfilePhoto() - " + e2.getMessage());
				}
			}
		}
	}
	
}

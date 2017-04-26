package com.ourwif.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

import javax.sql.DataSource;
import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ourwif.model.CachedObjects;
import com.ourwif.model.User;

public class UserDAO {

	// works
	private static final String SELECT_ALL_USERS = "SELECT user_id, first_name, last_name, username, email, password, mobile_number, birthdate, description, gender, profilephoto_path, CITY.name AS city_name, COUNTRY.name AS country_name FROM ourwif.users JOIN ourwif.cities CITY ON (users.city_id = CITY.city_id) JOIN ourwif.countries COUNTRY ON (CITY.country_id = COUNTRY.country_id)";
	// works
	private static final String INSERT_USER = "INSERT INTO ourwif.users (first_name, last_name, username, email, password, mobile_number, birthdate, description, gender, profilephoto_path, city_id, country_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	// works
	private static final String CHANGE_FIRST_NAME = "UPDATE ourwif.users SET first_name = ? WHERE user_id = ?";
	// works
	private static final String CHANGE_LAST_NAME = "UPDATE ourwif.users SET last_name = ? WHERE user_id = ?";
	// works 
	private static final String CHANGE_EMAIL = "UPDATE ourwif.users SET email = ? WHERE user_id = ?";
	// works
	private static final String CHANGE_MOBILENUMBER = "UPDATE ourwif.users SET mobile_number = ? WHERE user_id = ?";
	// works
	private static final String CHANGE_PASSWORD = "UPDATE ourwif.users SET password = ? WHERE user_id = ?";
	// works
	private static final String CHANGE_CITY_AND_COUNTRY = "UPDATE ourwif.users SET city_id = ?, country_id = ? WHERE user_id = ?";
	// works
	private static final String CHANGE_DESCRIPTION = "UPDATE ourwif.users SET description = ? WHERE user_id = ?";
	// works
	private static final String CHANGE_BIRTHDATE = "UPDATE ourwif.users SET birthdate = ? WHERE user_id = ?";
	// works
	private static final String CHANGE_PROFILEPHOTO = "UPDATE ourwif.users SET profilephoto_path = ? WHERE user_id = ?";
	
	// user_id follows follower_id? user_id sledva foll_id ||| follower - posledovatel
	// works
	private static final String FOLLOW_USER = "INSERT INTO ourwif.followers (user_id, followed_id) VALUES (?, ?);";
	private static final String UNFOLLOW_USER = "DELETE FROM ourwif.followers WHERE user_id = ? AND followed_id = ? ;";
	
	// WITHOUT CITY AND COUNTRY
	private static final String GET_ALL_FOLLOWERS = "SELECT USERS.user_id, first_name, last_name, username, email, password, mobile_number, birthdate, description, gender, profilephoto_path FROM ourwif.users USERS JOIN ourwif.followers FOLLOWERS ON (USERS.user_id = FOLLOWERS.user_id) WHERE followed_id = ?";
	
	private static final String GET_ALL_FOLLOWING = "SELECT u.user_id, u.first_name, u.last_name, u.username, u.email, u.password, u.mobile_number, u.birthdate, u.description, u.gender, u.profilephoto_path FROM users u JOIN followers f ON (u.user_id = f.followed_id) WHERE f.user_id = ?";

	private DataSource dataSource;
	private ApplicationContext context = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//create new user with default album and insert into cachedObjects
	public synchronized void addUser(User user){
		//TODO when creating new user add 1 album
		//TODO "default"
		
		
		
		context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
		Connection connection = null;
		// get city_id and country_id 
		String sql = "SELECT city_id, country_id FROM ourwif.cities WHERE name = '" + user.getCity() + "'";
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		long city_id = 0, country_id = 0;
		try {
			connection = (Connection) dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeQuery();
			if(result.next()){
				city_id = result.getLong("city_id");
				country_id = result.getLong("country_id");
			}
			try {
				user.addAllAlbums(albumDAO.getUserAlbums(user));
			} catch (ValidationException e) {
				System.out.println("cant add all albums to user");
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
			if(result != null){
				try {
					result.close();
				} catch (SQLException e3) {
					System.out.println("Error when closing resultSet in 1st catch block in UserDAO method addUser() - " + e3.getMessage());
				}
			}
		}
		
		// add new user to database
		PreparedStatement prepStatement = null;
		try {
			prepStatement = connection.prepareStatement(INSERT_USER);
			prepStatement.setString(1, user.getFirstName());
			prepStatement.setString(2, user.getLastName());
			prepStatement.setString(3, user.getUsername());
			prepStatement.setString(4, user.getEmail());
			prepStatement.setString(5, user.getPassword());
			prepStatement.setString(6, user.getMobileNumber());
			prepStatement.setDate(7, Date.valueOf(user.getBirthdate()));
			prepStatement.setString(8, user.getDescription());
			prepStatement.setString(9, (user.getGender().toString()));
			prepStatement.setString(10, user.getProfilePhotoPath());
			prepStatement.setLong(11, city_id);
			prepStatement.setLong(12, country_id);
			prepStatement.executeUpdate();
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
		
	public Map<Long, User> getAllUsers() throws ValidationException{
		context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		TreeMap<Long, User> allUsers = new TreeMap<>();
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
			result = preparedStatement.executeQuery();
			while(result.next()){
				User user = new User(result.getString("username"), result.getString("email"), result.getString("password"), result.getLong("user_id"));
				user.changeFirstName(result.getString("first_name"));
				user.changeLastName(result.getString("last_name"));
				user.changeMobileNumber(result.getString("mobile_number"));
				//user.changeBirthDate(result.getDate("birthdate").toLocalDate());
				user.changeDescription(result.getString("description"));
				// enum parser
				user.changeGender(Enum.valueOf(User.Gender.class, result.getString("gender").toUpperCase()));
				if(result.getString("profilephoto_path") != null){
					user.changeProfilePhoto(result.getString("profilephoto_path"));
				}
				user.changeCity(result.getString("city_name"));
				user.changeCountry(result.getString("country_name"));
				allUsers.put(user.getUserId(), user);
				CachedObjects.getInstance().addUser(user);
				user.addAllAlbums(albumDAO.getUserAlbums(user));
				//add followers and following
				getFollows(user, GET_ALL_FOLLOWERS);
				getFollows(user, GET_ALL_FOLLOWING);
			}
		} catch (SQLException e1) {
			System.out.println("Error in 1st catch block in UserDAO method getAllUsers() - " + e1.getMessage());
		}
		finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e2) {
					System.out.println("Error when closing statement in 1st catch block in UserDAO method getAllUsers() - " + e2.getMessage());
				}
			}
			if(result != null){
				try {
					result.close();
				} catch (SQLException e3) {
					System.out.println("Error when closing resultSet in 1st catch block in UserDAO method getAllUsers() - " + e3.getMessage());
				}
			}
		}
		return Collections.unmodifiableMap(allUsers);
	}
	
	//change first name
	public void changeFirstName(User user, String first_name) throws ValidationException{
		user.changeFirstName(first_name);
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
			preparedStatement = connection.prepareStatement(CHANGE_FIRST_NAME);
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
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
			preparedStatement = connection.prepareStatement(CHANGE_LAST_NAME);
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
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
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
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
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
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
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
		ResultSet resultSet = null;
		long country_id = 0;
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setString(1, country_name);
			resultSet = preparedStatement.executeQuery();
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
			if(resultSet != null){
				try {
					resultSet.close();
				} catch (SQLException e3) {
					System.out.println("Error when closing resultSet in 1st catch block in UserDAO method changeCityCountry() - " + e3.getMessage());
				}
			}
		}
		String sql2 = "SELECT city_id FROM ourwif.cities WHERE country_id = " + country_id + " AND name = ?";
		PreparedStatement prepStatement = null;
		ResultSet result = null;
		long city_id = 0;
		try {
			prepStatement = connection.prepareStatement(sql2);
			prepStatement.setString(1, city_name);
			result = prepStatement.executeQuery();
			if(result.next()){
				city_id = result.getLong("city_id");
			}
		} catch (SQLException e4) {
			System.out.println("Error in 2nd catch block in UserDAO method changeCityCountry() - " + e4.getMessage());
		}
		finally{
			if(prepStatement != null){
				try {
					prepStatement.close();
				} catch (SQLException e5) {
					System.out.println("Error when closing statement in 2nd catch block in UserDAO method changeCityCountry() - " + e5.getMessage());
				}
			}
			if(result != null){
				try {
					result.close();
				} catch (SQLException e6) {
					System.out.println("Error when closing resultSet in 2nd catch block in UserDAO method changeCityCountry() - " + e6.getMessage());
				}
			}
		}
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(CHANGE_CITY_AND_COUNTRY);
			statement.setLong(1, city_id);
			statement.setLong(2, country_id);
			statement.setLong(3, user.getUserId());
			statement.executeUpdate();
		} catch (SQLException e7) {
			System.out.println("Error in 3rd catch block in UserDAO method changeCityCountry() - " + e7.getMessage());
		}
		finally{
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e8) {
					System.out.println("Error when closing statement in 3rd catch block in UserDAO method changeCityCountry() - " + e8.getMessage());
				}
			}
		}
	}
	
	//change description
	public void changeDescription(User user, String description) throws ValidationException{
		user.changeDescription(description);
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
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
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
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
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
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
	
	// follow user
	public void followUser(User user, User followedUser) throws SQLException{
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
 			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(FOLLOW_USER);
			preparedStatement.setLong(1, user.getUserId());
			preparedStatement.setLong(2, followedUser.getUserId());
			preparedStatement.executeUpdate();
 		} catch (SQLException e) {
 			connection.rollback();
		}finally{
			preparedStatement.close();
		}
		followedUser.addFollower(user);
		user.addFollowing(followedUser);
	}
	
	// unfollow user
		public void unfollowUser(User user, User followedUser) throws SQLException{
			PreparedStatement preparedStatement = null;
			Connection connection = null;
			try {
				connection = (Connection) dataSource.getConnection();
	 			connection.setAutoCommit(false);
				preparedStatement = connection.prepareStatement(UNFOLLOW_USER);
				preparedStatement.setLong(1, user.getUserId());
				preparedStatement.setLong(2, followedUser.getUserId());
				preparedStatement.executeUpdate();
	 		} catch (SQLException e) {
	 			connection.rollback();
			}finally{
				preparedStatement.close();
			}
			followedUser.removeFollower(user);
			user.removeFollowing(followedUser);
		}
	
	// get user's followers and following
	public void getFollows(User user, String query) throws ValidationException, SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		boolean isFollowers = false;
		if(query.equals(GET_ALL_FOLLOWERS)){
			isFollowers = true;
		}
		try {
			connection = (Connection) dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, user.getUserId());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				User follower = new User(resultSet.getString("username"), resultSet.getString("email"),resultSet.getString("password"), resultSet.getLong("user_id"));
				String results = resultSet.getString("first_name");
				if(!resultSet.wasNull()){
					follower.changeFirstName(results);
				}
				results = resultSet.getString("last_name");
				if(!resultSet.wasNull()){
					follower.changeLastName(results);
				}
				results = resultSet.getString("description");
				if(!resultSet.wasNull()){
					follower.changeDescription(results);
				}
				follower.changeGender(Enum.valueOf(User.Gender.class, resultSet.getString("gender").toUpperCase()));
				Date res = resultSet.getDate("birthdate");
				if(!resultSet.wasNull()){
					follower.changeBirthDate(res.toLocalDate());
				}
				results = resultSet.getString("mobile_number");
				if(!resultSet.wasNull()){
					follower.changeMobileNumber(results);
				}
				results = resultSet.getString("profilephoto_path");
				if(!resultSet.wasNull()){
					follower.changeProfilePhoto(results);
				}
				if(isFollowers){
					user.addFollower(follower);
				}else{
					user.addFollowing(follower);
				}
			}
		}finally{
			preparedStatement.close();
			resultSet.close();
		}
	}

	public boolean validLogin(String username, String password) throws ValidationException {
		CachedObjects cachedObj = CachedObjects.getInstance();
		if(cachedObj.getAllUsers().isEmpty()){
			getAllUsers();
		}
		User user = cachedObj.getOneUser(username);
		if(user == null){
			return false;
		}
		return user.getPassword().equals(password);	
	}
	
}
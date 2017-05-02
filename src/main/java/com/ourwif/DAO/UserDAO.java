package com.ourwif.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.Collections;

import javax.sql.DataSource;
import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ourwif.model.CachedObjects;
import com.ourwif.model.User;

public class UserDAO {

	private static final String SELECT_ALL_USERS = "SELECT user_id, first_name, last_name, username, email, password, mobile_number, birthdate, description, gender, profilephoto_path FROM ourwif.users ";
	
	private static final String ADD_COUNTRIS_TO_USERS = "SELECT user_id, CITY.name AS city_name, COUNTRY.name AS country_name FROM ourwif.users JOIN ourwif.cities CITY ON (users.city_id = CITY.city_id) JOIN ourwif.countries COUNTRY ON (CITY.country_id = COUNTRY.country_id)";
	
	private static final String INSERT_USER = "INSERT INTO ourwif.users (first_name, last_name, username, email, password, mobile_number, birthdate, description, gender, profilephoto_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static final String FOLLOW_USER = "INSERT INTO ourwif.followers (user_id, followed_id) VALUES (?, ?);";
	
	private static final String UNFOLLOW_USER = "DELETE FROM ourwif.followers WHERE user_id = ? AND followed_id = ? ;";
	
	private static final String CHANGE_PASSWORD = "UPDATE ourwif.users SET password = ? WHERE user_id = ?";
	// WITHOUT CITY AND COUNTRY
	private static final String GET_ALL_FOLLOWERS = "SELECT USERS.user_id, first_name, last_name, username, email, password, mobile_number, birthdate, description, gender, profilephoto_path FROM ourwif.users USERS JOIN ourwif.followers FOLLOWERS ON (USERS.user_id = FOLLOWERS.user_id) WHERE followed_id = ?";
	
	private static final String GET_ALL_FOLLOWING = "SELECT u.user_id, u.first_name, u.last_name, u.username, u.email, u.password, u.mobile_number, u.birthdate, u.description, u.gender, u.profilephoto_path FROM users u JOIN followers f ON (u.user_id = f.followed_id) WHERE f.user_id = ?";

	private static final String CONTACT_US = "INSERT INTO ourwif.messages (name, email, mobile_number, message) VALUES (?,?,?,?)";
	
	private DataSource dataSource;
	private ApplicationContext context = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//create new user with default album and insert into cachedObjects
	public synchronized void addUser(User user) throws SQLException{
		//TODO when creating new user add 1 album
		//TODO "default"
		Connection connection = null;
		// get city_id and country_id 
			
		// add new user to database
		PreparedStatement prepStatement = null;
		try {
			connection = (Connection) dataSource.getConnection();
			connection.setAutoCommit(false);	
			prepStatement = connection.prepareStatement(INSERT_USER);
			prepStatement.setString(1, user.getFirstName());
			prepStatement.setString(2, user.getLastName());
			prepStatement.setString(3, user.getUsername());
			prepStatement.setString(4, user.getEmail());
			prepStatement.setString(5, user.getPassword());
			prepStatement.setString(6, user.getMobileNumber());
			prepStatement.setDate(7, null);
			prepStatement.setString(8, user.getDescription());
			prepStatement.setString(9, (user.getGender().toString()));
			prepStatement.setString(10, user.getProfilePhotoPath());
			//prepStatement.setLong(11, city_id);
			//prepStatement.setLong(12, country_id);
			prepStatement.executeUpdate();
		} catch (SQLException e3) {
			connection.rollback();
		}finally{
			connection.setAutoCommit(true);
			connection.close();
			prepStatement.close();
		}		
		CachedObjects.getInstance().addUser(user);		
	}
		
	@SuppressWarnings("resource")
	public Map<Long, User> getAllUsers() throws ValidationException, SQLException{
		context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AlbumDAO albumDAO = (AlbumDAO) context.getBean("AlbumDAO");
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		TreeMap<Long, User> allUsers = new TreeMap<>();
		TreeMap<Long, TreeMap<String, String>> userCounties = new TreeMap<>();
		//county -> city -> user_id
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
			result = preparedStatement.executeQuery();
			while(result.next()){
				User user = new User(result.getString("username"), result.getString("email"), result.getString("password"), result.getLong("user_id"));
				System.out.println(user.getUserId());
				String results = result.getString("first_name");
				if(!result.wasNull()){
					user.changeFirstName(results);
				}
				results = result.getString("last_name");
				if(!result.wasNull()){
					user.changeLastName(results);
				}
				results = result.getString("mobile_number");
				if(!result.wasNull()){
					user.changeMobileNumber(results);
				}
				Date res = result.getDate("birthdate");
				if(!result.wasNull()){
					user.changeBirthDate(res.toLocalDate());
				}
				results = result.getString("description");
				if(!result.wasNull()){
					user.changeDescription(results);
				}
				// enum parser
				user.changeGender(Enum.valueOf(User.Gender.class, result.getString("gender").toUpperCase()));
				
				results = result.getString("profilephoto_path");
				if(!result.wasNull()){
					user.changeProfilePhoto(results);
				}
				
				allUsers.put(user.getUserId(), user);
				CachedObjects.getInstance().addUser(user);
			}
			for(User user : allUsers.values()){
				user.addAllAlbums(albumDAO.getUserAlbums(user));
				//add followers and following
				getFollows(user, GET_ALL_FOLLOWERS);
				getFollows(user, GET_ALL_FOLLOWING);
			}
			//get counties
			preparedStatement = connection.prepareStatement(ADD_COUNTRIS_TO_USERS);
			preparedStatement.execute();
			result = preparedStatement.getResultSet();
			while(result.next()){
				String city = result.getString("city_name");
				String country = result.getString("country_name");
				Long userId = result.getLong("user_id");
				if(!userCounties.containsKey(userId)){
					userCounties.put(userId, new TreeMap<String, String>());
				}
				if(!userCounties.get(userId).containsKey(country)){
					userCounties.get(userId).put(city, country);
				}
			}
			for(Entry<Long, TreeMap<String, String>> e : userCounties.entrySet()){
				for(Entry<String, String> e2 : e.getValue().entrySet()){
					for(User user : allUsers.values()){
						if(user.getUserId() == e.getKey()){
							user.changeCountry(e2.getKey());
							user.changeCity(e2.getValue());
						}
					}
				}
			}
		}finally{
			preparedStatement.close();
			result.close();
			connection.close();
		}
		return Collections.unmodifiableMap(allUsers);
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
			connection.setAutoCommit(true);
		}
		System.out.println(user.getFollowers());
		followedUser.addFollower(user);
		System.out.println(user.getFollowers());
		System.out.println(followedUser.getFollowing());
		user.addFollowing(followedUser);
		System.out.println(followedUser.getFollowing());
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
				connection.setAutoCommit(true);
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
			connection.close();
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

	public boolean validLogin(String username, String password) throws ValidationException, SQLException {
		CachedObjects cachedObj = CachedObjects.getInstance();
		boolean valid = false;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(cachedObj.getAllUsers().isEmpty()){

			getAllUsers();
		}
		User user = cachedObj.getOneUser(username);
		String existingPassword = password; // Password entered by user
		String dbPassword = user.getPassword();  // Load hashed DB password
		if (passwordEncoder.matches(existingPassword, dbPassword)) {
			String hashedPassword = passwordEncoder.encode(password);
			user.changePassword(hashedPassword);
			changePassword(user, hashedPassword);
			valid = true;
		}
		return valid;	
	}
	
	public boolean isUsernameTaken(String username) throws ValidationException, SQLException {
		CachedObjects cachedObj = CachedObjects.getInstance();
		if(cachedObj.getAllUsers().isEmpty()){
			getAllUsers();
		}
		return cachedObj.containsUser(username);
	}
	
	public boolean isEmailTaken(String email) throws ValidationException, SQLException {
		CachedObjects cachedObj = CachedObjects.getInstance();
		Set<User> allUsers = cachedObj.getAllUsers();
		boolean containsEmail = false;
		if(allUsers.isEmpty()){
			getAllUsers();
		}
		for(User users : allUsers){
			if(users.getEmail().equals(email)){
				containsEmail = true;
				break;
			}
		}
		return containsEmail;
	}
	
	// contact form 
	public void contactUs(String name, String email, String message, String mobile_number) throws SQLException{
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
			preparedStatement = connection.prepareStatement(CONTACT_US);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, mobile_number);
			preparedStatement.setString(4, message);
			preparedStatement.executeUpdate();
		}
		finally{
			preparedStatement.close();
			connection.close();
		}
	}
	
}
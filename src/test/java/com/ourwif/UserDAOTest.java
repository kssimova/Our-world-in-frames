package com.ourwif;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ourwif.DAO.UserDAO;
import com.ourwif.model.User;
import com.ourwif.model.User.Gender;

public class UserDAOTest {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	List<User> allUsers = new ArrayList<>();
	User krisi = null;
	User mimi = null;

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllUsers() {	
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			List<User> allUsers = (List<User>) userDAO.getAllUsers();
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		for(User u : allUsers){
			System.out.println("User nomer " + u.getUserId() + " s username " + u.getUsername() + " i imena " + u.getFirstName() + " " + u.getLastName() + 
					" i pol " + u.getGender() + " s email " + u.getEmail() + " i parola " + u.getPassword() + 
					" ot grad " + u.getCity() + " i dyrjava " + u.getCountry() + " i opisanie " + u.getDescription());
		}
		
		fail("Not yet implemented");
	}

	@Test
	public void testFollowUser() throws SQLException {
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		this.user();
		userDAO.followUser(krisi, mimi);	
		fail("Not yet implemented");
	}

	@Test
	public void testGetFollowers() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidLogin() {
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			System.out.println(userDAO.validLogin("user123", "1234"));
		} catch (ValidationException | SQLException e) {
			System.out.println(e.getMessage());
		}
		fail("Not yet implemented");
	}

	
	
	private void user(){
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");

		try {
			krisi = new User("krisi", "krisi@abv.bg", "krisi123", 4);
			krisi.changeMobileNumber("0888888888");
			krisi.changeFirstName("Kristina");
			krisi.changeLastName("Simova");
			krisi.changeCity("sofia");
			krisi.changeCountry("bulgaria");
			krisi.changeDescription("wohoooo");
			krisi.changeBirthDate(LocalDate.now());
			krisi.changeGender(Gender.FEMALE);
			userDAO.addUser(krisi);
			mimi = new User("mimi", "mimi@abv.bg", "mimi123", 7);
			mimi.changeMobileNumber("0888888888");
			mimi.changeFirstName("MIMI");
			mimi.changeLastName("Yordanova");
			mimi.changeCity("sofia");
			mimi.changeCountry("bulgaria");
			mimi.changeDescription("dofngdfnbdibkn");
			mimi.changeBirthDate(LocalDate.now());
			mimi.changeGender(Gender.FEMALE);
			userDAO.addUser(mimi);
		} catch (ValidationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

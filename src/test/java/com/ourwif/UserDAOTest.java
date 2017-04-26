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
		} catch (ValidationException e) {
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
	public void testChangeFirstName() {
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			this.user();
			userDAO.changeFirstName(krisi, "KRISTINA");
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}	
				
		fail("Not yet implemented");
	}

	@Test
	public void testChangeLastName() {
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			this.user();
			userDAO.changeLastName(krisi, "SIMOVAAAA");
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}	
		
		fail("Not yet implemented");
	}

	@Test
	public void testChangeEmail() {	
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			this.user();
			userDAO.changeEmail(krisi, "krisisisisi@abv.bg");
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}	
		
		fail("Not yet implemented");
	}

	@Test
	public void testChangeMobileNumber() {
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			this.user();
			userDAO.changeMobileNumber(krisi, "0883411090");
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}	
		
		fail("Not yet implemented");
	}

	@Test
	public void testChangePassword() {
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			this.user();
			userDAO.changePassword(krisi, "kriska5678");
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}	
		fail("Not yet implemented");
	}

	@Test
	public void testChangeCityCountry() {
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			this.user();
			userDAO.changeCityCountry(krisi, "england", "london");
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}	
		
		fail("Not yet implemented");
	}

	@Test
	public void testChangeDescription() {
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			this.user();
			userDAO.changeDescription(krisi, "yoxoxoxo");
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}	
		
		fail("Not yet implemented");
	}

	@Test
	public void testChangeBirthdate() {
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		
		try {
			this.user();
			userDAO.changeBirthdate(krisi, LocalDate.of(1996, 8, 31));
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}	
		
		fail("Not yet implemented");
	}

	@Test
	public void testChangeProfilePhoto() {
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		try {
			this.user();
			userDAO.changeProfilePhoto(krisi, "poskwienfwoi4353o4n");
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
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
		} catch (ValidationException e) {
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
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

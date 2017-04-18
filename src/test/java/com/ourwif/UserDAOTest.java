package com.ourwif;

import static org.junit.Assert.*;

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
	UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
	List<User> allUsers = new ArrayList<>();
	User krisi = null;
	User mimi = null;

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllUsers() {		
		try {
			List<User> allUsers = userDAO.getAllUsers();
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
		try {
			this.user();
			userDAO.changeProfilePhoto(krisi, "poskwienfwoi4353o4n");
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}
		fail("Not yet implemented");
	}

	@Test
	public void testFollowUser() {
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
		fail("Not yet implemented");
	}

	
	
	private void user(){

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

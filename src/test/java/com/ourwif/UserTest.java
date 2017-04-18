package com.ourwif;
import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ourwif.DAO.PostDAO;
import com.ourwif.DAO.UserDAO;
import com.ourwif.model.User;
import com.ourwif.model.User.Gender;


public class UserTest {
	
	public static void main(String[] args) throws ValidationException {
		
    	ApplicationContext context =
        		new ClassPathXmlApplicationContext("Spring-Module.xml");
		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		
		List<User> allUsers = userDAO.getAllUsers();
		
		for(User u : allUsers){
			System.out.println("User nomer " + u.getUserId() + " s username " + u.getUsername() + " i imena " + u.getFirstName() + " " + u.getLastName() + 
					" i pol " + u.getGender() + " s email " + u.getEmail() + " i parola " + u.getPassword() + 
					" ot grad " + u.getCity() + " i dyrjava " + u.getCountry() + " i opisanie " + u.getDescription());
		}
		
		User krisi = new User("krisi", "krisi@abv.bg", "krisi123", 4);
		krisi.changeMobileNumber("0888888888");
		krisi.changeFirstName("Kristina");
		krisi.changeLastName("Simova");
		krisi.changeCity("sofia");
		krisi.changeCountry("bulgaria");
		krisi.changeDescription("wohoooo");
		krisi.changeBirthDate(LocalDate.now());
		krisi.changeGender(Gender.FEMALE);
		
		//UserDAO.getInstance().addUser(krisi);
		User mimi = new User("mimi", "mimi@abv.bg", "mimi123", 7);
		mimi.changeMobileNumber("0888888888");
		mimi.changeFirstName("MIMI");
		mimi.changeLastName("Yordanova");
		mimi.changeCity("sofia");
		mimi.changeCountry("bulgaria");
		mimi.changeDescription("dofngdfnbdibkn");
		mimi.changeBirthDate(LocalDate.now());
		mimi.changeGender(Gender.FEMALE);
		userDAO.addUser(mimi);
		
		userDAO.changeFirstName(krisi, "KRISTINA");
		
		userDAO.changeLastName(krisi, "SIMOVAAAA");
		
		userDAO.changePassword(krisi, "kriska5678");
		
		userDAO.changeEmail(krisi, "krisisisisi@abv.bg");
		
		userDAO.changeDescription(krisi, "yoxoxoxo");
		
		userDAO.changeProfilePhoto(krisi, "poskwienfwoi4353o4n");
		
		userDAO.changeCityCountry(krisi, "england", "london");
		
		userDAO.changeMobileNumber(krisi, "0883411090");
		
		userDAO.changeBirthdate(krisi, LocalDate.of(1996, 8, 31));
		
		userDAO.followUser(krisi, mimi);
		
		System.out.println(userDAO.validLogin("user123", "1234"));
		
		System.out.println(userDAO.validLogin("user123tdh", "12345dg"));
	}

}

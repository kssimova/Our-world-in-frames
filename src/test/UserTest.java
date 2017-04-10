package test;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.ValidationException;

import DAO.UserDAO;
import model.User;
import model.User.Gender;

public class UserTest {
	
	public static void main(String[] args) throws ValidationException {
		
		List<User> allUsers = UserDAO.getInstance().getAllUsers();
		
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
		
		UserDAO.getInstance().changeFirstName(krisi, "KRISTINA");
		
		UserDAO.getInstance().changeLastName(krisi, "SIMOVAAAA");
		
		UserDAO.getInstance().changePassword(krisi, "kriska5678");
		
		UserDAO.getInstance().changeEmail(krisi, "krisisisisi@abv.bg");
		
		UserDAO.getInstance().changeDescription(krisi, "yoxoxoxo");
		
		UserDAO.getInstance().changeProfilePhoto(krisi, "poskwienfwoi4353o4n");
		
		UserDAO.getInstance().changeCityCountry(krisi, "england", "london");
		
		UserDAO.getInstance().changeMobileNumber(krisi, "0883411090");
		
		UserDAO.getInstance().changeBirthdate(krisi, LocalDate.of(1996, 8, 31));
	}

}

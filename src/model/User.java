package model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class User {
	enum Gender {MALE, FEMALE}
	
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String country;
	private String city;
	private String descriprion;
	private Date birthdate;
	private Gender gender = Gender.FEMALE;
	private String profilePhotoPath;
	private TreeMap <String, Album> albums;
	private TreeSet <User> followers;
	private TreeSet <User> following;
	
	//again builder pattern
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	
	public void changeEmail(String email) {
		
	}
	
	
	public void changePassword(String password) {
		
	}
	
	
	public void changeFirstName(String FirstName) {
		
	}
	
	
	public void changeLastName(String lastNmae) {
		
	}
	
	
	public void changeCountry(String country) {
		
	}
	
	
	public void changeCity(String city) {
		
	}
	
	
	public void changeDescription(String desc) {
		
	}
	
	
	public void changeBirthDate(Date birthDate) {
		
	}
	
	
	public void changeGender(Gender gender) {
		
	}
	
	
	public void changeProfilePhoto(String photopath) {
		
	}
	
	
	public void createAlbum(String albumName, String desc) {
		this.albums.put(albumName, new Album(albumName, desc, LocalDateTime.now(), this));
	}
	
	
	public void addFollower(User user){
		this.followers.add(user);
	}
	
	
	public void addFollowing(User user){
		this.following.add(user);
	}
	
	
	public String getUsername() {
		return username;
	}


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getCountry() {
		return country;
	}


	public String getCity() {
		return city;
	}


	public String getDescriprion() {
		return descriprion;
	}


	public Date getBirthdate() {
		return birthdate;
	}


	public Gender getGender() {
		return gender;
	}


	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}


	public Map<String, Album> getAlbums()  {
		return Collections.unmodifiableSortedMap(albums);
	}

	public Set<User> getFollowers() {
		return Collections.unmodifiableSortedSet(followers);
	}

	public Set<User> getFollowing()  {
		return Collections.unmodifiableSortedSet(following);
	}
	
	
}

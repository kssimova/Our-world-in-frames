package com.ourwif.model;


import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ValidationException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Comparable<User>{
	public enum Gender {MALE, FEMALE}
	private final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9]+.[a-z.]+$";
	private final String MOBILEPHONE_PATTERN = "([08]{2}+[0-9]{8})";
	
	private long userId;
	private String username;
	@JsonIgnore
	private String email;
	@JsonIgnore
	private String password;
	private String firstName;
	private String lastName;
	private String country;
	private String city;
	private String descriprion;
	private String mobileNumber;
	private LocalDate birthdate;
	private Gender gender;
	private String profilePhotoPath;
	private TreeMap <Long, Album> albums;
	private TreeSet <User> followers;
	private TreeSet <User> following;
	private Pattern pattern = null;
	private Matcher matcher = null;
	
	public User(String username) throws ValidationException{
		if(validUsername(username)){
			this.username = username;
		}
		else{
			throw new ValidationException("Username not valid!");
		}
	}
	
	//again builder pattern
	public User(String username, String email, String password, long userId) throws ValidationException {
		if(validUsername(username)){
			this.username = username;
		}else{
			throw new ValidationException("Username not valid");
		}
		if(validEmail(email)){
			this.email = email;
		}else{
			throw new ValidationException("User email not valid");
		}
		if(validPassword(password)){
			this.password = password;
		}else{
			throw new ValidationException("User password not valid");
		}
		this.albums = new TreeMap<>();
		this.followers = new TreeSet<>();
		this.following = new TreeSet<>();
		this.userId = userId;
	}
	
	// getters
	
	public String getUsername() {
		return username;
	}
	@JsonIgnore
	public String getEmail() {
		return email;
	}
	@JsonIgnore
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
	
	public String getDescription() {
		return descriprion;
	}
	
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	public long getUserId() {
		return userId;
	}

	public Map<Long, Album> getAlbums()  {
		return Collections.unmodifiableSortedMap(albums);
	}
	public Set<Long> getFollowers() {
		TreeSet <Long> follower = new TreeSet<>();
		for(User u : followers){
			follower.add(u.getUserId());
		}
		return Collections.unmodifiableSortedSet(follower);
	}
	public Set<Long> getFollowing()  {
		TreeSet <Long> followin = new TreeSet<>();
		for(User u : followers){
			followin.add(u.getUserId());
		}
		return Collections.unmodifiableSortedSet(followin);
	}	

	// setters	
	
	public void SetUserId(long userId){
		this.userId = userId;
	}
	
	// change properties
	@JsonProperty
	public void changeEmail(String email) throws ValidationException {
		if(validEmail(email)){
			this.email = email;		
		}else{
			throw new ValidationException("User email not valid");
		}
	}
	
	public void changeMobileNumber(String mobileNumber) throws ValidationException{
		if(patternFinder(MOBILEPHONE_PATTERN, mobileNumber)){
			this.mobileNumber = mobileNumber;		
		}else{
			throw new ValidationException("User phone number not valid");
		}
	}
	@JsonProperty
	public void changePassword(String password) throws ValidationException {
		if (validPassword(password)) {
			this.password = password;
		}else{
			throw new ValidationException("User password not valid");
		}
	}
	
	
	public void changeFirstName(String firstName) throws ValidationException {
		if (validateString(firstName) && firstName.length() <= 45) {
			this.firstName = firstName;
		}else{
			throw new ValidationException("User first name not valid");
		}
	}
	
	public void changeLastName(String lastName) throws ValidationException {
		if (validateString(lastName) && lastName.length() <= 45) {
			this.lastName = lastName;
		}else{
			throw new ValidationException("User last name not valid");
		}
	}
	
	public void changeCountry(String country) throws ValidationException {
		if (validateString(country) && country.length() <= 45) {
			this.country = country;
		}else{
			throw new ValidationException("User country not valid");
		}
	}
	
	
	public void changeCity(String city) throws ValidationException {
		if (validateString(city) && city.length() <= 45) {
			this.city = city;
		}else{
			throw new ValidationException("User city not valid");
		}
	}
	
	
	public void changeDescription(String desc) throws ValidationException {
		if (desc.length() <= 400) {
			this.descriprion = desc;
		}else{
			throw new ValidationException("User description not valid");
		}
	}
	
	
	public void changeBirthDate(LocalDate birthDate) {
		this.birthdate = birthDate;
	}
	
	
	public void changeGender(Gender gender) {
		this.gender = gender;
	}
	
	
	public void changeProfilePhoto(String picturePath) throws ValidationException {
		if(picturePath.length() <= 500){
			this.profilePhotoPath = picturePath;
		}else{
			throw new ValidationException("User profile picture not valid");
		}
	}
		
	public Album createAlbum(String albumName, String desc,  long albumId) throws ValidationException {
		Album album = new Album(albumName, desc, LocalDate.now(), this);
		album.setAlbumId(albumId);
		this.albums.put(albumId, album);
		return album;
	}
	
	public void deleteAlbum(Album album) throws ValidationException {
		if(albums.containsKey(album.getAlbumId())){
			this.albums.remove(album.getAlbumId());
		}
	}
	
	
	
	public void addFollower(User user){
		if(validUser(user)){
			this.followers.add(user);
		}
	}
	
	
	public void removeFollower(User user){
		if(validUser(user)){
			this.followers.remove(user);
		}
	}
	
	
	public void addFollowing(User user){
		if(validUser(user)){
			this.following.add(user);
		}
	}
	
	public void removeFollowing(User user){
		if(validUser(user)){
			this.following.remove(user);
		}
	}
	
	//validations
	
	public boolean patternFinder(String regex, String field) {
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(field);
		return (matcher.matches() && !field.isEmpty() && field != null);
	}
	
	public boolean validateString(String str){
		return (!str.isEmpty() && str != null && str.length() >= 2 && !str.startsWith(" ") && !str.endsWith(" "));
	}
	
	private boolean validUser(User user){
		return user != null;
	}
	
	private boolean validUsername(String username) {
		return (validateString(username) && username.length() <= 45);
	}
	
	private boolean validEmail(String email){
		return (patternFinder(EMAIL_PATTERN, email));
	}
	private boolean validPassword(String password){
		return (validateString(password) && password.length() <= 100);
	}
	
	//compare to other Posts

	@Override
	public int compareTo(User user) {
		return this.username.compareTo(user.username);
	}

	public void addAllAlbums(TreeMap<Long, Album> userAlbums) {
		this.albums.putAll(userAlbums);	
	}

	public Set<User> getAllFollowers() {
		return Collections.unmodifiableSortedSet(followers);	
	}
	
}
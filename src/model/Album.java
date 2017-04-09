package model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

public class Album implements Comparable<Album>{
	
	private long albumId;
	private String name;
	private String description;
	private LocalDate dateCreated;
	private User user;
	private TreeSet <Post> photos;
	
	//if you want we can use builder pattern
	public Album(String name, String description, LocalDate dateCreated, User user) throws ValidationException {
		if(validName(name)){
			this.name = name;
		}else{
			throw new ValidationException("Album name not valid");
		}
		if(validDesc(description)){
			this.description = description;
		}else{
			throw new ValidationException("Album description not valid");
		}
		if(validDate(dateCreated)){
			this.dateCreated = dateCreated;
		}else{
			throw new ValidationException("Album date not valid");
		}
		if(validUser(user)){
			this.user = user;
		}else{
			throw new ValidationException("Album user not valid");
		}
		this.photos = new TreeSet<>();
	}

	//getters
	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public LocalDate getDateCreated() {
		return dateCreated;
	}


	public User getUser() {
		return user;
	}
	
	public Set<Post> getPhotos() {
		return Collections.unmodifiableSortedSet(photos);
	}
	
	public long getAlbumId() {
		return albumId;
	}

	
	//setters
	
	public void addPosts(Post post){
		if(validPost(post)){
			photos.add(post);
		}
	}
	
	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}
	
	public void changeName(String name){
		if (validName(name)){
			this.name = name;
		}
	}
	
	public void changeDescription(String desc){
		if(validDesc(desc)){
			this.description = desc;
		}
	}

	//validations
	private boolean validName(String name) {
		return (name != null && name.length()>2 && name.length() <=100);
	}
	
	private boolean validDesc(String description){
		return description.length() <= 500;
	}
	
	private boolean validDate(LocalDate date){
		return date != null;
	}
	
	private boolean validUser(User user){
		return user != null;
	}
	
	private boolean validPost(Post post){
		return post != null;
	}
	
	
	//compare to other Albums
	
	@Override
	public int compareTo(Album album) {
		int compare = this.name.compareTo(album.name);
		if(compare == 0){
			return this.dateCreated.compareTo(album.dateCreated);
		}
		return compare;
	}
	
}

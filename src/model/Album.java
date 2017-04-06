package model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Album {
	
	private String name;
	private String description;
	private LocalDateTime dateCreated;
	private User user;
	private TreeSet <Post> photos;
	
	//if you want we can use builder pattern
	public Album(String name, String description, LocalDateTime dateCreated, User user) {
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.user = user;
		this.photos = new TreeSet<>();
	}


	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public LocalDateTime getDateCreated() {
		return dateCreated;
	}


	public User getUser() {
		return user;
	}


	public Set<Post> getPhotos() {
		return Collections.unmodifiableSortedSet(photos);
	}
	
	public void addPosts(Post p){
		photos.add(p);
	}
	
}

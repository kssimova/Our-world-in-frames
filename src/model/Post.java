package model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Post {
	
	private User user;
	private String name;
	private String description;
	private LocalDateTime dateCreated;
	private String picturePath;
	private TreeSet <Comment> comments;
	private TreeSet <User> likes;
	private TreeSet <String> tags;
	
	
	public Post(User user, String name, String description, LocalDateTime dateCreated, String picturePath, TreeSet tags) {
		this.user = user;
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.picturePath = picturePath;
		this.comments = new TreeSet<>();
		this.likes = new TreeSet<>();
		this.tags = new TreeSet<>();
		this.tags = tags;
		
	}

	public User getUser() {
		return user;
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


	public String getPicturePath() {
		return picturePath;
	}

	
	public Set<Comment> getComments() {
		return Collections.unmodifiableSortedSet(comments);
	}
	
	
	public Set<User> getLikes() {
		return Collections.unmodifiableSortedSet(likes);
	}
	
	
	public Set<String> getTags() {
		return Collections.unmodifiableSortedSet(tags);
	}
	
	
	public void addComment(Comment comment){
		this.comments.add(comment);
	}
	
	
	public void addLike(User user){
		this.likes.add(user);
	}

}

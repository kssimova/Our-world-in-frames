package model;

import java.time.LocalDateTime;

public class Comment {
	
	private Post picture;
	private User user;
	protected String content;
	private LocalDateTime dateCreated;
	private Comment comment;
	
	
	public Comment(Post picture, User user, String content, LocalDateTime dateCreated) {
		this.picture = picture;
		this.user = user;
		this.content = content;
		this.dateCreated = dateCreated;
	}


	public Post getPicture() {
		return picture;
	}


	public User getUser() {
		return user;
	}


	public String getContent() {
		return content;
	}


	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	
	
	public Comment getComment(){
		return comment;
	}


	public void changeContent(String content) {
		this.content = content;
	}
	
	public void addComment(){
		if(this.comment == null){
			this.comment = comment;
		}
	}

	
}

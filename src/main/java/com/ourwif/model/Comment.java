package com.ourwif.model;


import java.time.LocalDate;

import javax.xml.bind.ValidationException;


public class Comment implements Comparable<Comment>{
	
	private long commentId;
	private Post picture;
	private User user;
	protected String content;
	private LocalDate dateCreated;
	
	
	public Comment(Post picture, User user, String content, LocalDate localDate, long commentId) throws ValidationException {
		if(validPost(picture)){
			this.picture = picture;
		}else{
			throw new ValidationException("Comment photo not valid");
		}
		if(validUser(user)){
			this.user = user;
		}else{
			throw new ValidationException("Comment user not valid");
		}
		if(validContent(content)){
			this.content = content;
		}else{
			throw new ValidationException("Comment content not valid");
		}
		if(validDate(localDate)){
			this.dateCreated = localDate;
		}else{
			throw new ValidationException("Comment date not valid");
		}
		setCommentId(commentId);
	}

	//getters
	
	public Post getPicture() {
		return picture;
	}


	public User getUser() {
		return user;
	}


	public String getContent() {
		return content;
	}


	public LocalDate getDateCreated() {
		return dateCreated;
	}
	

	public long getCommentId() {
		return commentId;
	}


	//setters
	
	public void changeContent(String content) {
		this.content = content;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	
	//validations
	
	private boolean validPost(Post picture) {
		return picture != null;
	}

	private boolean validUser(User user) {
		return user !=null;
	}
	
	private boolean validContent(String content) {
		return (!content.isEmpty() && content != null && content.length() <= 500) ;
	}
	
	private boolean validDate(LocalDate date){
		return date != null;
	}

	
	//compare to other Comments
	
	@Override
	public int compareTo(Comment comment) {
		return this.dateCreated.compareTo(comment.dateCreated);
	}
	
}
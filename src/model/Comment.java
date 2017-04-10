package model;

import java.time.LocalDate;

import javax.xml.bind.ValidationException;

import com.google.gson.JsonObject;

public class Comment implements Comparable<Comment>{
	
	private long commentId;
	private Post picture;
	private User user;
	protected String content;
	private LocalDate dateCreated;
	private Comment comment;
	
	
	public Comment(Post picture, User user, String content, LocalDate localDate, Comment comment, long commentId) throws ValidationException {
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
		addComment(comment);
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
	
	
	public Comment getComment(){
		return comment;
	}

	public long getCommentId() {
		return commentId;
	}


	//setters
	
	public void changeContent(String content) {
		this.content = content;
	}
	
	public void addComment(Comment comment) throws ValidationException{
		if(this.comment == null){
			this.comment = comment;
		}else{
			throw new ValidationException("You can't comment on a subcomment");
		}
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
	
	//JSON
	public JsonObject getAsJSON(){
		JsonObject root = new JsonObject();
		//create JSONObject
		root.addProperty("commentId", this.commentId);
		root.addProperty("content", this.content);
		root.addProperty("dateCreated", this.dateCreated.toString());
		root.addProperty("pictureId", this.picture.getPostId());
		if(this.comment != null){
			JsonObject subComment = this.comment.getAsJSON();
			root.add("supComment", subComment);
		}
		JsonObject user = new JsonObject();
		user.addProperty("username", this.user.getUsername());
		user.addProperty("userId", this.user.getUserId());
		root.add("user", user);		
		return root;
	}
	
}

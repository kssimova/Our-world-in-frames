package model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

public class Post implements Comparable<Post>{
	
	private long postId;
	private User user;
	private String name;
	private String description;
	private LocalDateTime dateCreated;
	private String picturePath;
	private TreeSet <Comment> comments;
	private TreeSet <User> likes;
	private TreeSet <String> tags;
	
	
	public Post(User user, String name, String description, LocalDateTime dateCreated, String picturePath, TreeSet<String> tags, long postId) throws ValidationException {
		if(validUser(user)){
			this.user = user;
		}else{
			throw new ValidationException("Post user can't be added");
		}
		if(validName(name)){
			this.name = name;
		}else{
			throw new ValidationException("Post name can't be added");
		}
		if(validDescription(description)){
			this.description = description;
		}else{
			throw new ValidationException("Post description can't be added");
		}
		if(validDate(dateCreated)){
			this.dateCreated = dateCreated;
		}else{
			throw new ValidationException("Post date can't be added");
		}
		if(validPicturePath(picturePath)){
			this.picturePath = picturePath;
		}else{
			throw new ValidationException("Post picture path can't be added");
		}
		this.comments = new TreeSet<>();
		this.likes = new TreeSet<>();
		this.tags = new TreeSet<>();
		addTags(tags);
		SetPostId(postId);
		
	}

	//getters
	
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
	
	public long getPostId() {
		return postId;
	}
	
	//setters
	
	public void SetPostId(long postId){
		this.postId = postId;
	}
	
	public void changeName(String name) throws ValidationException{
		if(validName(name)){
			this.name = name;
		}else{
			throw new ValidationException("Post name can't be changed");
		}
	}
	
	public void changeDescription(String desc) throws ValidationException{
		if(validDescription(desc)){
			this.description = desc;
		}else{
			throw new ValidationException("Post description can't be changed");
		}
	}
	
	public void addComment(Comment comment) throws ValidationException{
		if(validComment(comment)){
			this.comments.add(comment);
		}else{
			throw new ValidationException("Post comment can't be added");
		}
	}
	
	public void addLike(User user) throws ValidationException{
		if(validUser(user)){
			this.likes.add(user);
		}else{
			throw new ValidationException("Post like can't be added");
		}
	}
	
	public void removeLike(User user) throws ValidationException{
		if(validUser(user)){
			this.likes.remove(user);
		}else{
			throw new ValidationException("Post like can't be removed");
		}
	}
	
	public void addTags(TreeSet<String> tags) throws ValidationException{
		if(validTags(tags)){
			this.tags.addAll(tags);
		}else{
			throw new ValidationException("Post tags can't be added");
		}
	}
	
	//validations	
	
	private boolean validName(String name) {
		return (name != null && name.length()>2 && name.length() <= 45);
	}
	
	private boolean validDescription(String desc) {
		return description.length() <= 500;
	}
	
	private boolean validComment(Comment comment) {
		return !comment.equals(null);
	}
	
	private boolean validUser(User user){
		return !user.equals(null);
	}
	
	private boolean validTags(TreeSet<String> tags){
		return tags.equals(null);
	}
	
	private boolean validDate(LocalDateTime date){
		return !date.equals(null);
	}
	
	private boolean validPicturePath(String picturePath) {
		return (!picturePath.isEmpty() && picturePath.equals(null) && picturePath.length() <= 500);
	}

	//compare to other Posts
	
	@Override
	public int compareTo(Post post) {
		int compare = this.name.compareTo(post.name);
		if(compare == 0){
			return this.dateCreated.compareTo(post.dateCreated);
		}
		return compare;
	}
	
}

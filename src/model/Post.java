package model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Post implements Comparable<Post>{
	
	private String postId;
	private String deleteHash;
	private User user;
	private String name;
	private String description;
	private LocalDate dateCreated;
	private String picturePath;
	private TreeSet <Comment> comments;
	private TreeSet <User> likes;
	private TreeSet <String> tags;
	
	
	public Post(User user, String name, String description, LocalDate dateCreated, String picturePath, TreeSet<String> tags) throws ValidationException {
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
	}
	
	public Post(User user, String name, String description, LocalDate dateCreated, String picturePath, TreeSet<String> tags, String postId, String deleteHash) throws ValidationException {
		this(user, name, description, dateCreated, picturePath, tags);
		setPostId(postId);
		setDeleteHash(deleteHash);
		
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


	public LocalDate getDateCreated() {
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
	
	public String getPostId() {
		return postId;
	}
	
	public String getDeleteHash() {
		return deleteHash;
	}
	
	//setters
	
	public void setPostId(String postId){
		this.postId = postId;
	}
	

	public void setDeleteHash(String deleteHash) {
		this.deleteHash = deleteHash;
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
	
	public void removeComment(Comment comment) throws ValidationException{
		if(validComment(comment)){
			this.comments.remove(comment);
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
			this.tags.clear();
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
		return desc.length() <= 500;
	}
	
	private boolean validComment(Comment comment) {
		return comment != null;
	}
	
	private boolean validUser(User user){
		return user != null;
	}
	
	private boolean validTags(TreeSet<String> tags){
		return tags != null;
	}
	
	private boolean validDate(LocalDate date){
		return date != null;
	}
	
	private boolean validPicturePath(String picturePath) {
		return (!picturePath.isEmpty() && picturePath != null && picturePath.length() <= 500);
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
	
	//JSON
	public JsonObject getAsJSON(){
		JsonObject root = new JsonObject();
		//create JSONObject
		root.addProperty("postId", this.postId);
		root.addProperty("deleteHash", this.deleteHash);
		root.addProperty("name", this.name);
		root.addProperty("dateCreated", this.dateCreated.toString());
		root.addProperty("picturePath", this.picturePath);
		root.addProperty("description", this.description);
		//tags
		JsonArray tagsArray = new JsonArray();
		if(!tags.isEmpty() && tags != null ){
			for(String str: tags){
				JsonObject tag = new JsonObject();
				tag.addProperty("tag", str);
				tagsArray.add(tag);	
			}
		}
		root.add("tags", tagsArray);
		//comments
		JsonArray commentArray = new JsonArray();
		if(!comments.isEmpty() && comments != null){
			for(Comment comment: comments){
				JsonObject comm = new JsonObject();
				comm = comment.getAsJSON();
				commentArray.add(comm);
			}
		}
		root.add("comments", commentArray);
		//user
		JsonObject user = new JsonObject();
		user.addProperty("username", this.user.getUsername());
		user.addProperty("userId", this.user.getUserId());
		root.add("user", user);
		//likes
		JsonArray likesArray = new JsonArray();
		if(!likes.isEmpty() && likes != null ){
			for(User u: likes){
				JsonObject userLike = new JsonObject();
				userLike.addProperty("username", u.getUsername());
				userLike.addProperty("userId", u.getUserId());
				likesArray.add(userLike);
			}
			root.add("likes", likesArray);
		}
		return root;
	}
	
}

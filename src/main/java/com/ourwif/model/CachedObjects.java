 package com.ourwif.model;
 
 
 import java.util.Map.Entry;
 import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
 import java.util.Map;
 import java.util.TreeMap;
 import java.util.TreeSet;

public class CachedObjects {
	
	private static CachedObjects instance;
	private final TreeMap<Long, User> allUsers = new TreeMap<>();
	//user id -> user
	private final TreeMap<Long, TreeMap<String, Post>> allPosts = new TreeMap<>();
	//album id -> postId -> post
	private final TreeMap<Long, Album> allAlbums = new TreeMap<>();
	// Album id -> album
	
	//used for faster search
	private final TreeMap<String, TreeSet<String>> allTags = new TreeMap<>();
	// tag -> tree set of post id-s
	private final TreeMap<String, TreeSet<String>> allPost = new TreeMap<>();
	// name -> tree set of post id-s
	
	private CachedObjects() {
	}	
	public static synchronized CachedObjects getInstance() {
		if (instance == null) {
			instance = new CachedObjects();
		}
		return instance;
	}
	
	
	//USER
	public User getOneUser(long userId) {
		return allUsers.get(userId);
	}
	
	public User getOneUser(String username) {
		User user = null;
		for(User users : allUsers.values()){
			if(users.getUsername().equals(username)){
				user = users;
				break;
			}
		}
		return user;
	}
	
	public boolean containsUser(String username){
		boolean contains = false;
		for(User users : allUsers.values()){
			if(users.getUsername().equals(username)){
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	//add user
	public void addUser(User user){
		allUsers.put(user.getUserId(), user);
	}
	
	//POST
	public Post getOnePost(String postId) {
		Post p = null;
		for(Entry <Long, TreeMap<String, Post>> e : allPosts.entrySet()){
			for(Entry<String, Post> e1 : e.getValue().entrySet()){
				if(e1.getKey().equals(postId)){
					p = e1.getValue();
					break;
				}
			}
		}
		return p;
	}
	
	public Post getOnePost(String postId, long albumId) {
		Post p = null;
		for(Entry <Long, TreeMap<String, Post>> e : allPosts.entrySet()){
			if(e.getKey().equals(albumId)){
				for(Entry<String, Post> e1 : e.getValue().entrySet()){
					if(e1.getKey().equals(postId)){
						p = e1.getValue();
					}
				}
			}
		}
		return p;
	}
	
	//remove post
	public void removePost(Post post, Album album){
		for(Iterator <Entry <Long, TreeMap<String, Post>>> it = allPosts.entrySet().iterator(); it.hasNext(); ){
			Entry<Long, TreeMap<String, Post>> e1 = it.next();
			if(e1.getKey().equals(album.getAlbumId())){
				for(Iterator <Entry <String, Post>> it2 = e1.getValue().entrySet().iterator(); it2.hasNext();){
					Entry <String, Post> e2 = it2.next();
					if(e2.getKey().equals(post.getPostId())){
						it2.remove();
					}
				}
			}
		}
	}	
		
	//add post
	public void addPost(Post post, Album album){
		if(!allPosts.keySet().contains(album.getAlbumId())){
			allPosts.put(album.getAlbumId(), new TreeMap<String, Post>());
		}
		if(!allPosts.get(album.getAlbumId()).containsKey(post.getPostId())){
			allPosts.get(album.getAlbumId()).put(post.getPostId(), post);
		}
		
		if(!allPost.containsKey(post.getName())){
			allPost.put(post.getName(), new TreeSet<>());
		}
		allPost.get(post.getName()).add(post.getPostId());
	}
	
	//add post
	public void addPost(Post post, Long albumId){
		if(!allPosts.keySet().contains(albumId)){
			allPosts.put(albumId, new TreeMap<String, Post>());
		}
		if(!allPosts.get(albumId).containsKey(post.getPostId())){
			allPosts.get(albumId).put(post.getPostId(), post);
		}
		
		if(!allPost.containsKey(post.getName())){
			allPost.put(post.getName(), new TreeSet<>());
		}
		allPost.get(post.getName()).add(post.getPostId());
		if(!allAlbums.isEmpty()){
			allAlbums.get(albumId).addPosts(post);
		}
	}
	
	public boolean containsPost(String postId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//ALBUM
	//add albums
	public void addAlbums(Album album){
		if(!allAlbums.containsKey(album.getAlbumId())){
			allAlbums.put(album.getAlbumId(), album);
		}
	}
	
	public Album getOneAlbum(long albumId) {
		return allAlbums.get(albumId);
	}
	
	public Album getOneAlbum(String name) {
		Album album = null;
		for(Entry<Long, Album> e : allAlbums.entrySet()){
			if (e.getValue().getName().equals(name)){
				album = e.getValue();
			}
		}
		return album;
	}
	
	public boolean containsAlbum(Integer albumId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//COMMENTS
	
	
	//TAGS
	public void addTags(TreeSet <String> t, Post post){
		for(String tag: t){
			String tagche = tag.toLowerCase().trim();
			if (!allTags.containsKey(tag)){
				allTags.put(tagche, new TreeSet<String>());
				allTags.get(tagche).add(post.getPostId());
			}else{
				allTags.get(tagche).add(post.getPostId());
			}	
		}
	}
	
	//SEARCH
	public TreeSet<String> getPhotosWithTag(TreeSet <String> t){
		TreeSet<String> posts = new TreeSet<>();
		for(String alltag : allTags.keySet()){
			for(String tag: t){
				String tagche = tag.toLowerCase().trim();
				if(alltag.contains(tagche)){
					posts.addAll(allTags.get(alltag));
				}
			}
		}
		return posts;
	}
	
	public TreeSet<String> getPhotosWithName(TreeSet <String> t){
		TreeSet<String> posts = new TreeSet<>();
		for(String allpost : allPost.keySet()){
			for(String str: t){
				String stri =  str.toLowerCase().trim();
				if(allpost.contains(stri)){
					posts.addAll(allPost.get(allpost));
				}
			}
		}
		return posts;
	}	
	
	//just for tests
	public Map <Long, TreeMap<String, Post>> getAllPosts() {
		return  Collections.unmodifiableSortedMap(allPosts);
	}
	
	public Map <Long, Album> getAllAlbums(){
		return  Collections.unmodifiableSortedMap(allAlbums);
	}
	
	public Map<Long, User> getAllUsers() {
		return  Collections.unmodifiableSortedMap(allUsers);
	}
	
	public Map<String, TreeSet<String>> getAllTags() {
		return  Collections.unmodifiableSortedMap(allTags);
	}
	
	
	//comparators
	
	public static Comparator<Post> mostLikesComparator = new Comparator<Post>(){
		@Override
		public int compare(Post post1, Post post2) {
			int result = post2.getLikes().size() - post1.getLikes().size();
			if(result == 0){
				return post2.getPostId().compareTo(post1.getPostId());
			}
			return result;
		}
	};
	public static Comparator<Post> dateCreatedComparator = new Comparator<Post>(){
		@Override
		public int compare(Post post1, Post post2) {
			int result = post2.getDateCreated().compareTo(post1.getDateCreated());
			if(result == 0){
				return post2.getPostId().compareTo(post1.getPostId());
			}
			return result;
		}
	};
	
}

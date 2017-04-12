package com.ourwif.model;


import java.util.Map.Entry;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class CachedObjects {
	
	private static CachedObjects instance;
	private final TreeMap<Long, User> allUsers = new TreeMap<>();
	private final TreeMap<Long, TreeMap<String, Post>> allPosts = new TreeMap<>();
	private final TreeMap<Long, Album> allAlbums = new TreeMap<>();
	
	private CachedObjects() {

	}
	
	public static synchronized CachedObjects getInstance() {
		if (instance == null) {
			instance = new CachedObjects();
		}
		return instance;
	}
	
	public User getOneUser(long userId) {
		return allUsers.get(userId);
	}
	public User getOneUser(String username) {
		User user = null;
		for(User users : allUsers.values()){
			if(users.getUsername().equals(username)){
				user = users;
			}
		}
		return user;
	}
	
	public boolean containsUser(String username){
		boolean contains = false;
		for(User users : allUsers.values()){
			if(users.getUsername().equals(username)){
				contains = true;
			}
		}
		return contains;
	}
	
	

	public Post getOnePost(String postId) {
		Post p = null;
		for(Entry <Long, TreeMap<String, Post>> e : allPosts.entrySet()){
			for(Entry<String, Post> e1 : e.getValue().entrySet()){
				if(e1.getKey().equals(postId)){
					p = e1.getValue();
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
	
	//add user
	public void addUser(User user){
		allUsers.put(user.getUserId(), user);
	}
		
	//add post
	public void addPost(Post post, Album album){
		if(!allPosts.keySet().contains(album.getAlbumId())){
			allPosts.put(album.getAlbumId(), new TreeMap<String, Post>());
		}
		if(!allPosts.get(album.getAlbumId()).containsKey(post.getPostId())){
			allPosts.get(album.getAlbumId()).put(post.getPostId(), post);
		}
	}
	
	//add post
	public void addPost(Post post, Long albumId){
		if(!allPosts.keySet().contains(albumId)){
			allPosts.put(albumId, new TreeMap<String, Post>());
		}
		if(!allPosts.get(albumId).containsKey(post.getPostId())){
			allPosts.get(albumId).put(post.getPostId(), post);
		}
	}
	
	//add albums
	public void addAlbums(Album album){
		if(!allAlbums.containsKey(album.getAlbumId())){
			allAlbums.put(album.getAlbumId(), album);
		}
	}
	
	//just for tests
	public Map <Long, TreeMap<String, Post>> getAllPosts() {
		return  Collections.unmodifiableSortedMap(allPosts);
	}
	
	public Map <Long, Album> getAllAlbums(){
		return  Collections.unmodifiableSortedMap(allAlbums);
	}
	
	public void clearAlbums(){
		allAlbums.clear();
	}
	
	public void clearPosts(){
		allPosts.clear();
	}
	
}
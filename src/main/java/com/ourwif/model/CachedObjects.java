 package com.ourwif.model;
 
 import java.util.Map.Entry;
 import java.util.Set;


import java.util.Collections;
 import java.util.Comparator;
 import java.util.TreeSet;

public class CachedObjects {
	
	private static CachedObjects instance;
	private final TreeSet<User> allUsers = new TreeSet<>();
	
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
		User user = null;
		for(User user1 : allUsers){
			if(user1.getUserId() == userId){
				user = user1;
				break;
			}
		}
		return user;
	}
	
	public User getOneUser(String username) {
		User user = null;
		for(User user1 : allUsers){
			if(user1.getUsername().equals(username)){
				user = user1;
				break;
			}
		}
		return user;
	}
	
	public boolean containsUser(String username){
		boolean contains = false;
		for(User user1 : allUsers){
			if(user1.getUsername().equals(username)){
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	//add user
	public void addUser(User user){
		if(!allUsers.contains(user)){
			allUsers.add(user);
		}
	}
	
	//POST
	public Post getOnePost(String postId) {
		Post p = null;
		for(User user1: allUsers){
			for(Album album : user1.getAlbums().values()){
				for(Post post : album.getPhotos()){
					if(post.getPostId().equals(postId)){
						p = post;
						break;
					}
				}
			}
		}
		return p;
	}
	
	public Post getOnePost(String postId, long albumId) {
		Post p = null;
		for(User user1 : allUsers){
			for(Entry<Long, Album> e1 : user1.getAlbums().entrySet()){
				if(e1.getKey() == albumId){
					for(Post post : e1.getValue().getPhotos()){		
						if(post.getPostId() == postId){
							p = post;
							break;
						}
					}
				}
			}
		}
		return p;
	}
	
	//remove post
	public void removePost(Post post, Album album){
		for(User user : allUsers){
			for(Entry <Long, Album> albums : user.getAlbums().entrySet()){
				if(albums.getValue().equals(album)){
					for(Post post1 : albums.getValue().getPhotos()){
						if(post1.equals(post)){
							albums.getValue().removePost(post);
						}
					}
				}
			}	
		}
	}	
		
	//add post
	public void addPost(Post post, Album album){
		User user = post.getUser();
		if(!allUsers.contains(user)){
			allUsers.add(user);
		}
		if(!user.getAlbums().containsKey(album.getAlbumId())){
			user.getAlbums().put(album.getAlbumId(), album);
		}
		if(!user.getAlbums().get(album.getAlbumId()).getPhotos().contains(post)){
			user.getAlbums().get(album.getAlbumId()).getPhotos().add(post);
		}
	}
	
	//add post
	public void addPost(Post post, Long albumId){
		Album album = getOneAlbum(albumId);	
		addPost(post, album);
	}
	
	public boolean containsPost(String postId) {
		boolean contains = false;
		for(User user : allUsers){
			for(Entry<Long, Album> albums : user.getAlbums().entrySet()){
				for(Post post : albums.getValue().getPhotos()){
					if(post.getPostId().equals(postId)){
						contains = true;
						break;
					}
				}		
			}
		}
		return contains;
	}
	
	//ALBUM
	//add albums
	public void addAlbums(Album album){
		if(!allUsers.contains(album.getUser())){
			allUsers.add(album.getUser());
		}
	}
	
	public Album getOneAlbum(long albumId) {
		Album album = null;
		for(User user : allUsers){
			for(Entry<Long, Album> albums : user.getAlbums().entrySet()){
				if(albums.getKey() == albumId){
					album = albums.getValue();
					break;
				}
			}
		}
		return album;
	}
	
	public boolean containsAlbum(Long albumId) {
		boolean contains = false;
		for(User user : allUsers){
			for(Entry<Long, Album> albums : user.getAlbums().entrySet()){
				if(albums.getKey() == albumId){
					contains = true;
					break;
				}
			}
		}
		return contains;
	}
	
	//COMMENTS
	
	//SEARCH
	public TreeSet<Post> getPhotosWithTag(TreeSet <String> t){
		TreeSet<Post> posts = new TreeSet<>();
		for(User user : allUsers){
			for(Entry <Long, Album> albums : user.getAlbums().entrySet()){
				for(Post post : albums.getValue().getPhotos()){
					for(String tag: t){
						String tagche = tag.toLowerCase().trim();
						if(post.getTags().contains(tagche)){
							posts.add(post);
						}
					}
				}
			}
		}
		return posts;
	}
	
	public TreeSet<Post> getPhotosWithName(TreeSet <String> t){
		TreeSet<Post> posts = new TreeSet<>();
		for(User user : allUsers){
			for(Entry <Long, Album> albums : user.getAlbums().entrySet()){
				for(Post post : albums.getValue().getPhotos()){
					for(String str: t){
						String stri =  str.toLowerCase().trim();
						if(post.getName().contains(stri)){
							posts.add(post);
						}
					}
				}
			}
		}
		return posts;
	}	
	
	//just for tests
	public Set <User> getAllUsers() {
		return  Collections.unmodifiableSortedSet(allUsers);
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

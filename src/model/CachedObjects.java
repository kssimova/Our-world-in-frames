package model;

import java.util.Map.Entry;
import java.util.TreeMap;

public class CachedObjects {
	
	private static CachedObjects instance;
	private final TreeMap<Long, User> allUsers = new TreeMap<>();
	private final TreeMap<Long, TreeMap<String, Post>> allPosts = new TreeMap<>();
	
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

	public Post getOnePost(String postId) {
		Post p = null;
		for(Entry <Long, TreeMap<String, Post>> e : allPosts.entrySet()){
			for(Entry <String, Post> e1 : e.getValue().entrySet()){
				if(e1.getKey().equals(postId)){
					p = e1.getValue();
				}
			}
		}
		return p;
	}
	
	
	public Post getOnePost(String postId, long albumId) {
		Post p = null;
		if(allPosts.containsKey(albumId)){
			p = allPosts.get(allPosts).get(postId);
		}
		return p;
	}
	
	//remove post
	public void removePost(Post post){
		allPosts.remove(post);
	}	
	
	//add user
	public void addUser(User user){
		allUsers.put(user.getUserId(), user);
	}
	
	//delete post
	public void addPost(Post post, Album album){
		if(!allPosts.keySet().contains(album.getAlbumId())){
			allPosts.put(album.getAlbumId(), new TreeMap<String, Post>());
		}
		if(!allPosts.get(album.getAlbumId()).containsKey(post.getPostId())){
			allPosts.get(album.getAlbumId()).put(post.getPostId(), post);
		}
	}
	
}

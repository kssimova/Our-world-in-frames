package model;

import java.security.AllPermission;
import java.util.TreeMap;

public class CachedObjects {
	
	private static CachedObjects instance;
	private final TreeMap<Long, User> allUsers = new TreeMap<>();
	private final TreeMap<Long, TreeMap<Album, TreeMap<Long, Post>>> allPosts = new TreeMap<>();
	
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

	public Post getOnePost(long postId) {
		Post p = null;
		for(TreeMap<Album, TreeMap<Long, Post>> e : allPosts.values())
		for( TreeMap<Long, Post> e1 : e.values()){
			p = e1.get(postId);
		}
		return p;
	}
	
	public Post getOnePost(long postId, long albumId) {
		Post p = null;
		for( TreeMap<Long, Post> e : allPosts.get(albumId).values()){
			p = e.get(postId);
		}
		return p;
	}
	
	//TODO add user to allUsers
	//TODO add photos to allPosts
	
}

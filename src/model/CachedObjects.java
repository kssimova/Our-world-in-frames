package model;

import java.security.AllPermission;
import java.util.TreeMap;

public class CachedObjects {
	
	private static CachedObjects instance;
	private final TreeMap<Long, User> allUsers = new TreeMap<>();
	private final TreeMap<Long, Post> allPosts = new TreeMap<>();
	
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
		return allPosts.get(postId);
	}
	
	//TODO add user to allUsers
	//TODO add photos to allPosts
	
}

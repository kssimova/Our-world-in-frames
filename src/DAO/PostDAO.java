package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import model.Post;
import model.User;

public class PostDAO {
	
	private static PostDAO instance;

	private PostDAO() {

	}
	public static synchronized PostDAO getInstance() {
		if (instance == null) {
			instance = new PostDAO();
		}
		return instance;
	}
	
	

 	public synchronized void editPhototags(Post p, User u, String tags){
 		PreparedStatement st = null;
 		try {
 			DBManager.getInstance().getConnection().setAutoCommit(false);
 	
 			HashMap<Long, String> tagMap = new HashMap<>();
 			for(String s: p.getTags()){
 				String sql1 = "INSERT INTO tags (type) VALUES (?)";
 				st = DBManager.getInstance().getConnection().prepareStatement(sql1);
 				st.setString(1, s);
 				tagMap.put(st.getGeneratedKeys().getLong(1), s);
 			}
 			String sql2 = "DELETE FROM tas_photos WHERE  ? ";
 			st = DBManager.getInstance().getConnection().prepareStatement(sql2);
 			st.setLong(1, p.getPostId());
 			
 			for(Entry <Long, String> e : tagMap.entrySet()){
 				String sql3 = "UPDATE tags_photo SET tag_id = ? , photo_id = ? WHERE tag_id = (SELECT tag_id FROM tags where type = ?";
 				st = DBManager.getInstance().getConnection().prepareStatement(sql3);
 				st.setLong(1, e.getKey());
 				st.setLong(2, p.getPostId());
 				st.setString(3, e.getValue());
 				st.execute();
 			}		
 		} catch (SQLException e1) {
 			try {
 				DBManager.getInstance().getConnection().rollback();
 				System.out.println("cant change tags");
 			} catch (SQLException e) {
 				System.out.println("cant roll back");
 			}finally{
 				try {
 					DBManager.getInstance().getConnection().setAutoCommit(true);
 				} catch (SQLException e) {
 					System.out.println("this wont show up... but hey it's an " + e.getMessage());
 				}
 			}
 		}		
 	}
	
	
}

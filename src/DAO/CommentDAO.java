package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.xml.bind.ValidationException;


import model.CachedObjects;
import model.Comment;
import model.Post;
import model.User;

public class CommentDAO {
	
	private static CommentDAO instance;

	private CommentDAO() {

	}
	public static synchronized CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}	
	
	//get all comments
	
	public TreeMap<Long, Comment> getAllComments(String postId) throws ValidationException{
		ResultSet result = null;
		String sql = "SELECT c.comment_id, c.post_id, c.user_id, c.parent_comment_id, c.content, c.date_created "
 				+ " FROM comments c WHERE c.post_id = ? ";
 		//initialization
  		PreparedStatement st = null;
  		TreeMap<Long, Comment> allComments = new TreeMap<>();
  		Comment comment = null;
  		User user = null;
  		Post post = null;
 		try {
 		 	st = DBManager.getInstance().getConnection().prepareStatement(sql);
 		 	st.setString(1, postId);
 		 	//get result
 			st.execute();
 			result = st.getResultSet();
 			while(result.next()){
 				try {
 					user = CachedObjects.getInstance().getOneUser(result.getLong("user_id"));
 					post = CachedObjects.getInstance().getOnePost(postId);
 					Long num = result.getLong("parent_comment_id");
 					if(result.wasNull()){
 						comment = new Comment(post, user, result.getString("content"), result.getDate("date_created").toLocalDate(), null, result.getLong("comment_id"));
 						allComments.put(result.getLong("comment_id"), comment);
 					}else{
 						Comment perant = getComment(allComments, num);
 						comment = new Comment(post, user, result.getString("content"), result.getDate("date_created").toLocalDate(), perant, result.getLong("comment_id"));
 						allComments.put(result.getLong("comment_id"), comment);
 					}
 				} catch (SQLException e) {
 					System.out.println("Error#1 in CommentDAO. Error message: " + e.getMessage());
 				}
 			}
 		} catch (SQLException e1) {
 			System.out.println("Error#2 in CommentDAO. Error message: " + e1.getMessage());
 		}
 		return allComments;
	}
	
	//create new comment
	public Comment createComment(Post post, User user, Comment parent, String str) throws ValidationException, SQLException{
 		Comment comment = null;
		String sql = "INSERT INTO comments (post_id, user_id, parent_comment_id, content, date_created) " +
 					"VALUES (?, ?, ?, ?, ?)";
 		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
 		st.setString(1, post.getPostId());
 		st.setLong(2, user.getUserId());
 		if(parent !=null){
 			st.setLong(3, parent.getCommentId());
 		}else{
 			st.setNull(3, java.sql.Types.INTEGER);
 		}
 		st.setString(4, str);
 		st.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
 		st.execute();
 		ResultSet res = st.getGeneratedKeys();
 		res.next();
 		long commentId = res.getLong(1);
 		comment = new Comment(post, user, str, LocalDate.now(), parent, commentId);
 		if(parent == null){
 			post.addComment(comment);
 		}else{
 			parent.addComment(comment);
 		}
 		return comment;
 	}
	
	//edit comment
	public void editComment(Post post, Comment comment, String str) throws ValidationException, SQLException{
		String sql = "UPDATE comments SET content = ? WHERE  comment_id = ?";
	 	PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	 	st.setString(1, str);
	 	st.setLong(2, comment.getCommentId());
	 	st.execute();
	 	ResultSet res = st.getGeneratedKeys();
	 	res.next();
	 	comment.changeContent(str);
 	}	
	
	// delete comment
	public void deleteComment(Post post, User user, Comment comment) throws ValidationException, SQLException{
		String sql = "DELETE FROM comments WHERE comment_id = ?";
		PreparedStatement st = null;
		if(comment.getComment() != null){
 			try {
				DBManager.getInstance().getConnection().setAutoCommit(false);
				st = DBManager.getInstance().getConnection().prepareStatement(sql);
			 	st.setLong(1, comment.getComment().getCommentId());
			 	st.execute();	
				st = DBManager.getInstance().getConnection().prepareStatement(sql);
			 	st.setLong(1, comment.getCommentId());
			 	st.execute();
				post.removeComment(comment);
 			} catch (SQLException e) {
 				try {
					DBManager.getInstance().getConnection().rollback();
	 				System.out.println("Error#2 in delete commetnt. Error message: " + e.getMessage());
				} catch (SQLException e1) {
					System.out.println("Error#1 in delete commetnt. Error message: " + e1.getMessage());
				}
			}finally{
 				try {
					DBManager.getInstance().getConnection().setAutoCommit(true);
				} catch (SQLException e) {
					System.out.println("Error#3 in delete commetnt. Error message: " + e.getMessage());
				}
			}	
 			return;
		}	
		st = DBManager.getInstance().getConnection().prepareStatement(sql);
	 	st.setLong(1, comment.getCommentId());
	 	st.execute();
		post.removeComment(comment);
 	}
	
	private Comment getComment(TreeMap<Long, Comment> map , Long comNum){
		Comment c = null;
		for(Entry<Long, Comment> e : map.entrySet()){
			if(e.getKey().equals(comNum)){
				c = e.getValue();
			}
		}
		return c;
	}
	
}

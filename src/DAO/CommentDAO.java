package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeMap;

import javax.xml.bind.ValidationException;


import model.CachedObjects;
import model.Comment;
import model.Post;
import model.User;

public class CommentDAO {
	
	private static CommentDAO instance;
	private static int commentCoutn = 1;

	private CommentDAO() {

	}
	public static synchronized CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}	
	
	//get all comments
	
	public TreeMap<Integer, Comment> getAllComments(String postId) throws ValidationException{
 		String sql = "SELECT c.comment_id, c.post_id, c.user_id, c.parent_comment_id, c.content, c.date_created "
 				+ "FROM comments c WHERE c.post_id " + postId ;
 		//initialization
  		Statement st = null;
  		TreeMap<Integer, Comment> allComments = new TreeMap<>();
  		Comment comment = null;
  		User user = null;
  		Post post = null;
 		try {
 			st = DBManager.getInstance().getConnection().createStatement();
 		} catch (SQLException e2) {
 			System.out.println("Error#1 in PhotoDAO. Error message: " + e2.getMessage());
 		}
 		ResultSet result = null;
 		//get result
 		try {
 			result = st.executeQuery(sql);
 		} catch (SQLException e) {
 			System.out.println("Error#2 in PhotoDAO. Eroor message: " + e.getMessage());
 		}
 		try {
 			while(result.next()){
 				try {
 					user = CachedObjects.getInstance().getOneUser(result.getLong("user_id"));
 					post = CachedObjects.getInstance().getOnePost(result.getLong("post_id"));
 					if(result.getObject("parent_comment_id").equals(null)){
 						comment = new Comment(post, user, result.getString("content"), result.getDate("date_created").toLocalDate(), null, result.getLong("comment_id"));
 	 					allComments.put(commentCoutn++, comment);
 					}
 				} catch (SQLException e) {
 					System.out.println("Error#3 in PhotoDAO. Eroor message: " + e.getMessage());
 				}
 			}
 		} catch (SQLException e1) {
 			System.out.println("Error#4 in PhotoDAO. Eroor message: " + e1.getMessage());
 		}		
 		return allComments;
	}
	
	//get one sub comment from sup comment
	
	private Comment getSubComment(Comment supCommentId) throws ValidationException{
	 	String sql = "SELECT c.comment_id, c.post_id, c.user_id, c.parent_comment_id, c.content, c.date_created "
		 			+ "FROM comments c WHERE c.comment_id = " + supCommentId.getCommentId();
	 	//initialization
	  	Statement st = null;
	  	Comment comment = null;
	  	User user = null;
	  	Post post = null;
	 	try {
	 		st = DBManager.getInstance().getConnection().createStatement();
	 	} catch (SQLException e2) {
	 		System.out.println("Error#1 in PhotoDAO. Error message: " + e2.getMessage());
	 	}
	 	ResultSet result = null;
	 	//get result
	 	try {
	 		result = st.executeQuery(sql);
	 	} catch (SQLException e) {
	 		System.out.println("Error#2 in PhotoDAO. Eroor message: " + e.getMessage());
	 	}
	 	try {
	 		while(result.next()){
	 			try {
	 				user = CachedObjects.getInstance().getOneUser(result.getLong("user_id"));
	 				post = CachedObjects.getInstance().getOnePost(result.getLong("post_id"));
	 				comment = new Comment(post, user, result.getString("content"), result.getDate("date_created").toLocalDate(), supCommentId , result.getLong("comment_id"));
	 			} catch (SQLException e) {
	 				System.out.println("Error#3 in PhotoDAO. Eroor message: " + e.getMessage());
	 			}
	 		}
	 	} catch (SQLException e1) {
	 		System.out.println("Error#4 in PhotoDAO. Eroor message: " + e1.getMessage());
	 	}		
	 	return comment;
	}
	
	//create comment
	public void makeComment(Post post, User user, Comment parent, String str) throws ValidationException, SQLException{
		String sql = "INSERT INTO comments (post_id, user_id, parent_comment_id, content, date_created) " +
 					"VALUES (?, ?, ?, ?, ?)";
 		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
 		st.setLong(1, post.getPostId());
 		st.setLong(2, user.getUserId());
 		if(!parent.equals(null)){
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
 		Comment comment = new Comment(post, user, str, LocalDate.now(), parent, commentId);
 		if(!parent.equals(null)){
 			post.addComment(comment);
 		}else{
 			post.addSubComment(comment);
 		}
 	}
	
	//edit comment
	public void editComment(Post post, Comment comment, String str) throws ValidationException, SQLException{
		String sql = "UPDATE comments SET content = ? WHERE  comment_id = ?";
	 	PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
	 	st.setString(1, str);
	 	st.setLong(2, post.getPostId());
	 	st.execute();
	 	ResultSet res = st.getGeneratedKeys();
	 	res.next();
	 	comment.changeContent(str);
 	}	
	
	// delete comment
	public void deleteComment(Post post, User user, Comment comment) throws ValidationException, SQLException{
		String sql = "DELETE FROM comments WHERE comment_id ";
		if(!comment.getComment().equals(null)){
 			try {
				DBManager.getInstance().getConnection().setAutoCommit(false);
				String sql1 = sql + comment.getComment().getCommentId();
				Statement st = DBManager.getInstance().getConnection().createStatement();
				st.executeQuery(sql1);
				post.getComments().remove(comment.getComment());
				sql += comment.getCommentId();
				st = DBManager.getInstance().getConnection().createStatement();
				st.executeQuery(sql);
				post.getComments().remove(comment);
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
		sql += comment.getCommentId();
		Statement st = DBManager.getInstance().getConnection().createStatement();
		st.executeQuery(sql);
		post.getComments().remove(comment);
 	}
}

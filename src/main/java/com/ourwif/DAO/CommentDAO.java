package com.ourwif.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeMap;

import javax.sql.DataSource;
import javax.xml.bind.ValidationException;

import com.mysql.jdbc.Connection;
import com.ourwif.model.CachedObjects;
import com.ourwif.model.Comment;
import com.ourwif.model.Post;
import com.ourwif.model.User;

public class CommentDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
	
	//get all comments	
	public synchronized TreeMap<Long, Comment> getAllComments(Post post) throws ValidationException, SQLException{
		ResultSet result = null;
		String sql = "SELECT c.comment_id, c.post_id, c.user_id, c.content, c.date_created "
 				+ " FROM comments c WHERE c.post_id = ? ";
 		//initialization
  		PreparedStatement st = null;
  		TreeMap<Long, Comment> allComments = new TreeMap<>();
  		Comment comment = null;
  		User user = null;
		Connection conn = null;
		try{
			conn = (Connection) dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, post.getPostId());
			//get result
			st.execute();
			result = st.getResultSet();
			while(result.next()){
					Long userId = result.getLong("user_id");
					user = CachedObjects.getInstance().getOneUser(userId);
					comment = new Comment(post, user, result.getString("content"), result.getDate("date_created").toLocalDate(), result.getLong("comment_id"));
					allComments.put(result.getLong("comment_id"), comment);
 				}
		}finally {
			conn.close();
		}
 		return allComments;
	}
	
	//create new comment
	public Comment createComment(Post post, User user, String str) throws ValidationException, SQLException{
 		Comment comment = null;
		String sql = "INSERT INTO comments (post_id, user_id, content, date_created) " +
 					"VALUES (?, ?, ?, ?)";
		Connection conn = null;
 		try {
 			conn = (Connection) dataSource.getConnection();
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, post.getPostId());
			st.setLong(2, user.getUserId());
			st.setString(3, str);
			st.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			st.execute();
			ResultSet res = st.getGeneratedKeys();
			res.next();
			long commentId = res.getLong(1);
			comment = new Comment(post, user, str, LocalDate.now(), commentId);
			post.addComment(comment);
 		}finally{
 			conn.close();
 		}
 		return comment;
 	}
	
	//edit comment
	public void editComment(Post post, Comment comment, String str) throws ValidationException, SQLException{
		comment.changeContent(str);
		String sql = "UPDATE comments SET content = ? WHERE  comment_id = ?";
		Connection conn = null;
 		try {
 			conn = (Connection) dataSource.getConnection();
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, str);
			st.setLong(2, comment.getCommentId());
			st.execute();
 		}finally{
 			conn.close();
 		}
 	}	
	
	// delete comment
	public void deleteComment(Post post, User user, Comment comment) throws ValidationException, SQLException{
		String sql = "DELETE FROM comments WHERE comment_id = ?";
		PreparedStatement st = null;
		Connection conn = null;
 		try {
 			conn = (Connection) dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setLong(1, comment.getCommentId());
			st.execute();
			post.removeComment(comment);
 		}finally{
 			conn.close();
 		}
 	}	
}
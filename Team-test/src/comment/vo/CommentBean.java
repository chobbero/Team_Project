package comment.vo;

import java.security.Timestamp;

public class CommentBean {
	private int comment_num;
	private String user_id;
	private int board_num;
	private String comment_content;
	private Timestamp comment_date;
	private String comment_like;
	
	
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Timestamp getComment_date() {
		return comment_date;
	}
	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_like() {
		return comment_like;
	}
	public void setComment_like(String comment_like) {
		this.comment_like = comment_like;
	}

	
}

package comment.service;


import board.vo.BoardBean;
import comment.dao.CommentDAO;
import comment.vo.MemberBean;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;
import java.sql.Connection;

public class CommentWriteFormService {

	public BoardBean boardInfo(int board_num) {
		
        Connection con = getConnection();
        CommentDAO commentDAO = CommentDAO.getInstance();
        commentDAO.setConnection(con);
		
		BoardBean boardBean = commentDAO.boardInfo(board_num);
		
		close(con);
		
		return boardBean;
	}

    public MemberBean userInfo(String user_id) {
        
        Connection con = getConnection();
        CommentDAO commentDAO = CommentDAO.getInstance();
        commentDAO.setConnection(con);
        
        MemberBean memberBean = commentDAO.userInfo(user_id);
        
        close(con);
        
        return memberBean;
    }
}

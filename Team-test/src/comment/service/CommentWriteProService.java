package comment.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import comment.dao.CommentDAO;
import comment.vo.CommentBean;

public class CommentWriteProService {
	public boolean insertComment (CommentBean commentBean) {
		
        Connection con = getConnection();
        CommentDAO commentDAO = CommentDAO.getInstance();
        commentDAO.setConnection(con);
        
        
        boolean isCheck = false;
        
        
        int insertCount = commentDAO.insertComment(commentBean);
        
        if(insertCount > 0 ) {
        	isCheck =true;
        }else {
        	isCheck = false;
        }
        
        close(con);
        
        return isCheck;
	}
}

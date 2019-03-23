package comment.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import comment.dao.CommentDAO;
import comment.vo.CommentBean;

public class CommentWriteProService {
    public boolean insertComment(CommentBean commentBean) {
        boolean isCheck = false;

        Connection con = getConnection();
        CommentDAO commentDAO = CommentDAO.getInstance();
        commentDAO.setConnection(con);

        int insertCount = commentDAO.insertComment(commentBean);

        if (insertCount > 0) {
            commit(con);
            isCheck = true;
        } else {
            rollback(con);
        }

        close(con);

        return isCheck;
    }
}

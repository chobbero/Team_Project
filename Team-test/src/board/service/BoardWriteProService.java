package board.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;
import board.vo.FileBean;

import java.sql.Connection;
import java.util.List;

import board.dao.BoardDAO;
import board.vo.BoardBean;

public class BoardWriteProService {
    
    public boolean registArticle(BoardBean boardBean, List<FileBean> fileBeanList) throws Exception { 
        
        boolean isWriteSuccess = false; 
        
        Connection con = getConnection(); 
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        
        
        int insertCount = boardDAO.insertArticle(boardBean, fileBeanList);
        
        if(insertCount > 0) {
            commit(con);
            isWriteSuccess = true;
        } else {
            rollback(con);
        }
        
        close(con);
        
        return isWriteSuccess;
    }
    
}
























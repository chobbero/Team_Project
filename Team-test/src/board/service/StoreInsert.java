package board.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import board.dao.BoardDAO;
import mypage.vo.StoreBean;

public class StoreInsert {

    public int store_maxNum() throws Exception {

        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);

        int maxNum = boardDAO.store_maxNum();

        close(con);

        return maxNum;

    }

    public void store_insert(StoreBean storeBean) throws Exception {
        int insertCount = 0;
        
        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        
        insertCount = boardDAO.store_insert(storeBean);
        
        if (insertCount > 0) {
            commit(con);
        } else {
            rollback(con);
        }
        close(con);
    }
}

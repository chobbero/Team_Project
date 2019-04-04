package mypage.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import board.dao.BoardDAO;
import board.vo.ListBean;
import mypage.dao.MyPageDAO;
import mypage.vo.StoreBean;

public class BusinessEventWriteFormService {
    
	public StoreBean getStoreInfo(String user_id) throws Exception {
		
	    Connection con = getConnection();
	    
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		StoreBean storeBean = boardDAO.getStoreInfo(user_id);
		
		close(con);
		
		return storeBean;
	}
}

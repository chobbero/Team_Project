package board.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import board.dao.BoardDAO;

public class BoardDeleteService {
	public boolean deleteBoard(int board_num) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int result = 0;
		boolean isDeleteSuccess = false;

		result = boardDAO.deleteBoard(board_num);
		
		if(result > 0) {
			isDeleteSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isDeleteSuccess;
	}
	
	public boolean userCheck(String id, String pass) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		boolean isUserCheck = boardDAO.userCheck(id, pass);
		
		close(con);
		
		return isUserCheck;
	}
	
	
	
}

package board.service;

import java.sql.Connection;

import board.dao.BoardDAO;
import board.vo.BoardBean;

import static common.db.JdbcUtil.*;

public class BoardUpdateProService {
	public boolean updateBoard(BoardBean boardBean) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		boolean isUpdateSuccess = false;
		int result = 0;
		
		result = boardDAO.updateBoard(boardBean);
		System.out.println(result);
		
		if(result > 0) {
			isUpdateSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		System.out.println("Service에서 :"+isUpdateSuccess);
		return isUpdateSuccess;
		
	}
}

package board.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import board.dao.BoardDAO;
import board.vo.BoardBean;

public class BoardUpdateFormService {
	public BoardBean getBoardDetail(int board_num) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		BoardBean boardBean = new BoardBean();
		boardBean = boardDAO.selectBoardArticle(board_num);
		
		close(con);
		
		return boardBean;
	}
}

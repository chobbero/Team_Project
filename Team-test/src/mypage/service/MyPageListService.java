package mypage.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import board.vo.ListBean;
import mypage.dao.MyPageDAO;

public class MyPageListService {
	public ArrayList<ListBean> getMyPageBoardList(int page, int limit, String user_id) throws Exception {
		Connection con = getConnection();
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		myPageDAO.setConnection(con);
		
		ArrayList<ListBean> list = myPageDAO.getMyPageBoardList(page, limit, user_id);
		
		close(con);
		
		return list;
	}
	
	public int getMyPageBoardCount(String user_id) {
		int listCount = 0; // 총 게시물 갯수 저장할 변수

		Connection con = getConnection();
		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		myPageDAO.setConnection(con);
		
		listCount = myPageDAO.getMyPageBoardCount(user_id);
		
		close(con);
		
		return listCount;
	}
}

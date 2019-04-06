package board.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import board.dao.BoardDAO;
import board.vo.BoardBean;
import board.vo.ListBean;
import board.vo.StoreBean;
import mypage.vo.BusinessBoardBean;

public class BoardListService {

	public int getListCount() throws Exception {
		int listCount = 0; // 총 게시물 갯수 저장할 변수

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);

		listCount = boardDAO.getBoardListCount();

		close(con);

		return listCount;
	}

	public ArrayList<ListBean> getBoardList(int page, int limit) throws Exception {
		ArrayList<ListBean> list = null; // 게시물 목록을 저장할 변수

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);

		list = boardDAO.getList(page, limit);

		close(con);

		return list;
	}
	
	public ArrayList<ListBean> getBoardListRank(int page, int limit) throws Exception {
        ArrayList<ListBean> list = null; // 게시물 목록을 저장할 변수

        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);

        list = boardDAO.getListRank(page, limit);

        close(con);

        return list;
    }
	// 랭킹 게시판
	public ArrayList<StoreBean> getRankList() throws Exception {
		ArrayList<StoreBean> rankList = null;

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);

		rankList = boardDAO.getRankList();

		close(con);

		return rankList;
	}
	
	// 제휴 게시판
	public int getBusinessBoardCount() throws Exception {
        int count = 0;

        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);

        count = boardDAO.getBusinessBoardListCount();

        close(con);

        return count;
    }
	
	// 제휴 게시판
	public List<mypage.vo.StoreBean> getStoreList(int page, int limit) throws Exception {
	    List<mypage.vo.StoreBean> storeList = null;

        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);

        storeList = boardDAO.getStoreList(page,limit);

        close(con);

        return storeList;
    }
	
	// 제휴 게시판
    public List<BusinessBoardBean> getBusinessBoardList(int page, int limit) throws Exception {
        List<BusinessBoardBean> boardList = null;

        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);

        boardList = boardDAO.getBoardList(page, limit);

        close(con);

        return boardList;
    }
	
}

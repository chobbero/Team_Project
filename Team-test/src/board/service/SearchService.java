package board.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import board.dao.BoardDAO;
import board.vo.BoardBean;
import board.vo.FileBean;
import board.vo.StoreBean;

public class SearchService {

	// 검색 게시물 수 조회
	public int getSearchCount(String search, String category) throws Exception {

		int SearchlistCount = 0;

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);

		SearchlistCount = boardDAO.getSearchCount(search, category);

		close(con);

		return SearchlistCount;

	}

	// 검색 게시물 리스트 조회 (게시물)
	public ArrayList<BoardBean> getSearchList(String search, String category) {

		ArrayList<BoardBean> SearchList = null;

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);

		SearchList = boardDAO.getSearchList(search, category);

		close(con);

		return SearchList;

	}

	// 검색 게시물 리스트 조회 (매장)
	public ArrayList<StoreBean> getSearchList2(String search) {

		ArrayList<StoreBean> SearchList = null;

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);

		SearchList = boardDAO.getSearchList2(search);

		close(con);

		return SearchList;
	}

	// 이미지 갯수 조회
	public int getImgFileCount(int board_num) {

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int imgFileCount = 0;

		imgFileCount = boardDAO.getImgFileCount(board_num);

		close(con);

		return imgFileCount;
	}

	// 검색 게시물 이미지 파일명 가져오기 (한장)
	public FileBean getImgFileName(int board_num) {

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		FileBean fileBean = null;

		fileBean = boardDAO.getImgFileName(board_num);

		close(con);

		return fileBean;

	}

	// 메인에서 이동 맛집카테고리 클릭
	public int getSearchCount(String storeCategory) {
		int SearchlistCount = 0;

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);

		SearchlistCount = boardDAO.getSearchCount(storeCategory);

		close(con);

		return SearchlistCount;
	}

	// 메인에서 이동 맛집카테고리 클릭 게시글 리스트
	public ArrayList<BoardBean> getSearchList(String storeCategory) {
		ArrayList<BoardBean> SearchList = null;

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);

		SearchList = boardDAO.getSearchList(storeCategory);

		close(con);

		return SearchList;
	}

}

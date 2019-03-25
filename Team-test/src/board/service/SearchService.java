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

	// 이미지 리스트 조회
	public ArrayList<FileBean> getImgFileList(int board_num) {
		
		Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        ArrayList<FileBean> imgFileList = null;
		
		imgFileList = boardDAO.getImgFileList2(board_num);

        close(con);
		
		return imgFileList;
		
	}
	
}

package board.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.dao.BoardDAO;
import board.vo.BoardBean;
import board.vo.CommentBean;
import board.vo.StoreBean;

public class BoardDetailService {

    // 게시물 내용 가져오기 및 조회수 증가 메서드
    public BoardBean getBoardArticle(int board_num) throws Exception {
    	
    	System.out.println("BoardDetailService");
    	
        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        BoardBean boardBean = null;

        // 조회 수 증가
        int updateCount = boardDAO.updateReadcount(board_num);
        // 글 내용 가져오기
        boardBean = boardDAO.selectBoardArticle(board_num);

        if(updateCount > 0) {
            commit(con);
        } else {
            rollback(con);
        }
        
        close(con);
        
        return boardBean;
    }

    // 매장 정보 조회 메서드 (게시글의 store_num 참조)
	public StoreBean getStoreInfo(int store_num) {
		
		Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        StoreBean storeBean = null;
        
        storeBean = boardDAO.getStoreInfo(store_num);
		
		return storeBean;
	}

	public int getCommentCount(int board_num) {
		
		int commentCount = 0;
		Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
		
        commentCount = boardDAO.getCommentCount(board_num);
        
		return commentCount;
		
	}
	
	public ArrayList<CommentBean> getBoardComment(int board_num) {
		
		Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        ArrayList<CommentBean> commentList = null;
        
        commentList = boardDAO.getBoardComment(board_num);
		
		return commentList;
		
	}

	public ArrayList<String> getImgFileList(int board_num) {
		
		Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        ArrayList<String> imgFileList = null;
		
		imgFileList = boardDAO.getImgFileList(board_num);
		
		return imgFileList;
	}

	public int getBoardCount(int store_num) {
		
		Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        int boardCount = 0;
        
        boardCount = boardDAO.getBoardCount(store_num);
        
		return boardCount;
	}

}

package board.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import board.dao.BoardDAO;
import board.vo.BoardBean;

// 글 목록 보기 요청을 처리하는 비즈니스 로직 구현 서비스 클래스
public class BoardListService {
    
    // 총 게시물 갯수 조회 후 리턴할 메서드
    public int getListCount() throws Exception {
        int listCount = 0; // 총 게시물 갯수 저장할 변수
        
        // Connection 객체 가져오기(JdbcUtil 클래스의 static 메서드 getConnection() 메서드 사용)
        Connection con = getConnection();
        // BoardDAO 객체 가져오기(BoardDAO 클래스의 static 메서드 getInstance() 메서드 사용)
        BoardDAO boardDAO = BoardDAO.getInstance();
        // BoardDAO 객체에 Connection 객체 전달(BoardDAO 클래스의 setConnection() 메서드 사용)
        boardDAO.setConnection(con);
        
        // listCount 변수에 BoardDAO 객체의 selectListCount() 메서드 실행 결과 리턴받기
        listCount = boardDAO.selectListCount();
        
        // Connection 객체 자원 반환
        close(con);
        
        return listCount;
    }
    
    // 게시물 목록 조회 후 리턴할 메서드
    public ArrayList<BoardBean> getArticleList(int page, int limit) throws Exception {
        ArrayList<BoardBean> articleList = null; // 게시물 목록을 저장할 변수
        
        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        
        // BoardDAO 객체의 selectArticleList() 메서드를 호출하여 게시물 목록 가져와서 articleList 변수에 저장
        articleList = boardDAO.selectArticleList(page, limit); // 페이지번호, 페이지 당 게시물 수 전달
        
        close(con);
        
        return articleList;
    }
    
    
}



















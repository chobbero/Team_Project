package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import static common.db.JdbcUtil.*;
import board.vo.BoardBean;
import board.vo.FileBean;

public class BoardDAO {
    Connection con;
    DataSource ds;
    
   private static BoardDAO instance;
    
    private BoardDAO() {} 

    public static BoardDAO getInstance() {
        if(instance == null) {
            instance = new BoardDAO();
        }
        return instance; // 생성된 인스턴스 리턴
    }
    
    public void setConnection(Connection con) {
        this.con = con;
    }

    public int insertArticle(BoardBean article, List<FileBean> fileBeanList) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        int num = 0;
        int insertCount = 0; 
        
        String sql = "";
        
        try {
           
            sql = "SELECT MAX(board_num) FROM board";
            
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
                num = rs.getInt(1) + 1;
            } else {
                num = 1;
            }
            
            sql = "INSERT INTO board VALUES(?,?,?,?,?,?,?,now(),?)";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num); // 글번호(위에서 계산됨)
            pstmt.setString(2, article.getUser_id()); 
            pstmt.setInt(3, article.getStore_num()); 
            pstmt.setString(4, article.getBoard_subject()); // 글제목
            pstmt.setString(5, article.getBoard_content()); // 글내용
            pstmt.setDouble(6, article.getBoard_rating());
            pstmt.setInt(7, 0); 
            pstmt.setInt(8, 0); 
           
            insertCount = pstmt.executeUpdate(); 
            
            for(int i = 0; i < fileBeanList.size(); i++) {

                sql = "INSERT INTO board_file VALUES(?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, num);
                pstmt.setString(2, fileBeanList.get(i).getImage());
                pstmt.executeUpdate();
                
            }
            
        } catch (SQLException e) {
            System.out.println("insertArticle() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        
        return insertCount; // BoardWriteProService 객체로 리턴
        
    } // insertArticle() 끝
    
    // 총 게시물 수 계산하여 리턴
    public int selectListCount() {
        int listCount = 0; // 총 게시물 수 저장 변수
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // board 테이블의 모든 레코드 갯수 조회
            String sql = "SELECT COUNT(*) FROM board";
            
            // SQL 구문 전달 및 실행
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            // 조회 결과가 있을 경우 listCount 에 조회된 결과값 저장
            if(rs.next()) {
                listCount = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println("selectListCount() 에러 : " + e.getMessage());
        } finally {
            // pstmt, rs 자원 반환
            close(pstmt);
            close(rs);
        }
        
        // listCount 리턴
        return listCount;
    } // selectListCount() 끝
    
    // 게시물 목록을 조회하여 리턴
//    public ArrayList<BoardBean> selectArticleList(int page, int limit) {
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        
//        BoardBean boardBean = null;
//        ArrayList<BoardBean> articleList = new ArrayList<BoardBean>(); // 게시물 목록을 저장할 객체
//        
//        int startRow = (page - 1) * 10; // 읽어올 첫번째 행 번호 계산
//        
//        try {
//            // 모든 게시물 조회(단, board_re_ref 기준 내림차순 및 board_re_seq 기준 오름차순 정렬, startRow 부터 limit 갯수만큼 조회)
//            String sql = "SELECT * FROM board ORDER BY board_re_ref DESC,board_re_seq ASC LIMIT ?,?";
//            
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, startRow);
//            pstmt.setInt(2, limit);
//            rs = pstmt.executeQuery();
//            
//            // while문을 사용하여 조회 결과에 대한 처리 반복
//            while(rs.next()) {
//                // 게시물을 1개 저장할 BoardBean 객체 생성
//                boardBean = new BoardBean();
//                
//                // ResultSet 객체로부터 데이터를 가져와서 BoardBean 객체에 저장
//                boardBean.setBoard_num(rs.getInt("board_num"));
//                boardBean.setBoard_name(rs.getString("board_name"));
//                boardBean.setBoard_subject(rs.getString("board_subject"));
//                boardBean.setBoard_content(rs.getString("board_content"));
//                boardBean.setBoard_file(rs.getString("board_file"));
//                boardBean.setBoard_re_ref(rs.getInt("board_re_ref"));
//                boardBean.setBoard_re_lev(rs.getInt("board_re_lev"));
//                boardBean.setBoard_re_seq(rs.getInt("board_re_seq"));
//                boardBean.setBoard_readcount(rs.getInt("board_readcount"));
//                boardBean.setBoard_date(rs.getDate("board_date"));
//                
//                // BoardBean 객체를 ArrayList 객체인 articleList 변수에 저장
//                articleList.add(boardBean);
//            }
//            
//        } catch (SQLException e) {
//            System.out.println("selectArticleList() 에러 : " + e.getMessage());
//        } finally {
//            close(pstmt);
//            close(rs);
//        }
//        
//        return articleList;
//    } // selectArticleList() 끝
//    
//    // 게시물 상세 내용을 조회하여 리턴
//    public BoardBean selectArticle(int board_num) {
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        
//        BoardBean boardBean = null;
//        
//        try {
//            // board 테이블의 board_num 값으로 전달된 레코드 조회
//            String sql = "SELECT * FROM board WHERE board_num=?";
//            
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, board_num);
//            rs = pstmt.executeQuery();
//            
//            // 조회 결과가 존재할 경우, 패스워드를 제외한 모든 컬럼 데이터를 가져와서 BoardBean 객체에 저장(BoardBean 객체 생성 필요)
//            if(rs.next()) {
//                boardBean = new BoardBean();
//                
//                boardBean.setBoard_num(rs.getInt("board_num"));
//                boardBean.setBoard_name(rs.getString("board_name"));
//                boardBean.setBoard_subject(rs.getString("board_subject"));
//                boardBean.setBoard_content(rs.getString("board_content"));
//                boardBean.setBoard_file(rs.getString("board_file"));
//                boardBean.setBoard_re_ref(rs.getInt("board_re_ref"));
//                boardBean.setBoard_re_lev(rs.getInt("board_re_lev"));
//                boardBean.setBoard_re_seq(rs.getInt("board_re_seq"));
//                boardBean.setBoard_readcount(rs.getInt("board_readcount"));
//                boardBean.setBoard_date(rs.getDate("board_date"));
//            }
//            
//        } catch (SQLException e) {
//            System.out.println("selectArticle() 에러 : " + e.getMessage());
//        } finally {
//            close(pstmt);
//            close(rs);
//        }
//        
//        return boardBean;
//    }
//
//    // 게시물 조회수 업데이트(증가)
//    public int updateReadcount(int board_num) {
//        PreparedStatement pstmt = null;
//        
//        int updateCount = 0; // 업데이트 된 조회수 저장 변수
//        
//        // board 테이블 board_num 에 해당하는 게시물의 readcount 컬럼값을 1 증가
//        String sql = "UPDATE board SET board_readcount=board_readcount+1 WHERE board_num=?";
//        
//        try {
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, board_num);
//            updateCount = pstmt.executeUpdate(); // 조회수 증가 결과 정상적으로 증가된 조회수가 리턴되거나, 대상 게시물이 없을 경우 0 리턴
//        } catch (SQLException e) {
//            System.out.println("updateReadcount() 에러 : " + e.getMessage());
//        } finally {
//            close(pstmt);
//        }
//        
//        return updateCount;
//    }
//    
    
}

















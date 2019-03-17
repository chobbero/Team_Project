package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import static common.db.JdbcUtil.*;
import board.vo.BoardBean;

public class BoardDAO {
    Connection con;
    DataSource ds;
    
    // 싱글톤 디자인 패턴을 활용하여 미리 생성된 인스턴스 하나만 사용하여 모든 클래스에서 접근하도록 인스턴스 생성 강제
   private static BoardDAO instance;
    
    private BoardDAO() {} // 외부에서 인스턴스 생성이 불가능하도록 private 접근지정자 지정

    // 외부에서 생성된 인스턴스를 리턴받을 수 있도록 getConnection() 메서드를 정의하여 생성된 인스턴스 리턴 => static 메서드로 정의
    public static BoardDAO getInstance() {
        // BoardDAO 인스턴스가 존재하지 않을 경우 인스턴스 생성
        if(instance == null) {
            instance = new BoardDAO();
        }
        
        return instance; // 생성된 인스턴스 리턴
    }
    
    // 서비스클래스로부터 작업 수행에 필요한 Connection 객체 전달받는 setConnection() 메서드 정의
    public void setConnection(Connection con) {
        this.con = con;
    }

    // BoardBean 객체를 전달받아 글쓰기 수행
    public int insertArticle(BoardBean article) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        int num = 0; // 글번호
        int insertCount = 0; // 작업 성공한 레코드 수(PreparedStatement 객체의 execute() 메서드 수행 결과)
        
        String sql = "";
        
        try {
            // 현재 등록된 게시물의 글번호 최대값 조회하기(max() 함수 사용)
            sql = "SELECT MAX(board_num) FROM board";
            
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            // 글번호 조회 결과가 있을 경우 글번호(num)를 글번호 최대값 + 1번으로 설정하고, 조회 결과가 없을 경우 1번으로 설정
            if(rs.next()) {
                num = rs.getInt(1) + 1;
            } else {
                num = 1;
            }
            
            // board 테이블의 모든 컬럼 데이터를 추가할 INSERT 구문 작성
            sql = "INSERT INTO board VALUES(?,?,?,?,?,?,?,?,?,?,now())"; // 현재 시각 정보를 기록하기 위해 now() 함수 호출
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num); // 글번호(위에서 계산됨)
            pstmt.setString(2, article.getBoard_name()); // 작성자
            pstmt.setString(3, article.getBoard_pass()); // 패스워드
            pstmt.setString(4, article.getBoard_subject()); // 글제목
            pstmt.setString(5, article.getBoard_content()); // 글내용
            pstmt.setString(6, article.getBoard_file()); // 업로드 파일
            pstmt.setInt(7, num); // 참조할 글번호(본 글은 자기 자신의 번호를 그대로 사용)
            pstmt.setInt(8, 0); // 들여쓰기 레벨
            pstmt.setInt(9, 0); // 글 순서
            pstmt.setInt(10, 0); // 조회수
            
            insertCount = pstmt.executeUpdate(); // INSERT 구문 실행 및 결과값 저장(성공 1, 실패 0)
            
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("insertArticle() 에러 : " + e.getMessage());
        } finally {
            // 자원 반환(pstmt, rs)
            close(pstmt); // static import 문이 있을 경우 close() 메서드만 호출 가능
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
    public ArrayList<BoardBean> selectArticleList(int page, int limit) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        BoardBean boardBean = null;
        ArrayList<BoardBean> articleList = new ArrayList<BoardBean>(); // 게시물 목록을 저장할 객체
        
        int startRow = (page - 1) * 10; // 읽어올 첫번째 행 번호 계산
        
        try {
            // 모든 게시물 조회(단, board_re_ref 기준 내림차순 및 board_re_seq 기준 오름차순 정렬, startRow 부터 limit 갯수만큼 조회)
            String sql = "SELECT * FROM board ORDER BY board_re_ref DESC,board_re_seq ASC LIMIT ?,?";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, limit);
            rs = pstmt.executeQuery();
            
            // while문을 사용하여 조회 결과에 대한 처리 반복
            while(rs.next()) {
                // 게시물을 1개 저장할 BoardBean 객체 생성
                boardBean = new BoardBean();
                
                // ResultSet 객체로부터 데이터를 가져와서 BoardBean 객체에 저장
                boardBean.setBoard_num(rs.getInt("board_num"));
                boardBean.setBoard_name(rs.getString("board_name"));
                boardBean.setBoard_subject(rs.getString("board_subject"));
                boardBean.setBoard_content(rs.getString("board_content"));
                boardBean.setBoard_file(rs.getString("board_file"));
                boardBean.setBoard_re_ref(rs.getInt("board_re_ref"));
                boardBean.setBoard_re_lev(rs.getInt("board_re_lev"));
                boardBean.setBoard_re_seq(rs.getInt("board_re_seq"));
                boardBean.setBoard_readcount(rs.getInt("board_readcount"));
                boardBean.setBoard_date(rs.getDate("board_date"));
                
                // BoardBean 객체를 ArrayList 객체인 articleList 변수에 저장
                articleList.add(boardBean);
            }
            
        } catch (SQLException e) {
            System.out.println("selectArticleList() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        
        return articleList;
    } // selectArticleList() 끝
    
    // 게시물 상세 내용을 조회하여 리턴
    public BoardBean selectArticle(int board_num) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        BoardBean boardBean = null;
        
        try {
            // board 테이블의 board_num 값으로 전달된 레코드 조회
            String sql = "SELECT * FROM board WHERE board_num=?";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();
            
            // 조회 결과가 존재할 경우, 패스워드를 제외한 모든 컬럼 데이터를 가져와서 BoardBean 객체에 저장(BoardBean 객체 생성 필요)
            if(rs.next()) {
                boardBean = new BoardBean();
                
                boardBean.setBoard_num(rs.getInt("board_num"));
                boardBean.setBoard_name(rs.getString("board_name"));
                boardBean.setBoard_subject(rs.getString("board_subject"));
                boardBean.setBoard_content(rs.getString("board_content"));
                boardBean.setBoard_file(rs.getString("board_file"));
                boardBean.setBoard_re_ref(rs.getInt("board_re_ref"));
                boardBean.setBoard_re_lev(rs.getInt("board_re_lev"));
                boardBean.setBoard_re_seq(rs.getInt("board_re_seq"));
                boardBean.setBoard_readcount(rs.getInt("board_readcount"));
                boardBean.setBoard_date(rs.getDate("board_date"));
            }
            
        } catch (SQLException e) {
            System.out.println("selectArticle() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        
        return boardBean;
    }

    // 게시물 조회수 업데이트(증가)
    public int updateReadcount(int board_num) {
        PreparedStatement pstmt = null;
        
        int updateCount = 0; // 업데이트 된 조회수 저장 변수
        
        // board 테이블 board_num 에 해당하는 게시물의 readcount 컬럼값을 1 증가
        String sql = "UPDATE board SET board_readcount=board_readcount+1 WHERE board_num=?";
        
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            updateCount = pstmt.executeUpdate(); // 조회수 증가 결과 정상적으로 증가된 조회수가 리턴되거나, 대상 게시물이 없을 경우 0 리턴
        } catch (SQLException e) {
            System.out.println("updateReadcount() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
        }
        
        return updateCount;
    }
    
    
}

















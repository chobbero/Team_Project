package board.service;

//import static db.JdbcUtil.getConnection; // JdbcUtil 클래스 내의 static 메서드 중 getConnection() 메서드를 import
// 해당 클래스 내의 모든 static 메서드 import
import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import board.dao.BoardDAO;
import board.vo.BoardBean;

// Service 클래스 = 각 클라이언트의 요청을 처리하는 비지니스 로직 구현
// BoardWriteProAction 클래스로부터 전달받은 데이터를 사용하여 글 등록 처리할 서비스 클래스
public class BoardWriteProService {
    
    // 글 등록을 처리할 registArticle() 메서드 정의 => 글쓰기 요청 데이터가 포함된 BoardBean 객체 전달받음
    public boolean registArticle(BoardBean boardBean) throws Exception { 
        boolean isWriteSuccess = false; // 글쓰기 요청 결과를 저장할 변수
        
//        Connection con = JdbcUtil.getConnection(); // 커넥션풀로부터 Connection 객체 가져오기
        Connection con = getConnection(); // static import 사용 시 JdbcUtil 클래스를 지정하지 않아도 JdbcUtil 클래스 내의 static 메서드 호출 가능
        
        // BoardDAO 인스턴스 가져오기
        BoardDAO boardDAO = BoardDAO.getInstance();
        // BoardDAO 인스턴스에 Connection 객체 전달
        boardDAO.setConnection(con);
        
        // DAO 객체의 insertArticle() 메서드 수행 후 결과값 리턴받기
        int insertCount = boardDAO.insertArticle(boardBean);
        
        // INSERT 작업이 성공할 경우(insertCount 값이 0보다 클 경우) commit(), 아니면 rollback() 수행
        // => commit() 메서드 실행 후 isWriteSuccess 변수 값을 true 로 변경한 뒤 isWriteSuccess 값 리턴
        if(insertCount > 0) {
            commit(con);
            isWriteSuccess = true;
        } else {
            rollback(con);
        }
        
        // Connection 객체 자원 반환(작업 성공 여부와 관계없이 수행해야하므로 if() 문 바깥에 기술
        close(con);
        
        return isWriteSuccess; // BoardWriteProAction 객체로 리턴
    }
    
}
























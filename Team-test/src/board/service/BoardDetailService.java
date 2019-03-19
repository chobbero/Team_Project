package board.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import board.dao.BoardDAO;
import board.vo.BoardBean;

public class BoardDetailService {

    // 게시물 내용을 조회하여 BoardBean 타입으로 리턴하는 getAtricle() 메서드 정의 => 글 번호를 전달받음
    public BoardBean getArticle(int board_num) throws Exception {
        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        BoardBean bb = null;

        // BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 정보를 BoardBean 타입으로 리턴받음
//        BoardBean article = boardDAO.selectArticle(board_num);

        // 조회 수 증가
//        int updateCount = boardDAO.updateReadcount(board_num);

        // 조회 수 증가 작업 후 전달받은 조회수가 0보다 클 경우에만 commit 을 수행하고, 아니면 rollback 수행
//        if(updateCount > 0) {
//            commit(con);
//        } else {
//            rollback(con);
//        }
//        
//        close(con);
//        
//        return article;
//    }
        return bb;
    }
}

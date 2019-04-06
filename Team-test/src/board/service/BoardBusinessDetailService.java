package board.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.dao.BoardDAO;
import board.vo.StoreBean;
import mypage.vo.BusinessBoardBean;
import mypage.vo.BusinessFile;

public class BoardBusinessDetailService {

    public BusinessBoardBean getBoardArticle(int store_num) throws Exception {

        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);

        BusinessBoardBean businessBoardBean = null;

        // 조회 수 증가
        int updateCount = boardDAO.updateReadcountBusiness(store_num);
        // 글 내용 가져오기
        businessBoardBean = boardDAO.selectBusinessBoardArticle(store_num);

        if (updateCount > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);

        return businessBoardBean;
    }

    // 매장 정보 조회 메서드 (게시글의 store_num 참조)
    public StoreBean getStoreInfo(int store_num) {

        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        StoreBean storeBean = null;

        storeBean = boardDAO.getStoreInfo(store_num);

        close(con);

        return storeBean;
    }

    public ArrayList<BusinessFile> getImgFileList(int store_num) {

        Connection con = getConnection();
        BoardDAO boardDAO = BoardDAO.getInstance();
        boardDAO.setConnection(con);
        
        ArrayList<BusinessFile> imgFileList = null;

        imgFileList = boardDAO.getImgFileListBusiness(store_num);

        close(con);

        return imgFileList;

    }

}

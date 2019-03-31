package mypage.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import board.vo.ListBean;
import mypage.dao.MyPageDAO;
import mypage.vo.PickBean;

public class PickListService {

    public ArrayList<ListBean> getPickList(String user_id) {
        Connection con = getConnection();

        MyPageDAO myPageDAO = MyPageDAO.getInstance();
        myPageDAO.setConnection(con);

        ArrayList<ListBean> pl = myPageDAO.getPickList(user_id);

        close(con);

        return pl;
    }

}

package mypage.dao;

import static common.db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import board.vo.BoardBean;
import board.vo.ListBean;
import comment.vo.CommentBean;
import comment.vo.MemberBean;
import mypage.vo.PickBean;

public class MyPageDAO {
    Connection con;
    DataSource ds;

    private static MyPageDAO instance;

    private MyPageDAO() {
    }

    public static MyPageDAO getInstance() {
        if (instance == null) {
            instance = new MyPageDAO();
        }
        return instance;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    public ArrayList<ListBean> getPickList(String user_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ListBean> PickList = new ArrayList<ListBean>();

        try {
            String sql = "SELECT * FROM pick p JOIN board b ON(p.board_num = b.board_num) JOIN store s ON (b.store_num = s.store_num) WHERE p.user_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ListBean listBean = new ListBean();

                listBean.setBoard_num(rs.getInt("board_num"));
                listBean.setUser_id(rs.getString("user_id"));
                listBean.setBoard_subject(rs.getString("board_subject"));
                listBean.setBoard_content(rs.getString("board_content"));
                listBean.setBoard_rating(rs.getDouble("board_rating"));
                listBean.setBoard_like(rs.getInt("board_like"));
                listBean.setBoard_date(rs.getDate("board_date"));
                listBean.setBoard_readcount(rs.getInt("board_readcount"));
                listBean.setStore_num(rs.getInt("store_num"));
                listBean.setStore_name(rs.getString("store_name"));
                listBean.setStore_address(rs.getString("store_address"));
                listBean.setStore_category(rs.getString("store_category"));
                listBean.setStore_menu(rs.getString("store_menu"));
                listBean.setStore_price(rs.getInt("store_price"));
                listBean.setStore_time(rs.getString("store_time"));
                listBean.setStore_image(rs.getString("store_image"));
                listBean.setStore_contact(rs.getString("store_contact"));

                PickList.add(listBean);
            }
        } catch (SQLException e) {
            System.out.println("getList() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return PickList;
    }
}

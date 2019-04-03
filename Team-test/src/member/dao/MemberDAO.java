package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import static common.db.JdbcUtil.*;
import member.vo.MemberBean;

public class MemberDAO {
    Connection con;
    DataSource ds;

    private static MemberDAO instance;

    private MemberDAO() {
    }

    public static MemberDAO getInstance() {
        if (instance == null) {
            instance = new MemberDAO();
        }
        return instance;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    public int insertMember(MemberBean memberBean) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int num = 0;
        int insertCount = 0;
        String sql = "";

        try {
            sql = "INSERT INTO  user(user_id, user_grade, user_pw, user_name, user_birth, user_email, user_phone, user_nickname, user_coupon) VALUES(?,?,?,?,?,?,?,?,?)";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, memberBean.getUser_id());
            pstmt.setString(2, memberBean.getUser_grade());
            pstmt.setString(3, memberBean.getUser_pw());
            pstmt.setString(4, memberBean.getUser_name());
            pstmt.setString(5, memberBean.getUser_birth());
            pstmt.setString(6, memberBean.getUser_email());
            pstmt.setString(7, memberBean.getUser_phone());
            pstmt.setString(8, memberBean.getUser_nickname());
            pstmt.setString(9, memberBean.getUser_coupon());

            insertCount = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("insertMember() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
        }

        return insertCount;
    }

    public MemberBean userCheck(String user_id, String user_pw) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        MemberBean memberBean = null;
        try {
            sql = "select * from user where user_id=? and user_pw=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user_id);
            pstmt.setString(2, user_pw);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                memberBean = new MemberBean();

                memberBean.setUser_id(rs.getString("user_id"));
                memberBean.setUser_grade(rs.getString("user_grade"));
                memberBean.setUser_pw(rs.getString("user_pw"));
                memberBean.setUser_name(rs.getString("user_name"));
                memberBean.setUser_birth(rs.getString("user_birth"));
                memberBean.setUser_email(rs.getString("user_email"));
                memberBean.setUser_phone(rs.getString("user_phone"));
                memberBean.setUser_nickname(rs.getString("user_nickname"));
                memberBean.setUser_coupon(rs.getString("user_coupon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rs);
        }
        return memberBean;
    }

    // 마이페이지에 쓸 정보 가져가기
    public MemberBean getMember(String user_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        MemberBean memberBean = null;
        try {
            sql = "select * from user where user_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                memberBean = new MemberBean();

                memberBean.setUser_id(rs.getString("user_id"));
                memberBean.setUser_grade(rs.getString("user_grade"));
                memberBean.setUser_pw(rs.getString("user_pw"));
                memberBean.setUser_name(rs.getString("user_name"));
                memberBean.setUser_birth(rs.getString("user_birth"));
                memberBean.setUser_email(rs.getString("user_email"));
                memberBean.setUser_phone(rs.getString("user_phone"));
                memberBean.setUser_nickname(rs.getString("user_nickname"));
                memberBean.setUser_coupon(rs.getString("user_coupon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rs);
        }

        return memberBean;
    }
}

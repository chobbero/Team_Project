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
            sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?)";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, memberBean.getUser_id());
            pstmt.setString(2, memberBean.getUser_pw());
            pstmt.setString(3, memberBean.getUser_name());
            pstmt.setString(4, memberBean.getUser_birth());
            pstmt.setString(5, memberBean.getUser_email());
            pstmt.setString(6, memberBean.getUser_phone());
            pstmt.setString(7, memberBean.getUser_nickname());
            pstmt.setString(8, memberBean.getUser_coupon());

            insertCount = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("insertMember() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
        }

        return insertCount;
    }

}

package mypage.dao;

import static common.db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import board.vo.ListBean;
import member.vo.MemberBean;

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
    
    //회원정보 수정
    public int updateUser(MemberBean mb)throws Exception {
        PreparedStatement pstmt = null;
      
        int check = 0;
        try {
            String sql = "update user set user_name=?, user_birth=?, user_phone=?, user_email=? where user_id=?";
            pstmt = con.prepareStatement(sql);
            
    		System.out.println("DB");
    		System.out.println(sql);
    		System.out.println(mb.getUser_id());
    		System.out.println(mb.getUser_name());
    		System.out.println(mb.getUser_phone());
    		System.out.println(mb.getUser_birth());
    		System.out.println(mb.getUser_email());
    		
            pstmt.setString(1, mb.getUser_name());
            pstmt.setString(2, mb.getUser_birth());
            pstmt.setString(3, mb.getUser_phone());
            pstmt.setString(4, mb.getUser_email());
            pstmt.setString(5, mb.getUser_id());

            check = pstmt.executeUpdate();
            System.out.println("DB check : "+check);
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return check;
    	
    }
    
    //삭제전 비밀번호 확인
    public MemberBean userCheck(String user_id, String user_pw) throws Exception{
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
    
    //회원정보 삭제
    public int deleteUser(String user_id){
        PreparedStatement pstmt = null;
        int result = 0;
        
        try {
        	
        	String sql = "delete from user where user_id = ?";
        	pstmt = con.prepareStatement(sql);
        	pstmt.setString(1, user_id);
        	result = pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        
        return result;
    }
    
    public ArrayList<ListBean> getMyPageBoardList(int page, int limit, String user_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ListBean> list = new ArrayList<ListBean>();

        int startRow = (page - 1) * 10;
        
        try {
            String sql = "SELECT * FROM board b JOIN store s ON (b.store_num = s.store_num) WHERE user_id = ? LIMIT ?,?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user_id);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, limit);
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

                list.add(listBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rs);
        }
        return list;
    }
    
    public int getMyPageBoardCount(String user_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        
        try {
            String sql = "SELECT count(*) FROM board WHERE user_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rs);
        }
        return result;
    }
}

package comment.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import board.vo.BoardBean;
import comment.vo.CommentBean;
import comment.vo.MemberBean;

public class CommentDAO {
    Connection con;
    DataSource ds;

    private static CommentDAO instance;

    private CommentDAO() {
    }

    public static CommentDAO getInstance() {
        if (instance == null) {
            instance = new CommentDAO();
        }
        return instance;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    // 해당 게시물의 정보받기 시작
    public BoardBean boardInfo(int board_num) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardBean boardBean = null;

        try {
            String sql = "SELECT * from board where board_num = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                boardBean = new BoardBean();

                boardBean.setBoard_content(rs.getString("board_content"));
                boardBean.setBoard_date(rs.getDate("board_date"));
                boardBean.setBoard_like(rs.getInt("board_like"));
                boardBean.setBoard_num(rs.getInt("board_num"));
                boardBean.setBoard_rating(rs.getDouble("board_rating"));
                boardBean.setBoard_readcount(rs.getInt("board_readcount"));
                boardBean.setBoard_subject(rs.getString("board_subject"));
                boardBean.setStore_num(rs.getInt("store_num"));
                boardBean.setUser_id(rs.getString("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rs);
        }

        return boardBean;
    }
    // 해당 게시물의 정보받기 끝

    // 댓글 작성자 정보 가져오기
    public MemberBean userInfo(String user_id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberBean memberBean = null;

        try {
            String sql = "SELECT * from user where user_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                memberBean = new MemberBean();

                memberBean.setUser_id(rs.getString("user_id"));
                memberBean.setUser_pw(rs.getString("user_pw"));
                memberBean.setUser_name(rs.getString("user_name"));
                memberBean.setUser_birth(rs.getString("user_birth"));
                memberBean.setUser_email(rs.getString("user_email"));
                memberBean.setUser_phone(rs.getString("user_phone"));
                memberBean.setUser_nickname(rs.getString("user_nickname"));
                memberBean.setUser_coupon(rs.getString("user_coupon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rs);
        }

        return memberBean;
    }

    // 댓글 데이터 넣기 시작

    public int insertComment(CommentBean commentBean) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int insertCount = 0;

        try {
            String sql = "insert into board_comment values(?,?,?,?,now(),?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, commentBean.getComment_num());
            pstmt.setString(2, commentBean.getUser_id());
            pstmt.setInt(3, commentBean.getBoard_num());
            pstmt.setString(4, commentBean.getComment_content());
            pstmt.setString(5, commentBean.getComment_like());
            insertCount = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rs);
        }

        return insertCount;
    }
    // 댓글 데이터 넣기 끝

    // 게시글의 댓글 갯수 조회
    public int getCommentCount(int board_num) {

        int commentCount = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT COUNT(*) FROM board_comment where board_num = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                commentCount = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("getCommentCount() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return commentCount;

    }

    // 댓글 리스트 가져오기
    public ArrayList<CommentBean> getBoardComment(int board_num) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
        CommentBean commentBean = null;

        try {
            String sql = "SELECT * FROM board_comment WHERE board_num = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                commentBean = new CommentBean();

                commentBean.setComment_num(rs.getInt("comment_num"));
                commentBean.setUser_id(rs.getString("user_id"));
                commentBean.setBoard_num(board_num);
                commentBean.setComment_content(rs.getString("comment_content"));
                commentBean.setComment_date(rs.getTimestamp("comment_date"));
                commentBean.setComment_like(rs.getString("comment_like"));

                commentList.add(commentBean);
            }

        } catch (SQLException e) {
            System.out.println("getBoardComment() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return commentList;

    }

}

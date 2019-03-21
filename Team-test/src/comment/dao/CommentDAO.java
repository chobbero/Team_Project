package comment.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import board.vo.BoardBean;
import comment.vo.CommentBean;

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

}

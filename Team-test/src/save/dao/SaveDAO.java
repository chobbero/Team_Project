package save.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import board.vo.BoardBean;
import save.vo.SaveBean;


public class SaveDAO {
    Connection con;
    DataSource ds;

    private static SaveDAO instance;

    private SaveDAO() {
    }

    public static SaveDAO getInstance() {
        if (instance == null) {
            instance = new SaveDAO();
        }
        return instance;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }
    
    public int insertSave(SaveBean saveBean) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    	int insertCount = 0;
    	
    	try {
			String sql ="insert into pick values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, saveBean.getUser_id());
			pstmt.setInt(2, saveBean.getBoard_num());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            close(rs);
            close(pstmt);
        }
    	
    	return insertCount;
    }
    
    public ArrayList<SaveBean> saveList(String user_id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<SaveBean> saveList = new ArrayList<SaveBean>();
        
        try {
			String sql = "select board_num from where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			SaveBean saveBean = new SaveBean();
			saveBean.setBoard_num(rs.getInt("board_num"));
			
			saveList.add(saveBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            close(pstmt);
            close(rs);
        }
        return saveList;
    }
    public BoardBean saveListBoard(int board_num) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardBean boardBean = null;
        
        try {
			String sql = "select board_subject, user_id from board where board_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardBean = new BoardBean();
				boardBean.setBoard_subject(rs.getString("board_subject"));
				boardBean.setUser_id(rs.getString("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            close(pstmt);
            close(rs);
        }
        
        return boardBean;
    }
}

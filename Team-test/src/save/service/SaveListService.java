package save.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import board.vo.BoardBean;
import save.dao.SaveDAO;
import save.vo.SaveBean;

public class SaveListService {
	public ArrayList<BoardBean> saveList(String user_id){
		
		Connection con = getConnection();
        SaveDAO saveDAO = SaveDAO.getInstance();
        saveDAO.setConnection(con);
        ArrayList<BoardBean> saveListBoard = null;
        
        ArrayList<SaveBean> saveList = saveDAO.saveList(user_id);
        
        for(int i = 0 ; i < saveList.size() ; i++) {
        	
        	BoardBean boardBean = saveDAO.saveListBoard(i);
        	
        	saveListBoard.add(boardBean);
        	}
        
        
        close(con);
		
		return saveListBoard;
	}
}

package save.service;

import static common.db.JdbcUtil.*;


import java.sql.Connection;

import save.dao.SaveDAO;
import save.vo.SaveBean;

public class SaveService {
	public boolean insertSave(SaveBean saveBean) {
		
		Connection con = getConnection();
        SaveDAO saveDAO = SaveDAO.getInstance();
        saveDAO.setConnection(con);
        boolean isCheck;
		
        int insertCount = saveDAO.insertSave(saveBean);
        
		if(insertCount > 0) {
			isCheck = true;
		}else {
			isCheck = false;
		}
        
		close(con);
		
		
		
		
	return isCheck;	
	}
}

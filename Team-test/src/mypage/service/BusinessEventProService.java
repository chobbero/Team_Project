package mypage.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.List;

import mypage.dao.MyPageDAO;
import mypage.vo.BusinessBoardBean;
import mypage.vo.BusinessFile;

public class BusinessEventProService {

	public boolean registArticle(BusinessBoardBean businessBoardBean, List<BusinessFile> businessFileList) throws Exception {

		boolean isWriteSuccess = false;

		Connection con = getConnection();

		MyPageDAO myPageDAO = MyPageDAO.getInstance();
		myPageDAO.setConnection(con);

		int insertCount = myPageDAO.insertArticle(businessBoardBean, businessFileList);

		if (insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		} else {
			rollback(con);
		}

		close(con);

		return isWriteSuccess;
	}
}

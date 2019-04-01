package mypage.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import member.vo.MemberBean;
import mypage.dao.MyPageDAO;

public class UserUpdateProService {
	public boolean updateUser(MemberBean mb) throws Exception{
		
		
		Connection con = getConnection();
	    MyPageDAO myPageDAO = MyPageDAO.getInstance();
	    myPageDAO.setConnection(con);
		
		boolean check = false;
		
		System.out.println("서비스");
		System.out.println(mb.getUser_id());
		System.out.println(mb.getUser_name());
		System.out.println(mb.getUser_phone());
		System.out.println(mb.getUser_birth());
		System.out.println(mb.getUser_email());
		
		int updateCheck = myPageDAO.updateUser(mb);
		System.out.println("update check : " + updateCheck);
		if(updateCheck > 0) {
			commit(con);
			check = true;
		}else {
			rollback(con);
		}
		
		
		close(con);
		
		
		return check;
		
	}

}

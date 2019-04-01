package mypage.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;


import member.vo.MemberBean;
import mypage.dao.MyPageDAO;

public class UserUpdateFormService {
	
	//회원정보 가져오기
	public MemberBean userInfo(String user_id) throws Exception{
		
		Connection con = getConnection();
	    MyPageDAO myPageDAO = MyPageDAO.getInstance();
	    myPageDAO.setConnection(con);
		
		MemberBean memberBean = new MemberBean();
		memberBean = myPageDAO.getMember(user_id);
		
		close(con);
		return memberBean;
	}
	

	//비밀번호 확인
    public MemberBean userCheck(String user_id, String user_pw) throws Exception{
        MemberBean mb = null;

        Connection con = getConnection();

        MyPageDAO myPageDAO = MyPageDAO.getInstance();
        myPageDAO.setConnection(con);

        mb = myPageDAO.userCheck(user_id, user_pw);

        close(con);
        
        return mb;
    }
}

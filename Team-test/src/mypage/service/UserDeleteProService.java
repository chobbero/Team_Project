package mypage.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import member.vo.MemberBean;
import mypage.dao.MyPageDAO;

public class UserDeleteProService {
	
	//회원탈퇴
	public boolean userDelete(String user_id) {
        Connection con = getConnection();
        MyPageDAO myPageDAO = MyPageDAO.getInstance();
        myPageDAO.setConnection(con);
        
        boolean deleteCheck = false;
        int deleteCk = 0;
        deleteCk = myPageDAO.deleteUser(user_id);
        System.out.println("서비스 : "+deleteCk);
        if(deleteCk > 0) {
        	commit(con);
        	deleteCheck = true;
        	
        }else {
        	rollback(con);
        	
        }
        
        
        close(con);
        
        return deleteCheck;
        		
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

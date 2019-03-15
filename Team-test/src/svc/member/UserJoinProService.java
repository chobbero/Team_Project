package svc.member;

import vo.BoardBean;
import vo.MemberBean;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import dao.MemberDAO;

public class UserJoinProService {

    public boolean registUser(MemberBean memberBean) throws Exception {
        boolean isRegistUser = false;

        Connection con = getConnection();

        MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.setConnection(con);

        int insertCount = memberDAO.insertMember(memberBean);

        if (insertCount > 0) {
            commit(con);
            isRegistUser = true;
        } else {
            rollback(con);
        }

        close(con);

        return isRegistUser; 
    }

}

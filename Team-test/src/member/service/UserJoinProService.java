package member.service;

import member.vo.MemberBean;
import static common.db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.MemberDAO;

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

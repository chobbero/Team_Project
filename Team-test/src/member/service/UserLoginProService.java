package member.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class UserLoginProService {

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

    public MemberBean userCheck(String user_id, String user_pw) {
        MemberBean mb = null;

        Connection con = getConnection();

        MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.setConnection(con);

        mb = memberDAO.userCheck(user_id, user_pw);

        close(con);
        
        return mb;
    }

}

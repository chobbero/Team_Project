package member.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class UserGradeUpdate {

    public boolean userGradeUpdate(MemberBean memberBean) throws Exception {
        boolean isSuccess = false;

        Connection con = getConnection();

        MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.setConnection(con);

        int insertCount = memberDAO.userGradeUpdate(memberBean);

        if (insertCount > 0) {
            commit(con);
            isSuccess = true;
        } else {
            rollback(con);
        }

        close(con);

        return isSuccess;
    }

}

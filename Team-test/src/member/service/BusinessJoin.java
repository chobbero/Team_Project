package member.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;
import mypage.vo.BusinessMemberBean;

public class BusinessJoin {

    public void BusinessJoin(BusinessMemberBean businessMemberBean) throws Exception {
        Connection con = getConnection();

        MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.setConnection(con);

        int insertCount = memberDAO.BusinessJoin(businessMemberBean);

        if (insertCount > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }

}

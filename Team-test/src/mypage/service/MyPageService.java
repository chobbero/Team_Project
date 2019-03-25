package mypage.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class MyPageService {

    public MemberBean getMember(String user_id) {
        Connection con = getConnection();

        MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.setConnection(con);

        MemberBean mb = memberDAO.getMember(user_id);

        close(con);

        return mb;
    }

}

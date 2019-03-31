package member.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.action.Action;
import member.service.UserJoinProService;
import member.vo.ActionForward;
import member.vo.MemberBean;

public class UserJoinProAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;

        MemberBean memberBean = null;

        memberBean = new MemberBean();
        memberBean.setUser_id(request.getParameter("user_id"));
        memberBean.setUser_grade(request.getParameter("user_grade"));
        memberBean.setUser_pw(request.getParameter("user_pw"));
        memberBean.setUser_name(request.getParameter("user_name"));
        memberBean.setUser_birth(request.getParameter("user_birth"));
        memberBean.setUser_email(request.getParameter("user_email"));
        memberBean.setUser_phone(request.getParameter("user_phone"));
        memberBean.setUser_nickname(request.getParameter("user_nickname"));

        UserJoinProService userJoinProService = new UserJoinProService();
        boolean isWriteSuccess = userJoinProService.registUser(memberBean);

        if (!isWriteSuccess) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('회원 가입 실패!')");
            out.println("history.back()");
            out.println("</script>");
        } else {
            forward = new ActionForward();
            forward.setRedirect(true);

            forward.setPath("main");
        }

        return forward;
    }

}

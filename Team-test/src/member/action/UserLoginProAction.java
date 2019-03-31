package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.dao.MemberDAO;
import member.service.UserJoinProService;
import member.service.UserLoginProService;
import member.vo.ActionForward;
import member.vo.MemberBean;

public class UserLoginProAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String user_id = request.getParameter("id");
        String user_pw = request.getParameter("pass");

        UserLoginProService userLoginProService = new UserLoginProService();

        MemberBean mb = userLoginProService.userCheck(user_id, user_pw);

        if (mb == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('회원 정보를 다시 확인하세요.');");
            out.println("history.back();");
            out.println("</script>");
            out.close();

        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user_id", mb.getUser_id());
            session.setAttribute("user_grade", mb.getUser_grade());

        }

        ActionForward forward = new ActionForward();

        forward.setRedirect(true);
        forward.setPath("./main");

        return forward;
    }
}

package mypage.action;

import static common.db.JdbcUtil.*;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.vo.MemberBean;
import mypage.service.UserDeleteProService;
import mypage.vo.ActionForward;

public class UserDeleteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("user_id");
		String user_pw = request.getParameter("user_pw");
		
		UserDeleteProService userDeleteProService = new UserDeleteProService();
		MemberBean mb = userDeleteProService.userCheck(user_id, user_pw);
		
		
		
		if(mb == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('회원 정보를 다시 확인하세요.');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
            

		}else {
			boolean deleteCheck = userDeleteProService.userDelete(user_id);
			if(deleteCheck == false) {
	            response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>");
	            out.println("alert('회원 회원탈퇴에 실패하셨습니다.');");
	            out.println("history.back();");
	            out.println("</script>");
	            out.close();
			}else {
				session.invalidate();
				forward.setPath("/main.jsp");
			}
		}
		
		return forward;
	}
	

}

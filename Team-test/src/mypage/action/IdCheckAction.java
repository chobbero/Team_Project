package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import member.vo.MemberBean;
import mypage.service.UserDeleteProService;
import mypage.service.UserUpdateFormService;
import mypage.vo.ActionForward;

public class IdCheckAction implements Action {

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
			
			forward.setPath("/mypage/user_update.jsp");
		}
		
		return forward;
	}
	

}

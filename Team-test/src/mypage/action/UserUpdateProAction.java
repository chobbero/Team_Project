package mypage.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.vo.MemberBean;
import mypage.service.UserUpdateProService;
import mypage.vo.ActionForward;

public class UserUpdateProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		MemberBean mb = new MemberBean();

		mb.setUser_id(user_id);
		mb.setUser_name(request.getParameter("user_name"));
		mb.setUser_birth(request.getParameter("user_birth"));
		mb.setUser_phone(request.getParameter("user_phone"));
		mb.setUser_email(request.getParameter("user_email"));
		
		System.out.println(user_id);
		System.out.println(request.getParameter("user_name"));
		System.out.println(request.getParameter("user_birth"));
		System.out.println(request.getParameter("user_phone"));
		System.out.println(request.getParameter("user_email"));
		
		boolean check = false;
		
		UserUpdateProService userUpdateProService  = new UserUpdateProService();
		
		check = userUpdateProService.updateUser(mb);
		
		System.out.println("액션 check : "+check);
		if (check == true) {
			forward.setPath("./main.jsp");
		}else {
			forward.setPath("./main.jsp");
     /*       response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('회원 수정 실패!')");
            out.println("history.back()");
            out.println("</script>");*/
		}
		
		return forward;
	}
	

}

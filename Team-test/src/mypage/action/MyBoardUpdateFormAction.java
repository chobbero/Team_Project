package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.vo.ActionForward;

public class MyBoardUpdateFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		System.out.println(request.getAttribute("user_id"));
		forward.setPath("./mypage/board_update.jsp");
		
		return forward;
	}
	
}

package main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.vo.ActionForward;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MainAction"); // 위치확인용
		
		ActionForward forward = new ActionForward();

		forward.setPath("./main.jsp");

		return forward;
	}
	

}

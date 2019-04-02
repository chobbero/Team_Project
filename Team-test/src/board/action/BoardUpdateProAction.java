package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardUpdateProService;
import board.vo.ActionForward;
import board.vo.BoardBean;

public class BoardUpdateProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardBean boardBean = new BoardBean();
		boolean isUpdateSuccess = false;
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String user_id = (String)request.getParameter("user_id");

		boardBean.setUser_id(user_id);
		boardBean.setBoard_num(board_num);
		boardBean.setBoard_subject(subject);
		boardBean.setBoard_content(content);
		
		BoardUpdateProService boardUpdateProService = new BoardUpdateProService();
		isUpdateSuccess = boardUpdateProService.updateBoard(boardBean);
		
		if(!isUpdateSuccess) {
    		response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
    		response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 성공')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}
	
}

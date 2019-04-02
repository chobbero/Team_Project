package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardDeleteService;
import board.vo.ActionForward;

public class BoardDeleteProAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ActionForward forward = new ActionForward();
    	HttpSession session = request.getSession(false);
    	
    	int board_num = Integer.parseInt(request.getParameter("board_num"));
    	String user_id = (String)session.getAttribute("user_id");
    	boolean isDeleteSuccess = false;
    	
    	BoardDeleteService boardDeleteService = new BoardDeleteService();
    	isDeleteSuccess = boardDeleteService.deleteBoard(board_num, user_id);
    	
    	if(!isDeleteSuccess) {
    		response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
    	} else {
    		response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제하였습니다')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("boardUpdateListForm.mp");
    	}
    	
    	return forward;
    }

}

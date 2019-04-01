package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardUpdateFormService;
import board.vo.ActionForward;
import board.vo.BoardBean;

public class BoardUpdateFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println("board_num : "+board_num);
		BoardUpdateFormService boardUpdateFormService = new BoardUpdateFormService();
		BoardBean boardBean = new BoardBean(); 
		boardBean = boardUpdateFormService.getBoardDetail(board_num);
		
		request.setAttribute("BoardBean", boardBean);
		request.setAttribute("board_num", board_num);
		
		forward.setPath("/board/boardUpdateForm.jsp");
		
		return forward;
	}
	
}

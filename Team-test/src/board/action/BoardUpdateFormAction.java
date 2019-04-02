package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardUpdateFormService;
import board.vo.ActionForward;
import board.vo.BoardBean;

public class BoardUpdateFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
    	HttpSession session = request.getSession(false);
    	
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String user_id = (String)session.getAttribute("user_id");
		
		BoardUpdateFormService boardUpdateFormService = new BoardUpdateFormService();
		BoardBean boardBean = new BoardBean(); 
		boardBean = boardUpdateFormService.getBoardDetail(board_num);
		if (!boardBean.getUser_id().equals(user_id)) { // 게시글 작성자와 세션아이디가 동일한지 확인 
			request.setAttribute("msg", "작성자만이 가능합니다");
			forward.setPath("/member/msg.jsp");
		} else {
			request.setAttribute("BoardBean", boardBean);
			request.setAttribute("board_num", board_num);
			forward.setPath("/board/boardUpdateForm.jsp");
		}
		
		return forward;
	}
	
}

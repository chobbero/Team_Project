package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.service.CommentWriteProService;
import comment.vo.ActionForward;
import comment.vo.CommentBean;

public class CommentWriteProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
    	
		CommentBean commentBean = null;
		HttpSession session = null;
        
        boolean isCheck = false;
		
		String user_id = (String)session.getAttribute("user_id");
		
		commentBean.setComment_content(request.getParameter("commnet_content"));
		commentBean.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		commentBean.setUser_id(user_id);
		
		CommentWriteProService insertComment = new CommentWriteProService();
		isCheck = insertComment.insertComment(commentBean);
		
        if(isCheck) {
            forward.setPath("/board/boardList.jsp");
        }else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('삭제 실패')");
            out.println("history.back()");
            out.println("</script>");
        }
		
		
    	return forward;
    	
	}
}
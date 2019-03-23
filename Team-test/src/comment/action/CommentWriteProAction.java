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
        CommentBean commentBean = new CommentBean();
        boolean isCheck = false;

        String user_id = request.getParameter("user_id");
        int board_num = Integer.parseInt(request.getParameter("board_num"));
        String content = request.getParameter("comment_content");
        String like = request.getParameter("comment_like");

        commentBean.setUser_id(user_id);
        commentBean.setBoard_num(board_num);
        commentBean.setComment_content(content);
        commentBean.setComment_like(like);

        CommentWriteProService commentWriteProService = new CommentWriteProService();
        isCheck = commentWriteProService.insertComment(commentBean);

        if (isCheck) {
            forward.setPath("./BoardList.bo");
            forward.setRedirect(true);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('등록 실패')");
            out.println("history.back()");
            out.println("</script>");
        }

        return forward;

    }
}
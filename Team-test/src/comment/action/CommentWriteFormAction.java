package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.vo.BoardBean;
import comment.service.CommentWriteFormService;
import comment.vo.ActionForward;
import comment.vo.MemberBean;

public class CommentWriteFormAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // HttpSession 객체를 request 객체로부터 전달받기
        // HttpSession 객체의 setAttribute() 메서드를 사용하여 아이디 속성 저장
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("user_id");
        ActionForward forward = new ActionForward();

        int board_num = Integer.parseInt(request.getParameter("board_num"));
//        int board_num = 1;
//        String user_id = "demian";

        if (user_id == null) {
            forward.setPath("./board/boardDatail.jsp");
        } else {
            CommentWriteFormService commentWriteFormService = new CommentWriteFormService();
            BoardBean boardBean = commentWriteFormService.boardInfo(board_num);
            MemberBean memberBean = commentWriteFormService.userInfo(user_id);
            
            request.setAttribute("boardBean", boardBean);
            request.setAttribute("memberBean", memberBean);

            forward.setPath("./comment/commentWrite.jsp");

        }

        return forward;

    }
}

package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.Action;
import board.vo.ActionForward;

public class BoardDeleteProAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("BoardDeleteProAction!");
        System.out.println("글번호 : " + request.getParameter("board_num"));
        System.out.println("페이지 번호 : " + request.getParameter("page"));
        System.out.println("패스워드 : " + request.getParameter("board_pass"));
        return null;
    }

}

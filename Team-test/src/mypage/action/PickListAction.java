package mypage.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.vo.ListBean;
import member.vo.MemberBean;
import mypage.service.PickListService;
import mypage.vo.ActionForward;
import mypage.vo.PickBean;

public class PickListAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       ActionForward forward = null;
       
       HttpSession session = request.getSession();
       String user_id = (String)session.getAttribute("user_id");
       
       PickListService pickListService = new PickListService();
       
       ArrayList<ListBean> pl = pickListService.getPickList(user_id);
       
       request.setAttribute("pl", pl);
       forward = new ActionForward();
       forward.setPath("./mypage/pickList.jsp");
       
        return forward;
    }

}

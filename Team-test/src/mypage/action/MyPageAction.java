package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import member.vo.MemberBean;
import mypage.service.MyPageService;
import mypage.vo.ActionForward;

public class MyPageAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       ActionForward forward = null;
       
       HttpSession session = request.getSession();
       String user_id = (String)session.getAttribute("user_id");
       
       MyPageService myPageService = new MyPageService();
       
       MemberBean mb = myPageService.getMember(user_id);
       
       request.setAttribute("mb", mb);
       forward = new ActionForward();
       forward.setPath("./mypage/myPage.jsp");
       
        return forward;
    }

}

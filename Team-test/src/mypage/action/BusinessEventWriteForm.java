package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.vo.MemberBean;
import mypage.service.BusinessEventWriteFormService;
import mypage.vo.ActionForward;
import mypage.vo.StoreBean;

public class BusinessEventWriteForm implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();

        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");

        BusinessEventWriteFormService businessEventWriteFormService = new BusinessEventWriteFormService();

        StoreBean storeBean = businessEventWriteFormService.getStoreInfo(user_id);

        request.setAttribute("storeBean", storeBean);
        
        forward.setPath("./mypage/businessEvent.jsp");

        return forward;
    }

}

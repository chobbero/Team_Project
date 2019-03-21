package save.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.vo.BoardBean;
import save.service.SaveListService;
import save.vo.ActionForward;
import save.vo.SaveBean;

public class SaveListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = null;
		session.setAttribute("user_id", 123);
		
		
    	
    	
    	String user_id = (String)session.getAttribute("user_id");
    	
    	if(user_id == null) {
            //로그인 페이지로 이동
    	}else {
    		SaveListService saveListService = new SaveListService();
    		ArrayList<BoardBean> boardBean = saveListService.saveList(user_id);
    		
    		request.setAttribute("boardBean", boardBean);
    		
    		forward.setPath("페이지");
    	
    	}
    	return forward;
	}	
}

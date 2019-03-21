package save.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import save.service.SaveService;
import save.vo.ActionForward;
import save.vo.SaveBean;


public class SaveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		HttpSession session = null;
		
		SaveBean saveBean = null;
    	
    	int board_num = Integer.parseInt(request.getParameter("board_num"));
    	//String user_id = (String)session.getAttribute("user_id");
    	
    	//if(user_id == null) {
          //  forward.setPath("../board/boardDatail.jsp"); 
    	//}else {
    	saveBean.setBoard_num(board_num);
    	//saveBean.setUser_id(user_id);
    	
    	SaveService saveService = new SaveService();
    	boolean isCheck = saveService.insertSave(saveBean);
		
    	if(isCheck) {
    		 forward.setPath("../board/boardDatail.jsp"); 
    	}else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('찜하기에 실패하셨습니다.')");
            out.println("</script>");
            response.sendRedirect("../board/boardDatail.jsp");
    	}
    	
		return forward;
	}
	
	
}

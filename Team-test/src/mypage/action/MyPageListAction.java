package mypage.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardListService;
import board.vo.ListBean;
import board.vo.PageInfo;
import mypage.service.MyPageListService;
import mypage.vo.ActionForward;

public class MyPageListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		PageInfo pageInfo = new PageInfo();
		HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("user_id");
		
		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		MyPageListService myPageListService = new MyPageListService();
		
		int listCount = myPageListService.getMyPageBoardCount(user_id);
		
		ArrayList<ListBean> listBean = myPageListService.getMyPageBoardList(page, limit, user_id);

		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 9;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pageInfo.setPage(page);
		pageInfo.setListCount(listCount);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setMaxPage(maxPage);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("ListBean", listBean);

		forward.setPath("./mypage/board_update_list.jsp");

		return forward;
	}
	
}

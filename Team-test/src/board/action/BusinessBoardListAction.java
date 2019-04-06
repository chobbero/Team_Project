package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardListService;
import board.vo.ActionForward;
import board.vo.ListBean;
import board.vo.PageInfo;
import mypage.vo.BusinessBoardBean;
import mypage.vo.StoreBean;

public class BusinessBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		PageInfo pageInfo = new PageInfo();

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		BoardListService boardListService = new BoardListService();
		
		int listCount = boardListService.getBusinessBoardCount();
		
		ArrayList<StoreBean> storeBean = (ArrayList<StoreBean>) boardListService.getStoreList(page, limit);
		ArrayList<BusinessBoardBean> boardBean = (ArrayList<BusinessBoardBean>) boardListService.getBusinessBoardList(page, limit);
		
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
		request.setAttribute("storeBean", storeBean);
		request.setAttribute("boardBean", boardBean);


		forward.setPath("./board/boardList_business.jsp");

		return forward;
	}

}

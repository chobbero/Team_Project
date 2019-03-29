package main.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardDetailService;
import board.service.BoardListService;
import board.vo.BoardBean;
import board.vo.StoreBean;
import main.vo.ActionForward;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MainAction"); // 위치확인용
		
		BoardListService boardListService = new BoardListService();
		
		// 댓글 수 많은 맛집 리스트 가져오기
		ArrayList<StoreBean> rankList = boardListService.getRankList();
		
		// TOP1 맛집 리스트의 좋아요 많은 후기 가져오기
		int topStoreNum = rankList.get(0).getStore_num();
		BoardDetailService boardDetailService = new BoardDetailService();
		BoardBean topStoreReview = boardDetailService.getTopBoardArticle(topStoreNum);
		
		request.setAttribute("rankList", rankList);
		request.setAttribute("topStoreReview", topStoreReview);
		
		ActionForward forward = new ActionForward();

		forward.setPath("/main.jsp");

		return forward;
	}
	

}

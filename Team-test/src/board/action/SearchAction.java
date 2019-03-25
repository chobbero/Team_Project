package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardDetailService;
import board.service.SearchService;
import board.vo.ActionForward;
import board.vo.BoardBean;
import board.vo.FileBean;
import board.vo.StoreBean;

public class SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("SearchAction"); // 위치확인용
		
		// 인스턴스 생성
		SearchService SearchService = new SearchService();
		
		// 검색카테고리 가져오기 (상호, 게시물제목, 게시글내용)
		String category = request.getParameter("category");
		
		// 검색어 가져오기
		String search = request.getParameter("search_input");
		search.trim(); // 앞뒤 공백 제거
		System.out.println("검색어 : " + search); // 검색어 확인용
		
		// 게시글 검색 갯수 조회 (카테고리 전달)
		int searchCount = 0;
		searchCount = SearchService.getSearchCount(search, category);
		System.out.println("갯수 : " + searchCount); // 검색 게시물 갯수 확인용
		
		ArrayList<BoardBean> SearchList = null;
		ArrayList<StoreBean> SearchList2 = null;
		
		// 게시글 내용 검색
		if (searchCount > 0) { // 검색 결과가 존재 시 행동
			
			// 카테고리에 따라 글 목록 가져오기
			if (category.equals("subject") || category.equals("content")) { // 게시물 검색
				
				SearchList = new ArrayList<BoardBean>();
				
				// 
				SearchList = SearchService.getSearchList(search, category);
				int ImgFileCount = 0;
				ArrayList<FileBean> imgFileList = new ArrayList<FileBean>();
				for (BoardBean bb : SearchList) {
					
					ImgFileCount = SearchService.getImgFileCount(bb.getBoard_num());
					System.out.println("글번호 : " + bb.getBoard_num() + " 이미지 갯수 : " + ImgFileCount);
					
					
					if (ImgFileCount > 0) {
						imgFileList = SearchService.getImgFileList(bb.getBoard_num());
						request.setAttribute("imgFileList", imgFileList);
					}
				}
				request.setAttribute("searchList", SearchList);
				
			} else if (category.equals("store")) { // 매장 검색
				
				SearchList2 = new ArrayList<StoreBean>();
				SearchList2 = SearchService.getSearchList2(search);
				request.setAttribute("searchList2", SearchList2);
			}
		}
		
		request.setAttribute("search", search);
		request.setAttribute("searchCount", searchCount);
		request.setAttribute("category", category);
		
		ActionForward forward = new ActionForward();

		forward.setPath("./search.jsp");

		return forward;
	}
	
}

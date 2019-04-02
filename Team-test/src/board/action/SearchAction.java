package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.SearchService;
import board.vo.ActionForward;
import board.vo.BoardBean;
import board.vo.FileBean;
import board.vo.StoreBean;

public class SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 인스턴스 생성
		SearchService SearchService = new SearchService();
		int searchCount = 0;
		int ImgFileCount = 0;
		ArrayList<BoardBean> SearchList = null;
		System.out.println(request.getParameter("store_category"));
		
		/// 메인에서 이동 맛집카테고리 클릭
		if (request.getParameter("store_category") != null) {
			String storeCategory = request.getParameter("store_category").trim();
			searchCount = SearchService.getSearchCount(storeCategory);
			
			if (searchCount > 0) {
				SearchList = SearchService.getSearchList(storeCategory);
				
				// 검색 게시물 이미지 파일명 가져오기 (한장)
				ArrayList<FileBean> imgFileList = new ArrayList<FileBean>();
				for (BoardBean bb : SearchList) {
					ImgFileCount = SearchService.getImgFileCount(bb.getBoard_num());
					if (ImgFileCount > 0) {
						// 여러장을 가져와야할 때는 반복문 추가 사용
						imgFileList.add(SearchService.getImgFileName(bb.getBoard_num())); 
					}
				}
				request.setAttribute("imgFileList", imgFileList);
				request.setAttribute("searchList", SearchList);
				
			}
			request.setAttribute("store_category", storeCategory);
			
		// 검색창 검색시
		} else {
			// 검색카테고리 가져오기 (상호, 게시물제목, 게시글내용)
			String category = request.getParameter("category");
			
			// 검색어 가져오기
			String search = request.getParameter("search_input").trim(); // 공백 제거
			
			// 게시글 검색 갯수 조회 (카테고리 전달)
			searchCount = SearchService.getSearchCount(search, category);
			
			ArrayList<StoreBean> SearchList2 = null;
			
			// 게시글 내용 검색
			if (searchCount > 0) { // 검색 결과가 존재 시 행동
				
				// 카테고리에 따라 글 목록 가져오기
				if (category.equals("subject") || category.equals("content")) { // 게시물 검색
					
					SearchList = new ArrayList<BoardBean>();
					// 검색 게시물 가져오기
					SearchList = SearchService.getSearchList(search, category);

					// 검색 게시물 이미지 파일명 가져오기 (한장)
					ImgFileCount = 0;
					ArrayList<FileBean> imgFileList = new ArrayList<FileBean>();
					for (BoardBean bb : SearchList) {
						ImgFileCount = SearchService.getImgFileCount(bb.getBoard_num());
						if (ImgFileCount > 0) {
							// 여러장을 가져와야할 때는 반복문 추가 사용
							imgFileList.add(SearchService.getImgFileName(bb.getBoard_num())); 
						}
					}
					request.setAttribute("imgFileList", imgFileList);
					request.setAttribute("searchList", SearchList);
					
				} else if (category.equals("store")) { // 매장 검색
					SearchList2 = new ArrayList<StoreBean>();
					SearchList2 = SearchService.getSearchList2(search);
					request.setAttribute("searchList2", SearchList2);
				}
			}
			request.setAttribute("search", search);
			request.setAttribute("category", category);
		}
		
		request.setAttribute("searchCount", searchCount);
		
		ActionForward forward = new ActionForward();

		forward.setPath("./search.jsp");

		return forward;
	}
	
}

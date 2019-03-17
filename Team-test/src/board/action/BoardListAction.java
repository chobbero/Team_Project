package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.Action;
import board.service.BoardListService;
import board.vo.ActionForward;
import board.vo.BoardBean;
import board.vo.PageInfo;

public class BoardListAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("BoardListAction!");
        
        // 게시물 목록을 저장할 ArrayList 객체
        ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
        
        int page = 1; // 현재 페이지 번호(기본값 1)
        int limit = 10; // 페이지 당 출력할 게시물 갯수
        
        // request 객체로부터 전달된 파라미터 "page" 가 null 이 아닐 경우 변수 page 의 값에 해당 파라미터 값 저장
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page")); // String 타입으로 리턴되는 page 번호를 int형으로 변환
        }
        
        // Service 객체 생성
        BoardListService boardListService = new BoardListService();
        // Service 객체의 getListCount() 메서드를 사용하여 총 게시물 수 리턴받아 listCount 변수에 저장
        int listCount = boardListService.getListCount();
//        System.out.println("listCount : " + listCount);
        
        // Service 객체의 getArticleList() 메서드를 사용하여 게시물 목록 리턴받아 ArrayList 객체인 articleList 변수에 저장
        // => 메서드 호출 시 page, limit 전달
        articleList = boardListService.getArticleList(page, limit);
        
        // 페이지 수 관련 계산
        int maxPage = (int)((double)listCount / limit + 0.95); // 총 페이지 수(0.95 더해서 올림 처리)
        int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1; // 현재 페이지에서 표시할 시작페이지 (1, 11, 21... 등)
        int endPage = startPage + 10 - 1; // 현재 페이지에서 보여줄 끝페이지(10, 20, 30... 등)
        
        if(endPage > maxPage) {
            // 최대 페이지 번호보다 끝 페이지 번호가 클 경우 최대 페이지 번호를 끝 페이지 번호로 변경
            endPage = maxPage;
        }
        
        // 페이징 정보를 저장할 PageInfo 객체 생성 후 각 정보 저장
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(page);
        pageInfo.setListCount(listCount);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        pageInfo.setMaxPage(maxPage);
        
        // request 객체를 사용하여 jsp 페이지로 넘겨줄 객체를 저장
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("articleList", articleList);
        
        // ActionForward 객체 생성하여 setPath() 메서드를 통해 포워딩 할 jsp 페이지 지정
        ActionForward forward = new ActionForward();
        forward.setPath("/board/qna_board_list.jsp");
        
        return forward; // ActionForward 객체 리턴 => FrontController 로 전달됨
    }
    
}






















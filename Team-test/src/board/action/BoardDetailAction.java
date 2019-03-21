package board.action;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.Action;
import board.service.BoardDetailService;
import board.vo.ActionForward;
import board.vo.BoardBean;
import board.vo.CommentBean;
import board.vo.StoreBean;

public class BoardDetailAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("BoardDetailAction"); // 위치확인용
        
        // request 객체로부터 전달받은 글 번호(board_num), 페이지 번호(page) 가져오기
        // 페이지 번호는 조회에서는 필요없고, 다음 페이지 표시할 때 사용하기 때문에 정수형 변환이 필요없음
        
        int board_num = 2; // 하드코딩
        
//       int board_num = Integer.parseInt(request.getParameter("board_num"));
        
//        String page = request.getParameter("page"); // ?(용도?)
        
        
        // boardList에서 board_num 값 가져오기 (현재 board_num 하드코딩)
        BoardDetailService boardDetailService = new BoardDetailService();
        
        // 후기 가져오기
        BoardBean boardBean = boardDetailService.getBoardArticle(board_num);
        
        // 해당 글의 매장 정보 로드
        int store_num = boardBean.getStore_num();
        StoreBean storeBean = boardDetailService.getStoreInfo(store_num);
        
        // 매장 후기 갯수
        int boardCount = boardDetailService.getBoardCount(store_num);
        
        // 이미지 파일 가져오기
        ArrayList<String> imgFileList = boardDetailService.getImgFileList(board_num);
        
        // 댓글 가져오기 (갯수 카운트, 댓글 내용 등)
        int commentCount = boardDetailService.getCommentCount(board_num);
        
        // 댓글 존재시에만 실행 댓글 리스트 가져오기 및 전달
        if (commentCount > 0 ) { 
        	ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
        	commentList = boardDetailService.getBoardComment(board_num);
        	request.setAttribute("cl", commentList);
        }
        
        ActionForward forward = new ActionForward();
        
        // 데이터 전달
        request.setAttribute("bb", boardBean);
        request.setAttribute("sb", storeBean);
        request.setAttribute("bc", boardCount);
        request.setAttribute("img", imgFileList);
        request.setAttribute("cc", commentCount);
//        request.setAttribute("page", page); // ?
        
        // 경로 지정
        forward.setPath("/board/boardDetail.jsp");
        
        return forward; // ActionForward 객체 리턴
    }

}





















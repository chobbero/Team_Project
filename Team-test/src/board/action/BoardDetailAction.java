package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.Action;
import board.service.BoardDetailService;
import board.vo.ActionForward;
import board.vo.BoardBean;
import board.vo.StoreBean;
import comment.vo.CommentBean;

public class BoardDetailAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("BoardDetailAction"); // 위치확인용
        
//        int board_num = 2; // 하드코딩
        
        // boardList에서 board_num 값 가져오기 (현재 board_num 하드코딩)
        int board_num = Integer.parseInt(request.getParameter("board_num"));
                
        // 인스턴스 생성
        BoardDetailService boardDetailService = new BoardDetailService();
        
        // 후기 가져오기
        BoardBean boardBean = boardDetailService.getBoardArticle(board_num);
        
        // 해당 글의 매장 정보 로드
        int store_num = boardBean.getStore_num();
        StoreBean storeBean = boardDetailService.getStoreInfo(store_num);
        
        // 매장 후기 갯수
        int boardCount = boardDetailService.getBoardCount(store_num);
        
        // 이미지 파일 갯수
        int imgFileCount = boardDetailService.getImgFileCount(board_num);
        
        // 이미지 파일 가져오기
        if (imgFileCount > 0) {
        	ArrayList<String> imgFileList = boardDetailService.getImgFileList(board_num);
            request.setAttribute("img", imgFileList);
        }
        
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
        request.setAttribute("ic", imgFileCount);
        request.setAttribute("cc", commentCount);
        
        
        // 경로 지정
        forward.setPath("./board/boardDetail.jsp");
        
        return forward; // ActionForward 객체 리턴
    }

}





















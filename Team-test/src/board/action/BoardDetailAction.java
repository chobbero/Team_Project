package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.Action;
import board.service.BoardDetailService;
import board.vo.ActionForward;
import board.vo.BoardBean;

public class BoardDetailAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        System.out.println("BoardDetailAction");
        
        // request 객체로부터 전달받은 글 번호(board_num), 페이지 번호(page) 가져오기
        // => Service 객체 또는 다음 페이지에 전달하기 위해 변수에 저장(DB 조회 용도로 사용하기 위해 글 번호를 정수형으로 형변환)
        //       페이지 번호는 조회에서는 필요없고, 다음 페이지 표시할 때 사용하기 때문에 정수형 변환이 필요없음
        int board_num = Integer.parseInt(request.getParameter("board_num"));
        String page = request.getParameter("page");
        
        // num, page 확인
//        System.out.println("board_num : " + board_num + ", page : " + page);
        
        // Service 객체 생성 및 getArticle() 메서드 호출하여 게시물 상세 내용 가져오기(글 번호 전달) => BoardBean 객체에 저장
        BoardDetailService boardDetailService = new BoardDetailService();
        BoardBean article = boardDetailService.getArticle(board_num);
        
        // ActionForward 객체 생성
        ActionForward forward = new ActionForward();
        
        // 다음 페이지에 전달할 데이터를 request.setAttribute() 메서드로 저장 => page, BoardBean 객체
        request.setAttribute("page", page);
        request.setAttribute("article", article);
        
        // ActionForward 객체의 setPath() 사용하여 포워딩 할 페이지 지정(board 디렉토리의 qna_board_view.jsp 페이지)
        forward.setPath("/board/qna_board_view.jsp");
        
        return forward; // ActionForward 객체 리턴
    }

}





















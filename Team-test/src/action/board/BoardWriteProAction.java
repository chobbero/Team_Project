package action.board;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.board.BoardWriteProService;
import vo.ActionForward;
import vo.BoardBean;

/*
 * qna_board_write.jsp 페이지에서 등록 버튼 클릭했을 때
 * BoardWritePro.bo 주소를 요청하면 BoardFrontController 에서
 * BoardWriteProAction 클래스 파일로 요청을 전달
 * 
 * 파일 업로드를 위해 cos.jar 파일을 WEB-INF 폴더의 lib 폴더에 복사 필요
 */
public class BoardWriteProAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        System.out.println("BoardWriteProAction!");
        
        // 최종적으로 리턴할 ActionForward 객체 생성을 위한 변수 선언
        ActionForward forward = null;
        // 전달할 데이터를 보관할 자바빈(DTO)객체 BoardBean 변수 선언
        BoardBean boardBean = null;
        
        String realFolder = ""; // 파일이 업로드 되는 실제 경로
        String saveFolder = "/boardUpload"; // 파일 업로드 할 논리적 경로(webcontent 폴더 내에 생성 필요)
        
        int fileSize = 5 * 1024 * 1024; // 한 번에 업로드 할 파일 크기(5KB)
        
        // 서버상의 실제 경로 가져오기
        ServletContext context = request.getServletContext(); // 컨텍스트 객체 가져오기
        System.out.println("1"+context);
        realFolder = context.getRealPath(saveFolder); // 논리적 경로에 대한 실제 경로 가져오기
        
        // 파일 업로드를 위한 MultiPartRequest 객체 생성하여 전달받은 요청 객체 정보 가져오기
        // (request 객체, 실제 경로, 파일 크기, 인코딩타입, RenamePolicy 객체)
        MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

        // 게시판에서 글 작성 시 전달된 모든 데이터를 BoardBean 객체에 저장(이름, 패스워드, 제목, 내용, 파일)
        boardBean = new BoardBean(); // BoardBean 객체 생성
        // MultiPartRequest 객체로부터 전달받은 데이터를 input 태그에서 지정한 이름을 통해 가져와서 BoardBean 객체에 저장
        boardBean.setBoard_name(multi.getParameter("board_name"));
        boardBean.setBoard_pass(multi.getParameter("board_pass"));
        boardBean.setBoard_subject(multi.getParameter("board_subject"));
        boardBean.setBoard_content(multi.getParameter("board_content"));
        boardBean.setBoard_file(multi.getOriginalFileName((String)multi.getFileNames().nextElement())); // 파일명 읽어와서 문자열로 변환하여 전달
        
        // Service 객체를 생성한 후 실제 DB 작업을 처리하도록 BoardBean 객체 전달
        BoardWriteProService boardWriteProService = new BoardWriteProService();
        boolean isWriteSuccess = boardWriteProService.registArticle(boardBean); // 글쓰기 수행 후 결과값을 리턴받음
        
        // isWriteSuccess 가 false 일 경우 글쓰기 실패 처리, true 일 경우 글쓰기 성공 처리
        if(!isWriteSuccess) { // 글쓰기 실패할 경우
            // 자바스크립트를 활용하여 "등록 실패!" 경고창 출력한 뒤 이전 페이지(글쓰기 페이지)로 이동
            // => 자바 클래스 파일에서 자바스크립트 사용하는 방법 : response 객체로부터 출력 객체인 PrintWriter 객체를 얻어와서 스크립트 출력
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter(); // 출력 객체 가져오기
            // 자바스크립트 코드를 out.println() 메서드를 통해 전달
            out.println("<script>");
            out.println("alert('등록 실패!')"); // 경고창 출력하기
            out.println("history.back()"); // 이전 페이지로 이동
            out.println("</script>");
        } else { // 글쓰기 성공할 경우
            // 자바 클래스에서 다른 페이지로 이동을 요청할 경우 Redirect 방식으로 포워딩해야함
            // ActionForward 객체 생성한 뒤 Redirect 방식으로 포워딩하도록 설정 = setRedirect() 메서드를 사용하여 true 전달
            forward = new ActionForward();
            forward.setRedirect(true); // Redirect 방식 설정
            // ActionForward 객체의 setPath() 메서드를 사용하여 이동할 페이지 지정 => BoardList.bo
            forward.setPath("BoardList.bo");
        }
        
        return forward; // ActionForward 객체 리턴 => BoardFrontController 로 리턴
    }

}


























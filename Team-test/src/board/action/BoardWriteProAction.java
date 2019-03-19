package board.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.action.Action;
import board.service.BoardWriteProService;
import board.vo.*;

public class BoardWriteProAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        BoardBean boardBean = null;

        String realFolder = ""; // 파일이 업로드 되는 실제 경로
        String saveFolder = "/files"; // 파일 업로드 할 논리적 경로(webcontent 폴더 내에 생성 필요)

        int fileSize = 50 * 1024 * 1024; // 한 번에 업로드 할 파일 크기(5KB)

        // 서버상의 실제 경로 가져오기
        ServletContext context = request.getServletContext(); // 컨텍스트 객체 가져오기
        System.out.println(context);
        realFolder = context.getRealPath(saveFolder); // 논리적 경로에 대한 실제 경로 가져오기

        // (request 객체, 실제 경로, 파일 크기, 인코딩타입, RenamePolicy 객체)
        MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
                new DefaultFileRenamePolicy());

        List<FileBean> fileBeanList = new ArrayList<FileBean>();

        Enumeration fileNames = multi.getFileNames();
        
        while (fileNames.hasMoreElements()) {
            String param = (String)fileNames.nextElement();
            String fileName = multi.getOriginalFileName(param);
            if(fileName == null) {
                continue;
            }
            FileBean file = new FileBean();
            file.setImage(fileName);
            fileBeanList.add(file);
        }
        
        boardBean = new BoardBean();

        
        boardBean.setUser_id(multi.getParameter("user_id"));
        boardBean.setStore_num(Integer.parseInt(multi.getParameter("store_num")));
        boardBean.setBoard_subject(multi.getParameter("board_subject"));
        boardBean.setBoard_rating(Double.parseDouble(multi.getParameter("board_rating")));
        boardBean.setBoard_content(multi.getParameter("board_content"));
        

        // Service 객체를 생성한 후 실제 DB 작업을 처리하도록 BoardBean 객체 전달
        BoardWriteProService boardWriteProService = new BoardWriteProService();
        boolean isWriteSuccess = boardWriteProService.registArticle(boardBean, fileBeanList);

        // isWriteSuccess 가 false 일 경우 글쓰기 실패 처리, true 일 경우 글쓰기 성공 처리
        if (!isWriteSuccess) { // 글쓰기 실패할 경우
            // 자바스크립트를 활용하여 "등록 실패!" 경고창 출력한 뒤 이전 페이지(글쓰기 페이지)로 이동
            // => 자바 클래스 파일에서 자바스크립트 사용하는 방법 : response 객체로부터 출력 객체인 PrintWriter 객체를 얻어와서
            // 스크립트 출력
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter(); // 출력 객체 가져오기
            // 자바스크립트 코드를 out.println() 메서드를 통해 전달
            out.println("<script>");
            out.println("alert('등록 실패!')"); // 경고창 출력하기
            out.println("history.back()"); // 이전 페이지로 이동
            out.println("</script>");
        } else { // 글쓰기 성공할 경우
            // 자바 클래스에서 다른 페이지로 이동을 요청할 경우 Redirect 방식으로 포워딩해야함
            // ActionForward 객체 생성한 뒤 Redirect 방식으로 포워딩하도록 설정 = setRedirect() 메서드를 사용하여 true
            // 전달
            forward = new ActionForward();
            forward.setRedirect(true); // Redirect 방식 설정
            // ActionForward 객체의 setPath() 메서드를 사용하여 이동할 페이지 지정 => BoardList.bo
            forward.setPath("BoardWriteForm.bo");
        }

        return forward; // ActionForward 객체 리턴 => BoardFrontController 로 리턴
    }

}

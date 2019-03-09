package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardDeleteProAction;
import action.BoardDetailAction;
import action.BoardListAction;
import action.BoardModifyFormAction;
import action.BoardModifyProAction;
import action.BoardReplyFormAction;
import action.BoardReplyProAction;
import action.BoardWriteProAction;
import vo.ActionForward;

/*
 * 모든 클라이언트의 요청을 받아서 제어할 컨트롤러 클래스(= 초기 진입점)
 * => 모든 요청은 반드시 FrontController 를 통해 제어됨 
 *
 * HttpServlet 클래스를 상속받는 서브클래스 타입으로 정의
 * => doGet(), doPost() 오버라이딩  및 doProcess() 메서드 직접 정의 또는 service() 메서드 오버라이딩
 *       => Get 방식의 요청은 doGet() 메서드를 통해 전달받고,
 *             Post 방식의 요청은 doPost() 메서드를 통해 전달받는다
 *             이 때, 호출하는 방식의 차이만 있을 뿐 수행할 작업은 동일하므로
 *             doProcess() 메서드에서 모든 작업을 처리(중복 제거)하고, 
 *             doGet() 메서드와 doPost() 메서드는 doProcess() 메서드를 호출하여 객체 전달하면 된다!
 */

/*
 * 자바의 Annotation(@) 기능을 활용하여 web.xml 에 설정했던 서블릿 매핑(URL패턴 매핑)을 대체 가능
 * => @WebServlet("URL패턴명")
 *       => 해당 패턴이 매칭되는 주소는 모두 현재 컨트롤러 클래스에서 처리하도록 함!
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get 방식의 요청이 전달될 경우 자동으로 호출되는 메서드
        doProcess(request, response); // 공통 기능을 수행하는 doProcess() 메서드에 요청, 응답 객체 전달
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Post 방식의 요청이 전달될 경우 자동으로 호출되는 메서드
        doProcess(request, response); // 공통 기능을 수행하는 doProcess() 메서드에 요청, 응답 객체 전달
    }
    
    // doGet() 메서드와 doPost() 메서드로부터 요청, 응답 객체를 전달받아 공통적으로 컨트롤을 수행할 doProcess() 메서드 정의
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Post 방식으로 전달되는 요청에 대한 한글 문자 처리
        
        /*
         * 주소창에 입력되는 URL 에 대한 매핑을 위해 필요한 부분(XXX.bo 등)만 추출하기 위해
         * HttpServletRequest 객체로부터 주소 정보 추출하는 작업
         * ex) http://localhost:8080/MVC_Board/BoardWriteForm.bo 일 때
         *       => 앞의 주소까지를 제외하고 "/프로젝트명/페이지명" 부분을 추출한 후
         *             프로젝트명까지를 제외한 나머지 페이지명 또는 디렉토리 부분만 매핑에 사용하기 위함
         */
        String requestURI = request.getRequestURI(); // "/프로젝트명/페이지명" 추출됨 => ex) "/MVC_Board/BoardWriteForm.bo"
        String contextPath = request.getContextPath(); // "/프로젝트명" 추출됨 => ex) "/MVC_Board"
        // 위의 추출된 문자열을 활용하여 URI 에서 contextPath 만큼을 제외한 나머지 문자열(매핑할 주소)만 추출 
        // => String 클래스의 substring() 메서드 사용
        // => contextPath 길이에 해당하는 URI 인덱스 부분부터 문자열 추출(ex. contextPath 가 10글자일 경우 URI 의 10번 인덱스부터 추출)
        String command = requestURI.substring(contextPath.length()); // "/BoardWriteForm.bo"
        
        System.out.println(requestURI);
        System.out.println(contextPath);
        System.out.println(command);
        
        // 각 요청을 처리하는 Action 인터페이스, ActionForward 클래스 변수 선언
        Action action = null;
        ActionForward forward = null;
        
        // substring() 메서드를 통해 추출된 서블릿매핑 주소부분(command 변수)을 
        // if문을 사용하여 판별 => 각각 요청에 맞는 페이지로 포워딩
        if(command.equals("/BoardMain.bo")) {
            // 별도의 비지니스 로직이 동작하지 않는 페이지로의 요청은 바로 JSP 페이지로 포워딩
            // ActionForward 객체 생성 후 setPath() 메서드를 사용하여 이동할 페이지 지정
            forward = new ActionForward();
            forward.setPath("./index.jsp"); // 최상위 폴더의 index.jsp 파일 지정
        } else if(command.equals("/BoardWriteForm.bo")) {
            // board 폴더내의 qna_board_write.jsp 파일로 포워딩
            forward = new ActionForward();
            // 최상위 폴더 -> board 폴더 내의 qna_board_write.jsp 파일 지정 
            forward.setPath("./board/qna_board_write.jsp"); 
        } else if(command.equals("/BoardWritePro.bo")) {
//            System.out.println("/BoardWritePro.bo 입력됨");
            // Action 클래스를 사용하여 요청을 처리해야하므로 jsp 페이지 포워딩과 코드가 다름
            // BoardWriteProAction 클래스의 인스턴스를 생성하여 Action 타입으로 리턴받음
            // => XXXAction 클래스는 모두 Action 인터페이스를 구현하므로 다형성 활용 가능
            action = new BoardWriteProAction(); // BoardWriteProAction -> Action 으로 업캐스팅
            
            // Action 클래스의 execute() 메서드를 호출하여 request(요청), response(응답) 객체를 전달한 뒤
            // 요청된 작업을 처리한 후 ActionForward 객체를 리턴 => throws 키워드를 통해 예외처리가 위임되어있음
            try {
                forward = action.execute(request, response); // BoardWriteProAction 객체의 execute() 메서드 호출
                // execute() 메서드 실행 후 ActionForward 객체를 리턴받아 포워딩
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/BoardList.bo")) {
            action = new BoardListAction();
            
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/BoardDetail.bo")) {
            action = new BoardDetailAction();
            
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(command.equals("/BoardDeleteForm.bo")) {
            // board 폴더내의 qna_board_delete.jsp 파일로 포워딩
            // 단, 글 삭제를 위한 게시물 번호(board_num)와 글 삭제 후 페이징 처리를 위한 페이지번호(page)를 가져와서 request.setAttribute() 메서드로 저장하여 이동
            int board_num = Integer.parseInt(request.getParameter("board_num"));
            String page = request.getParameter("page");
            
            request.setAttribute("board_num", board_num);
            request.setAttribute("page", page);
            
            forward = new ActionForward();
            // 최상위 폴더 -> board 폴더 내의 qna_board_delete.jsp 파일 지정 
            forward.setPath("./board/qna_board_delete.jsp"); 
            
        } else if(command.equals("/BoardDeletePro.bo")) {
            action = new BoardDeleteProAction();
            
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else if(command.equals("/BoardModifyForm.bo")) {
            action = new BoardModifyFormAction();
            
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else if(command.equals("/BoardModifyPro.bo")) {
            action = new BoardModifyProAction();
            
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else if(command.equals("/BoardReplyForm.bo")) {
            action = new BoardReplyFormAction();
            
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else if(command.equals("/BoardReplyPro.bo")) {
            action = new BoardReplyProAction();
            
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  
        
        
        // ActionForward 객체 정보를 사용하여 포워딩 처리
        // isRedirect() 메서드 실행 결과가 true 일 경우 Redirect 방식으로 포워딩
        //                   ""                     false 일 경우 Dispatch 방식으로 포워딩
        // forward 객체가 비어있지 않을 경우 포워딩 방식을 구분하여 포워딩 작업 수행
        if(forward != null) {
            if(forward.isRedirect()) { // true 일 경우 redirect 방식으로 포워딩
                // HttpServletResponse(응답) 객체의 sendRedirect() 메서드를 사용하여 포워딩
                // => jsp 페이지로 바로 이동하지 않고, 특정 Action 클래스로 이동하기 위해 isRedirect 값을 true 로 변경하여 Redirect 방식으로 포워딩 => *.bo 로 이동 
                response.sendRedirect(forward.getPath()); // ActionForward 객체의 path 변수값 사용
                System.out.println("redirect 방식으로 포워딩 : " + forward.getPath());
            } else { // false 일 경우 dispatch 방식으로 포워딩
                // HttpServletRequest(요청) 객체의 getRequestDispatcher() 메서드를 사용하여
                // RequestDispatcher 인터페이스 타입 객체 리턴받아 포워딩(요청, 응답 객체 전달)
                // => jsp 페이지로 바로 이동하는 경우 => *.jsp 로 이동
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
                System.out.println("dispatch 방식으로 포워딩 : " + forward.getPath());
            }
        }
        
        
        
        
        
        
    }
    
}



























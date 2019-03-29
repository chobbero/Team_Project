package main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.BoardWriteProAction;
import main.action.Action;
import main.action.MainAction;
import main.vo.ActionForward;

@WebServlet("/main")
public class MainController extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String requestURI = request.getRequestURI(); 
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        
        System.out.println(command);
        
        ActionForward forward = null;
        
        Action action = null;
        action = new MainAction(); 
        
        try {
            forward = action.execute(request, response); 
        } catch (Exception e) {
            e.printStackTrace();
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

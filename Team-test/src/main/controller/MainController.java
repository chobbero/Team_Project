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
        
        if(forward != null) {
            if(forward.isRedirect()) { 
                response.sendRedirect(forward.getPath());
                System.out.println("redirect 방식으로 포워딩 : " + forward.getPath());
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
                System.out.println("dispatch 방식으로 포워딩 : " + forward.getPath());
            }
        }
    }
}

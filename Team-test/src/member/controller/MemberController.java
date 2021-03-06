package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.action.Action;
import member.action.UserJoinProAction;
import member.action.UserLoginProAction;
import member.vo.ActionForward;

@WebServlet("*.mb")
public class MemberController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        System.out.println(command);

        Action action = null;
        ActionForward forward = null;

        if (command.equals("/UserJoinForm.mb")) {
            forward = new ActionForward();
            forward.setPath("./member/join.jsp");
        } else if (command.equals("/main.mb")) {
            forward = new ActionForward();
            forward.setPath("./main.jsp");
        } else if (command.equals("/UserJoinPro.mb")) {
            action = new UserJoinProAction();

            try {
                forward = action.execute(request, response); // BoardWriteProAction 객체의 execute() 메서드 호출
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (command.equals("/UserLoginForm.mb")) {
            forward = new ActionForward();
            forward.setPath("./member/login.jsp");
        } else if (command.equals("/UserLoginPro.mb")) {
            action = new UserLoginProAction();
            
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
        
        if (forward != null) {
            if (forward.isRedirect()) {
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

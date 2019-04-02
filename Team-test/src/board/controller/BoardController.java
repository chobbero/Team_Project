package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.Action;
import board.action.BoardDeleteProAction;
import board.action.BoardDetailAction;
import board.action.BoardListAction;
import board.action.BoardUpdateFormAction;
import board.action.BoardUpdateProAction;
import board.action.BoardWriteProAction;
import board.action.SearchAction;
import board.vo.ActionForward;
import common.db.SessionCheck;


@WebServlet("*.bo")
public class BoardController extends HttpServlet {

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

        Action action = null;
        ActionForward forward = null;
        
        // 세션이 필요한 행동
        if(SessionCheck.SessionCheck(request)) {
        	
        	if(command.equals("/BoardWriteForm.bo")) {
                forward = new ActionForward();
                forward.setPath("./board/boardWrite.jsp"); 
                
            } else if(command.equals("/BoardWritePro.bo")) {
                action = new BoardWriteProAction(); 
                
                try {
                    forward = action.execute(request, response); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            } else if(command.equals("/BoardUpdateForm.bo")) {
                action = new BoardUpdateFormAction();
                
                try {
                    forward = action.execute(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(command.equals("/BoardUpdatePro.bo")) {
                action = new BoardUpdateProAction();
                
                try {
                    forward = action.execute(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(command.equals("/BoardDelete.bo")) {
                action = new BoardDeleteProAction();
                
                try {
                    forward = action.execute(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
        } else {
            forward = new ActionForward();
            request.setAttribute("msg", "로그인이 필요합니다");
            forward.setPath("/member/msg.jsp");
        }

        // 세션이 필요없는 행동
		if (command.equals("/BoardList.bo")) {
			action = new BoardListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardDetail.bo")) {
        	// board_num 유무 확인 및 이동
        	if (request.getParameter("board_num") != null && request.getParameter("board_num").trim().length() != 0 && !request.getParameter("board_num").trim().equals("")) {
        		action = new BoardDetailAction();
	            
	            try {
	                forward = action.execute(request, response);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
        	} else {
        		forward = new ActionForward();
                forward.setPath("/BoardList.bo");
        	}
        } else if(command.equals("/Search.bo")) {
        	// 검색어 유무에 따른 페이지 이동
        	if ((request.getParameter("search_input") != null && request.getParameter("search_input").trim().length() != 0 && !request.getParameter("search_input").trim().equals("")) || 
        		(request.getParameter("store_category") != null && request.getParameter("store_category").trim().length() != 0 && !request.getParameter("store_category").trim().equals("")))
        	{
	        	action = new SearchAction();
	            
	            try {
	                forward = action.execute(request, response);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
        	} else {
                forward = new ActionForward();
                forward.setPath("/search.jsp");
        	}
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



























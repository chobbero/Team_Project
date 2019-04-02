package common.db;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionCheck {

	public static boolean SessionCheck(HttpServletRequest request) {
		boolean check = false;
        HttpSession session = request.getSession(false); // (false) = 세션 존재시 기존 세션리턴, 없으면 null 리턴
        
        // 세션 "user_id" 확인
        if(session.getAttribute("user_id") != null) {
        	System.out.println("현재 세션 ID : " + session.getAttribute("user_id"));
        	return check = true;
        }
        return check;
	}
}

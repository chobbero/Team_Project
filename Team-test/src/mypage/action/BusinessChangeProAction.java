package mypage.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.service.StoreInsert;
import mypage.vo.StoreBean;
import member.service.BusinessJoin;
import member.service.UserGradeUpdate;
import member.vo.MemberBean;
import mypage.vo.ActionForward;
import mypage.vo.BusinessMemberBean;

public class BusinessChangeProAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MemberBean memberBean = new MemberBean();
        StoreBean storeBean = new StoreBean();
        BusinessMemberBean businessMemberBean = new BusinessMemberBean();
        ActionForward forward = new ActionForward();
        
        StoreInsert storeInsert = new StoreInsert();
        int maxNum = storeInsert.store_maxNum();
        
        
        
        
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
        
        storeBean.setStore_num(maxNum);
        storeBean.setStore_name(multi.getParameter("business_name"));
        storeBean.setStore_address(multi.getParameter("store_address"));
        storeBean.setStore_category(multi.getParameter("store_category"));
        storeBean.setStore_menu(multi.getParameter("store_menu"));
        storeBean.setStore_price(Integer.parseInt(multi.getParameter("store_price")));
        storeBean.setStore_time(multi.getParameter("store_time"));
        storeBean.setStore_contact(multi.getParameter("store_contact"));
        storeBean.setStore_cooperation("Y");
        storeBean.setStore_image(multi.getOriginalFileName((String)multi.getFileNames().nextElement())); 
        // 파일명 읽어와서 문자열로 변환하여 전달
        
        storeInsert.store_insert(storeBean);
        
        // 비즈니스 회원 입력
        businessMemberBean.setStore_num(maxNum);
        businessMemberBean.setBusiness_name(multi.getParameter("business_name"));
        businessMemberBean.setBusiness_number(multi.getParameter("business_number"));
        businessMemberBean.setUser_id(multi.getParameter("user_id"));
        
        BusinessJoin businessJoin = new BusinessJoin();
        businessJoin.BusinessJoin(businessMemberBean);
        
        // 회원 등급 변경
        memberBean.setUser_id(multi.getParameter("user_id"));
        memberBean.setUser_grade(multi.getParameter("user_grade"));
        
        UserGradeUpdate userGradeUpdate = new UserGradeUpdate();
        userGradeUpdate.userGradeUpdate(memberBean);
        
        HttpSession session = request.getSession();
        session.invalidate();
        
        forward.setPath("./UserJoinForm.mb");
        forward.setRedirect(true);
        return forward;
    }

}

package mypage.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.vo.StoreBean;
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
        
        storeBean.setStore_image(multi.getOriginalFileName((String)multi.getFileNames().nextElement())); 
        // 파일명 읽어와서 문자열로 변환하여 전달
        
        return forward;
    }

}

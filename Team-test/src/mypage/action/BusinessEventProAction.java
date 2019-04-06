package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mypage.service.BusinessEventProService;
import mypage.vo.ActionForward;
import mypage.vo.BusinessBoardBean;
import mypage.vo.BusinessFile;

public class BusinessEventProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

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

		List<BusinessFile> businessFileList = new ArrayList<BusinessFile>();

		Enumeration fileNames = multi.getFileNames();

		while (fileNames.hasMoreElements()) {
			String param = (String) fileNames.nextElement();
			String fileName = multi.getOriginalFileName(param);
			if (fileName == null) {
				continue;
			}
			BusinessFile file = new BusinessFile();
			file.setStore_num(Integer.parseInt(multi.getParameter("store_num")));
			file.setImage(fileName);
			businessFileList.add(file);
		}

		BusinessBoardBean businessBoardBean = new BusinessBoardBean();
		businessBoardBean.setUser_id(multi.getParameter("user_id"));
		businessBoardBean.setStore_num(Integer.parseInt(multi.getParameter("store_num")));
		businessBoardBean.setBoard_subject(multi.getParameter("board_subject"));
		businessBoardBean.setBoard_content(multi.getParameter("board_content"));

		BusinessEventProService businessEventProService = new BusinessEventProService();
		boolean isWriteSuccess = businessEventProService.registArticle(businessBoardBean, businessFileList);

		if (!isWriteSuccess) { 
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			out.println("<script>");
			out.println("alert('등록 실패!')"); 
			out.println("history.back()"); 
			out.println("</script>");
		} else { 
			forward = new ActionForward();
			
			forward.setRedirect(true); 
			
			forward.setPath("./BusinessBoardList.bo");
		}

		return forward;
	}

}

package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardBusinessDetailService;
import board.vo.ActionForward;
import board.vo.StoreBean;
import mypage.vo.BusinessBoardBean;
import mypage.vo.BusinessFile;

public class BoardBusinessDetailAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        
        int store_num = Integer.parseInt(request.getParameter("store_num"));
        
        BoardBusinessDetailService boardBusinessDetailService = new BoardBusinessDetailService();
        StoreBean storeBean =  boardBusinessDetailService.getStoreInfo(store_num);
        BusinessBoardBean boardBean = boardBusinessDetailService.getBoardArticle(store_num);
        ArrayList<BusinessFile> fileList = boardBusinessDetailService.getImgFileList(store_num);
        
        forward = new ActionForward();
        
        request.setAttribute("storeBean", storeBean);
        request.setAttribute("boardBean", boardBean);
        request.setAttribute("fileList", fileList);
        
        forward.setPath("./board/boardList_business_detail.jsp");
        
        return forward;
    }

}

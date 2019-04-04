package board.dao;

import static common.db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sun.org.apache.regexp.internal.recompile;

import board.vo.BoardBean;
import board.vo.FileBean;
import board.vo.ListBean;
import board.vo.StoreBean;

public class BoardDAO {
    Connection con;
    DataSource ds;

    private static BoardDAO instance;

    private BoardDAO() {
    }

    public static BoardDAO getInstance() {
        if (instance == null) {
            instance = new BoardDAO();
        }
        return instance; // 생성된 인스턴스 리턴
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    // 글쓰기
    public int insertArticle(BoardBean article, List<FileBean> fileBeanList) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int num = 0;
        int insertCount = 0;

        String sql = "";

        try {

            sql = "SELECT MAX(board_num) FROM board";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                num = rs.getInt(1) + 1;
            } else {
                num = 1;
            }

            sql = "INSERT INTO board VALUES(?,?,?,?,?,?,?,now(),?)";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num); // 글번호(위에서 계산됨)
            pstmt.setString(2, article.getUser_id());
            pstmt.setInt(3, article.getStore_num());
            pstmt.setString(4, article.getBoard_subject()); // 글제목
            pstmt.setString(5, article.getBoard_content()); // 글내용
            pstmt.setDouble(6, article.getBoard_rating());
            pstmt.setInt(7, 0);
            pstmt.setInt(8, 0);

            insertCount = pstmt.executeUpdate();

            for (int i = 0; i < fileBeanList.size(); i++) {

                sql = "INSERT INTO board_file VALUES(?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, num);
                pstmt.setString(2, fileBeanList.get(i).getImage());
                pstmt.executeUpdate();

            }

        } catch (SQLException e) {
            System.out.println("insertArticle() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return insertCount;
    }

    // board list 보여주기
    public int getBoardListCount() {
        int listCount = 0; // 총 게시물 수 저장 변수

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT COUNT(*) FROM board";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                listCount = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("getBoardListCount() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }

        return listCount;
    }

    // store,board 합친 List
    public ArrayList<ListBean> getList(int page, int limit) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ListBean> list = new ArrayList<ListBean>();

        int startRow = (page - 1) * 10;

        try {
            String sql = "SELECT * FROM board b JOIN store s ON (b.store_num = s.store_num) LIMIT ?,?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, limit);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ListBean listBean = new ListBean();

                listBean.setBoard_num(rs.getInt("board_num"));
                listBean.setUser_id(rs.getString("user_id"));
                listBean.setBoard_subject(rs.getString("board_subject"));
                listBean.setBoard_content(rs.getString("board_content"));
                listBean.setBoard_rating(rs.getDouble("board_rating"));
                listBean.setBoard_like(rs.getInt("board_like"));
                listBean.setBoard_date(rs.getDate("board_date"));
                listBean.setBoard_readcount(rs.getInt("board_readcount"));
                listBean.setStore_num(rs.getInt("store_num"));
                listBean.setStore_name(rs.getString("store_name"));
                listBean.setStore_address(rs.getString("store_address"));
                listBean.setStore_category(rs.getString("store_category"));
                listBean.setStore_menu(rs.getString("store_menu"));
                listBean.setStore_price(rs.getInt("store_price"));
                listBean.setStore_time(rs.getString("store_time"));
                listBean.setStore_image(rs.getString("store_image"));
                listBean.setStore_contact(rs.getString("store_contact"));

                list.add(listBean);
            }
        } catch (SQLException e) {
            System.out.println("getList() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return list;
    }

    // 랭킹 리스트
    public ArrayList<ListBean> getListRank(int page, int limit) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ListBean> list = new ArrayList<ListBean>();

        int startRow = (page - 1) * 10;

        try {
            String sql = "SELECT * FROM board b JOIN store s ON (b.store_num = s.store_num) ORDER BY b.board_like desc LIMIT ?,?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, limit);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ListBean listBean = new ListBean();

                listBean.setBoard_num(rs.getInt("board_num"));
                listBean.setUser_id(rs.getString("user_id"));
                listBean.setBoard_subject(rs.getString("board_subject"));
                listBean.setBoard_content(rs.getString("board_content"));
                listBean.setBoard_rating(rs.getDouble("board_rating"));
                listBean.setBoard_like(rs.getInt("board_like"));
                listBean.setBoard_date(rs.getDate("board_date"));
                listBean.setBoard_readcount(rs.getInt("board_readcount"));
                listBean.setStore_num(rs.getInt("store_num"));
                listBean.setStore_name(rs.getString("store_name"));
                listBean.setStore_address(rs.getString("store_address"));
                listBean.setStore_category(rs.getString("store_category"));
                listBean.setStore_menu(rs.getString("store_menu"));
                listBean.setStore_price(rs.getInt("store_price"));
                listBean.setStore_time(rs.getString("store_time"));
                listBean.setStore_image(rs.getString("store_image"));
                listBean.setStore_contact(rs.getString("store_contact"));

                list.add(listBean);
            }
        } catch (SQLException e) {
            System.out.println("getList() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return list;
    }

    // 매장의 후기 갯수 조회
    public int getBoardCount(int store_num) {

        int boardCount = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT COUNT(*) FROM board where store_num = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, store_num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                boardCount = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("getBoardCount() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return boardCount;

    }

    // 게시물 조회수 증가
    public int updateReadcount(int board_num) {

        PreparedStatement pstmt = null;
        int updateCount = 0;

        String sql = "UPDATE board SET board_readcount = board_readcount + 1 WHERE board_num = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            updateCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateReadcount() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
        }
        return updateCount;

    }

    // 게시물 내용 가져오기
    public BoardBean selectBoardArticle(int board_num) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardBean boardBean = null;

        try {
            String sql = "SELECT * FROM board WHERE board_num = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                boardBean = new BoardBean();

                boardBean.setBoard_num(rs.getInt("board_num"));
                boardBean.setUser_id(rs.getString("user_id"));
                boardBean.setStore_num(rs.getInt("store_num"));
                boardBean.setBoard_subject(rs.getString("board_subject"));
                boardBean.setBoard_content(rs.getString("board_content"));
                boardBean.setBoard_rating(rs.getDouble("board_rating"));
                boardBean.setBoard_like(rs.getInt("board_like"));
                boardBean.setBoard_date(rs.getDate("board_date"));
                boardBean.setBoard_readcount(rs.getInt("board_readcount"));
            }

        } catch (SQLException e) {
            System.out.println("selectBoardArticle() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return boardBean;

    }

    // 매장 정보 가져오기
    public StoreBean getStoreInfo(int store_num) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StoreBean storeBean = null;

        try {
            String sql = "SELECT * FROM store WHERE store_num = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, store_num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                storeBean = new StoreBean();

                storeBean.setStore_num(store_num);
                storeBean.setStore_name(rs.getString("store_name"));
                storeBean.setStore_address(rs.getString("store_address"));
                storeBean.setStore_category(rs.getString("store_category"));
                storeBean.setStore_menu(rs.getString("store_menu"));
                storeBean.setStore_price(rs.getInt("store_price"));
                storeBean.setStore_time(rs.getString("store_time"));
                storeBean.setStore_image(rs.getString("store_image"));
                storeBean.setStore_contact(rs.getString("store_contact"));
            }

        } catch (SQLException e) {
            System.out.println("getStoreInfo() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return storeBean;

    }

    // 이미지 파일 리스트 가져오기
    public ArrayList<String> getImgFileList(int board_num) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<String> imgFileList = new ArrayList<String>();

        try {
            String sql = "SELECT * FROM board_file WHERE board_num = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String imgFileName = rs.getString("image");

                imgFileList.add(imgFileName);
            }

        } catch (SQLException e) {
            System.out.println("getImgFileList() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return imgFileList;

    }

    public int getImgFileCount(int board_num) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int imgFileCount = 0;

        try {
            String sql = "SELECT COUNT(*) FROM board_file where board_num = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                imgFileCount = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("getImgFileCount() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return imgFileCount;
    }

    // 검색 게시물 갯수 조회
    public int getSearchCount(String search, String category) {

        int SearchCount = 0;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        if (category.length() == 0 || category.equals(null) || category == null) {
            return SearchCount = -1;
        }

        try {

            String sql = "";

            switch (category) {
                case "subject": // 글제목
                    sql = "SELECT COUNT(*) FROM board WHERE board_subject like ?";
                    break;
                case "content": // 글내용
                    sql = "SELECT COUNT(*) FROM board WHERE board_content like ?";
                    break;
                case "store": // 상호명
                    sql = "SELECT COUNT(*) FROM store WHERE store_name like ?";
                    break;
            }

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + search + "%");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                SearchCount = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("SearchCount() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }

        return SearchCount;
    }

    // 검색 목록 조회(게시글)
    public ArrayList<BoardBean> getSearchList(String search, String category) {

        ArrayList<BoardBean> SearchList = new ArrayList<BoardBean>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "";

            switch (category) {
                case "subject": // 글제목 검색
                    sql = "SELECT * FROM board WHERE board_subject like ?";
                    break;
                case "content": // 글내용 검색
                    sql = "SELECT * FROM board WHERE board_content like ?";
                    break;
            }

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + search + "%");
            rs = pstmt.executeQuery();

            // 카테고리에 따라 다른 리스트 전달
            while (rs.next()) {
                BoardBean boardBean = new BoardBean();

                boardBean.setBoard_num(rs.getInt("board_num"));
                boardBean.setUser_id(rs.getString("user_id"));
                boardBean.setBoard_subject(rs.getString("board_subject"));
                boardBean.setBoard_content(rs.getString("board_content"));
                boardBean.setBoard_rating(rs.getDouble("board_rating"));
                boardBean.setBoard_like(rs.getInt("board_like"));
                boardBean.setBoard_date(rs.getDate("board_date"));
                boardBean.setBoard_readcount(rs.getInt("board_readcount"));

                SearchList.add(boardBean);

            }
        } catch (SQLException e) {
            System.out.println("getSearchList() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }

        return SearchList;
    }

    // 검색 목록 조회 (매장)
    public ArrayList<StoreBean> getSearchList2(String search) {

        ArrayList<StoreBean> SearchList = new ArrayList<StoreBean>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM store WHERE store_name like ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + search + "%");
            rs = pstmt.executeQuery();

            // 카테고리에 따라 다른 리스트 전달
            while (rs.next()) {
                StoreBean storeBean = new StoreBean();

                storeBean.setStore_num(rs.getInt("store_num"));
                storeBean.setStore_name(rs.getString("store_name"));
                storeBean.setStore_address(rs.getString("store_address"));
                storeBean.setStore_category(rs.getString("store_category"));
                storeBean.setStore_menu(rs.getString("store_menu"));
                storeBean.setStore_price(rs.getInt("store_price"));
                storeBean.setStore_time(rs.getString("store_time"));
                storeBean.setStore_image(rs.getString("store_image"));
                storeBean.setStore_contact(rs.getString("store_contact"));

                SearchList.add(storeBean);
            }
        } catch (SQLException e) {
            System.out.println("getSearchList2() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return SearchList;

    }

    // 검색 게시물 이미지 파일명 가져오기 (한장)
    public FileBean getImgFileName(int board_num) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FileBean fileBean = null;

        try {
            String sql = "SELECT * FROM board_file WHERE board_num = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                fileBean = new FileBean();

                fileBean.setBoard_num(rs.getInt("board_num"));
                fileBean.setImage(rs.getString("image"));

            }

        } catch (SQLException e) {
            System.out.println("getImgFileName() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return fileBean;

    }

    // 후기 많은 TOP 10 의 맛집 리스트 가져오기
    public ArrayList<StoreBean> getRankList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<StoreBean> rankList = new ArrayList<StoreBean>();

        try {

            String sql = "SELECT * FROM store s ORDER BY (SELECT count(*) FROM board b WHERE b.store_num = s.store_num) DESC LIMIT 10";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StoreBean StoreBean = new StoreBean();

                StoreBean.setStore_num(rs.getInt("store_num"));
                StoreBean.setStore_name(rs.getString("store_name"));
                StoreBean.setStore_address(rs.getString("store_address"));
                StoreBean.setStore_category(rs.getString("store_category"));
                StoreBean.setStore_menu(rs.getString("store_menu"));
                StoreBean.setStore_price(rs.getInt("store_price"));
                StoreBean.setStore_time(rs.getString("store_time"));
                StoreBean.setStore_image(rs.getString("store_image"));
                StoreBean.setStore_contact(rs.getString("store_contact"));

                rankList.add(StoreBean);
            }
        } catch (SQLException e) {
            System.out.println("getRankList() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return rankList;
    }

    // TOP1 맛집의 '좋아요' 가장 높은 게시물 내용 가져오기
    public BoardBean selectTopBoardArticle(int topStoreNum) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardBean boardBean = null;

        try {
            String sql = "SELECT * FROM board WHERE store_num = ? ORDER by board_like DESC LIMIT 1";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, topStoreNum);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                boardBean = new BoardBean();

                boardBean.setBoard_num(rs.getInt("board_num"));
                boardBean.setUser_id(rs.getString("user_id"));
                boardBean.setStore_num(rs.getInt("store_num"));
                boardBean.setBoard_subject(rs.getString("board_subject"));
                boardBean.setBoard_content(rs.getString("board_content"));
                boardBean.setBoard_rating(rs.getDouble("board_rating"));
                boardBean.setBoard_like(rs.getInt("board_like"));
                boardBean.setBoard_date(rs.getDate("board_date"));
                boardBean.setBoard_readcount(rs.getInt("board_readcount"));
            }

        } catch (SQLException e) {
            System.out.println("selectTopBoardArticle() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return boardBean;

    }

    public int updateBoard(BoardBean boardBean) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = "update board set board_subject = ?, board_content = ? where board_num = ? and user_id = ?";
            pstmt = con.prepareStatement(sql);

            System.out.println(boardBean.getUser_id());
            pstmt.setString(1, boardBean.getBoard_subject());
            pstmt.setString(2, boardBean.getBoard_content());
            pstmt.setInt(3, boardBean.getBoard_num());
            pstmt.setString(4, boardBean.getUser_id());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateBoard() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int deleteBoard(int board_num, String user_id) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = "DELETE FROM board WHERE board_num = ? AND user_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            pstmt.setString(2, user_id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public boolean userCheck(String id, String pass) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        boolean result = false;

        try {
            String sql = "SELECT * FROM user WHERE user_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                if (pass.equals(rs.getString("user_pass")))
                    ;
                {
                    result = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("userCheck() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
        return result;
    }

    // 메인에서 이동 맛집카테고리 클릭
    public int getSearchCount(String storeCategory) {
        int SearchCount = 0;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT count(*) FROM board b JOIN store s ON (b.store_num = s.store_num) WHERE s.store_category = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, storeCategory);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                SearchCount = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("SearchCount() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }

        return SearchCount;
    }

    public ArrayList<BoardBean> getSearchList(String storeCategory) {
        ArrayList<BoardBean> SearchList = new ArrayList<BoardBean>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM board b JOIN store s ON (b.store_num = s.store_num) WHERE s.store_category = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, storeCategory);
            rs = pstmt.executeQuery();

            // 카테고리에 따라 다른 리스트 전달
            while (rs.next()) {
                BoardBean boardBean = new BoardBean();

                boardBean.setBoard_num(rs.getInt("board_num"));
                boardBean.setUser_id(rs.getString("user_id"));
                boardBean.setBoard_subject(rs.getString("board_subject"));
                boardBean.setBoard_content(rs.getString("board_content"));
                boardBean.setBoard_rating(rs.getDouble("board_rating"));
                boardBean.setBoard_like(rs.getInt("board_like"));
                boardBean.setBoard_date(rs.getDate("board_date"));
                boardBean.setBoard_readcount(rs.getInt("board_readcount"));

                SearchList.add(boardBean);
            }
        } catch (SQLException e) {
            System.out.println("getSearchList() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }

        return SearchList;
    }
    
    // 가게 번호 구하기
    public int store_maxNum() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int maxNum = 0;

        try {
            String sql = "select max(store_num) from store";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxNum = rs.getInt(1) + 1 ;
            } else {
                maxNum = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rs);
        }
        return maxNum;
    }
    
    // 가게 입력
    public int store_insert(mypage.vo.StoreBean storeBean) {
        PreparedStatement pstmt = null;
        int isSuccess = 0;
        try {
            String sql = "INSERT INTO "
                    + "store(store_num, store_name, store_address, store_category, store_menu, store_price, store_time, store_image,store_contact,store_cooperation)  "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, storeBean.getStore_num());
            pstmt.setString(2, storeBean.getStore_name());
            pstmt.setString(3, storeBean.getStore_address());
            pstmt.setString(4, storeBean.getStore_category());
            pstmt.setString(5, storeBean.getStore_menu());
            pstmt.setInt(6, storeBean.getStore_price());
            pstmt.setString(7, storeBean.getStore_time());
            pstmt.setString(8, storeBean.getStore_image());
            pstmt.setString(9, storeBean.getStore_contact());
            pstmt.setString(10, storeBean.getStore_cooperation());
            
            isSuccess = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return isSuccess;
    }
}

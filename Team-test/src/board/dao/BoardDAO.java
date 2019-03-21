package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import static common.db.JdbcUtil.*;
import board.vo.BoardBean;
import board.vo.CommentBean;
import board.vo.FileBean;
import board.vo.StoreBean;

public class BoardDAO {
    Connection con;
    DataSource ds;
    
   private static BoardDAO instance;
    
    private BoardDAO() {} 

    public static BoardDAO getInstance() {
        if(instance == null) {
            instance = new BoardDAO();
        }
        return instance; // 생성된 인스턴스 리턴
    }
    
    public void setConnection(Connection con) {
        this.con = con;
    }

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
            
            if(rs.next()) {
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
            
            for(int i = 0; i < fileBeanList.size(); i++) {

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
        
        return insertCount; // BoardWriteProService 객체로 리턴
        
    } // insertArticle() 끝
    
    // 총 게시물 수 계산하여 리턴
    public int selectListCount() {
        int listCount = 0; // 총 게시물 수 저장 변수
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // board 테이블의 모든 레코드 갯수 조회
            String sql = "SELECT COUNT(*) FROM board";
            
            // SQL 구문 전달 및 실행
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            // 조회 결과가 있을 경우 listCount 에 조회된 결과값 저장
            if(rs.next()) {
                listCount = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println("selectListCount() 에러 : " + e.getMessage());
        } finally {
            // pstmt, rs 자원 반환
            close(pstmt);
            close(rs);
        }
        
        // listCount 리턴
        return listCount;
    } // selectListCount() 끝
    
    // 게시물 목록을 조회하여 리턴
//    public ArrayList<BoardBean> selectArticleList(int page, int limit) {
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        
//        BoardBean boardBean = null;
//        ArrayList<BoardBean> articleList = new ArrayList<BoardBean>(); // 게시물 목록을 저장할 객체
//        
//        int startRow = (page - 1) * 10; // 읽어올 첫번째 행 번호 계산
//        
//        try {
//            // 모든 게시물 조회(단, board_re_ref 기준 내림차순 및 board_re_seq 기준 오름차순 정렬, startRow 부터 limit 갯수만큼 조회)
//            String sql = "SELECT * FROM board ORDER BY board_re_ref DESC,board_re_seq ASC LIMIT ?,?";
//            
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, startRow);
//            pstmt.setInt(2, limit);
//            rs = pstmt.executeQuery();
//            
//            // while문을 사용하여 조회 결과에 대한 처리 반복
//            while(rs.next()) {
//                // 게시물을 1개 저장할 BoardBean 객체 생성
//                boardBean = new BoardBean();
//                
//                // ResultSet 객체로부터 데이터를 가져와서 BoardBean 객체에 저장
//                boardBean.setBoard_num(rs.getInt("board_num"));
//                boardBean.setBoard_name(rs.getString("board_name"));
//                boardBean.setBoard_subject(rs.getString("board_subject"));
//                boardBean.setBoard_content(rs.getString("board_content"));
//                boardBean.setBoard_file(rs.getString("board_file"));
//                boardBean.setBoard_re_ref(rs.getInt("board_re_ref"));
//                boardBean.setBoard_re_lev(rs.getInt("board_re_lev"));
//                boardBean.setBoard_re_seq(rs.getInt("board_re_seq"));
//                boardBean.setBoard_readcount(rs.getInt("board_readcount"));
//                boardBean.setBoard_date(rs.getDate("board_date"));
//                
//                // BoardBean 객체를 ArrayList 객체인 articleList 변수에 저장
//                articleList.add(boardBean);
//            }
//            
//        } catch (SQLException e) {
//            System.out.println("selectArticleList() 에러 : " + e.getMessage());
//        } finally {
//            close(pstmt);
//            close(rs);
//        }
//        
//        return articleList;
//    } // selectArticleList() 끝
//    
    
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
 			
 			if(rs.next()) {
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
            
            if(rs.next()) {
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
            
            if(rs.next()) {
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
	
	// 게시글의 댓글 갯수 조회
	public int getCommentCount(int board_num) {
		
		int commentCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM board_comment where board_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				commentCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("getCommentCount() 에러 : " + e.getMessage());
		} finally {
			close(pstmt);
			close(rs);
		}
		return commentCount;
		
	}

	// 댓글 리스트 가져오기
	public ArrayList<CommentBean> getBoardComment(int board_num) {
		
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
        CommentBean commentBean = null;
        
        try {
            String sql = "SELECT * FROM board_comment WHERE board_num = ?";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, board_num);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	commentBean = new CommentBean();
                
            	commentBean.setComment_num(rs.getInt("comment_num"));
            	commentBean.setUser_id(rs.getString("user_id"));
            	commentBean.setBoard_num(board_num);
            	commentBean.setComment_content(rs.getString("comment_content"));
            	commentBean.setComment_date(rs.getDate("comment_date"));
            	commentBean.setComment_like(rs.getString("comment_like"));
            	
            	commentList.add(commentBean);
            }
            
        } catch (SQLException e) {
            System.out.println("getBoardComment() 에러 : " + e.getMessage());
        } finally {
            close(pstmt);
            close(rs);
        }
		return commentList;
		
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
            
            while(rs.next()) {
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


    
    
}

















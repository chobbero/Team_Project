package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// JDBC 관련(접속, 자원반환, commit, rollback) 기능을 수행하는 메서드를 정의한 클래스
// => 모든 메서드는 인스턴스 생성없이 접근 가능하도록 static 메서드로 선언한다!
public class JdbcUtil {
    
    public static Connection getConnection() {
        Connection con = null;
        
        try {
            // 커넥션 풀에서 Connection 객체 가져와서 리턴
            Context initCtx = new InitialContext(); // 톰캣으로부터 컨텍스트 객체 리턴받음
            Context envCtx = (Context) initCtx.lookup("java:comp/env"); // context.xml 에 정의된 Resource 정의 컨텍스트 가져오기
            DataSource ds = (DataSource) envCtx.lookup("jdbc/MySQL"); // context.xml 에 정의된 Resource 에서 DataSource 객체 가져오기
                                                                                                          // => Rosource 태그내의 name="XXXX" 부분을 정확하게 지정해야함
            con = ds.getConnection(); // 지정된 커넥션풀로부터 Connection 객체 가져오기
            
            con.setAutoCommit(false); // DB 작업(트랜잭션) 자동 커밋 해제
            
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return con;
        
    }
    
    public static void close(Connection con) {
        // Connection 객체를 전달받아 자원 반환
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(Statement stmt) {
        // Statement 객체를 전달받아 자원 반환
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(ResultSet rs) {
        // ResultSet 객체를 전달받아 자원 반환
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void commit(Connection con) {
        // 전달받은 Connection 객체를 사용하여 commit() 작업 수행
        try {
            con.commit();
            System.out.println("Commit success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void rollback(Connection con) {
        // 전달받은 Connection 객체를 사용하여 rollback() 작업 수행
        try {
            con.rollback();
            System.out.println("Rollback success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}




















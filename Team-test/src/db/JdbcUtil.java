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
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/MySQL");
            con = ds.getConnection();
            con.setAutoCommit(false);

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;

    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection con) {
        try {
            con.commit();
            System.out.println("Commit success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection con) {
        try {
            con.rollback();
            System.out.println("Rollback success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.db.JdbcUtil"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String id = request.getParameter("id");
    int board_num = Integer.parseInt(request.getParameter("board_num"));
    int output = 0;

    Connection con = JdbcUtil.getConnection();
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    String sql = "select * from pick where user_id=? and board_num=?;";
    pstmt = con.prepareStatement(sql);
    pstmt.setString(1, id);
    pstmt.setInt(2, board_num);
    rs = pstmt.executeQuery();

    if (rs.next()) {
        pstmt.close();
        rs.close();
    } else {
        sql = "insert into pick values(?,?)";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setInt(2, board_num);
        output = pstmt.executeUpdate();
        pstmt.close();
        rs.close();
    }
    if (output == 1) {
        JdbcUtil.commit(con);
    } else if (output == 0) {
        JdbcUtil.rollback(con);
    }

    JdbcUtil.close(con);
    
    JSONObject result = new JSONObject();
    result.put("output", output);
    PrintWriter pw = response.getWriter();
    pw.print(result);
    pw.flush();
    pw.close();
%>
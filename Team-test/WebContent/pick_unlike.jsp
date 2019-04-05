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

    Connection con = JdbcUtil.getConnection();
    
    String sql = "delete from pick where user_id=? and board_num=?";
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setString(1, id);
    pstmt.setInt(2, board_num);
    int output = pstmt.executeUpdate();
    JdbcUtil.commit(con);
    JdbcUtil.close(con);
    
    JSONObject result = new JSONObject();
    result.put("output", output);
    PrintWriter pw = response.getWriter();
    pw.print(result);
    pw.flush();
    pw.close();
%>
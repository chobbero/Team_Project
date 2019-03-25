<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String id = request.getParameter("id");

    Class.forName("com.mysql.jdbc.Driver");

    String dbUrl = "jdbc:mysql://localhost:3306/team_project";
    String dbUser = "team_project";
    String dbPass = "1234";

    Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

    String sql = "select * from user where user_id=?";
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setString(1, id);
    //4단계 rs = 실행 결과 저장
    ResultSet rs = pstmt.executeQuery();
    JSONObject result = new JSONObject();
    if (rs.next()) {
        result.put("user_id", rs.getString("user_id"));
        PrintWriter pw = response.getWriter();
        pw.print(result);
        pw.flush();
        pw.close();
    }
%>

<%=result %>
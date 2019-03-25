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
    String nickname = request.getParameter("nickname");

    Class.forName("com.mysql.jdbc.Driver");

    String dbUrl = "jdbc:mysql://localhost:3306/team_project";
    String dbUser = "team_project";
    String dbPass = "1234";

    Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

    String sql = "select * from user where user_nickname=?";
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setString(1, nickname);
    //4단계 rs = 실행 결과 저장
    ResultSet rs = pstmt.executeQuery();
    JSONObject result = new JSONObject();
    if (rs.next()) {
        result.put("nickname", rs.getString("user_nickname"));
        PrintWriter pw = response.getWriter();
        pw.print(result);
        pw.flush();
        pw.close();
    }
%>

<%=result %>
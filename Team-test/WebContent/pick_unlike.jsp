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
    int board_num = Integer.parseInt(request.getParameter("board_num"));

    Class.forName("com.mysql.jdbc.Driver");
    //2단계 디비연결
    String dbUrl = "jdbc:mysql://localhost:3306/team_project";
    String dbUser = "team_project";
    String dbPass = "1234";
    Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
    //3단계 sql select 
    String sql = "delete from pick where user_id=? and board_num=?";
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setString(1, id);
    pstmt.setInt(2, board_num);
    //4단계 rs = 실행 결과 저장
    int output = pstmt.executeUpdate();
    JSONObject result = new JSONObject();
    result.put("output", output);
    // 5단계 rs => json변경해서 저장
    // MemberBean => JSONObject
    PrintWriter pw = response.getWriter();
    pw.print(result);
    pw.flush();
    pw.close();
%>
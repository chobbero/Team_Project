<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 데이터베이스 member 테이블에 id,pass,name 가져오기
// JDBC  mysql-connector-java-5.1.45-bin.jar
// json 바꾸는 프로그램  json-simple-1.1.1.jar

//1단계 드라이버로더
Class.forName("com.mysql.jdbc.Driver");
//2단계 디비연결
String dbUrl="jdbc:mysql://localhost:3306/jspdb5";
String dbUser="jspid";
String dbPass="jsppass";
Connection con=DriverManager.getConnection(dbUrl,dbUser,dbPass);
//3단계 sql select 
String sql="select id,pass,name from member";
PreparedStatement pstmt=con.prepareStatement(sql);
//4단계 rs = 실행 결과 저장
ResultSet rs=pstmt.executeQuery();
// 5단계 rs => json변경해서 저장
// ArrayList => JSONArray
// MemberBean => JSONObject
JSONArray mbList=new JSONArray();
while(rs.next()){
	JSONObject mb=new JSONObject();
	mb.put("id", rs.getString("id"));
	mb.put("pass",rs.getString("pass"));
	mb.put("name",rs.getString("name"));
	//배열한칸 저장
	mbList.add(mb);
}
%>
<%=mbList %>







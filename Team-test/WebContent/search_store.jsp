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
// 데이터베이스 member 테이블에 id,pass,name 가져오기
// JDBC  mysql-connector-java-5.1.45-bin.jar
// json 바꾸는 프로그램  json-simple-1.1.1.jar

String searchValue = request.getParameter("searchValue"); 

// String searchValue2 = "노르웨이";

//1단계 드라이버로더
Class.forName("com.mysql.jdbc.Driver");
//2단계 디비연결
String dbUrl="jdbc:mysql://localhost:3306/team_project";
String dbUser="team_project";
String dbPass="1234";
Connection con=DriverManager.getConnection(dbUrl,dbUser,dbPass);
//3단계 sql select 
String sql="select store_num, store_name, store_address from store where store_name like ?";
PreparedStatement pstmt=con.prepareStatement(sql);
pstmt.setString(1, "%"+searchValue+"%");
//4단계 rs = 실행 결과 저장
ResultSet rs=pstmt.executeQuery();
// 5단계 rs => json변경해서 저장
// ArrayList => JSONArray
// MemberBean => JSONObject
JSONArray storeList=new JSONArray();
while(rs.next()){
	JSONObject store=new JSONObject();
	store.put("store_num", rs.getInt("store_num"));
	store.put("store_name",rs.getString("store_name"));
	store.put("store_address",rs.getString("store_address"));
	//배열한칸 저장
	storeList.add(store);
}

PrintWriter pw = response.getWriter();
pw.print(storeList);
pw.flush();
pw.close();

%>

<%=storeList %>






<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String board_num = request.getParameter("board_num");
// 데이터 베이스를 연결하고 sql구문을 실행해서, id와 board_num을 이용해서 
// update 각 회원의 좋아요를 업데이트 한다.
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="jspstudy.domain.MemberVo" %>
	    <%@page import="jspstudy.service.MemberDao" %>
    <% String memberpwd = (String)session.getAttribute("memberpwd"); %> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>비밀번호찾기화면</title>
<style>
@import url("../Dubek/css/index.css");
</style>
</head>
<body>
<section>
  <div style="width:50%">
    <nav><a href="<%=request.getContextPath() %>/home/index.do">Home</a></nav>
    <nav><a href="<%=request.getContextPath() %>/board/boardList.do">게시판</a></nav>
    <nav><a href="<%=request.getContextPath() %>/notice/noticeList.do">공지사항</a></nav>
    <nav><a href="<%=request.getContextPath() %>/member/memberLogin.do">로그인</a></nav>
  </div>
</section>
<table align="center">
<thead><tr><td>비밀번호 찾기</td></tr></thead>
<tbody><tr><td><%=memberpwd %></td></tr></tbody>
<tfoot><tr><td>
<button type="button"onclick="<%=request.getContextPath() %>/member/memberLogin.do">로그인 화면</button></td></tr></tfoot>
</table>
</body>
</html>
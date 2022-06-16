<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="jspstudy.domain.MemberVo" %>
	    <%@page import="jspstudy.service.MemberDao" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
<style type="text/css">
  @import url("../Dubek/css/index.css");
</style>
</head>
<body style="text-align:center">
<section>
  <div style="width:50%">
    <nav><a href="<%=request.getContextPath() %>/member/MyPage.do">마이페이지</a></nav>
    <nav><a href="<%=request.getContextPath() %>/board/boardList.do">게시판</a></nav>
    <nav><a href="<%=request.getContextPath() %>/notice/noticeList.do">공지사항</a></nav>
    <nav><a href="<%=request.getContextPath() %>/member/memberLogout.do">로그아웃</a></nav>
  </div>
</section>
<br><br><br><br><br><br><br><br><br><br><br><br>
<table align="center">
<thead><tr><td>마이페이지</td></tr></thead>
<tbody><tr><td><%
if(session.getAttribute("midx") != null) {
	out.println("회원 아이디(이메일) :"+ session.getAttribute("memberEmail" )+"<br>");    // list 창 위에 해당 회원의 아이디를 출력함.
	out.println("회원 이름 :"+ session.getAttribute("memberName")+ "<br>");  // list 창 위에 해당 회원의 이름을 출력함.
	out.println("회원 거주지 :"+ session.getAttribute("memberAddr")+ "<br>");
	out.println("회원 전화번호 :"+ session.getAttribute("memberPhone")+ "<br>");
	out.println("회원 성별 :"+ session.getAttribute("memberGender")+ "<br>");
	out.println("회원 가입일 :"+ session.getAttribute("writeday")+ "<br>");
}
%>

</td></tr></tbody>
<tfoot><tr><td>
</table>
</body>
</html>
<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import ="jspstudy.domain.*" %>
    <%@page import="java.util.*" %>
<%
             ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");
%>
	<%
if(session.getAttribute("memberRank") == null) {
	out.println("<script>alert('※!!등급이 낮아 열람이 불가능합니다!!※');location.href='"+request.getContextPath()+"/home/index.do'</script>");
	}
	%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>memberList</title>
<style type="text/css">
  @import url("../Dubek/css/index.css");
  </style>

</head>
<body>
<section>
  <div style="width:50%">
    <nav><a href="<%=request.getContextPath() %>/member/MyPage.do">마이페이지</a></nav>
    <nav><a href="<%=request.getContextPath() %>/board/boardList.do">게시판</a></nav>
    <nav><a href="<%=request.getContextPath() %>/notice/noticeList.do">공지사항</a></nav>
    <nav><a href="<%=request.getContextPath() %>/member/memberLogout.do">로그아웃</a></nav>
  </div>
</section>

	<form name="frm">
	<h4 style="text-align:right; margin-right:400px;">등급 : <%=session.getAttribute("memberRank") %></h4>
<table border="1"  style= "width:800px" align="center">
<tr style="color:green;">
<td>midx번호</td>
<td>이름</td>
<td>전화번호</td>
<td>회원가입일</td>
</tr>
<% for (MemberVo mv : alist){ %>
<tr>
<td><%=mv.getMidx()%></td>
<td><%=mv.getMembername()%></td>
<td><%=mv.getMemberphone()%></td>
<td><%=mv.getWriteday() %></td>

</tr>
<% } %>
</table>
</form>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="jspstudy.domain.*"  %>  
<%@ page import="java.util.*" %>  
<%
	ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if (session.getAttribute("midx") != null){
	out.println("회원아이디:"+session.getAttribute("memberId")+"<br>");
	out.println("회원이름:"+session.getAttribute("memberName")+"<br>");
	
	out.println("<a href='"+request.getContextPath()+"/member/memberLogout.do'>로그아웃</a><br>");
}

%>
<h1>회원 목록 만들기</h1>
<table border="1" style="width:800px">
<tr style="color:green;">
<td>midx번호</td>
<td>이름</td>
<td>전화번호</td>
<td>작성일</td>
</tr>
<% for (MemberVo mv : alist){ %>
<tr>
<td><%=mv.getMidx() %></td>
<td><%=mv.getMembername()%></td>
<td><%=mv.getMemberphone() %> </td>
<td><%=mv.getWriteday() %></td>
</tr>
<% } %>

</table>
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
</body>
</html>
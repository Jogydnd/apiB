<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jspstudy.domain.*" %>
<% NoticeVo nv =(NoticeVo)request.getAttribute("nv"); %>

<%=session.getAttribute("memberRank") %>
	<%
if(session.getAttribute("memberRank") == null) {
	out.println("<script>location.href='"+request.getContextPath()+"/notice/noticeContent2.do?nidx="+nv.getNidx()+"'</script>");
	}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 보기</title>
</head>
<body>
<h1>공지사항</h1>
<table border=1 style="width:1000px;">
<tr>
<td style = "width:250px;">제목 &nbsp;&nbsp;(날짜 : <%=nv.getWriteday().substring(0,10) %>)</td>
<td><%=nv.getSubject() %></td>   <!-- 해당 글의 제목을 가져오는 코드 -->
</tr>
<tr style="height:500px;">
<td>내용</td>
<td>
<%=nv.getContent() %>
</td>
</tr>
<tr>
<td style = "width:100px;">작성자</td>
<td style="height:20px">
운영자
</td>
</tr>
<tr>
<td colspan="2" style= "text-align:right;">
<input type="button" name="btn" value="수정" onclick="location.href='<%=request.getContextPath()%>/notice/noticeModify.do?nidx='<%=nv.getNidx() %>">      
<input type="button" name="btn" value="목록" onclick="location.href='<%=request.getContextPath()%>/notice/noticeList.do'"> <!-- 목록 버튼을 누를 경우 공지사항 목록으로 넘어가도록 수행하는 코드 -->
</td>
</tr>

</table>
</body>
<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	    <%@page import="jspstudy.domain.BoardVo" %>
	    <%@page import="jspstudy.service.BoardDao" %>
    <% BoardVo bv = (BoardVo)request.getAttribute("bv"); %> 
    	<%
	if(session.getAttribute("midx") == null) {
		out.println("<script>alert('로그인 해주세요.');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
	}
	%>   <!-- 글쓰기 페이지이기 때문에 회원번호 필수 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 수정하기</title>
<script type="text/javascript">


function check(){  
	var fm = document.frm;	
	//페이지 이동
	 fm.action = "<%=request.getContextPath()%>/board/boardModifyAction.do";     
	 fm.method = "post";
	 fm.submit();
	return;
}

</script>
<style type="text/css">
 @import url("../Dubek/css/barddd.css");
</style>

</head>
<body>
<section>
  <div>
    <nav><a href="<%=request.getContextPath() %>/home/index.do">Home</a></nav>
    <nav><a href="<%=request.getContextPath() %>/board/boardList.do">게시판</a></nav>
    <nav><a href="<%=request.getContextPath() %>/notice/noticeList.do">공지사항</a></nav>
     <nav><a href="<%=request.getContextPath() %>/member/memberLogout.do">로그아웃</a></nav>
  </div>
</section>

<h1 style="text-align:center;">게시판 수정하기</h1>
<form name="frm">
<input type="hidden"  name="bidx"  value=<%=bv.getbidx()%>> <!-- 타입 hiddent을 통해 페이지 정보 감추기. bv 객체를 참조하여 bidx를 받을 수 있다. -->
<table border=1 align="center" style="width:900px; height:600px; ">
<tr>
<td style="width:100px">제목</td>
<td><input type="text" name="subject" size="100" value=<%=bv.getsubject() %>></td>   <!-- 위에서 선언했던 bv 객체를 참조하여 subject를 가져올 수 있다. -->
</tr>
<tr>
<td>내용</td>
<td><textarea class="form-control" name="content" cols="100" rows="30" 
							><%=bv.getcontent() %></textarea></td>         <!-- 위에서 선언했던 bv 객체를 참조하여 content 가져오기 -->
</tr>
<tr>
<td>작성자</td>
<td><input type="text" name="writer" size="30" value=<%=bv.getwriter()%> readonly="readonly" readonly="readonly" ></td>     <!--  위에서 선언했던 bv 객체를 참ㅈ하여 writer 가져오기 -->
</tr>
<tr>
<td colspan=2 style= "text-align:right;">
<input type="button" name="btn" value="수정하기" onclick="check();"></td>
</tr>
</table>
</form>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="jspstudy.domain.BoardVo" %>    
<%
	BoardVo bv = (BoardVo)request.getAttribute("bv");

if (session.getAttribute("midx") == null){
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정쓰기</title>

<script>
  function check(){
  //	alert("테스트");
  	
  	var fm = document.frm;
  	
  	if (fm.subject.value ==""){
  		alert("제목을 입력하세요");
  		fm.subject.focus();
  		return;
  	}else if (fm.content.value ==""){
  		alert("내용을 입력하세요");
  		fm.content.focus();
  		return;
  	}else if (fm.writer.value ==""){
  		alert("작성자를 입력하세요");
  		fm.writer.focus();
  		return;
  	}
  		fm.action = "<%=request.getContextPath()%>/board/boardModifyAction.do";
		fm.method = "post";
		fm.submit();
	
  }
</script>
</head>
<body>
<h1>게시판 수정하기</h1>
<table border=1 style="width:800px;">
<form name="frm">
<input type="hidden" name="bidx" value="<%=bv.getBidx() %>">
<tr>
<td style="width:100px">제목</td>
<td><input type="text" name="subject" size="50" value="<%=bv.getSubject() %>"></td>
</tr>
<tr>
<td>내용</td>
<td>
<textarea name="content" placeholder="내용을 입력해주세요" cols="80" rows="10">
<%=bv.getContent() %>
</textarea>

</td>
</tr>
<tr>
<td>작성자</td>
<td><input type="text" name="writer" size="50" value="<%=bv.getWriter() %>"></td>
</tr>
<tr>
<td colspan=2 style="text-align:center;">
<input type="button" name="btn" value="확인" onclick="check();">
<input type="reset" name="reset" value="취소">
</td>
</tr>
</form>
</table>
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
</body>
</html>
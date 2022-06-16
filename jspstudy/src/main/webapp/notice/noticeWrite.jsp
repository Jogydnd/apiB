<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%
	if(session.getAttribute("midx") == null) {
		
		session.setAttribute("saveUrl", request.getRequestURI());    // 1
		out.println("<script>alert('로그인 해주세요.');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
	}
	%> <!-- 글쓰기 페이지이기 때문에 회원번호 필수 -->
	<%
if(session.getAttribute("memberRank") == null) {
	out.println("<script>alert('※!!등급이 낮아 공지사항 작성이 불가능합니다!!※');location.href='"+request.getContextPath()+"/home/index.do'</script>");
	}
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공지사항 작성</title>
<script type="text/javascript">

function check(){   // 유효성 검사 
	var fm = document.frm;
	 if(fm.subject.value=="") {  // 제목이 기입되지 않았을 경우       
		 alert("제목을 입력해주세요.");
		 fm.subject.focus();
		 return;	 
	 }
	 else if(fm.content.value=="") {   // 내용이 기입되지 않았을 경우
		 alert("내용을 입력해주세요.");
		 fm.content.focus();
		 return;
	 }
	// 페이지 이동
	 fm.action = "<%=request.getContextPath()%>/notice/noticeWriteAction.do";
	 fm.method = "post";
	 fm.submit();
	return;
}

</script>
<style>
 @import url("../Dubek/css/index.css");
</style>

</head>
<body>
<h1 style="text-align:center">공지사항 작성 페이지</h1>
<a style="text-align:left; margin-left: 300px;"href="<%=request.getContextPath() %>/home/index.do"><img alt="Dubek" src="../home/img/Home.ico"></a>
<section>
  <div style="width:50%">
    <nav><a href="<%=request.getContextPath() %>/member/MyPage.do">마이페이지</a></nav>
    <nav> <a href=" <%=request.getContextPath() %>/member/memberList.do">회원 리스트</a></nav>
    <nav><a href="<%=request.getContextPath() %>/board/boardList.do">게시판</a></nav>
    <nav><a href="<%=request.getContextPath() %>/notice/noticeList.do">공지사항</a></nav>
    <nav><a href="<%=request.getContextPath() %>/member/memberLogout.do">로그아웃</a></nav>
  </div>
</section>
<br><br>
<form name="frm">
<table border=1 style="width:900px; height: 600px;" align="center">
<tr>
<td style="width:100px">제목</td>
<td><input type="text" name="subject" size="100"></td>
</tr>
<tr>
<td>내용</td>
<td>
<textarea name="content" placeholder="내용을 입력해주세요." cols="100" rows="30">
</textarea>
</td>
</tr>
<tr>
<td>작성자</td>
<td><input type="text" name="writer" size="30" value="운영진" readonly="readonly" ></td>
</tr>
<tr>
<td colspan=2 style= "text-align:center;">
<input type="button" name="btn" value="확인" onclick="check();"></td>
</tr>
</table>
</form>
</body>
</html>
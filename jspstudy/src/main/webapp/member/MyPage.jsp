<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My page</title>
<script>
function check(){
	var fm = document.frm;
	if(fm.memberEmail.value=="") {
		alert("아이디(이메일)을 입력해주세요.");
		fm.memberEmail.focus();
		return;
	}
	alert("마이페이지로 넘어갑니다.");
	fm.action="<%=request.getContextPath()%>/member/MyPageAction.do"
	fm.submit();
	return;
}
</script>
<style>
  @import url("../Dubek/css/index.css");
</style>
</head>
<body style="text-align:center">
<section>
  <div style="width:50%">
   <nav><a href="<%=request.getContextPath() %>/home/index.do">Home</a></nav>
    <nav><a href="<%=request.getContextPath() %>/member/MyPage.do">마이페이지</a></nav>
    <nav><a href="<%=request.getContextPath() %>/board/boardList.do">게시판</a></nav>
    <nav><a href="<%=request.getContextPath() %>/notice/noticeList.do">공지사항</a></nav>
    <nav><a href="<%=request.getContextPath() %>/member/memberLogout.do">로그아웃</a></nav>
  </div>
</section>
<br><br><br><br><br><br><br><br><br>
<form name="frm">
<h3>본인 확인 절차</h3>
<input type="email" name="memberEmail" placeholder="이메일을 입력해주세요." size="50">
<input type="button" value="확인" onclick="check();"> 
</form>
</body>
</html>
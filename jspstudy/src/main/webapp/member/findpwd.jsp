<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>비밀번호찾기</title>
<script type="text/javascript">
function check() {
	var fm = document.frm;
	var memberemail = fm.memberemail.value;
	var membername = fm.membername.value;
	
	if(membername=="") {
		alert("이름을 입력해주세요.");
		membername.focus();
	return;
    }
    else if(memberemail=="") {
	alert("이메일을 입력해주세요.");
	memberemail.focus();
	return;
	}
   fm.action = "<%=request.getContextPath()%>/member/findpwdAction.do";
   fm.method = "post";
   fm.submit();
   alert("전송합니다.");
	 return;	
}
</script>
<style>
 @import url("../Dubek/css/index.css");
</style>
</head>
<body style="text-align:center;">
<section>
  <div style="width:50%">
    <nav><a href="<%=request.getContextPath() %>/home/index.do">Home</a></nav>
    <nav><a href="<%=request.getContextPath() %>/board/boardList.do">게시판</a></nav>
    <nav><a href="<%=request.getContextPath() %>/notice/noticeList.do">공지사항</a></nav>
    <nav><a href="<%=request.getContextPath() %>/member/memberLogin.do">로그인</a></nav>
  </div>
</section>
<form name="frm">
<h1>비밀번호 찾기</h1>
<br>
<p>비밀번호는 이름, 가입하신 아이디(이메일)을 통해 찾으실 수 있습니다.</p>
<div>

<input type="text" placeholder="이름" name="membername"></div>
<div>
<input type="email" placeholder="이메일" name="memberemail"></div>
<button type="button" onclick="check();"> 
	                 <span>비밀번호 찾기</span></button>
	</form>                                  
</body>
</html>
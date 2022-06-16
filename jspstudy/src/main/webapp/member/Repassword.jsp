<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>비밀번호찾기화면</title>
<style type="text/css">
 @import url("css/style.css");
h1, h4{
align: center;
}
#text{
margin-left:1em;
margin-right:1em;
height:30px;
width:80%;
}

</style>
<script type="text/javascript">
function check() {
	var fm = document.frm;
	var email = fm.email.value;
	
	if(email=="") {
		alert("이메일을 입력해주세요.");
		fm.Email.focus();
		return;
	}
	 fm.action = "<%=request.getContextPath()%>/member/RepasswordAction.do?memberemail";
	 fm.method = "post";
	 fm.submit();
	 return;	
}
</script>
</head>
<body>
<form action="" name="frm">

<h1>비밀번호 재설정</h1>
<div>
<h4>회원가입시 등록한 이메일을 입력해주세요.</h4>
<input type="email" id="text" name="memberemail" placeholder="이메일 주소" required="" class="required" value="" data-msg-required="이메일 주소를 확인해 주세요.">
</div>
<div class="btn_area">
<button type="sumit" id="btnJoin" onclick="check();"> 
	                 <span>비밀번호 찾기</span></button>
	       </div>
</form>
</body>
</html>
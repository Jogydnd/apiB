<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@page import="jspstudy.domain.MemberVo" %>
	    <%@page import="jspstudy.service.MemberDao" %>
	    <% MemberVo bv = (MemberVo)request.getAttribute("mv"); %> 
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>비밀번호 재설정</title>
<script type="text/javascript">



function check(){   // 유효성 검사 
	var fm = document.frm;	
	var Pwd = fm.password.value;
	var Pwd2 = fm.password2.value;
	
      if(Pwd==""){   // 비밀번호가 누락된 경우
		alert("비밀번호를 입력하세요.")
		fm.password.focus();
		return;
	}
	else if(Pwd.length <  6 || Pwd.length>15) {  // 비밀번호를 6~15자 이내로 입력하지 않은 경우
		alert("비밀번호는 6~ 15자 이내로 입력해야합니다.")
		fm.password.focus();
		return;
	}
	else if(Pwd2==""){
		alert("비밀번호 확인을 입력해주세요.")
		fm.password2.focus();
		return;
	}
	else if(Pwd!=Pwd2){   // 비밀번호와 비밀번호 확인이 일치하지 않을 경우
		alert("비밀번호가 일치하지 않습니다.");
		password2="";
		fm.password2.focus();
		return;
	}
	
	//페이지 이동
	 fm.action = "<%=request.getContextPath()%>/member/Repassword2Action.do";     
	 fm.method = "post";
	 fm.submit();
	return;
}
</script>
</head>
<body>
<form name="frm">
<input type="hidden" name="email" value=<%=mv.getMemberemail() %>> 
<div>
<h3>새 비밀번호</h3>
<input type="password" name="password">
</div>
<div>
<h3>비밀번호 확인</h3>
<input type="password" name="password2">
</div>
<div class="btn_area">
<button type="submit" id="btnJoin" onclick="check();">
	                 <span>비밀번호 재설정</span></button>
	       </div>
</form>
</body>
</html>
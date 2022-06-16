<<<<<<< HEAD

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML lang="en">
 <HEAD>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <TITLE> Login Page</TITLE>
 <link href="https://fonts.googleapis.com/css2?
      family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
 <script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>
 
 <script>
  function check(){   //유효성검사
//	 alert 알림 창("테스트입니다."); 
var fm = document.frm;  
	 if(fm.memberEmail.value=="") {  // 아이디가 기입되지 않았을 경우       
		 alert("아이디를 입력하세요.");
		 fm.memberEmail.focus();
		 return;	 
	 }
	 else if(fm.memberPwd.value=="") {   // 비밀번호가 기입되지 않았을 경우
		 alert("비밀번호를 입력하세요.");
		 fm.memberPwd.focus();
		 return;
	 }
	 alert("전송합니다.");    // 위에 조건을 모두 충족하고 확인 버튼을 클릭할 경우
	 fm.action = "<%=request.getContextPath()%>/member/memberLoginAction.do";
	 fm.method = "post";
	 fm.submit();
	 return;
  }
  </script>
  <style type="text/css">
    @import url("../Dubek/css/Login.css");
</style>
      
 </HEAD>
 <body>
    <div class="wrap">

        <div class="login">
            <form name="frm"> 
          <h2 >Log-in</h2>
           <div class="login_sns">
          <li><a href=""><i class="fab fa-instagram"></i></a></li>
          <li><a href=""><i class="fab fa-facebook-f"></i></a></li>
          <li><a href=""><i class="fab fa-twitter"></i></a></li>
 </div>
          <div class="login_id">
          <h4>E-mail</h4>
        <input type="email" name="memberEmail" id="" placeholder="Email">
 </div>
          <div class="login_pw">
           <h4>Password</h4>
        <input type="password" name="memberPwd" id="" placeholder="Password">
 </div>
          <div class="login_etc">
           <div class="checkbox">
           
                 <a href="<%=request.getContextPath() %>/member/memberJoin.do">회원가입</a>
  </div>
                <div class="forgot_pw">
                <a href="<%=request.getContextPath()%>/member/findpwd.do">Forgot Password?</a>
  </div>
  </div>
            <div class="submit">
                <input type="submit" value="submit" onclick="check();">
   </div>
                    </form>
 </div>
 </div>
</body>
</html>
       



=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<HTML>
 <HEAD>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE> New Document </TITLE> 
  <script>
  function check(){  
  var fm = document.frm;   
  if (fm.memberId.value==""){
  		alert("아이디를 입력해주세요");
  		fm.memberId.focus();
  		return;
  }else if (fm.memberPwd.value==""){
  		alert("비밀번호를 입력해주세요");
  		fm.memberPwd.focus();
  		return;
  }
  		alert("전송합니다..");  	
  		fm.action = "<%=request.getContextPath()%>/member/memberLoginAction.do";
  		fm.method = "post";
  		fm.submit();  
  
    return;
  }  
  </script>  
 </HEAD>

 <BODY>
<center><h1>로그인</h1></center>
<hr></hr>
<form name="frm"> 
 <table border="1" style="text-align:left;width:800px;height:300px">
<tr>
<td>아이디</td>
<td><input type="text" name="memberId" size="30"></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type="password" name="memberPwd" size="30"></td>
</tr>
<tr>
<td>확인</td>
<td>
<input type="button" value="확인" onclick="check();"> 
<input type="reset" value="리셋"> 
</td>
</tr>
 </table>
 </form>
 </BODY>
</HTML>
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git

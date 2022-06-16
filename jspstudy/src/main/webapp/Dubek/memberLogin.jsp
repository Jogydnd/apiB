<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<HTML>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>LogIn</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <style type="text/css">
  @import url("Dubek/css/Login.css");
  </style>

 </HEAD>
 <BODY>
<div class="container">
        <div id="imail">
            <i class="material-icons">person_outline</i>
        </div>
        <div id="ipw">
            <i class="material-icons">lock_outline</i>
        </div>
        	<h5><span>로그인</span> 페이지입니다.</h5>
        <hr />
        <form action="login.do" method="post">
            <input type="text" placeholder="아이디" name="id" required style="height:30px; width: 380px" /><br />
            <input type="password" placeholder="비밀번호" name="pw" required style="height:30px; width: 380px" /><br />
            <input type="submit" value="로그인" class="login" />
            <button onclick="location.href='index.do';" class="login" >HOME</button>
        </form>
        <hr />
        <p><a href="join.do"><input type="button" value="회원가입" id="signup" /></a></p>
    </div>
 </BODY>
</HTML>

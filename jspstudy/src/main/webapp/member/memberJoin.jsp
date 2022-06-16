<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>회원 가입</title>
    <style>
       @import url("../Dubek/css/Join.css");
    </style>
    <script src="../resources/js/jquery-3.6.0.min.js"></script>
    <script>
///-----------아이디(이메일)중복체크--------------
$(document).ready(function(){

	$("#checkEmail").click(function(){

		let memberEmail = $("#member_Email").val();

		$.ajax({ //ajax통신
			type: 'post', //데이터 보내는 방식
			url: "<%=request.getContextPath()%>/member/Join.do" , //서버 요청 페이지
			data: { "Email": memberEmail }, //서버로 보낼 데이터(JSON 형식)
			success: function(data) { //수신 성공시
				//서버로부터 수신한 응답내용인 data를 다시 자바스크립트 JSON객체로 파싱해줌
				let obj = JSON.parse(data);

				if(obj.flag == "N" ){//기존의 이메일 아이디와 중복이 안될 경우
					result = "사용 가능한 아이디입니다";
					$("#result_checkId").html(result).css("color", "green");
				} else {//중복될 경우
					result = "이미 사용중인 아이디입니다";
					$("#result_checkId").html(result).css("color", "red");
					$("#member_Email").val("").trigger("focus"); //trigger(): 강제로 이벤트 발생시킴
				}
			},
			error : function(error){ //에러 발생시
				alert(error);
			}
		});
    });
});
//-------------유효성 검사-----------------------
  function check(){   //유효성검사
//	 alert 알림 창("테스트입니다.");
var fm = document.frm;
var Email= fm.memberEmail.value;
var Pw = fm.memberPwd.value;
var Pw2 = fm.memberPwd2.value;
var Name = fm.memberName.value;
var Phone = fm.memberPhone.value;

	 if(Email =="") {  // 아이디가 기입되지 않았을 경우
		 alert("이메일을 입력하세요.");
		 fm.memberEmail.focus();
		 return;
	 }
	 else if(Pw =="") {   // 비밀번호가 기입되지 않았을 경우
		 alert("비밀번호를 입력하세요.");
		 fm.memberPwd.focus();
		 return;
	 }
	 else if(Pw2 =="") {   // 비밀번호 확인 칸이 비어 있을 경우
		 alert("비밀번호 확인를 입력하세요.");
		 fm.memberPwd2.focus();
		 return;
	 }
	 else if(Pw != Pw2) {     // 입력한 비밀번호와 비밀번호 확인이 다를 경우
		 alert("비밀번호가 일치하지 않습니다.");
		 fm.memberPwd2.focus();
		 return;
	 }
	 else if(Pw.length<6 || Pw.length>15){
		 alert("비밀번호를 6~15자 이내로 작성해주세요.");
		 fm.memberPwd.focus();
	 }
	 else if(Name =="") {   // 이름을 기입하지 않았을 경우
		 alert("이름을 입력하세요.");
		 fm.memberName.focus();
		 return;
	 }
	 else if(Phone =="") {    // 연락처를 입력하지 않았을 경우
			 alert("연락처를 입력해주세요.");
			 fm.memberPhone.focus();
			 return;
	 }
	 
	 alert("전송합니다.");    // 위에 조건을 모두 충족하고 확인 버튼을 클릭할 경우
	 fm.action = "<%=request.getContextPath()%>/member/memberJoinAction.do";
	 fm.method = "post";
	 fm.submit();
	 return;
  }
    </script>

</HEAD>
<BODY>
<div class="register">
    <form name="frm">
        <h3>회원가입</h3>
            <div class="flex">
                <ul class="container">
                    <li class="item center">
                        이름
                    </li>
                    <li class="item">
                        <input type="text" name="memberName" autofocus required>
                    </li>
                    <li class="item">

                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        이메일
                    </li>
                    <li class="item">
                        <input type="email" placeholder="이메일(아이디)를 입력하세요." name="memberEmail" id="member_Email" required>
                               <label id="result_checkId">
                                                   </label>
                    </li>
                    <li class="item">
                        <button class="idcheck" id="checkEmail">중복확인</button>
                        <br>
                 
                    </li>
                    <li>
                      
                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        비밀번호
                    </li>
                    <li class="item">
                        <input type="password" name="memberPwd" placeholder="비밀번호를 입력하세요." required>
                    </li>
                    <li class="item">

                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        비밀번호 확인
                    </li>
                    <li class="item">
                        <input type="password" name="memberPwd2" placeholder="비밀번호를 입력하세요." required>
                    </li>
                    <li class="item">

                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        거주중인 지역
                    </li>
                    <li class="item">
                        <select name="memberAddr">
                            <option value="선택안함">선택안함</option>
                            <option value="정읍">정읍</option>
                            <option value="전주">전주</option>
                            <option value="군산">군산</option>
                            <option value="부안">부안</option>
                            <option value="김제">김제</option>
                            <option value="완주">완주</option>
                            <option value="고창">고창</option>
                            <option value="익산">익산</option>
                            <option value="장수">장수</option>
                            <option value="순창">순창</option>
                            <option value="무주">무주</option>
                            <option value="진안">진안</option>
                            <option value="임실">임실</option>
                            <option value="남원">남원</option>
                        </select>
                    </li>
                    <li class="item">

                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        성별
                    </li>
                    <li class="item">
                        <select name="memberGender" id="">
                            <option value="선택" selected>선택</option>
                            <option value="M">남성</option>
                            <option value="F">여성</option>
                        </select>
                    </li>
                    <li class="item">

                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        전화번호
                    </li>
                    <li class="item">
                        <input type="text" placeholder="휴대전화번호" name="memberPhone" onKeyup="this.value=this.value.replace(/[^0-9]/g,'') ;">
                    </li>
                    <li class="item">

                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">

                    </li>
                    <li class="item">
                        <button type="button" class="submit" onclick="check();">가입하기</button>
                    </li>
                    <li class="item">

                    </li>
                </ul>
            </div>
         </form>
        </div>
    
    </BODY>
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
  }else if (fm.memberPwd2.value==""){
  		alert("비밀번호 확인을 입력해주세요");
  		fm.memberPwd2.focus();
  		return;
  }else if (fm.memberPwd.value != fm.memberPwd2.value){
  		alert("비밀번호가 일치하지 않습니다.");
  		fm.memberPwd2.value = "";
  		fm.memberPwd2.focus();
  		return;
  }else if (fm.memberName.value==""){
  		alert("이름을 입력해주세요");
  		fm.memberName.focus();
  		return;
  }else if (fm.memberEmail.value==""){
  		alert("이메일을 입력해주세요");
  		fm.memberEmail.focus();
  		return;
  }else if (fm.memberPhone.value==""){
  		alert("연락처를 입력해주세요");
  		fm.memberPhone.focus();
  		return;
  }else if (fm.memberJumin.value==""){
  		alert("주민번호를 입력해주세요");
  		fm.memberJumin.focus();
  		return;
  }else {
  		var flag = false; 
    	for(var i=0;i<fm.memberHobby.length;i++){   
    		if(fm.memberHobby[i].checked ==true){	
    	   		flag = true;
    	   		break;
    	   	}    	
    	}  
    	
    	if (flag == false){
    		alert("한개이상 선택해주세요");
    		return;
    	}    	
  }  
  
  		alert("전송합니다..");
  		//fm.action = "./memberJoinOk.jsp";
  		//가상경로 사용
  		fm.action = "<%=request.getContextPath()%>/member/memberJoinAction.do";
  		fm.method = "post";
  		fm.submit();  
  
    return;
  }  
  </script>  
 </HEAD>

 <BODY>
<center><h1>회원가입</h1></center>
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
<td>비밀번호 확인</td>
<td><input type="password" name="memberPwd2" size="30"></td>
</tr>
<tr>
<td>이름</td>
<td><input type="text" name="memberName" size="30"></td>
</tr>
<tr>
<td>이메일</td>
<td><input type="email" name="memberEmail" size="30"></td>
</tr>
<tr>
<td>성별</td>
<td>
<input type="radio" name ="memberGender" value="M" checked>남
<input type="radio" name ="memberGender" value="F">여
</td>
</tr>
<tr>
<td>지역</td>
<td><select name="memberAddr" style="width:100px;height:25px">
	<option value="전주">전주</option>
	<option value="대전">대전</option>
	<option value="서울">서울</option>
	</select>
</td>
</tr>
<tr>
<td>연락처</td>
<td>
<input type="text" name="memberPhone" size="30">
</td>
</tr>
<tr>
<td>주민번호</td>
<td><input type="number" name="memberJumin" size="30">	
</td>
</tr>

<tr>
<td>취미</td>
<td>
<input type="checkbox" name ="memberHobby" value="축구" checked>축구
<input type="checkbox" name ="memberHobby" value="농구">농구
<input type="checkbox" name ="memberHobby" value="야구">야구
</td>
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
>>>>>>> branch 'master' of https://github.com/tjdwnd105/apiB.git
</HTML>

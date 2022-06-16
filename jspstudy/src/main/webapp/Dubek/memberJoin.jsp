<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 페이지</title>
<style>
 @import url("css/style.css");

#cc {
height: 30px;
width: 5%;
}
#date{
height: 50px;
width:20%;
text-align:center;
}

</style>

<script type="text/javascript">
function submit() {   // 유효성 검사
	//alert 알림창 ("테스트 입니다.")
	var fm = document.frm;   
	
    if(fm.Email.value =="") {  // 이메일을 입력하지 않았을 경우
		alert("이메일을 입력해주세요.");
		fm.Email.focus();
		return;
	}
    else if(fm.Password.value ==""){   // 비밀번호가 누락된 경우
		alert("비밀번호를 입력하세요.");
		fm.Password.focus();
		return;
	}
	else if(fm.Password.value.length <  6 || fm.Password.value.length>15) {  // 비밀번호를 6~15자 이내로 입력하지 않은 경우
		alert("비밀번호는 6~ 15자 이내로 입력해야합니다.");
		fm.Password.focus();
		return;
	}
	else if(fm.Password2.value ==""){    // 비밀번호 확인이 누락된 경우
		alert("비밀번호 확인을 입력해주세요.");
		fm.Password2.focus();
		return;
	}
	else if(Password !=Password2){   // 비밀번호와 비밀번호 확인이 일치하지 않을 경우
		alert("비밀번호가 일치하지 않습니다.");
		Pw2="";
		fm.Password2.focus();
		return;
	}
	else if(fm.Name.value =="") {    // 이름을 입력하지 않았을 경우
		alert("이름을 입력해주세요.");
		fm.Name.focus();
		return;
	}
	else if(fm.Phone.value =="") {    // 휴대전화번호를 입력하지 않은 경우
		alert("휴대전화번호를 입력해주세요.");
		fm.Phone.focus();
		return;
		}
	else if(fm.Phone.value.length != 11) {   // 휴대전화 번호가 11자리가 안되거나 넘을 경우
			alert("휴대전화번호를 정확히 입력해주세요. ");
			fm.Phone.focus();
			return;
		}
	else if(fm.YY.value=="") {
			alert("출생연도를 입력해주세요.");
			fm.YY.focus();
			 return;
		}
	else if(fm.MM.value=="월") {
			alert("출생 월을 입력해주세요.");
			fm.MM.focus();
			return;
		}
	else if(fm.DD.value=="월") {
			alert("출생 일을 입력해주세요.");
			fm.DD.focus();
			 return;
		}
    alert("전송합니다.")   //위에 조건 모두 충족 후 확인 버튼 클릭
    fm.action = "<%=request.getContextPath()%>/Dubek/memberJoinAction.do";
	fm.method = "post";
	fm.submit();
	return;
}	

</script>
</head>
<body>
<h1>회원가입 페이지</h1>
<br>
<form name="frm">
<div id="wrapper">
<div> 
<h3>이메일</h3> 
<input type="text" id="text" name="Email" placeholder="이메일 입력"></div>
<div>
<h3>비밀번호</h3>
<input type="Password" id="text" name ="Password">
</div>
<div> 
<h3>비밀번호 확인</h3>
<input type="Password" id="text" name ="Password2"></div>
<div> 
<h3>이름</h3>
<input type="text" id="text" name="Name" >
</div>
<div> 
<h3>휴대전화번호</h3>
<input type="text" id="text" name="Phone" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"><!-- 숫자이외의 다른 것들은 입력하지 못하도록 설정 -->
</div>
<div> 
<h3>생년월일</h3>
<span>
<input type="text" id="date" name="YY" maxlength=4 placeholder="년(4자)" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
</span>
<span>
<select name="MM" id="date" >
<option value>월</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
</select></span>
<input type="text" id="date"  name="dd" maxlength=2 placeholder="일" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
</div>
<div> 
<h3>성별</h3>
<select name="Gender" id="text">
<option value="M">남자</option>
<option value="F">여자</option>
<option value="N" selected>선택안함</option>
</select>
</div>
<div>
<h3>거주중인 지역</h3>
<select name=Addr id="text">
<option value="군산">군산</option>
<option value="임실">임실</option>
<option value="순창">순창</option>
<option value="완주">완주</option>
<option value="무주">무주</option>
<option value="고창">고창</option>
<option value="김제">김제</option>
<option value="남원">남원</option>
<option value="장수">장수</option>
<option value="익산">익산</option>
<option value="진안">진안</option>
<option value="정읍">정읍</option>
<option value="전주">전주</option>
<option value="부안">부안</option>
<option value="선택안함" selected>선택안함</option>
</select>
</div>

<div class="btn_area">
<button type="submit" id="btnJoin" onclick="check();">
	                 <span>가입하기</span></button>
	       </div>
</div>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 페이지</title>
<style>
h1 { text-align : center;}
td, th, tr {
text-align : left;
padding:10px;
height: 30px; width: 150px; }
</style>
<script type="text/javascript">
function check() {   // 유효성 검사
	//alert 알림창 ("테스트 입니다.")
	var fm = document.frm;  
    var Id = fm.Id.value;
    var Pw = fm.Password.value;
    var Pw2 = fm.Password2.value;
    var Name = fm.Name.value;
    var Phone = fm.Phone.value;
    var Email = fm.Email.value;
	
	if(Id ==""){   // 아이디가 기입되지 않은 경우
		alert("아이디를 입력하세요.")
		fm.Id.focus();
		return;
	}else if(Id.length <  4 || Id.length>13) {  // 아이디를 4~13자 이내로 입력하지 않은 경우
		alert("아이디는 4~ 13자 이내로 입력해야합니다.")
		fm.Id.focus();
		return;
	}else if(Pw==""){   // 비밀번호가 누락된 경우
		alert("비밀번호를 입력하세요.")
		fm.Password.focus();
		return;
	}else if(Pw.length <  6 || Pw.length>15) {  // 비밀번호를 6~15자 이내로 입력하지 않은 경우
		alert("비밀번호는 6~ 15자 이내로 입력해야합니다.")
		fm.Password.focus();
		return;
	}else if(Pw2==""){    // 비밀번호 확인이 누락된 경우
		alert("비밀번호 확인을 입력해주세요.")
		fm.Password2.focus();
		return;
	}else if(Pw!=Pw2.value){   // 비밀번호와 비밀번호 확인이 일치하지 않을 경우
		alert("비밀번호가 일치하지 않습니다.");
		Pw2="";
		fm.Password2.focus();
		return;
	}else if(Name =="") {    // 이름을 입력하지 않았을 경우
		alert("이름을 입력해주세요.");
		fm.Name.focus();
		return;
	}else if(Phone =="") {    // 휴대전화번호를 입력하지 않은 경우
		alert("휴대전화번호를 입력해주세요.");
		fm.Phone.focus();
		return;
		}else if(Phone.length != 11) {   // 휴대전화 번호가 11자리가 안되거나 넘을 경우
			alert("휴대전화번호를 정확히 입력해주세요. ")
			fm.Phone.focus();
			return;
		}	else if(Email=="") {  // 이메일을 입력하지 않았을 경우
		alert("이메일을 입력해주세요.");
		fm.Email.focus();
		return;
	} else{ '#select').change(function() {
        if ($('#select').val() == 'directly') {
            $('#textEmail').attr("disabled", false);
            $('#textEmail').val("");
            $('#textEmail').focus();
        } else {
            $('#textEmail').val($('#select').val());
        }
    }}	
	alert("전송합니다.")   //위에 조건 모두 충족 후 확인 버튼 클릭
	//fm.action = 
		//가상경로 사용
	fm.action = "<%=request.getContextPath()%>/ㅈㅁ/회원가입Action.do";
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
<center><table style="width:800px; height:300px"></center>
<thead>
<tr>
<th>아이디</th>
<th><input type="text" name="Id" size="30"></th>
</tr>
</thead>
<tbody>
<tr>
<td>비밀번호</td>
<td><input type="Password" name ="Password" size="30"></td>
</tr> 
<tr>
<td>비밀번호 확인</td>
<td><input type="Password" name ="Password2" size="30"></td>
</tr>
<tr>
<td>이름</td>
<td><input type="text" name="Name" size="30"></td>
</tr>
<tr>
<td>휴대전화번호</td>
<td><input type="text" name="Phone" size="30" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>   <!-- 숫자이외의 다른 것들은 입력하지 못하도록 설정 -->
</tr>
<tr>
<td>이메일</td>
<td>
    <div> 
<input type="text"  id="emailId"  name="Email" placeholder="이메일 입력"  ><span>@</span>
 <select id="select">
            <option value="" disabled selected>E-Mail 선택</option>
            <option value="naver.com" id="naver.com">naver.com</option>
            <option value="hanmail.net" id="hanmail.net">hanmail.net</option>
            <option value="gmail.com" id="gmail.com">gmail.com</option>
            <option value="nate.com" id="nate.com">nate.com</option>
            <option value="directly" id="textEmail">직접 입력하기</option>
        </select></div>
</td>
</tr>
<tr>
<td>생년월일</td>
<td><input type="date" name="Date" size="30"></td>
</tr>
<tr>
<td>성별</td>
<td>
<input type="radio" name="Gender" value="남" checked>남
<input type="radio" name="Gender" value="여">여</td>
</tr> 
<tr>
</tr>
</tbody>
<tfoot>
<tr>
<td colspan =2 style="text-align:right;">
<input  type="button" value="가입하기" onclick="check();">
</td>
</tr>
</tfoot>
</table>
</form>
</body>
</html>
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
<title>이벤트 작성</title>
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
	 else {
		 var flag = false;   //기본값 체크가 안된 상태를 false로 지정
		 for(var i=0;i<fm.transportation.length;i++) {      //배열을 반복해서
			 if(fm.transportation[i].checked ==true)   {     //각 배열 방에 값이 하나라도 있다면
			 flag = true;   
			 break;    // 반복을 종료함.
			 }
		 }
		 if(flag == false){     // 이동수단 선택을 하지 않았을 경우
			 alert("이동수단을 한개 이상 선택해주세요.");
			 return;
		 }
	 }
	// 페이지 이동
	 fm.action = "<%=request.getContextPath()%>/board/noticeAction.do";
	 fm.enctype = "multipart/form-data";
	 fm.method = "post";
	 fm.submit();
	return;
}

</script>

</head>
<body>
<h1>게시판 글쓰기</h1>
<form name="frm">
<table border=1 style="width:800px;">
<tr>
<td style="width:100px">제목</td>
<td><input type="text" name="subject" size="100"></td>
</tr>
<tr>
<td>내용</td>
<td>
<textarea name="content" placeholder="내용을 입력해주세요." cols="100" rows="10">
</textarea>
</td>
</tr>
<tr>
<td>작성자</td>
<td><input type="text" name="writer" size="30" value="운영진" readonly="readonly" ></td>
</tr>
<tr>
<td>파일 업로드</td>
<td><input type="file" name="filename" size="30"></td>
</tr>
<tr>
<td colspan=2 style= "text-align:center;">
<input type="button" name="btn" value="확인" onclick="check();"></td>
</tr>
</table>
</form>
</body>
</html>
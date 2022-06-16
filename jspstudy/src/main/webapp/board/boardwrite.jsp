<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%
	if(session.getAttribute("midx") == null) {
		
		session.setAttribute("saveUrl", request.getRequestURI());    // 1
		out.println("<script>alert('로그인 해주세요.');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
	}
	%> <!-- 글쓰기 페이지이기 때문에 회원번호 필수 -->
	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글쓰기</title>
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
	 fm.action = "<%=request.getContextPath()%>/board/boardwriteAction.do";
	 fm.enctype = "multipart/form-data";
	 fm.method = "post";
	 fm.submit();
	return;
}

</script>

<style>
@import url("../Dubek/css/barddd.css");
</style>

</head>
<body>
<section>
  <div>
    <nav><a href="<%=request.getContextPath() %>/home/index.do">Home</a></nav>
    <nav><a href="<%=request.getContextPath() %>/board/boardList.do">게시판</a></nav>
    <nav><a href="<%=request.getContextPath() %>/notice/noticeList.do">공지사항</a></nav>
    <nav><a href="<%=request.getContextPath() %>/member/memberLogout.do">로그아웃</a></nav>
  </div>
</section>

<h1 style="text-align:center"> 게시판 글쓰기 </h1>
<form name="frm">
<table border=1 align="center" style="width:900px; height:600px; ">
<tr>
<td style="width:100px">제목</td>
<td><input type="text" name="subject" size="100"></td>
</tr>
<tr>
<td>내용</td>
<td>
<textarea name="content" placeholder="내용을 입력해주세요." cols="100" rows="30">
</textarea>
</td>
</tr>
<tr>
<td>작성자</td>
<td><input type="text" name="writer" size="30" value="<%=session.getAttribute("memberName") %>" readonly="readonly" ></td>
</tr>
<tr>
<td>파일 업로드</td>
<td><input type="file" name="filename" size="30"></td>
</tr>
<tr>
<td>이용했던 교통수단</td>
<td colspan="2"><input type="checkbox" id="cc"  name ="transportation" value="도보" checked>도보
<input type="checkbox" id="cc" name ="transportation" value="자전거">자전거
<input type="checkbox" id="cc" name ="transportation" value="택시">택시
<input type="checkbox" id="cc" name ="transportation" value="시내버스">시내버스
<input type="checkbox" id="cc" name ="transportation" value="시외버스">시외버스</td>
</tr>
<tr>
<td colspan=2 style= "text-align:center;">
<input type="button" name="btn" value="등록" onclick="check();"></td>
</tr>
</table>
</form>
</body>
</html>
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
<style type="text/css">
body{
text-align: center;

}
</style>
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
	 else if(fm.writer.value=="") {   // 작성자 입력 칸이 비어 있을 경우
		 alert("작성자를 입력해주세요.");
		 fm.writer.focus();
		 return;
	 }
	// 페이지 이동
	 fm.action = "<%=request.getContextPath()%>/board/boardwriteAction.do";
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
<td><input type="text" name="writer" size="30" value="<%=session.getAttribute("memberName") %>" readonly="readonly" ></td>
</tr>
<tr>
<td>파일 업로드</td>
<td><input type="file" name="filename" size="30" ></td>
</tr>
<tr>
<td><select name="season">
<option value="봄">봄</option>
<option value="여름">여름</option>
<option value="가을">가을</option>
<option value="겨울">겨울</option>
<option value="선택안함" selected>선택안함</select>
</td>
<td><select name="media">
<option value="영화">영화</option>
<option value="드라마">드라마</option>
<option value="책">책</option>
<option value="음악">음악</option>
<option value="선택안함" selected>선택안함</option>
</select></td>
<td><select name="since">
<option value="청동기">청동기</option>
<option value="삼국시대">삼국시대</option>
<option value="통일신라">통일신라</option>
<option value="고려">고려</option>
<option value="조선">조선</option>
<option value="근현대">근현대</option>
<option value="현대">현대</option>
<option value="선택안함" selected>선택안함</option>
</select></td>
<td>
<select name="area">
<option value="전주">전주</option>
<option value="정읍">정읍</option>
<option value="군산">군산</option>
<option value="남원">남원</option>
<option value="무주">무주</option>
<option value="진안">진안</option>
<option value="부안">부안</option>
<option value="고창">고창</option>
<option value="김제">김제</option>
<option value="장수">장수</option>
<option value="익산">익산</option>
<option value="임실">임실</option>
<option value="순창">순창</option>
<option value="완주">완주</option>
<option value="선택안함" selected>선택안함</option>
</select>
</td>
</tr>
<tr>
<td colspan=2 style= "text-align:center;">
<input type="button" name="btn" value="확인" onclick="check();">
<input type="button" name="btn" value="리셋" onclick="check();"></td>
</tr>
</table>
</form>
</body>
</html>
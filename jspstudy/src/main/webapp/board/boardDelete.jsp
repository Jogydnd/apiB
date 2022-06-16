<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    	    <%@page import="jspstudy.domain.BoardVo" %>
	    <%@page import="jspstudy.service.BoardDao" %>
	    <% BoardVo bv = (BoardVo)request.getAttribute("bv"); %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시물 삭제하기</title>
<script type="text/javascript">
function check(){   // 유효성 검사 
	var fm = document.frm;	
	//페이지 이동
	 fm.action = "<%=request.getContextPath()%>/board/boardDeleteAction.do";   
	 fm.method = "post";      
	 fm.submit();     // 삭제됨.
	 
	return;
}
</script>
<style>
   @import url("../Dubek/css/barddd.css");
</style>

</head>
<body  style = "text-align:center" >
<section>
  <div>
    <nav><a href="<%=request.getContextPath() %>/home/index.do">Home</a></nav>
    <nav><a href="<%=request.getContextPath() %>/board/boardList.do">게시판</a></nav>
    <nav><a href="<%=request.getContextPath() %>/notice/noticeList.do">공지사항</a></nav>
     <nav><a href="<%=request.getContextPath() %>/member/memberLogout.do">로그아웃</a></nav>
  </div>
</section>
<h3>게시물 삭제하기</h3>
<form name="frm">
<input type="hidden" name="bidx" value=<%=bv.getbidx() %>>    <!-- hidden 타입을 통해 해당되는 bidx값을 안보이게 가져올 수 있다.-->
<table align="center" border=1 style="width:300px; height:150px" >
<tr style="height:120px;">
<td>정말 게시물을 삭제하시겠습니까?</td>
</tr>
<tr>
<td style = "text-align: center;"><input type="button" name="btn" value="확인" onclick="check();"> <!-- BoardController에서 설정해 두었음. 확인을 누를시 삭제 처리 취소시 해당 글로보기 페이지로 넘어감 -->
<a href="<%=request.getContextPath() %>/board/boardList.do"><input type="button" name="btn" value="취소" ></a></td>
</tr>
</table>
</form>
</body>
</html>
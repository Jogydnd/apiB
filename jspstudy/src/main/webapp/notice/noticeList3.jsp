<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@page import="jspstudy.domain.*" %>   <!-- 해당 파일을 사용하기 위해 import 처리 -->
    <%@page import="java.util.*" %>    <!-- 해당 파일을 사용하기 위해 import 처리 -->
   <% ArrayList<NoticeVo> nlist = (ArrayList<NoticeVo>)request.getAttribute("nlist");  // (ArrayList<BoardVo>)를 붙여 명시적 형변환 후 객체 생성 및 arrayList
         PageMaker pm = (PageMaker)request.getAttribute("pm");   // (PageMaker)를 붙여 명시적 형변환 후 객체 생성     
         %> 

    
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
<style type="text/css">
  @import url("../Dubek/css/NoticeList.css");
   @import url("../Dubek/css/index.css");
</style>
</head>
<body>

<h1>공지사항 리스트</h1>

<section>
  <div style="width:50%">
   <nav><a href="<%=request.getContextPath() %>/home/index.do">Home</a></nav>
    <nav><a href="<%=request.getContextPath() %>/board/boardList.do">게시판</a></nav>
    <nav><a href="<%=request.getContextPath() %>/notice/noticeList.do">공지사항</a></nav>
    <nav><a href="<%=request.getContextPath() %>/member/memberLogin.do">로그인</a></nav>
  </div>
</section>

<form name= "frm" action="<%=request.getContextPath()%>/notice/noticeList.do" method="post">
<table style="border:0; text-align:right">
<tr>
<th>
      <select name="searchType">
      <option value="subject">제목</option>
      <option value="writer">작성자</option>
      </select>
	<input type="text" name="keyword" size ="10">
	<input type="submit" name="submit" value="검색">
</th>
</tr>
</table>
</form>

<br><br><br>

<table class="type09">
    <thead>
        <tr>
            <th scope="cols" id="nidx">nidx번호</th>
            <th scope="cols" id="wp">제목</th>
            <th scope="cols" id="wkr">작성자</th>
            <th scope="cols" id="skf">작성일</th>
        </tr>
    </thead>
    <% for (NoticeVo nv : nlist) {%>
    <tbody>
        <tr>
            <td id="nidx"><%=nv.getNidx() %></td>
            <th scope="row" id="wp">
                <a href="<%=request.getContextPath()%>/notice/noticeContent.do?nidx=<%=nv.getNidx() %>"><%=nv.getSubject() %></a>
            </th>
            <td id="wkr">운영자</td>
            <td id="skf"><%=nv.getWriteday() %></td>
        </tr>
        <% } %>
    </tbody>
</table>

<table style=" text-align:center;">
<tr>
<td style="width:200px; text-align:right">
<%   // 이전 페이지 목록으로 넘어가는 버튼 생성
String keyword = pm.getScri().getKeyword();

String searchType = pm.getScri().getSearchType();
// 파라미터 변수에 담을 생각

if (pm.isPrev() == true) {
	out.println("<a href= '"+request.getContextPath()+"/notice/noticeList.do?page="+(pm.getStartPage()-1)+"&keyword="+keyword+"&searchType="+searchType+"'>◀</a>");
}
%>
</td>
<td>
<%    //  페이지별 번호 부여
for (int i = pm.getStartPage(); i<= pm.getEndPage();i++){
out.println("<a href= '"+request.getContextPath()+"/notice/noticeList.do?page="+i+"&keyword="+keyword+"&searchType="+searchType+"'>"+i+"</a>");
}
%>   
</td>
<td style=" width:200px; text-align:left;">
<%   // 다음 페이지 목록으로 넘어가는 버튼 생성
if (pm.isNext()&&pm.getEndPage() >0){
	out.println("<a href= '"+request.getContextPath()+"/notice/noticeList.do?page="+(pm.getEndPage()+1)	+"&keyword="+keyword+"&searchType="+searchType+"'>▶</a>");
}
%>
</td>
</tr>
</table>
</body>
</html>
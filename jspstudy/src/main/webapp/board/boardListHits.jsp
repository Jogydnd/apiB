<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@page import="jspstudy.domain.*" %>   <!-- 해당 파일을 사용하기 위해 import 처리 -->
    <%@page import="java.util.*" %>    <!-- 해당 파일을 사용하기 위해 import 처리 -->
   <% ArrayList<BoardVo> blist = (ArrayList<BoardVo>)request.getAttribute("blist");  // (ArrayList<BoardVo>)를 붙여 명시적 형변환 후 객체 생성 및 arrayList
         PageMaker pm = (PageMaker)request.getAttribute("pm");   // (PageMaker)를 붙여 명시적 형변환 후 객체 생성     
         
         %> 
          
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 리스트</title>
<script src="../resources/js/jquery-3.6.0.min.js"></script>

<link rel="icon" type="image/x-icon" href="assets/favicon.ico">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<link href="../Dubek/css/list.css" rel="stylesheet" />


<script type="text/javascript">
$( document ).ready( function() {
	$(".col mb-5").hover(function(){
				$(this).animate({
					Size:"50px"
				},500);
	}, function () {
        $( this ).animate( {
          Size: "1em"
        }, 500 );
      }
    );
  } );
</script>

<style type="text/css">
 @import url("../Dubek/css/boardList.css");
</style>

</head>
<body>
<!-- 검색하는 곳 -->

<form name= "frm" action="<%=request.getContextPath()%>/board/boardList.do" method="post">
<table style="text-align:right">
<thead>
<tr>
<th style="text-align:left"><a href=" <%=request.getContextPath() %>/home/index.do"><img alt="Dubek" src="../home/img/Home.ico"></a></th>
</tr>
<tr>

</tr>
<tr>
<th style="text-align:left"><a href= "<%=request.getContextPath()%>/board/boardwrite.do">📝글쓰기</a></th>
<th>
<a href="<%=request.getContextPath()%>/board/HitArray.do"><input class="btn btn-link-dark" type="button" value="조회수"></a>
</th>
<th>
<a href="<%=request.getContextPath()%>/board/LikeArray.do"><input class="btn btn-link-dark" type="button" value="좋아요"></a>
</th>
<th>
<a href="<%=request.getContextPath()%>/board/AngryArray.do"><input class="btn btn-link-dark" type="button" value="화나요"></a>
</th>
<th>
<a href="<%=request.getContextPath()%>/board/boardList.do"><input class="btn btn-link-dark" type="button" value="업로드"></a>
</th>

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




<section class="py-5">
<div class="container px-4 px-lg-mt-5">
<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
<% for (BoardVo bv : blist) { %>
<% if(bv.getFilename()==null) { bv.setfilename("fileNull.jpg");}%>

<div class="col mb-5">
<a href="<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=bv.getbidx()%>" >
<div class="card h-100">
<!-- image -->

<img class="card-img-top" alt="해당 파일 이미지" src="<%=request.getContextPath()%>/img/<%=bv.getFilename()%>">


<!-- detail -->
<div class="card-body-p-4">
<div class="text-center"> 
<p class="fw-bolder"><%=bv.getsubject() %></p>
</div>
</div>
<hr style="color:gray">
<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
<div class="text-center">
<%=bv.getwriter()%>&nbsp;&nbsp;&nbsp;😍:<%=bv.getLike_it() %>&sdot;😡:<%=bv.getAngry_it()%>&nbsp;조회수:<%=bv.getHits() %>
</div>
</div>

</div>
</a>
</div>

<% } %>
</div>
</div>
</section>
<table style="width:800px; text-align:center;">
<tr>
<td style="width:200px; text-align:right">
<%   // 이전 페이지 목록으로 넘어가는 버튼 생성
String keyword = pm.getScri().getKeyword();

String searchType = pm.getScri().getSearchType();
// 파라미터 변수에 담을 생각

if (pm.isPrev() == true) {
	out.println("<a href= '"+request.getContextPath()+"/board/boardList.do?page="+(pm.getStartPage()-1)+"&keyword="+keyword+"&searchType="+searchType+"'>◀</a>");
}
%>
</td>
<td>
<%    //  페이지별 번호 부여
for (int i = pm.getStartPage(); i<= pm.getEndPage();i++){
out.println("<a href= '"+request.getContextPath()+"/board/boardList.do?page="+i+"&keyword="+keyword+"&searchType="+searchType+"'>"+i+"</a>");
}
%>   
</td>
<td style=" width:200px; text-align:left;">
<%   // 다음 페이지 목록으로 넘어가는 버튼 생성
if (pm.isNext()&&pm.getEndPage() >0){
	out.println("<a href= '"+request.getContextPath()+"/board/boardList.do?page="+(pm.getEndPage()+1)	+"&keyword="+keyword+"&searchType="+searchType+"'>▶</a>");
}
%>
</td>
</tr>
</table>

<footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright © Your Website 2022</p></div>
        </footer>
</body>
</html>
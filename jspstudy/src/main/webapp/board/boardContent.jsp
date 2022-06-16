<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import ="jspstudy.domain.*" %>
    <%@page import="java.util.*" %>
    <% BoardVo bv =(BoardVo)request.getAttribute("bv"); %>   <!--  bv를 객체생성하여 BoardVo에 있는 것들을 사용하도록 도와주는 코드 -->  
   <% ArrayList<ReplyVo> rlist = (ArrayList<ReplyVo>)request.getAttribute("rlist"); %>
   
   <%
   if(session.getAttribute("midx") == null) {
		out.println("<script>location.href='"+request.getContextPath()+"/board/boardContent3.do?bidx="+bv.getbidx()+"'</script>");
	}
   else if((int)session.getAttribute("midx")!=bv.getmidx()) {
	out.println("<script>location.href='"+request.getContextPath()+"/board/boardContent2.do?bidx="+bv.getbidx()+"'</script>");
	}
 	%>   

     
<!DOCTYPE html>
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓴이전용 글열람 페이지</title>
<script src="../resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	// 좋아요 구현
	$("#likeCheck").click(function(){
		let bidx = "<%=bv.getbidx()%>";
		
		$.ajax({
			type: 'post',
		    url: "<%=request.getContextPath()%>/board/like.do",
		    data: {"bidx": bidx},
		    success: function(data) {
		    	
		    	let obj = JSON.parse(data);
		    	
		    	if(obj.like ==1 ){//기존의 이메일 아이디와 중복이 안될 경우
					result = "좋아요가 반영되었습니다!!";
		    	    alert("'좋아요'가 반영되었습니다!!");
		    	    location.reload();
				} else {//중복될 경우
					result = "좋아요가 반영되지 않았습니다.";
					alert("오류로 인해'좋아요' 반영하기가 누락되었습니다..");
				} 
			},
			error : function(error){ //에러 발생시
				alert(error);
			}
		}); 
    });
	
	//화나요 구현
	$("#AngryCheck").click(function(){
		let bidx = "<%=bv.getbidx()%>";
		
		$.ajax({
			type: 'post',
		    url: "<%=request.getContextPath()%>/board/angry.do",
		    data: {"bidx": bidx},
		    success: function(data) {
		    	
		    	let obj = JSON.parse(data);
		    	
		    	if(obj.Angry == 1 ){//기존의 이메일 아이디와 중복이 안될 경우
					result = "화나요가 반영되었습니다!!";
		    	    alert("'화나요'가 반영되었습니다!!");
		    	    location.reload();
				} else {//중복될 경우
					result = "화나요가 반영되지 않았습니다.";
					alert("오류로 인해'화나요' 반영하기가 누락되었습니다..!");
				} 
			},
			error : function(error){ //에러 발생시
				alert(error);
			}
		}); 
    });
	
	// 댓글 쓰기 구현
$("#btnReply").click(function(){
	
		
		let replytext=$("#replytext").val(); //댓글 내용
	    let bidx = "<%=bv.getbidx()%>"; //게시물 번호
	    let memberEmail = "<%=session.getAttribute("memberEmail")%>";
	    
	    $.ajax({
	        type: 'post', //데이터를 보낼 방식
	        url: "<%=request.getContextPath()%>/reply/Insert.do",
	        data: { "replytext": replytext, "bidx": bidx, "memberEmail": memberEmail}, //보낼 데이터
	        success: function(data){ //데이터를 보내는것이 성공했을시 출력되는 메시지
	        	
	        	let obj = JSON.parse(data);
		    	
		    	if(obj.Insert ==1 ){//기존의 이메일 아이디와 중복이 안될 경우
					result = "작성하신 댓글이 등록되었습니다.";
		    	    alert("작성하신 댓글이 등록되었습니다!!");
		    	    location.reload();
				} else {//중복될 경우
					result = "로그인 후에 다시 작성해주세요.";
					alert("로그인 후에 다시 작성해주세요.");
				} 
			},
			error : function(error){ //에러 발생시
				alert(error);
			}
		}); 
	});	
	
	//댓글 추천하기 구현
	$(".Good").each(function(){
		$(this).click(function(){
			let ridx = $(this).next().val();
			alert("ridx: "+ridx);
			
		$.ajax({
			type: 'post',
			url: "<%=request.getContextPath()%>/reply/Good.do",
		    data: {"ridx": ridx},
		    success: function(data) {
		    	
		    	let obj = JSON.parse(data);
		    	
		    	if(obj.Good ==1 ){//기존의 이메일 아이디와 중복이 안될 경우
					result = "'추천하기'가 반영되었습니다!!";
		    	    alert("'추천하기'가 반영되었습니다!!");
		    	    location.reload();
				} else {//중복될 경우
					result = "'추천하기'가 반영되지 않았습니다.";
					alert("오류로 인해' 추천하기' 반영이 누락되었습니다..");
				} 
			},
			error : function(error){ //에러 발생시
				alert(error);
			}
		});
		}); 
    });
	
	var target = document.querySelectorAll('.btn_open');
	var btnPopClose = document.querySelectorAll('.pop_wrap .btn_close');
	var targetID;

	 // 팝업 열기
	 for(var i = 0; i < target.length; i++){
	   target[i].addEventListener('click', function(){
	     targetID = this.getAttribute('href');
	     document.querySelector(targetID).style.display = 'block';
	   });
	 }

	 for(var j = 0; j < target.length; j++){
		    btnPopClose[j].addEventListener('click', function(){
		      this.parentNode.parentNode.style.display = 'none';
		    });
		  }
	 
	 //대댓글 등록하기
	  $(".Re_reply").each(function(){
			$(this).click(function(){
				
		    let Re_retext=$(this).prev().prev().val(); //댓글 내용
		    let memberEmail = "<%=session.getAttribute("memberEmail")%>";
			let bidx = "<%=bv.getbidx()%>";
			    
		    let ridx =$(this).next().val(); 
		    let R_origin =$(this).next().next().val();
		    let R_depth =$(this).next().next().next().val();
		    let R_lev = $(this).next().next().next().next().val();
	
		    alert("ridx:"+ridx+" R_origin:"+R_origin+" R_depth:"+R_depth+" R_lev:"+R_lev);
		    
		    $.ajax({
		        type: 'post', //데이터를 보낼 방식
		        url: "<%=request.getContextPath()%>/reply/RRInsert.do",
		        data: { "Re_retext": Re_retext,"ridx": ridx,"memberEmail": memberEmail,"R_origin":R_origin, "R_depth":R_depth, "R_lev":R_lev, "bidx": bidx}, //보낼 데이터
		        success: function(data){ //데이터를 보내는것이 성공했을시 출력되는 메시지
		        	
		        	let obj = JSON.parse(data);
		
			    	if(obj.RRInsert ==1 ){
						result = "작성하신 댓글이 등록되었습니다.";
			    	    alert("작성하신 댓글이 등록되었습니다!!");
			    	    location.reload();
					} 
			    	else {
						result = "알수없는 오류로 인해 작성하신 댓글이 누락되었습니다.";
						alert("알수없는 오류로 인해 작성하신 댓글이 누락되었습니다.");
					} 
		        },
				error : function(error){ //에러 발생시
					alert(error);
				}
			});
		    
		}); 
		});	
	
	 
	 
	 
	 
	 
});

</script>
<style type="text/css">
  @import url("../Dubek/css/Content.css");
  @import url("../Dubek/css/barddd.css");
  
  h4{
  font-size:1.3em;
  }
  
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
<table style=" padding:0px 16px;">
<thead>
<tr>
<th><h1><%=bv.getsubject() %></h1></th>
</tr>
<tr>
<th><%=bv.getwriter() %></th>
<th>&nbsp;( <%=bv.getwriteday().substring(0,10) %>)</th> <!-- 해당 글을 쓴 날짜를 가져오는 코드 -->
</tr>
</thead>
<tbody>
<tr>
<td>
<img alt="해당 파일 이미지" src="<%=request.getContextPath()%>/img/<%=bv.getFilename()%>" width="768px" height="434.312px">
<br>
<%=bv.getcontent() %>
</td>
</tr>
<tr>
<% if(bv.getTransportation()==null) { bv.setTransportation("선택 안함");}%>
<td><%=bv.getTransportation() %></td>
</tr>
<tr>
<td style="height:20px">
<%=bv.getwriter() %>
</td>
</tr>
</tbody>
<tfoot>
<tr>
<td>

<form>
<table style="width:300px; height:150px">
<thead>
<tr>
<th><input type="button" name="like" value="😍" id="likeCheck" class="emoticon"></th>
<th><input type="button" name="Angry" value="😡" id="AngryCheck" class="emoticon"></th>
</tr>
</thead>
<tbody>
<tr>
<td><p id="emo_scrip">좋아요</p></td> 
<td><p id="emo_scrip">화나요</p></td>
</tr> 
</tbody>
<tfoot>
<tr>
<td><p id="emo_num"><%=bv.getLike_it()%></p></td>
<td><p id="emo_num"><%=bv.getAngry_it() %></p></td>
</tr>
</tfoot>
</table>
</form>

</td>
</tr>
<tr>
<td colspan=2 style= "text-align:right;">
<input type="button" name="btn" value="수정" onclick="location.href='<%=request.getContextPath()%>/board/boardModify.do?bidx=<%=bv.getbidx()%>'">       <!-- 수정하기 위한 글의 bidx를 가져오는 코드 -->
<input type="button" name="btn" value="삭제" onclick="location.href='<%=request.getContextPath()%>/board/boardDelete.do?bidx=<%=bv.getbidx()%>'">        <!-- 삭제하기를 수행하기 위해 bidx값을 가져오는 코드 -->
<input type="button" name="btn" value="목록" onclick="location.href='<%=request.getContextPath()%>/board/boardList.do'"> 
</td>
</tr>
<tr>
<td style="text-align:left">조회수<br><%=bv.getHits()%>회</td>
</tr>
<tr>
<td>
<div>
         <textarea rows="5" cols="80" id="replytext" class="retext" placeholder="댓글을 작성해주세요." ></textarea>
         <br>
         <button type="button" id="btnReply" class="rebtn">댓글쓰기</button>
</div></td>
</tr>
</tfoot>
</table>

<br><br><br><br>
<% for (ReplyVo rv : rlist){ %> 

<table style="text-align:left">  
<thead>
<tr>
<th>
<h4><%for(int i = 1; i<= rv.getR_lev(); i++) {   // 답변글이 아니라면 r_lev()이 0임. 만일 해당글이 답변일 경우 1이상이기 때문에 반복을 통해 i값이 증가
	out.println("&nbsp;&nbsp;&nbsp;&nbsp;");	 // i값이 증가될 경우 띄어쓰기 작동 
	if(i== rv.getR_lev()){       // 만약 i와 해당글의 level이 같을 경우
		out.println("&nbsp;&nbsp;");         //  띄어쓰기 처리 후 답변글 앞에 'ㄴ'을 붙임.
	}
	}%><%=rv.getR_name()%></h4></th>
</tr>
<tr>
<td style="font:8px"><%for(int i = 1; i<= rv.getR_lev(); i++) {   // 답변글이 아니라면 r_lev()이 0임. 만일 해당글이 답변일 경우 1이상이기 때문에 반복을 통해 i값이 증가
	out.println("&nbsp;&nbsp;&nbsp;&nbsp;");	 // i값이 증가될 경우 띄어쓰기 작동 
	if(i== rv.getR_lev()){       // 만약 i와 해당글의 level이 같을 경우
		out.println("&nbsp;&nbsp;");         //  띄어쓰기 처리 후 답변글 앞에 'ㄴ'을 붙임.
	}
	}%><%=rv.getR_wday()%></td>
</tr> 
</thead>
<tbody>
<tr>
<td><%for(int i = 1; i<= rv.getR_lev(); i++) {   // 답변글이 아니라면 r_lev()이 0임. 만일 해당글이 답변일 경우 1이상이기 때문에 반복을 통해 i값이 증가
	out.println("&nbsp;&nbsp;&nbsp;&nbsp;");	 // i값이 증가될 경우 띄어쓰기 작동 
	if(i== rv.getR_lev()){       // 만약 i와 해당글의 level이 같을 경우
		out.println("&nbsp;&nbsp;");         //  띄어쓰기 처리 후 답변글 앞에 'ㄴ'을 붙임.
	}
	}%><%=rv.getR_content() %></td>  
</tr>
</tbody>
<tfoot>
<tr>
<td>
<div class="div_Good">
<%for(int i = 1; i<= rv.getR_lev(); i++) {   // 답변글이 아니라면 r_lev()이 0임. 만일 해당글이 답변일 경우 1이상이기 때문에 반복을 통해 i값이 증가
	out.println("&nbsp;&nbsp;&nbsp;&nbsp;");	 // i값이 증가될 경우 띄어쓰기 작동 
	if(i== rv.getR_lev()){       // 만약 i와 해당글의 level이 같을 경우
		out.println("&nbsp;&nbsp;");         //  띄어쓰기 처리 후 답변글 앞에 'ㄴ'을 붙임.
	}
	}%><input type="button" value="👍<%=rv.getR_like_it()%>" class="Good">
<input type="hidden" value="<%=rv.getRidx() %>" class="ridx"></div>

<%for(int i = 1; i<= rv.getR_lev(); i++) {   // 답변글이 아니라면 r_lev()이 0임. 만일 해당글이 답변일 경우 1이상이기 때문에 반복을 통해 i값이 증가
	out.println("&nbsp;&nbsp;&nbsp;&nbsp;");	 // i값이 증가될 경우 띄어쓰기 작동 
	if(i== rv.getR_lev()){       // 만약 i와 해당글의 level이 같을 경우
		out.println("&nbsp;&nbsp;");         //  띄어쓰기 처리 후 답변글 앞에 'ㄴ'을 붙임.
	}
	}%><a href="#pop_info_<%=rv.getRidx()%>" class="btn_open"><button type="button">답글</button></a>

 <%for(int i = 1; i<= rv.getR_lev(); i++) {   // 답변글이 아니라면 r_lev()이 0임. 만일 해당글이 답변일 경우 1이상이기 때문에 반복을 통해 i값이 증가
	out.println("&nbsp;&nbsp;&nbsp;&nbsp;");	 // i값이 증가될 경우 띄어쓰기 작동 
	if(i== rv.getR_lev()){       // 만약 i와 해당글의 level이 같을 경우
		out.println("&nbsp;&nbsp;");         //  띄어쓰기 처리 후 답변글 앞에 'ㄴ'을 붙임.
	}
	}%>
	<div id="pop_info_<%=rv.getRidx()%>" class="pop_wrap" style="display:none;">
	 <div class="pop_inner">
    <textarea rows="5" cols="80" class="Re_retext"
             placeholder="댓글을 작성해주세요." style="border:1; padding: 16px 16px 24px; margin:0px 0px 16px; background:#FFFFFF;"></textarea>
             <br>
     <button type="button" class="Re_reply" style="font: 16px -apple-system, BlinkMacSystemFont; Color:#FFFFFF; Background:#12B886; Padding:0px 20px;">댓글쓰기</button>
           <input type="hidden" id="ridx" value="<%=rv.getRidx()%>">
             <input type="hidden" id="origin" value="<%=rv.getR_origin()%>">
             <input type="hidden" id="depth" value="<%=rv.getR_depth()%>">
              <input type="hidden" id="level" value="<%=rv.getR_lev()%>">
      <button type="button" class="btn_close">닫기</button>
  </div>
  </div>
</td>
</tr>
</tfoot>
</table>
<% } %>
</body>
</html>
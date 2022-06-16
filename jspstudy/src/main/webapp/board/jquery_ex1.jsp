<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		
				
		$(".Good").each(function(index, item){
			$(this).click(function(){
				let ridx = $(this).next().val();
				alert("ridx: "+ridx);
			});
		});
		
	});
	
</script>
</head>
<body>

<% 
    for(int ridx=0; ridx < 5; ridx++){
%>
<div class="div_Good">
	<input type="button" class="Good" value="좋아요"/>
	<input type="text" class="ridx" value="<%=ridx %>"/>
</div>
<% 
    }
%>

</body>
</html>
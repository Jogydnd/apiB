<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@page import ="jspstudy.domain.*" %>
    <%@page import="java.util.*" %>
    <% MemberVo mv = new MemberVo(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 페이지</title>

</head>
<body>
<%= mv.getMamberemail() %>
<%= mv.getMemberrank() %>
<%= mv.getMemberip() %>
<%= mv.getMemberaddr() %>
<%= mv.getMemberphone() %>
<%= mv.getMembername() %>
<%= mv.getWriteday() %>
<%= mv.getMembergender() %>

</body>
</html>
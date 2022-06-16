<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="com.google.gson.Gson, com.google.gson.JsonObject" %>
    
<%
	request.setCharacterEncoding("UTF-8");
    int Good = (int)request.getAttribute("value");
  
	Gson gson = new Gson();//Gson객체 생성
	JsonObject obj = new JsonObject();//JSON객체 생성
	
	obj.addProperty("Good", Good);
	
	out.print(gson.toJson(obj));
%>
 
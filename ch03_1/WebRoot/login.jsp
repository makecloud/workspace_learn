<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <center>
    <form action="login" method="post" >
    	<table border="0" >
    		<tr>
    			<td>用户名</td><td><input type="text" name="user.username" /></td>
    		</tr>
    		<tr>
    			<td>密码</td><td><input type="password" name="user.password" /></td>
    		</tr>
    		<tr>
    			<td><input type="reset" value="重填" /></td>
    			<td><input type="submit" value="登录" /></td>	
    		
    		</tr>
    	</table>
    </form>
  </center>
  </body>
</html>

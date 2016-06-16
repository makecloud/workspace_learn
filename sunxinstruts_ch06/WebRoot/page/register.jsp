<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body><center>
	<form>
		<table>
			<tr>
				<td>用户名：<input type="text" name="user.username" ></td>
			</tr>
			<tr>
				<td>密码：<input type="password" name="user.password" ></td>
			</tr>
			<tr>
				<td>性别：<input type="radio" name="user.sex"></td>
			</tr>
			<tr>
				<td>邮箱地址：<input type="text" name="user.email" ></td>
			</tr>
			<tr>
				<td>密码问题：<input type="text" name="user.pwdQuestion"></td>
			</tr>
			<tr>
				<td>密码答案：<input type="text" name="user.pwdAnswer"></td>
			</tr>
		</table>
	</form>
  </center>
  </body>
</html>

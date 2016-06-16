<%@page import="ch14.entity.MyMessage"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="ch14.ORM.HibernateSessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>使用Session的get，load方法装载持久化对象</title>
    
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
	<%
		Session mySession = HibernateSessionFactory.getSession();
		Transaction tx=mySession.beginTransaction();
		MyMessage myMessage=new MyMessage();
		myMessage.setId(123456);
		myMessage.setName("飞碟");
		mySession.saveOrUpdate(myMessage);
		
		Object obj=mySession.get(MyMessage.class, 123456);
		if(null!=obj)
		{
			myMessage=(MyMessage)obj;
			out.println("id:"+myMessage.getId()+"&nbsp;&nbsp;name:"+myMessage.getName());	
		}
		else
		{
			out.println("未找到id为123456的记录！");
		}
		out.println("<br>");
		obj=mySession.get(MyMessage.class,12345678);
		if(null==obj)
		{
			out.println("未找到id为12345678的记录！<br>");
		}
		
		try
		{
			obj=mySession.load(MyMessage.class, 1234567);
			myMessage=(MyMessage)obj;
			myMessage.getName();
			
		}
		catch(Exception e)
		{
			out.println(e.getMessage());
		}
		
		tx.commit();
		mySession.close();
		
	 %>
  </body>
</html>

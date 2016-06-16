<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
	/*
	var xmlHttpReq=null;
	
	function Ajax() {
		
		
		xmlHttpReq=new XMLHttpRequest();
		
		xmlHttpReq.open("GET","helloworld.jsp",true);
		
		xmlHttpReq.onreadystatechange=RequestCallBack;//设置回掉函数
		xmlHttpReq.send(null);
		
		
	}
	function RequestCallBack () {
		
		if (xmlHttpReq.readyState == 4) {
			
			if (xmlHttpReq.status == 200) {
				;
				//将xmlHttpReq.responsText的值赋给id为resText的元素
				document.getElementById('resText').innerHTML=xmlHttpReq.responseText;

			};
		};
	}
	*/
		$(document).ready(function(){
			
			$("#tijiao").click(function(){
				
				$.get("helloworld.jsp",null,function (data,textStatus) {
					//alert(data);
					$("#resText")[0].innerHTML=data;
				});
			});
			$("#loading").ajaxStart(function () {
				$(this).show();
			});
			$("#loading").ajaxStop(function () {
				$(this).hide();
			});
		});
	</script>
	
  </head>
  
  <body>
    This is my JSP page. <br>
    <input type="button" value="ajax提交" id="tijiao" >
		<div id="loading" style="display:none">加载中……</div>

    <div id="resText" ></div>
  </body>
  
</html>

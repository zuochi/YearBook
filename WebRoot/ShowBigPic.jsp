<%@page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%
	String url = request.getParameter("url");
	String name = request.getParameter("name");
%>
<base href="<%=basePath%>">

<title><%=name %> - YearBook</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">

body{background-image:url(images/background_main.png);background-repeat: repeat;margin:0; }
.top{
    z-index:2; 
    top:0;
    left:0;
	background: #ffffff;	
    width:100%;
	overflow:hidden;
	height: 40px;
	position:absolute;
	box-shadow:  rgba(0,0,0, 0.1) 0px 0px 5px;
	-moz-box-shadow:  rgba(0,0,0, 0.1) 0px 0px 5px;
	-webkit-box-shadow:  rgba(0,0,0, 0.1) 0px 0px 5px;
	position: relative;
   opacity: 0.7;}
   
.pic{padding:20px 0px 50px 0px; }
</style>

</head>
<body>
<div class="top"> 
<input type="text"  style="font-size:14px; margin-left:30px;">
<input type="button" value="S" style="font-size:18px">earch
</div>
<div class="pic" align="center" ><img style="max-width:100%" src="<%=url %>"/></div>

</body>
</html>

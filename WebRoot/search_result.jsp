<%@page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 搜索结果页面。 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>The search results </title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="styles/search_result.css" />
<link rel="stylesheet" type="text/css" href="styles/jquery.cssemoticons.css" media="screen"/>
<link rel="stylesheet" href="styles/styles_guide.css" type="text/css">
</head>
<body>
<jsp:include page="guide.jsp" />
<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("/YearBook/user/autoLogin_execute");
	}
%>
<div style="height:100px; width:100%"></div>
<div class="top"> 
<input type="text"  style="font-size:14px; margin-left:30px;">
<input type="button" value="S" style="font-size:18px">earch
</div>
<div class="user">
<div class="headphoto"></div>
<div class="information">
<div class="left2">
<h1>Username</h1><h1>male</h1><h1 >Posts:12</h1><h1>Followings:7</h1><h1>Followers:11</h1>
</div>

<div class="left3"><h2>Hometown:Foshan</h2><h2>Profession:IT</h2><h2>School year:2015</h2>
</div>
<div class="sign"><h3>Sign:</h3></div>
</div>
<div class="left4"></div>
</div>

<div class="user">
<div class="headphoto"></div>
<div class="information">
<div class="left2">
<h1>Username</h1><h1>male</h1><h1 >Posts:12</h1><h1>Followings:7</h1><h1>Followers:11</h1>
</div>

<div class="left3"><h2>Hometown:Foshan</h2><h2>Profession:IT</h2><h2>School year:2015</h2>
</div>
<div class="sign"><h3>Sign:</h3></div>
</div>
<div class="left4"></div>
</div>


<div class="user">
<div class="headphoto"></div>
<div class="information">
<div class="left2">
<h1>Username</h1><h1>male</h1><h1 >Posts:12</h1><h1>Followings:7</h1><h1>Followers:11</h1>
</div>

<div class="left3"><h2>Hometown:Foshan</h2><h2>Profession:IT</h2><h2>School year:2015</h2>
</div>
<div class="sign"><h3>Sign:</h3></div>
</div>
<div class="left4"></div>
</div>



</body>
</html>

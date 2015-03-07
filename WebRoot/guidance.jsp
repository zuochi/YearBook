<%@ page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 左侧菜单栏 -->
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="styles/reset.css" rel="stylesheet"  type="text/css">

<jsp:include page="styles/my.css" />
<body>
	<!--左侧导航-->
	<div id="container">
		<div id="logo">
			<a href="index.jsp"><img src="images/logo1.png" alt="" width="80"
				height="80" />
			</a>
		</div>
		<!-- end #logo -->
		<div id="logo1">
			<s:if test="#session.headPhoto.urlL!=null">
				<a href="javascript:void(0)" onclick="goUrl(1)" target="main"> 
					<img src="<s:property value="#session.headPhoto.urlL"/>" alt="" width="65" height="65" title="修改头像"> 
				</a>
			</s:if>
			<s:elseif test="#session.headPhoto.urlL==null">
				<a href="javascript:void(0)" onclick="goUrl(1)" target="main"> 
					<img src="images/bg.png" alt="" width="65" height="65" title="修改头像"> 
				</a>
			</s:elseif>
			<s:else>
				<img src="images/bg.png" alt="" width="65" height="65" title="修改头像">
			</s:else>
		</div>
		<div id="t1">
			<label> <s:if test="#session.user.name!=null">
					<a class="name" style="color:white" href="javascript:void(0)" onclick="goUrl(2)" target="main"><s:property value="#session.user.name" /></a>
				</s:if> <s:else>
					tourist
				</s:else> </label>
		</div>
		<%
	    int i=1;
		//if(user!=null){ 
		if(i==1){ 
		%>
		<div id="footer">
			<div id="home">
				<a href="javascript:void(0)" onclick="goUrl(3)" target="main"></a>
			</div>
			<div id="profile">
				<a href="javascript:void(0)" onclick="goUrl(2)" target="main"></a>
			</div>
			<!-- <div id="friend">
				<a href="javascript:void(0)" onclick="goUrl(4)" target="main"></a>
			</div> -->
				<div id="photo">
					<a href="javascript:void(0)" onclick="goUrl(5)" target="main"></a>
				</div>
				<div id="bbs">
					<a href="javascript:void(0)" onclick="goUrl(6)" target="main"></a>
				</div>
				<div id="message"><div class="tishi">99+</div>
					<a href="javascript:void(0)" onclick="goUrl(7)" target="main"> </a>
					
				</div>
				<div id="exit">
					<a href="javascript:void(0)" onclick="goUrl(8)" target="_parent"></a>
				</div>
			
			<div id="t2">
				please. <a href="javascript:void(0)" onclick="goUrl(9)" target="main"> login</a><br>
				 or<a href="javascript:void(0)" onclick="goUrl(10)" target="main">sign up</a>
			</div>
			<%}else{ %>
			<div id="t2">
				please. <a href="javascript:void(0)" onclick="goUrl(9)" target="main"> log in!</a><br>
				 or<a href="javascript:void(0)" onclick="goUrl(10)" target="main">sign up</a>
			</div>
			<%} %>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/goUrl.js"></script>
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="js/jquery.animate-colors-min.js"></script>
	<script type="text/javascript" src="js/my.js"></script>

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
<base href="<%=basePath%>">

<title>Friend </title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="styles/sass-compiled.css" />
<link rel="stylesheet" type="text/css" href="styles/jquery.cssemoticons.css" media="screen"/>
</head>
<body>
<div class="top"> 
<input type="text"  style="font-size:14px; margin-left:30px;">
<input type="button" value="S" style="font-size:18px">earch
</div>
<a id="followersTitleA" href="javascript:void(0)" title="click to show followers."><div>Followers </div></a>
<a id="followingTitleA" href="javascript:void(0)" title="click to show following."><div>Following </div></a>

<input id="type" type="hidden" value="<%=request.getParameter("type") %>"/>
<input id="userId" type="hidden" value="<s:property value='#session.user.id'/>"/>
<div class="frame" id="context">

	<div id="followingContext"></div>
	<div id="hasFollowing"></div>
	<div id="followersContext"></div>
	<div id="hasFollowers"></div>
	
   </div>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script src="js/followOperationSmall.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.cssemoticons.js"></script>
<script src="js/goUrl.js" type="text/javascript"></script>
<script src="js/modernizr.js" type="text/javascript"></script>
<script src="js/friend.js" type="text/javascript"></script>
</body>
</html>

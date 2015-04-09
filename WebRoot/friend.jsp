<%@page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 用户关注、粉丝页面，用穿过来的type判断是显示关注还是粉丝。 -->
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
<link rel="stylesheet" href="styles/styles_guide.css" type="text/css">
</head>
<body>
<jsp:include page="guide.jsp" />
<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("user/autoLogin_execute");
	}
%>
<div style="height:100px; width:100%"></div>
<div class="top"> 
<input type="text"  style="font-size:14px; margin-left:30px;">
<input type="button" value="S" style="font-size:18px">earch
</div>
<a id="followersTitleA" href="javascript:void(0)" title="click to show followers."><div class="Follow">Followers</div></a>
<a id="followingTitleA" href="javascript:void(0)" title="click to show following."><div class="Follow">Following</div></a>

<input id="type" type="hidden" value="<%=request.getParameter("type") %>"/>
<input id="hasBeenModify" type="hidden" value="0"/>
<input id="userId" type="hidden" value="<s:property value='#session.user.id'/>"/>
<div class="frame" id="context">

	<div id="followingContext"></div>
	<div id="hasFollowing"></div>
	<div id="followersContext"></div>
	<div id="hasFollowers"></div>
	
   </div>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script src="js/jquery.cssemoticons.js" type="text/javascript"></script>
<script src="js/goUrl.js" type="text/javascript"></script>
<script src="js/modernizr.js" type="text/javascript"></script>
<script src="js/user/followOperationSmall.js" type="text/javascript"></script>
<script src="js/user/friend.js" type="text/javascript"></script>
</body>
</html>

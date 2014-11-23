<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
<base href="<%=basePath%>">

<title>Message</title>
<link href="styles/message.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="styles/jquery.cssemoticons.css" media="screen"/>
</head>
<body>

<div id="M">
<div class="border_radius">
  <h2>Message</h2>
  <h3>Here you can reply messages that you have received </h3>
 <!--以下为收到的评论与对应评论的人的头像-->
 <br/>
 <a href="javascript:void(0)">照片评论</a>|<a href="javascript:void(0)">BBS评论</a>|<a href="javascript:void(0)">好友私信</a><br/>
-------------------------------------


<!--评论-->
<div id="photoReplyDiv"></div>

<!--反馈版块-->
  <h2>FEEDBACK</h2>
  <h3>please contact us if you have any questions</h3> 
  <h3> yearbook@soy.com</h3>
 
</div>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/goUrl.js" ></script>
<script type="text/javascript" src="js/jquery.cssemoticons.js" ></script>
<script type="text/javascript" src="js/calculateDistanceTime.js" ></script>
<script type="text/javascript" src="js/getMessage.js" ></script>
</body>
</html>
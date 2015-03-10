<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 查看Message的页面 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Feedback</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/feedback.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="styles/styles_guide.css" type="text/css">
<jsp:include page="guide.jsp" />



</head>
<body>

<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("/YearBook/user/autoLogin_execute");
	}
%>

<div style="height:100px; width:100%"></div>
<div class="subject">
<div class="border_radius">

  <h2>Feedback</h2>
  <h3>No matter what problem you have, just contact us </h3>
  
 <!--以下为收到的评论与对应评论的人的头像-->
 <br/>
 <div align="center">

<textarea rows="15" cols="60"></textarea>
<br/>
<input type="button" value="Send" onclick="updateCheck()"
				style="font-size:18px;margin:0 0 50px 0;">
				
<h3>In case you need any assistance using our website,get in touch with us by sending an email to</h3><h4> Yearbook@xxx.com</h4>				
				 
</div>



</div></div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/goUrl.js" ></script>
<script type="text/javascript" src="js/at.js" ></script>
<script type="text/javascript" src="js/jquery.cssemoticons.js" ></script>
<script type="text/javascript" src="js/calculateDistanceTime.js" ></script>
<script type="text/javascript" src="js/getMessage.js" ></script>

</body>
</html>
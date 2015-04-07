<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 用户图像上传页面 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>HeadPhoto Upload</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="styles/styles_guide.css" type="text/css">
<style type="text/css">
h1 {
	margin-top: 0px;
	color: #234;
	text-align: center;
	text-shadow:1px 1px 3px #fff;
}
h3 {
	margin-top: 32px;
	color: #234;
	text-align: center;
}
body{
	background-image:url(images/background_main.png);
	background-repeat: repeat;
}
</style>
</head>
<body>
<jsp:include page="guide.jsp" />
<div style="height:100px; width:100%"></div>
	<%
		if (session.getAttribute("user") == null) {
			response.sendRedirect("user/User_autoLogin");
		}
	%>
	<div style="width:630px;margin: 0 auto;">
		<h1>Year Book</h1>
		<h3>Please choose your photo.</h3>
		<div>
			<p id="swfContainer">
				本组件需要安装Flash Player后才可使用，请从<a
					href="http://www.adobe.com/go/getflashplayer">这里</a>下载安装。
			</p>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>js/swfobject.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/fullAvatarEditor.js"></script>
</body>
</head>
</html>

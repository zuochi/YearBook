<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 旧页面、无用 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>welcome to login!</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="styles/my.css" />
<link href="styles/photo.css" rel="stylesheet" type="text/css" />
</head>
<body >

	<br />
	<div id="secretbg"></div>
	<div id="secret" style="display:none;">
		<h1>
			<input id="close_secret" type="button" style="height:20px;width:20px; color:#234; font-size:15px; float:right;" title="退出" value="X" 
				onclick="close_secret()" />
		</h1>
		<!--警告提示-->
	</div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/user/my.js"></script>

</body>
</html>

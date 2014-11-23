<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>上存图片</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="styles/webuploader.css" />
<link rel="stylesheet" type="text/css"
	href="styles/style_upload.css" />
</head>
<body>
<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("/YearBook/user/autoLogin_execute");
	}
%>
	<div id="wrapper">
		<div id="container">
			<!--头部，相册选择和格式选择-->
			<div id="uploader">
				<div class="queueList">
					<div id="dndArea" class="placeholder">
						<div id="filePicker"></div>
						<p>或将照片拖到这里，单次最多可选300张</p>
					</div>
				</div>
				<div class="statusBar" style="display:none;">
					<div class="progress">
						<span class="text">0%</span> <span class="percentage"></span>
					</div>
					<div class="info"></div>
					<div class="btns">
						<div id="filePicker2"></div>
						<div class="uploadBtn">开始上传</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/webuploader.js"></script>
	<script type="text/javascript" src="js/upload.js"></script>
</body>
</html>

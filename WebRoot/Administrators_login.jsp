<%@ page import="bean.User"%>
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

<title>Administrator </title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="styles/Administrator.css" />
</head>
<body>
<div class="form-wrapper">
	<div class="top">
	<div class="colors">
	</div>
	</div>
	
	<h1 class="h1h">Administrator</h1>
	<form name="form" action="#">
		<div class="form">
			<div class="form-item">
				<input type="text" name="text" required="required" placeholder="Name" autocomplete="off">
			</div>
			<div class="form-item">
			<input type="password" name="password" required="required" placeholder="Password" autocomplete="off">
</div>
			<div class="button-panel">
			<input type="submit" class="button" title="Sign In" value="Login">
				<ul>
			<li class="lil">
					<p class="view">
						<a href="http://localhost:8080/YearBook/" class="ref">View on YearBook</a>
					</p>
			

		</li>
		
		

			</li>
				</ul>
		

	</div>
		</div>
	</form>
</div>
<script>solid()

</script>
</body>
</html>

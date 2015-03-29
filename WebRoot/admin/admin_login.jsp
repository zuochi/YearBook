<%@ page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<h3><span style="color:red">${result}</span></h3>
	<form action="admin/loginA_execute" method="post">
		<div class="form">
			<div class="form-item">
				<input type="text" name="user.userName" required="required" placeholder="Name" autocomplete="off"/>
			</div>
			<div class="form-item">
				<input type="password" name="user.password" required="required" placeholder="Password" autocomplete="off"/>
			</div>
			<div class="button-panel">
				<input type="submit" class="button" title="Sign In" value="Login">
				<ul>
					<li class="lil">
						<p class="view">
							<a href="index.jsp" class="ref">View on User Side</a>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
</body>
</html>

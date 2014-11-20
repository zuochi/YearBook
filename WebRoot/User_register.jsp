<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>welcome to register！</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="styles/my.css" />
</head>
<body>
	<!--注册界面-->
	<div id="registerbg"></div>
	<div id="register" style="display:none;">
		<h1>
			<input id="close_register" type="button" title="退出注册" value="x"
				onclick="close_register()" />
		</h1>
		<!--警告提示-->
		<div id="Signwarn">&nbsp;</div>
		<form id="regForm" method="post">
			<!--ID文本框提示-->
			<div id="username1">
				<input
					onblur="isDuplicationOfName(this.value)"
					onkeyup="keyup(event)"
					onfocus="this.style.color='#666';if(this.value=='ID') this.value='';"
					value="ID" maxLength=35 size=12 class="search_keyword"  name="user.userName" id="user.userName" onkeydown="registerEnter()"/>
			</div>
			<!--Password文本框提示-->
			<div id="focus">
				<div id="password1">
					<label><span>Password</span><input type="password" class="input_txt" name="user.password" id="user.password" onkeydown="registerEnter()"/>
					</label>
				</div>
				<div id="password2">
					<label><span>Confirm Password</span><input type="password"
						class="input_txt" name="passwordConfirm" id="passwordConfirm" onkeydown="registerEnter()"/>
					</label>
				</div>
			</div>
			<!--end password-->
			<!--注册按钮-->
			<div>
				<input id="fnregister" type="button" onclick="registerCheck()"
					style="color:#FFF" value="Sign Up" />
			</div>
			<s:token></s:token>
		</form>
	</div>
 <script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/my.js"></script>
</body>
</html>

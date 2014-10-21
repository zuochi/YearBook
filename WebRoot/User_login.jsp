<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<title>welcome to login!</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="styles/my.css" />
</head>
<body>
	<!--登录界面-->
	<br />
	<div id="loginbg"></div>
	<div id="login" style="display:none;">
		<h2>
			<input id="close_login" type="button" title="退出登录" value="x"
				onclick="close_login()" />
		</h2>
		<!--警告提示-->
		<div id="Loginwarn">&nbsp;</div>
		<form id="logForm" method="post">
			<!--ID文本框提示-->
			<div id="username">
				<input
					onkeyup="keyup(event)"
					onfocus="this.style.color='#666';if(this.value=='ID') this.value='';"
					value="ID" maxLength="35" size="12" class="search_keyword" name="userName" id="userName">
			</div>
			<!--Password文本框提示-->
			<div id="focus">
				<div id="password">
					<label><span>Password</span><input type="password" class="input_txt" name="password" id="ps">
					</label>
				</div>
			</div>
			<div id="reme">
				<input type="checkbox" name="auto_login" />自动登录
			</div>
			<div>
				<input id="fnlogin" type="button" onclick="loginCheck()"
					style="color:#FFF" value="Log In" />
			</div>
		</form>
	</div>
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="js/.my.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#focus .input_txt").each(function() {
			var thisVal = $(this).val();
			//判断文本框的值是否为空，有值的情况就隐藏提示语，没有值就显示
			if (thisVal != "") {
				$(this).siblings("span").hide();
			} else {
				$(this).siblings("span").show();
			}
			//聚焦型输入框验证
			$(this).focus(function() {
				$(this).siblings("span").hide();
			}).blur(function() {
				var val = $(this).val();
				if (val != "") {
					$(this).siblings("span").hide();
				} else {
					$(this).siblings("span").show();
				}
			});
		});
	});
</script>
</body>
</html>

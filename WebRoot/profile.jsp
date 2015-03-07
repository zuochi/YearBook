<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 用户资料修改页面，profile -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My Profile - YearBook</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/profile.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="styles/styles_guide.css" type="text/css">
</head>
<body>
<jsp:include page="guide.jsp" />
	<%
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/YearBook/user/autoLogin_execute");
		}
	%>
	<div style="height:100px; width:100%"></div>
	<form class="border_radius" id="focus">
		<input type="hidden" name="user.id" value="<s:property value="#session.user.id"/>" />
		<input type="hidden" id="nameOld" name="nameOld" value="<s:property value="#session.user.name"/>" />
		<input type="hidden" id="user.passwordOld" name="user.passwordOld" value="<s:property value="#session.user.password"/>" />
		<input type="hidden" name="user.signupDate" value="<s:property value="#session.user.signupDate"/>" />
		<input type="hidden" name="user.lastLogintime" value="<s:property value="#session.user.lastLogintime"/>" />
		<h2>Profile</h2>
		<h3>please complete your information</h3>
		<h4>*必填</h4>
		<label><span>nickName</span><input type="text" class="input_txt border_radius" onblur="isDuplicationOfName(this.value)" id="user.name" name="user.name" value="<s:property value="#session.user.name"/>" /> </label>
		<label><span>Old
				Password</span><input type="password" id="passwordOld" name="passwordOld"
			class="input_txt border_radius"> </label> <label><span>New
				Password</span><input type="password" id="user.password"
			name="user.password" class="input_txt border_radius"> </label> <label><span>Confirm
				Password</span><input type="password" id="passwordConfirm"
			class="input_txt border_radius"> </label> <label><span>QQ
		</span><input type="text" class="input_txt border_radius" id="user.qq"
			name="user.qq" value="<s:property value="#session.user.qq"/>">
		</label> <label><span>WeChat</span><input type="text"
			class="input_txt border_radius" id="user.weChat" name="user.weChat"
			value="<s:property value="#session.user.weChat"/>"> </label>
		<h4>*必填</h4>
		<label><span>E-mail</span><input type="text"
			class="input_txt border_radius" id="user.email" name="user.email"
			value="<s:property value="#session.user.email"/>"> </label>
		<h4>*必填</h4>
		<!--专业选择和性别-->
		<div id="option">
				
				<select style="font-size:16px;" name="user.schoolYear.id" id="select3">
				<option value="">School year&nbsp;&nbsp;
				</option>
				<s:iterator value="#request.schoolYears" id="o">
					<option
						<s:if test="#session.user.schoolYear.id==#o.id">
								selected="selected"
							</s:if>
						value="<s:property value="#o.id" />">
						<s:property value="#o.year" />
					</option>
				</s:iterator>
				</select>
			
			<select style="font-size:16px;" name="user.profession.id" id="select"
				tabindex="2">
				<option value="">Profession&nbsp;&nbsp;
</option>
				<s:iterator value="#request.professions" id="o">
					<option
						<s:if test="#session.user.profession.id==#o.id">
								selected="selected"
							</s:if>
						value="<s:property value="#o.id" />">
						<s:property value="#o.name" />
					</option>
				</s:iterator>
			</select>
			
			<select style="font-size:16px;" name="user.sex" id="select2">
				<option value="">Gender&nbsp;&nbsp;
					&nbsp;</option>
				<option
					<s:if test="#session.user.sex==1">
								selected="selected"
					</s:if>
					value="1">男</option>
				<option
					<s:if test="#session.user.sex==0">
								selected="selected"
					</s:if>
					value="0">女</option>
			</select>
		</div>

		<!--资料填写有误提示-->
		<h5 id="warn"></h5>
		<div id="save">
			<input type="button" value="Save" onclick="updateCheck()"
				style="font-size:18px">
		</div>
	</form>
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/profile.js"></script>
</body>
</html>
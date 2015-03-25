<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- 用户资料修改页面，profile -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>User Profile - YearBook</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/profile.css" rel="stylesheet" type="text/css" />
<link href="styles/guanli.css" rel="stylesheet"  type="text/css">
</head>
<body>
<jsp:include page="/admin/guidce.jsp"></jsp:include>
<form class="border_radius" id="focus">
	<input type="hidden" name="user.id" value="${obj.id}" />
	<input type="hidden" id="nameOld" value="${obj.name}" />
	<input type="hidden" name="user.password" value="${obj.password}">
	<h3>修改用户资料</h3>
	<h4>*必填</h4>
	<label><span>nickName</span><input type="text" class="input_txt border_radius" onblur="isDuplicationOfName(this.value)" id="user.name" name="user.name" value="${obj.name}" /> </label>
	<label><span>Home Town</span><input type="text" class="input_txt border_radius" id="user.homeTown" name="user.homeTown" value="${obj.homeTown}"></label>
	<label><span>TelePhone</span><input type="text" class="input_txt border_radius" id="user.telePhone" name="user.telePhone" value="${obj.telePhone}"></label>
	<label><span>QQ</span><input type="text" class="input_txt border_radius" id="user.qq" name="user.qq" value="${obj.qq}"></label>
	<label><span>WeChat</span><input type="text" class="input_txt border_radius" id="user.weChat" name="user.weChat" value="${obj.weChat}"></label>
	<label><span>Sign</span><input type="text" class="input_txt border_radius" id="user.sign" name="user.sign" value="${obj.sign}"></label>
	<h4>*必填</h4>
	<label><span>E-mail</span><input type="text" class="input_txt border_radius" id="user.email" name="user.email" value="${obj.email}"></label>
	<h4>*必填</h4>
	<!--专业选择和性别-->
	<div id="option">
	<select style="font-size:16px;" name="user.schoolYear.id" id="select3">
	<option value="">School year&nbsp;&nbsp;
	</option>
	<s:iterator value="#request.schoolYears" id="o">
		<option
			<s:if test="#request.obj.schoolYear.id==#o.id">
					selected="selected"
			</s:if>
			value="<s:property value="#o.id" />">
			<s:property value="#o.year" />
		</option>
	</s:iterator>
	</select>
		
	<select style="font-size:16px;" name="user.profession.id" id="select"
		tabindex="2">
		<option value="">Profession&nbsp;&nbsp;</option>
		<s:iterator value="#request.professions" id="o">
			<option
				<s:if test="#request.obj.profession.id==#o.id">
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
			<s:if test="#request.obj.sex==1">
						selected="selected"
			</s:if>
			value="1">男</option>
		<option
			<s:if test="#request.obj.sex==0">
						selected="selected"
			</s:if>
			value="0">女</option>
	</select>
	
	<select style="font-size:16px;" name="user.isDelete" id="selectStatus">
		<option value="">Status&nbsp;&nbsp;
			&nbsp;</option>
		<option
			<s:if test="#request.obj.isDelete==0">
						selected="selected"
			</s:if>
			value="0">正常
		</option>
		<option
			<s:if test="#request.obj.isDelete==1">
						selected="selected"
			</s:if>
			value="1">注销
		</option>
	</select>
	</div>

	<!--资料填写有误提示-->
	<h5 id="warn"></h5>
	<div id="save">
		<input type="button" value="Save" onclick="updateCheck()" style="font-size:18px">
	</div>
</form>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/admin/profile.js"></script>
</body>
</html>
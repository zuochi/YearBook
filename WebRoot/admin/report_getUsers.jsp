<%@ page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 后台管理页面 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Year Book - 用户管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/guanli.css" rel="stylesheet"  type="text/css">
</head>
<body>
<jsp:include page="/admin/guidce.jsp"></jsp:include>

<form id="form" action="admin/getUsers_execute">
<%--高级搜索 --%>
<br>
<div align="center">
账号:<input type="text" name="user.userName" value="${params.userName}">
昵称:<input type="text" name="user.name" value="${params.name}">
状态:<select name="user.isDelete">
		<option value="">--全部--</option>
		<option <c:if test="${params.isDelete==0}">selected="selected"</c:if> value="0">--正常--</option>
		<option <c:if test="${params.isDelete==1}">selected="selected"</c:if> value="1">--注销--</option>
	</select>
性别:<select name="user.sex">
		<option value="">--全部--</option>
		<option <c:if test="${params.sex==1}">selected="selected"</c:if> value="1">--男--</option>
		<option <c:if test="${params.sex==0}">selected="selected"</c:if> value="0">--女--</option>
	</select>
专业:<select name="user.profession.id">
			<option value="">--全部--</option>
		<c:forEach var="o" items="${professions}">
			<option <c:if test="${params.profession.id==o.id}">selected="selected"</c:if> value="${o.id}">${o.name}</option>
		</c:forEach>
	</select>
学年:<select name="user.schoolYear.id">
			<option value="">--全部--</option>
		<c:forEach var="o" items="${schoolYears}">
			<option <c:if test="${params.schoolYear.id==o.id}">selected="selected"</c:if> value="${o.id}">${o.year}</option>
		</c:forEach>
	</select>
<input type="submit" value="查找">
<input type="button" onclick="resetSearch()" value="重置">
<script type="text/javascript">
function resetSearch(){
	window.location.href="admin/getUsers_execute";
};
</script>
</div>
<br>
<%--高级搜索 --%>

<%--导入排序 --%>
<jsp:include page="/template/sort.jsp"></jsp:include>
<%--导入排序 --%>

<%--翻页按钮 开始 --%>
<jsp:include page="/template/pageControl.jsp"></jsp:include>
</form>
<%--翻页按钮 结束 --%>

<!-- class=main里面的内容为局部刷新内容，即每一个模块的布局都要写在main里面 -->
<div class="main">
<!-- 图片管理模块 -->
<table border="1" align="center">
<thead>
	<tr>
		<th>头像</th>
		<th><a href="javascript:orderBy('user.id')">id</a></th>
		<th><a href="javascript:orderBy('user.schoolYear.year')">学年</a></th>
		<th><a href="javascript:orderBy('user.profession.name')">专业</a></th>
		<th><a href="javascript:orderBy('user.userName')">账号</a></th>
		<th><a href="javascript:orderBy('user.name')">昵称</a></th>
		<th><a href="javascript:orderBy('user.sex')">性别</a></th>
		<th><a href="javascript:orderBy('user.qq')">QQ</a></th>
		<th><a href="javascript:orderBy('user.signupDate')">注册时间</a></th>
		<th><a href="javascript:orderBy('user.lastLogintime')">最后登录</a></th>
		<th><a href="javascript:orderBy('user.isDelete')">状态</a></th>
		<th>编辑/查看</th>
	</tr>
</thead>
<c:forEach var="obj" items="${objs}">
	<tr>
		<td><img alt="head photo" src="${obj.headPhoto.urlM}"></td>
		<td>${obj.id}</td>
		<td>${obj.schoolYear.year}</td>
		<td>${obj.profession.name}</td>
		<td>${obj.userName}</td>
		<td>${obj.name}</td>
		<td>
			<c:if test="${obj.sex==0}">女</c:if>
			<c:if test="${obj.sex==1}">男</c:if>
		</td>
		<td>${obj.qq}</td>
		<td>${obj.signupDate}</td>
		<td>${obj.lastLogintime}</td>
		<td>
			<c:if test="${obj.isDelete==0}">正常</c:if>
			<c:if test="${obj.isDelete==1}">注销</c:if>
		</td>
		<td><a target="_blank" href="admin/getUserDetail_execute?user.id=${obj.id}">编辑/查看</a></td>
	</tr>
</c:forEach>
</table>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
</body>
</html>

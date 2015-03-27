<%@page import="bean.User"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 后台管理页面 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Year Book </title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/guanli.css" rel="stylesheet"  type="text/css">
</head>
<body>
<jsp:include page="/admin/guidce.jsp"></jsp:include>

<form id="form" action="admin/getReplys_execute">
<%--高级搜索 --%>
<br>
<div align="center">
评论人编号:<input type="text" name="reply.userByUserId.id" value="${params.userByUserId.id}" onkeyup="this.value=this.value.replace(/\D/g,'')">
评论人昵称:<input type="text" name="reply.userByUserId.name" value="${params.userByUserId.name}">
删除状态:<select name="reply.isDelete">
			<option value="">全部</option>
			<option <c:if test="${params.isDelete==0}">selected="selected"</c:if> value="0">未删除</option>
			<option <c:if test="${params.isDelete==1}">selected="selected"</c:if> value="1">已删除</option>
		</select>
举报状态:<select name="reply.isAccusation">
			<option value="">全部</option>
			<option <c:if test="${params.isAccusation==0}">selected="selected"</c:if> value="0">正常</option>
			<option <c:if test="${params.isAccusation==1}">selected="selected"</c:if> value="1">被举报</option>
		</select>
<input type="submit" value="查找">
<input type="button" onclick="resetSearch()" value="重置">
<script type="text/javascript">
function resetSearch(){
	window.location.href="admin/getReplys_execute";
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
<!-- 评论管理模块 -->
<table border="1" align="center">
<thead>
	<tr>
		<!-- <th>头像</th> -->
		<th><a href="javascript:orderBy('userByUserId.id')">编号</a></th>
		<th><a href="javascript:orderBy('userByUserId.id')">评论人编号</a></th>
		<th><a href="javascript:orderBy('userByUserId.name')">评论人昵称</a></th>
		<th><a href="javascript:orderBy('context')">评论类容</a></th>
		<th><a href="javascript:orderBy('signupDate')">评论时间</a></th>
		<th><a href="javascript:orderBy('isAccusation')">状态</a></th>
		<%-- <th>忽略</th> --%>
		<th>操作</th>
	</tr>
</thead>
<c:forEach var="obj" items="${objs}">
	<tr>
		<td>${obj.id}</td>
		<td><a target="_blank" href="admin/getUserDetail_execute?user.id=${obj.userByUserId.id}" title="查看更多">${obj.userByUserId.id}</a></td>
		<td><a target="_blank" href="admin/getUserDetail_execute?user.id=${obj.userByUserId.id}" title="查看更多">${obj.userByUserId.name}</a></td>
		<td>${obj.context}</td>
		<td>${obj.signupDate}</td>
		<td>
			<c:if test="${obj.isAccusation==0}"><span style="color:green">正常</span></c:if>
			<c:if test="${obj.isAccusation==1}"><span style="color:red">被举报</span></c:if>
		</td>
		<td>
			<c:if test="${obj.isDelete==0}"><a href="javascript:void(0)" onclick="deleteReply('${obj.id}')">删除</a></c:if>
			<c:if test="${obj.isDelete==1}"><a href="javascript:void(0)" onclick="recoveryReply('${obj.id}')">恢复</a></c:if>
		</td>
	</tr>
</c:forEach>
</table>
</div>
<br>
<hr>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/goUrl.js"></script>
<script type="text/javascript" src="js/admin/reply.js"></script>
</body>
</html>

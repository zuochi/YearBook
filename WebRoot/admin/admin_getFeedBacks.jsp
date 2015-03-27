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

<form id="form" action="admin/getFeedBacks_execute">
<%--高级搜索 --%>
<br>
<div align="center">
反馈人编号:<input type="text" name="feedBack.user.id" value="${params.user.id}" onkeyup="this.value=this.value.replace(/\D/g,'')">
反馈人昵称:<input type="text" name="feedBack.user.name" value="${params.user.name}">
处理人编号:<input type="text" name="feedBack.userDeal.id" value="${params.userDeal.id}" onkeyup="this.value=this.value.replace(/\D/g,'')">
处理人昵称:<input type="text" name="feedBack.userDeal.name" value="${params.userDeal.name}">
反馈内容:<input type="text" name="feedBack.context" value="${params.context}">
删除状态:<select name="feedBack.isDelete">
			<option value="">全部</option>
			<option <c:if test="${params.isDelete==0}">selected="selected"</c:if> value="0">未删除</option>
			<option <c:if test="${params.isDelete==1}">selected="selected"</c:if> value="1">已删除</option>
		</select>
状态:<select name="feedBack.status">
			<option value="">全部</option>
			<option <c:if test="${params.status==0}">selected="selected"</c:if> value="0">未处理</option>
			<option <c:if test="${params.status==1}">selected="selected"</c:if> value="1">已处理</option>
		</select>
<input type="submit" value="查找">
<input type="button" onclick="resetSearch()" value="重置">
<script type="text/javascript">
function resetSearch(){
	window.location.href="admin/getFeedBacks_execute";
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
		<th><a href="javascript:orderBy('user.id')">反馈人编号</a></th>
		<th><a href="javascript:orderBy('user.name')">反馈人昵称</a></th>
		<th><a href="javascript:orderBy('userDeal.id')">处理人编号</a></th>
		<th><a href="javascript:orderBy('userDeal.name')">处理人昵称</a></th>
		<th><a href="javascript:orderBy('context')">反馈内容</a></th>
		<th><a href="javascript:orderBy('signupDate')">反馈时间</a></th>
		<th><a href="javascript:orderBy('dealDate')">处理时间</a></th>
		<th><a href="javascript:orderBy('status')">状态</a></th>
		<%-- <th>忽略</th> --%>
		<th>操作</th>
		<th>操作</th>
	</tr>
</thead>
<c:forEach var="obj" items="${objs}">
	<tr>
		<td><a target="_blank" href="admin/getUserDetail_execute?user.id=${obj.user.id}" title="查看更多">${obj.user.id}</a></td>
		<td><a target="_blank" href="admin/getUserDetail_execute?user.id=${obj.user.id}" title="查看更多">${obj.user.name}</a></td>
		<td><a target="_blank" href="admin/getUserDetail_execute?user.id=${obj.userDeal.id}" title="查看更多">${obj.userDeal.id}</a></td>
		<td><a target="_blank" href="admin/getUserDetail_execute?user.id=${obj.userDeal.id}" title="查看更多">${obj.userDeal.name}</a></td>
		<td>${obj.context}</td>
		<td>${obj.signupDate}</td>
		<td>${obj.dealDate}</td>
		<td>
			<c:if test="${obj.status==0}"><span style="color:red">未处理</span></c:if>
			<c:if test="${obj.status==1}"><span style="color:green">已处理</span></c:if>
		</td>
		<td>
			<c:if test="${obj.status==0}"><a href="javascript:void(0)" onclick="reviewFeedBack('${obj.id}','1')">标记为处理</a></c:if>
			<c:if test="${obj.status==1}"><a href="javascript:void(0)" onclick="reviewFeedBack('${obj.id}','0')">标记为未处理</a></c:if>
		</td>
		<td>
			<c:if test="${obj.isDelete==0}"><a href="javascript:void(0)" onclick="deleteFeedBack('${obj.id}')">删除</a></c:if>
			<c:if test="${obj.isDelete==1}"><a href="javascript:void(0)" onclick="recoveryFeedBack('${obj.id}')">恢复</a></c:if>
		</td>
	</tr>
</c:forEach>
</table>
</div>
<br>
<hr>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/goUrl.js"></script>
<script type="text/javascript" src="js/admin/feed_back.js"></script>
</body>
</html>

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
<title>Year Book - 审核“上首页”的图片</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/guanli.css" rel="stylesheet"  type="text/css">
</head>
<body>
<jsp:include page="/admin/guidce.jsp"></jsp:include>

<form id="form" action="admin/getIWantTops_execute" method="post">
<%--高级搜索 --%>
<br>
<div align="center">
申请编号:<input type="text" name="iwant.id" value="${params.id}" onkeyup="this.value=this.value.replace(/\D/g,'')">
上传人编号:<input type="text" name="iwant.user.id" value="${params.user.id}" onkeyup="this.value=this.value.replace(/\D/g,'')">
上传人昵称:<input type="text" name="iwant.user.name" value="${params.user.name}">
状态:<select name="iwant.status">
		<option value="">全部</option>
		<option <c:if test="${params.status==0}">selected="selected"</c:if> value="0">未审核</option>
		<option <c:if test="${params.status==1}">selected="selected"</c:if> value="1">通过</option>
		<option <c:if test="${params.status==2}">selected="selected"</c:if> value="2">未通过</option>
		<option <c:if test="${params.status==3}">selected="selected"</c:if> value="3">下架</option>
	</select>
<input type="submit" value="查找">
<input type="button" onclick="resetSearch()" value="重置">
<script type="text/javascript">
function resetSearch(){
	window.location.href="admin/getIWantTops_execute";
};
</script>
</div>
<br>
<div>
<table border="0" align="center">
<thead>
	<tr>
		<th>排序：</th>
		<th><a href="javascript:orderBy('id')">申请编号</a></th>
		<th><a href="javascript:orderBy('user.id')">上传人编号</a></th>
		<th><a href="javascript:orderBy('user.name')">上传人昵称</a></th>
		<th><a href="javascript:orderBy('signupDate')">申请时间</a></th>
		<th><a href="javascript:orderBy('status')">状态</a></th>
		<!-- <th>编辑/查看</th> -->
	</tr>
</thead>
</table>
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



<c:forEach var="obj" items="${objs}" >
<div class="table">
<table border="1" align="center" style="float:left;height:320px;width:560px; overflow:hidden">
	<thead>
	<tr>
		<th>上传图片预览</th>
		<th><a href="javascript:orderBy('id')">简介</a></th>
	</tr>
	</thead>

	<tr>
		<td><a target="_blank" href="ShowBigPic.jsp?url=${obj.photo.url}&name=${obj.user.name}" title="点击放大"><img style="max-height:270px;max-width:250px;overflow:hidden" src="${obj.photo.urlThumb}" title="点击放大" /></a></td>
		<td>申请编号:${obj.id}<br><br>
			上传人id:${obj.user.id}<br><br>
			上传人昵称:${obj.user.name}<br><br>
			申请时间:${obj.signupDate}<br><br>
			状态:<c:if test="${obj.status==0}"><span style="color:red">未审核</span></c:if>
				<c:if test="${obj.status==1}"><span style="color:green">通过</span></c:if>
				<c:if test="${obj.status==2}"><span style="color:orange">未通过</span></c:if>
				<c:if test="${obj.status==3}"><span style="color:black">下架</span></c:if>
			<br><br>
			操作：
				<c:if test="${obj.status==0}"><input type="button" value="通过" onclick="reviewIWantTop(${obj.id},1)"></c:if>
				<c:if test="${obj.status==2}"><input type="button" value="通过" onclick="reviewIWantTop(${obj.id},1)"></c:if>
				<c:if test="${obj.status==0}"><input type="button" value="不通过" onclick="reviewIWantTop(${obj.id},2)"></c:if>
				<c:if test="${obj.status==1}"><input type="button" value="不通过" onclick="reviewIWantTop(${obj.id},2)"></c:if>
		</td>
	</tr></table>
	</div>
</c:forEach>

</div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/admin/i_want_top.js"></script>
</body>
</html>
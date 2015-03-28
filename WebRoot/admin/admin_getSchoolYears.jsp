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
<title>Year Book - 学年管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/guanli.css" rel="stylesheet"  type="text/css">
</head>
<body>
<jsp:include page="/admin/guidce.jsp"></jsp:include>

<form id="form" action="admin/getSchoolYears_execute" method="post">
<%--高级搜索 --%>
<br>
<div align="center">
编号:<input type="text" name="schoolYear.id" value="${params.id}" onkeyup="this.value=this.value.replace(/\D/g,'')">
学年:<input type="text" name="schoolYear.year" value="${params.year}">
状态:<select name="schoolYear.isDelete">
		<option value="">--全部--</option>
		<option <c:if test="${params.isDelete==0}">selected="selected"</c:if> value="0">--启动--</option>
		<option <c:if test="${params.isDelete==1}">selected="selected"</c:if> value="1">--停用--</option>
	</select>
<input type="submit" value="查找">
<input type="button" onclick="resetSearch()" value="重置">
<script type="text/javascript">
function resetSearch(){
	window.location.href="admin/getSchoolYears_execute";
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
<!-- 管理模块 -->
<div align="center">
	输入添加的学年:<input type="text" id="add_year_year" maxlength="4" style="width:40"/>
	<select id="add_year_isDelete">
		<option value="0">启用</option>
		<option value="1">停用</option>
	</select>
	<input type="button" onclick="addSY()" value="添加"/>
</div>
<br>
<div>
<table border="1" align="center">
<thead>
	<tr>
		<th><a href="javascript:orderBy('id')">编号</a></th>
		<th><a href="javascript:orderBy('year')">学年</a></th>
		<th><a href="javascript:orderBy('isDelete')">状态</a></th>
		<th>操作</th>
	</tr>
</thead>
<c:forEach var="obj" items="${objs}">
	<tr>
		<td>${obj.id}</td>
		<td>${obj.year}</td>
		<td>
			<c:if test="${obj.isDelete==0}">启用</c:if>
			<c:if test="${obj.isDelete==1}">停用</c:if>
		</td>
		<td>
			<c:if test="${obj.isDelete==1}">
				<a href="javascript:void(0)" onclick="recoverySY(${obj.id})">启用</a>
			</c:if>
			<c:if test="${obj.isDelete==0}">
				<a href="javascript:void(0)" onclick="deleteSY(${obj.id})">停用</a>
			</c:if>
		</td>
	</tr>
</c:forEach>
</table>
</div>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
<script type="text/javascript" src="js/admin/school_year.js"></script>
</body>
</html>

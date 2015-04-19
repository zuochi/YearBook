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
<title>Year Book - 公告管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/guanli.css" rel="stylesheet"  type="text/css">
</head>
<body>
<jsp:include page="/admin/guidce.jsp"></jsp:include>

<form id="form" action="admin/getAnnouncements_execute" method="post">
<%--高级搜索 --%>
<br>
<div align="center" style="font-size:14px;">

编号:<input type="text" size=4 name="announcement.id" value="${params.id}" onkeyup="this.value=this.value.replace(/\D/g,'')">
标题:<input type="text" size=8 name="announcement.title" value="${params.title}">
内容:<input type="text" size=14 name="announcement.context" value="${params.context}">
发起人编号:<input type="text" size=4 name="announcement.user.id" value="${params.user.id}" onkeyup="this.value=this.value.replace(/\D/g,'')">
发起人昵称:<input type="text" size=8 name="announcement.user.name" value="${params.user.name}">
<%-- 置顶编号:<input type="text" name="announcement.topId" value="${params.topId}" onkeyup="this.value=this.value.replace(/\D/g,'')" style="width:30px" maxlength="3"> --%>
<%-- 是否置顶:<select name="announcement.top">
			<option value="">全部</option>
			<option <c:if test="${params.top==1}">selected="selected"</c:if> value="1">是</option>
			<option <c:if test="${params.top==0}">selected="selected"</c:if> value="0">否</option>
		</select> --%>
状态:<select name="announcement.isDelete">
		<option value="">全部</option>
		<option <c:if test="${params.isDelete==0}">selected="selected"</c:if> value="0">启用</option>
		<option <c:if test="${params.isDelete==1}">selected="selected"</c:if> value="1">停用</option>
	</select>
<input type="submit" value="查找">
<input type="button" onclick="resetSearch()" value="重置">
<script type="text/javascript">
function resetSearch(){
	window.location.href="admin/getAnnouncements_execute";
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

<%-- 添加模板 --%>
<div align="center">
	<input type="button" id="add_announcement_button1" onclick="showAdd()" value="添加"/>
	<table id="add_announcement_div" style="display:none">
		<tr>
			<td>标题:</td>
			<td><input type="text" id="add_announcement_title" maxlength="10"></td>
		</tr>
		<tr>
			<td>内容:</td>
			<td><textarea style="width:173px;height:50px" id="add_announcement_context"></textarea></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<%-- <select id="add_announcement_top">
					<option value="0">不置顶</option>
					<option value="1">-置顶-</option>
				</select>--%>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<select id="add_announcement_isDelete">
					<option value="0">启用</option>
					<option value="1">停用</option>
				</select> 
				<input type="button" id="add_announcement_button2" onclick="addAnnouncement()" value="添加"  style="float:right"/>
				<input type="button" onclick="hideAdd()" value="取消"/>
			</td>
		</tr>
	</table>
</div>
<%-- 添加模板 --%>

<div>
<table border="1" align="center">
<thead>
	<tr>
		<th><a href="javascript:orderBy('id')">编号</a></th>
		<th><a href="javascript:orderBy('user.id')">发起人编号</a></th>
		<th><a href="javascript:orderBy('user.name')">发起人昵称</a></th>
		<th><a href="javascript:orderBy('title')">标题</a></th>
		<th><a href="javascript:orderBy('context')">内容</a></th>
		<th><a href="javascript:orderBy('signupDate')">创建时间</a></th>
		<%-- <th><a href="javascript:orderBy('topId')">置顶编号</a></th> --%>
		<th><a href="javascript:orderBy('isDelete')">状态</a></th>
		<th>操作</th>
		<th>编辑</th>
		<th><a href="javascript:orderBy('topDate')">置顶顺序</a></th>
	</tr>
</thead>
<c:forEach var="obj" items="${objs}">
	<tr id="announcement_trid_${obj.id}">
		<td>${obj.id}</td>
		<td>${obj.user.id}</td>
		<td>${obj.user.name}</td>
		<td>${obj.title}</td>
		<td>${obj.context}</td>
		<td>${obj.signupDate}</td>
		<%-- <td>
			<c:if test="${obj.top==0}">否</c:if>
			<c:if test="${obj.top==1}">是</c:if>
		</td> --%>
		<%-- <td>${obj.topId}</td> --%>
		<td>
			<c:if test="${obj.isDelete==0}">启用</c:if>
			<c:if test="${obj.isDelete==1}">停用</c:if>
		</td>
		<td>
			<c:if test="${obj.isDelete==1}">
				<a href="javascript:void(0)" onclick="recoveryAnnouncement(${obj.id})">启用</a>
			</c:if>
			<c:if test="${obj.isDelete==0}">
				<a href="javascript:void(0)" onclick="deleteAnnouncement(${obj.id})">停用</a>
			</c:if>
		</td>
		<td><a href="javascript:void(0)" onclick="loadAnnouncement(${obj.id})">编辑</a></td>
		<td align="center"><a href="javascript:void(0)" onclick="overheadAnnouncement(${obj.id})">置顶</a></td>
	</tr>
</c:forEach>
</table>
</div>

</div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
<script type="text/javascript" src="js/admin/announcement.js"></script>
</body>
</html>
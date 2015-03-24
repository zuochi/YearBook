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
<%--翻页按钮 开始 --%>
<form id="form" action="admin/getIWantTops_execute">
<jsp:include page="/template/pageControl.jsp"></jsp:include>
</form>
<%--翻页按钮 结束 --%>
<!-- class=main里面的内容为局部刷新内容，即每一个模块的布局都要写在main里面 -->
<div class="main">
<!-- 评论管理模块 -->
评论管理

<c:forEach var="obj" items="${objs}">
	<div class="no">${obj.name}
		<div class="comment">
			<div class="pic">
				<img alt="" src="${obj.url_m}">
			</div>
			<div class="neirong">${obj.context}</div>
			<div><a href="javascript:void(0)" onclick="deleteReply('${obj.id}')">删除</a></div>
			<div><a href="javascript:void(0)" onclick="missReply('${obj.id}')">忽略</a></div>
		</div>
		
	</div>
	<br>
</c:forEach>
<div class="no">NO.2
<div class="comment">
<div class="pic">头像</div>
<div class="neirong">测试一下</div>
</div>
</div>
<br>
<hr>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
<script type="text/javascript" src="js/admin/reply.js"></script>
<script type="text/javascript">
$(function(){
	var $ctr = $('#ctr');
    $ctr.imagesLoaded( function(){
      $ctr.masonry({
        itemSelector : '.box',
		isFitWidth: true,
		isAnimated: true
      });
    });
});
</script>
</body>
</html>

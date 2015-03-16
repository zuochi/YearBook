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
<title>Year Book - 处理举报的图片</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/guanli.css" rel="stylesheet"  type="text/css">
 <script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
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
	  $(document).ready(function() {
		$("#random").skippr();
		$("#random2").skippr({
		navType: 'bubble',
		autoPlay: true,
		autoPlayDuration: 2000
		});
	});
	</script>

</head>

<body>
<jsp:include page="/admin/guidce.jsp"></jsp:include>
<%--翻页按钮 开始 --%>
<form id="form" action="admin/getIWantTops_execute">
<input type="hidden" id="toPage" name="toPage" value="1">
<jsp:include page="/template/pageControl.jsp"></jsp:include>
</form>
<%--翻页按钮 结束 --%>
<!-- class=main里面的内容为局部刷新内容，即每一个模块的布局都要写在main里面 -->
<div class="main">

<!-- 图片管理模块 -->
<div id="ctr">
	<c:forEach var="obj" items="${objs}">
		<div class="box photo col1">
			<img src="${obj.urlThumb}" alt="Stanley" />
			<div class="likes_icon"><div class="like_number"><h2>删除</h2></div></div>
		</div>
	</c:forEach>
</div>
<!-- <div id="ctr">
		<div class="box photo col1">
			<img src="images/1.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number"><h2>删除</h2></div></div>
		</div>
		<div class="box photo col1">
			<img src="images/2.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number"><h2>删除</h2></div></div>
		</div>
				<div class="box photo col1">
			<img src="images/1.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number"><h2>删除</h2></div></div>
		</div>
</div> -->

<hr>
</div>
</body>
</html>

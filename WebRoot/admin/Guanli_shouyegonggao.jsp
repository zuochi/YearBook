<%@page import="bean.User"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
<!-- class=main里面的内容为局部刷新内容，即每一个模块的布局都要写在main里面 -->
<div class="main">


<!-- 首页公告模块 -->
首页公告



<div class="tagline ">
 <h3>Tips:</h3>
  <p>Show your Pic here <br> Click the top nearby your photos.</p>                  
</div>

<hr>



</div>
</body>
</html>

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
<div style="width:100%;height:7%;background:#3ab6ae; ">

<div class="top"><a href="Guanli_tupianshenhe.jsp">图片审核</a></div>
<div class="top"><a href="Guanli_shouyegonggao.jsp" >首页公告</a></div>
<div class="top"><a href="Guanli_pinglunguanli.jsp" >评论管理</a></div>
<div class="top"><a href="Guanli_tupianguanli.jsp" >图片管理</a></div>
<div class="top2">BBs管理</div>

</div>
<!-- class=main里面的内容为局部刷新内容，即每一个模块的布局都要写在main里面 -->
<div class="main">

<!-- bbs管理模块 -->
bbs管理


<div class="no">NO.1
<div class="comment">
<div class="pic">头像</div>
<div class="neirong">测试一下</div></div></div>
<br>

<div class="no">NO.2
<div class="comment">
<div class="pic">头像</div>
<div class="neirong">测试一下</div>

</div>
</div>
<br>
<hr>



</div>
</body>
</html>

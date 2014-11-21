<%@page import="bean.User"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
<style type="text/css">
body{background-image:url(images/NO.3.jpg);background-repeat: repeat;;margin:0;}
a:hover{color:#fff;}
h1{float:left;font-size:13px;font-weight:normal;text-align:left;height:15px;width:45px;margin:0px;}
h1:hover{float:left;font-size:13px;font-weight:normal;text-align:left;height:15px;width:45px;margin:0px;color:#3ab6ae;}
h2{float:right;font-size:12px;font-weight:normal;text-align:right;height:15px;width:35px;margin:0px;}
h2:hover{float:right;font-size:12px;font-weight:normal;text-align:right;height:15px;width:35px;margin:0px;color:#3ab6ae;}

.top{float:left;width:25%;height:40px;color:#3ab6ae;font-size:27px;background:#fff;text-align:center;}
.top:hover{float:left;width:25%;height:40px;color:#fff;font-size:27px;background:#3ab6ae;text-align:center;}
.main{height:90%;width:100%;}
#ctr {
	font-size: 1em;
	border-radius: 5px;
	width: 100%;
	position: relative;
	clear: both;
	padding-top:20px;
	margin-top: auto;
	margin-right: auto;
	margin-bottom: auto;

}

.box h3 {text-align:center;margin:0 auto;}
#ctr ul li {list-style:none}
#ctr ol li {list-style: none}
.centered { margin: 0 auto; }

.box {
  margin: 8px;
  padding: 5px;
  background: #fff;
  border: 1px solid #ccc;
  font-size: 100%;
  line-height: 1.4em;
  float: left;
}

.box h2 {
  font-size: 14px;
  font-weight: 200;
}

.box img,
#tumblelog img {
  display: block;
  width: 100%;
}

.rtl .box {
  float: right;
  text-align: right; 
  direction: rtl;
}

.col1 { width: 120px; }
.col2 { width: 180px; }
.col3 { width: 267px; }
.col4 { width: 380px; }
.col5 { width: 480px; }

.col1 img { max-width: 120px; }
.col2 img { max-width: 180px; }
.col3 img { max-width: 267px; }
.col4 img { max-width: 380px; }
.col5 img { max-width: 480px; }

</style>
</head>

<body>
<div style="width:100%;height:7%;background:#3ab6ae; ">

<div class="top"><a>图片审核</a></div>
<div class="top"><a>评论管理</a></div>
<div class="top"><a>图片管理</a></div>
<div class="top"><a>BBS管理</a></div>
</div>
<!-- class=main里面的内容为局部刷新内容，即每一个模块的布局都要写在main里面 -->
<div class="main">

<!-- 图片审核模块 -->
<div id="ctr">
		<div class="box photo col1">
			<img src="images/1.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number"><h1>上首页</h1><h2>删除</h2></div></div>
		</div>
		<div class="box photo col1">
			<img src="images/2.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number"><h1>上首页</h1><h2>删除</h2></div></div>
		</div>
				<div class="box photo col1">
			<img src="images/1.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number"><h1>上首页</h1><h2>删除</h2></div></div>
		</div>
</div>

<!-- 评论管理模块 -->





</div>
</body>
</html>

<%@ page import="bean.User"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- Home 首页 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/csshake.css" rel="stylesheet" type="text/css"/>
<link href="styles/reset.css" rel="stylesheet"  type="text/css"/>
<link href="styles/styles.css" rel="stylesheet" type="text/css"/>
<link href="styles/jquery.skippr.css" rel="stylesheet" type="text/css"/>
<jsp:include page="guide.jsp" />
<jsp:include page="styles/my.css" />
<title>Camereeye</title>
</head>
<%if("login".equals(request.getParameter("type"))){
	
	%>
<body onload="open_login();">
<%
	} else if("signup".equals(request.getParameter("type"))){
%>
<body onload="open_register();">
<%
	} else{
%>
<body>
<%
	}
%>

<!-- top -->
<!--  <div style="position:fixed; top:0px; left:10px; width:100px; height:100px; border:solid 2px blue;">ssssssssssssssssssssss</div> -->
	<!--滚动图-->

<jsp:include page="template/user/loadCoverAndAnnouncement.jsp"></jsp:include>	  

<jsp:include page="template/user/loadProfession.jsp"></jsp:include>

<jsp:include page="template/user/loadPhoto.jsp"></jsp:include>
	
<div id="noMore" style="font-size:x-large;text-align:center;color:#8f8f8f;font-weight:normal;padding:20px 0 50px 0;display:none">oops,there are no more photos.</div>
    <!--流布局-->
    <!--  <div id="pic">
	<div id="ctr">
		
	</div></div> -->
	<!-- END container -->
	
	
	<jsp:include page="User_login.jsp" />
	<jsp:include page="User_register.jsp" />

 	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/jquery.skippr.js"></script>
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="js/jquery.animate-colors-min.js"></script>
	<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
	<script type="text/javascript">
		$(function(){ 
			var $ctr = $('#ctr');
			$ctr.imagesLoaded( function(){ 
				$ctr.masonry({ 
					itemSelector : '.box', 
					isFitWidth: true,//// 是否可调整大小 Boolean 
					isRTL:false, ////使用从右到左的布局 Boolean
					isAnimated:true
				}); 
			});
		});
	
	  $(document).ready(function() {
		//异步读取内容
		loadProfessions();
		//读取封面以及公告
		loadAnnouncementAndCover();
	});
	</script>
</body>
</html>

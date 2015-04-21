<%@ page import="bean.User"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/csshake.css" rel="stylesheet" type="text/css"/>
<link href="styles/reset.css" rel="stylesheet"  type="text/css"/>
<link href="styles/styles.css" rel="stylesheet" type="text/css"/>
<link href="styles/jquery.skippr.css" rel="stylesheet" type="text/css"/>
<jsp:include page="../styles/my.css" />
<title>Camereeye - preview</title>
</head>
<body>
<div class="hero">
	<div id="random">
		<div style="background-image: url(<%=request.getParameter("url")%>)">
			<div class="tagline">
		    	<h3>Welcome1:</h3>
		    	<p>Show your Pic here <br> Click the top nearby your photos.</p>
		    </div> 
	    </div>
		<%-- <div style="background-image: url(images/slide1.jpg)">
			<div class="tagline">
		    	<h3>Welcome1:</h3>
		    	<p>Show your Pic here <br> Click the top nearby your photos.</p>
		    </div> 
	    </div>
	    
	    <div style="background-image: url(images/slide2.jpg)"> 
	    	<div class="tagline">
	    		<h3>Welcome2:</h3>
	    		<p>Show your Pic here <br> Click the top nearby your photos.</p>
			</div> 
	    </div> 
	    
	    <div style="background-image: url(images/slide3.jpg)"></div>                   
	    <div style="background-image: url(images/slide4.jpg)"></div> --%>
	</div>
 </div>

<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.skippr.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.animate-colors-min.js"></script>
<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
	//滚动图		
		$("#random").skippr();
		$("#random2").skippr({
			navType: 'bubble',
			autoPlay: true,
			autoPlayDuration: 2000
		});
});
</script>
</body>
</html>

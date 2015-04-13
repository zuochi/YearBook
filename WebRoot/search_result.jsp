<%@page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 搜索结果页面。 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Year Book - Search '${keyword}'</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="styles/search_result.css" />
<link rel="stylesheet" type="text/css" href="styles/jquery.cssemoticons.css" media="screen"/>
<link rel="stylesheet" href="styles/styles_guide.css" type="text/css">
</head>
<body>
<jsp:include page="guide.jsp" />
<%
	/* if (session.getAttribute("user") == null) {
		response.sendRedirect("user/autoLogin_execute");
	} */
%>
<div style="height:100px; width:100%"></div>
<form action="user/search_execute" method="post" id="form">

<jsp:include page="template/user/search.jsp"></jsp:include>
<jsp:include page="template/pageControl.jsp"></jsp:include>
<div id="result" class="result">
<c:forEach var="obj" items="${objs}">
	<div class="user">
	<div class="headphoto"><a href="javascript:void(0)" onclick="goSocialIndex(${obj.id})"><img style="border-radius:50%;" alt="show more" src="${obj.url_l}"height="70" width="70" ></a></div>
	<div class="information">
		<div class="left2">
			<h1 style=" padding:0 0 0 20px;">
				<a  href="javascript:void(0)" onclick="goSocialIndex(${obj.id})" title="show more">
					<c:if test="${fn:length(obj.name)<=10}">${obj.name}</c:if>
					<c:if test="${fn:length(obj.name)>10}">No name</c:if>
				</a>
			</h1>
			<c:if test="${obj.sex==1}">
				<div class="male">male</div>
			</c:if>
			<c:if test="${obj.sex==0}">
				<div class="female">female</div>
			</c:if>
			</div>
			<div class="pff">
			<h1 style=" padding:0 0 0 40px;">Posts:<a href="javascript:void(0)" onclick="goSocialIndex(${obj.id})" title="show">${obj.post}</a></h1>
			<h1 style=" padding:0 0 0 130px;">Followings:<a href="javascript:void(0)" onclick="goSocialFollowing(${obj.id})" title="show">${obj.following}</a></h1>
			<h1 style=" padding:0 0 0 250px;">Followers:<a href="javascript:void(0)" onclick="goSocialIndex(${obj.id})" title="show">${obj.follower}</a></h1>
		</div>
		<div class="left3">
			<h2>Hometown:${obj.home_town}</h2>
			<h2>School year:${obj.year}</h2>
			<h2>Profession:${obj.professionName}</h2>
		</div>
		<div class="sign">
			<h3>Sign:${obj.sign}</h3>
		</div>
	</div>
	<div class="left4"></div>
	</div>
</c:forEach>
</div>
</form>
<script type="text/javascript" src="js/jquery.cssemoticons.js"></script>
<!-- <div class="user">
<div class="headphoto"></div>
<div class="information">
<div class="left2">
<h1>Username</h1><div class="female">female</div><h1 >Posts:12</h1><h1>Followings:7</h1><h1>Followers:11</h1>
</div>
<div class="left3"><h2>Hometown:Foshan</h2><h2>School year:2015</h2><h2>Profession:国际经济与金融学院</h2>
</div>
<div class="sign"><h3>Sign:</h3></div>
</div>
<div class="left4"></div>
</div>

<div class="user">
<div class="headphoto"></div>
<div class="information">
<div class="left2">
<h1>Username</h1><div class="male">male</div><h1 >Posts:12</h1><h1>Followings:7</h1><h1>Followers:11</h1>
</div>
<div class="left3"><h2>Hometown:Foshan</h2><h2>School year:2015</h2><h2>Profession:IT</h2>
</div>
<div class="sign"><h3>Sign:</h3></div>
</div>
<div class="left4"></div>
</div> -->
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.cssemoticons.js"></script>
<script type="text/javascript">
	$('.sign').emoticonize();
</script>
</body>
</html>

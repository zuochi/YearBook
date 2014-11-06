<%@page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Friend </title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="styles/sass-compiled.css" />
<script src="js/modernizr.js" type="text/javascript"></script>
	
</head>
<body>


		

<div  style="float:right;
    margin-bottom:10px;
    width:200px;
    height:30px;
    -webkit-box-shadow: 0px 0px 6px #757575;
	-moz-box-shadow: 0px 0px 6px #757575;
	box-shadow: 0px 0px 9px #757575;
	-webkit-transition: all;
	-moz-transition: all;
	-ms-transition: all;
	-o-transition: all;
	transition: all;
	font-size:25px;
	line-height:30px;
	text-align:center;
	background-color:#3ab6ae;">Friends </div>
		


<div class="frame">

<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>11.13 welcome to my graduation</p>
 </span>
</div>
<div class="name">Kass</div> 
</div>






<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>中文签名就个字你试试个中文英文的话很容易就满了主要是中文中文占位置很多哈哈哈哈哈哈哈</p>
 </span>
</div>
<div class="name">Kass</div> 
</div>
<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>HI,I Am Kass</p>
 </span>
</div>
<div class="name">Kass</div> 
</div>

<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>HI,I Am Kass</p>
 </span>
</div>
<div class="name">Kass</div> 
</div>
<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>HI,I Am Kass</p>
 </span>
</div>
<div class="name">Kass</div> 
</div>
<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>HI,I Am Kass</p>
 </span>
</div>
<div class="name">Kass</div> 
</div>



	
   </div>

</body>
</html>
<%@ page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 顶部菜单栏 -->
<div id="menu">
<div class="menu2"><p>Please <a href="javascript:void(0)" onclick="goUrl(9)">Sign In</a>  or <a href="javascript:void(0)" onclick="goUrl(10)">Resgister</a></p></div>
<div class="center">
<p><a href="javascript:void(0)" onclick="goUrl(3)">Home</a></p>
<p><a href="javascript:void(0)" onclick="goUrl(2)">Profile</a></p>
<p><a href="javascript:void(0)" onclick="goUrl(5)">Post</a></p>
<img style="float:left" src="images/logo.png" alt="" width="120"	height="80"  />
<p><a href="javascript:void(0)" onclick="goUrl(7)">Message</a></p>
<p><a href="javascript:void(0)" >Feedback</a></p>
<p><a href="javascript:void(0)" onclick="goUrl(8)">Exit</a></p>

</div>
</div>
<script type="text/javascript" src="js/goUrl.js"></script>
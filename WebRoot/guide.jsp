<%@ page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 顶部菜单栏 -->
<div id="menu">
<p>Please Sign In or Resgister</p>
<div class="center">
<p>Home</p>
<p>Profile</p>
<p>Post</p>
<img style="float:left" src="images/logo.png" alt="" width="120"	height="80"  />
<p>Message</p>
<p>Feedback</p>
<p>Exit</p>

</div>


</div>
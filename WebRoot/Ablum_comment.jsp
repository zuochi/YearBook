<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>welcome to login!</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="styles/my.css" />
</head>
<body >

	<br />
	<div id="commentbg"></div>
	<div id="comment" style="display:none;">
		<h1>
<input id="close_comment" type="button"  onclick="close_comment()"  style="height:20px;width:20px; color:#234; font-size:15px; float:right;" title="退出" value="X" />
		</h1>
	
		<div id="liuyan" >

	<textarea cols="45" rows="3" type="text" class="liuyan"  maxLength=50 ></textarea>

		<div class="send"><input type="button" style=" color:#234; font-size:13px;"  value="发送" /></div></div>


<!--评论-->
<div class="ds-post-main">
		<div class="ds-avatar">
			<a title="用户名" href="" target="_blank"><img alt="设计达人" src="images/alex.jpg"></a><!-- 用户头像 -->
		</div>
		<div class="ds-comment-body">
			<a title="用户名" href="" target="_blank" class="user-name">用户名</a>
			<div class="message">what'up man.heyyo,我想长高10厘米，增重15斤 </div>
			<div id="tc">
			<p>时间：2015年9月14日22:38</p>
			<div class=tree_1 onMouseover="line(this)" onMouseOut="delline(this)" onClick="showtree('reply')"><a href="javascript:void(0)">	
			<div class="comments_icon"></div>
			</a><span id=span_reply style="color:gray"></span></div>
			<div id=reply_value class=tree_2 style="display:none">
			<div class=tree_add onMouseOver="line(this)" onMouseOut="delline(this)">
				<form action="">
				<textarea cols="35" rows="3" type="text" class="liuyan2"  maxLength=50 name="reply"></textarea>
					<input type="button" style=" color:#234; font-size:11px;" value="发送"  />
				</form> 
			
			</div>
			<div class="receive">收到的评论:
				<div class="message">肥妹：heyhey.我要减肥!</div>
				<div class="message">肥妹：heyhey.我要减肥!</div>
				<div class="message">肥妹：heyhey.我要减肥!</div>
			</div>	
				</div>
			</div>	
		</div>
	</div>
</div>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/my.js"></script>
<script type="text/javascript" src="js/photo.js"></script>

</body>
</html>

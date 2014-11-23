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

<title>Message</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/message.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="styles/jquery.cssemoticons.css" media="screen"/>
</head>
<body>

<div id="M">
<div class="border_radius">
  <h2>Message</h2>
  <h3>Here you can reply messages that you have received </h3>
 <!--以下为收到的评论与对应评论的人的头像-->
 <br/>
 <a href="javascript:void(0)">照片评论</a>|<a href="javascript:void(0)">BBS评论</a>|<a href="javascript:void(0)">好友私信</a><br/>
-------------------------------------


<!--评论-->
<div id="photoReplyDiv">
	<s:iterator var="index" begin="1" end="#request.messageReplyPc.totalPages">
		<div id='replyBody"+json[i].id+"' class='ds-post-main'>
		<div class='ds-avatar'>
			<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_id+")' target='_blank'><img src='"+json[i].url_m+"'></a>
		</div>
		<div class='ds-comment-body'>
		<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_id+")' target='_blank' class='user-name'>json[i].name</a>
		<p id='commentEmo"+json[i].id+"'>json[i].context</p>
		<div class='time'><p>calculateDT(json[i].signup_date)</p></div>
		<a href='javascript:void(0)' onclick='deleteMessage("+json[i].id+")'><div class='shanchu' title='delete this reply'></div></a>
		<a href='javascript:void(0)' onclick='showReplyFrame(\"replyFrame"+json[i].id+"\")'><div class='comments_icon' title='reply "+json[i].name+"' style='float:right'></div></a>
		<div id='replyFrame"+json[i].id+"' style='display:none'>
			<input id='replyFrame"+json[i].id+"Text' type='text' style='width:360px'/>
			<input type='button' value='reply'/>
		</div>
	</s:iterator>
</div>

<!-- 翻页 -->
<ul>
	<li>Page:<s:property value="#request.messageReplyPc.currentPage" />/<s:property value="#request.messageReplyPc.totalPages" />
	<s:if test="#request.messageReplyPc.hasPrevious==true">
		<li><a href="lzf/adminMessage_msgShow?toPage=1">First</a></li>
		<li><a href="lzf/adminMessage_msgShow?toPage=<s:property value="#request.messageReplyPc.previousPage"/>">Previous</a></li>
	</s:if>
	<s:else>
		<!-- <li>First</li>
		<li>Previous</li> -->
	</s:else> 
	<s:if test="#request.pc.hasNext==true">
		<li><a href="lzf/adminMessage_msgShow?toPage=<s:property value="#request.messageReplyPc.nextPage"/>">Next</a></li>
		<li><a href="lzf/adminMessage_msgShow?toPage=<s:property value="#request.messageReplyPc.totalPages"/>">Last</a></li>
	</s:if>
	<s:else>
		<!-- <li>Next</li>
		<li>Last</li> -->
	</s:else>
	<s:if test="#request.messageReplyPc.totalPages>1">
		<li>Jump:
			<select id="changePage" onchange="toPage(this.value)">
				<%--<s:iterator var="index" begin="1" end="#request.messageReplyPc.totalPages">
					<s:if test="#request.messageReplyPc.currentPage==#index">
						<option selected="selected">
							<s:property value="#index" />
						</option>
					</s:if>
					<s:else>
						<option>
							<s:property value="#index" />
						</option>
					</s:else>
				</s:iterator>--%>
			</select> 
		</li>
	</s:if>
</ul>

<!--反馈版块-->
  <h2>FEEDBACK</h2>
  <h3>please contact us if you have any questions</h3> 
  <h3>yearbook@soy.com</h3>
 
</div>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/goUrl.js" ></script>
<script type="text/javascript" src="js/jquery.cssemoticons.js" ></script>
<script type="text/javascript" src="js/calculateDistanceTime.js" ></script>
<script type="text/javascript" src="js/getMessage.js" ></script>
</body>
</html>
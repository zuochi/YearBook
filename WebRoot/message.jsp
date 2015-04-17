<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 查看Message的页面 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Year Book - Message</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="styles/message.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="styles/jquery.cssemoticons.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="styles/friendTips.css" />
<link rel="stylesheet" href="styles/styles_guide.css" type="text/css">
<jsp:include page="guide.jsp" />
</head>
<body>
<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("user/autoLogin_execute");
	}
%>
<input id="typeM" type="hidden" value="<s:property value="#request.type" />"/>
<input id="pageM" type="hidden" value="<s:property value="#request.messageReplyPc.currentPage" />"/>
<input id="countM" type="hidden" value="<s:property value="#request.messageReplyCount" />"/>
<div style="height:100px; width:100%"></div>
<div id="M">
<div class="border_radius">

  <h2>Message</h2>
  <h3>Here you can reply messages that you have received </h3>
  
 <!--以下为收到的评论与对应评论的人的头像-->
 <br/>
 <div align="center" style="margin-left:25px;">

 <s:if test="#request.type=='photo'">
  	<span style="font-size:16px;color:#3ab6ac;font-weight:normal;" >Photo's reply(<s:property value="#request.messageReplyUnReadCount"/>)</span>&nbsp;&nbsp;
 </s:if>
 <s:else>
 	<a style="font-size:15px;color:#303030;" href="javascript:void(0)" onclick="changeType('photo')">Photo's reply(<s:property value="#request.messageReplyUnReadCount"/>)</a>&nbsp;&nbsp;
 </s:else>
 <s:if test="#request.type=='mentionMe'">
 	<span style="font-size:16px;color:#3ab6ac;font-weight:normal;" >Mention me(<s:property value="#request.mentionMeUnReadCount"/>)</span>&nbsp;&nbsp;
 </s:if>
 <s:else>
 	<a style="font-size:15px;color:#303030;" href="javascript:void(0)"  onclick="changeType('mentionMe')">Mention me(<s:property value="#request.mentionMeUnReadCount"/>)</a>&nbsp;&nbsp;
 </s:else>
 <s:if test="#request.type=='pl'">
  <span style="font-size:16px;color:#3ab6ac;font-weight:normal;" >Private letter(<s:property value="#request.pirvateLetterUnReadCount"/>)</span>&nbsp;&nbsp;
 </s:if>
 <s:else>
 	<a style="font-size:15px;color:#303030;" href="javascript:void(0)"  onclick="changeType('pl')">Private letter(<s:property value="#request.pirvateLetterUnReadCount"/>)</a>&nbsp;&nbsp;
 </s:else>
 <br/></div>
 <div style=" float:top;margin:10px 0 10px 0;position:relative;border-bottom:1px dashed #8f8f8f;"></div>

<!-- 翻页 -->
<s:if test="#request.messageReplyCount>0">
<ul style=" padding:0px;margin-left:5px; font-size:16px;">
	<li style="margin-left:0px;">Page:<s:property value="#request.messageReplyPc.currentPage" />/<s:property value="#request.messageReplyPc.totalPages" />
	<s:if test="#request.messageReplyPc.hasPrevious==true">
		<li><a href="javascript:void(0)" onclick="messageTurnPage(1,<s:property value="#request.messageReplyCount"/>,'<s:property value="#request.type"/>')">First</a></li>
		<li><a href="javascript:void(0)" onclick="messageTurnPage(<s:property value="#request.messageReplyPc.previousPage"/>,<s:property value="#request.messageReplyCount"/>,'<s:property value="#request.type"/>')">Previous</a></li>
	</s:if>
	<s:else>
		<li style="font-size:14px;" class='disableTurnPage'>First</li>
		<li style="font-size:14px;" class='disableTurnPage'>Previous</li>
	</s:else> 
	<s:if test="#request.messageReplyPc.hasNext==true">
		<li><a href="javascript:void(0)" onclick="messageTurnPage(<s:property value="#request.messageReplyPc.nextPage"/>,<s:property value="#request.messageReplyCount"/>,'<s:property value="#request.type"/>')">Next</a></li>
		<li><a href="javascript:void(0)" onclick="messageTurnPage(<s:property value="#request.messageReplyPc.totalPages"/>,<s:property value="#request.messageReplyCount"/>,'<s:property value="#request.type"/>')">Last</a></li>
	</s:if>
	<s:else>
		<li style="font-size:14px;" class='disableTurnPage'>Next</li>
		<li style="font-size:14px;" class='disableTurnPage'>Last</li>
	</s:else>
	<s:if test="#request.messageReplyPc.totalPages>1">
		<li style="width:100px;" >Jump:
			<select style="float:right;" id="changePage" onchange="messageTurnPage(this.value,<s:property value="#request.messageReplyCount"/>,'<s:property value="#request.type"/>')">
				<s:iterator var="index" begin="1" end="#request.messageReplyPc.totalPages">
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
				</s:iterator>
			</select> 
		</li>
	</s:if>
</ul>
</s:if>
<s:else>
<h3 align="center"><span class="css-emoticon animated-emoticon" style="font-size:1.8em;">:'(</span></h3><p><h3>You haven't received any replys yet.</h3>
</s:else>

<!--评论-->
<div id="photoReplyDiv">
	<%-- <s:iterator var="index" id="o" value="#request.messageReply">
		<div id="replyBody<s:property value="#o.id"/>" class="ds-post-main">
			<div class='ds-avatar'>
				<a title='<s:property value="#o.name"/>' href='javascript:goSocialIndex(<s:property value="#o.user_id"/>)' target='_blank'><img src='<s:property value="#o.url_m"/>'></a>
			</div>
			<div class='ds-comment-body'>
				<a title='<s:property value="#o.name"/>' href='javascript:goSocialIndex(<s:property value="#o.user_id"/>)' target='_blank' class='user-name'><s:property value="#o.name"/></a>
				<p id='commentEmo<s:property value="#o.id"/>'><s:property value="#o.context"/></p>
				<div class='time'><p>calculateDT(json[i].signup_date)</p></div>
				<a href='javascript:void(0)' onclick='deleteMessage(<s:property value="#o.id"/>)'><div class='shanchu' title='delete this reply'></div></a>
				<a href='javascript:void(0)' onclick='showReplyFrame(\"replyFrame<s:property value="#o.id"/>\")'><div class='comments_icon' title='reply <s:property value="#o.name"/>' style='float:right'></div></a>
				<div id='replyFrame<s:property value="#o.id"/>' style='display:none'>
					<input id='replyFrame<s:property value="#o.id"/>Text' type='text' style='width:360px'/>
					<input type='button' value='reply'/>
				</div>
			</div>
		</div>
	</s:iterator> --%>
</div>

<div id="replySuccessTip"></div>
</div>
</div>

<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/user/at.js" ></script>
<script type="text/javascript" src="js/jquery.cssemoticons.js" ></script>
<script type="text/javascript" src="js/user/calculateDistanceTime.js" ></script>
<script type="text/javascript" src="js/user/getMessage.js" ></script>
</body>
</html>
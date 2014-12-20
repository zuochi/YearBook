<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 论坛页面 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/styles.css" rel="stylesheet" type="text/css" />
<link href="styles/bbs.css" rel="stylesheet" type="text/css" />
<script>
function showtree(str)//展开|关闭子分支
{
var eval1="span_"+str+".innerHTML=span_"+str+".innerHTML==''?'':'+'";
var eval2=str+"_value.style.display="+str+"_value.style.display=='none'?'':'none'";
eval(eval1);
eval(eval2);
}
</script>
</head>


<body>
<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("/YearBook/user/autoLogin_execute");
	}
%>
	<!-- 绿色-->
<div id="liuyanban">

<img src="images/bbs1.jpg" /></div>

<!-- 文本框和输入内容-->
<div id="liuyan" >
<textarea cols="78" rows="3" type="text" class="ly"  maxLength=170 ></textarea>

		<div class="send"><input type="button" value="发送" style="font-size:20px"  /></div></div>
		<!--评论次数-->
		<div class="coment_number">11条留言/</div>
<!--评论-->
<div class="ds-post-main">
		<div class="ds-avatar">
			<a title="用户名" href="" target="_blank"><img alt="设计达人" src="images/alex.jpg"></a><!-- 用户头像 -->
		</div>
		<div class="ds-comment-body">
			<a title="用户名" href="" target="_blank" class="user-name">用户名</a>
			<p>"是不是上了5天班了？"、"以为是周末了？"、"今天才周三，哈哈！"。</p>
			<div id="tc">
			<div class="time"><p>时间</p></div>
			<div class="likes_icon"></div><div class="like_number">(11111)</div>
			<div class=tree_1 onMouseover="line(this)" onMouseOut="delline(this)" onClick="showtree('reply')"><a href="javascript:void(0)">	
			<div class="comments_icon"></div><div class="com_number">(111)</div>
			</a><span id=span_reply style="color:gray"></span></div>
			<div id=reply_value class=tree_2 style="display:none">
			<div class=tree_add onMouseOver="line(this)" onMouseOut="delline(this)">
				<form action="">
				<textarea cols="50" rows="2" type="text" class="liuyan"  maxLength=60 name="reply"></textarea>
					<input type="button" value="发送"  />
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

</body></html>


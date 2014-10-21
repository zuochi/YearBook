<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Message</title>
<meta http-equiv="content-type" content="text/html;charset=gb2312">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/message.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
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
<div id="M">
<div class="border_radius">
  <h2>Message</h2>
  <h3>Here you can reply messages that you have received </h3>
 <!--用户自己头像-->
 <img src="images/alex.jpg" alt="" width="100" height="100"  class="ipic">
 <!--以下为收到的评论与对应评论的人的头像-->
 <br/>
 <a href="javascript:void(0)">照片评论</a>|<a href="javascript:void(0)">BBS评论</a>|<a href="javascript:void(0)">好友私信</a><br/>
-------------------------------------


<!--评论-->
<div class="ds-post-main">
		<div class="ds-avatar">
			<a title="用户名" href="" target="_blank"><img alt="设计达人" src="images/alex.jpg"></a><!-- 用户头像 -->
		</div>
		<div class="ds-comment-body">
			<a title="用户名" href="" target="_blank" class="user-name">用户名</a>
			<p>"是不是上了5天班了？"、"以为是周末了？"、"今天才周三，哈哈！"。</p>
		
			<div class="time"><p>时间</p></div>
			<div class=tree_1 onMouseover="line(this)" onMouseOut="delline(this)" onClick="showtree('reply')"><a href="javascript:void(0)">	<div class="comments_icon"></div>
			</a><span id=span_reply style="color:gray"></span></div>
			<div id=reply_value class=tree_2 style="display:none">
			<div class=tree_add onMouseOver="line(this)" onMouseOut="delline(this)">
				<form action="">
					<input type="text" name="reply"/>
					<input type="button" value="回复"/>
				</form> 
			</div>
			</div>
		</div>
	</div>




<!--点击more可以查看更多评论-->
<input type="button"  class="more"  value="More"  style="font-size:18px" />

<!--反馈版块-->
  <h2>FEEDBACK</h2>
  <h3>please contact us if you have any questions</h3> 
  <!--2个管理员的头像-->
  <div id="yb"><img src="images/alex.jpg" alt="" width="100" height="100"  ></div>
  <div id="by"><img src="images/alex.jgp" alt="" width="100" height="100"  ></div>
  <!--发送给管理员对应的文本框以及发送按钮-->
  <div id="feedback"><textarea cols="54" rows="3" type="text" class="fb"  maxLength=150 ></textarea></div>
  <div id="feedback"><textarea cols="54" rows="3" type="text" class="fb"  maxLength=150 ></textarea></div>
  <input type="button" value="Send" style="font-size:16px"  class="sent"/>
  <input type="button" value="Send" style="font-size:16px"  class="sent1"/>
 
</div>


</body>
</html>
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
    <title><s:property value="#session.user.name"/> - Post</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/demo2.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/component.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/jquery.cssemoticons.css" media="screen"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/friendTips.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/showLoading.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="js/jquery.cssemoticons.js" ></script>
		<script type="text/javascript">	
$(document).ready(function()
{
			$('#text').emoticonize({
				//delay: 800,
				//animate: false,
				//exclude: 'pre, code, .no-emoticons'
			});
			$('#toggle-headline').toggle(
				function(){
					$('#large').unemoticonize({
						//delay: 800,
						//animate: false
					})
				}, 
				function(){
					$('#large').emoticonize({
						//delay: 800,
						//animate: false
					})
				}
			);
		})
	</script>
	
	
</head>
<body>
<input type="hidden" id="userId" value="<s:property value="#session.user.id"/>"/>
<input type="hidden" id="nickName" value="<s:property value="#session.user.name"/>"/>
<input type="hidden" id="urlM" value="<s:property value="#session.user.headPhoto.urlM"/>"/>
<input type="hidden" id="photoCountHidden" value="<s:property value="#request.photosCount"/>"/>
<input type="hidden" id="basePath" value="<%=basePath%>"/>
<div class="top"> 
<input type="text"  style="font-size:14px; margin-left:30px;">
<input type="button" value="S" style="font-size:18px">earch
</div>
		<div class="container">
			<header class="clearfix">
				<div class="prooo">
					<div class="rad">
						<s:if test="#session.headPhoto.urlL!=null">
							<a href="javascript:void(0)" onclick="goUrl(1)" target="main"> 
								<img src="<s:property value="#session.headPhoto.urlL"/>" alt="" width="120" height="120" title="修改头像"> 
							</a>
						</s:if>
						<s:else>
							<a href="javascript:void(0)" onclick="goUrl(1)" target="main"> 
								<img src="images/bg.png" alt="" width="120" height="120" title="修改头像"> 
							</a>
						</s:else>
						<div class="na"><s:property value="#session.user.name" /></div></div>
						<s:if test="#session.user.sex==1">
                            <div class="male">&nbsp;&nbsp;&nbsp;&nbsp;male</div>
                        </s:if>
                        <s:else>
                            <div class="female">&nbsp;&nbsp;&nbsp;&nbsp;female</div>
                        </s:else>
							<div class="sign">
								<a href="javascript:void(0)" title='edit my sign.' onclick="showSignEdit()"><div id="signButton" class="pen"></div></a>
							sign：<input type="text" id="signTextArea" style="display:none" maxlength="42"/><input id="updateSignButton" style="display:none" type="button" value="Edit" onclick="updateSign()"/>
								  <sign id="signContext"><s:property value="#session.user.sign"/></sign>
							</div>
							<a href="javascript:void(0)" title="click to show my Post." onclick="myPostShow()" target="main"><div id="myPostSelectd" class="mypostSelect">Mypost</div></a>
							<a href="javascript:void(0)" title="click to show latest social's infomation." onclick="socialShow()" target="main"><div id="socialSelectd" class="social">Social</div></a>
							<a href="javascript:void(0)" title="click to upload." onclick="uploadPhoto()" target="main"><div class="upload">Upload</div></a>
							

					<div class="attfan">
						<s:if test="#request.photosCount!=0">
							<a href="javascript:void(0)" title="click to show my Post." onclick="myPostShow()" target="main"><div class="posts">Posts<div id="picCountDiv" class="a1"><s:property value="#request.photosCount"/></div></div></a><div class="l"></div>
							<%-- <div class="posts">Posts<a href="/YearBook/user/getMyPost_execute?userId=<s:property value="#session.user.id"/>" target="main"><div class="a1"><s:property value="#request.photosCount"/></div></a></div> <div class="l"></div> --%>
						</s:if>
						<s:else>
							<div title="no Post here,plz click upload." class="posts">Posts<div class="a1">0</div></div> <div class="l"></div>
						</s:else>
						<a href="javascript:goUrl(4)" title="click to show my Following."><div class="atten">Following<div class="f1"><s:property value="#request.followingCount"/></div></div><div class="l"></div></a>
						<a href="javascript:goUrl(11)" title="click to show my Followers."><div class="fans">Followers<div class="a1"><s:property value="#request.followersCount"/></div></div></a>
					</div>
				</div>
			</header>
			
<br><br><br><br><br><br><br><br>
				<!-- 注意！从这里开始，有多少个li下面的弹框就要对应多少个li,图片的位置也是对应的 -->
						
				
					<!-- 用户图片 -->
			<div id="grid-gallery" class="grid-gallery">
				<section class="grid-wrap">
					<ul id="photosUL" class="grid">
				<li class="grid-sizer"></li>
						<!-- 打印用户图片缩略图 -->
						<s:if test="#request.photosCount==0">
							<br><br>
							<center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
							<h3 id="text">: (  : )</h3><p>You are not upload any pictures yet,please click <a href="javascript:void(0)" onclick="uploadPhoto()" target="main">Upload.</a></p></center>
						</s:if>
					</ul>
				</section> <!-- // grid-wrap -->

				<section class="slideshow">
					
				<!--点击用户弹出资料 -->					
									
				<!--点击图片弹出大图加评论 -->
				<ul id="photosUL2"></ul>
					<nav>
						<span class="icon nav-prev" style="color:#234;width:80px"></span>
						<span class="icon nav-next" style="color:#234;width:80px"></span>
						<span class="icon nav-close" style="color:#fff"></span>
					</nav>
				</section><!-- // slideshow -->
			</div><!-- // grid-gallery -->
		</div>
		
		<script type="text/javascript" src="<%=basePath%>js/goUrl.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/calculateDistanceTime.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.showLoading.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/at.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/getReply.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/User_index.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.cssemoticons.js" ></script>
		<script type="text/javascript" src="<%=basePath%>js/modernizr.custom.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/imagesloaded.pkgd.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/masonry.pkgd.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/classie.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/cbpGridGallery.js"></script>
		<script>
			new CBPGridGallery( document.getElementById( 'grid-gallery' ) );
		</script>
		
		<div id="hasPhotos"></div>
		<div id="showPhotosLoading"></div>
		
		<div id="socialDiv" style="display: none">
			<!-- 好友图片刷新 -->
				<div class="friendframepic">
				<div class="friendframe">
				<div class="frienduser"><img src="images/alex.jpg" width="80" height="80">
				<div class="friendname">用户名</div>	</div>
				<div class="l"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="mor">More...</div>
				<div class="time2">2014-10-20</div>
				</div>
				
				<div class="friendframe">
				<div class="frienduser"><img src="images/alex.jpg" width="80" height="80">
				<div class="friendname">用户名</div>	</div>
				<div class="l"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="mor">More...</div>
				</div>
				
				<div class="friendframe">
				<div class="frienduser"><img src="images/alex.jpg" width="80" height="80">
				<div class="friendname">用户名</div>	</div>
				<div class="l"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="mor">More...</div>
				</div>
				
				<div class="friendframe">
				<div class="frienduser"><img src="images/alex.jpg" width="80" height="80">
				<div class="friendname">用户名</div>	</div>
				<div class="l"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="friendpic"><img src="images/alex.jpg" width="80" height="80"></div>
				<div class="mor">More...</div>
				</div>		
				
				</div>
		</div>
	</body>
</html>
   
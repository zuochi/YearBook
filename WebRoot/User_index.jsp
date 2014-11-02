<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>PicKass</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/demo2.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/component.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/jquery-ui.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/jquery.cssemoticons.css" media="screen"/>

		<script type="text/javascript">
		//提示测试
		var availableTags = new Array();
		$(function(){
			
			availableTags = ["ActionScript","AppleScript","Asp","BASIC","C","C++","Clojure","COBOL高","ColdFusion"];
			//document.getElementById("tags").focus();
			$( "#tags").autocomplete({
				source: availableTags
			});
		});
		
		function friendNameTipsInit(photoId){
			 $( "#reply"+photoId).autocomplete({
				source: availableTags
			});
		} 
		
		//评论
		function comment(userBid,photoBid){
			var reply = $("#reply"+photoBid).val();
			if(reply.length<1 || reply.length>80){
				alert("reply at least 1 in length,and max at 80.");
				return;
			}
			$.ajax({
				url:'/YearBook/user/doReply_execute',  
				type:'post', 
		        data:"reply.userByUserBid.id="+userBid+"&reply.photo.id="+photoBid+"&reply.context="+reply,
		        async:false,
				//dataType:'json', 
				success:function () {
					$("#reply"+photoBid).val("");
					alert("success");
				}
			});
		};
		
		//当用户输入@的时候 寻找用户好友列表里面的相关人员
		var searchFriendNameStatus = 0;
		var atFriendName;
		function getAtName(text,photoId){
			//删除前后空格
	        //text.replace(/(^\s*)|(\s*$)/g,"");
			if(text=="@"){
				searchFriendNameStatus = 1;
				atFriendName = "";
			}
			if(searchFriendNameStatus==1){
				if(text==" "){
					searchFriendNameStatus=0;
					return;
				}else if(text!="@"){
					atFriendName += text;
					$.ajax({
						url:'/YearBook/user/getSearchInfomation_execute',  
						type:'post', 
				        data:"type=searchFriendName&friendList.userByFriendId.name="+atFriendName,
				        async:false,
						dataType:'json', 
						success:function (json) {
							/* if(json.error==undefined){
								var availableTags = new Array();
								for(var i=0; i<json.length; i++){
									availableTags[i] = json[i];
								}
								$( "#reply"+photoId ).autocomplete({
									json: availableTags
								});
							} */
						}
					});
				}
			}
		};
		

	
		$(document).ready(function(){
			$('.emoticonText').emoticonize({
				//delay: 800,
				//animate: false,
				//exclude: 'pre, code, .no-emoticons'
			});
			$('#toggle-headline').toggle(function(){
				$('#large').unemoticonize({
					//delay: 800,
					//animate: false
			})},function(){
				$('#large').emoticonize({
					//delay: 800,
					//animate: false
				})
			});
		});

		
		//去某用户的主页
		function goSocialIndex(userId){
			window.location.href='YearBook/user/getSocial?userId='+userId;
		}
</script>
</head>
<body>
<textarea rows="10" cols="40" id="tags"></textarea>
<!-- <h1 class="emoticonText">It's a pirate ?-) ARGHHH!!!! :)</h1> -->
<!--<a href='YearBook/user/getSocial?userId=2'>ssss</a>-->
	<input type="hidden" id="userId" value="<s:property value="#session.user.id"/>"/>
	<input type="hidden" id="photoCountHidden" value="<s:property value="#request.photosCount"/>"/>
	<input type="hidden" id="basePath" value="<%=basePath%>"/>
	<div class="top"> 
<input type="text"  style="font-size:14px; margin-left:30px;">
<input type="button" value="S" style="font-size:18px">earch
</div>
		<div class="container">
			<header class="clearfix">
				<div class="prooo">
					<div class="rad"><a href="<%=basePath%>User_headPhotoUpload.jsp" target="main"> <img src="<%=basePath%><s:property value="#session.headPhoto.urlL"/>" width="120" height="120" title="修改头像"/></a><div class="na"><s:property value="#session.user.name" /></div></div>
						<s:if test="#session.user.sex==1">
                            <div class="male">&nbsp;&nbsp;&nbsp;&nbsp;male</div>
                        </s:if>
                        <s:else>
                            <div class="female">&nbsp;&nbsp;&nbsp;&nbsp;female</div>
                        </s:else>
							<div class="sign"><div class="pen"></div>sign：<s:property value="#session.user.sign"/>.</div>
							<a href="javascript:void(0)" onclick="myPostShow()" target="main"><div id="myPostSelectd" class="mypostSelect">Mypost</div></a>
							<a href="javascript:void(0)" onclick="socialShow()" target="main"><div id="socialSelectd" class="social">Social</div></a>
							<a href="javascript:void(0)" onclick="uploadPhoto()" target="main"><div class="upload">Upload</div></a>
							

					<div class="attfan">
						<s:if test="#request.photosCount!=0">
							<div class="posts">Posts<a href="javascript:void(0)" onclick="myPostShow()" target="main"><div id="picCountDiv" class="a1"><s:property value="#request.photosCount"/></div></a></div> <div class="l"></div>
							<%-- <div class="posts">Posts<a href="/YearBook/user/getMyPost_execute?userId=<s:property value="#session.user.id"/>" target="main"><div class="a1"><s:property value="#request.photosCount"/></div></a></div> <div class="l"></div> --%>
						</s:if>
						<s:else>
							<div class="posts">Posts<div class="a1">0</div></div> <div class="l"></div>
						</s:else>
						<div class="atten">Following<div class="f1"><s:property value="#request.followingCount"/></div></div><div class="l"></div>
						<div class="fans">Followers<div class="a1"><s:property value="#request.followersCount"/></div></div> 
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
							<br><br><br><br>
							<center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<p class="text">
							:( You are not upload any pictures yet,please click <a href="javascript:void(0)" onclick="uploadPhoto()" target="main">Upload.</a></p></center>
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
		
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/jquery-ui.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/User_index.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/jquery.cssemoticons.min.js" ></script>
		<script type="text/javascript" src="<%=basePath %>js/modernizr.custom.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/imagesloaded.pkgd.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/masonry.pkgd.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/classie.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/cbpGridGallery.js"></script>
		
		<script>
			new CBPGridGallery( document.getElementById( 'grid-gallery' ) );
		</script>
		
		<div id="hasPhotos"></div>
		
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
   
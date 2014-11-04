<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <title><s:property value="#request.socialUser.name"/> - Year Book</title>
    <meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/demo.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/component_social.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/jquery-ui.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/friendTips.css" />
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.11.1.js"></script>
		<script type="text/javascript">
		
			function follow(socialUserId){
				$.ajax({
			         url:'/YearBook/user/follow_execute',  
			         type:'post', 
			         data:"userId="+socialUserId+"&type=follow",
			         async:false,
			         success:function (msg) {
			        	 if(msg=="success"){
			        		 $("#followAttrDIV").html("<a href='javascript:void(0)' onclick='cancleFollow(<s:property value='#request.socialUser.id'/>)'><div id='followAttr' class='yiguanzhu'></div></a>");
			        	 }else{
			        		 alert("操作失败");
			        	 }
			         }
				 });
			};
			
			function cancleFollow(socialUserId){
				$.ajax({
			         url:'/YearBook/user/follow_execute',  
			         type:'post', 
			         data:"userId="+socialUserId+"&type=cancelFollow",
			         async:false,
			         success:function (msg) {
			        	 if(msg=="success"){
			        		 $("#followAttrDIV").html("<a href='javascript:void(0)' onclick='follow(<s:property value='#request.socialUser.id'/>)'><div id='followAttr' class='guanzhu'></div></a>");
			        	 }else{
			        		alert("操作失败");
			        	 }
			         }
				 });
			};
		</script>
	</head>
	<body>
	<input type="hidden" id="userId" value="<s:property value="#request.socialUser.id"/>"/>
	<input type="hidden" id="basePath" value="<%=basePath%>"/>
	<div class="top"> 
<input type="text"  style="font-size:14px;margin-left:30px;">
<input type="button" value="S" style="font-size:18px">earch
</div>
		<div class="container">
	<header class="clearfix">
		<div id="followAttrDIV">
		<s:if test="#session.user.id!=#request.socialUser.id">
			<s:if test="#request.isfollow==true">
				<a href="javascript:void(0)" onclick="cancleFollow(<s:property value="#request.socialUser.id"/>)"><div id="followAttr" class="yiguanzhu"></div></a>
			</s:if>
			<s:else>
				<a href="javascript:void(0)" onclick="follow(<s:property value="#request.socialUser.id"/>)"><div id="followAttr" class="guanzhu"></div></a>
			</s:else>
		</s:if>
		</div></header></div>
					<div class="prooo">
							<div class="rad">
								<s:if test="#request.socialHeadPhoto.urlL!=null">						
									<img src="<%=basePath%><s:property value="#request.socialHeadPhoto.urlL"/>"> 
								</s:if>						
								<s:else>
									<img src="<%=basePath%>images/bg.png">
								</s:else>
							</div>
							<br></br>
							<h7>Hi,I'm <s:property value="#request.socialUser.name"/></h7><br>
							<s:if test="#request.socialUser.sign!=null && #request.socialUser.sign!=''">
								<h7>Sign:<s:property value="#request.socialUser.sign"/></h7><br>
							</s:if>
							<h7>Profile:</h7><br>
							<s:if test="#request.socialUser.sex==1">
								<div class="male">Sex:male</div>
							</s:if>
							<s:else>
								<div class="female">Sex:female</div>
							</s:else>
							<s:if test="#request.socialUser.weChat!=null && #request.socialUser.weChat!=''">
								<h7>Wechat:<s:property value="#request.socialUser.weChat"/></h7><br>
							</s:if>
							<s:if test="#request.socialUser.qq!=null && #request.socialUser.qq!=''">
								<h7>QQ:<s:property value="#request.socialUser.qq"/></h7><br>
							</s:if>
							<s:if test="#request.socialUser.email!=null">
								<h7>E-mail:<s:property value="#request.socialUser.email"/></h7><br>
							</s:if>
							 <s:if test="#request.socialProfession!=null">
								Profession:<s:property value="#request.socialProfession.name"/><br>
						  </s:if>
                           <s:if test="#request.socialSchoolYear!=null">
								Academic Year:<s:property value="#request.socialSchoolYear.year"/><br>
						  </s:if>
							<div class="attfan">
							<div class="fans">followers<div class="a1"><s:property value="#request.socialFollowingCount"/></div></div> <div class="l"></div>
							<div class="atten">following<div class="f1"><s:property value="#request.socialFollowersCount"/></div></div>
							</div>
								</div>
	
	
			<div id="grid-gallery" class="grid-gallery">
				<section class="grid-wrap">
					<ul id="photosUL" class="grid">
				<li class="grid-sizer"></li>
				<!-- 注意！从这里开始，有多少个li下面的弹框就要对应多少个li,图片的位置也是对应的 -->
						
					<s:if test="#request.socialPhotosCount==0">
						<center style="color:#8f8f8f;font-size:22px;">oops,this crappy hasn't upload any photos yet,<a href="javascript:history.go(-1);">click to Go Back.</a></center>
					</s:if>
					</ul>
				</section><!-- // grid-wrap -->

				<section class="slideshow">
					<ul id="photosUL2">
					<!--点击用户弹出资料 -->	
						
									
				<!--点击图片弹出大图加评论 -->
					</ul>
						<nav>
							<span class="icon nav-prev" style="color:#234"></span>
							<span class="icon nav-next" style="color:#234"></span>
							<span class="icon nav-close" style="color:#fff"></span>
						</nav>
				</section><!-- // slideshow -->
			</div><!-- // grid-gallery -->
		
		<script type="text/javascript" src="<%=basePath %>js/at.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/jquery-ui.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/Social_index.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/modernizr.custom.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/imagesloaded.pkgd.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/masonry.pkgd.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/classie.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/cbpGridGallery.js"></script>
		<script>
			new CBPGridGallery( document.getElementById( 'grid-gallery' ) );
		</script>
		<div id="hasPhotos"></div>
	</body>
</html>

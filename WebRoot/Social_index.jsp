<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 查看关注、粉丝的页面主页-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<base href="<%=basePath%>">
<title><s:property value="#request.socialUser.name"/> - Year Book</title>
<link rel="stylesheet" type="text/css" href="styles/demo.css" />
<link rel="stylesheet" type="text/css" href="styles/component_social.css" />
<link rel="stylesheet" href="styles/sass-compiled.css" />
<link rel="stylesheet" type="text/css" href="styles/jquery.cssemoticons.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="styles/friendTips.css" />
<link rel="stylesheet" type="text/css" href="styles/showLoading.css" />
<link rel="stylesheet" href="styles/styles_guide.css" type="text/css">
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<jsp:include page="guide.jsp" />
</head>
<body>
<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("/YearBook/user/autoLogin_execute");
	}
%>
<input type="hidden" id="userId" value="<s:property value="#request.socialUser.id"/>"/>
<input type="hidden" id="nickName" value="<s:property value="#request.socialUser.name"/>"/>
<input type="hidden" id="currentUserId" value="<s:property value="#session.user.id"/>"/>
<input type="hidden" id="isMine" value="0"/>
<input id="hasBeenModify" type="hidden" value="0"/>
<input type="hidden" id="basePath" value="<%=basePath%>"/>
<div class="top"> 
<input type="text"  style="font-size:14px;margin-left:30px;">
<input type="button" value="S" style="font-size:18px">earch
</div>
		<div class="container">
	<header class="clearfix">
		<div id="followAttrDIV">
		<s:if test="#session.user.id!=#request.socialUser.id">
			<s:if test="#request.relationShip==2">
				<a href="javascript:void(0)" onclick="cancleFollowFriend(<s:property value="#request.socialUser.id"/>)"><div id="followAttr" class="friend"></div></a>
			</s:if>
			<s:elseif test="#request.relationShip==3">
				<a href="javascript:void(0)" onclick="followFriend(<s:property value="#request.socialUser.id"/>)"><div id="followAttr" class="guanzhuFriend"></div></a>
			</s:elseif>
			<s:elseif test="#request.relationShip==1">
				<a href="javascript:void(0)" onclick="cancleFollow(<s:property value="#request.socialUser.id"/>)"><div id="followAttr" class="yiguanzhu"></div></a>
			</s:elseif>
			<s:else>
				<a href="javascript:void(0)" onclick="follow(<s:property value="#request.socialUser.id"/>)"><div id="followAttr" class="guanzhu"></div></a>
			</s:else>
		</s:if>
		</div></header></div>
					<div class="prooo">
							<div class="rad">
								<s:if test="#request.socialHeadPhoto.urlL!=null">						
									<img src="<s:property value="#request.socialHeadPhoto.urlL"/>"> 
								</s:if>						
								<s:else>
									<img src="images/bg.png">
								</s:else>
							</div>
							<br></br>
							<h7>Hi,I'm <s:property value="#request.socialUser.name"/></h7><br>
							<s:if test="#request.socialUser.sign!=null && #request.socialUser.sign!=''">
								<h7 id="signContext">Sign: <s:property value="#request.socialUser.sign"/></h7><br>
							</s:if>	
							<s:if test="#request.socialUser.email!=null">
								<h7>E-mail:<s:property value="#request.socialUser.email"/></h7><br>
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
						
							 <s:if test="#request.socialProfession!=null">
								<h7>Profession:<s:property value="#request.socialProfession.name"/></h7><br>
						  </s:if>
                           <s:if test="#request.socialSchoolYear!=null">
								<h7>Academic Year:<s:property value="#request.socialSchoolYear.year"/></h7><br>
						  </s:if>
							<div class="attfan">
							<a href="javascript:void(0)" onclick="showPost()"><div class="po">Post : <s:property value="#request.socialPhotosCount"/></div></a>
							<a href="javascript:void(0)" onclick="showFollowing()"><div class="fans">Following : <s:property value="#request.socialFollowingCount"/></div></a>
							<a href="javascript:void(0)" onclick="showFollowers()"><div class="atten">Followers : <span id="followersNumber" ><s:property value="#request.socialFollowersCount"/></span></div></a>
							</div>
								</div>
	
		<!-- // grid-gallery -->
		<div id="grid-gallery" class="grid-gallery">
			<section class="grid-wrap">
				<ul id="photosUL" class="grid">
			<li class="grid-sizer"></li>
				<%-- <s:if test="#request.socialPhotosCount==0">
					<center style="color:#8f8f8f;font-size:22px;">oops,this crappy hasn't upload any photos yet,<a href="javascript:history.go(-1);">click to Go Back.</a></center>
				</s:if> --%>
				</ul>
			</section><!-- // grid-wrap -->
			
			<section class="slideshow">
				<ul id="photosUL2"></ul>
					<nav>
						<span class="icon nav-prev" style="color:#234"></span>
						<span class="icon nav-next" style="color:#234"></span>
						<span class="icon nav-close" style="color:#fff"></span>
					</nav>
			</section><!-- // slideshow -->
		</div>
			
		<div id="followingContext"></div>
		<div id="followersContext"></div>
		
		<div id="hasPhotos"></div>
		<div id="loadingComment" class="loadComm"></div>
		<div id="loadingPhoto" class="loadPic"></div>
		
		<div id="hasFollowing"></div>
		<div id="hasFollowers"></div>
		<script type="text/javascript" src="js/goUrl.js"></script>
		<script type="text/javascript" src="js/calculateDistanceTime.js"></script>
		<script type="text/javascript" src="js/jquery.showLoading.js"></script>
		<script type="text/javascript" src="js/at.js"></script>
		<script type="text/javascript" src="js/followOperationSmall.js"></script>
		<script type="text/javascript" src="js/jquery.cssemoticons.js" ></script>
		<script type="text/javascript" src="js/modernizr.custom.js"></script>
		<script type="text/javascript" src="js/imagesloaded.pkgd.min.js"></script>
		<script type="text/javascript" src="js/masonry.pkgd.min.js"></script>
		<script type="text/javascript" src="js/classie.js"></script>
		<script type="text/javascript" src="js/cbpGridGallery.js"></script>
		<script type="text/javascript" src="js/getReply.js"></script>
		<script type="text/javascript" src="js/Social_index.js"></script>
		<script>
			new CBPGridGallery( document.getElementById( 'grid-gallery' ) );
			
			$(document).ready(function(){
				$('#signContext').emoticonize();
			});
		</script>
	</body>
</html>

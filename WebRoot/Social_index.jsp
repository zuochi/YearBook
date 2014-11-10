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
<title><s:property value="#request.socialUser.name"/> - Year Book</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/demo.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/component_social.css" />
<!--<link rel="stylesheet" href="styles/sass-compiled.css" /> 这个是新加的布局与其他布局冲突了 -->
<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>styles/jquery-ui.css" /> --%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/friendTips.css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.11.1.js"></script>
<script type="text/javascript">

//following
var followingIsNew=true;
var hasFollowing=1;
var toPageFollowing=0;
function getFollowingByPerPage(){
	//自动返向下一页
	toPageFollowing+=1;
	if(hasFollowing==1){
		$.ajax({
			url:'/YearBook/user/getSocial_getFollowingByPerPage',  
			type:'post', 
			data:"user.id="+$("#userId").val()+"&toPage="+toPageFollowing,
			async:false,
			dataType:"json",
			success:function (json) {
				if(json.error==undefined){
					if(json.length==undefined){
						hasFollowing=0;
						$("#hasFollowing").html("<br><br><br><br><div><center class='noMoreInfo'>oops:(,there are no more following,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top </a>OR <a href='javascript:history.go(-1);'>click to Go Back.</a></center><br><br><br><br>");
					}
					for(var i=0; i<json.length; i++){
						$("#followingContext").append(
							"<div class='one'>"+
								"<div class='pic'>"+
									"<img src='"+(json[i].lurl!=null&&json[i].lurl!=''?json[i].lurl:"images/bg.png")+"' class='pic-image' alt='Pic'/>"+
									"<span class='pic-caption left-to-right'>"+      
									"<p>"+(json[i].sign!=null&&json[i].sign!=''?json[i].sign:"no sign yet.")+"</p>"+
									"</span>"+
								"</div>"+
								"<a href='javascript:void(0)' onclick='goSocialIndex("+json[i].userId+")' target='main'>"+
								"<div class='name'>"+(json[i].isFriend==true?"<isFriend style='color:red' title='we followed each other.'>♥ </isFriend><name title='click to show detail.'>":"<name title='click to show detail.'>")+json[i].fname+"</name></div></a>"+
							"</div>"
						);
					}
				}
			}
		});
	}
};

//followers
var followersIsNew=true;
var hasFollowers=1;
var toPageFollowers=0;
function getFollowersByPerPage(){
	//自动返向下一页
	toPageFollowers+=1;
	if(hasFollowers==1){
		$.ajax({
			url:'/YearBook/user/getSocial_getFollowersByPerPage',  
			type:'post', 
			data:"user.id="+$("#userId").val()+"&toPage="+toPageFollowers,
			async:false,
			dataType:"json",
			success:function (json) {
				if(json.error==undefined){
					if(json.length==undefined){
						hasFollowers=0;
						$("#hasFollowers").html("<br><br><br><br><center class='noMoreInfo'>oops:(,there are no more followers,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top </a>OR <a href='javascript:history.go(-1);'>click to Go Back.</a></center><br><br><br><br>");
					}
					for(var i=0; i<json.length; i++){
						$("#followersContext").append(
							"<div class='one'>"+
								"<div class='pic'>"+
									"<img src='"+(json[i].lurl!=null&&json[i].lurl!=''?json[i].lurl:"images/bg.png")+"' class='pic-image' alt='Pic'/>"+
									"<span class='pic-caption left-to-right'>"+      
									"<p>"+(json[i].sign!=null&&json[i].sign!=''?json[i].sign:"no sign yet.")+"</p>"+
									"</span>"+
								"</div>"+
								"<a href='javascript:void(0)' onclick='goSocialIndex("+json[i].userId+")' target='main'>"+
								"<div class='name'>"+(json[i].isFriend==true?"<isFriend style='color:red' title='we followed each other.'>♥ </isFriend><name title='click to show detail.'>":"<name title='click to show detail.'>")+json[i].fname+"</name></div></a>"+
							"</div>"
						);
					}
				}
			}
		});
	}
};

function showPost(){
	$("#grid-gallery").show();
	$("#followingContext").hide();
	$("#followersContext").hide();
	$("#hasPhotos").show();
	$("#hasFollowing").hide();
	$("#hasFollowers").hide();
	myPostDisplay=1;
	display=3;
};
	
function showFollowing(){
	if(followingIsNew==true){
		followingIsNew=false;
		getFollowingByPerPage();
	}
	$("#grid-gallery").hide();
	$("#followingContext").show();
	$("#followersContext").hide();
	$("#hasPhotos").hide();
	$("#hasFollowing").show();
	$("#hasFollowers").hide();
	myPostDisplay=0;
	display=0;
};
	
function showFollowers(){
	if(followersIsNew==true){
		followersIsNew=false;
		getFollowersByPerPage();
	}
	$("#grid-gallery").hide();
	$("#followingContext").hide();
	$("#followersContext").show();
	$("#hasPhotos").hide();
	$("#hasFollowing").hide();
	$("#hasFollowers").show();
	myPostDisplay=0;
	display=1;
};
	
//为窗口添加动作
$(document).ready(function(){
	window.addEventListener( 'scroll', function() {
		if(document.body.scrollTop+document.body.clientHeight>=(document.body.scrollHeight)){
			if(display==0){
				getFollowingByPerPage();
			}else if(display==1){
				getFollowersByPerPage();
			}
	    }
	});
});
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
				<s:if test="#request.socialPhotosCount==0">
					<center style="color:#8f8f8f;font-size:22px;">oops,this crappy hasn't upload any photos yet,<a href="javascript:history.go(-1);">click to Go Back.</a></center>
				</s:if>
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
		<div id="hasFollowing"></div>
		<div id="hasFollowers"></div>
		<script type="text/javascript" src="<%=basePath %>js/at.js"></script>
		<%-- <script type="text/javascript" src="<%=basePath %>js/jquery-ui.js"></script> --%>
		<script type="text/javascript" src="<%=basePath%>js/Social_index.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/modernizr.custom.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/imagesloaded.pkgd.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/masonry.pkgd.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/classie.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/cbpGridGallery.js"></script>
		<script>
			new CBPGridGallery( document.getElementById( 'grid-gallery' ) );
		</script>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Camereeye - View</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="styles/demo2.css" />
<link rel="stylesheet" type="text/css" href="styles/component.css" />
<link rel="stylesheet" type="text/css" href="styles/jquery.cssemoticons.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="styles/friendTips.css" />
<link rel="stylesheet" type="text/css" href="styles/showLoading.css" />
<link rel="stylesheet" href="styles/styles_guide.css" type="text/css">
</head>
<body>
<input type="hidden" id="isMine" value="<c:if test="${session.user.id!=obj.user.id}">0</c:if>"/>
<input type="hidden" id="currentUserId" value="${session.user.id}"/>
<input type="hidden" id="userId" value="${obj.user.id}"/>
<div id="grid-gallery" class="grid-gallery">
	<section class="grid-wrap">
		<ul class="grid">
			<li class="grid-sizer"></li><!-- for Masonry column width -->
			<li id='viewDIV' onclick='loadBigPic(${obj.id})'>
				<figure>
					<div id='photo${obj.id}' class='gc' style="display:none">
						<div class='gridpic'><img src="${obj.urlThumb}"/></div>
							<div id="timeS" class='time'>${obj.uploadDate}</div>
					</div>
				</figure>
				<input id='bigPicUrl${obj.id}' type='hidden' value='${obj.url}'/>
			</li>
		</ul>
	</section><!-- // grid-wrap -->
	
	
	<section class="slideshow">
		<ul>
			<li value='${obj.id}'>
				<div id='comment${obj.id}' class='text2'>
					<div id='friendTips${obj.id}' class='friendTip'></div>
					<div style='margin-left:10px;font-size:15px;height:15px;width:300px;margin-top:5px;'>You can also input <charNumber style='font-size:18;font-family:Georgia;color:#FF7748;' id='wordsNumber${obj.id}'>80</charNumber>&nbsp;words.</div>
					<textarea style='margin-left:7px;word-break:break-all;resize: none;width:90%;' rows='3'   id='reply${obj.id}' onkeydown='enterDeal(${obj.id})' onkeyup='getAtName(this.value,${obj.id})'></textarea>
					<input style='position:absolute; left:7px;top:75px' type='button' value='Reply' onclick='comment(${obj.user.id},${obj.id},${obj.user.id})'/><br>
					<input id='commentCount${obj.id}' type='hidden'/>
					<input id='commentPage${obj.id}' type='hidden' value='1'/>
					<div align='left' style='margin:3px 0 0 7px;'>
						<span id='commentRefreshButton${obj.id}' style='display:none'>
							<a style='font-size:small;position:absolute;margin:0 50px 0 100px' href='javascript:void(0)' onclick='reloadReply(${obj.id})'>Refresh</a>
						</span>
						<span id='commentPageTurningButton${obj.id}' style='display:none;font-size:small;position:absolute;margin:0 30px 0 180px'>
							<a href='javascript:void(0)' onclick='showCommentPreviousPage(${obj.id})'>Previous</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href='javascript:void(0)' onclick='showCommentNextPage(${obj.id})'>Next</a>
						</span>
						<span id='pageShow${obj.id}' style='display:none;font-size:small;float:right;margin:0 40px 0 0'>
							Page:<span id='commentCurrentPage${obj.id}'>1</span>/<span id='commentTotalPage${obj.id}'></span>
						</span>&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div style='width:100%;height:20px'></div>
					<span id='commentBody${obj.id}'></span>
				</div>
				<figure>
					<figcaption>
						<div class='p2' id='commentDesc${obj.id}'>${obj.name}</div>
					</figcaption>
					<div id='commentPic${obj.id}' class='slideshowpic'>
						<a href='ShowBigPic.jsp?url=${obj.url}&name=${obj.user.name}' target='_blank' title='Show big size in a new window.'><img id='bigPic${obj.id}' /></a>
					</div>
			    </figure>
			</li>
		</ul>
		<nav>
			<span class="icon nav-prev" style="display:none"></span>
			<span class="icon nav-next" style="display:none"></span>
			<span class="icon nav-close"></span>
		</nav>
	</section><!-- // slideshow -->
</div><!-- // grid-gallery -->
<div id="loadingComment" class="loadComm"></div>

<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/goUrl.js"></script>
<script type="text/javascript" src="js/user/calculateDistanceTime.js"></script>
<script type="text/javascript" src="js/jquery.showLoading.js"></script>
<script type="text/javascript" src="js/user/at.js"></script>
<script type="text/javascript" src="js/jquery.cssemoticons.js"></script>
<script type="text/javascript" src="js/modernizr.custom.js"></script>
<script type="text/javascript" src="js/imagesloaded.pkgd.min.js"></script>
<script type="text/javascript" src="js/masonry.pkgd.min.js"></script>
<script type="text/javascript" src="js/classie.js"></script>
<script type="text/javascript" src="js/user/cbpGridGallery.js"></script>
<script type="text/javascript" src="js/user/getReply.js"></script>
<script>
	new CBPGridGallery( document.getElementById( 'grid-gallery' ) );
	
	//自动加载
	$(function(){ 
		document.getElementById("viewDIV").click();
	}); 
	
	//放大图片之后读取评论
	function loadBigPic(picId){
		$("#bigPic"+picId).attr("src",$("#bigPicUrl"+picId).val());
		//激活图片描述的表情动画
		$('#desContext'+picId).emoticonize();
		//读取评论
		delayLoadCommTime(picId,$("#commentPage"+picId).val(),700);
	};
	
	//评论
	function comment(userBid,photoBid,photoOwnerId){
		var reply = $("#reply"+photoBid).val();
		if(reply.length<1 || reply.length>80){
			alert("reply at least 1 in length,and max at 80.");
			return;
		}
		/** 屏蔽所有按钮 **/
		$("input[type='button']").each(function() {
			this.disabled = true;
		});
		$.ajax({
			url:'user/doReply_execute',
			type:'post',
	        data:"reply.userByUserBid.id="+userBid+"&reply.photo.id="+photoBid+"&reply.context="+reply+"&photo.user.id="+photoOwnerId,
	        async:false,
			success:function (context) {
				if(context.error == undefined){
					/** 恢复所有按钮 **/
					$("input[type='button']").each(function() {
						this.disabled = false;
					});
					$("#reply"+photoBid).val("");
					//统计剩余字数
					wordsNumber(photoBid);
					//获取时间 
					//var date=new Date();
					//即时刷新评论
					reloadReply(photoBid);
				}
			}
		});
	};
</script>
</body>
</html>
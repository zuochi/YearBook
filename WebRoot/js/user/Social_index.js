var httpRequest = null;
function createXMLHttpRequest() {
	try {
		// 创建针对火狐，opera 8.0+,Safari等浏览器的对象
		httpRequest = new XMLHttpRequest();
	} catch (e) {
		// 如果使用的是IE浏览器，将会出现异常
		try {
			httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			httpRequest = new ActiveObject("Microsoft.XMLHTTP");
		}

	}
}

createXMLHttpRequest();

//局部加载图片
var toPageP=1;
var hasPic=1;
var myPostDisplay=1;
var showPhotoLoading=2;

function getPhotosByPerPage(isNew){
	if("true"==isNew){
		toPageP=1;
	}
	if(hasPic==1){
		if("true"!=isNew  && showPhotoLoading==toPageP){
			$("#loadingPhoto").showLoading();//显示读取状态
			showPhotoLoading+=1;
		}
		$.ajax({
			url:'user/getPhotosByPerPage_execute',  
			type:'post', 
			data:"user.id="+$("#userId").val()+"&toPage="+toPageP,
			async:false,
			dataType:'json', 
			success:function (json) {
				if(json.error==undefined){
					//成功之后翻页
					toPageP+=1;
					if(json.length==undefined){
						hasPic=0;
						$("#hasPhotos").html("<br><br><center class='noMoreInfo'>oops,there are no more photos,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top</a>.</center><br><br>");
					}
					for(var i=0; i<json.length; i++){
						$("#photosUL").append(
							($("#isMine").val()==0?($("#currentUserId").val()!=json[i].user_id?("<a href='javascript:void(0)' onclick='accusationPhoto(\""+json[i].id+"\")' title='举报'><div class='report'>举报</div></a>"):""):($("#userId").val()!=json[i].user_id?("<a href='javascript:void(0)' onclick='accusationPhoto(\""+json[i].id+"\")' title='举报'><div class='report'>举报</div></a>"):""))+
							"<li onclick='loadBigPic("+json[i].id+")'>"+
								"<figure>"+
									"<div id='photo"+json[i].id+"' class='gc'>"+
										"<div class='gridpic'><img src='"+json[i].urlThumb+"'/></div>"+
											"<div class='time'>"+calculateDT(json[i].uploadDate)+"</div>"+
									"</div>"+
								"</figure>"+
								"<input id='bigPicUrl"+json[i].id+"' type='hidden' value='"+json[i].url+"'/>"+
							"</li>"
						);
								
						$("#photosUL2").append(
								"<li value='"+json[i].id+"'>"+
								"<div id='comment"+json[i].id+"' class='text2'>"+
								"<div id='friendTips"+json[i].id+"' class='friendTip'></div>"+"<div style='margin-left:10px;font-size:15px;height:15px;width:300px;margin-top:5px;'>You can also input <charNumber style='font-size:18;font-family:Georgia;color:#FF7748;' id='wordsNumber"+json[i].id+"'>80</charNumber>&nbsp;words.</div>"+
								"<textarea style='margin-left:7px;word-break:break-all;resize: none;' rows='3' cols='60'  id='reply"+json[i].id+"' onkeydown='enterDeal("+json[i].id+")' onkeyup='getAtName(this.value,"+json[i].id+")'></textarea>"+
								"<input style='position:absolute; left:7px;top:70px' type='button' value='Reply' onclick='comment("+$("#userId").val()+","+json[i].id+","+$("#userId").val()+")'/><br>"+
								"<input id='commentCount"+json[i].id+"' type='hidden'/>"+
								"<input id='commentPage"+json[i].id+"' type='hidden' value='1'/>"+
								"<div align='left' style='margin:3px 0 0 7px;'>" +
								"<span id='commentRefreshButton"+json[i].id+"' style='display:none'><a style='font-size:small;padding:0 50px 0 120px' href='javascript:void(0)' onclick='reloadReply("+json[i].id+")'>Refresh</a></span>"+
									"<span id='pageShow"+json[i].id+"' style='display:none;font-size:small;'>Page:<span id='commentCurrentPage"+json[i].id+"'>1</span>/<span id='commentTotalPage"+json[i].id+"'></span></span>&nbsp;&nbsp;&nbsp;&nbsp;" +
									"<span id='commentPageTurningButton"+json[i].id+"' style='display:none;font-size:small;'><a href='javascript:void(0)' onclick='showCommentPreviousPage("+json[i].id+")'>Previous</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
									"<a href='javascript:void(0)' onclick='showCommentNextPage("+json[i].id+")'>Next</a></span>&nbsp;&nbsp;&nbsp;&nbsp;" +

								"</div>"+
								"<span id='commentBody"+json[i].id+"'><span>"+
								"</div>"+
								"<figure>"+"<figcaption><div class='p2' id='commentDesc"+json[i].id+"'>"+json[i].name+"</div></figcaption>"+	
									"<div id='commentPic"+json[i].id+"' class='slideshowpic'><a href='ShowBigPic.jsp?url="+json[i].url+"&name="+$("#nickName").val()+"' target='_blank' title='Show big size in a new window.'><img id='bigPic"+json[i].id+"' /></a></div>"+
							    "</figure>"+
							"</li>"
						);
					}
				}else{
					alert("access fail!");
				}
			}
		});
		if("true"!=isNew && showPhotoLoading>=toPageP){
			$("#loadingPhoto").hideLoading();//隐藏读取状态
		}
	}
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
			/** 恢复所有按钮 **/
			$("input[type='button']").each(function() {
				this.disabled = false;
			});
			$("#reply"+photoBid).val("");
			//统计剩余字数
			wordsNumber(photoBid);
			//var date=new Date();
			//即时刷新评论
			reloadReply(photoBid);
			/*$("#commentBody"+photoBid).prepend(
				"<div class='ds-post-main'>"+
					"<div class='ds-avatar'>"+
						"<a title='"+$("#nickName").val()+"' href='javascript:goSocialIndex("+$("#userId").val()+")' target='_blank'><img src='"+$("#urlM").val()+"'></a>"+
					"</div>"+
					"<div class='ds-comment-body'>"+
						"<a title='"+$("#nickName").val()+"' href='javascript:goSocialIndex("+$("#userId").val()+")' target='_blank' class='user-name'>"+$("#nickName").val()+"</a>"+
						"<div class='message'>"+context+"</div>"+
						"<div align='right' class='p1'>"+date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"</div>"+
					"</div>"+
				"</div>"
			);*/
		}
	});
};

//放大图片之后读取评论
function loadBigPic(picId){
	$("#bigPic"+picId).attr("src",$("#bigPicUrl"+picId).val());
	//激活图片表情动画
	$('#commentDesc'+picId).emoticonize();
	//读取评论
	delayLoadCommTime(picId,$("#commentPage"+picId).val(),700);
};

//返回顶部
function scrollToTop(){
	document.body.scrollTop=0;
	document.getElementById("menu").style.display="block";
};

function follow(socialUserId){
	$.ajax({
         url:'user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=follow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followersNumber").html(parseInt($("#followersNumber").html())+parseInt(1));
        		 $("#followAttrDIV").html("<a href='javascript:void(0)' onclick='cancleFollow("+socialUserId+")'><div id='followAttr' class='yiguanzhu'></div></a>");
        	 }else{
        		 operationRepeatWarn();
        	 }
         }
	 });
};

function cancleFollow(socialUserId){
	$.ajax({
         url:'user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=cancelFollow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followersNumber").html(parseInt($("#followersNumber").html())-parseInt(1));
        		 $("#followAttrDIV").html("<a href='javascript:void(0)' onclick='follow("+socialUserId+")'><div id='followAttr' class='guanzhu'></div></a>");
        	 }else{
        		 operationRepeatWarn();
        	 }
         }
	 });
};

function followFriend(socialUserId){
	$.ajax({
         url:'user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=follow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followersNumber").html(parseInt($("#followersNumber").html())+parseInt(1));
        		 $("#followAttrDIV").html("<a href='javascript:void(0)' onclick='cancleFollowFriend("+socialUserId+")'><div id='followAttr' class='friend'></div></a>");
        	 }else{
        		 operationRepeatWarn();
        	 }
         }
	 });
};

function cancleFollowFriend(socialUserId){
	$.ajax({
         url:'user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=cancelFollow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followersNumber").html(parseInt($("#followersNumber").html())-parseInt(1));
        		 $("#followAttrDIV").html("<a href='javascript:void(0)' onclick='followFriend("+socialUserId+")'><div id='followAttr' class='guanzhuFriend'></div></a>");
        	 }else{
        		 operationRepeatWarn();
        	 }
         }
	 });
};

//关注、粉丝、图片切换显示

//following
var followingIsNew=true;
var hasFollowing=1;
var toPageFollowing=1;
function getFollowingByPerPage(){
	if(hasFollowing==1){
		$.ajax({
			url:'user/getSocial_getFollowingByPerPage',  
			type:'post', 
			data:"user.id="+$("#userId").val()+"&isMine="+false+"&toPage="+toPageFollowing,
			async:false,
			dataType:"json",
			success:function (json) {
				if(json.error==undefined){
					//成功之后翻页
					toPageFollowing+=1;
					if(json.length==undefined){
						hasFollowing=0;
						$("#hasFollowing").html("<br><br><br><br><div><center class='noMoreInfo'>oops:(,there are no more following,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top </a>.</center><br><br><br><br>");
					}
					//alert(json[0].relationShip);
					for(var i=0; i<json.length; i++){
						$("#followingContext").append(
							"<div class='one'>"+
							(json[i].userId==$("#currentUserId").val()?"<div class='itisme'></div>":getRelationStatus(json[i].relationShip,"\"followingAttrSpan"+json[i].userId+"\"",json[i].userId,json[i].fname))+
								"<div class='pic'>"+
									"<img src='"+(json[i].lurl!=null&&json[i].lurl!=''?json[i].lurl:"images/bg.png")+"' class='pic-image' alt='Pic'/>"+
									"<span class='pic-caption left-to-right'>"+      
									"<p id='followingSignContext"+json[i].userId+"'+>"+(json[i].sign!=null&&json[i].sign!=''?json[i].sign:"no sign yet.")+"</p>"+
									"</span>"+
								"</div>"+
								"<a href='javascript:void(0)' onclick='goSocialIndex("+json[i].userId+")' target='main'>"+
								"<div id='followFriendNamefollowingAttrSpan"+json[i].userId+"' class='name'>"+(json[i].relationShip==2?"<isFriend style='color:#ff406d' title='we followed each other.'>♥ </isFriend><name title='click to show detail.'>":"<name title='click to show detail.'>")+json[i].fname+"</name></div></a>"+
							"</div>"
						);
						$('#followingSignContext'+json[i].userId).emoticonize();
					}
				}
			}
		});
	}
};

//followers
var followersIsNew=true;
var hasFollowers=1;
var toPageFollowers=1;
function getFollowersByPerPage(){
	if(hasFollowers==1){
		$.ajax({
			url:'user/getSocial_getFollowersByPerPage',  
			type:'post', 
			data:"user.id="+$("#userId").val()+"&isMine="+false+"&toPage="+toPageFollowers,
			async:false,
			dataType:"json",
			success:function (json) {
				if(json.error==undefined){
					//成功之后
					toPageFollowers+=1;
					if(json.length==undefined){
						hasFollowers=0;
						$("#hasFollowers").html("<br><br><br><br><center class='noMoreInfo'>oops:(,there are no more followers,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top</a>.</center><br><br><br><br>");
					}
					for(var i=0; i<json.length; i++){
						$("#followersContext").append(
							"<div class='one'>"+
								(json[i].userId==$("#currentUserId").val()?"<div class='itisme'></div>":getRelationStatus(json[i].relationShip,"\"followerAttrSpan"+json[i].userId+"\"",json[i].userId,json[i].fname))+
								"<div class='pic'>"+
									"<img src='"+(json[i].lurl!=null&&json[i].lurl!=''?json[i].lurl:"images/bg.png")+"' class='pic-image' alt='Pic'/>"+
									"<span class='pic-caption left-to-right'>"+      
									"<p id='followersSignContext"+json[i].userId+"'>"+(json[i].sign!=null&&json[i].sign!=''?json[i].sign:"no sign yet.")+"</p>"+
									"</span>"+
								"</div>"+
								"<a href='javascript:void(0)' onclick='goSocialIndex("+json[i].userId+")' target='main'>"+
								"<div id='followFriendNamefollowerAttrSpan"+json[i].userId+"' class='name'>"+(json[i].relationShip==2?"<isFriend style='color:#ff406d' title='we followed each other.'>♥ </isFriend><name title='click to show detail.'>":"<name title='click to show detail.'>")+json[i].fname+"</name></div></a>"+
							"</div>"
						);
						$('#followersSignContext'+json[i].userId).emoticonize();
					}
				}
			}
		});
	}
};

function getRelationStatus(relationShip,elementId,userId,fname){
	if(relationShip==0){
		return ("<span id="+elementId+"><a href='javascript:void(0)' onclick='followSmall("+elementId+","+userId+")'><div class='guanzhuSmall'></div></a></span>");
	}else if(relationShip==1){
		return ("<span id="+elementId+"><a href='javascript:void(0)' onclick='cancleFollowSmall("+elementId+","+userId+")'><div class='yiguanzhuSmall'></div></a></span>");
	}else if(relationShip==2){
		return ("<span id="+elementId+"><a href='javascript:void(0)' onclick='cancleFollowFriendSmall("+elementId+","+userId+",\""+fname+"\")'><div class='friendSmall'></div></a></span>");
	}else{
		return ("<span id="+elementId+"><a href='javascript:void(0)' onclick='followFriendSmall("+elementId+","+userId+",\""+fname+"\")'><div class='guanzhuFriendSmall'></div></a></span>");
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
	$("#grid-gallery").hide();
	$("#followingContext").show();
	$("#followersContext").hide();
	$("#hasPhotos").hide();
	$("#hasFollowing").show();
	$("#hasFollowers").hide();
	if($("#hasBeenModify").val()==1){
		hasFollowing=1;
		toPageFollowing=0;
		followingIsNew=true;
		$("#followingContext").html("");
		$("#hasFollowing").html("");
		$("#hasBeenModify").val(0);
	}
	if(followingIsNew==true){
		followingIsNew=false;
		getFollowingByPerPage();
	}
	myPostDisplay=0;
	display=0;
};
	
function showFollowers(){
	$("#grid-gallery").hide();
	$("#followingContext").hide();
	$("#followersContext").show();
	$("#hasPhotos").hide();
	$("#hasFollowing").hide();
	$("#hasFollowers").show();
	if($("#hasBeenModify").val()==1){
		hasFollowers=1;
		toPageFollowers=0;
		followersIsNew=true;
		$("#followersContext").html("");
		$("#hasFollowers").html("");
		$("#hasBeenModify").val(0);
	}
	if(followersIsNew==true){
		followersIsNew=false;
		getFollowersByPerPage();
	}
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
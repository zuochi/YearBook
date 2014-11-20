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
function getPhotosByPerPage(isNew){
	if("true"==isNew){
		toPageP=0;
	}
	if(hasPic==1){
	toPageP+=1;
	$.ajax({
		url:'/YearBook/user/getPhotosByPerPage_execute',  
		type:'post', 
		data:"user.id="+document.getElementById("userId").value+"&toPage="+toPageP,
		async:false,
		dataType:'json', 
		success:function (json) {
			if(json.error==undefined){
				if(json.length==undefined){
					hasPic=0;
					$("#hasPhotos").html("<br><br><center style='color:#8f8f8f;font-size:22px;'>oops,there are no more photos,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top </a>OR <a href='javascript:history.go(-1);'>click to Go Back.</a></center><br><br>");
				}
				for(var i=0; i<json.length; i++){
					$("#photosUL").append(
						"<li onclick='loadBigPic("+json[i].id+")'>"+
							"<figure>"+
								"<div id='photo"+json[i].id+"' class='gc'>"+
									"<div class='gridpic'><img src='"+document.getElementById("basePath").value+json[i].urlThumb+"'/></div>"+
										"<div class='time'>"+calculateDT(json[i].uploadDate)+"</div>"+
										/*"<div class='time'>20"+(json[i].uploadDate.year-100)+"-"+
										(json[i].uploadDate.month+1)+"-"+
										json[i].uploadDate.date+" "+
										json[i].uploadDate.hours+":"+json[i].uploadDate.minutes+":"+json[i].uploadDate.seconds+
										"</div>"+*/
								"</div>"+
							"</figure>"+
							"<input id='bigPicUrl"+json[i].id+"' type='hidden' value='"+json[i].url+"'/>"+
						"</li>"
					);
							
					$("#photosUL2").append(
							"<li value='"+json[i].id+"'>"+
							"<div id='comment"+json[i].id+"' class='text2'>"+
							"<div id='friendTips"+json[i].id+"' class='friendTip'></div>"+"<div style='margin-left:10px;font-size:15px;height:15px;width:200px;margin-top:5px;'>You can also input <charNumber style='font-size:18;font-family:Georgia;color:#FF7748;' id='wordsNumber"+json[i].id+"'>80</charNumber>&nbsp;words.</div>"+
							"<textarea style='margin-left:7px;word-break:break-all;resize: none;' rows='3' cols='52'  id='reply"+json[i].id+"' onkeydown='enterDeal("+json[i].id+")' onkeyup='getAtName(this.value,"+json[i].id+")'></textarea>"+
							"<input style='position:absolute; left:340px;top:70px' type='button' value='Reply' onclick='comment("+document.getElementById("userId").value+","+json[i].id+")'/><br>"+
							"<input id='commentCount"+json[i].id+"' type='hidden'/>"+
							"<input id='commentPage"+json[i].id+"' type='hidden' value='1'/>"+
							"<div align='left' style='margin:3px 0 0 7px;'>" +
								"<span id='loadingComment"+json[i].id+"' class='loadComm'></span>"+
								"<span id='pageShow"+json[i].id+"' style='display:none'>Page:<span id='commentCurrentPage"+json[i].id+"'>1</span>/<span id='commentTotalPage"+json[i].id+"'></span></span>&nbsp;&nbsp;&nbsp;&nbsp;" +
								"<span id='commentPageTurningButton"+json[i].id+"' style='display:none'><a href='javascript:void(0)' onclick='showCommentPreviousPage("+json[i].id+")'>Previous</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
								"<a href='javascript:void(0)' onclick='showCommentNextPage("+json[i].id+")'>Next</a></span>&nbsp;&nbsp;&nbsp;&nbsp;" +
								"<span id='commentRefreshButton"+json[i].id+"' style='display:none'><a href='javascript:void(0)' onclick='reloadReply("+json[i].id+")'>Refresh</a></span>"+
							"</div>"+
							"<span id='commentBody"+json[i].id+"'><span>"+
							"</div>"+
							"<figure>"+"<figcaption><div class='p2' id='commentDesc"+json[i].id+"'>"+json[i].name+"</div></figcaption>"+	
								"<div id='commentPic"+json[i].id+"' class='slideshowpic'><a href='"+document.getElementById("basePath").value+json[i].url+"' target='_blank' title='点击在新页面中打开'><img id='bigPic"+json[i].id+"' /></a></div>"+
						    "</figure>"+
						"</li>"
					);
				}
			}else{
				alert("access fail!");
			}
		}
	});
	}
};

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
		success:function (context) {
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

//放大图片之后读取
function loadBigPic(picId){
	//$("#loadingBigPhoto"+picId).showLoading();//显示图片读取状态
	document.getElementById("bigPic"+picId).src=document.getElementById("basePath").value+document.getElementById("bigPicUrl"+picId).value;
	//$("#loadingBigPhoto"+picId).hideLoading();//隐藏图片读取状态
	//document.getElementById("reply"+picId).focus();
	//激活图片表情动画
	$('#commentDesc'+picId).emoticonize();
	//读取评论
	delayLoadCommTime(picId,$("#commentPage"+picId).val(),700);
};

//返回顶部
function scrollToTop(){
	document.body.scrollTop=0;
};

function follow(socialUserId){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=follow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followersNumber").html(parseInt($("#followersNumber").html())+parseInt(1));
        		 $("#followAttrDIV").html("<a href='javascript:void(0)' onclick='cancleFollow("+socialUserId+")'><div id='followAttr' class='yiguanzhu'></div></a>");
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
        		 $("#followersNumber").html(parseInt($("#followersNumber").html())-parseInt(1));
        		 $("#followAttrDIV").html("<a href='javascript:void(0)' onclick='follow("+socialUserId+")'><div id='followAttr' class='guanzhu'></div></a>");
        	 }else{
        		alert("操作失败");
        	 }
         }
	 });
};

function followFriend(socialUserId){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=follow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followersNumber").html(parseInt($("#followersNumber").html())+parseInt(1));
        		 $("#followAttrDIV").html("<a href='javascript:void(0)' onclick='cancleFollowFriend("+socialUserId+")'><div id='followAttr' class='friend'></div></a>");
        	 }else{
        		 alert("操作失败");
        	 }
         }
	 });
};

function cancleFollowFriend(socialUserId){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=cancelFollow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followersNumber").html(parseInt($("#followersNumber").html())-parseInt(1));
        		 $("#followAttrDIV").html("<a href='javascript:void(0)' onclick='followFriend("+socialUserId+")'><div id='followAttr' class='guanzhuFriend'></div></a>");
        	 }else{
        		alert("操作失败");
        	 }
         }
	 });
};

//关注、粉丝、图片切换显示

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
								"<div class='name'>"+(json[i].isFriend==true?"<isFriend style='color:#ff406d' title='we followed each other.'>♥ </isFriend><name title='click to show detail.'>":"<name title='click to show detail.'>")+json[i].fname+"</name></div></a>"+
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
								"<div class='name'>"+(json[i].isFriend==true?"<isFriend style='color:#ff406d' title='we followed each other.'>♥ </isFriend><name title='click to show detail.'>":"<name title='click to show detail.'>")+json[i].fname+"</name></div></a>"+
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
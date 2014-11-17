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

//放大图片之后读取
function loadBigPic(picId){
	document.getElementById("bigPic"+picId).src=document.getElementById("basePath").value+document.getElementById("bigPicUrl"+picId).value;
};
		
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
										"<div class='time'>20"+(json[i].uploadDate.year-100)+"-"+
										(json[i].uploadDate.month+1)+"-"+
										json[i].uploadDate.date+" "+
										json[i].uploadDate.hours+":"+json[i].uploadDate.minutes+":"+json[i].uploadDate.seconds+
										"</div>"+
								"</div>"+
							"</figure>"+
							"<input id='bigPicUrl"+json[i].id+"' type='hidden' value='"+json[i].url+"'/>"+
						"</li>"
					);
							
					$("#photosUL2").append(
						"<li value='"+json[i].id+"'>"+
						"<div id='comment"+json[i].id+"' class='text2'>"+
						"<div id='friendTips"+json[i].id+"' class='friendTip'></div>"+"<div style='margin-left:10px;font-size:15px;height:15px;width:200px;margin-top:5px;'>You can also input <charNumber style='font-size:18;font-family:Georgia;color:#FF7748;' id='wordsNumber"+json[i].id+"'>80</charNumber>&nbsp;words.</div>"+
						"<textarea style='word-break:break-all;resize: none;' rows='3' cols='50' id='reply"+json[i].id+"' onkeydown='enterDeal("+json[i].id+")' onkeyup='getAtName(this.value.charAt(value.length-1),"+json[i].id+")'></textarea><br>"+
						"<input type='button' value='Reply' onclick='comment("+document.getElementById("userId").value+","+json[i].id+")'/>"+
							"<div class='ds-post-main'>"+
									"<div class='ds-avatar'>"+
										"<a title='用户名' href='' target='_blank'><img src='"+document.getElementById("basePath").value+"images/alex.jpg'></a>"+
									"</div>"+
									"<div class='ds-comment-body'>"+
										"<a title='用户名' href='' target='_blank' class='user-name'>用户名</a>"+
										"<div class='message'>what'up man.heyyo,我想长高10厘米，增重15斤 </div>"+
										"<div class='p1'>时间：2015年9月14日22:38</div>"+
									"</div>"+
								"</div>"+
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
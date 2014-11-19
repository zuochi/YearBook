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

//上传图片
function uploadPhoto(){
	window.location.href=document.getElementById("basePath").value+"User_photoUpload.jsp";
};

//局部加载图片
var type="mypost";
var toPageP=1;
var hasPic=1;
var myPostDisplay=1;
var socialDisplay=0;

function myPostShow(){
	myPostDisplay=1;
	socialDisplay=0;
	$("#socialDiv").hide();
	$("#photosUL").show();
	$("#photosUL2").show();
	$("#hasPhotos").show();
	$("#myPostSelectd").removeClass().addClass("mypostSelect"); 
	$("#socialSelectd").removeClass().addClass("social"); 
};
		
function socialShow(){
	myPostDisplay=0;
	socialDisplay=1;
	$("#socialDiv").show();
	$("#photosUL").hide();
	$("#photosUL2").hide();
	$("#hasPhotos").hide();
	$("#myPostSelectd").removeClass().addClass("mypost"); 
	$("#socialSelectd").removeClass().addClass("socialSelect"); 
};

function getPhotosByPerPage(isNew){
	if("true"==isNew){
		toPageP=0;
	}
	if(hasPic==1){
		//$("#showPhotosLoading").showLoading();//显示读取状态
		//自动返向下一页
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
						$("#hasPhotos").html("<br><br><br><br><center style='color:#8f8f8f; font-size:22px;'>oops,there are no more photos,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top.</a></center><br><br><br><br>");
					}
					for(var i=0; i<json.length; i++){
						$("#photosUL").append(
							"<li onclick='loadBigPic("+json[i].id+")'>"+
								"<figure>"+
									"<div id='photo"+json[i].id+"' class='gc'>"+
										"<span id='loadingBigPhoto"+json[i].id+"'></span>"+
										"<div class='gridpic'><img src='"+document.getElementById("basePath").value+json[i].urlThumb+"'/></div>"+
											"<div class='time'>20"+(json[i].uploadDate.year-100)+"-"+
											(json[i].uploadDate.month+1)+"-"+
											json[i].uploadDate.date+" "+
											json[i].uploadDate.hours+":"+json[i].uploadDate.minutes+":"+json[i].uploadDate.seconds+
											"</div>"+
									"</div>"+
								"</figure>"+
								"<input id='bigPicUrl"+json[i].id+"' type='hidden' value='"+json[i].url+"'/>"+
							"</li>"+
							"<div id='photoOPE"+json[i].id+"'>"+
								"<a href='javascript:void(0)' onclick='iWantTop("+json[i].id+","+$("#userId").val()+")'><div class='up' title='apply to post on Home Page.'></div>" +
								"<a href='javascript:void(0)' onclick='deletePhoto("+json[i].id+")'><div class='de' title='delete this Post.'></div>"+
							"</div>"
						);
								
						$("#photosUL2").append(
							"<li value='"+json[i].id+"'>"+
							"<div id='comment"+json[i].id+"' class='text2'>"+
							"<div id='friendTips"+json[i].id+"' class='friendTip'></div>"+"<div style='margin-left:10px;font-size:15px;height:15px;width:200px;margin-top:5px;'>You can also input <charNumber style='font-size:18;font-family:Georgia;color:#FF7748;' id='wordsNumber"+json[i].id+"'>80</charNumber>&nbsp;words.</div>"+
							"<textarea style='word-break:break-all;resize: none;' rows='3' cols='50'  id='reply"+json[i].id+"' onkeydown='enterDeal("+json[i].id+")' onkeyup='getAtName(this.value.charAt(value.length-1),"+json[i].id+")'></textarea>"+
							"<input type='button' value='Reply' onclick='comment("+document.getElementById("userId").value+","+json[i].id+")'/>"+
							"<input id='commentCount"+json[i].id+"' type='hidden'/>"+
							"<input id='commentPage"+json[i].id+"' type='hidden' value='1'/>"+
							"<div align='center'>" +
								"<span id='loadingComment"+json[i].id+"'></span>"+
								"<span id='pageShow"+json[i].id+"'>Page:<span id='commentCurrentPage"+json[i].id+"'>1</span>/<span id='commentTotalPage"+json[i].id+"'></span></span>&nbsp;&nbsp;&nbsp;&nbsp;" +
								"<span id='commentPageTurningButton"+json[i].id+"'><a href='javascript:void(0)' onclick='showCommentPreviousPage("+json[i].id+")'>Previous</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
								"<a href='javascript:void(0)' onclick='showCommentNextPage("+json[i].id+")'>Next</a><span>&nbsp;&nbsp;&nbsp;&nbsp;" +
								"<a href='javascript:void(0)' onclick='reloadReply("+json[i].id+")'>Refresh</a><span>" +
							"</div>"+
							"<span id='commentBody"+json[i].id+"'><span>"+
							"</div>"+
								"<figure>"+	
								"<figcaption><a href='javascript:void(0)' title='edit Post's description.' onclick='showDesEdit("+json[i].id+")'><div id='desPenButton"+json[i].id+"' class='pen'></div></a>" +
								"<input type='text' id='desTextArea"+json[i].id+"' style='display:none;' maxlength='60'/>" +
								"<input id='updateDesButton"+json[i].id+"' style='display:none' type='button' value='Edit' onclick='updateDes("+json[i].id+")'/>"+
								"<des class='p2' id='desContext"+json[i].id+"'>"+json[i].name+"</des>" +
							    "</figcaption>"+
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
		//$("#showPhotosLoading").showLoading();//隐藏读取状态
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
			if(context.error == undefined){
				$("#reply"+photoBid).val("");
				//统计剩余字数
				wordsNumber(photoBid);
				//获取时间 
				var date=new Date();
				//即时刷新评论
				$("#commentBody"+photoBid).prepend(
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
				);
			}
		}
	});
};

//放大图片之后读取
function loadBigPic(picId){
	$("#loadingBigPhoto"+photoBid).showLoading();//显示图片读取状态
	document.getElementById("bigPic"+picId).src=document.getElementById("basePath").value+document.getElementById("bigPicUrl"+picId).value;
	$("#loadingBigPhoto"+photoBid).hideLoading();//隐藏图片读取状态
	//document.getElementById("reply"+picId).focus();
	//读取评论
	showComments(picId,$("#commentPage"+picId).val());
};

//评论上翻页
function showCommentPreviousPage(photoBid){
	//上翻过头复原
	if(parseInt($("#commentPage"+photoBid).val())>1){
		$("#commentPage"+photoBid).val(parseInt($("#commentPage"+photoBid).val())-parseInt(1));
		$("#commentBody"+photoBid).html("");
		$("#commentCurrentPage"+photoBid).html($("#commentPage"+photoBid).val());
		showComments(photoBid,$("#commentPage"+photoBid).val());
	}
};

//评论下翻页
function showCommentNextPage(photoBid){
	//下翻过头复原
	if( parseInt($("#commentPage"+photoBid).val()) < parseInt($("#commentTotalPage"+photoBid).html())){
		$("#commentPage"+photoBid).val(parseInt($("#commentPage"+photoBid).val())+parseInt(1));
		$("#commentBody"+photoBid).html("");
		$("#commentCurrentPage"+photoBid).html($("#commentPage"+photoBid).val());
		showComments(photoBid,$("#commentPage"+photoBid).val());
	}
};

//显示评论
function showComments(photoBid,toPageC){
	if($("#commentBody"+photoBid).text()==''){
		$("#loadingComment"+photoBid).showLoading();//显示读取状态
		//获取总条数
		getReplyCount(photoBid);
		$.ajax({
			url:'/YearBook/user/getReply_execute',  
			type:'post', 
	        data:"photo.id="+photoBid+"&toPage="+toPageC+"&replyCountN="+$("#commentCount"+photoBid).val(),
	        async:false,
	        dataType:'json', 
			success:function (json) {
				if(json.error==undefined){
					for(var i=0; i<json.length; i++){
						$("#commentBody"+photoBid).append(
							"<div class='ds-post-main'>"+
								"<div class='ds-avatar'>"+
									"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_bid+")' target='_blank'><img src='"+json[i].url_m+"'></a>"+
								"</div>"+
								"<div class='ds-comment-body'>"+
									"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_bid+")' target='_blank' class='user-name'>"+json[i].name+"</a>"+
									"<div class='message'>"+json[i].context+"</div>"+
									"<div align='right' class='p1'>20"+(json[i].signup_date.year-100)+"-"+
										(json[i].signup_date.month+1)+"-"+
										json[i].signup_date.date+" "+
										json[i].signup_date.hours+":"+json[i].signup_date.minutes+":"+json[i].signup_date.seconds+"</div>"+
								"</div>"+
							"</div>"	
						);
					}
				}
			}
		});
		$("#loadingComment"+photoBid).hideLoading();//隐藏读取状态
	}
};

//获取评论的条数
function getReplyCount(photoBid){
	$.ajax({
		url:'/YearBook/user/getReply_getReplyCount',  
		type:'post', 
        data:"photo.id="+photoBid,
        async:false,
        dataType:'text', 
		success:function (json) {
			if(json!="fail"){
				//如果没有评论则不显示翻页或者评论数少于5
				if(parseInt(json)<5){
					$("#commentPageTurningButton"+photoBid).hide();
				}else{
					$("#commentPageTurningButton"+photoBid).show();
				}
				$("#commentCount"+photoBid).val(json);
				//计算总页数
				if(parseInt(json)==0){
					$("#pageShow"+photoBid).hide();
					$("#commentBody"+photoBid).append("<div align='center'>no reply here,click <a href='javascript:void(0)' onclick='reloadReply("+photoBid+")'>Refresh</a> to receive the latest replys.<div>");
				}else{
					$("#pageShow"+photoBid).show();
					var totalPages = 0 ;
					if((parseInt(json)%5) == 0){
						totalPages = parseInt(json) / 5;
					}else{
						totalPages = parseInt(json) / 5 + 1;
					}
					$("#commentTotalPage"+photoBid).html(parseInt(totalPages));
				}
			}
		}
	});
};

//重新读取评论
function reloadReply(photoBid){
	$("#commentPage"+photoBid).val(parseInt(1));
	$("#commentBody"+photoBid).html("");
	$("#commentCurrentPage"+photoBid).html(1);
	showComments(photoBid,1);
};

//改写图片描述
function updateDes(photoId){
	$("#desPenButton"+photoId).show();
	$("#desTextArea"+photoId).hide();
	$("#updateDesButton"+photoId).hide();
	
	//若是重复 则不提交
	if($("#desContext"+photoId).html() != $("#desTextArea"+photoId).val()){
		//更新图片描述
		$.ajax({
			url:'/YearBook/user/updatePhoto_updateDescription',  
			type:'post', 
	        data:"photo.id="+photoId+"&photo.name="+$("#desTextArea"+photoId).val(),
	        async:false,
			dataType:'text', 
			success:function (result) {
				if(result=="success"){
					$("#desContext"+photoId).html($("#desTextArea"+photoId).val());
				}
			}
		});
	}
	$("#desContext"+photoId).show();
	$("#desContext"+photoId).css("width","350px");
};

//显示图片描述
function showDesEdit(photoId){
	$("#desPenButton"+photoId).hide();
	$("#desContext"+photoId).hide();
	$("#desTextArea"+photoId).show();
	$("#desTextArea"+photoId).val($("#desContext"+photoId).html());
	$("#updateDesButton"+photoId).show();
	$("#desTextArea"+photoId).css("width","350px");
	
	//聚焦
	$("#desTextArea"+photoId).focus();
};

//改写签名
function updateSign(){
	$("#signButton").show();
	$("#signTextArea").hide();
	$("#updateSignButton").hide();
	
	//若是重复 则不提交
	if($("#signContext").html() != $("#signTextArea").val()){
		//更新签名
		$.ajax({
			url:'/YearBook/user/updateUser_updateSign',  
			type:'post', 
	        data:"user.id="+$("#userId").val()+"&user.sign="+$("#signTextArea").val(),
	        async:false,
			dataType:'text', 
			success:function (result) {
				if(result=="success"){
					$("#signContext").html($("#signTextArea").val());
				}
			}
		});
	}
	$("#signContext").show();
	$("#signContext").css("width","350px");
};

//显示签名框 
function showSignEdit(){
	$("#signButton").hide();
	$("#signContext").hide();
	$("#signTextArea").show();
	$("#signTextArea").val($("#signContext").html());
	$("#updateSignButton").show();
	$("#signTextArea").css("width","350px");
	
	//聚焦
	$("#signTextArea").focus();
};

//返回顶部
function scrollToTop(){
	document.body.scrollTop=0;
};
		
function mypost(userId){
	window.location.href="/YearBook/user/getMyPost_execute?userId="+userId;
};
		
function social(userId){
	window.location.href="/YearBook/user/getSocial_execute?userId="+userId;
};
		
//删除照片
var loadPhotoCount = false;
var photoCount;
function deletePhoto(photoId){
	if(!loadPhotoCount){
		photoCount = $("#photoCountHidden").val();
		loadPhotoCount = true;
	}
	if(confirm("Are you sure to delete this photo?")) { 
		  $.ajax({
			url:'/YearBook/user/deletePhoto_execute',  
			type:'post', 
			data:"photo.id="+photoId,
			async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){   
					photoCount = photoCount-1;
					$("#picCountDiv").html(""+photoCount);
					$("#photoOPE"+photoId).html("<div class='up' title='我要上首页'></div>"+"<div class='de' title='删除'></div>");
					$("#photo"+photoId).html("<div class='gridpic'><center><img src='"+document.getElementById("basePath").value+"images/deleted.jpg"+"'/></center></div>");
					$("#comment"+photoId).html("<center>&nbsp;</center>");
					$("#commentPic"+photoId).html("<center><div id='bigPic"+photoId+"' ><img src='"+document.getElementById("basePath").value+"images/deletedBig.jpg"+"'/></center></div></center>");
					$("#commentDesc"+photoId).html("<center>&nbsp;</center>");
				 }else{
					alert("delete " + msg);
				}
			}
		});
	} 
};

//我要上首页
function iWantTop(photoId,userId){
	if(confirm("Are you sure to submit this photo to admin?")) { 
		$.ajax({
			url:'/YearBook/user/iwantTop_execute',  
			type:'post', 
		    data:"photo.id="+photoId+"&user.id="+userId,
			async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){
					alert("submit " + msg + ".");
				}else{
					alert("submit " + msg + ",this photo had been sumited.");
				}
			}
		});
	}
};

//跳去关注或者粉丝列表
function goUrl(type){
	if(type==4){
		window.parent.main.location.href="friend.jsp?type=following";
	}else if(type==11){
		window.parent.main.location.href="friend.jsp?type=followers";
	}
};

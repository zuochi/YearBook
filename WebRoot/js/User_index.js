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
											"<div class='time'>"+calculateDT(json[i].uploadDate)+"</div>"+
											/*"<div class='time'>20"+(json[i].uploadDate.year-100)+"-"+
											(json[i].uploadDate.month+1)+"-"+
											json[i].uploadDate.date+" "+
											json[i].uploadDate.hours+":"+json[i].uploadDate.minutes+":"+json[i].uploadDate.seconds+
											"</div>"+*/
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
							"<textarea style='margin-left:7px;word-break:break-all;resize: none;' rows='3' cols='52'  id='reply"+json[i].id+"' onkeydown='enterDeal("+json[i].id+")' onkeyup='getAtName(this.value.charAt(value.length-1),"+json[i].id+")'></textarea>"+
							"<input style='position:absolute; left:340px;top:70px' type='button' value='Reply' onclick='comment("+document.getElementById("userId").value+","+json[i].id+")'/><br>"+
							"<input id='commentCount"+json[i].id+"' type='hidden'/>"+
							"<input id='commentPage"+json[i].id+"' type='hidden' value='1'/>"+
							"<div align='left' style='margin:3px 0 0 7px;'>" +
								"<span id='loadingComment"+json[i].id+"' class='loadComm'></span>"+
								"<span id='pageShow"+json[i].id+"' style='display:none'>Page:<span id='commentCurrentPage"+json[i].id+"'>1</span>/<span id='commentTotalPage"+json[i].id+"'></span></span>&nbsp;&nbsp;&nbsp;&nbsp;" +
								"<span id='commentPageTurningButton"+json[i].id+"' style='display:none'><a href='javascript:void(0)' onclick='showCommentPreviousPage("+json[i].id+")'>Previous</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
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
		//$("#showPhotosLoading").hideLoading();//隐藏读取状态
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
		}
	});
};

//放大图片之后读取
function loadBigPic(picId){
	document.getElementById("loadingComment"+picId).display="block";
	document.getElementById("pageShow"+picId).display="block";
	//$("#loadingBigPhoto"+picId).showLoading();//显示图片读取状态
	document.getElementById("bigPic"+picId).src=document.getElementById("basePath").value+document.getElementById("bigPicUrl"+picId).value;
	//$("#loadingBigPhoto"+picId).hideLoading();//隐藏图片读取状态
	//document.getElementById("reply"+picId).focus();
	//读取评论
	showComments(picId,$("#commentPage"+picId).val());
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

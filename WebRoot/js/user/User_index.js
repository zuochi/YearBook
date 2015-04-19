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
var showPhotoLoading=2;

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
	//读取可能认识的人
	loadNearBy();
};

function getPhotosByPerPage(isNew){
	if("true"==isNew){
		toPageP=1;
	}
	if(hasPic==1){
		if("true"!=isNew && showPhotoLoading==toPageP){
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
						$("#hasPhotos").html("<br><br><br><br><center style='color:#8f8f8f; font-size:20px;'>oops,there are no more photos,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top.</a></center><br><br><br><br>");
					}
					for(var i=0; i<json.length; i++){
						$("#photosUL").append(
							"<li onclick='loadBigPic("+json[i].id+")'>"+
								"<figure>"+
									"<div id='photo"+json[i].id+"' class='gc'>"+
										"<span id='loadingBigPhoto"+json[i].id+"'></span>"+
										"<div class='gridpic'><img src='"+json[i].urlThumb+"'/></div>"+
											"<div class='time'>"+calculateDT(json[i].uploadDate)+"</div>"+
									"</div>"+
								"</figure>"+
								"<input id='bigPicUrl"+json[i].id+"' type='hidden' value='"+json[i].url+"'/>"+
							"</li>"+
							"<div id='photoOPE"+json[i].id+"'>"+"<div class='operate'>"+
							"<a href='javascript:void(0)' onclick='deletePhoto("+json[i].id+")'><div class='de' title='delete this Post.'>删除</div>"+
								"<a href='javascript:void(0)' onclick='iWantTop("+json[i].id+","+$("#userId").val()+")'><div class='up' title='apply to post on Home Page.'>上首页</div>" +
							"</div>"+"</div>"
						);
								
						$("#photosUL2").append(
							"<li value='"+json[i].id+"'>"+
							"<div id='comment"+json[i].id+"' class='text2'>"+
							"<div id='friendTips"+json[i].id+"' class='friendTip'></div>"+"<div style='margin-left:10px;font-size:15px;height:15px;width:300px;margin:5px;'>You can also input <charNumber style='font-size:18;font-family:Georgia;color:#FF7748;' id='wordsNumber"+json[i].id+"'>80</charNumber>&nbsp;words.</div>"+
							"<textarea style='margin-left:7px;word-break:break-all;resize: none;width:90%;' rows='3'   id='reply"+json[i].id+"' onkeydown='enterDeal("+json[i].id+")' onkeyup='getAtName(this.value,"+json[i].id+")'></textarea>"+
							"<input style='position:absolute; left:7px;top:75px' type='button' value='Reply' onclick='comment("+$("#userId").val()+","+json[i].id+","+$("#userId").val()+")'/><br>"+
							"<input id='commentCount"+json[i].id+"' type='hidden'/>"+
							"<input id='commentPage"+json[i].id+"' type='hidden' value='1'/>"+
							"<div align='left' style='margin:3px 0 0 7px;'>" +
							"<span id='commentRefreshButton"+json[i].id+"' style='display:none'><a style='font-size:small;position:absolute;margin:0 50px 0 100px' href='javascript:void(0)' onclick='reloadReply("+json[i].id+")'>Refresh</a></span>"+
								"<span id='commentPageTurningButton"+json[i].id+"' style='display:none;font-size:small;position:absolute;margin:0 30px 0 180px'><a href='javascript:void(0)' onclick='showCommentPreviousPage("+json[i].id+")'>Previous</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
								"<a href='javascript:void(0)' onclick='showCommentNextPage("+json[i].id+")'>Next</a></span>" +
								"<span id='pageShow"+json[i].id+"' style='display:none;font-size:small;float:right;margin:0 40px 0 0'>Page:<span id='commentCurrentPage"+json[i].id+"'>1</span>/<span id='commentTotalPage"+json[i].id+"'></span></span>&nbsp;&nbsp;&nbsp;&nbsp;" +
							"</div>"+"<div style='width:100%;height:20px'>"+"</div>"+
							"<span id='commentBody"+json[i].id+"'></span>"+
							"</div>"+
								"<figure>"+	
								"<figcaption ><a href='javascript:void(0)' title='edit Post's description.' onclick='showDesEdit("+json[i].id+")'><div id='desPenButton"+json[i].id+"' class='pen'>Edit</div></a>" +
								"<input type='text' id='desTextArea"+json[i].id+"' style='display:none;' maxlength='60'/>" +
								"<input id='updateDesButton"+json[i].id+"' style='display:none' type='button' value='Edit' onclick='updateDes("+json[i].id+")'/>"+
								"<des class='p2' id='desContext"+json[i].id+"'>"+json[i].name+"</des>" +
							    "</figcaption>"+
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
				/*$("#commentBody"+photoBid).prepend(
					"<div class='ds-post-main'>"+
						"<div class='ds-avatar'>"+
							"<a title='"+$("#nickName").val()+"' href='javascript:goSocialIndex("+$("#userId").val()+")' target='_blank'><img src='"+$("#urlM").val()+"'></a>"+
						"</div>"+
						"<div class='ds-comment-body'>"+
							"<a title='"+$("#nickName").val()+"' href='javascript:goSocialIndex("+$("#userId").val()+")' target='_blank' class='user-name'>"+$("#nickName").val()+"</a>"+
							"<div class='message' >"+context+"</div>"+
							"<div align='right' class='p1'>"+date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"</div>"+
						"</div>"+
					"</div>"
				);*/
			}
		}
	});
};

//放大图片之后读取评论
function loadBigPic(picId){
	$("#bigPic"+picId).attr("src",$("#bigPicUrl"+picId).val());
	//激活图片描述的表情动画
	$('#desContext'+picId).emoticonize();
	//读取评论
	delayLoadCommTime(picId,$("#commentPage"+picId).val(),700);
};

//显示图片描述
function showDesEdit(photoId){
	$('#desContext'+photoId).unemoticonize({
		//delay: 0,
		animate: true,
		//exclude: 'pre, code, .no-emoticons'
	});
	setTimeout("showDesEditElement("+photoId+")", 1000);
};

//显示描述修改的元素
function showDesEditElement(photoId){
	$("#desPenButton"+photoId).hide();
	$("#desContext"+photoId).hide();
	$("#desTextArea"+photoId).show();
	$("#desTextArea"+photoId).val($("#desContext"+photoId).html());
	$("#updateDesButton"+photoId).show();
	$("#desTextArea"+photoId).css("width","350px");
	
	//聚焦
	$("#desTextArea"+photoId).focus();
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
			url:'user/updatePhoto_updateDescription',  
			type:'post', 
	        data:"photo.id="+photoId+"&photo.name="+$("#desTextArea"+photoId).val(),
	        async:false,
			dataType:'text', 
			success:function (result) {
				if(result=="success"){
					$("#desContext"+photoId).html($("#desTextArea"+photoId).val());
					$("#desContextShow"+photoId).html($("#desTextArea"+photoId).val());
				}
			}
		});
	}
	$("#desContext"+photoId).show();
	$("#desContext"+photoId).css("width","350px");
	$('#desContext'+photoId).emoticonize();
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
			url:'user/updateUser_updateSign',  
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
	$('#signContext').emoticonize();
};

//显示签名框 
function showSignEdit(){
	$('#signContext').unemoticonize({
		//delay: 0,
		animate: true,
		//exclude: 'pre, code, .no-emoticons'
	});
	setTimeout("showSignEditElement()", 1000);
};

//显示签名修改的元素
function showSignEditElement(){
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
	document.getElementById("menu").style.display="block";
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
			url:'user/deletePhoto_execute',  
			type:'post', 
			data:"photo.id="+photoId,
			async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){   
					photoCount = photoCount-1;
					$("#picCountDiv").html(""+photoCount);
					$("#photoOPE"+photoId).html("<div class='operate'>"+"<div class='de' title='delete this Post'>删除</div>"+"<div class='up' title='show my pic in Home page'>上首页</div>"+"</div>");
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
	var professionName=document.getElementById("professionName").value;
	if(professionName=='' || professionName==null || professionName=='null'){
		alert("Please complete your profile.");
		window.location.href="user/getProfiles_execute";
		return;
	}
	
	if(confirm("Are you sure to submit this photo to admin?")) { 
		$.ajax({
			url:'user/iwantTop_execute',  
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

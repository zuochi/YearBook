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
		
//放大图片之后读取
function loadBigPic(picId){
	document.getElementById("bigPic"+picId).src=document.getElementById("basePath").value+document.getElementById("bigPicUrl"+picId).value;
	//document.getElementById("reply"+picId).focus();
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
	$("#socialDiv").css("display","none");
	$("#photosUL").css("display","block");
	$("#photosUL2").css("display","block");
	$("#hasPhotos").css("display","block");
	$("#myPostSelectd").removeClass().addClass("mypostSelect"); 
	$("#socialSelectd").removeClass().addClass("social"); 
};
		
function socialShow(){
	myPostDisplay=0;
	socialDisplay=1;
	$("#socialDiv").css("display","block");
	$("#photosUL").css("display","none");
	$("#photosUL2").css("display","none");
	$("#hasPhotos").css("display","none");
	$("#myPostSelectd").removeClass().addClass("mypost"); 
	$("#socialSelectd").removeClass().addClass("socialSelect"); 
};
		
function getPhotosByPerPage(isNew){
	if("true"==isNew){
		/*$("#photosUL").html("");
		$("#photosUL2").html("");
		$("#hasPhotos").html("");*/
		toPageP=0;
	}
	if(hasPic==1){
	//自动返向下一页
	toPageP+=1;
	$.ajax({
		url:'/YearBook/user/getPhotosByPerPage_execute',  
		type:'post', 
		data:"userId="+document.getElementById("userId").value+"&toPage="+toPageP,
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
							"<a href='javascript:void(0)' onclick='iWantTop("+json[i].id+","+$("#userId").val()+")'><div class='up' title='我要上首页'></div>" +
							"<a href='javascript:void(0)' onclick='deletePhoto("+json[i].id+")'><div class='de' title='删除'></div>"+
						"</div>"
					);
							
					$("#photosUL2").append(
						"<li value='"+json[i].id+"'>"+
						"<div id='comment"+json[i].id+"' class='text2'>"+
						"<div id='friendTips"+json[i].id+"' class='friendTip'></div>"+
						"<textarea rows='3' cols='50'  id='reply"+json[i].id+"' onfocus='friendNameTipsInit("+json[i].id+")' onkeyup='getAtName(this.value.charAt(value.length-1),"+json[i].id+")'></textarea><br>"+
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
							"<figure>"+
								"<div id='commentPic"+json[i].id+"' class='slideshowpic'><a href='"+document.getElementById("basePath").value+json[i].url+"' target='_blank' title='点击在新页面中打开'><img id='bigPic"+json[i].id+"' /></a></div>"+
								"<figcaption><div class='pen'></div><div class='p2' id='commentDesc"+json[i].id+"'>Kale chips lomo biodiesel stumptown Godard Tumblr, mustache sriracha tattooed cray aute slow-carb placeat delectus. Letterpress asymmetrical fanny pack art party est pour-over skateboard anim quis, ullamco craft beer.</div></figcaption>"+	
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

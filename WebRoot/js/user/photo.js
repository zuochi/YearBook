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

//更换相册
function changeAlbum(){
	var newPhotoAlbum = $("#newPhotoAlbum").val();
	if(document.getElementsByName("isNew")[1].checked==true){
		if(newPhotoAlbum==""){
			alert("the name of new photo album couldn't be null.");
			return;
		}
		$.ajax({
			url:'/YearBook/user/photoUpload_execute',
			type:'post', 
	        data:"newPhotoAlbum="+newPhotoAlbum+"&isNew="+(document.getElementsByName("isNew")[0].checked==true?"no":"yes"),
	        async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){
					//创建完成后重新获取信息
					window.location.href="/YearBook/user/getPhotoAlbumsByPerPage_execute?userId="+$("#userId").val();
					alert("create success!");
				}else{
					alert("fail " + newAlbumId);
				}
			}
		});
	}else{
		window.location.href="User_photoUpload.jsp?photoAlbumId="+document.getElementById("photoAlbumId").value+
			"&newPhotoAlbum="+document.getElementById("newPhotoAlbum").value+
			"&isNew=no";
	}
};

//换页
function toPage(toPage){
	window.location.href="/YearBook/user/getPhotoAlbumsByPerPage_execute?userId="+document.getElementById("userId").value+"&toPage=" + toPage;
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

// 更新相册
function updateAlbum(photoAlbumId) {
	var photoAlbumName = $("#photoAlbumName" + photoAlbumId).val();
	if(confirm("Are you sure to update this photo album ?")) { 
		if (photoAlbumName == "") {
			alert("Name couldn't be null.");
			return;
		}
		var photoAlbumPermission = $("#photoAlbumPermission" + photoAlbumId).val();
		var photoAlbumCode = $("#photoAlbumCode" + photoAlbumId).val();
		$.ajax({
			url : '/YearBook/user/updatePhotoAlbum_execute',
			type : 'post',
			data : "photoAlbum.id=" + photoAlbumId + "&photoAlbum.name="
					+ photoAlbumName + "&photoAlbum.permission="
					+ photoAlbumPermission + "&photoAlbum.password="
					+ photoAlbumCode,
			async : false,
			dataType : 'text',
			success : function(msg) {
				if (msg == "success") {
					alert("update " + msg);
					$("#photoAlbum" + photoAlbumId).html(photoAlbumName);
					$("#photoAlbumSelectElement" + photoAlbumId).html(photoAlbumName);
				} else {
					alert("update " + msg);
				}
			}
		});
	}
};

//删除相册
function deletePhotoAlbum(photoAlbumId){
	var photoAlbumName = $("#photoAlbumName"+photoAlbumId).val();
	if(confirm("Are you sure to delete '" + photoAlbumName +"' photo album?")) { 
		$.ajax({
			url:'/YearBook/user/deletePhotoAlbum_execute',  
			type:'post', 
	        data:"photoAlbum.id="+photoAlbumId+"&photoAlbum.name="+photoAlbumName,
	        async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){
					$("#pAlbum"+photoAlbumId).remove();
					$("#photoAlbumSelectElement" + photoAlbumId).remove();
					alert("delete " + msg);
				}else{
					alert("update " + msg);
				}
			}
		});
	}
};

//删除照片
function deletePhoto(photoId){
	if(confirm("Are you sure to delete this photo?")) { 
		$.ajax({
			url:'/YearBook/user/deletePhoto_execute',  
			type:'post', 
	        data:"photo.id="+photoId,
	        async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){
					$("#photo"+photoId).remove();
					alert("delete " + msg);
				}else{
					alert("update " + msg);
				}
			}
		});
	}
};

//展开|关闭子分支
function showtree(str)
{
	var eval1 = "span_" + str + ".innerHTML=span_" + str
			+ ".innerHTML==''?'':'+'";
	var eval2 = str + "_value.style.display=" + str
			+ "_value.style.display=='none'?'':'none'";
	eval(eval1);
	eval(eval2);
}

//局部获取相册--暂时用不上
function getPhotoAlbumsByPerPage(userId,toPage){
	$.ajax({
		url:'/YearBook/user/getPhotoAlbumsByPerPage_execute',  
		type:'post', 
        data:"userId="+userId+"&ajax=ajax&toPage="+toPage,
        async:false,
		dataType:'json', 
		success:function (json) {
			if(json.error==undefined){
				$("#photoAlbumsDIV").html("");
				for(var i=0; i<json.length; i++){
					$("#photoAlbumsDIV").append(
						"<div id='pAlbum"+json[i].id+"' class='ablum'> <a href='javascript:void(0)' onclick='getPhotosByPerPage("+json[i].id+")'>"+
					    "<s:if test='"+json[i].photoUrl==null+"'><img src='images/ablum.png' alt='' width='140' height='140' /></s:if>"+
					    "<s:else><img src='"+json[i].photoUrl+"' alt='' width='140' height='140' /></s:else>"+
					    "<h1 id='photoAlbum"+json[i].id+"'>"+json[i].name+"</h1></a>"+
						"<a href='javascript:void(0)' onclick='deletePhotoAlbum("+json[i].id+")'><div class='delete' title='删除'></div></a>"+
						"<div class=tree_1 onMouseover='line(this)' onMouseOut='delline(this)' onClick='showtree('album"+json[i].id+"')'>"+
						"<a href='javascript:void(0)'><div class='pen_icon' title='修改'></div></a>"+
						"<span id='span_album"+json[i].id+"' style='color:black'></span>"+
						"</div>"+
						"<br></br>"+
						"<div id='album"+json[i].id+"_value' class=tree_2 style='display:none'>"+
						"<div class=tree_add onMouseOver='line(this)' onMouseOut='delline(this)'>"+
						"<h7>name:</h7><input type='text' id='photoAlbumName"+json[i].id+"' name='photoAlbumName' value='"+json[i].name+"' style='width:96px;height:17px; color:black; font-size:11px' />"+
						"<h7>perm :</h7><select id='photoAlbumPermission"+json[i].id+"' name='photoAlbumPermission' style='width:99px;'>"+
						"<option <s:if test='"+json[i].permission+"==0'>selected='selected'</s:if> value='0'>私有</option>"+
						"<option <s:if test='"+json[i].permission+"==1'>selected='selected'</s:if> value='1'>公开</option>"+
						"<option <s:if test='"+json[i].permission+"==2'>selected='selected'</s:if> value='2'>仅好友</option>"+
						"<option <s:if test='"+json[i].permission+"==3'>selected='selected'</s:if> value='3'>密码</option>"+
						"</select>"+
						"<h7>code :</h7><input type='text' id='photoAlbumCode"+json[i].id+"' name='photoAlbumCode' style='width:97px;height:17px; color:black; font-size:11px' value='"+json[i].password+"'/>"+
						"<input style='color:black; font-size:11px;float:right;margin-top:3px;' type='button' value='修改' onclick='updateAlbum("+json[i].id+")'/>"+
						"</div>"+
						"</div>"+
						"</div>"
						);
				}
			}else{
				alert("access fail!");
			}
		}
	});
};

//隐藏与现实按钮
function showUpButton(){ 
	$("#showButton").html("<a href='javascript:void(0)' onclick='changeAlbum()' target='main' class='up'>&nbsp;</a>");
};
function showNewButton(){
	$("#showButton").html("<a href='javascript:void(0)' onclick='changeAlbum()' target='main' class='new'>&nbsp;</a>");
};


//弹窗
function open_secret() {
	document.getElementById('secretbg').style.display = 'block';
	document.getElementById('secret').style.display = 'block';
	showloginbg();
}
function close_secret() {
	document.getElementById('secretbg').style.display = 'none';
	document.getElementById('secret').style.display = 'none';
}
function show_secretbg() {
	var sWidth, sHeight;
	sWidth = screen.width;
	sWidth = document.body.offsetWidth;
	sHeight = document.body.offsetHeight;
	if (sHeight < screen.height) {
		sHeight = screen.height;
	}
	document.getElementById("secretbg").style.width = sWidth + "px";
	document.getElementById("secretbg").style.height = sHeight + "px";
	document.getElementById("secretbg").style.display = "block";
	document.getElementById("secretbg").style.display = "block";
	document.getElementById("secretbg").style.right = document
			.getElementById("secret").offsetLeft
			+ "px";
}

function open_comment() {
	document.getElementById('commentbg').style.display = 'block';
	document.getElementById('comment').style.display = 'block';
	showloginbg();
}
function close_comment() {
	document.getElementById('commentbg').style.display = 'none';
	document.getElementById('comment').style.display = 'none';
}
function show_commentbg() {
	var sWidth, sHeight;
	sWidth = screen.width;
	sWidth = document.body.offsetWidth;
	sHeight = document.body.offsetHeight;
	if (sHeight < screen.height) {
		sHeight = screen.height;
	}
	document.getElementById("commentbg").style.width = sWidth + "px";
	document.getElementById("commentbg").style.height = sHeight + "px";
	document.getElementById("commentbg").style.display = "block";
	document.getElementById("commentbg").style.display = "block";
	document.getElementById("commentbg").style.right = document
			.getElementById("comment").offsetLeft
			+ "px";
}


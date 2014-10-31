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
		}
		
		//局部加载图片
		var toPageP=1;
		var hasPic=1;
		var myPostDisplay=1;
		function getPhotosByPerPage(isNew){
			if("true"==isNew){
				/*$("#photosUL").html("");
				$("#photosUL2").html("");
				$("#hasPhotos").html("");*/
				toPageP=0;
			}
			if(hasPic==1){
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
							$("#hasPhotos").html("<center style='color:#01b8f0;'>oops,there are no more photos,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top.</a></center><br><br><br><br>");
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
								"<textarea rows='3' cols='50' id='reply"+json[i].id+"' onfocus='friendNameTipsInit("+json[i].id+")' onkeyup='getAtName(this.value.charAt(value.length-1),"+json[i].id+")'></textarea><br>"+
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
										"<figcaption><div class='p2' id='commentDesc"+json[i].id+"'>Kale chips lomo biodiesel stumptown Godard Tumblr, mustache sriracha tattooed cray aute slow-carb placeat delectus. Letterpress asymmetrical fanny pack art party est pour-over skateboard anim quis, ullamco craft beer.</div></figcaption>"+	
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
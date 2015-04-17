//点赞图片
function doPraise(elementId,photoId){
	  $.ajax({
		url:'user/doPraise_execute',  
		type:'post', 
		data:"praise.photo.id="+photoId+"&i_want_top.id="+elementId,
		async:false,
		dataType:'text', 
		success:function (msg) {
			if(msg=="success"){
				var className = document.getElementById("isPraiseDIV"+elementId).className;
				var praiseCount = parseInt((document.getElementById("praise_countDIV"+elementId).innerHTML).replace('\(','').replace('\)',''));
				
				if(className == "likes_icon"){
					document.getElementById("isPraiseDIV"+elementId).className = "unlikes_icon";
					document.getElementById("praise_countDIV"+elementId).innerHTML = "";
					document.getElementById("praise_countDIV"+elementId).innerHTML = "(" + (praiseCount+1) + ")";
				}else{
					document.getElementById("isPraiseDIV"+elementId).className = "likes_icon";
					if(praiseCount>=1){
						document.getElementById("praise_countDIV"+elementId).innerHTML = "";
						document.getElementById("praise_countDIV"+elementId).innerHTML = "(" + (praiseCount-1) + ")";
					}
				}
			}else{
				alert(msg);
			}
		}
	});
};

function toPage(toPage){
	//隐藏菜单
	document.getElementById("menu").style.display="none";
	//更改页码
	document.getElementById("toPage").value=toPage;
	//向上滚动
	mScroll("professionsDIV");
	//读取图片
	loadPhotos();
};

//把专业滚动到顶部
function mScroll(id){
	$("html,body").stop(true);
	$("html,body").animate({scrollTop: $("#"+id).offset().top}, 500);
};

//读取首页图片
var isNotice = 0;
function loadPhotos(){
	//每次翻页+1
	if(document.getElementById("toPage").value==0){//等于0说明是第一次加载
		document.getElementById("toPage").value = parseInt(document.getElementById("toPage").value) + 1;
	}else{
		if((parseInt(document.getElementById("toPage").value) + 1)<=document.getElementById("totalPages").value){
			document.getElementById("toPage").value = parseInt(document.getElementById("toPage").value) + 1;
		}else{
			if(isNotice==0){
				isNotice=1;
				alert("oops,there are no more photos.");
			}
			return;
		}
	}
	$.ajax({
		type: "POST",
		url: "user/getIndexPhotos_execute",
	 	data: "i_want_top.user.profession.id="+document.getElementById("choosedProfessionId").value + "&toPage="+document.getElementById("toPage").value,
		dataType: "json",
		success: function(json){
			//document.getElementById('loadingSpace').style.display = 'block';
			if(json.error == undefined && json[0].objs.length>0){
				for(var i=0 ; i<json[0].objs.length ; i++ ){
					//加载页码
					/*if(i==0){
						$("#ctr").append(
							"<ul>"+
								"<li>Page: "+ json[0].pc.currentPage +"/" + json[0].pc.totalPages + "</li>"+
								"<li><a href='javascript:void(0)' onclick='toPage("+(json[0].pc.currentPage)+")'>Refresh</a></li>"+
								(json[0].pc.currentPage>1?"<li><a href='javascript:void(0)' onclick='toPage("+(json[0].pc.currentPage-1)+")'>Previous</a></li>":"<li>Previous</li>")+
								(json[0].pc.currentPage<json[0].pc.totalPages?"<li><a href='javascript:void(0)' onclick='toPage("+(json[0].pc.currentPage+1)+")'>Next</a></li>":"<li>Next</li>")+
								"<li>Lift:"+
									"<select id='lift' onchange='toPage(this.value)'></select>"+
								"</li>"+
							"</ul>"
						);
						//为转跳添加页码
						for(var j=1 ; j<=json[0].pc.totalPages ; j++){
							$("#lift").append("<option "+(json[0].pc.currentPage==j?"selected='selected'":"")+" value='"+j+"'>"+j+"</option>");
						}
					}*/
					//保存总页面
					document.getElementById("totalPages").value=json[0].pc.totalPages;
					//输出图片
					$("#ctr").append(
						"<div class='box photo col3'>"+
							"<a href='javascript:void(0)' onclick='goSocialIndex("+json[0].objs[i].user_id+")'><div class='like_user_name'>"+json[0].objs[i].name+"</div></a>"+
							"<div class='top_time'>"+calculateDT(json[0].objs[i].review_date)+"</div>"+
							"<a href='ShowBigPic.jsp?url="+json[0].objs[i].url+"&name="+json[0].objs[i].name+"' target='_blank' title='Show big size in a new window.'><img src='"+json[0].objs[i].url+"' alt='Like' />"+
							"<a href='javascript:void(0)' onclick='doPraise("+json[0].objs[i].id+","+json[0].objs[i].photo_id+")'><div id='isPraiseDIV"+json[0].objs[i].id+"' class="+(json[0].objs[i].is_praise==0?'likes_icon':'unlikes_icon')+">"+
								"<div class='like_number' id='praise_countDIV"+json[0].objs[i].id+"'>("+json[0].objs[i].praise_count+")</div>"+
							"</div></a>"+
						"</div>"
					);
				}
			}else{
				$("#ctr").append("<div style=' font-size:x-large;text-align:center;color:#8f8f8f;'>oops,there are no more photos.</div>");
			}
			setTimeout("recall()", 0);
		}
	});
};

//重新计算流布局
function recall(){
	$("#ctr").masonry('reload');
};

//滚动条出发
window.addEventListener( 'scroll', function() {
	if(document.body.scrollTop+document.body.clientHeight>=(document.body.scrollHeight)){
		if(document.getElementById("choosedProfessionId").value!=0){
			loadPhotos();
		}
    }
});

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
ul {
	text-align: center;
	list-style-type:none;
	height: 20px;
} /* 即使没有菜单项也能保持顶级菜单栏的高度。 */

ul>li {
	text-align: center;
	display: inline-block;
	width: 100px;
}
</style>
<br>
<div id="pic">
	<div id="ctr">
		<!-- <div class="box photo col3">
			<img src="images/1.jpg" alt="Like" />
		   	<div class="likes_icon"><div class="like_number">(9999+)</div>
		   </div></div>
		<div class="box photo col3">
		<div class="like_user_name">Kass</div><div class="top_time">2015/12/31</div>
			<img src="images/2.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>

		<div class="box photo col3">
			<img src="images/3.jpg" alt="Like" />
		<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/4.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/5.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/6.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/7.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/8.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/9.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/10.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>

		<div class="box photo col3">
			<img src="images/11.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div style="clear:both; height: 40px"></div> -->
	</div>
	<!-- <div id="loadingSpace" style="display:none;height:1000px"></div> -->
</div>
<input id="toPage" type="hidden"/>
<script type="text/javascript" src="js/user/calculateDistanceTime.js"></script>
<script type="text/javascript">
//点赞图片
function doPraise(elementId,photoId){
	  $.ajax({
		url:'user/doPraise_execute',  
		type:'post', 
		data:"praise.photo.id="+photoId,
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
	$("html,body").animate({scrollTop: $("#"+id).offset().top}, 1000);
};

//读取首页图片
function loadPhotos(){
	$.ajax({
		type: "POST",
		url: "user/getIndexPhotos_execute",
	 	data: "i_want_top.user.profession.id="+document.getElementById("choosedProfessionId").value + "&toPage="+document.getElementById("toPage").value,
		dataType: "json",
		success: function(json){
			//document.getElementById('loadingSpace').style.display = 'block';
			if(json.error == undefined && json[0].objs.length>0){
				$("#ctr").html("");
				for(var i=0 ; i<json[0].objs.length ; i++ ){
					//加载页码
					if(i==0){
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
					}
					//输出图片
					$("#ctr").append(
						"<div class='box photo col3'>"+
							"<a href='javascript:void(0)' onclick='goSocialIndex("+json[0].objs[i].user_id+")'><div class='like_user_name'>"+json[0].objs[i].name+"</div></a>"+
							"<div class='top_time'>"+calculateDT(json[0].objs[i].review_date)+"</div>"+
							"<img src='"+json[0].objs[i].url+"' alt='Like' />"+
							"<a href='javascript:void(0)' onclick='doPraise("+json[0].objs[i].id+","+json[0].objs[i].photo_id+")'><div id='isPraiseDIV"+json[0].objs[i].id+"' class="+(json[0].objs[i].is_praise==0?'likes_icon':'unlikes_icon')+">"+
								"<div class='like_number' id='praise_countDIV"+json[0].objs[i].id+"'>("+json[0].objs[i].praise_count+")</div>"+
							"</div></a>"+
						"</div>"
					);
				}
			}else{
				$("#ctr").html("");
				$("#ctr").append("<div style=' font-size:large;text-align:center;color:#8f8f8f;'>oops,there are no more photos.</div>");
			}
			//document.getElementById('loadingSpace').style.display = 'none';
		}
	}); 
	
	var $ctr = $("#ctr");
    $ctr.imagesLoaded( function(){
      $ctr.masonry({
        itemSelector : '.box',
		isFitWidth: true,
		isAnimated: true
      });
    });
};
</script>
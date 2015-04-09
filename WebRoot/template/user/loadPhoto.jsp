<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</div>
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

//读取首页图片
function loadPhotos(){
	$.ajax({
		type: "POST",
		url: "user/getIndexPhotos_execute",
		data: "i_want_top.user.profession.id="+document.getElementById("choosedProfessionId").value,
		dataType: "json",
		success: function(json){
			if(json.error == undefined && json.length>0){
				$("#ctr").html("");
				for(var i=0 ; i<json.length ; i++ ){
					//加载页码
					if(i==0){
						
					}
					$("#ctr").append(
						"<div class='box photo col3'>"+
							"<a href='javascript:void(0)' onclick='goSocialIndex("+json[i].user_id+")'><div class='like_user_name'>"+json[i].name+"</div></a>"+
							"<div class='top_time'>"+calculateDT(json[i].review_date)+"</div>"+
							"<img src='"+json[i].url+"' alt='Like' />"+
							"<a href='javascript:void(0)' onclick='doPraise("+json[i].id+","+json[i].photo_id+")'><div id='isPraiseDIV"+json[i].id+"' class="+(json[i].is_praise==0?'likes_icon':'unlikes_icon')+">"+
								"<div class='like_number' id='praise_countDIV"+json[i].id+"'>("+json[i].praise_count+")</div>"+
							"</div></a>"+
						"</div>"
					);
				}
			}else{
				$("#ctr").html("");
				$("#ctr").append("<div style=' font-size:large;text-align:center;color:#8f8f8f;'>oops,there are no more photos.</div>");
			}
		}
	}); 
	
	var $ctr = $('#ctr');
    $ctr.imagesLoaded( function(){
      $ctr.masonry({
        itemSelector : '.box',
		isFitWidth: true,
		isAnimated: true
      });
    });
};
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="socialDiv">
	<!-- 留言板and可能认识的人 -->
	<div class="friendframe">
		<%-- <div class="bbs">
			<div class="ds">
				<div class="ds-post-main" >
					<div class="ds-avatar">
						<a><img src=""></a>
					</div>
					<div class="ds-comment-body">
						<a title="bbs"  class="user-name">bbs</a>
						<p style="font-size:12px;">"hello,world"。</p>
					</div>
				</div>
				<div class="ds-post-main" >
					<div class="ds-avatar">
						<a><img src=""></a>
					</div>
					<div class="ds-comment-body">
						<a title="bbs"  class="user-name">bbs</a>
						<p style="font-size:12px;">"hello,world"。</p>
					</div>
				</div>
			</div>
		</div> --%>
		<div class="circle">
			<div class="tips2">people you may know</div>
			<div id="nearBy">
				 <%-- <div class="user-inf">
					<div class="usersphoto">    </div>
					<div class="usersname">     Kasstiel</div>
				</div>
				<div class="user-inf">
					<div class="usersphoto">    </div>
					<div class="usersname">     Kasstiel</div>
				</div>
				<div class="user-inf">
					<div class="usersphoto">    </div>
					<div class="usersname">     Kasstiel</div>
				</div>
				<div class="user-inf">
					<div class="usersphoto">    </div>
					<div class="usersname">     Kasstiel</div>
				</div>  --%>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){ 
	loadNearBy();
});
//读取可能认识的人
function loadNearBy(){
	$.ajax({
		type: "POST",
		url: "user/nearBy_execute",
		data: "",
		dataType: "json",
		success: function(json){
			if(json.error == undefined){
				$("#nearBy").html("");
				for(var i=0 ; i<json.length ; i++){
					$("#nearBy").append(
						"<div class='user-inf'>"+
							"<a href='javascript:void(0)' onclick='goSocialIndex("+json[i].id+")'><div class='usersphoto'><img style='border-radius:50%;' height='65' width='65' src='"+json[i].url_m+"'/></div></a>"+
							"<a href='javascript:void(0)' onclick='goSocialIndex("+json[i].id+")'><div class='usersname'>"+json[i].name+"</div></a>"+
						"</div>");
				}
				if(json.length==0){
					$("#nearBy").append("<div align='center'>Sorry,couldn't find any that you may konw.</div>");
				}
			}
		}
	}); 
};
</script>
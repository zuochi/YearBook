<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<div id="pic">
	<div id="ctr">
		
	</div>
</div>
<input id="choosedProfessionId" type="hidden" value="4"/>
<script type="text/javascript">
//读取类型
function loadPhotos(){
	$.ajax({
		type: "POST",
		url: "user/getIndexPhotos_execute",
		data: "i_want_top.user.profession.id="+document.getElementById("choosedProfessionId").value,
		dataType: "json",
		success: function(json){
			if(json.error == undefined){
				for(var i=0 ; i<json.length ; i++){
					$("#ctr").append(
						"<div class='box photo col3'>"+
							"<img src='"+json[i].url+"' alt='Like' />"+
						    "<div class='like_user'>"+
							    "<a href='javascript:void(0)' onclick='goSocialIndex("+json[i].user_id+")'><div class='like_user_name'>"+json[i].name+"</div></a>"+
							    "<div class='like_user_head'><img src='"+json[i].url_m+"'/></div>"+
						    "</div>"+
							"<div class='likes_icon'>"+
								"<div class='like_number'>("+json[i].praise_count+")</div>"+
							"</div>"+
						"</div>"
					);
				}
			}
		}
	}); 
};
</script>
//显示、隐藏回复框 
function showReplyFrame(elementId){
	eval(eval2=elementId+".style.display="+elementId+".style.display=='none'?'':'none'");
	replyAutoComplete
	$("#"+elementId+"Text").focus();
};

//var toPageM = 0;

//显示评论
function showPhotoReplys(toPageM,countM){
	//if($("#commentBody"+photoBid).text()==''){
		//toPageM+=1;
		$.ajax({
			url:'/YearBook/user/getMessage_getPhotoReplysByPerPage',  
			type:'post', 
	        data:"toPage="+toPageM+"&count="+countM,
	        async:false,
	        dataType:'json', 
			success:function (json) {
				if(json.error==undefined){
					for(var i=0; i<json.length; i++){
						$("#photoReplyDiv").append(
							"<div id='replyBody"+json[i].id+"' class='ds-post-main'>"+
							"<div class='ds-avatar'>"+
								"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_id+")' target='_blank'><img src='"+json[i].url_m+"'></a>"+
							"</div>"+
							"<div class='ds-comment-body'>"+
								"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_id+")' target='_blank' class='user-name'>"+json[i].name+"</a>"+
								"<p id='commentEmo"+json[i].id+"'>"+json[i].context+"</p>"+
								"<div class='time'><p>"+calculateDT(json[i].signup_date)+"</p></div>"+
								"<a href='javascript:void(0)' onclick='deleteMessage("+json[i].id+")'><div class='shanchu' title='delete this reply'></div></a>"+
								"<a href='javascript:void(0)' onclick='showReplyFrame(\"replyFrame"+json[i].id+"\")'><div class='comments_icon' title='reply "+json[i].name+"' style='float:right'></div></a>"+
								"<div id='replyFrame"+json[i].id+"' style='display:none'>"+
										"<input id='replyFrame"+json[i].id+"Text' type='text' style='width:360px'/>"+
										"<input type='button' value='reply'/>"+
								"</div>"+
						"</div>"
						);
						$('#commentEmo'+json[i].id).emoticonize();
					}
				}
			}
		});
	//}
	//$("#loadingComment"+photoBid).hideLoading();//隐藏读取状态
};

//删除消息
function deleteMessage(replyId){
	$("#replyBody"+replyId).remove();
};

//先加载一次 
$(document).ready(function(){
	showPhotoReplys($("#pageM").val(),$("#countM").val());
});
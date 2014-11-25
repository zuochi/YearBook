//显示、隐藏回复框 
function showReplyFrame(elementId,nickName){
	eval(eval2=elementId+".style.display="+elementId+".style.display=='none'?'':'none'");
	var replyId = elementId.substring(1,elementId.length);
	$("#"+replyId).val("reply@" + nickName+" ");
	focusLast(document.getElementById((replyId)));
};

//var toPageM = 0;

//显示评论
function showPhotoReplys(toPageM,countM){
	//if($("#commentBody"+photoBid).text()==''){
		//toPageM+=1;
		$("#photoReplyDiv").html("");
		$.ajax({
			url:'/YearBook/user/getMessage_getPhotoReplysByPerPage',  
			type:'post', 
	        data:"toPage="+toPageM+"&count="+countM+"&type=photo",
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
								"<a href='javascript:void(0)' onclick='showReplyFrame(\"Freply"+json[i].id+"\",\""+json[i].name+"\")'><div class='comments_icon' title='reply "+json[i].name+"' style='float:right'></div></a>"+
								"<div id='friendTips"+json[i].id+"' class='friendTipMessage'/>"+
								"<div id='Freply"+json[i].id+"' style='display:none'>"+
										"<input id='reply"+json[i].id+"' type='text' style='width:360px' onkeydown='enterDeal("+json[i].id+")' onkeyup='getAtName(this.value,"+json[i].id+")'/>"+
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
	if(confirm("Are you sure to delete this reply?")) {
		$.ajax({
			url:'/YearBook/user/deleteReply_execute',
			type:'post',
			data:"reply.id="+replyId,
			async:false,
			dataType:'text',
			success:function (msg) {
				if(msg=="success"){
					$("#replyBody"+replyId).remove();
				}else{
					alert("delete " + msg);
				}
			}
		});
		$("#replyBody"+replyId).remove();
	}
};

//翻页
function messageTurnPage(toPageM,countM,type){
	window.location.href="/YearBook/user/getMessage_execute?toPage="+toPageM+"&count="+countM+"&type="+type;
};

//selectType
function changeType(type){
	window.location.href="/YearBook/user/getMessage_execute??type="+type;
};

//先加载一次 
$(document).ready(function(){
	showPhotoReplys($("#pageM").val(),$("#countM").val());
});
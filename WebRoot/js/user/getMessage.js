//显示、隐藏回复框 
function showReplyFrame(elementId,nickName){
	eval(eval2=elementId+".style.display="+elementId+".style.display=='none'?'':'none'");
	var replyId = elementId.substring(1,elementId.length);
	$("#"+replyId).val("reply@" + nickName+" ");
	focusLast(document.getElementById((replyId)));
};

//回复
function comment(userBid,replyId,photoBid,nickName,photoOwnerId){
	var reply = $("#reply"+replyId).val();
	if(reply.length<1 || reply.length>80){
		alert("reply at least 1 in length,and max at 80.");
		return;
	}
	/** 屏蔽所有按钮 **/
	$("input[type='button']").each(function() {
		this.disabled = true;
	});
	$.ajax({
		url:'user/doReply_execute',
		type:'post',
        data:"reply.userByUserBid.id="+userBid+"&reply.photo.id="+photoBid+"&reply.context="+reply+"&photo.user.id="+photoOwnerId,
        async:false,
		success:function (context) {
			if(context.error == undefined){
				/** 恢复所有按钮 **/
				$("input[type='button']").each(function() {
					this.disabled = false;
				});
				$("#reply"+replyId).val("reply@"+nickName+" ");
				$("#replySuccessTip").html("reply success!").hide(3000); 
				focusLast(document.getElementById(("reply"+replyId)));
				//统计剩余字数
				//wordsNumber(photoBid);
				//获取时间 
				//var date=new Date();
				//即时刷新评论
				//reloadReply(photoBid);
				/*$("#commentBody"+photoBid).prepend(
					"<div class='ds-post-main'>"+
						"<div class='ds-avatar'>"+
							"<a title='"+$("#nickName").val()+"' href='javascript:goSocialIndex("+$("#userId").val()+")' target='_blank'><img src='"+$("#urlM").val()+"'></a>"+
						"</div>"+
						"<div class='ds-comment-body'>"+
							"<a title='"+$("#nickName").val()+"' href='javascript:goSocialIndex("+$("#userId").val()+")' target='_blank' class='user-name'>"+$("#nickName").val()+"</a>"+
							"<div class='message' >"+context+"</div>"+
							"<div align='right' class='p1'>"+date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"</div>"+
						"</div>"+
					"</div>"
				);*/
			}
		}
	});
};

//var toPageM = 0;

//显示图片评论
function showPhotoReplys(toPageM,countM){
	//if($("#commentBody"+photoBid).text()==''){
		//toPageM+=1;
		$("#photoReplyDiv").html("");
		$.ajax({
			url:'user/getMessage_getPhotoReplysByPerPage',  
			type:'post', 
	        data:"toPage="+toPageM+"&count="+countM+"&type=photo",
	        async:false,
	        dataType:'json', 
			success:function (json) {
				if(json.error==undefined){
					for(var i=0; i<json.length; i++){
						$("#photoReplyDiv").append(
							//json[i].status+
							"<div id='replyBody"+json[i].id+"' class='ds-post-main'>"+
							//json[i].photo_bid+
							"<div class='ds-avatar'>"+
								"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_id+")' target='_blank'><img src='"+json[i].url_m+"'></a>"+
							"</div>"+
							"<div class='ds-comment-body'>"+
								"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_id+")' target='_blank' class='user-name'>"+json[i].name+"</a>"+
								"<div class='message' id='commentEmo"+json[i].id+"'>"+json[i].context+"</div>"+
								"<div class='time'><p>"+calculateDT(json[i].signup_date)+"</p></div>"+
								"<a href='javascript:void(0)' onclick='deleteMessage(\""+json[i].id+"\")'><div class='operate' title='delete this reply'>delete</div></a>"+
								"<a href='javascript:void(0)' onclick='showReplyFrame(\"Freply"+json[i].id+"\",\""+json[i].name+"\")'><div class='operate' title='reply "+json[i].name+"' style='float:right'>reply</div></a>"+
								"<div id='friendTips"+json[i].id+"' class='friendTipMessage'/>"+
								"<div id='Freply"+json[i].id+"' style='display:none'>"+
										"<input id='reply"+json[i].id+"' type='text' style='width:360px' onkeydown='enterDeal(\""+json[i].id+"\")' onkeyup='getAtName(this.value,\""+json[i].id+"\")'/>"+
										"<input type='button' value='reply' onclick='comment("+json[i].user_id+",\""+json[i].id+"\","+json[i].photo_bid+",\""+json[i].name+"\","+json[i].photoOwnerId+")'/>"+
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

//显示Mention me的消息
function showMentionMe(toPageM,countM){
	//if($("#commentBody"+photoBid).text()==''){
		//toPageM+=1;
		$("#photoReplyDiv").html("");
		$.ajax({
			url:'user/getMessage_getMentionMeByPerPage',  
			type:'post', 
	        data:"toPage="+toPageM+"&count="+countM+"&type=photo",
	        async:false,
	        dataType:'json', 
			success:function (json) {
				if(json.error==undefined){
					for(var i=0; i<json.length; i++){
						$("#photoReplyDiv").append(
							//json[i].status+
							"<div id='replyBody"+json[i].id+"' class='ds-post-main'>"+
							//json[i].photo_bid+
							"<div class='ds-avatar'>"+
								"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_id+")' target='_blank'><img src='"+json[i].url_m+"'></a>"+
							"</div>"+
							"<div class='ds-comment-body'>"+
								"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_id+")' target='_blank' class='user-name'>"+json[i].name+"</a>"+
								"<div class='message' id='commentEmo"+json[i].id+"'>"+json[i].context+"</div>"+
								"<div class='time'><p>"+calculateDT(json[i].signup_date)+"</p></div>"+
								"<a href='javascript:void(0)' onclick='deleteAtNotify("+json[i].id+")'><div class='operate' title='delete this reply'>delete</div></a>"+
								"<a href='javascript:void(0)' onclick='showReplyFrame(\"Freply"+json[i].id+"\",\""+json[i].name+"\")'><div class='operate' title='reply "+json[i].name+"' style='float:right'>reply</div></a>"+
								"<div id='friendTips"+json[i].id+"' class='friendTipMessage'/>"+
								"<div id='Freply"+json[i].id+"' style='display:none'>"+
										"<input id='reply"+json[i].id+"' type='text' style='width:360px' onkeydown='enterDeal("+json[i].id+")' onkeyup='getAtName(this.value,"+json[i].id+")'/>"+
										"<input type='button' value='reply' onclick='comment("+json[i].user_id+","+json[i].id+","+json[i].photo_bid+",\""+json[i].name+"\","+json[i].photoOwnerId+")'/>"+
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
			url:'user/deleteReply_execute',
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

//删除消息
function deleteAtNotify(atNotifyId){
	if(confirm("Are you sure to delete this message?")) {
		$.ajax({
			url:'user/deleteAtNotify_execute',
			type:'post',
			data:"atNotify.id="+atNotifyId,
			async:false,
			dataType:'text',
			success:function (msg) {
				if(msg=="success"){
					$("#replyBody"+atNotifyId).remove();
				}else{
					alert("delete " + msg);
				}
			}
		});
		$("#replyBody"+atNotifyId).remove();
	}
};

//翻页
function messageTurnPage(toPageM,countM,type){
	window.location.href="user/getMessage_execute?toPage="+toPageM+"&count="+countM+"&type="+type;
};

//选择模式
function changeType(type){
	window.location.href="user/getMessage_execute?type="+type;
};

//先加载一次 
$(document).ready(function(){
	if($("#typeM").val()=="photo"){
		showPhotoReplys($("#pageM").val(),$("#countM").val());
	}else if($("#typeM").val()=="mentionMe"){
		showMentionMe($("#pageM").val(),$("#countM").val());
	}
});

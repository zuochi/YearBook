//删除评论
function deleteReply(replyId){
	if(confirm("Are you sure to delete this reply?")) { 
		  $.ajax({
			url:'/YearBook/admin/deleteReplyA_execute',  
			type:'post', 
			data:"reply.id="+replyId,
			async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){   
					window.location.reload(true);
				 }else{
					alert("delete " + msg);
				}
			}
		});
	}
};

//恢复评论
function recoveryReply(replyId){
	if(confirm("Are you sure to recovery this reply?")) { 
		  $.ajax({
			url:'/YearBook/admin/recoveryReplyA_execute',  
			type:'post', 
			data:"reply.id="+replyId,
			async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){   
					window.location.reload(true);
				 }else{
					alert("delete " + msg);
				}
			}
		});
	}
};

//举报评论
function missReply(replyId){
	if(confirm("Are you sure to miss this reply?")) { 
		  $.ajax({
			url:'/YearBook/admin/missAccusationReply_execute',  
			type:'post', 
			data:"reply.id="+replyId,
			async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){   
					window.location.reload(true);
				 }else{
					alert("delete " + msg);
				}
			}
		});
	}
};
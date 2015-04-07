//审核
function reviewFeedBack(feedBackId,result){
	if(confirm(result==1?"确定标记为“已处理”？":"确定标记为“未处理”？")) { 
		  $.ajax({
			url:'admin/reviewFeedBacks_execute',  
			type:'post', 
			data:{"feedBack.id":feedBackId,"feedBack.status":result},
			async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){   
					window.location.reload(true);
				 }
			}
		});
	}
};

//删除
function deleteFeedBack(feedbackId){
	if(confirm("Are you sure to delete this feedback?")) { 
		  $.ajax({
			url:'admin/deleteFeedBackA_execute',  
			type:'post', 
			data:"feedBack.id="+feedbackId,
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

//恢复
function recoveryFeedBack(feedbackId){
	if(confirm("Are you sure to recovery this feedback?")) { 
		  $.ajax({
			url:'admin/recoveryFeedBackA_execute',  
			type:'post', 
			data:"feedBack.id="+feedbackId,
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

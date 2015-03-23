//审核
function reviewIWantTop(iWantTopId,result){
	if(confirm(result==1?"图片将会通过审核？":"图片不通过审核？")) { 
		  $.ajax({
			url:'/YearBook/admin/reviewIWantTops_execute',  
			type:'post', 
			data:{"i_want_top.id":iWantTopId,"i_want_top.status":result},
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

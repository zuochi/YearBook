//审核
function reviewIWantTop(iWantTopId,result){
	if(confirm(result==1?"图片将会通过审核？":"图片不通过审核？")) { 
		  $.ajax({
			url:'admin/reviewIWantTops_execute',  
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

//审核
function overhead(iWantTopId){
	if(confirm("该图片在首页的位置将被顶置，确定要继续吗？")) { 
		  $.ajax({
			url:'admin/reviewIWantTops_overhead',  
			type:'post', 
			data:{"i_want_top.id":iWantTopId},
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

//审核
function setIWantTopStatusCover(iWantTopId,result){
	if(confirm(result==1?"图片将会上架到首页？":"图片将在首页下架？")) { 
		  $.ajax({
			url:'admin/reviewIWantTops_updateStatusCover',  
			type:'post', 
			data:{"i_want_top.id":iWantTopId,"i_want_top.statusCover":result},
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

//预览
function preview(url){
	window.open("admin/index_preview.jsp?url="+url);
};
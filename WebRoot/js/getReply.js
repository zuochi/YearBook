//评论上翻页
function showCommentPreviousPage(photoBid){
	//上翻过头复原
	if(parseInt($("#commentPage"+photoBid).val())>1){
		$("#commentPage"+photoBid).val(parseInt($("#commentPage"+photoBid).val())-parseInt(1));
		$("#commentBody"+photoBid).html("");
		$("#commentCurrentPage"+photoBid).html($("#commentPage"+photoBid).val());
		//showComments(photoBid,$("#commentPage"+photoBid).val());
		delayLoadCommTime(photoBid,$("#commentPage"+photoBid).val(),500);
	}
};

//评论下翻页
function showCommentNextPage(photoBid){
	//下翻过头复原
	if( parseInt($("#commentPage"+photoBid).val()) < parseInt($("#commentTotalPage"+photoBid).html())){
		$("#commentPage"+photoBid).val(parseInt($("#commentPage"+photoBid).val())+parseInt(1));
		$("#commentBody"+photoBid).html("");
		$("#commentCurrentPage"+photoBid).html($("#commentPage"+photoBid).val());
		//showComments(photoBid,$("#commentPage"+photoBid).val());
		delayLoadCommTime(photoBid,$("#commentPage"+photoBid).val(),500);
	}
};

var pageSizeN = 0;

function setPageSizeN(){
	if(pageSizeN == 0){
		pageSizeN = Math.round(window.screen.availHeight/145.6);
	}
};

//显示评论
function showComments(photoBid,toPageC){
	if($("#commentBody"+photoBid).text()==''){
		//获取PageSizeN
		setPageSizeN();
		//获取总条数
		getReplyCount(photoBid);
		$.ajax({
			url:'/YearBook/user/getReply_execute',  
			type:'post', 
	        data:"photo.id="+photoBid+"&toPage="+toPageC+"&replyCountN="+$("#commentCount"+photoBid).val()+"&pageSizeN="+pageSizeN+"&user.id="+$("#userId").val(),
	        async:false,
	        dataType:'json', 
			success:function (json) {
				if(json.error==undefined){
					for(var i=0; i<json.length; i++){
						$("#commentBody"+photoBid).append(
							"<div id='replyBody"+json[i].id+"' class='ds-post-main'>"+
								"<div class='ds-avatar'>"+
									"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_id+")' target='_blank'><img src='"+json[i].url_m+"'></a>"+
								"</div>"+
								"<div  class='ds-comment-body'>"+
									"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_id+")' target='_blank' class='user-name'>"+json[i].name+"</a>"+
									"<div class='message' ><span id='commentEmo"+json[i].id+"'>"+json[i].context+"</span></div>"+
									($("#isMine").val()==0?($("#currentUserId").val()==json[i].user_id?("<a href='javascript:void(0)' onclick='deleteReply("+json[i].id+")' title='delete'><div class='shanchu'></div></a>"):""):("<a href='javascript:void(0)' onclick='deleteReply("+json[i].id+")' title='delete'><div class='shanchu'></div></a>"))+
									($("#isMine").val()==0?($("#currentUserId").val()!=json[i].user_id?("<a href='javascript:void(0)' onclick='replyAutoComplete(\""+json[i].name+"\",+"+photoBid+")' title='reply'><div class='pinglun'></div></a>"):""):($("#userId").val()!=json[i].user_id?("<a href='javascript:void(0)' onclick='replyAutoComplete(\""+json[i].name+"\",+"+photoBid+")' title='reply'><div class='pinglun'></div></a>"):""))+
									"<div class='jvbao'>"+"举报"+"</div>"+
									"<div align='left' class='p1'>"+calculateDT(json[i].signup_date)+"</div>"+
								"</div>"+
							"</div>"	
						);
						$('#commentEmo'+json[i].id).emoticonize();
					}
				}
			}
		});
	}
	$("#loadingComment").hideLoading();//隐藏读取状态
};

//评论自动回复填写
function replyAutoComplete(nickName,photoBid){
	$("#reply"+photoBid).val("reply@" + nickName+" ");
	//$("#reply"+photoBid).focus();
	focusLast(document.getElementById(("reply"+photoBid)));
};

//删除评论
function deleteReply(replyId){
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
	}
};

//获取评论的条数
function getReplyCount(photoBid){
	$.ajax({
		url:'/YearBook/user/getReply_getReplyCount',  
		type:'post', 
        data:"photo.id="+photoBid,
        async:false,
        dataType:'text', 
		success:function (json) {
			if(json!="fail"){
				//如果没有评论则不显示翻页或者评论数少于5
				$("#loadingComment"+photoBid).show();
				if(parseInt(json)<=pageSizeN){
					$("#commentPageTurningButton"+photoBid).hide();
				}else{
					$("#commentPageTurningButton"+photoBid).show();
				}
				$("#commentCount"+photoBid).val(json);
				//计算总页数
				if(parseInt(json)==0){
					$("#commentRefreshButton"+photoBid).hide();
					$("#pageShow"+photoBid).hide();
					$("#commentBody"+photoBid).append("<div align='left' style='margin:15px 0 5px 30px'>no reply yet,click <a href='javascript:void(0)'style='color:#DE4C1C ' onclick='reloadReply("+photoBid+")'>Refresh</a> to display the latest status<div>");
				}else{
					$("#pageShow"+photoBid).show();
					$("#commentRefreshButton"+photoBid).show();
					var totalPages = 0 ;
					if((parseInt(json)%pageSizeN) == 0){
						totalPages = parseInt(json) / pageSizeN;
					}else{
						totalPages = parseInt(json) / pageSizeN + 1;
					}
					$("#commentTotalPage"+photoBid).html(parseInt(totalPages));
				}
			}
		}
	});
};

//重新读取评论
function reloadReply(photoBid){
	$("#commentRefreshButton"+photoBid).hide();
	$("#commentPage"+photoBid).val(parseInt(1));
	$("#commentBody"+photoBid).html("");
	$("#commentCurrentPage"+photoBid).html(1);
	//showComments(photoBid,1);
	delayLoadCommTime(photoBid,1,1200);
};

//短暂停顿之后获取评论
function delayLoadCommTime(photoBid,currentPage,time){
	if($("#commentBody"+photoBid).text()==''){
		$("#loadingComment").showLoading();//显示读取状态
		setTimeout('showComments('+ photoBid +','+ currentPage +')', time);
	}
}

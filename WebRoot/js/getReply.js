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
							"<div  class='ds-post-main'>"+
								"<div class='ds-avatar'>"+
									"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_bid+")' target='_blank'><img src='"+json[i].url_m+"'></a>"+
								"</div>"+
								"<div  class='ds-comment-body'>"+
									"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_bid+")' target='_blank' class='user-name'>"+json[i].name+"</a>"+
									"<div id='text' class='message' >"+json[i].context+"</div>"+
									"<div align='right' class='p1'>"+calculateDT(json[i].signup_date)+"</div>"+
									/*"<div align='right' class='p1'>20"+(json[i].signup_date.year-100)+"-"+
										(json[i].signup_date.month+1)+"-"+
										json[i].signup_date.date+" "+
										json[i].signup_date.hours+":"+json[i].signup_date.minutes+":"+json[i].signup_date.seconds+"</div>"+*/
								"</div>"+
							"</div>"	
						);
					}
				}
			}
		});
	}
	$("#loadingComment"+photoBid).hideLoading();//隐藏读取状态
	$(document).ready(function()
			{
						$('#text').emoticonize({
							//delay: 800,
							//animate: false,
							//exclude: 'pre, code, .no-emoticons'
						});
						$('#toggle-headline').toggle(
							function(){
								$('#large').unemoticonize({
									//delay: 800,
									//animate: false
								})
							}, 
							function(){
								$('#large').emoticonize({
									//delay: 800,
									//animate: false
								})
							}
						);
					})
	
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
	$("#loadingComment"+photoBid).showLoading();//显示读取状态
	setTimeout('showComments('+ photoBid +','+ currentPage +')', time);
}

//改写图片描述
function updateDes(photoId){
	$("#desPenButton"+photoId).show();
	$("#desTextArea"+photoId).hide();
	$("#updateDesButton"+photoId).hide();
	
	//若是重复 则不提交
	if($("#desContext"+photoId).html() != $("#desTextArea"+photoId).val()){
		//更新图片描述
		$.ajax({
			url:'/YearBook/user/updatePhoto_updateDescription',  
			type:'post', 
	        data:"photo.id="+photoId+"&photo.name="+$("#desTextArea"+photoId).val(),
	        async:false,
			dataType:'text', 
			success:function (result) {
				if(result=="success"){
					$("#desContext"+photoId).html($("#desTextArea"+photoId).val());
				}
			}
		});
	}
	$("#desContext"+photoId).show();
	$("#desContext"+photoId).css("width","350px");
};
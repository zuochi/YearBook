var toPageM = 0;

//显示评论
function showPhotoReplys(){
	//if($("#commentBody"+photoBid).text()==''){
		toPageM+=1;
		$.ajax({
			url:'/YearBook/user/getMessage_execute',  
			type:'post', 
	        data:"toPage="+toPageM,
	        async:false,
	        dataType:'json', 
			success:function (json) {
				if(json.error==undefined){
					for(var i=0; i<json.length; i++){
						$("#commentBody"+photoBid).append(
								
							
							"<div id='replyBody"+json[i].id+"' class='ds-post-main'>"+
								"<div class='ds-avatar'>"+
									"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_bid+")' target='_blank'><img src='"+json[i].url_m+"'></a>"+
								"</div>"+
								"<div  class='ds-comment-body'>"+
									"<a title='"+json[i].name+"' href='javascript:goSocialIndex("+json[i].user_bid+")' target='_blank' class='user-name'>"+json[i].name+"</a>"+
									"<div class='message' ><span id='commentEmo"+json[i].id+"'>"+json[i].context+"</span></div>"+
									($("#isMine").val()==0?($("#currentUserId").val()==json[i].user_id?("<a href='javascript:void(0)' onclick='deleteReply("+json[i].id+")' title='delete'><div class='shanchu'></div></a>"):""):("<a href='javascript:void(0)' onclick='deleteReply("+json[i].id+")' title='delete'><div class='shanchu'></div></a>"))+
									($("#isMine").val()==0?($("#currentUserId").val()!=json[i].user_id?("<a href='javascript:void(0)' onclick='replyAutoComplete(\""+json[i].name+"\",+"+photoBid+")' title='reply'><div class='pinglun'></div></a>"):""):($("#userId").val()!=json[i].user_id?("<a href='javascript:void(0)' onclick='replyAutoComplete(\""+json[i].name+"\",+"+photoBid+")' title='reply'><div class='pinglun'></div></a>"):""))+
									"<div align='left' class='p1'>"+calculateDT(json[i].signup_date)+"</div>"+
								"</div>"+
							"</div>"
						);
						
						$("#photoReplyDiv").append(
							"<div class="ds-post-main">"+
							<div class="ds-avatar">"+
								<a title="用户名" href="" target="_blank"><img alt="设计达人" src="images/alex.jpg"></a>"+
							</div>"+
							<div class="ds-comment-body">"+
								<a title="用户名" href="" target="_blank" class="user-name">用户名</a>"+
								<p class="text">" :-) :) :o)是不是上了5天班了？"、"以为是周末了？"、"今天才周三，哈哈！"。</p>"+
								<div class="time"><p>时间</p></div>"+
								<div class=tree_1 onMouseover="line(this)" onMouseOut="delline(this)" onClick="showtree('reply')"><a href="javascript:void(0)">	<div class="comments_icon"></div>"+
								</a><span id=span_reply style="color:gray"></span></div>"+
								<div id=reply_value class=tree_2 style="display:none">"+
								<div class=tree_add onMouseOver="line(this)" onMouseOut="delline(this)">"+
									<form action="">"+
										<input type="text" name="reply"/>"+
										<input type="button" value="回复"/>"+
									</form> "+
								</div>"+
								</div>"+
							</div>"+
						</div>"+
						);
						$('#commentEmo'+json[i].id).emoticonize();
					}
				}
			}
		});
	//}
	//$("#loadingComment"+photoBid).hideLoading();//隐藏读取状态
};
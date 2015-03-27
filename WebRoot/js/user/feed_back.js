// 发送反馈
function addFeedBack() {
	if (confirm("Are you sure to send this feed back to Admin?")) {
		var context = document.getElementById("fe_se").value;
		
		if($.trim(context)==""){
			alert("内容不能为空");			
			return ;
		}
		
		$.ajax({
			url : '/YearBook/user/addFeedBack_execute',
			type : 'post',
			data : "feedBack.context=" + context,
			async : false,
			dataType : 'text',
			success : function(msg) {
				if (msg == "success") {
					document.getElementById("fe_se").value="";
					alert("反馈成功！请耐心等待");
				} else {
					alert("失败，请先登录！");
				}
			}
		});
	}
};
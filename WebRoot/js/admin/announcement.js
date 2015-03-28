//停用 
function deleteAnnouncement(announcementId) {
	if (confirm("Are you sure to block up this announcement?")) {
		$.ajax({
			url : '/YearBook/admin/deleteAnnouncementA_execute',
			type : 'post',
			data : "announcement.id=" + announcementId,
			aPronc : false,
			dataType : 'text',
			success : function(msg) {
				if (msg == "success") {
					window.location.reload(true);
				} else {
					alert("fail " + msg);
				}
			}
		});
	}
};

// 恢复学年
function recoveryAnnouncement(announcementId) {
	if (confirm("Are you sure to recovery this announcement?")) {
		$.ajax({
			url : '/YearBook/admin/recoveryAnnouncementA_execute',
			type : 'post',
			data : "announcement.id=" + announcementId,
			async : false,
			dataType : 'text',
			success : function(msg) {
				if (msg == "success") {
					window.location.reload(true);
				} else {
					alert("delete " + msg);
				}
			}
		});
	}
};

// 添加专业
function addAnnouncement() {
	if (confirm("Are you sure to add this announcement?")) {
		var title = document.getElementById("add_announcement_title").value;
		var context = document.getElementById("add_announcement_context").value;
		var top = document.getElementById("add_announcement_top").value;
		var is_delete = document.getElementById("add_announcement_isDelete").value;
		
		if($.trim(title)==""){
			alert("标题不能为空");			
			return ;
		}
		
		if($.trim(context)==""){
			alert("内容不能为空");			
			return ;
		}
		
		$.ajax({
			url : '/YearBook/admin/addAnnouncementA_execute',
			type : 'post',
			data : "announcement.title=" + title + "&announcement.isDelete=" + is_delete + "&announcement.context=" + context + "&announcement.top=" + top,
			async : false,
			dataType : 'text',
			success : function(msg) {
				if (msg == "success") {
					window.location.reload(true);
				} else {
					alert("失败, 数据库出错！");
				}
			}
		});
	}
};

//根据id读取公告
function loadAnnouncement(announcementId) {
	$.ajax({
		url : '/YearBook/admin/getAnnouncementDetail_execute',
		type : 'post',
		data : "announcement.id="+announcementId,
		async : false,
		dataType : 'json',
		success : function(json) {
			if (json.error == undefined) {
				//window.location.reload(true);
				document.getElementById("announcement_trid_"+json.id).innerHTML="";
				document.getElementById("announcement_trid_"+json.id).innerHTML=
					"<td>"+ json.id +"</td>"+
					"<td>Editing</td>"+
					"<td>Editing</td>"+
					"<td><input id='add_announcement_title_"+ json.id +"' type='text' value='"+ json.title +"'></td>"+
					"<td><input id='add_announcement_context_"+ json.id +"' type='text' value='"+ json.context +"'></td>"+
					"<td>Editing</td>"+
					"<td>"+
						"<select id='announcement_td_top_"+ json.id +"'>"+
							"<option "+(json.top==0?"selected='selected'":"")  + " value='0'>不置顶</option>"+
							"<option "+(json.top==1?"selected='selected'":"")  + " value='1'>-置顶-</option>"+
						"</select>"+
					"</td>"+
					"<td>"+
						"<select id='announcement_td_isDelete_"+ json.id +"'>"+
							"<option "+(json.isDelete==0?"selected='selected'":"")  + " value='0'>启动</option>"+
							"<option "+(json.isDelete==1?"selected='selected'":"")  + " value='1'>停用</option>"+
						"</select>"+
					"</td>"+
					"<td>Editing</td>"+
					"<td><a href='javascript:void(0)' onclick='saveAnnouncement("+ json.id +")'>保存</a></td>";
			} else {
				alert("失败，已经存在该专业 或 数据库出错！");
			}
		}
	});
};

//更新专业
function saveAnnouncement(announcementId) {
	if (confirm("Are you sure to update this announcement?")) {
		var title = document.getElementById("add_announcement_title_"+announcementId).value;
		var context = document.getElementById("add_announcement_context_"+announcementId).value;
		var top = document.getElementById("announcement_td_top_"+announcementId).value;
		var is_delete = document.getElementById("announcement_td_isDelete_"+announcementId).value;
		
		$.ajax({
			url : '/YearBook/admin/editAnnouncement_execute',
			type : 'post',
			data : "announcement.id=" + announcementId + "&announcement.title=" + title + "&announcement.isDelete=" + is_delete + "&announcement.context=" + context + "&announcement.top=" + top,
			async : false,
			dataType : 'text',
			success : function(msg) {
				if (msg == "success") {
					window.location.reload(true);
				} else {
					alert("失败，数据库出错！");
				}
			}
		});
	}
}

//显示添加公告
function showAdd() {
	 document.getElementById("add_announcement_div").style.display="block";
	 document.getElementById("add_announcement_button1").style.display="none";
	 document.getElementById("add_announcement_button2").style.display="block";
};

//隐藏添加公告
function hideAdd() {
	 document.getElementById("add_announcement_div").style.display="none";
	 document.getElementById("add_announcement_button2").style.display="none";
	 document.getElementById("add_announcement_button1").style.display="block";
};
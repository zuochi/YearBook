//停用 
function deleteProfession(proId) {
	if (confirm("Are you sure to block up this profession?")) {
		$.ajax({
			url : '/YearBook/admin/deleteProfessionA_execute',
			type : 'post',
			data : "profession.id=" + proId,
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
function recoveryProfession(proId) {
	if (confirm("Are you sure to recovery this profession?")) {
		$.ajax({
			url : '/YearBook/admin/recoveryProfessionA_execute',
			type : 'post',
			data : "profession.id=" + proId,
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
function addPro() {
	if (confirm("Are you sure to add this profession?")) {
		var is_delete = document.getElementById("add_profession_isDelete").value;
		var name = document.getElementById("add_profession_name").value;
		var context = document.getElementById("add_profession_context").value;
		
		if($.trim(name)==""){
			alert("专业名称不能为空");			
			return ;
		}
		
		$.ajax({
			url : '/YearBook/admin/addProfessionA_execute',
			type : 'post',
			data : "profession.name=" + name + "&profession.isDelete=" + is_delete + "&profession.context=" + context,
			async : false,
			dataType : 'text',
			success : function(msg) {
				if (msg == "success") {
					window.location.reload(true);
				} else {
					alert("失败，已经存在该专业 或 数据库出错！");
				}
			}
		});
	}
};

//根据id读取专业
function loadPro(proId) {
	$.ajax({
		url : '/YearBook/admin/getProfessionDetail_execute',
		type : 'post',
		data : "profession.id="+proId,
		async : false,
		dataType : 'json',
		success : function(json) {
			if (json.error == undefined) {
				//window.location.reload(true);
				document.getElementById("profession_trid_"+json.id).innerHTML="";
				document.getElementById("profession_trid_"+json.id).innerHTML=
					"<td>"+ json.id +"</td>"+
					"<td><input id='profession_td_name_"+ json.id +"' type='text' value='"+ json.name +"'></td>"+
					"<td><input id='profession_td_context_"+ json.id +"' type='text' value='"+ json.context +"'></td>"+
					"<td>" +
						(json.isDelete==0?"启用":"停用")+
					"</td>"+
					"<td>"+
						"<select id='profession_td_isDelete_"+ json.id +"'>"+
							"<option "+(json.isDelete==0?"selected='selected'":"")  + " value='0'>启动</option>"+
							"<option "+(json.isDelete==1?"selected='selected'":"")  + " value='1'>停用</option>"+
						"</select>"+
					"</td>"+
					"<td><a href='javascript:void(0)' onclick='savePro("+ json.id +")'>保存</a></td>";
			} else {
				alert("失败，已经存在该专业 或 数据库出错！");
			}
		}
	});
};

//更新专业
function savePro(proId) {
	if (confirm("Are you sure to update this profession?")) {
		var is_delete = document.getElementById("profession_td_isDelete_"+proId).value;
		var name = document.getElementById("profession_td_name_"+proId).value;
		var context = document.getElementById("profession_td_context_"+proId).value;
		
		$.ajax({
			url : '/YearBook/admin/editProfession_execute',
			type : 'post',
			data : "&profession.id=" + proId + "&profession.name=" + name + "&profession.isDelete=" + is_delete + "&profession.context=" + context,
			async : false,
			dataType : 'text',
			success : function(msg) {
				if (msg == "success") {
					window.location.reload(true);
				} else {
					alert("失败，已经存在该专业 或 数据库出错！");
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
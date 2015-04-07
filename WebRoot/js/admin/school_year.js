//停用 
function deleteSY(syId) {
	if (confirm("Are you sure to block up this school year?")) {
		$.ajax({
			url : 'admin/deleteSchoolYearA_execute',
			type : 'post',
			data : "schoolYear.id=" + syId,
			async : false,
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
function recoverySY(syId) {
	if (confirm("Are you sure to recovery this school year?")) {
		$.ajax({
			url : 'admin/recoverySchoolYearA_execute',
			type : 'post',
			data : "schoolYear.id=" + syId,
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

// 添加学年
function addSY() {
	if (confirm("Are you sure to add this school year?")) {
		var year = document.getElementById("add_year_year").value;
		var is_delete = document.getElementById("add_year_isDelete").value;
		
		if($.trim(year)==""){
			alert("不能为空");			
			return ;
		}
		
		$.ajax({
			url : 'admin/addSchoolYearA_execute',
			type : 'post',
			data : "schoolYear.year=" + year + "&schoolYear.isDelete=" + is_delete,
			async : false,
			dataType : 'text',
			success : function(msg) {
				if (msg == "success") {
					window.location.reload(true);
				} else {
					alert("失败，已经存在该学年 或 数据库出错！");
				}
			}
		});
	}
};
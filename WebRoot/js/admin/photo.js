//删除照片
function deletePhoto(photoId){
	if(confirm("Are you sure to delete this photo?")) { 
		  $.ajax({
			url:'/YearBook/admin/deletePhotoA_execute',  
			type:'post', 
			data:"photo.id="+photoId,
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

//忽略图片
function missPhoto(photoId){
	if(confirm("Are you sure to miss this photo?")) { 
		  $.ajax({
			url:'/YearBook/admin/missAccusationPhoto_execute',  
			type:'post', 
			data:"photo.id="+photoId,
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
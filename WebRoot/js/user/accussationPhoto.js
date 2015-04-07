//举报图片
function accusationPhoto(photoId){
	if(confirm("Are you sure to report this photo to adminstartor?")) { 
		  $.ajax({
			url:'user/accusationPhoto_execute',  
			type:'post', 
			data:"photo.id="+photoId,
			async:false,
			dataType:'text', 
			success:function (msg) {
				if(msg=="success"){   
					alert("Please wait for the adminstartor's reply,thank for your report.");
				}else if(msg=="repeat"){
					alert("This photo had been report to adminstartor.");
				}else{
					alert("Report fail,please contact the adminstartor to fix this problem QAQ,thank you!");
				}
			}
		});
	}
};
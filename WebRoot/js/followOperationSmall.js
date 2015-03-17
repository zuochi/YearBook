function followSmall(elementId,socialUserId){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=follow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#"+elementId).html("<a href='javascript:void(0)' onclick='cancleFollowSmall(\""+elementId+"\","+socialUserId+")'><div class='yiguanzhuSmall'></div></a>");
        		 $("#hasBeenModify").val(1);
        	 }else{
        		 operationRepeatWarn();
        	 }
         }
	 });
};

function cancleFollowSmall(elementId,socialUserId){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=cancelFollow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#"+elementId).html("<a href='javascript:void(0)' onclick='followSmall(\""+elementId+"\","+socialUserId+")'><div class='guanzhuSmall'></div></a>");
        		 $("#hasBeenModify").val(1);
        	 }else{
        		 operationRepeatWarn();
        	 }
         }
	 });
};

function followFriendSmall(elementId,socialUserId,fname){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=follow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#"+elementId).html("<a href='javascript:void(0)' onclick='cancleFollowFriendSmall(\""+elementId+"\","+socialUserId+",\""+fname+"\")'><div class='friendSmall'></div></a>");
        		 $("#followFriendName"+elementId).html("<name title='click to show detail.'>"+fname+"</name>");
        		 $("#hasBeenModify").val(1);
        	 }else{
        		 operationRepeatWarn();
        	 }
         }
	 });
};

function cancleFollowFriendSmall(elementId,socialUserId,fname){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=cancelFollow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#"+elementId).html("<a href='javascript:void(0)' onclick='followFriendSmall(\""+elementId+"\","+socialUserId+",\""+fname+"\")'><div class='guanzhuFriendSmall'></div></a>");
        		 $("#followFriendName"+elementId).html("<name title='click to show detail.'>"+fname+"</name>");
        		 $("#hasBeenModify").val(1);
        	 }else{
        		 operationRepeatWarn();
        	 }
         }
	 });
};

function operationRepeatWarn(){
	alert("This operation has been submit,we will refresh the web page for your right now.");
	 window.location.reload(true);
};
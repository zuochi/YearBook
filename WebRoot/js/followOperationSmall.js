function followSmall(socialUserId){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=follow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followAttrDIV"+socialUserId).html("<span id='followAttrDIV"+socialUserId+"'><a href='javascript:void(0)' onclick='cancleFollowSmall("+socialUserId+")'><div class='yiguanzhuSmall'></div></a></span>");
        	 }else{
        		 alert("fail");
        	 }
         }
	 });
};

function cancleFollowSmall(socialUserId){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=cancelFollow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followAttrDIV"+socialUserId).html("<span id='followAttrDIV"+socialUserId+"'><a href='javascript:void(0)' onclick='followSmall("+socialUserId+")'><div class='guanzhuSmall'></div></a></span>");
        	 }else{
        		alert("fail");
        	 }
         }
	 });
};

function followFriendSmall(socialUserId,fname){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=follow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followAttrDIV"+socialUserId).html("<span id='followAttrDIV"+socialUserId+"'><a href='javascript:void(0)' onclick='cancleFollowFriendSmall("+socialUserId+",\""+fname+"\")'><div class='friendSmall'></div></a></span>");
        		 $("#followFriendName"+socialUserId).html("<span style='color:#ff406d' title='we followed each other.'>♥ </span><name title='click to show detail.'>"+fname+"</name>");
        	 }else{
        		 alert("fail");
        	 }
         }
	 });
};

function cancleFollowFriendSmall(socialUserId,fname){
	$.ajax({
         url:'/YearBook/user/follow_execute',  
         type:'post', 
         data:"userId="+socialUserId+"&type=cancelFollow",
         async:false,
         success:function (msg) {
        	 if(msg=="success"){
        		 $("#followAttrDIV"+socialUserId).html("<span id='followAttrDIV"+socialUserId+"'><a href='javascript:void(0)' onclick='followFriendSmall("+socialUserId+",\""+fname+"\")'><div class='guanzhuFriendSmall'></div></a></span>");
        		 $("#followFriendName"+socialUserId).html("<name title='click to show detail.'>"+fname+"</name>");
        	 }else{
        		alert("fail");
        	 }
         }
	 });
};
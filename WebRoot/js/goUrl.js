function goUrl(type){
	if(type==1){
		window.location.href="User_headPhotoUpload.jsp";
	}else if(type==2){
		window.location.href="user/getProfiles_execute";
	}else if(type==3){
		window.location.href="index.jsp";
	}
	/* if(type==4){
		//window.parent.main.location.href="user/getSocial_getFriendByPerPage?user.id=<s:property value='#session.user.id'/>";
		window.parent.main.location.href="friend.jsp";
	} */
	else if(type==4){
		window.location.href="friend.jsp?type=following";
	}else if(type==5){
		window.location.href="user/getMyPost_execute";
	}else if(type==6){
		window.location.href="bbs.jsp";
	}else if(type==7){
		window.location.href="user/getMessage_execute?type=photo";
	}else if(type==8){
		window.location.href="user/logout_execute";
	}else if(type==9){
		window.location.href="user/autoLogin_execute";
	}else if(type==10){
		window.location.href="index.jsp?type=signup";
	}else if(type==11){
		window.location.href="friend.jsp?type=followers";
	}else if(type==12){
		window.location.href="feedback.jsp";
	}
};

//post方式打开新窗口
function openPostWindow(url,args1,args2,name){
	var tempForm = document.createElement("form");
	tempForm.id="tempForm";
	tempForm.method="post";
	tempForm.action=url;
	tempForm.target=name;
	tempForm.style.display="none";
	
    var hideinput=document.createElement("input");
    hideinput.type="submit";  
    hideinput.name=args1; 
    hideinput.value=args2;
    
    tempForm.appendChild(hideinput); 
    document.body.appendChild(tempForm);
    hideinput.click();
    document.body.removeChild(tempForm);
}

//去某用户的主页
function goSocialIndex(userId){
	//window.open("user/getSocial?user.id="+userId,'_blank');
	openPostWindow("user/getSocial","user.id",userId,"_blank");
};

//去某用户的主页
function goSocialFollowing(userId){
	//window.open("user/getSocial?user.id="+userId,'_blank');
	openPostWindow("user/getSocial","user.id",userId,"_blank");
	showFollowing();
};
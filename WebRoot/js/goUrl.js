function goUrl(type){
	if(type==1){
		window.parent.main.location.href="User_headPhotoUpload.jsp";
	}else if(type==2){
		window.parent.main.location.href="/YearBook/user/getProfiles_execute";
	}else if(type==3){
		window.parent.main.location.href="mainPage.jsp";
	}
	/* if(type==4){
		//window.parent.main.location.href="/YearBook/user/getSocial_getFriendByPerPage?user.id=<s:property value='#session.user.id'/>";
		window.parent.main.location.href="friend.jsp";
	} */
	else if(type==4){
		window.parent.main.location.href="friend.jsp?type=following";
	}else if(type==5){
		window.parent.main.location.href="/YearBook/user/getMyPost_execute";
	}else if(type==6){
		window.parent.main.location.href="bbs.jsp";
	}else if(type==7){
		window.parent.main.location.href="/YearBook/user/getMessage_execute";
	}else if(type==8){
		window.parent.location.href="/YearBook/user/logout_execute";
	}else if(type==9){
		window.parent.main.location.href="/YearBook/user/autoLogin_execute";
	}else if(type==10){
		window.parent.main.location.href="mainPage.jsp?type=signup";
	}else if(type==11){
		window.parent.main.location.href="friend.jsp?type=followers";
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
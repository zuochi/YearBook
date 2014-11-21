var display = 0;
//following
var followingIsNew=true;
var hasFollowing=1;
var toPageFollowing=0;
function getFollowingByPerPage(){
	//自动返向下一页
	toPageFollowing+=1;
	if(hasFollowing==1){
		$.ajax({
			url:'/YearBook/user/getSocial_getFollowingByPerPage',  
			type:'post', 
			data:"user.id="+$("#userId").val()+"&toPage="+toPageFollowing,
			async:false,
			dataType:"json",
			success:function (json) {
				if(json.error==undefined){
					if(json.length==undefined){
						hasFollowing=0;
						$("#hasFollowing").html("<br><br><br><br><div><center class='noMoreInfo'>oops:(,there are no more following,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top </a>OR <a href='javascript:history.go(-1);'>click to Go Back.</a></center><br><br><br><br>");
					}
					for(var i=0; i<json.length; i++){
						$("#followingContext").append(
							"<div class='one'>"+
								"<div class='pic'>"+
									"<img src='"+(json[i].lurl!=null&&json[i].lurl!=''?json[i].lurl:"images/bg.png")+"' class='pic-image' alt='Pic'/>"+
									"<span class='pic-caption left-to-right'>"+      
									"<p id='followingSignContext"+json[i].userId+"'+>"+(json[i].sign!=null&&json[i].sign!=''?json[i].sign:"no sign yet.")+"</p>"+
									"</span>"+
								"</div>"+
								"<a href='javascript:void(0)' onclick='goSocialIndex("+json[i].userId+")' target='main'>"+
								"<div class='name'>"+(json[i].isFriend==true?"<isFriend style='color:#ff406d' title='we followed each other.'>♥ </isFriend><name title='click to show detail.'>":"<name title='click to show detail.'>")+json[i].fname+"</name></div></a>"+
							"</div>"
						);
						$('#followingSignContext'+json[i].userId).emoticonize();
					}
				}
			}
		});
	}
};

//followers
var followersIsNew=true;
var hasFollowers=1;
var toPageFollowers=0;
function getFollowersByPerPage(){
	//自动返向下一页
	toPageFollowers+=1;
	if(hasFollowers==1){
		$.ajax({
			url:'/YearBook/user/getSocial_getFollowersByPerPage',  
			type:'post', 
			data:"user.id="+$("#userId").val()+"&toPage="+toPageFollowers,
			async:false,
			dataType:"json",
			success:function (json) {
				if(json.error==undefined){
					if(json.length==undefined){
						hasFollowers=0;
						$("#hasFollowers").html("<br><br><br><br><center class='noMoreInfo'>oops:(,there are no more followers,<a class='solltop' href='javascript:void(0)' onclick='scrollToTop()'>scroll to top </a>OR <a href='javascript:history.go(-1);'>click to Go Back.</a></center><br><br><br><br>");
					}
					for(var i=0; i<json.length; i++){
						$("#followersContext").append(
							"<div class='one'>"+
								"<div class='pic'>"+
									"<img src='"+(json[i].lurl!=null&&json[i].lurl!=''?json[i].lurl:"images/bg.png")+"' class='pic-image' alt='Pic'/>"+
									"<span class='pic-caption left-to-right'>"+      
									"<p id='followersSignContext"+json[i].userId+"'>"+(json[i].sign!=null&&json[i].sign!=''?json[i].sign:"no sign yet.")+"</p>"+
									"</span>"+
								"</div>"+
								"<a href='javascript:void(0)' onclick='goSocialIndex("+json[i].userId+")' target='main'>"+
								"<div class='name'>"+(json[i].isFriend==true?"<isFriend style='color:#ff406d' title='we followed each other.'>♥ </isFriend><name title='click to show detail.'>":"<name title='click to show detail.'>")+json[i].fname+"</name></div></a>"+
							"</div>"
						);
						$('#followersSignContext'+json[i].userId).emoticonize();
					}
				}
			}
		});
	}
};

//先加载一次 
$(document).ready(function(){
	if($("#type").val()=="following"){
		display=0;
		followingIsNew=false;
		showFollowing();
		getFollowingByPerPage();
		$("#followersTitleA").attr("onclick","changeType(1)");
		$("#followingTitleA").removeClass().addClass("titleSelect"); 
		$("#followersTitleA").removeClass().addClass("title"); 
	}else if($("#type").val()=="followers"){
		display=1;
		followersIsNew=false;
		showFollowers();
		getFollowersByPerPage();
		$("#followingTitleA").attr("onclick","changeType(0)");
		$("#followingTitleA").removeClass().addClass("title"); 
		$("#followersTitleA").removeClass().addClass("titleSelect"); 
	}
	window.addEventListener( 'scroll', function() {
		if(document.body.scrollTop+document.body.clientHeight>=(document.body.scrollHeight)){
			if(display==0){
				getFollowingByPerPage();
			}else{
				getFollowersByPerPage();
			}
	    }
	});
});

//showFollowing
function showFollowing(){
	$("#followingContext").show();
	$("#hasFollowing").show();
	$("#followersContext").hide();
	$("#hasFollowers").hide();
};

//showFollowers
function showFollowers(){
	$("#followingContext").hide();
	$("#hasFollowing").hide();
	$("#followersContext").show();
	$("#hasFollowers").show();
};

//设换
function changeType(type){
	if(type==0){
		display=0;
		//若是没有加载则进行第一次加载
		if(followingIsNew==true){
			getFollowingByPerPage();
			followingIsNew=false;
		}
		showFollowing();
		$("#followingTitleA").attr("onclick","");
		$("#followersTitleA").attr("onclick","changeType(1)");
		$("#followingTitleA").removeClass().addClass("titleSelect"); 
		$("#followersTitleA").removeClass().addClass("title"); 
	}else{
		display=1;
		//若是没有加载则进行第一次加载
		if(followersIsNew==true){
			getFollowersByPerPage();
			followersIsNew=false;
		}
		showFollowers();
		$("#followingTitleA").attr("onclick","changeType(0)");
		$("#followersTitleA").attr("onclick","");
		$("#followingTitleA").removeClass().addClass("title"); 
		$("#followersTitleA").removeClass().addClass("titleSelect"); 
	}
};

//返回顶部
function scrollToTop(){
	document.body.scrollTop=0;
};

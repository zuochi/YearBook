var httpRequest = null;
function createXMLHttpRequest() {
	try {
		//创建针对火狐，opera 8.0+,Safari等浏览器的对象
		httpRequest = new XMLHttpRequest();
	} catch (e) {
		//如果使用的是IE浏览器，将会出现异常
		try {
			httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			httpRequest = new ActiveObject("Microsoft.XMLHTTP");
		}

	}
}

createXMLHttpRequest();

var canUpdate = 1;
//判断是否重名
function isDuplicationOfName(value) {
	if( $("#nameOld").val() != value){
		 $.ajax({
	         url:'/YearBook/user/isDuplicationOfName_execute',  
	         type:'post', 
	         data:"name="+value,
	         async:false,
	         success:function (msg) {
	        	 if(msg=="used"){
	        		 $("#warn").html("nickName had been used.");
	        		 canUpdate=0;
	        	 }else{
	        		 $("#warn").html("");
	        		 canUpdate=1;
	        	 }
	         }
		 });
	}else{
		canUpdate = 1;
	}
}

function updateCheck(){
	var name = document.getElementById("user.name").value;
	if(name==""){
    	$("#warn").html("nickName couldn't be null");
		return;
	}else if (name.length < 3 || name.length > 10) {
		$("#warn").html("nickName at least 4-10 in length.");
		return;
	} else{
		$("#warn").html("");
	}
	var passwordOld = document.getElementById("passwordOld").value;
	var passwordOrigin = document.getElementById("user.passwordOld").value;
	var password1 = document.getElementById("user.password").value;
	var password2 = document.getElementById("passwordConfirm").value;
	if(passwordOld!="" || password1!="" || password2!=""){
		if(passwordOld!=passwordOrigin){
			$("#warn").html("Old password incorrent.");
			return;
		}
		else if(password1=="" || password2==""){
			$("#warn").html("passwords couldn't be null");
			return;
		}
		else if(password1.length < 6 || password2.length < 6){
			$("#warn").html("passwords at least 6 in length.");
			return;
		}else if(password1 != password2){
			$("#warn").html("passwords not the same.");
			return;
		}else{
	   	 	$("#warn").html("");
		}
	}
	
	var qq = document.getElementById("user.qq").value;
	if(qq!=""){
		if (!(/^\d*$/.test(qq))) {
			//alert("你输入的手机的格式不对");
			$("#warn").html("QQ number should be digital.");
			return;
		}else if(qq.length < 6 || qq.length > 16){
			$("#warn").html("QQ number is 6-16 in length.");
			return;
		}else{
			$("#warn").html("");
		}
	}
	
	var weChat = document.getElementById("user.weChat").value;
	if(weChat!=""){
		if(weChat.length > 18){
				$("#warn").html("too long for weChat Id.");
				return;
			}else{
				$("#warn").html("");
			}
	}
	var email = document.getElementById("user.email").value;
	if(email==""){
		$("#warn").html("email couldn't be null");
		return;
	}else if (!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
			.test(email))) {
		//alert("电子邮箱不能为空 或者 格式不对！");
		$("#warn").html("wrong email address.");
		return;
	}else{
		$("#warn").html("");
	}
	
	var schoolYear = document.getElementById("select3").value;
	if(schoolYear==""){
		$("#warn").html("please select your school year.");
		return;
	}
	
	var profession = document.getElementById("select").value;
	if(profession==""){
		$("#warn").html("please select your profession.");
		return;
	}
	
	var gender = document.getElementById("select2").value;
	if(gender==""){
		$("#warn").html("please select your gender.");
		return;
	}
	
	if(canUpdate == 1){
		 $.ajax({
	         url:'/YearBook/user/updateUser_execute',  
	         data:$('#focus').serialize(),
	         async:false,
	         dataType:'text', 
	         success:function (msg) {
	        	if(msg=="success"){
	        		$("#warn").html("revised successfully.");
	        		window.parent.left.location.reload();
	        	}else{
	        		$("#warn").html("revised fali");
	        	}
	         }
		 });
	}else{
		 $("#warn").html("nickName had been used.");
		 canUpdate=0;
	}
};

//不用移去别的js，在这里就可以了
$(document).ready(function() {
	$("#focus .input_txt").each(function() {
		var thisVal = $(this).val();
		//判断文本框的值是否为空，有值的情况就隐藏提示语，没有值就显示
		if (thisVal != "") {
			$(this).siblings("span").hide();
		} else {
			$(this).siblings("span").show();
		}
		//聚焦型输入框验证
		$(this).focus(function() {
			$(this).siblings("span").hide();
		}).blur(function() {
			var val = $(this).val();
			if (val != "") {
				$(this).siblings("span").hide();
			} else {
				$(this).siblings("span").show();
			}
		});
	})
})

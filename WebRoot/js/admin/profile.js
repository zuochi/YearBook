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
	         url:'user/isDuplicationOfName_execute',  
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
	if($.trim(name)==""){
    	$("#warn").html("nickName couldn't be null.");
    	document.getElementById("user.name").value="";
    	document.getElementById("user.name").focus();
		return;
	}else if(/\s/.test(name)){
		$("#warn").html("nickName can not have spaces.");
		document.getElementById("user.name").focus();
        return;
    }else if(/@/.test(name)){
		$("#warn").html("nickName can not have '@'.");
		document.getElementById("user.name").focus();
        return;
    }else if (name.length < 3 || name.length > 10) {
		$("#warn").html("nickName is 4-10 in length.");
		document.getElementById("user.name").focus();
		return;
	} else{
		$("#warn").html("");
	}
	
	var qq = document.getElementById("user.qq").value;
	if($.trim(qq)!=""){
		if (!(/^\d*$/.test(qq))) {
			//alert("你输入的手机的格式不对");
			$("#warn").html("QQ number should be digital.");
			document.getElementById("user.qq").focus();
			return;
		}else if(qq.length < 6 || qq.length > 16){
			$("#warn").html("QQ number is 6-16 in length.");
			document.getElementById("user.qq").focus();
			return;
		}else{
			$("#warn").html("");
		}
	}
	
	var weChat = document.getElementById("user.weChat").value;
	if($.trim(weChat)!=""){
		if(weChat.length > 18){
			$("#warn").html("too long for weChat Id.");
			document.getElementById("user.weChat").focus();
			return;
		}else{
			$("#warn").html("");
		}
	}
	var email = document.getElementById("user.email").value;
	if($.trim(email)==""){
		$("#warn").html("email couldn't be null");
		document.getElementById("user.email").value="";
		document.getElementById("user.email").focus();
		return;
	}else if (!(/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
			.test(email))) {
		//alert("电子邮箱不能为空 或者 格式不对！");
		$("#warn").html("wrong email address.");
		document.getElementById("user.email").focus();
		return;
	}else{
		$("#warn").html("");
	}
	
	var schoolYear = document.getElementById("select3").value;
	if($.trim(schoolYear)==""){
		$("#warn").html("please select user's school year.");
		return;
	}
	
	var profession = document.getElementById("select").value;
	if($.trim(profession)==""){
		$("#warn").html("please select user's profession.");
		return;
	}
	
	var gender = document.getElementById("select2").value;
	if($.trim(gender)==""){
		$("#warn").html("please select user's gender.");
		return;
	}
	
	var status = document.getElementById("selectStatus").value;
	if($.trim(status)==""){
		$("#warn").html("please select user's status.");
		return;
	}
	
	if(canUpdate == 1){
		$.ajax({
	         url:'admin/editUser_execute',  
	         data:$('#focus').serialize(),
	         async:false,
	         dataType:'text', 
	         success:function (msg) {
	        	if(msg=="success"){
	        		$("#warn").html("revised successfully.");
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

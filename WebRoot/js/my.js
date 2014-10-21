﻿/*
 * jQuery Easing v1.3 - http://gsgd.co.uk/sandbox/jquery/easing/
 *
 * Uses the built in easing capabilities added In jQuery 1.1
 * to offer multiple easing options
 *
 * TERMS OF USE - jQuery Easing
 * 
 * Open source under the BSD License. 
 * 
 * Copyright 漏 2008 George McGinley Smith
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of 
 * conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * Neither the name of the author nor the names of contributors may be used to endorse 
 * or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *  COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 *  GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED 
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE. 
 *
 */

// t: current time, b: begInnIng value, c: change In value, d: duration
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
};

createXMLHttpRequest();

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
	});
});

var canReg=1;

//判断是否重名
function isDuplicationOfName(value) {
	 $.ajax({
         url:'/YearBook/user/isDuplicationOfUserName_execute',  
         type:'post', 
         data:"userName="+value,
         async:false,
         success:function (msg) {
        	 if(msg=="used"){
        		 $("#Signwarn").html("ID had been used.");
        		 canReg=0;
        	 }else{
        		 $("#Signwarn").html("&nbsp;");
        		 canReg=1;
        	 }
         }
	 });
};

function registerCheck() {
	var userName = document.getElementById("user.userName").value;
	if(userName==""){
    	$("#Signwarn").html("ID couldn't be null");
		return;
	}else if (userName.length < 6 || userName.length > 28) {
		$("#Signwarn").html("ID at least 6-28 in length.");
		return;
	} else{
		$("#Signwarn").html("&nbsp;");
	}
	
	var password1 = document.getElementById("user.password").value;
	var password2 = document.getElementById("passwordConfirm").value;
	if(password1=="" || password2==""){
		$("#Signwarn").html("passwords couldn't be null");
		return;
	}else if(password1.length < 6 || password2.length < 6){
		$("#Signwarn").html("pws at least 6 in length.");
		return;
	}else if(password1 != password2){
		$("#Signwarn").html("passwords not the same.");
		return;
	}else{
   	 	$("#Signwarn").html("&nbsp;");
	}
	
	if(canReg==1){
		 $.ajax({
	         url:'/YearBook/user/register_execute',  
	         type:'post', 
	         data:$('#regForm').serialize(),
	         async:false,
	         dataType:'text',  
	         success:function (msg) {
	        	if(msg=="success"){
	        		$("#Signwarn").html("signup success,now loging.");
	        		window.parent.left.location.reload();
	        		close_register();
	        	}else{
	        		$("#Signwarn").html(msg);
	        	}
	         }
		 });
	}else{
		$("#Signwarn").html("ID had been used.");
	}
};

function loginCheck() {
	var userName = document.getElementById("userName").value;
	if(userName==""){
    	$("#Loginwarn").html("ID couldn't be null");
		return;
	}else if (userName.length < 6 || userName.length > 28) {
		$("#Loginwarn").html("ID at least 6-28 in length.");
		return;
	} else{
		$("#Loginwarn").html("&nbsp;");
	}
	
	var password = document.getElementById("ps").value;
	if(password==""){
		$("#Loginwarn").html("password couldn't be null");
		return;
	}else if(password.length < 6){
		$("#Loginwarn").html("pw at least 6 in length.");
		return;
	}else{
   	 	$("#Loginwarn").html("&nbsp;");
	}
	 $.ajax({
         url:'/YearBook/user/login_execute',  
         type:'post', 
         data:$('#logForm').serialize(),
         async:false,
         dataType:'text',  
         success:function (msg) {
        	if(msg=="success"){
        		$("#Loginwarn").html("login success.");
        		window.parent.left.location.reload();
        		close_login();
        	}else{
        		$("#Loginwarn").html("ID or Password incorrect.");
        	}
         }
	 });
};

function open_login() {
	document.getElementById('loginbg').style.display = 'block';
	document.getElementById('login').style.display = 'block';
	showloginbg();
};
function close_login() {
	document.getElementById('loginbg').style.display = 'none';
	document.getElementById('login').style.display = 'none';
};
function show_loginbg() {
	var sWidth, sHeight;
	sWidth = screen.width;
	sWidth = document.body.offsetWidth;
	sHeight = document.body.offsetHeight;
	if (sHeight < screen.height) {
		sHeight = screen.height;
	}
	document.getElementById("loginbg").style.width = sWidth + "px";
	document.getElementById("loginbg").style.height = sHeight + "px";
	document.getElementById("loginbg").style.display = "block";
	document.getElementById("loginbg").style.display = "block";
	document.getElementById("loginbg").style.right = document
			.getElementById("login").offsetLeft
			+ "px";
};

function open_register() {
	document.getElementById('registerbg').style.display = 'block';
	document.getElementById('register').style.display = 'block';
	showregisterbg();
};
function close_register() {
	document.getElementById('registerbg').style.display = 'none';
	document.getElementById('register').style.display = 'none';
};
function show_registerbg() {
	var sWidth, sHeight;
	sWidth = screen.width;
	sWidth = document.body.offsetWidth;
	sHeight = document.body.offsetHeight;
	if (sHeight < screen.height) {
		sHeight = screen.height;
	}
	document.getElementById("registerbg").style.width = sWidth + "px";
	document.getElementById("registerbg").style.height = sHeight + "px";
	document.getElementById("registerbg").style.display = "block";
	document.getElementById("registerbg").style.display = "block";
	document.getElementById("registerbg").style.right = document
			.getElementById("register").offsetLeft
			+ "px";
};
function log_in() {
	alert();
	// 验证
	// 转向...
	// myform.action=""
	// myform.submit()
	close_register();
};

// 图片滚动
jQuery(document).ready(function() {
	jQuery(document).ready(function() {
		jQuery(".box_skitter_large").skitter({
			animation : "random",
			interval : 3000,
			numbers : false,
			numbers_align : "right",
			hideTools : true,
			controls : false,
			focus : false,
			focus_position : true,
			width_label : '340px',
			enable_navigation_keys : true,
			progressbar : false
		});
	});
});

//流布局
$(function(){
    var $ctr = $('#ctr');
    $ctr.imagesLoaded( function(){
      $ctr.masonry({
        itemSelector : '.box',
		isFitWidth: true,
		isAnimated: true
      });
    });
  });






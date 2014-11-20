		//当用户输入@的时候 寻找用户好友列表里面的相关人员
		var searchFriendNameStatus = 0;
		var atFriendName;
		var friendNameLength = 0;
		var selectIndex = 0;
		var selectLength = 0;
		var atIndex = 0;
		function getAtName(context,photoId){
			var cursorPos = getCursorPos("reply"+photoId);
			var text = context.charAt(cursorPos-1);
			var afterText = context.charAt(cursorPos);
			//统计剩余字数
			wordsNumber(photoId);
			//判断当前输入的字符是否为@，且判断@后面是否结尾或者空格。
			if(text=="@" && (afterText==" " || afterText=="")){
				searchFriendNameStatus = 1;
				atFriendName = "";
				//记录@的位置
				atIndex = cursorPos;
				$("#friendTips"+photoId).html("<center>Who do you want to at?</center>");
				$("#friendTips"+photoId).css("display","block");
			}
			if(searchFriendNameStatus==1){
				if(window.event.keyCode==32){
					searchFriendNameStatus=0;
					$("#friendTips"+photoId).html("");
					$("#friendTips"+photoId).css("display","none");
					return;
				}else if(text!="@"){
					//上选择
					if(window.event.keyCode==38){
						//保持不变
						setCursorPos("reply"+photoId, atIndex+atFriendName.length);
						$("#friendTips"+photoId).css("display","block");
						if(selectIndex>=1){
							$("#ft"+photoId+selectIndex).css("color","white");
							selectIndex -= 1;
							$("#ft"+photoId+selectIndex).css("color","yellow");
						}else{
							$("#ft"+photoId+selectIndex).css("color","white");
							selectIndex = (selectLength-1);
							$("#ft"+photoId+selectIndex).css("color","yellow");
						}
						return;
					}
					//下选择
					if(window.event.keyCode==40){
						//保持不变
						setCursorPos("reply"+photoId, atIndex+atFriendName.length);
						$("#friendTips"+photoId).css("display","block");
						if(selectIndex<(selectLength-1)){
							$("#ft"+photoId+selectIndex).css("color","white");
							selectIndex += 1;
							$("#ft"+photoId+selectIndex).css("color","yellow");
						}else{
							$("#ft"+photoId+selectIndex).css("color","white");
							selectIndex = 0;
							$("#ft"+photoId+selectIndex).css("color","yellow");
						}
						return;
					}
					//退格
					if(window.event.keyCode==8){
						if(atFriendName.length>0){
							atFriendName = atFriendName.substring(0,atFriendName.length-1);
						}else{
							searchFriendNameStatus=0;
							$("#friendTips"+photoId).html("");
							$("#friendTips"+photoId).css("display","none");
							return;
						}
					}else{
						//atFriendName += text;
						atFriendName = $.trim($("#reply"+photoId).val().substring(atIndex,getCursorPos("reply"+photoId)));
					}
					$.ajax({
						url:'/YearBook/user/getSearchInfomation_execute',  
						type:'post', 
				        data:"type=searchFriendName&friendList.userByFriendId.name="+atFriendName,
				        async:false,
						dataType:'json', 
						success:function (json) {
							if(json.error==undefined && json.length>0){
								$("#friendTips"+photoId).html("<center>Please choose your follow</center>");
								$("#friendTips"+photoId).css("display","block");
								//重置选择INDEX
								selectIndex = 0;
								selectLength = json.length;
								for(var i=0; i<json.length; i++){
									$("#friendTips"+photoId).append("<a onmouseover='mouserOver("+photoId+","+i+")' onmouseout='mouserOut("+photoId+","+i+")' id='ft"+photoId+i+"' href='javascript:void(0)' onclick='autoComplete("+photoId+",\""+json[i].fname+"\")'><div><img style='width:32;heigh:32;margin-top:3px;' src='"+document.getElementById("basePath").value+((json[i].surl==null || json[i].surl=='')?"images/bg.png":json[i].surl)+"'/>"+json[i].fname+"</div></a>");
								}
								$("#ft"+photoId+selectIndex).css("color","yellow");
							}
						}
					});
				}
			}
		};
		
		//鼠标进来
		function mouserOver(photoId,index){
			$("#ft"+photoId+index).css("color","yellow");
		};
		
		//鼠标出去
		function mouserOut(photoId,index){
			$("#ft"+photoId+index).css("color","white");
		};
		
		//回车选择
		function enterDeal(photoId){
			if(window.event.keyCode==13){
				if(searchFriendNameStatus==0){
					return;
				}
				document.getElementById("ft"+photoId+selectIndex).click();
				setTimeout('removeEnter('+ photoId +')', 0);
				return;
			}
		};
		
		//去除回车
		function removeEnter(photoId){
			$("#reply"+photoId).val($("#reply"+photoId).val().replace(/[\r\n]/g,""));
			//保持光标位置
			setCursorPos("reply"+photoId, atIndex+friendNameLength);
		};
		
		//自动填充
		function autoComplete(photoId,friendName){
			var value = $("#reply"+photoId).val();
			
			var currentIndex = atIndex+atFriendName.length;
			
			var head = value.substring(0,currentIndex-atFriendName.length);
			
			var end = value.substring(currentIndex+atFriendName.length);
			
			//value+=(friendName+" ");
			if($.trim(end)==''){
				$("#reply"+photoId).val(head + friendName);
			}else{
				$("#reply"+photoId).val(head + friendName + " " + end);
			}
			//重置搜索状态
			searchFriendNameStatus=0;
			$("#friendTips"+photoId).html("");
			$("#friendTips"+photoId).css("display","none");
			//光标置后,并获取焦点
			//focusLast(document.getElementById("reply"+photoId));
			//保持光标位置
			setCursorPos("reply"+photoId, atIndex+friendName.length);
			//清空用户名
			atFriendName = "";
			//记录完整用户名长度
			friendNameLength = friendName.length;
			//统计剩余字数
			wordsNumber(photoId);
		};
		
		//显示还能输入多少字
		function wordsNumber(photoId){
			if($("#reply"+photoId).val().length>80){
				$("#reply"+photoId).val($("#reply"+photoId).val().substring(0,80));
			}
			$("#wordsNumber"+photoId).html((80-$("#reply"+photoId).val().length));
		};
		
		//光标置后
		function focusLast(obj){
			obj.focus();
		    var len = obj.value.length;
		    if (document.selection) {
		        var sel = obj.createTextRange();
		        sel.moveStart('character',len);
		        sel.collapse();
		        sel.select();
		    } else if (typeof obj.selectionStart == 'number' && typeof obj.selectionEnd == 'number') {
		        obj.selectionStart = obj.selectionEnd = len;
		    }
		};
		
		/**
	     * 获取光标在短连接输入框中的位置
	     * @param inputId 框Id
	     * @return {*}
	     */
		function getCursorPos(inputId){
	        var inpObj = document.getElementById(inputId);
	         if(navigator.userAgent.indexOf("MSIE") > -1) { // IE
	            var range = document.selection.createRange();
	            range.text = '';
	            range.setEndPoint('StartToStart',inpObj.createTextRange());
	            return range.text.length;
	        } else {
	            return inpObj.selectionStart;
	        }
	    };
	    
	    /**
	     * 设置光标在短连接输入框中的位置
	     * @param inputId 框Id
	     * @param pos
	     * @return {*}s
	     */
	    function setCursorPos(inputId, pos){

	        var inpObj = document.getElementById(inputId);
	        if(navigator.userAgent.indexOf("MSIE") > -1){
	            var range = document.selection.createRange();
	            var textRange = inpObj.createTextRange();
	            textRange.moveStart('character',pos);
	            textRange.collapse();
	            textRange.select();
	        }else{
	            inpObj.setSelectionRange(pos,pos);
	        	//inpObj.selectionStart = inpObj.selectionEnd = pos;
	        	inpObj.focus();
	        }
	    };
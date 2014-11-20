		//当用户输入@的时候 寻找用户好友列表里面的相关人员
		var searchFriendNameStatus = 0;
		var atFriendName;
		var selectIndex = 0;
		var selectLength = 0;
		var atIndex = 0;
		function getAtName(text,photoId){
			//统计剩余字数
			wordsNumber(photoId);
			//删除前后空格
	        //text.replace(/(^\s*)|(\s*$)/g,"");
			if(text=="@"){
				searchFriendNameStatus = 1;
				atFriendName = "";
				//记录@的位置
				atIndex = getCursorPos("reply"+photoId);
				$("#friendTips"+photoId).html("<center>Who do you want to at?</center>");
				$("#friendTips"+photoId).css("display","block");
			}
			if(searchFriendNameStatus==1){
				if(text==" "){
					searchFriendNameStatus=0;
					$("#friendTips"+photoId).html("");
					$("#friendTips"+photoId).css("display","none");
					return;
				}else if(text!="@"){
					//上选择
					if(window.event.keyCode==38){
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
						atFriendName += text;
						atFriendName = $("#reply"+photoId).val().substr(atIndex,atFriendName.length);
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
		};
		
		//自动填充
		function autoComplete(photoId,friendName){
			var value = $("#reply"+photoId).val();
			value = value.substring(0,(value.length-atFriendName.length));
			value+=(friendName+" ");
			$("#reply"+photoId).val(value);
			//重置搜索状态
			searchFriendNameStatus=0;
			$("#friendTips"+photoId).html("");
			$("#friendTips"+photoId).css("display","none");
			//光标置后,并获取焦点
			focusLast(document.getElementById("reply"+photoId));
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
	            inpObj.setSelectionRange(n,n);
	        }
	    };
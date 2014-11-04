		//当用户输入@的时候 寻找用户好友列表里面的相关人员
		var searchFriendNameStatus = 0;
		var atFriendName;
		var selectIndex = 0;
		var selectLength = 0;
		function getAtName(text,photoId){
			//删除前后空格
	        //text.replace(/(^\s*)|(\s*$)/g,"");
			if(text=="@"){
				searchFriendNameStatus = 1;
				atFriendName = "";
				$("#friendTips"+photoId).html("<center>who you want to at?</center>");
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
					}
					$.ajax({
						url:'/YearBook/user/getSearchInfomation_execute',  
						type:'post', 
				        data:"type=searchFriendName&friendList.userByFriendId.name="+atFriendName,
				        async:false,
						dataType:'json', 
						success:function (json) {
							if(json.error==undefined && json.length>0){
								$("#friendTips"+photoId).html("<center>Please choose the follow</center>");
								$("#friendTips"+photoId).css("display","block");
								//重置选择INDEX
								selectIndex = 0;
								selectLength = json.length;
								for(var i=0; i<json.length; i++){
									$("#friendTips"+photoId).append("<a id='ft"+photoId+i+"' href='javascript:void(0)' onclick='autoComplete("+photoId+",\""+json[i].fname+"\")'><div><img style='width:32;heigh:32' src='"+document.getElementById("basePath").value+((json[i].surl==null || json[i].surl=='')?"images/bg.jpg":json[i].surl)+"'/>"+json[i].fname+"</div></a>");
								}
								$("#ft"+photoId+selectIndex).css("color","yellow");
							}
						}
					});
				}
			}
		};
		
		//回车选择
		function enterDeal(photoId){
			if(window.event.keyCode==13){
				document.getElementById("ft"+photoId+selectIndex).click();
				setTimeout('removeEnter('+ photoId +')', 0);
				return;
			}
		}
		
		//去除回车
		function removeEnter(photoId){
			$("#reply"+photoId).val($("#reply"+photoId).val().replace(/[\r\n]/g,""));
		}
		
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
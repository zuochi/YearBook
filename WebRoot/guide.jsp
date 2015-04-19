<%@ page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 顶部菜单栏 -->
<div id="menu">

<s:if test="#session.user==null">
	<p>Please <a href="javascript:void(0)" onclick="goUrl(9)">Sign In</a>  or <a href="javascript:void(0)" onclick="goUrl(10)">Resgister</a></p>
</s:if>

<div class="guidance">
<div class="center">

<img style="position:absolute;margin:10px 0 0 -70px;" src="images/logo.png" alt="" width="136"	height="70"  />

<p><a style="margin-left:30px;" href="javascript:void(0)" onclick="goUrl(3)">Home</a></p>

<s:if test="#session.user!=null">
	<p><a href="javascript:void(0)" onclick="goUrl(5)">Post</a></p>
	<p><a href="javascript:void(0)" onclick="goUrl(2)">Profile</a></p>
	<p><a href="javascript:void(0)" onclick="goUrl(7)" style="position:absolute;margin:0 0 0 150px;">Message<my id="getCountSpan"></my></a></p>
	<p><a href="javascript:void(0)" onclick="goUrl(12)" style="position:absolute;margin:0 0 0 230px;">Feedback</a></p>
	<p><a href="javascript:void(0)" onclick="goUrl(8)" style="position:absolute;margin:0 0 0 300px;">Exit</a></p>
</s:if>
</div>
</div>
</div>
<input id="getCount" type="hidden" value='<s:property value="#session.user.name"/>'/>
<script type="text/javascript" src="js/goUrl.js"></script>
<script type="text/javascript">
	var scrollFunc = function (e) {
        e = e || window.event;
        if (e.wheelDelta) {  //判断浏览器IE，谷歌滑轮事件             
            if (e.wheelDelta > 0) { //当滑轮向上滚动时
               document.getElementById("menu").style.display="block";
            }
            if (e.wheelDelta < 0) { //当滑轮向下滚动时
            	document.getElementById("menu").style.display="none";
            }
        } else if (e.detail) {  //Firefox滑轮事件
            if (e.detail> 0) { //当滑轮向上滚动时
            	document.getElementById("menu").style.display="block";
            }
            if (e.detail< 0) { //当滑轮向下滚动时
            	document.getElementById("menu").style.display="none";
            }
        }
    }
    //给页面绑定滑轮滚动事件
    if (document.addEventListener) {//firefox
        document.addEventListener('DOMMouseScroll', scrollFunc, false);
    }
    //滚动滑轮触发scrollFunc方法  //ie 谷歌
    window.onmousewheel = document.onmousewheel = scrollFunc;
    
    //获取未读条数
    setInterval("getNewMessageCount()", "8888");//每个5秒刷新一次
    function getNewMessageCount(){
    	var getCount = document.getElementById("getCount").value;
    	if(getCount!='' && getCount!=null){
	    	$.ajax({
				url:'user/getMessage_getNewMessageCount',  
				type:'post', 
		        data:"",
		        async:false,
				dataType:'text', 
				success:function (count) {
					if(parseInt(count)>0){
						if(count>99){
							document.getElementById("getCountSpan").innerHTML="(99+)";
						}
						document.getElementById("getCountSpan").innerHTML="("+count+")";
					}else{
						document.getElementById("getCountSpan").innerHTML="";
					}
				}
			});
    	}
    };
    
    //初始化
    window.onload=function(){
    	getNewMessageCount();
    };
</script>
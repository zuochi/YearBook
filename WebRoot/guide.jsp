<%@ page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 顶部菜单栏 -->
<div id="menu">
<p>Please <a href="javascript:void(0)" onclick="goUrl(9)">Sign In</a>  or <a href="javascript:void(0)" onclick="goUrl(10)">Resgister</a></p>
<div class="guidance">
<div class="center">
<p><a href="javascript:void(0)" onclick="goUrl(3)">Home</a></p>
<p><a href="javascript:void(0)" onclick="goUrl(5)">Post</a></p>
<p><a href="javascript:void(0)" onclick="goUrl(7)">Message</a></p>
<img style="float:left;margin:0 0 0 5px;" src="images/logo.png" alt="" width="120"	height="80"  />
<p><a href="javascript:void(0)" onclick="goUrl(2)">Profile</a></p>
<p><a href="javascript:void(0)" onclick="goUrl(12)">Feedback</a></p>
<p><a href="javascript:void(0)" onclick="goUrl(8)">Exit</a></p>
</div>
</div>
</div>
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
</script>
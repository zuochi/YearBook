<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div style="width:100%;height:50px;background:#3ab6ae; ">
<input type="hidden" value="" id="url_value"/>

<div id="getUsers" class="top"><a href="admin/getUsers_execute" >用户管理</a></div>
<div id="getIWantTops" class="top"><a href="admin/getIWantTops_execute">图片审核</a></div>
<div id="getAnnouncements" class="top"><a href="admin/getAnnouncements_execute" >首页公告</a></div>
<div id="getReplys" class="top"><a href="admin/getReplys_execute" >评论管理</a></div>
<div id="getPhotos" class="top"><a href="admin/getPhotos_execute">图片管理</a></div>
<div id="getSchoolYears" class="top"><a href="admin/getSchoolYears_execute">学年管理</a></div>
<div id="getProfessions" class="top"><a href="admin/getProfessions_execute">专业管理</a></div>
<div id="getFeedBacks" class="top" ><a href="admin/getFeedBacks_execute">反馈处理</a></div>
<div id="getFeedBacks" class="top" ><a href="index.jsp">前往用户端</a></div>
<div id="getFeedBacks" class="top" ><a href="admin/logoutA_execute">退出</a></div>

<!-- <div class="top2">用户反馈</div> -->
</div>
<script type="text/javascript">
window.onload=function(){
	if((window.location.href).indexOf("getUsers_execute")>0){
		document.getElementById("getUsers").setAttribute("class", "top2");
	}
	else if((window.location.href).indexOf("getAnnouncements")>0){
		document.getElementById("getAnnouncements").setAttribute("class", "top2");
	}
	else if((window.location.href).indexOf("getIWantTops")>0){
		document.getElementById("getIWantTops").setAttribute("class", "top2");
	}
	else if((window.location.href).indexOf("getReplys")>0){
		document.getElementById("getReplys").setAttribute("class", "top2");
	}
	else if((window.location.href).indexOf("getPhotos")>0){
		document.getElementById("getPhotos").setAttribute("class", "top2");
	}
	else if((window.location.href).indexOf("getSchoolYears")>0){
		document.getElementById("getSchoolYears").setAttribute("class", "top2");
	}
	else if((window.location.href).indexOf("getProfessions")>0){
		document.getElementById("getProfessions").setAttribute("class", "top2");
	}
	else if((window.location.href).indexOf("getFeedBacks")>0){
		document.getElementById("getFeedBacks").setAttribute("class", "top2");
	}
};
</script>
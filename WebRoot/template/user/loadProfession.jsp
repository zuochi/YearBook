<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<div id="professionsDIV" class="xi">
	<!-- <div class="shake"><a href="mainPage_xiyu.jsp" target="main"><div class="professions">西语</div></a></div>
	<div class="shake"><a href="mainPage_xinke.jsp" target="main"><div class="professions">信科</div></a></div>
	<div class="shake"><a href="mainPage_xiyu.jsp" target="main"><div class="professions">西语</div></a></div>
	<div class="shake"><a href="mainPage_jingji.jsp" target="main"><div class="professions">经济</div></a></div>
	<div class="shake"><a href="mainPage_yingyu.jsp" target="main"><div class="professions">英语</div></a></div>
	<div class="shake"><a href="mainPage_guanli.jsp" target="main"><div class="professions">管理</div></a></div>
	<div class="shake"><a href="mainPage_dongyu.jsp" target="main"><div class="professions">东语</div></a></div>
	<div class="shake"><a href="mainPage_zhongwen.jsp" target="main"><div class="professions">中文</div></a></div> -->
</div>
<script type="text/javascript">
//读取类型
function loadProfessions(){
	$.ajax({
		type: "POST",
		url: "user/getIndexProfessions_execute",
		data: "",
		dataType: "json",
		success: function(json){
			if(json.error == undefined){
				for(var i=0 ; i<json.length ; i++){
					$("#professionsDIV").append("<div class='shake'><a href='javascript:void(0)' title='"+json[i].context+"'><div class='professions'>"+json[i].name+"</div></a></div>");
				}   
			}
		}
	}); 
};
</script>
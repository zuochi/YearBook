<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<div id="professionsDIV" class="xi">
	<div class="shake">
		<a href="javascript:void(0)" onclick='selectProfession(0)' title='Top 20.'>
			<div id='professionDIV0' class="professions">人气</div>
		</a>
	</div>
	<!--  <div class="shake"><a href="mainPage_xinke.jsp" target="main"><div class="professions">信科</div></a></div>
	<div class="shake"><a href="mainPage_xiyu.jsp" target="main"><div class="professions">西语</div></a></div>
	<div class="shake"><a href="mainPage_jingji.jsp" target="main"><div class="professions">经济</div></a></div>
	<div class="shake"><a href="mainPage_yingyu.jsp" target="main"><div class="professions">英语</div></a></div>
	<div class="shake"><a href="mainPage_guanli.jsp" target="main"><div class="professions">管理</div></a></div>
	<div class="shake"><a href="mainPage_dongyu.jsp" target="main"><div class="professions">东语</div></a></div>
	<div class="shake"><a href="mainPage_zhongwen.jsp" target="main"><div class="professions">中文</div></a></div> 
    <div class="kong"></div> 
    <div class="kong"></div>
    <div class="kong"></div>
    <div class="kong"></div>
    <div class="kong"></div>
    <div class="kong"></div>
    <div class="kong"></div>
    <div class="kong"></div>
    <div class="kong"></div>
    <div class="kong"></div>
    <div class="kong"></div>-->
</div>
<input id="choosedProfessionId" type="hidden"/>
<script type="text/javascript">
//选择专业
function selectProfession(professionId){
	//隐藏菜单
	document.getElementById("menu").style.display="none";
	//更变状态
	document.getElementById("professionDIV"+document.getElementById("choosedProfessionId").value).className="professions";
	document.getElementById("professionDIV"+professionId).className="professions2";
	//更新专业id
	document.getElementById("choosedProfessionId").value = professionId;
	//更新页码为0
	document.getElementById("toPage").value=0;
	//清空图片
	$("#ctr").html("");
	//加载图片
	loadPhotos();
	//向上滚动
	mScroll("professionsDIV");
	//重置没有图片的提示
	isNotice=0;
};

//读取专业
function loadProfessions(){
	$.ajax({
		type: "POST",
		url: "user/getIndexProfessions_execute",
		data: "",
		dataType: "json",
		success: function(json){
			if(json.error == undefined){
				var random = Math.floor(Math.random()*json.length);
				for(var i=0 ; i<json.length ; i++){
					if(i==random){
						document.getElementById("choosedProfessionId").value = json[i].id;
						$("#professionsDIV").append(
								"<div class='shake'>"+
									"<a href='javascript:void(0)' onclick='selectProfession("+json[i].id+")' title='"+json[i].context+"'>"+
										"<div id='professionDIV"+json[i].id+"' class='professions2'>"+json[i].name+"</div>"+
									"</a>"+
								"</div>");
					}else{
						$("#professionsDIV").append(
								"<div class='shake'>"+
									"<a href='javascript:void(0)' onclick='selectProfession("+json[i].id+",\""+json[i].name+"\")' title='"+json[i].context+"'>"+
										"<div id='professionDIV"+json[i].id+"' class='professions'>"+json[i].name+"</div>"+
									"</a>"+
								"</div>");
					}
				}
				if((11-json.length)>0){
					for(var i=0 ; i<(11-json.length) ; i++){
						$("#professionsDIV").append("<div class='kong'></div>");
					}
				}
				//更新页码为0
				document.getElementById("toPage").value=0;
				loadPhotos();
			}
		}
	}); 
};
</script>
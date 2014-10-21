<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 

"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/photo.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="styles/screen.css" media="screen"/>
<link rel="stylesheet" href="styles/lightbox.css" media="screen"/>
<title>photo</title>
<script type="text/javascript">
//局部获取照片
var toPageP=1;
var photoAlbumIdP=0;
function getPhotosByPerPage(photoAlbumId,isNew){
	if("true"==isNew){
		$("#ctr").html("");
		$("#hasPhotos").html("");
		toPageP=1;
		photoAlbumIdP=photoAlbumId;
	}
	$.ajax({
		url:'/YearBook/user/getPhotosByPerPage_execute',  
		type:'post', 
        data:"userId="+document.getElementById("userId").value+"&photoAlbumId="+photoAlbumId+"&toPage="+toPageP,
        async:false,
		dataType:'json', 
		success:function (json) {
			if(json.error==undefined){
				$("#photoAlbumTitle").html("相册:" + $("#photoAlbumName"+photoAlbumId).val());
				for(var i=0; i<json.length; i++){
					$("#ctr").append(
							"<div class='box photo col21' id='photo"+json[i].id+"'>"+
							"<a class='example-image-link' href='"+json[i].url+"' data-lightbox='example-set' >"+
							"<img src='"+json[i].url+"' alt='Plants'/></a>"+
							"<a onclick='open_comment();' href='javascript:void(0)' target='main' class='comments_icon' title='评论'>&nbsp;</a><div class='com_number'>(999+)</div>"+
							"<div class='likes_icon' href='javascript:void(0)'  title='like'></div><div class='like_number'>(999+)</div>"+
						 	"<div class='up_icon'  title='我要上主页'><a href='javascript:void(0)' onclick='iWantTop("+json[i].id+","+json[i].userId+")'>Up↑</a></div>"+
							"<div class='delete' title='删除'><a href='javascript:void(0)' onclick='deletePhoto("+json[i].id+")'>&nbsp;</a></div>"+
							"</div>"
						);
				}
			}else{
				alert("access fail!");
			}
			if(json==false){
				$("#hasPhotos").html("木有了哦,<a href='javascript:void(0)' onclick='scrollToTop()'>返回顶部</a>");
			}
		}
	});
};

function scrollToTop(){
	document.body.scrollTop=0;
}

document.onscroll = function(){
	//alert(document.body.scrollTop + ":" + document.body.clientHeight + ":" +document.body.scrollHeight);
    if(document.body.scrollTop+document.body.clientHeight>=(document.body.scrollHeight-648)){//630=最后一个减去前面2个
    	toPageP+=1;
    	getPhotosByPerPage(photoAlbumIdP,"false");
    }
};
</script>

</head>
<body>
<input type="hidden" id="userId" value="<s:property value="#session.user.id"/>"/>
<div id="main" class="border_radius">
  <div class="p"><p>相册专辑（共<s:property value="#session.photoAlbumscount"/>个）</p></div>
       <div id="header_tagline"> <div class="header_tagline"></div></div>
       
    <s:if test="#session.photoAlbums!=null">
    <div id=showButton><a href="javascript:void(0)" onclick="changeAlbum()" target="main" class="up">&nbsp;</a></div>
    <input type="radio" checked="checked" name="isNew" onchange="showUpButton()"/>已有相册:
	<select id="photoAlbumId" name="photoAlbumId">
		<s:iterator value="#session.photoAlbumsAll" id="o">
			<option id="photoAlbumSelectElement<s:property value="#o.id"/>" value="<s:property value="#o.id"/>"><s:property value="#o.name"/></option>
		</s:iterator>
	</select>
	<input type="radio" name="isNew" onchange="showNewButton()"/>新相册:
	<input type="text" name="newPhotoAlbum" id="newPhotoAlbum"/>
	</s:if>
	<s:else>
		<div id=showButton><a href="javascript:void(0)" onclick="changeAlbum()" target="main" class="new">&nbsp;</a></div>
		<input type="hidden" name="isNew"/>
		<input type="radio" checked="checked" name="isNew" onchange="showNewButton()"/>新相册:
		<input type="text" name="newPhotoAlbum" id="newPhotoAlbum"/>
	</s:else>
	
 <div id="ablum">

<s:if test="#session.photoAlbumscount>#session.photoAlbumPc.pageSize ">
<div align="center">
				页码：<s:property value="#session.photoAlbumPc.currentPage" />/<s:property
						value="#session.photoAlbumPc.totalPages" /> <s:if
						test="#session.photoAlbumPc.hasPrevious==true">
						<a href="/YearBook/user/getPhotoAlbumsByPerPage_execute?userId=<s:property value="#session.user.id"/>&toPage=1">首页</a>
						
						<a href="/YearBook/user/getPhotoAlbumsByPerPage_execute?userId=<s:property value="#session.user.id"/>&toPage=<s:property value="#session.photoAlbumPc.previousPage"/>">上一页</a>
						
					</s:if> <s:else>
						首页
						上一页
					</s:else> <s:if test="#session.photoAlbumPc.hasNext==true">
						<a href="/YearBook/user/getPhotoAlbumsByPerPage_execute?userId=<s:property value="#session.user.id"/>&toPage=<s:property value="#session.photoAlbumPc.nextPage"/>">下一页</a> 
						<%-- <a onclick="getPhotoAlbumsByPerPage(<s:property value="#session.user.id"/>,<s:property value="#session.photoAlbumPc.nextPage"/>)" href="javascript:void(0)">下一页</a>--%>
						<a href="/YearBook/user/getPhotoAlbumsByPerPage_execute?userId=<s:property value="#session.user.id"/>&toPage=<s:property value="#session.photoAlbumPc.totalPages"/>">尾页</a>
						
					</s:if> <s:else>
						下一页
						尾页
					</s:else>
				跳转：<select id="changePage" onchange="toPage(this.value)">
						<s:iterator var="index" begin="1" end="#session.photoAlbumPc.totalPages">
							<option
							<s:if test="#session.photoAlbumPc.currentPage==#index">selected="selected"</s:if>>
								<s:property value="#index" />
							</option>
						</s:iterator>
				</select>
		</div>
</s:if>

    <%--<div class="ablum"> <a onclick="open_secret();"  target="main">
    <img src="images/ablum.png" alt="" width="140" height="140" /> <h1>相册1</h1></a>  <div class="delete" title="删除"></div>
     <div class="pen_icon" title="改名">	 </div></div>--%>

<s:if test="#session.photoAlbums!=null">
<s:iterator value="#session.photoAlbums" id="o">
    <div id="pAlbum<s:property value="#o.id"/>" class="ablum"> <a href="javascript:void(0)" onclick="getPhotosByPerPage(<s:property value="#o.id"/>,'true')">
    	<s:if test="#o.photoUrl==null"><img src="images/ablum.png" alt="" width="140" height="140" /></s:if>
    	<s:else><img src="<s:property value="#o.photoUrl"/>" alt="" width="140" height="140" /></s:else>
    	<h1 id="photoAlbum<s:property value="#o.id"/>"><s:property value="#o.name"/></h1></a>
<a href="javascript:void(0)" onclick="deletePhotoAlbum(<s:property value="#o.id"/>)"><div class="delete" title="删除"></div></a>
<div class=tree_1 onMouseover="line(this)" onMouseOut="delline(this)" onClick="showtree('album<s:property value="#o.id"/>')">
			<a href="javascript:void(0)"><div class="pen_icon" title="修改"></div></a>
			<span id="span_album<s:property value="#o.id"/>" style="color:black"></span>
</div>
<br></br>
			<div id="album<s:property value="#o.id"/>_value" class=tree_2 style="display:none">
			<div class=tree_add onMouseOver="line(this)" onMouseOut="delline(this)">
				<h7>name :</h7><input type="text" id="photoAlbumName<s:property value="#o.id"/>" name="photoAlbumName" value="<s:property value="#o.name"/>" style="width:96px;height:17px; color:black; font-size:11px" /></h7>
				<h7>perm :</h7><select id="photoAlbumPermission<s:property value="#o.id"/>" name="photoAlbumPermission" style="width:99px;"></h7>
					<option <s:if test="#o.permission==0">selected="selected"</s:if> value="0">私有</option>
					<option <s:if test="#o.permission==1">selected="selected"</s:if> value="1">公开</option>
					<option <s:if test="#o.permission==2">selected="selected"</s:if> value="2">仅好友</option>
					<option <s:if test="#o.permission==3">selected="selected"</s:if> value="3">密码</option>
				</select>
				<h7>code :</h7><input type="text" id="photoAlbumCode<s:property value="#o.id"/>" name="photoAlbumCode" style="width:97px;height:17px; color:black; font-size:11px" value="<s:property value="#o.password"/>"/></h7>
					<input style="color:black;font-size:11px;float:right;margin-top:3px;" type="button" value="修改" onclick="updateAlbum(<s:property value="#o.id"/>)"/>
			</div>
			</div>
	</div>
</s:iterator>
</s:if><s:else>
<center>还没有相册哦,请新建相册。</center>
</s:else>
</div>
<br/><br/>
  <div id="header_tagline"> <div class="header_tagline2"></div></div>

<div class="wrapper">

	<section id="intro" class="intro-section">
		
	<div class="t1"><h3 id="photoAlbumTitle" style="clear: both;"></h3></div>

	<div class="image-row">
		<div class="image-set">
			<div id="ctr">
			</div>
		</div>
	</div>
	<div id="hasPhotos"></div>
</section>
</div></div>
	
	<!--评论弹窗-->
	<br />
	<div id="commentbg"></div>
	<div id="comment" style="display:none;">	
	<h1><input id="close_comment" type="button"  onclick="close_comment()"  style="height:20px;width:20px; color:#234; font-size:15px; float:right;" title="退出" value="X" /></h1>
	<div id="liuyan" >
	<textarea cols="45" rows="3" type="text" class="liuyan"  maxLength=50 ></textarea>
	<div class="send"><input type="button" style=" color:#234; font-size:13px;"  value="发送" /></div></div>
<!--评论-->
<div class="ds-post-main">
		<div class="ds-avatar">
			<a title="用户名" href="" target="_blank"><img alt="设计达人" src="images/alex.jpg"></a><!-- 用户头像 -->
		</div>
		<div class="ds-comment-body">
				<a title="用户名" href="" target="_blank" class="user-name">用户名</a>
				<div class="message">what'up man.heyyo,我想长高10厘米，增重15斤 </div>
				<div id="tc">
				<h1>时间：2015年9月14日22:38</h1>
				<div class=tree_1 onMouseover="line(this)" onMouseOut="delline(this)" onClick="showtree('reply')">
					<a href="javascript:void(0)"><div class="comments_icon"></div></a>
					<span id=span_reply style="color:gray"></span>
				</div>
				<div id=reply_value class=tree_2 style="display:none">
					<div class=tree_add onMouseOver="line(this)" onMouseOut="delline(this)">
						<form action="">
						<textarea cols="35" rows="3" type="text" class="liuyan2"  maxLength=50 name="reply"></textarea>
							<input type="button" style=" color:#234; font-size:11px;" value="发送"  />
						</form> 
					</div>
					<div class="receive">收到的评论:
						<div class="message">肥妹：heyhey.我要减肥!</div>
						<div class="message">肥妹：heyhey.我要减肥!</div>
						<div class="message">肥妹：heyhey.我要减肥!</div>
					</div>	
				</div>
			</div>
		</div>
	</div>
</div>
    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/lightbox-2.6.min.js"></script>
	<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" src="js/photo.js"></script>
	 <script type="text/javascript">
	$(function() {
	var $ctr = $('#ctr');
	$ctr.imagesLoaded(function() {
		$ctr.masonry({
			itemSelector : '.box',
			isFitWidth : true,
			isAnimated : true
		});
	});
});</script>
</body>
</html>
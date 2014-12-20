<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!-- 旧页面、无用 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 

"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/photo.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="styles/screen.css" media="screen"/>
<link rel="stylesheet" href="styles/lightbox.css" media="screen"/>
<title>photo</title>
</head>

<body>
<input type="hidden" id="userId" value="<s:property value="#session.user.id"/>"/>
<div id="main" class="border_radius">
  <div class="p"><p>相册专辑（共<s:property value="#session.photoAlbumscount"/>个）</p></div>
       <div id="header_tagline"> <div class="header_tagline"></div></div>
   <input type="radio" name="isNew" checked="checked"/>已有相册:
	<select id="photoAlbumId" name="photoAlbumId">
		<s:iterator value="#session.photoAlbumsAll" id="o">
			<option id="photoAlbumSelectElement<s:property value="#o.id"/>" value="<s:property value="#o.id"/>"><s:property value="#o.name"/></option>
		</s:iterator>
	</select>
	<input type="radio" name="isNew"/>新相册:
	<input type="text" name="newPhotoAlbum" id="newPhotoAlbum"/>

   <a href="javascript:void(0)" onclick="changeAlbum()" target="main" class="up">&nbsp;</a>
   <a href="" target="main" class="new"></a>
 <div id="ablum">

<s:if test="#session.photoAlbumscount>#session.pc.pageSize ">
<div align="center">
				页码：<s:property value="#session.pc.currentPage" />/<s:property
						value="#session.pc.totalPages" /> <s:if
						test="#session.pc.hasPrevious==true">
						<a href="/YearBook/user/User_getPhotoAlbumsByPerPage?userId=<s:property value="#session.user.id"/>&toPage=1">首页</a>
						
						<a href="/YearBook/user/User_getPhotoAlbumsByPerPage?userId=<s:property value="#session.user.id"/>&toPage=<s:property value="#session.pc.previousPage"/>">上一页</a>
						
					</s:if> <s:else>
						首页
						上一页
					</s:else> <s:if test="#session.pc.hasNext==true">
						<a href="/YearBook/user/User_getPhotoAlbumsByPerPage?userId=<s:property value="#session.user.id"/>&toPage=<s:property value="#session.pc.nextPage"/>">下一页</a>
						
						<a href="/YearBook/user/User_getPhotoAlbumsByPerPage?userId=<s:property value="#session.user.id"/>&toPage=<s:property value="#session.pc.totalPages"/>">尾页</a>
						
					</s:if> <s:else>
						下一页
						尾页
					</s:else>
				跳转：<select id="changePage" onchange="toPage(this.value)">
						<s:iterator var="index" begin="1" end="#session.pc.totalPages">
							<option
							<s:if test="#session.pc.currentPage==#index">selected="selected"</s:if>>
								<s:property value="#index" />
							</option>
						</s:iterator>
				</select>
		</div>
</s:if>

    <div class="ablum"> <a onclick="open_secret();"  target="main">
    <img src="images/ablum.png" alt="" width="140" height="140" /> <h1>相册1</h1></a>  <div class="delete" title="删除"></div>
     <div class="pen_icon" title="改名">	 </div></div>




    <div id="pAlbum<s:property value="#o.id"/>" class="ablum"> <a href="javascript:void(0)" onclick="getPhotosByPerPage(<s:property value="#o.id"/>)">
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
				<h7>name:</h7><input type="text" id="photoAlbumName<s:property value="#o.id"/>" name="photoAlbumName" value="<s:property value="#o.name"/>" style="width:96px;height:17px; color:black; font-size:11px" />
				<h7>perm :</h7><select id="photoAlbumPermission<s:property value="#o.id"/>" name="photoAlbumPermission" style="width:99px;">
					<option <s:if test="#o.permission==0">selected="selected"</s:if> value="0">私有</option>
					<option <s:if test="#o.permission==1">selected="selected"</s:if> value="1">公开</option>
					<option <s:if test="#o.permission==2">selected="selected"</s:if> value="2">仅好友</option>
					<option <s:if test="#o.permission==3">selected="selected"</s:if> value="3">密码</option>
				</select>
				<h7>code :</h7><input type="text" id="photoAlbumCode<s:property value="#o.id"/>" name="photoAlbumCode" style="width:97px;height:17px; color:black; font-size:11px" value="<s:property value="#o.password"/>"/>
					<input style="color:black; font-size:11px;float:right;margin-top:3px;" type="button" value="修改" onclick="updateAlbum(<s:property value="#o.id"/>)"/>
			</div>
			</div>
	</div>
</div>

    

  <div id="header_tagline"> <div class="header_tagline2"></div></div>

<div class="wrapper">

	<section id="intro" class="intro-section">
		

		<div class="t1"><h3 style="clear: both;">相册1</h3></div>

		<div class="image-row">
			<div class="image-set">
				<div id="ctr">
	    <div class="box photo col21">
	    <a class="example-image-link" href="images/2.jpg" data-lightbox="example-set" >
		<img src="images/2.jpg" alt="Plants"/> </a>
		<a onclick="open_comment();"  target="main" class="comments_icon" title="评论"></a><div class="com_number">(999+)</div>
		<div class="likes_icon" title="like"></div><div class="like_number">(999+)</div>
		<div class="up_icon"  title="我要上主页">Up↑</div>
		<div class="delete" title="删除"></div>
	
		</div>
		
		<div class="box photo col21">
		<a class="example-image-link" href="images/3.jpg" data-lightbox="example-set" >
		<img src="images/3.jpg" alt="Plants"/></a>
		<a onclick="open_comment();"  target="main" class="comments_icon" title="评论"></a><div class="com_number">(999+)</div>
		<div class="likes_icon" title="like"></div><div class="like_number">(999+)</div>
		<div class="up_icon"  title="我要上主页">Up↑</div>
		<div class="delete" title="删除"></div>
		</div>
		
		<div class="box photo col21">
		<a class="example-image-link" href="images/4.jpg" data-lightbox="example-set" >
		<img src="images/4.jpg" alt="Plants"/></a>
	<a onclick="open_comment();"  target="main" class="comments_icon" title="评论"></a><div class="com_number">(999+)</div>
		<div class="likes_icon" title="like"></div><div class="like_number">(999+)</div>
		<div class="up_icon"  title="我要上主页">Up↑</div>
		<div class="delete" title="删除"></div>
		</div>
		
		<div class="box photo col21">
		<a class="example-image-link" href="images/5.jpg" data-lightbox="example-set" >
		<img src="images/5.jpg" alt="Plants"/></a>
		<a onclick="open_comment();"  target="main" class="comments_icon" title="评论"></a><div class="com_number">(999+)</div>
		<div class="likes_icon" title="like"></div><div class="like_number">(999+)</div>
		<div class="up_icon"  title="我要上主页">Up↑</div>
		<div class="delete" title="删除"></div>
		</div>
	
	    <div class="box photo col21">
		<a class="example-image-link" href="images/6.jpg" data-lightbox="example-set" >
		<img src="images/6.jpg" alt="Plants"/></a>
		<a onclick="open_comment();"  target="main" class="comments_icon" title="评论"></a><div class="com_number">(999+)</div>
		<div class="likes_icon" title="like"></div><div class="like_number">(999+)</div>
		<div class="up_icon"  title="我要上主页">Up↑</div>
		<div class="delete" title="删除"></div>
		</div>
		
		<div class="box photo col21">
		<a class="example-image-link" href="images/7.jpg" data-lightbox="example-set" >
		<img src="images/7.jpg" alt="Plants"/></a>
		<a onclick="open_comment();"  target="main" class="comments_icon" title="评论"></a><div class="com_number">(999+)</div>
		<div class="likes_icon" title="like"></div><div class="like_number">(999+)</div>
		<div class="up_icon"  title="我要上主页">Up↑</div>
		<div class="delete" title="删除"></div>
		</div>
	
	
	    <div class="box photo col21">
	    <a class="example-image-link" href="images/8.jpg" data-lightbox="example-set" >
		<img src="images/8.jpg" alt="Plants"/></a>
	<a onclick="open_comment();"  target="main" class="comments_icon" title="评论"></a><div class="com_number">(999+)</div>
		<div class="likes_icon" title="like"></div><div class="like_number">(999+)</div>
		<div class="up_icon"  title="我要上主页">Up↑</div>
		<div class="delete" title="删除"></div>
		</div>
	
	    <div class="box photo col21">
	    <a class="example-image-link" href="images/9.jpg" data-lightbox="example-set" >
		<img src="images/9.jpg" alt="Plants"/></a>
		<a onclick="open_comment();"  target="main" class="comments_icon" title="评论"></a><div class="com_number">(999+)</div>
		<div class="likes_icon" title="like"></div><div class="like_number">(999+)</div>
		<div class="up_icon"  title="我要上主页">Up↑</div>
		<div class="delete" title="删除"></div>
		</div>
	
	    <div class="box photo col21">
	    <a class="example-image-link" href="images/10.jpg" data-lightbox="example-set" >
		<img src="images/10.jpg" alt="Plants"/></a>
	<a onclick="open_comment();"  target="main" class="comments_icon" title="评论"></a><div class="com_number">(999+)</div>
		<div class="likes_icon" title="like"></div><div class="like_number">(999+)</div>
		<div class="up_icon"  title="我要上主页">Up↑</div>
		<div class="delete" title="删除"></div>
		</div>
		
		
		
	
			</div>
		</div>
	</div>
</section>
</div></div>
	<jsp:include page="Ablum_sceret.jsp" />
	<jsp:include page="Ablum_comment.jsp" />
    <script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/lightbox-2.6.min.js"></script>
	<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
	<script type="text/javascript" src="js/photo.js"></script>

	 
	 <script>
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
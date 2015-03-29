<%@ page import="bean.User"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!-- Home 首页 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/csshake.css" rel="stylesheet" type="text/css"/>
<link href="styles/reset.css" rel="stylesheet"  type="text/css"/>
<link rel="stylesheet" href="styles/styles.css" type="text/css"/>
<link rel="stylesheet" href="styles/jquery.skippr.css" type="text/css"/>
        
<jsp:include page="guide.jsp" />
<jsp:include page="styles/my.css" />
<title>Year Book </title>
</head>
<%if("login".equals(request.getParameter("type"))){
	
	%>
<body onload="open_login();">
<%
	} else if("signup".equals(request.getParameter("type"))){
%>
<body onload="open_register();">
<%
	} else{
%>
<body>
<%
	}
%>

<!-- top -->

	<!--滚动图-->

	  <div class="hero">

            <div id="random">
               
                <div style="background-image: url(images/slide1.jpg)">
                 <div class="tagline">
                 <h3>Welcome1:</h3>
                 <p>Show your Pic here <br> Click the top nearby your photos.</p>
                 </div></div>
                
                 <div style="background-image: url(images/slide2.jpg)"> 
                 <div class="tagline">
                 <h3>Welcome2:</h3>
                 <p>Show your Pic here <br> Click the top nearby your photos.</p>
                </div></div> 
                
                <div style="background-image: url(images/slide3.jpg)"></div>                   
                <div style="background-image: url(images/slide4.jpg)"></div>
            </div>

            <!-- <div id="random">
                <img src="img/test3.jpg">
                <img src="img/test4.jpg">
                <img src="img/test1.jpg">
                <img src="img/test5.jpg">
            </div> -->

  
                <div class="container">
               
                
            </div> 
           
           
        </div>


<div id="kong1"></div>
	<!-- end #slider-container -->

	<!-- end #header -->

<div class="xi">
	<!--各系-->
<div class="f"></div>
<div class="shake" style="margin:0"><a href="mainPage_xinke.jsp" target="main"><div class="professions">信科</div> </a></div>
<div class="shake"><a href="mainPage_xiyu.jsp" target="main"><div class="professions">西语</div> </a>     </div>
<div class="shake"><a href="mainPage_jingji.jsp" target="main"><div class="professions">经济</div> </a>   </div>
<div class="shake"><a href="mainPage_yingyu.jsp" target="main"><div class="professions">英语</div> </a>   </div>
<div class="shake"><a href="mainPage_guanli.jsp" target="main"><div class="professions">管理</div> </a>   </div>
<div class="shake"><a href="mainPage_dongyu.jsp" target="main"><div class="professions">东语</div> </a>   </div>
<div class="shake"><a href="mainPage_zhongwen.jsp" target="main"><div class="professions">中文</div> </a> </div>
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
<div class="kong"></div>
</div>



	
    
    <!--流布局-->
     <div id="pic">
	<div id="ctr">
		<div class="box photo col3">
			<img src="images/1.jpg" alt="Like" />
		    <div class="like_user"><div class="like_user_name">Kass</div><div class="like_user_head"></div></div> 
			<div class="likes_icon"><div class="like_number">(9999+)</div>
		 
		   </div></div>
		<div class="box photo col3">
			<img src="images/2.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>

		<div class="box photo col3">
			<img src="images/3.jpg" alt="Like" />
		<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/4.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/5.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/6.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/7.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/8.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/9.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/10.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>

		<div class="box photo col3">
			<img src="images/11.jpg" alt="Like" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div style="clear:both; height: 40px"></div>
	</div></div>
	<!-- END container -->
	
	
	<jsp:include page="User_login.jsp" />
	<jsp:include page="User_register.jsp" />
	
 	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/jquery.skippr.js"></script>
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="js/jquery.animate-colors-min.js"></script>
	<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
	<script type="text/javascript">
	    $(function(){
	    var $ctr = $('#ctr');
	    $ctr.imagesLoaded( function(){
	      $ctr.masonry({
	        itemSelector : '.box',
			isFitWidth: true,
			isAnimated: true
	      });
	    });
	  
	  });
	  $(document).ready(function() {
		$("#random").skippr();
		$("#random2").skippr({
		navType: 'bubble',
		autoPlay: true,
		autoPlayDuration: 2000
		});
	});
	</script>
</body>
</html>
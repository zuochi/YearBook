<%@ page import="bean.User"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="styles/profession.css" />
<link href="styles/csshake.css" rel="stylesheet" type="text/css"/>
<script src="js/modernizr.js" type="text/javascript"></script>
<link href="styles/reset.css" rel="stylesheet"  type="text/css">
<link rel="stylesheet" href="styles/styles.css" type="text/css">
<link rel="stylesheet" href="styles/jquery.skippr.css" type="text/css">
        

<jsp:include page="styles/my.css" />
<title>IT </title>
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
	<!--滚动图-->

	  <div class="hero">

            <div id="random">
               
                <div style="background-image: url(images/slide1.jpg)"></div>
                <div style="background-image: url(images/slide2.jpg)"></div> 
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
                <div class="tagline">
                    <h3>Tips:</h3>
                    <div class="tips">Show your Pic here <br> Click the top nearby your photos.</div>
                </div>
                
            </div>
           
           
        </div>


<div id="kong1"></div>
	<div class="xi">
	<!--各系-->
<div class="f"></div>
<img src="images/xinke.png" alt="" width="90" height="90" /> &nbsp;&nbsp;
<div class="shake"><a href="mainPage_xiyu.jsp" target="main"><img src="images/xiyu.jpg" alt="" width="90" height="90" /> </a>     </div>&nbsp;&nbsp;
<div class="shake"><a href="mainPage_jingji.jsp" target="main"><img src="images/jingji.jpg" alt="" width="90" height="90" /> </a>     </div>&nbsp;&nbsp;
<div class="shake"><a href="mainPage_yingyu.jsp" target="main"><img src="images/yingyu.jpg" alt="" width="90" height="90" /> </a>   </div>&nbsp;&nbsp;
<div class="shake"><a href="mainPage_guanli.jsp" target="main"><img src="images/guanli.jpg" alt="" width="90" height="90" /> </a>   </div>&nbsp;&nbsp;
<div class="shake"><a href="mainPage_dongyu.jsp" target="main"><img src="images/dongyu.jpg" alt="" width="90" height="90" /> </a>   </div> &nbsp;&nbsp;
<div class="shake"><a href="mainPage_jinrong.jsp" target="main"><img src="images/jinrong.jpg" alt="" width="90" height="90" /></a> </div>&nbsp;&nbsp;
<div class="shake"><a href="mainPage_zhongwen.jsp" target="main"><img src="images/zhongwen.jpg" alt="" width="90" height="90" /> </a> </div>&nbsp;&nbsp;

<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
<img src="images/kong.jpg" alt="" width="90" height="90" />&nbsp;
</div>



<div class="frame">

<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>11.13 welcome to my graduation</p>
 </span>
</div>
<div class="name1">Kass</div> 
</div>






<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>中文签名就个字你试试个中文英文的话很容易就满了主要是中文中文占位置很多哈哈哈哈哈哈哈</p>
 </span>
</div>
<div class="name1">Kass</div> 
</div>
<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>HI,I Am Kass</p>
 </span>
</div>
<div class="name1">Kass</div> 
</div>

<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>HI,I Am Kass</p>
 </span>
</div>
<div class="name1">Kass</div> 
</div>
<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>HI,I Am Kass</p>
 </span>
</div>
<div class="name1">Kass</div> 
</div>
<div class="one">
<div class="pic">
 <img src="images/alex.jpg" class="pic-image" alt="Pic"/>
	<span class="pic-caption left-to-right">	      
<p>HI,I Am Kass</p>
 </span>
</div>
<div class="name1">Kass</div> 
</div>



	
   </div>


	
	
 	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/jquery.skippr.js"></script>
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="js/jquery.animate-colors-min.js"></script>
	
	<script type="text/javascript">
	  
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
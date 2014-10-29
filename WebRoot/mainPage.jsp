<%@ page import="bean.User"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/csshake.css" rel="stylesheet" type="text/css"/>
<link href="styles/reset.css" rel="stylesheet"  type="text/css">
<link rel="stylesheet" href="styles/styles.css" type="text/css">
<link rel="stylesheet" href="styles/jquery.skippr.css" type="text/css">
        

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
                    <p>Show your Pic here <br> Click the top nearby your photos.</p>
                </div>
                
            </div>
           
           
        </div>


<div id="kong1"></div>
	<!-- end #slider-container -->

	<!-- end #header -->

<div class="xi">
	<!--各系-->
<div class="f"></div>
<div class="shake"><a href=""><img src="images/xinke.jpg" alt="" width="90" height="90" /> </a></div>&nbsp;&nbsp;
<div class="shake"><a href=""><img src="images/xiyu.jpg" alt="" width="90" height="90" /> </a>     </div>&nbsp;&nbsp;
<div class="shake"><a href=""><img src="images/jingji.jpg" alt="" width="90" height="90" /> </a>   </div>&nbsp;&nbsp;
<div class="shake"><a href=""><img src="images/yingyu.jpg" alt="" width="90" height="90" /> </a>   </div>&nbsp;&nbsp;
<div class="shake"><a href=""><img src="images/guanli.jpg" alt="" width="90" height="90" /> </a>   </div>&nbsp;&nbsp;
<div class="shake"><a href=""><img src="images/dongyu.jpg" alt="" width="90" height="90" /> </a>   </div>&nbsp;&nbsp;
<div class="shake"><a href=""><img src="images/jinrong.jpg" alt="" width="90" height="90" /> </a>  </div>&nbsp;&nbsp;
<div class="shake"><a href=""><img src="images/zhongwen.jpg" alt="" width="90" height="90" /> </a> </div>&nbsp;&nbsp;
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



	
    
    <!--流布局-->
     <div id="pic">
	<div id="ctr">
		<div class="box photo col3">
			<img src="images/1.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/2.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>

		<div class="box photo col3">
			<img src="images/3.jpg" alt="Stanley" />
		<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/4.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/5.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/6.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/7.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/8.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/9.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div class="box photo col3">
			<img src="images/10.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>

		<div class="box photo col3">
			<img src="images/11.jpg" alt="Stanley" />
			<div class="likes_icon"><div class="like_number">(9999+)</div></div>
		</div>
		<div style="clear:both; height: 40px"></div>
	</div></div>
	<!-- END container -->

	<jsp:include page="User_login.jsp" />
	<jsp:include page="User_register.jsp" />
	
 <script src="js/jquery.min.js"></script>
        <script src="js/jquery.skippr.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.animate-colors-min.js"></script>

<script type="text/javascript" src="js/jquery.masonry.min.js"></script>
<script>
    $(function(){
    var $ctr = $('#ctr');
    $ctr.imagesLoaded( function(){
      $ctr.masonry({
        itemSelector : '.box',
		isFitWidth: true,
		isAnimated: true
      });
    });
  
  });</script>
      <script>
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
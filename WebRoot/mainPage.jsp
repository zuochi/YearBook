<%@ page import="bean.User"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/style.css" rel="stylesheet" type="text/css" />
<link href="styles/csshake.css" rel="stylesheet" type="text/css"/>
<link href="styles/skitter.styles.css" media="all"  rel="stylesheet" type="text/css" />
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
	<div id="s">
	<div id="slider-container">
		<div class="box_skitter box_skitter_large">
			<ul>
				<li><a href=""><img src="images/slide1.jpg" alt=""
							width="1160" height="652" /> </a>
					<div class="label_text">
						<h3 class="colortext uppercase">Boys, be ambitious</h3>
						<span>Man can survive, only when he has the substance,
						 man can live only when he has ideal.</span>
					</div></li>
				<li><a href=""><img src="images/slide2.jpg" alt=""
							width="1160" height="652" /> </a>
					<div class="label_text">
						<h3 class="colortext uppercase">Wish you happiness forever!</h3>
						<span>Wherever you go， no matter what the weather is like， always bring your own sunshine.</span>
					</div></li>
				<li><a href=""><img src="images/slide3.jpg" alt=""
						width="1160" height="652" /> </a>
					<div class="label_text">
						<h3 class="colortext uppercase"> Friendship is always in my heart.</h3>
						<span>Time does not water down the wine of friendship； distance does not separate our hands of longing.</span>
					</div></li>
                    <li><a href=""><img src="images/slide4.jpg" alt=""
						width="1160" height="652" /> </a>
					<div class="label_text">
						<h3 class="colortext uppercase"> A friend is, as it were, a second self.</h3>
						<span>There is a friend that sticks closer than a brother. </span></div></li>
			</ul>
		</div> <div id="shadow-img-slider"></div>
	</div>
</div>
<div id="kong"></div>
<div id="kong1"></div>
	<!-- end #slider-container -->

	<!-- end #header -->


	<!--各系-->
<div id="f"></div>
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

	
    
    <!--流布局-->
     <div id="pic">
	<div id="ctr">
		<div class="box photo col3">
			<img src="images/1.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>
		<div class="box photo col3">
			<img src="images/2.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>

		<div class="box photo col3">
			<img src="images/3.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>
		<div class="box photo col3">
			<img src="images/4.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>
		<div class="box photo col3">
			<img src="images/5.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>
		<div class="box photo col3">
			<img src="images/6.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>
		<div class="box photo col3">
			<img src="images/7.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>
		<div class="box photo col3">
			<img src="images/8.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>
		<div class="box photo col3">
			<img src="images/9.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>
		<div class="box photo col3">
			<img src="images/10.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>

		<div class="box photo col3">
			<img src="images/11.jpg" alt="Stanley" />
			<h3>Studio Photography</h3>
		</div>
		<div style="clear:both; height: 40px"></div>
	</div></div>
	<!-- END container -->

	<jsp:include page="User_login.jsp" />
	<jsp:include page="User_register.jsp" />
	
	<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.animate-colors-min.js"></script>
<script type="text/javascript" src="js/jquery.skitter.js"></script>
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
</body>
</html>
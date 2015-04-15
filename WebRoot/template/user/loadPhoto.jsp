<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
ul {
	text-align: center;
	list-style-type:none;
	height: 20px;
} /* 即使没有菜单项也能保持顶级菜单栏的高度。 */

ul>li {
	text-align: center;
	display: inline-block;
	width: 100px;
}
</style>
<br>
<div id="pic">
	<div id="ctr">
		<!-- <div class="box photo col3">
			<img src="images/1.jpg" alt="Like" />
		   	<div class="likes_icon"><div class="like_number">(9999+)</div>
		   </div></div>
		<div class="box photo col3">
		<div class="like_user_name">Kass</div><div class="top_time">2015/12/31</div>
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
		<div style="clear:both; height: 40px"></div> -->
	</div>
	<!-- <div id="loadingSpace" style="display:none;height:1000px"></div> -->
</div>
<input id="toPage" type="hidden"/>
<script type="text/javascript" src="js/user/calculateDistanceTime.js"></script>
<script type="text/javascript" src="js/user/loadPhoto.js"></script>

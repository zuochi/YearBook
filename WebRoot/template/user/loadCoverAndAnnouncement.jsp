<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="hero">
	<div id="random">
	<%-- 
		 <div style="background-image: url(images/slide1.jpg)">
			<div class="tagline">
		    	<h3>Welcome1:</h3>
		    	<p>Show your Pic here <br> Click the top nearby your photos.</p>
		    </div> 
	    </div>
	    
	    <div style="background-image: url(images/slide2.jpg)"> 
	    	<div class="tagline">
	    		<h3>Welcome2:</h3>
	    		<p>Show your Pic here <br> Click the top nearby your photos.</p>
			</div> 
	    </div> 
	    
	    <div style="background-image: url(images/slide3.jpg)"></div>                   
	    <div style="background-image: url(images/slide4.jpg)"></div> --%>
	</div>
 </div>
 
<script type="text/javascript">
//读取专业
function loadAnnouncementAndCover(){
	$.ajax({
		type: "POST",
		url: "user/getIndexAnnouncementAndCover_execute",
		data: "",
		dataType: "json",
		success: function(json){
			if(json.error == undefined){
				console.log(json);
				for(var i=0 ; i<json[0].objs.length ; i++){
					$("#random").append(
						"<div style='background-image: url("+json[0].objs[i].i_want_top.url+")'>"+
				    		"<div class='tagline'>"+
			    				"<h3>"+json[0].objs[i].title+"</h3>"+
			    				"<p>"+json[0].objs[i].context+"</p>"+
							"</div>"+
			    		"</div>");
				}
			}
			//滚动图		
			$("#random").skippr();
			$("#random2").skippr({
				navType: 'bubble',
				autoPlay: true,
				autoPlayDuration: 2000
			});
		}
	}); 
};
</script>
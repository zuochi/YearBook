<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="top">
<form action="user/search_execute" method="post" id="form">
	<input type="text" name="keyword" value="${keyword}" onkeyup="enterSearch()" style="font-size:14px; margin-left:30px;">
	<input type="submit" value="S" style="font-size:18px">earch
</form>
</div>
<script type="text/javascript">
function enterSearch(){
	if(window.event.keyCode==13){
		window.form.submit();	
	}
};
</script>
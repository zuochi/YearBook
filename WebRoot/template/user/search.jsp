<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="top">
	<input type="text" id="keyword" name="keyword" value="${keyword}" onkeyup="enterDeal()" style="font-size:14px; margin-left:30px;">
	<input type="submit" value="S" style="font-size:18px">earch
</div>
<script type="text/javascript">
function enterDeal(){
	if(window.event.keyCode==13){
		window.form.submit();	
	}
};
</script>
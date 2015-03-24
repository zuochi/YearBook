<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input id="orderBy" type="hidden" name="orderBy" value="${orderBy}">
<input id="desc" type="hidden" name="desc" value="${desc}">
<script type="text/javascript">
function orderBy(key){
	document.getElementById("orderBy").value = key;
	
	var desc = document.getElementById("desc").value;
	
	if(desc=="desc"){
		document.getElementById("desc").value = "";
	}else{
		document.getElementById("desc").value = "desc";
	}
	
	window.form.submit();
};
</script>
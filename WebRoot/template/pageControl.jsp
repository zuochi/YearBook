<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
ul {
	list-style-type:none;
	height: 20px;
} /* 即使没有菜单项也能保持顶级菜单栏的高度。 */

ul>li {
	text-align: center;
	display: inline-block;
	width: 100px;
}
</style>
<input type="hidden" id="toPage" name="toPage" value="1">
<div align="center">
	<ul>
		<li>${pc.currentPage}/${pc.totalPages}</li>
		<c:choose>
			<c:when test="${pc.hasPrevious==true}">
				<li><a href="javascript:goToPage(1)">First</a></li>
				<li><a href="javascript:goToPage(${pc.previousPage})">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li>First</li>
				<li>Previous</li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pc.hasNext==true}">
				<li><a href="javascript:goToPage(${pc.nextPage})">Next</a></li>
				<li><a href="javascript:goToPage(${pc.totalPages})">Last</a></li>
			</c:when>
			<c:otherwise>
				<li>Next</li>
				<li>Last</li>
			</c:otherwise>
		</c:choose>
		<li>
			Lift:<select id="changePage" onchange="goToPage(this.value)">
				<c:forEach var="index" begin="1" end="${pc.totalPages}">
					<c:choose>
						<c:when test="${index==pc.currentPage}">
							<option selected="selected">${index}</option>
						</c:when>
						<c:otherwise>
							<option>${index}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</li>
		<li>
			 Size:<input style="width:30px" maxlength="2" name="pageSize" value="${pageSize}">
		</li>
		<li>
			<input type="submit" value="Set Page Size">
		</li>
	</ul>
</div>
<script type="text/javascript">
	function goToPage(toPage){
		document.getElementById("toPage").value = toPage;
		window.form.submit();
	};
</script>
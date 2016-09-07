<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>collet disk info</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function formSubmit() {
		document.getElementById("myForm").submit()
	}
</script>
</head>
<body>
	<form id="myForm" action="<%=basePath%>showMemInfo" method="post">
		<table border="1">
			<tr>
				<th>id</th>
				<th>filesystem</th>
				<th>size</th>
				<th>used</th>
				<th>avail</th>
				<th>useage</th>
				<th>mountedon</th>
				<th>operatingtime</th>
			</tr>
			<c:forEach var="item" items="${diskInfo}">
				<tr>
					<td>${item.id}</td>
					<td>${item.filesystem}</td>
					<td>${item.size}</td>
					<td>${item.used}</td>
					<td>${item.avail}</td>
					<td>${item.useage}</td>
					<td>${item.mountedon}</td>
					<td>${item.operatingtime}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<input type="button" onclick="formSubmit()" value="获取最新磁盘信息">
</body>
</html>
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
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Collect system information</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Dashboard</a></li>
				<li><a href="#">Settings</a></li>
				<li><a href="#">Author</a></li>
				<li><a href="#">Help</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>

		<div class="jumbotron">
			<h1 class="text-center">welcome</h1>
			<p></p>
			<p class="text-center">
				<a class="btn btn-primary btn-lg" href="#" role="button">Learn
					more</a>
			</p>
		</div>

		<div class="row">
			<div class="col-md-6" >
				<h3>memory status:</h3>
				<form id="myForm" action="<%=basePath%>showInfo" method="post">
					<table border="1" class="table table-striped">
						<tr>
							<th>id</th>
							<th>total</th>
							<th>used</th>
							<th>avail</th>
							<th>useage</th>
							<th>operatingtime</th>
						</tr>
						<tr>
							<td>${memInfo.id}</td>
							<td>${memInfo.totalmem}</td>
							<td>${memInfo.usedmem}</td>
							<td>${memInfo.freemem}</td>
							<td>${memInfo.useage}</td>
							<td>${memInfo.operatingtime}</td>
						</tr>
					</table>
				</form>
			</div>
			
			<div class="col-md-6" id="mysecondform">
				<h3>hard disk status:</h3>
				<form id="myForm">
					<table class="table table-striped">
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
			</div>
			<button onclick="formSubmit()" class="btn btn-primary">get the latest status information</button>
		</div>
	</div>
	</nav>
</body>
</html>
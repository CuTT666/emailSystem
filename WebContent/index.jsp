<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript">
	function receiveEmail(account) {
		window.location.href = "emailServlet?op=showReceiveEmail&account="
				+ account + "";
	}
	function sendEmail() {
		window.location.href = "emailServlet?op=sendUIEmail";
	}
	function draftEmail(account) {
		window.location.href = "emailServlet?op=showDraftEmail&account="
				+ account + "";
	}
	function outBox(account) {
		window.location.href = "emailServlet?op=showOutboxEmail&account="
				+ account + "";
	}
</script>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp">主页</a></li>
				<!-- session中存的账号 -->
				<li><a href="#" onclick="receiveEmail('${ACCOUNT }')">收件箱 </a></li>
				<li><a href="#" onclick="outBox('${ACCOUNT }')">已发送</a></li>
				<li><a href="#" onclick="draftEmail('${ACCOUNT }')">草稿箱</a></li>
				<li><a href="#">垃圾箱</a></li>
			</ul>
			<form class="navbar-form navbar-left" action="emailServlet?op=searchEmail" method="post">
				<div class="form-group">
					<input type="hidden" value="${ACCOUNT }" name="account">
					<input type="text" class="form-control" placeholder="请输入邮件名" name="search">
				</div>
				<button type="submit" class="btn btn-default">查询</button>
			</form>
			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown">
				<li><a href="#" onclick="sendEmail()">我要发邮件</a></li>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<center>
		<h1>你好！${USERNAME }</h1>
		<h2>你的邮箱账号：${ACCOUNT }</h2>

	</center>

	<footer class="navbar-fixed-bottom">
	<div class="container" align="center">邮箱系统3.5版本</div>
	<div class="container" align="center">第一小组</div>
	</footer>
</body>
</html>
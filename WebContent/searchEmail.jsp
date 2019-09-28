<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>草稿箱</title>
<script type="text/javascript">
	//收件箱
	function receiveEmail(account) {
		window.location.href = "emailServlet?op=showReceiveEmail&account="
				+ account + "";
	}
	//发邮件
	function sendEmail(account) {
		window.location.href = "emailServlet?op=showSendEmail&account="
				+ account + "";
	}
	//跳转发件箱
	function outBox(account) {
		window.location.href = "emailServlet?op=showOutboxEmail&account="
				+ account + "";
	}
	//修改草稿
	function update(emailId) {
		window.location.href = "emailServlet?op=updateUIDraftEmail&emailId="
				+ emailId + "";
	}
	//删除草稿
	function del(emailId) {
		window.location.href = "emailServlet?op=delDraftEmail&emailId="
				+ emailId + "";
	}
</script>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">主页</a></li>
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
				<li><a href="#">我要发邮件</a></li>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<div class="container">
		<h3 style="text-align: center">搜索结果</h3>
		<table border="1" class="table table-bordered table-hover">
			<thead>
				<tr class="success">
					<th>编号</th>
					<th>标题</th>
					<th>邮件内容</th>
					<th>时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${emailList}" var="email">
					<tr>
						<td>${email.email_id}</td>
						<td>${email.title}</td>
						<td>${email.content}</td>
						<td>${email.create_date}</td>
						<td><a class="btn btn-default btn-sm" href="#"
							onclick="update('${email.email_id}')">修改</a>&nbsp;<a
							class="btn btn-default btn-sm" href="#"
							onclick="del('${email.email_id}')">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
	<footer class="navbar-fixed-bottom">
	<div class="container" align="center">邮箱系统3.5版本</div>
	</footer>
</body>
</html>
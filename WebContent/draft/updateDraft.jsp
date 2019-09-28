<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
</head>
<body>
	<%-- 	<center>
		<h1>修改草稿</h1>
		<form class="form-horizontal" action="emailServlet?op=updateDraft"
			method="post">
			<input type="hidden" value="${email.email_id }" name="email_id">
			<div class="form-group">
				<label class="col-sm-4 control-label">标题</label>
				<div class="col-sm-3">
					<input type="text" name="title" class="form-control"
						value="${email.title }" id="title" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">内容</label>
				<div class="col-sm-3">
					<input type="text" name="content" class="form-control"
						value="${email.content }" id="content" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-3">
					<button type="submit" class="btn btn-info">修改</button>
				</div>
			</div>
		</form>
	</center> --%>
	<a href="index.jsp"><button class="btn btn-default">返回主页</button></a>
	<div class="container" style="width: 400px;">
		<h3 style="text-align: center">发邮件</h3>
		<form method="post" action="emailServlet?op=updateDraft">
			<input type="hidden" value="${email.email_id }" name="email_id">
			<div class="form-group">
				<label for="age">邮件标题</label> <input type="text"
					class="form-control" id="title" name="title"
					value="${email.title }" />
			</div>

			<div class="form-group">
				<label for="email">邮件内容</label>
				<textarea cols="50" rows="10" class="form-control" id="content"
					name="content" value="${email.content }">${email.content }</textarea>
			</div>

			<div class="form-group" style="text-align: center">

				<button class="btn btn-primary" type="submit">修改</button>


			</div>
		</form>

	</div>
</body>
</html>
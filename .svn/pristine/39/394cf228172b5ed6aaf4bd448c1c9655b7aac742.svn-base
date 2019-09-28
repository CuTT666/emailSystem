<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>发件箱</title>
<script type="text/javascript">
	function send() {
		//拿到用户输入的信息
		var receive_user_id = $("#receive_user_id").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var account = $("#account").val();
		//开始进入ajax（服务器必须返回内容）

		$.ajax({
			//设置键值对
			type : "post",//设置请求方式
			url : "emailServlet?op=sendEmail", //设置请求的url
			data : {
				receive_user_id : receive_user_id,
				title : title,
				content : content,
				account : account
			},//设置数据传入服务器
			dataType : "text",//设置返回回来类型
			//成功的回调函数(请求成功) -->result就是服务器返回回来的数据
			success : function(result) {
				//alert(result);
				if (result == "YES") {
					window.location.href = "loginServlet?op=index";
				} else if (result == "NO") {
					$("#fo").html("收件人id有误")
				} else {
					$("#fo").html("内容不能为空！")
				}

			},
			//失败的回调函数
			error : function() {
				alert("ajax请求失败！")
			}
		})
	}
</script>
</head>
<body>
	<a href="index.jsp"><button class="btn btn-default">返回主页</button></a>
	<div class="container" style="width: 400px;">
		<h3 style="text-align: center">发邮件</h3>
		<font color="red">${info }</font> <font color="red" id="fo"></font>
		<form method="post" action="emailServlet?op=sendEmail">
			<input type="hidden" value="${ACCOUNT }" name="account" id="account">
			<div class="form-group">
				<label for="name">收件人邮箱</label> <input type="text"
					class="form-control" id="receive_user_id" name="receive_user_id"
					placeholder="请输入收件人邮箱" />
			</div>

			<div class="form-group">
				<label for="age">邮件标题</label> <input type="text"
					class="form-control" id="title" name="title" placeholder="请输入邮件标题" />
			</div>

			<div class="form-group">
				<label for="email">邮件内容</label>
				<textarea cols="50" rows="10" class="form-control" id="content"
					name="content" placeholder="请输入邮件内容"></textarea>
				<!--  <input type="text" id="email" class="form-control" name="content" placeholder="请输入邮件内容"/> -->
			</div>

			<div class="form-group" style="text-align: center">

				<button class="btn btn-primary" type="button" onclick="send()">发送</button>


			</div>
		</form>

	</div>
</body>
</html>
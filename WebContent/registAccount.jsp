<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function regist() {
		//拿到用户输入的信息
		var username = $("#username").val();
		var account = $("#account").val();
		var password = $("#password").val();
		var password2 = $("#password2").val();
		//alert(check);

		//开始进入ajax（服务器必须返回内容）

		$.ajax({
			//设置键值对
			type : "post",//设置请求方式
			url : "loginServlet?op=regist1", //设置请求的url
			data : {
				username : username,
				account : account,
				password : password,
				password2 : password2
			},//设置数据传入服务器
			dataType : "text",//设置返回回来类型
			//成功的回调函数(请求成功) -->result就是服务器返回回来的数据
			success : function(result) {
				//alert(result);
				//$("#username").val("孙悟空");
				if (result == "YES") {
					window.location.href = "loginServlet?op=loginDo";
				} else if (result == "NO") {
					$("#fo").html("两次输入的密码不一样,请重新确认密码！")
				} else {
					$("#fo").html("账号或者密码不能为空！")
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
	<div class="container" style="width: 400px;">
		<h3 style="text-align: center">注册账号</h3>
		<form method="post" action="loginServlet?op=regist1">
			<font color="red">${info }</font> <font color="red" id="fo"></font>
			<div class="form-group">
				<label for="name">姓名</label> <input type="text" class="form-control"
					id="username" name="username" placeholder="请输入姓名" />
			</div>
			<div class="form-group">
				<label for="name">账号</label> <input type="text" class="form-control"
					id="account" name="account" placeholder="请输入账号" />
			</div>

			<div class="form-group">
				<label for="age">密码</label> <input type="password"
					class="form-control" id="password" name="password"
					placeholder="请输入密码" />
			</div>
			<div class="form-group">
				<label for="email">确认密码</label> <input type="password"
					id="password2" class="form-control" name="password2"
					placeholder="请再次输入密码" />
			</div>

			<div class="form-group" style="text-align: center">
				<button class="btn btn-primary" type="button" onclick="regist()">注册</button>

			</div>
		</form>
	</div>
</body>
</html>
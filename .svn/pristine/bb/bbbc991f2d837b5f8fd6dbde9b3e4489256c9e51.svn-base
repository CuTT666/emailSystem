<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/LoginPage.css" />
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	function regist() {
		window.location.href = "loginServlet?op=registAccount";
	}
	function login() {
		//拿到用户输入的信息
		var account = $("#inputEmail").val();
		var password = $("#inputPassword").val();
		//拿到复选框的状态  prop函数是来得到属性的值
		var check = $("#checkbox").prop("checked");
		//alert(check);
		//alert(typeof(check))
		var checkState = "NO";
		if (check) {
			checkState = "YES";
		}

		//开始进入ajax（服务器必须返回内容）

		$.ajax({
			//设置键值对
			type : "post",//设置请求方式
			url : "loginServlet?op=checkLogin", //设置请求的url
			data : {
				account : account,
				password : password,
				checkState : checkState
			},//设置数据传入服务器
			dataType : "text",//设置返回回来类型
			//成功的回调函数(请求成功) -->result就是服务器返回回来的数据
			success : function(result) {
				//alert(result);
				//$("#username").val("孙悟空");
				if (result == "YES") {
					window.location.href = "loginServlet?op=index";
				} else if (result == "NO") {
					$("#fo").html("账号或者密码错误！")
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

	//文档加载事件（查看Cookie）
	$(function() {
		//访问服务器，查看是否 有指定的Cookie存在
		$.ajax({
			//设置键值对
			type : "post",//设置请求方式
			url : "loginServlet?op=searchCookie", //设置请求的url
			data : {

			},//设置数据传入服务器
			dataType : "text",//设置返回回来类型
			//成功的回调函数(请求成功) -->result就是服务器返回回来的数据
			success : function(result) {
				//alert(result);
				//将Json字符串转化成Json对象
				var user = JSON.parse(result);
				//alert(user.username)
				//将信息写到输入框
				$("#inputEmail").val(user.account);
				$("#inputPassword").val(user.password);
				$("#checkbox").prop("checked", "checked");

			},
			//失败的回调函数
			error : function() {
				alert("ajax请求失败！")
			}
		})

	})
</script>
</head>


<body>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<form class="form-horizontal" action="userServlet?op=checkLogin"
					method="post">
					<span class="heading">用户登录</span> <font color="red">${info }</font>
					<font color="red" id="fo"></font>
					<div class="form-group">
						<input type="text" class="form-control" id="inputEmail"
							placeholder="用 户 名"> <i class="fa fa-user"></i>
					</div>
					<div class="form-group help">
						<input type="password" class="form-control" id="inputPassword"
							placeholder="密　码"> <i class="fa fa-lock"></i> <a href="#"
							class="fa fa-question-circle"></a>
					</div>
					<%-- <div class="form-group">
						验证码:<input type="text" name="checkCode" /> <img alt="验证码"
							id="imagecode"
							src="<%=request.getContextPath()%>/servlet/ImageServlet" /> <a
							href="javascript:reloadCode();">看不清楚</a><br>
					</div> --%>
					<div class="form-group">
						<div class="main-checkbox">
							<input type="checkbox" value="None" id="checkbox" name="check" />
							<label for="checkbox"></label>
						</div>
						<span class="text">记住密码</span>
						<button type="button" class="btn btn-default" onclick="regist()">注册</button>
						<button type="button" class="btn btn-default" onclick="login()">登录</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收件箱</title>


<script type="text/javascript">
	//删除收件箱
	function del(emailId) {
		window.location.href = "emailServlet?op=delReceiveEmail&emailId="
				+ emailId + "";
	}
	//发邮件
	function sendEmail() {
		window.location.href = "emailServlet?op=sendUIEmail";
	}
	//展示收信箱
	function receiveEmail(account,pageNow) {
		window.location.href="emailServlet?op=showReceiveEmail&account="+account+"&pageNow="+pageNow+"";
	}
	//跳转草稿
	function draftEmail(account) {
		window.location.href="emailServlet?op=showDraftEmail&account="+account+"";
	}
	//跳转发件箱
	function outBox(account) {
		window.location.href="emailServlet?op=showOutboxEmail&account="+account+"";
	}
	
	function beforePage(account,pageNow) {
		//将上一页下一页的button的参数重新设置一下
		//给定一个变量来代表是ajax请求
		
		var requestWay = "ajax";
		$.ajax({
			type:"post",
			url:"emailServlet?op=showReceiveEmail",
			data:{
				pageNow:pageNow,
				account:account,
				rw:requestWay       //rw代表是ajax请求
				},
			dataType:"JSON",
			success:function(result){
				//alert(result);
				//将json字符串转换成JS数组
				var emailArray = eval(result);
				var tbObject = $("#tb")
				tbObject.empty();
				$(emailArray).each(function(i,m) {
					//得到表格jquery对象
					tbObject.append("<tr>"
							+"<td>"+m.email_id+"</td>"
							+"<td>"+m.sender_user_id+"</td>"
							+"<td>"+m.title+"</td>"
							+"<td>"+m.content+"</td>"
							+"<td>"+m.create_date+"</td>"
							+"<td> <button  type='button' class='btn btn-default' onclick=del('"+m.email_id +"')>删除</button> </td>"
							+"</tr>"
							)
					
				})
				
				//表格刷新完以后，还要刷新上一页标签
				var div1 = $("#div1");
				div1.empty();
				if(pageNow!=1){
					 div1.append("<button type='button'  class='btn btn-primary' onclick=beforePage("+${ACCOUNT }+","+(pageNow-1)+")>上一页</button>") 
					/* div1.append("<li><a href="#" aria-label="Previous" onclick=beforePage("+${ACCOUNT }+","+(pageNow-1)+")> <span aria-hidden='true'>&laquo;</span></a></li>") */
				}
				
				var div2 = $("#div2");
				
				div2.empty();
				if(pageNow>=1){
					 div2.append("<button type='button'  class='btn btn-primary' onclick=beforePage("+${ACCOUNT }+","+(pageNow+1)+")>下一页</button>") 
					/* div2.append("<li><a href="#" aria-label="Next" onclick=afterPage("+${ACCOUNT }+","+(pageNow+1)+")> <span aria-hidden='true'>&raquo;</span></a></li>")  */
				}
				
			},
			error:function(){
				alert("ajax请求失败")
			}
			
			
		})	
	}
	
	function afterPage(account,pageNow,pageCount) {
		//将上一页下一页的button的参数重新设置一下
		//给定一个变量来代表是ajax请求
		var requestWay = "ajax";
		$.ajax({
			type:"post",
			url:"emailServlet?op=showReceiveEmail",
			data:{
				account:account,
				pageNow:pageNow,
				rw:requestWay      //rw代表是ajax请求
				},
			dataType:"JSON",
			success:function(result){
				//alert(result);
				//将json字符串转换成JS数组
				var userArray = eval(result);
				var tbObject = $("#tb")
				tbObject.empty();
				$(userArray).each(function(i,m) {
					//alert(m.email_id)
					//得到表格jquery对象
					tbObject.append("<tr>"
							+"<td>"+m.email_id+"</td>"
							+"<td>"+m.sender_user_id+"</td>"
							+"<td>"+m.title+"</td>"
							+"<td>"+m.content+"</td>"
							+"<td>"+m.create_date+"</td>"
							+"<td> <button  type='button' class='btn btn-default' onclick=del('"+m.email_id +"')>删除</button> </td>"
							+"</tr>"
							)
					
				})
				//表格刷新完以后，还要刷新下一页标签
				var div2 = $("#div2");
				div2.empty();
			
				if(pageNow<pageCount){
					div2.append("<button type='button'  class='btn btn-primary' onclick=afterPage("+${ACCOUNT }+","+(pageNow+1)+","+pageCount+")>下一页</button>")
					/* div2.append("<li><a href='#' aria-label='Next' onclick=afterPage("+${ACCOUNT }+","+(pageNow+1)+","+pageCount+")> <span aria-hidden='true'>&raquo;</span></a></li>") */
				}
				var div1 = $("#div1");
				
				div1.empty();
				if(pageNow<=pageCount){
	          		div1.append("<button type='button'  class='btn btn-primary' onclick=beforePage("+${ACCOUNT }+","+(pageNow-1)+")>上一页</button>")
					/* div1.append("<li><a href='#' aria-label='Previous' onclick=beforePage("+${ACCOUNT }+","+(pageNow-1)+")> <span aria-hidden='true'>&laquo;</span></a></li>") */
				}
				
			},
			error:function(){
				alert("ajax请求失败")
			}
			
			
		})	
	}
	
	function hiddenPage(pageNow) {
		if(pageNow==1){
			var div = $("#div1");
			div.empty();
		}
	}
</script>
</head>
<body onload="hiddenPage(${pageNow})">
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">主页</a></li>
				<!-- session中存的账号 -->
				<li class="active"><a href="#">收件箱 </a></li>
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
	<div class="container">
		<h3 style="text-align: center">收件箱</h3>
		<table border="1" class="table table-bordered table-hover" id="tab">
			<thead>
				<tr class="success">
					<th>编号</th>
					<th>发件人邮箱</th>
					<th>标题</th>
					<th>邮件内容</th>
					<th>收件时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tb">
				<c:forEach items="${emailList}" var="email">
					<tr>
						<td>${email.email_id}</td>
						<td>${email.sender_user_id}</td>
						<td>${email.title}</td>
						<td>${email.content}</td>
						<td>${email.create_date}</td>
						<td>&nbsp;<a class="btn btn-default btn-sm" href="#"
							onclick="del('${email.email_id}')">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<center>
		<div id="div1">

			<button type="button" class="btn btn-primary"
				onclick="beforePage('${ACCOUNT }',${pageNow}-1)">上一页</button>
		</div>


		<!--用JSTL标签加EL表达式完成类似java里面的for循环  -->
		<c:forEach var="i" begin="1" end="${pageCount }">
			<button type="button" class="btn btn-primary"
				onclick="receiveEmail('${ACCOUNT }',${i},${pageCount })">[${i}]</button>
		</c:forEach>

		<c:if test="${pageNow!=pageCount }">
			<div id="div2">
				<button type="button" class="btn btn-primary"
					onclick="afterPage('${ACCOUNT }',${pageNow}+1,${pageCount })">下一页</button>
			</div>
		</c:if>

	<%-- 	<nav aria-label="Page navigation">
		<ul class="pagination pagination-lg">

			<li id="div1"><a href="#" aria-label="Previous" onclick="beforePage('${ACCOUNT }',${pageNow}-1)"> <span
					aria-hidden="true">&laquo;</span>
			</a></li>

			<c:forEach var="i" begin="1" end="${pageCount }">
				<li><a href="#" onclick="receiveEmail('${ACCOUNT }',${i},${pageCount })">[${i}]</a></li>
			</c:forEach>

			<c:if test="${pageNow!=pageCount }">
				<li id="div2"><a href="#" aria-label="Next" onclick="afterPage('${ACCOUNT }',${pageNow}+1,${pageCount })">
						<span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>
		</ul>
		</nav> --%>
	</center>
</body>

<footer class="navbar-fixed-bottom">
<div class="container" align="center">邮箱系统3.5版本</div>
</footer>
</html>
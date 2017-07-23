<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'forget.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="jquery/jquery-2.2.0.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
.txt {
	width: 300px;
	height: 30px;
	line-height: 30px;
}

.item {
	width: 300px;
}
</style>
</head>

<body>
	<img style="width:15em;" src="assets/logo.png">
	<p>修改密码</p>
	<hr>
	<div align="center">
		<form name="formone" action="" method="post">
			<table>
				<tr>
					<td><label id="item">用户名&nbsp;</label></td>
					<td><input type="text" name="username" id="username"
						class="txt">第一步</td><td ><input type="submit" value="确定" onclick="doone(event)"></td>
				</tr>
				<tr>
					<td><label id="item">安全问题</label>
					</td>
					<td><input type="text" name="question" id="question"
						class="txt">
					</td>
				</tr>
				<tr>
					<td><label id="item">答&nbsp;&nbsp;案</label></td>
					<td><input type="text" name="answer" id="answer" class="txt">第二步&nbsp;<p id="s" ></p></td><td><input type="submit" value="确定" onclick="dotwo(event)">
					</td>
				</tr>
				<tr>
					<td><label id="item">修改密码</label>
					</td>
					<td><input type="password" name="password" id="password"
						class="txt">第三步</td>
				</tr>				
			</table>
			<input type="submit" value="确定" onclick="dothree(event)">
		</form>
	</div>
	<script type="text/javascript">
		function doone(event) {
			event.preventDefault();
			$.ajax({
				url : 'userservlet/passwordfoget',
				type : 'POST',
				dataType : 'json',
				data : {
					code : '0',
					username : $("#username").val()
				},
				success : function(data) {
					console.log(data);
					$("#question").val(data[0].question);
				},
				error : function() {

				}
			});

		}
		function dotwo(event) {
			event.preventDefault();
			var data = {
				code : '0',
				username : $("#username").val(),
				answer : $("#answer").val()
			};
			var keyone = encodeURIComponent(JSON.stringify(data));
			$.ajax({
				url : 'userservlet/passwordfoget',
				type : 'GET',
				dataType : 'json',
				data : {
					keyone : keyone
				},

				success : function(data) {
					console.log(data);
					var o = new Boolean("false");
					o = data.success;
					if (o) {
						$("#s").append("答案正确");
						return true;
					} else {
						alert("答案不正确，请好好想想!!!");
						return false;
					}
				},
				error : function() {
					alert("页面错误");
				}
			});
		}
		function dothree(event) {
			event.preventDefault();
			$.ajax({
				url : 'userservlet/passwordfoget',
				type : 'POST',
				dataType : 'json',
				data : {

					code : '1',
					username : $("#username").val(),
					password : $("#password").val()
				},
				success : function(data) {
					alert("修改成功");
					location.href = "login.jsp";
				},
				error : function() {

				}
			});
		}
	</script>
</body>
</html>

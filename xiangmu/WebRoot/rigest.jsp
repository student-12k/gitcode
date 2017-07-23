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

<title>注册</title>

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
* {
	margin: 0;
	padding: 0;
}

h3 {
	color: blue;
	font-size: 30px;
}

.login {
	width: 120px;
	float: center;
	height: 40px;
	font: bold 32px;
	background: #69b946;
	color: #fff;
	border: none;
}

.reset {
	width: 120px;
	float: center;
	height: 40px;
	font: bold 32px;
	background: #69b946;
	color: #fff;
	border: none;
}

.item {
	width: 300px;
	float: left;
	text-align: right;
}

.tipbox {
	width: 400px;
	height: 30px;
	margin: 20px auto;
}

.txt {
	width: 300px;
	height: 30px;
	line-height: 30px;
	float: right;
}

#wrip {
	margin: 30px auto;
	width: 610px;
	font: 16px;
	background: #f8fdff;
}

body {
	background-position: center;
	background-repeat: no-repeat;
}
</style>
<script type="text/javascript">
	

	
</script>
</head>

<body>
	<div id="wrid">
		<img style="width:15em;" src="assets/logo.png">
		<h5>账户注册</h5>
		<br>
		<br>
		<br>
		<br>
		<div>
			<div class="tipbox">
				<label id="item">用户 名</label> <input type="text" name="username"
					id="username" class="txt" value="">
			</div>

			<div class="tipbox">
				<label id="item">密 码</label> <input type="password" name="password"
					id="password" class="txt" value="">
			</div>
			<div class="tipbox">
				<label id="item">确认密码</label> <input type="password"
					name="password_again" id="password_again" class="txt" value="">
			</div>


			<div class="tipbox">
				<label id="item">邮 箱</label> <input type="text" name="email"
					id="email" class="txt" value="">
			</div>

			<div class="tipbox">
				<label id="item">地 址</label> <input type="text" name="address"
					id="address" class="txt" value="">
			</div>

			<div class="tipbox">
				<label id="item">电 话</label> <input type="text" name="phonenumber"
					id="phonenumber" class="txt" value="">
			</div>

			<div class="tipbox">
				<label id="item">安全问题</label> <select class="txt" id="question"
					size="1">
					<option >你爸爸叫什么名字?</option>
					<option >你妈妈叫什么名字</option>
					<option >你叫什么名字？</option>
					<option >你高中老师是谁？</option>
					<option >你老婆是谁？</option>
				</select>

			</div>

			<div class="tipbox">
				<label id="item">答 案</label> <input type="text" name="answer"
					id="answer" class="txt" value="">
			</div>

			<div class="tipbox">
				<label id="item">个人爱好</label> <input type="text"
					name="personalpreferences" id="personalpreferences" class="txt"
					value="">
			</div>


			<br>
			<br>
			<p class="space" align="center">
				<input type="submit" value="提交" class="login"
					style="cursor:pointer; " onclick="checknull();"> <input
					type="button" name="button" class="reset" value="返回登录页" onclick="fanhui();"
					style="cursor:pointer;">
			</p>

		</div>
	</div>
	<script type="text/javascript">
	function checknull(){

var password=document.getElementById("password").value;
var password_again=document.getElementById("password_again").value;
if($("#username").val().length<=0){
document.getElementById("username").focus();
	return ;
}
if($("#password").val().length<=0){
document.getElementById("password").focus();
	return ;
}
if(password_again.length<=0){
document.getElementById("password_again").focus();
	return ;
}
if (password!=password_again) {
	alert("两次密码不同，请重新输入");
	return ;
}
if($("#email").val().length<=0){
document.getElementById("email").focus();
	return ;
}
if($("#address").val()==""){
document.getElementById("address").focus();
	return ;
}
if($("#phonenumber").val().length<=0){
document.getElementById("phonenumber").focus();
	return ;
}
	reg();
	}

	
	function reg(){
   var data={
   	            username:$("#username").val(),
		  		password:$("#password").val(),
		  			email:$("#email").val(),
		  			address:$("#address").val(),
		  				phonenumber:$("#phonenumber").val(),
		  				question:$("#question").val(),
		  				answer:$("#answer").val(),
		  				personalpreferences:$("#personalpreferences").val()
	};
	var addInfo = encodeURIComponent(JSON.stringify(data));
		$.ajax({
		  url: 'userservlet/dorigestservlet',
		  type: 'POST',
		  dataType:'json',
		  data: {
		     addkey :encodeURIComponent(JSON.stringify(data))
		  },
		  success: function(data) {	  
		   window.alert("恭喜您注册成功!!!!"); 
		   location.href="login.jsp";
		  },
		error: function() {
	  }
	  });
	  }
	 function fanhui(){
	 window.location.href="login.jsp";
	 }
	</script>

</body>
</html>

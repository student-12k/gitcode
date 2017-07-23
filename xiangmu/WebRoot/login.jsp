<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" href="css/login.css" rel="stylesheet" />	
	<link type="text/css" href="css/smoothness/jquery-ui-1.7.2.custom.html" rel="stylesheet" />
	<script type="text/javascript" src="js/easyTooltip.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js"></script>
    <script src="jquery/jquery-2.2.0.min.js" ></script>
    <style>
    a{
   align:center;
    }
    </style>
  </head>
  
  <body>
    <div id="container">
    <div class="logo">
<a href="#"><img src="assets/logo.png"  alt=""  /></a>
    </div>
    <div id="box">
    <form action="dologin.jsp" method="post">
    <p class="table">
    <label>用户名：</label>
    <input name="username" value=""/>
    <label>密码：</label>
    <input type="password" name="password" value=""/>
    <input type="submit" value="提交" style="margin:0px 20px;" />
     <a href="forget.jsp" >忘记密码</a>
    <a href="rigest.jsp" >未注册</a>
    </p>
    </form>
    </div>
    </div>
  </body>
</html>

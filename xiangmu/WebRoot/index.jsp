<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="open.Items" %>
<%@ page import="dao.ItemsDAO" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	  .item{
	      float:left;
	      margin: 10px;
	   } 
	   div dd{
	      margin:0px;
	      font-size:10pt;
	   }
	   div dd.dd_name
	   {
	      color:blue;
	   }
	   div dd.dd_city
	   {
	      color:#000;
	   }
	   h1{
	   color:blue;
	   }
	   .searchinput{
	border-right-width: 0px;
	padding-left: 3px;
	width: 50em;
	font-family: arial;
	float: left;
	border-top-width: 0px;
	border-bottom-width: 0px;
	color: #636365;
	margin-left: 4px;
	font-size: 8pt;
	vertical-align: middle;
	border-left-width: 0px;
	margin-right: 3px;
	height:40px
}
.searchaction{
	width: 40px;
	float: left;
	height: 40px;
	}
	.dropbtn {
    padding: 16px;
    border: none;
    cursor: pointer;
}
.dropdown {
    position: relative;
    display: inline-block;
}
.dropdown-content {
    display: none;
    position: absolute;
    right: 0;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}
.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}
.dropdown-content a:hover {background-color: #f1f1f1}
.dropdown:hover .dropdown-content {
    display: block;
}
	</style>
  </head>
  
  <body>
  <hr>
 <img style="width:16em;" src="assets/logo.png">
<div class="dropdown" style="float:right;">
  <img class="dropbtn" src="images/1.gif">
  <div class="dropdown-content" style="float:none;">
    <a href="login.jsp">登陆</a>
    <a href="index.jsp">首页</a>
    <a href="rigest.jsp">注册</a>
    <a href="forget.jsp">修改密码</a>
    <a href="cart.jsp">购物车</a>
    <a href="destorylogin.jsp">注销</a>
  </div>                      
  </div>
 <hr>
 <center>
 <div align="center">
 </div>
 <table  width="750" height="60" cellpadding="0" cellspacing="0" border="0">
<tr>
		<td>
			<input type="text" name="q" title="Search" class="searchinput" id="searchinput" onkeydown="if (event.keyCode==13) {}" onblur="if(this.value=='')value='Search Products';" onfocus="if(this.value=='Search Products')value='';" value="Search Products" size="10"/>
			<input type="image" width="21" height="17" class="searchaction" onclick="if(document.forms['search'].searchinput.value=='- Search Products -')document.forms['search'].searchinput.value='';" alt="Search" src="images/magglass.gif" border="0" hspace="2"/>
		</td>
	</tr>
 <tr>
 <td>
 <!-- 商品循环开始 -->
 <%
 ItemsDAO itemsdao=new ItemsDAO();
  ArrayList<Items> list = itemsdao.getAllItems();
  if(list!=null&&list.size()>0){
    for(int i=0;i<list.size();i++){
    Items item=list.get(i);
  %>
 <div class="item">
  <dl>
               <dt>
                 <a href="detail.jsp?id=<%=item.getId()%>"><img src="images/<%=item.getPicture()%>" width="120" height="90" border="1"/></a>
               </dt>
               <dd class="dd_name"><%=item.getName() %></dd> 
               <dd class="dd_city">产地:<%=item.getCity() %><br>价格:￥ <%=item.getPrice() %></dd> 
             </dl>
             </div>
             <!-- 商品循环结束 -->
             <%
             }
             }
              %>
   </td>
      </tr>
 </table>
 </center>
  </body>
</html>

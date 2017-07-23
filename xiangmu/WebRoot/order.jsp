<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="open.Items" %>
<%@ page import="open.Cart" %>
<%@ page import="com.dao.UsersDAO" %>
<%@ page import="com.po.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'order.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="jquery/order.js" ></script>
	<script src="jquery/jquery-2.2.0.min.js" ></script>
	
<style type="text/css">
	th{
		text-align: center;
		font-size: 1em;	
	}
	td{
	text-align: center;
	}
	div#box1{
		border-style:solid none;
		border-width: 2px;
		 
		 width: 820px;
		 margin:50px 200px;
	}
	div.box1{
		 font-size: 1em;
		 margin: 30px;
	}
    span.box{
    	font-size: 1em;
    	font-weight: bold;
    }
input.box1{
width: 80px;
height: 40px;
margin: 0px 350px;
border-radius: 10px;
font-weight: bold;
}
input.box{
border-radius: 5px;
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
    display: block;}
</style>
  </head>
  
  <body>
  <img style="width:15em;" src="assets/logo.png">
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
<form method="Post">
	<div id="box1">
	<span class="box">》》订单确定</span>
<br><br>
      <%Random random = new Random(); %>
	<span  class="box">订单号&nbsp;&nbsp;<span id="orderID"><%=Math.abs(random.nextInt())%></span> </span>
	<hr style="border: 1px dashed blue">
	
	<table style="width: 100%" id="myTable">
		<tr style="background-color: gray;border-color:gray" >
			<th>商品名</th>
			<th>商品单价</th>
			<th>购买数量</th>
			<th>小计</th>
		</tr>
		<tr>
		<% 
		if (session.getAttribute("loginUser")==null){
		 response.sendRedirect("login.jsp"); 
		}
				   //首先判断session中是否有购物车对象
				   if(request.getSession().getAttribute("cart")!=null)
				   {
				%>
				<!-- 循环的开始 -->
				 <% 
				         Cart cart = (Cart)request.getSession().getAttribute("cart");
				         HashMap<Items,Integer> goods = cart.getGoods();
				        Set<Items> items = goods.keySet();
				         Iterator<Items> it = items.iterator();
				         
				        while(it.hasNext())
				         {
				            Items i = it.next();  
				     %> 
		<td><a href="detail.jsp?id=<%=i.getId()%>"><%=i.getName()%></a></td>
					<td ><%=i.getPrice() %></td>
					<td class="number"><%=goods.get(i)%></td>
					<td class="price" id="price_id_1">
						<span><%=i.getPrice()*goods.get(i) %></span>
					</td>               
		</tr>
		 <% 
				         }
				     %>       
		<tr>
		<td></td>
		<td></td>
		<td></td>
		<td style="text-align: center;font-size: 10px">总价<%=cart.getTotalprice() %>￥</td>
		  <% 
			    }
			 %>
		</tr>
	</table>
	
	<span class="box">我的信息</span>
	<hr style="border: 1px dashed blue">
	<%   if(session.getAttribute("loginUser")!=null)
				   {
				   String username=session.getAttribute("loginUser").toString();
				   System.out.println(username);
				    UsersDAO usersDao=new UsersDAO();
	    	        User u=new User();
	    	        u=usersDao.get(username);
				   %>
				    
	<div class="box1">
		收货人姓名&nbsp;<input type="text" class="box" id="username" value="<%=u.getUsername() %>"><span style="font-size:5px;">可修改</span>
		<br>
		收货人地址&nbsp;<input type="text" class="box" id="address" value="<%=u.getAddress() %>"><span style="font-size:5px;">可修改</span>
		<br>
	收货人电话&nbsp;<input type="text" class="box" id="phone" value="<%=u.getPhonenumber() %>"><span style="font-size:5px;">可修改</span>
		<% 
         } 
				     %>
		<br>
	<label >支付方式&nbsp;&nbsp;
		<select style="width:153px;border-radius: 5px;" id="payway">
		<option ></option>
		<option >支付宝</option>
		<option >微信</option>
		<option>货到付款</option>
		</select>
		</label>	
	</div>
	
	<div><input type="button" class="box1" value="付款" onclick="addOrder();"></div>
	</div>
	</form>
</body>
</html>

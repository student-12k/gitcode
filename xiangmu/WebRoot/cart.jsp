<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="open.Items" %>
<%@ page import="open.Cart" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>购物车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	   <link type="text/css" rel="stylesheet" href="css/style1.css" />
	   <script type="text/javascript" src="js/lhgcore.js"></script>
       <script type="text/javascript" src="js/lhgdialog.js"></script>
<script type="javascript">
   function delcfm() {
	        if (!confirm("确认要删除？")) {
	            window.event.returnValue = false;
	        }
	    }
</script>
<style>
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
    display: block;}</style>

  </head>
  <img style="width:16em;" src="assets/logo.png">
    <div class="dropdown" style="float:right;">
  <img class="dropbtn" style="width:15px;height:15px;" src="images/1.gif">
  <div class="dropdown-content" >
    <a href="login.jsp">登陆</a>
    <a href="index.jsp">首页</a>
    <a href="rigest.jsp">注册</a>
    <a href="forget.jsp">修改密码</a>
    <a href="cart.jsp">购物车</a>
    <a href="destorylogin.jsp">注销</a>
  </div>                      
  </div>
  <br>
  <h1>我的购物车</h1>
     <a href="index.jsp">首页</a>  <a href="index.jsp">商品列表</a>
  <hr>
  <body>
   <div id="shopping">
   <form action="" method="Post">		
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品单价</th>
					<th>购买数量</th>
					<th>总价</th>
					<th>操作</th>
				</tr>
				<% 
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
				   
				<tr class="products" id="product_id_1">
					<td class="thumb"><img src="images/<%=i.getPicture()%>" /><a href=""><%=i.getName()%></a></td>
					<td class="number"><%=i.getPrice() %></td>
					<td class="number"><%=goods.get(i)%></td>
					<td class="price" id="price_id_1">
						<span><%=i.getPrice()*goods.get(i) %></span>
						<input type="hidden" value="" />
					</td>                        
                    <td class="delete">
					  <a href="itemservlet/Cartservlet?action=delete&id=<%=i.getId()%>" >删除</a>					                  
					</td>
				</tr>
				     <% 
				         }
				     %>
				<!--循环的结束-->
				
			</table>
			 <div class="total"><span id="total">总计：<%=cart.getTotalprice() %>￥</span></div>
			  <% 
			    }
			 %>
		</form>
		<div class="submit" ><a href="./order.jsp"><button style="width:60px;height:30px" >提交</button></a></div>	
	</div>
  </body>
</html>

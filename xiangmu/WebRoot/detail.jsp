<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ page import="dao.ItemsDAO"%>
<%@ page import="open.Items"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'details.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/lhgcore.js"></script>
<script type="text/javascript" src="js/lhgdialog.js"></script>
<script type="text/javascript">
	function selflog_show(id){
	var num=document.getElementById("number").value;
	 J.dialog.get({id: 'haoyue_creat',title: '成功加入购物车',width: 600,height:400, link: '<%=path%>/itemservlet/Cartservlet?id=' + id + '&num=' + num
					+ '&action=add',
			cover : true
		});
		//alert("成功加入购物车");
	}
	function add() {
		var num = parseInt(document.getElementById("number").value);
		if (num < 100) {
			document.getElementById("number").value = ++num;
		}
	}
	function sub() {
		var num = parseInt(document.getElementById("number").value);
		if (num > 1) {
			document.getElementById("number").value = --num;
		}
	}
</script>
<style type="text/css">
#item {
	float: left;
	margin-left: 30px;
	margin-right: 30px;
	margin-top: 5px;
	margin-bottom: 5px;
}

div dd {
	margin: 0px;
	font-size: 10pt;
}

div dd.dd_name {
	color: blue;
}

div dd.dd_city {
	color: #000;
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
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #f1f1f1
}

.dropdown:hover .dropdown-content {
	display: block;
}
</style>
</head>

<body>
	<img style="width:15em;" src="assets/logo.png">
	<div class="dropdown" style="float:right;">
		<img class="dropbtn" src="images/1.gif">
		<div class="dropdown-content" style="float:none;">
			<a href="login.jsp">登陆</a> <a href="index.jsp">首页</a> <a
				href="rigest.jsp">注册</a> <a href="forget.jsp">修改密码</a> <a
				href="cart.jsp">购物车</a>    <a href="destorylogin.jsp">注销</a>
		</div>
	</div>
	<hr>
	<center>
		<table width="750" height="60" cellpadding="0" cellspacing="0"
			border="0">
			<tr>
				<!-- 商品详情 -->
				<%
					ItemsDAO itemDao = new ItemsDAO();
					Items item = itemDao.detailbyid(Integer.parseInt(request
							.getParameter("id")));
					if (item != null) {
				%>
				<td width="70%" valign="top">
					<table>
						<tr>
							<td rowspan="4"><img src="images/<%=item.getPicture()%>"
								width="200" height="160" />
							</td>
						</tr>
						<tr>
							<td><B><%=item.getName()%></B>
							</td>
						</tr>
						<tr>
							<td>产地：<%=item.getCity()%></td>
						</tr>
						<tr>
							<td>价格：<%=item.getPrice()%>￥</td>
						</tr>
						<tr>
							<td>购买数量：<span id="sub" onclick="sub();">-</span><input
								type="text" id="number" name="number" value="1" size="2" /><span
								id="add" onclick="add();">+</span>
							</td>
						</tr>
					</table>
					<div id="cart">
						<a href="javascript:selflog_show(<%=item.getId()%>)"><img
							src="images/1/in_cart.png">
						</a><a href="itemservlet/Cartservlet?action=show"><img
							src="images/1/view_cart.jpg" />
						</a>
					</div></td>
				<%
					}
				%>
				<%
					String list = "";
					//从客户端获得Cookies集合
					Cookie[] cookies = request.getCookies();
					//遍历这个Cookies集合
					if (cookies != null && cookies.length > 0) {
						for (Cookie c : cookies) {
							if (c.getName().equals("ListViewCookie")) {
								list = c.getValue();
							}
						}
					}

					list += request.getParameter("id") + ",";
					//如果浏览记录超过1000条，清零.
					String[] arr = list.split(",");
					if (arr != null && arr.length > 0) {
						if (arr.length >= 1000) {
							list = "";
						}
					}
					Cookie cookie = new Cookie("ListViewCookie", list);
					response.addCookie(cookie);
				%>
				<!-- 浏览过的商品 -->
				<td width="30%" bgcolor="#EEE" align="center"><br> <b>您浏览过的商品</b><br>
					<!-- 循环开始 --> <%
 	ArrayList<Items> itemlist = itemDao.getViewlist(list);
 	if (itemlist != null && itemlist.size() > 0) {
 		System.out.println("itemlist.size=" + itemlist.size());
 		for (Items i : itemlist) {
 %>
					<div id="item">
						<dl>
							<dt>
								<a href="detail.jsp?id=<%=i.getId()%>"><img
									src="images/<%=i.getPicture()%>" width="120" height="90"
									border="1" />
								</a>
							</dt>
							<dd class="dd_name"><%=i.getName()%></dd>
							<dd class="dd_city">
								产地:<%=i.getCity()%>&nbsp;&nbsp;价格:<%=i.getPrice()%>
								￥
							</dd>
						</dl>
					</div> <%
 	}
 	}
 %> <!-- 循环结束 --></td>
			</tr>
		</table>
	</center>
</body>
</html>

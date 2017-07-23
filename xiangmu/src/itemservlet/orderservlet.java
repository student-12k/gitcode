package itemservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsersDAO;
import com.po.User;

import open.Cart;
import open.Items;
import open.Td_item;
import open.Td_orderitem;

import dao.orderDAO;
import dao.orderItemsDAO;

public class orderservlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public orderservlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //防止页面传值乱码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//收货人地址
		String username=request.getParameter("username");
		//收货地址
		String address=request.getParameter("address");
		//收货电话
		String phonenumber=request.getParameter("phone");
		//支付方式
		String payway=request.getParameter("payway");
		request.getSession().setAttribute("payway", payway);
		//订单ID
		Integer orderID=Integer.parseInt(request.getParameter("orderID"));
		//如果session购物车中不为空
	    if(request.getSession().getAttribute("cart")!=null){
	    	//获取购物车
	    	 Cart cart = (Cart)request.getSession().getAttribute("cart"); 
	    	 HashMap<Items,Integer> goods = cart.getGoods();
	    	 //获取总价
	    	 double totalPrice=(Double)cart.getTotalprice();
	    	 //获取session中的用户名
	    	 String loginName=(String) request.getSession().getAttribute("loginUser");
	    	 //将购物车中的条目加入到数据库
	    	 try{
	    	 Set<Items> items = goods.keySet();
	    	 Iterator<Items> it = items.iterator();
	    	 while(it.hasNext())
	         {
	            Items i = it.next();
	            orderDAO orderDAO=new orderDAO();
	            int number=i.getNumber()-goods.get(i);
	            int id=i.getId();
	            orderDAO.updatenumber(number, id);
	            Td_item tdm=new Td_item();	
	            tdm.setProID(i.getId());
	            tdm.setProName(i.getName());
	            tdm.setProPrice(i.getPrice());
	            tdm.setNumber(goods.get(i));
	            tdm.setOrderID(orderID);        
	            orderDAO.itemOrderAdd(tdm);
	         }
	    	 //将订单信息加入到订单表中
	    	 UsersDAO usersDao=new UsersDAO();
	    	 usersDao.get(loginName);
	    	 User u=new User();
             Td_orderitem orderu=new Td_orderitem();
             //订单ID
             orderu.setOrderID(orderID);
             //用户ID
             orderu.setUserid(u.getUserid());
             orderu.setName(username);
             orderu.setAddress(address);
             orderu.setPhonenumber(phonenumber);
             orderu.setTotalPrice(totalPrice);
             orderu.setPayway(payway);
             //处理状态
             orderu.setOrderState("未处理");
             orderItemsDAO orderItem=new  orderItemsDAO();
             orderItem.addUserOrder(orderu);
            	 		}
	 		catch (Exception ex) {
	 			ex.printStackTrace();	
	 		}
	    	 request.getSession().removeAttribute("cart");
             out.write("{\"success\":1}");

	    }
	    else{
	    	out.write("{\"success\":false}");
	    }	
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

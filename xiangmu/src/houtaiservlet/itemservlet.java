package houtaiservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;

import open.Items;
import open.databean;


import net.sf.json.JSONObject;

public class itemservlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public itemservlet() {
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
		//防止页面传值乱码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//获取删除的商品ID
		String id=request.getParameter("deleteId");
		//将一串需要删除的商品ID值分开
		List<String> deleteid = Arrays.asList(id.split(","));
		ItemsDAO itemdao=new ItemsDAO();
		try {
			//for循环删除商品
			for(int i=0;i<deleteid.size();i++){
				System.out.println(deleteid.get(i));
			itemdao.delectByKey(deleteid.get(i));
			}
			out.write("{\"success\":true}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		//获取页面加密传值，解码
		 String str = URLDecoder.decode(request.getParameter("searchKey"),"UTF-8");
	     JSONObject staff = JSONObject.fromObject(str);
	     //获取检索值
	     String id=(String)staff.get("checkCode");
	     String name=(String) staff.get("checkName");
	     String city=(String) staff.get("checkcity");
	     //调用数据库执行方法（DAO方法）
	     ItemsDAO itemdao=new ItemsDAO();
	     List<Items> list = new ArrayList<Items>();
	     list= itemdao.getItemsByKey(id, name, city);
	     databean databe=new databean();
	     databe.setResultList(list);
	     JSONObject jsonObjectol = JSONObject.fromObject(databe);
	     //输出给页面的值
	     out.write(jsonObjectol.toString());
		out.flush();
		out.close();
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

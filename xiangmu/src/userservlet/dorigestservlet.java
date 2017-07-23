package userservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dao.UsersDAO;
import com.po.User;



public class dorigestservlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public dorigestservlet() {
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
		response.setContentType("application/json;charset=utf-8");
	       PrintWriter pw=response.getWriter();
	       System.out.println("--------------"+request.getParameter("addKey"));
	       String str = URLDecoder.decode(request.getParameter("addkey"),"UTF-8");
	       JSONObject staff = JSONObject.fromObject(str);
			UsersDAO dao=new UsersDAO();
			User u=new User();
			u.setUsername((String)staff.get("username"));
			u.setPassword((String)staff.get("password"));
		    u.setEmail((String)staff.get("email"));
		    u.setAddress((String)staff.get("address"));
			u.setPhonenumber((String)staff.get("phonenumber"));
			u.setQuestion((String)staff.get("question"));
			u.setAnswer((String)staff.get("answer"));	
			u.setPersonalpreferences((String)staff.get("personalpreferences"));     
			try{
				dao.Addusers(u);
				pw.write("{\"success\":true}");
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
				
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

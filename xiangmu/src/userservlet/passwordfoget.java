package userservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;


import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dao.UsersDAO;
import com.po.User;

@SuppressWarnings("unused")
public class passwordfoget extends HttpServlet {
	private static final long serialVersionUID = 2L;
	/**
	 * Constructor of the object.
	 */
	public passwordfoget() {
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
	 * The doDelete method of the servlet. <br>
	 *
	 * This method is called when a HTTP delete request is received.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

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

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		 String str = URLDecoder.decode(request.getParameter("keyone"),"UTF-8");
	      JSONObject staff = JSONObject.fromObject(str);
		UsersDAO dao=new UsersDAO();
		String username =(String) staff.get("username");
		String answer=(String) staff.get("answer");
        try{
        	User u=dao.get(username); 
        	System.out.println("----"+answer);
        	System.out.println(u.getAnswer());
         if(answer.equals(u.getAnswer())){
           	 out.write("{\"success\":true}");
           	out.flush();
          	 out.close();
          	 
            }
            else {
            	
           	 out.write("{\"success\":false}");
           	 out.flush();
          	 out.close();
            }
        	
        	
        }
	
       
        catch(Exception ex){
        	ex.printStackTrace();
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
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		UsersDAO dao=new UsersDAO();
		String username = request.getParameter("username");
		String json="";
		Integer id=Integer.parseInt(request.getParameter("code"));
        try{
        	User u=dao.get(username);
      if(id!=1){
    	  
      
        	if(u.isLogined()){
        		pw.write(0);
        	}
        	else{
        	String user="";
        	user +="\"username\":\""+u.getUsername()+"\"";	
        	user +=",\"question\":\""+u.getQuestion()+"\"";
        	user =",{"+user+"}";
        	json +=user;
        	 json=json.substring(1);
        	 pw.write("["+json+"]");
             pw.flush();
             pw.close();
        	}
	} 
      else {
    	  String password=request.getParameter("password");
    	  User user=new User();
    	  user.setPassword(password);
       	   user.setUsername(username);
       	   dao.Modifypw(user); 	   
       	pw.write("{\"success\":true}");
      } 	
        }
        catch(Exception ex){
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

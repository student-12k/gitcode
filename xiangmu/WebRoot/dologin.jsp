-<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%> 
<%@ page import="java.sql.*"%>  
<%@ page import="com.dao.Userdao"%>  
<%@ page import="com.po.User"%>  
 <% 
  request.setCharacterEncoding("utf-8");
%> 
<jsp:useBean id="myDBbean" class="com.dao.Userdao"></jsp:useBean>  
  
<jsp:useBean id="users" class="com.po.User" scope="session"></jsp:useBean>  
<jsp:setProperty name="users" property="*" />  
  

    <%  
    String username;
    String password;
      username = request.getParameter("username");
      password = request.getParameter("password");
        String sql = "select * from users where username='"+username+"' and password='"+password+"'";  
        if (session.getAttribute("loginUser")!=null){  
            out.println("您已经登录过了!"); 
             response.sendRedirect("index.jsp"); 
        }else{  
            ResultSet rs = myDBbean.query(sql);  
            if (rs.next()){  
                users.setLogined(true); 
           session.setAttribute("loginUser", username);
           response.sendRedirect("index.jsp");
            }else{  
                users.setLogined(false);  
                response.sendRedirect("login_failure.jsp");
            }                     
        }                 
    %>  

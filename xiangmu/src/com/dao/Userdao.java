package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBHelper;

//用户的业务逻辑类
public class Userdao {
    private Connection conn;
    private Statement state;
    private PreparedStatement stmt;
    private ResultSet rs;
    
	//用户登录方法
    public ResultSet query(String sql){  
        try{  
            conn = DBHelper.getConnection();  
            state = conn.createStatement();  
            rs = state.executeQuery(sql);  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return rs;  
    }  
      
    //执行数据库更新  
    public void update(String sql){  
        try{  
            conn =DBHelper.getConnection();  
            state = conn.createStatement();  
            state.executeUpdate(sql);  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
    }  
  
    //执行数据库更新  
    public void update(String sql,String[] args){  
        try{  
        	   conn =DBHelper.getConnection();  
                stmt= conn.prepareStatement(sql);  
            for (int i=0;i<args.length;i++){  
                stmt.setString(i+1,args[i]);  
            }  
            stmt.executeUpdate();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
    }  
      
    //关闭数据库连接  
    public void close(){  
        try{  
            if (rs != null)rs.close();  
            if (state != null)state.close();  
            if (stmt != null)stmt.close();  
            if (conn!= null)conn.close();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }         
    }     
      
}  
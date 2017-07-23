package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBHelper;

//�û���ҵ���߼���
public class Userdao {
    private Connection conn;
    private Statement state;
    private PreparedStatement stmt;
    private ResultSet rs;
    
	//�û���¼����
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
      
    //ִ�����ݿ����  
    public void update(String sql){  
        try{  
            conn =DBHelper.getConnection();  
            state = conn.createStatement();  
            state.executeUpdate(sql);  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
    }  
  
    //ִ�����ݿ����  
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
      
    //�ر����ݿ�����  
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
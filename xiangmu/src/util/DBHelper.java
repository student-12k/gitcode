package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
private static final String driver="com.mysql.jdbc.Driver";
private static final String url="jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEnoding=UTF-8";
private static final  String username="root";
private static final  String password="123456";

//��̬����ģ�������
 static
 {
	 try
	 {
		 Class.forName(driver);

	 }
	 catch(Exception ex)
	 {
		 ex.printStackTrace();
	 }
 }
 //������ݿ����Ӷ���conn
 public static Connection getConnection() throws Exception 
 {
		 return DriverManager.getConnection(url, username, password);
	 }
 //������ݿ�����
 public static void main(String[] args){
	 try{
		 Connection conn=DBHelper.getConnection();
		 if(conn==null){
			 System.out.println("测试失败");
		 }
		 else{
			 System.out.println("测试成功");
		 }
	 }
		 catch(Exception ex){
			ex.printStackTrace(); 
		 }
	 }
 }
 
 


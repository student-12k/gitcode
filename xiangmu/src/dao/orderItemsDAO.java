package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import open.Td_orderitem;
import util.DBHelper;

public class orderItemsDAO {

	public List<Td_orderitem> getAllUserOrder(String username,String checkId) throws Exception {
		Connection conn = null;
		conn = DBHelper.getConnection();
		String sql = "select * from td_orderitem where 1=1";
        List<String> strList=new ArrayList<String>();
		if (username.length()>0) {
			sql += " and name like ?";
			strList.add("%"+username+"%");
		}
		if (checkId.length()>0) {
			sql += " and orderID like ?";
			strList.add(checkId+"%");
		}
		System.out.println(sql);
		 PreparedStatement stmt = conn.prepareStatement(sql);
       for(int i=0;i<strList.size();i++){
    	 stmt.setString(i+1, strList.get(i));
       }
		List<Td_orderitem> list = new ArrayList<Td_orderitem>();
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Td_orderitem tdorder = new Td_orderitem();
			tdorder.setOrderID(rs.getInt("orderID"));
			tdorder.setUserid(rs.getInt("userid"));
			tdorder.setName(rs.getString("name"));
			tdorder.setAddress(rs.getString("address"));
			tdorder.setPhonenumber(rs.getString("address"));
			tdorder.setTotalPrice(rs.getDouble("totalPrice"));
			tdorder.setCreatTime(rs.getString("creatTime"));
			tdorder.setPayway(rs.getString("payway"));
			tdorder.setOrderState(rs.getString("orderState"));
			list.add(tdorder);
		}
		return list;

	}
	public void addUserOrder(Td_orderitem td) throws Exception{
		Connection conn=DBHelper.getConnection();
		String sql="insert into td_orderitem (orderID,userid,name,address,phonenumber,"
				+"totalPrice,creatTime,payway,orderState)"
				+" values(?,?,?,?,?,?,sysdate(),?,?)";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, td.getOrderID());
		stmt.setInt(2, td.getUserid());
		stmt.setString(3, td.getName());
		stmt.setString(4, td.getAddress());
		stmt.setString(5, td.getPhonenumber());
		stmt.setDouble(6, td.getTotalPrice());
		stmt.setString(7, td.getPayway());
		stmt.setString(8, td.getOrderState());
		stmt.executeUpdate();
	}
	public void upUserOrder(String id) throws Exception{
		Connection conn=DBHelper.getConnection();
		String sql="update td_orderitem set orderState='已处理' where orderID=?";	
		System.out.println(sql);
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.executeUpdate();
		
	}
}

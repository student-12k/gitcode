package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import open.Td_item;
import util.DBHelper;

public class orderDAO {
	Connection conn=null;
	public void itemOrderAdd( Td_item itemTd) throws Exception {
		conn = DBHelper.getConnection();
		String sql = "insert into td_order(proID,proName,proPrice,number,orderID) values (?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, itemTd.getProID());
		stmt.setString(2, itemTd.getProName().trim());
		stmt.setDouble(3, itemTd.getProPrice());
		stmt.setInt(4, itemTd.getNumber());
		stmt.setInt(5, itemTd.getOrderID());
	    stmt.executeUpdate(); 
		
	}


	public ArrayList<Td_item> getAllitemOrder( Td_item itemTd)
			throws Exception {
		ArrayList<Td_item> prolist = new ArrayList<Td_item>();
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from td_order";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				itemTd.setProID(rs.getInt("proID"));
				itemTd.setProName(rs.getString("proName"));
				itemTd.setProPrice(rs.getDouble("proPrice"));
				itemTd.setNumber(rs.getInt("number"));
				itemTd.setOrderID(rs.getInt("orderID"));
                prolist.add(itemTd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return prolist;
	}
	public Td_item doProSearch( Td_item itemTd) throws Exception{
		conn = DBHelper.getConnection();
		String sql = "select * from td_order where orderID=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, itemTd.getOrderID());
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			itemTd.setProID(rs.getInt("proID"));
			itemTd.setProName(rs.getString("proName"));
			itemTd.setProPrice(rs.getDouble("proPrice"));
			itemTd.setNumber(rs.getInt("number"));
			itemTd.setOrderID(rs.getInt("orderID"));
		}
		return itemTd;
	}
	public void updatenumber(int number,int id) throws Exception{
			conn = DBHelper.getConnection();
			String sql="update items set number=? where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, number);
			stmt.setInt(2, id);
			stmt.executeUpdate();
	}
}

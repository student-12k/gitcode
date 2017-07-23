package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBHelper;

import open.Items;

public class ItemsDAO {

	public ArrayList<Items> getAllItems() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>();
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				list.add(item);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// �ͷ���ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	public Items detailbyid(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items where id=?;"; // SQL���
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				return item;
			} else {
				return null;
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// �ͷ���ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	public ArrayList<Items> getViewlist(String list) {
		System.out.println("List:" + list);
		int icount = 5;
		ArrayList<Items> itemslist = new ArrayList<Items>();
		if (list != null && list.length() > 0) {
			String[] arr = list.split(",");
			System.out.println("arr.length=" + arr.length);
			if (arr.length >= 5) {
				for (int i = arr.length - 1; i >= arr.length - icount; i--) {
					itemslist.add(detailbyid(Integer.parseInt(arr[i])));
				}

			} else {
				for (int i = arr.length - 1; i >= arr.length; i--) {
					itemslist.add(detailbyid(Integer.parseInt(arr[i])));
				}
			}
			return itemslist;
		} else {
			return null;
		}
	}

	public Items getItemsById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items where id=?;"; // SQL���
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				return item;
			} else {
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// �ͷ���ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}

	public List<Items> getItemsByKey(String id, String name, String city) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "select it.id,it.name,it.city,it.price,it.number,it.picture," +
					"td.name AS cateName " +
					"from items it left join td_category td " +
					"on it.cateID=td.id where 1=1";	
			List<String> strList=new ArrayList<String>();
			//判断商品id是否为空
			if (id.length()>0) {
				sql += " and it.id=?";
				strList.add(id);
		    //判断商品名是否为空
			} if (name.length()>0) {
				sql += " and it.name like ?";
				strList.add("%"+name+"%");
		    //判断商品产地是否为空
			} if (city.length()>0) {
				sql += " and it.city like ?";
				strList.add("%"+city+"%");
			}
			System.out.println(sql);
			stmt = conn.prepareStatement(sql);
		    for(int i=0;i<strList.size();i++){
		    	stmt.setString(i+1,strList.get(i));
		    }
			rs = stmt.executeQuery();
			List<Items> itemsList = new ArrayList<Items>();
			//将从数据库取到的值加入到list集合中
			while(rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				item.setCateName(rs.getString("cateName"));
				itemsList.add(item);
			}
			//返回list集合值
			return itemsList;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}
	public void delectByKey(String id) throws Exception{
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DBHelper.getConnection();
		String sql=" delete from items where id=?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.executeUpdate();
	}
	public void addItems(Items item) throws Exception{
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DBHelper.getConnection();
		String sql="insert into items (name,city,price,number,picture,itemDetail,cateID) " +
				"values(?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, item.getName());
		stmt.setString(2, item.getCity());
		stmt.setDouble(3, item.getPrice());
		stmt.setInt(4, item.getNumber());
		stmt.setString(5, item.getPicture());
		stmt.setString(6, item.getItemDetail());
		stmt.setInt(7, item.getCateId());
		stmt.executeUpdate();
	}
}

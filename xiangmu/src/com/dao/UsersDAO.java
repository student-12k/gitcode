package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBHelper;

import com.po.User;

//�û���ҵ���߼���
@SuppressWarnings("unused")
public class UsersDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	String sql;

	public ArrayList<User> getAllUser() {
		ArrayList<User> list = new ArrayList<User>();
		try {
			conn = DBHelper.getConnection();
			sql = "select * from users ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				User users = new User();
				users.setUserid(rs.getInt("userid"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setEmail(rs.getString("email"));
				users.setPhonenumber(rs.getString("phonenumber"));
				users.setQuestion(rs.getString("question"));
				users.setAnswer(rs.getString("answer"));
				users.setAddress(rs.getString("address"));
				users.setPersonalpreferences(rs
						.getString("personalpreferences"));
				list.add(users);
			}
			return list;
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

	public void Addusers(User u) throws Exception {

		conn = DBHelper.getConnection();
		sql = "insert into users (username,password,email,address,phonenumber,question,answer,personalpreferences) values(?,?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(sql);

		stmt.setString(1, u.getUsername());
		stmt.setString(2, u.getPassword());
		stmt.setString(3, u.getEmail());
		stmt.setString(4, u.getAddress());
		stmt.setString(5, u.getPhonenumber());
		stmt.setString(6, u.getQuestion());
		stmt.setString(7, u.getAnswer());
		stmt.setString(8, u.getPersonalpreferences());
		stmt.executeUpdate();
	}

	public User get(String username) throws Exception {
		conn = DBHelper.getConnection();
		sql = "select * from users where username=? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		rs = stmt.executeQuery();
		User u = new User();
		while (rs.next()) {
			u.setUserid(rs.getInt("userid"));
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setEmail(rs.getString("email"));
			u.setPhonenumber(rs.getString("phonenumber"));
			u.setQuestion(rs.getString("question"));
			u.setAnswer(rs.getString("answer"));
			u.setAddress(rs.getString("address"));
			u.setPersonalpreferences(rs.getString("personalpreferences"));
		}
		return u;

	}

	
	public void Modifypw(User u) throws Exception {

		conn = DBHelper.getConnection();
		sql = "update users set password=? where username=? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, u.getUsername());
		stmt.setString(2, u.getPassword());
		stmt.execute();
	}

}

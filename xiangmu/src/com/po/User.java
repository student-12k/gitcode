package com.po;

public class User {
	private int userid;
	private String username;
	private String password;
	private String email;
	private String address;
	private String phonenumber;
	private String question;
	private String answer;
	private String  personalpreferences;
	
	boolean logined = false;  
	public String getPersonalpreferences() {
		return personalpreferences;
	}
	public void setPersonalpreferences(String personalpreferences) {
		this.personalpreferences = personalpreferences;
	}
	public User()
	{
		
	}
	public boolean isLogined() {
		return logined;
	}
	
	public void setLogined(boolean logined) {
		this.logined = logined;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

	
}

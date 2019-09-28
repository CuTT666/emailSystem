package com.team1.model;

public class User {

	private String user_id;   //id
	private String account;   //�˺š������
	private String password;   //����
	private String username;   //����
	private String tel;        //��ϵ�绰
	private String address;   //��ַ
	private String time;      //ע��ʱ��
	
	public User() {}
	
	public User(String user_id, String account, String password) {
		this.user_id = user_id;
		this.account = account;
		this.password = password;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", account=" + account + ", password=" + password + ", username=" + username
				+ ", tel=" + tel + ", address=" + address + ", time=" + time + "]";
	}
	
}

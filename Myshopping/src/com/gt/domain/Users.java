package com.gt.domain;

//这是一个javabean，和数据库中的 users表对应
public class Users {
	private int id;				//用户id
	private String name;		//用户名
	private String pwd;		//用户密码
	private String email;	//Email
	private String tel;		//电话
	private int grade;	//等级
	
	public Users(int u, String pwd) {
		super();
		this.id = u;
		this.pwd = pwd;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}

package com.dw.domain;

import java.util.Date;

public class User {

	private String u_phone;// 用户号
	private String u_name;// 姓名
	private String u_sex;// 用户性别
	private String u_ID;// 用户身份证
	private String u_level;// 用户级别
	private String u_adress;// 用户地址
	private String u_regDate;// 用户注册时间，由系统自动填写
	private String u_password;
	private String u_status;
	public String getuPhone() {
		return u_phone;
	}
	public void setuPhone(String uPhone) {
		this.u_phone = uPhone;
	}
	public String getuName() {
		return u_name;
	}
	public void setuName(String uName) {
		this.u_name = uName;
	}
	public String getuSex() {
		return u_sex;
	}
	public void setuSex(String uSex) {
		this.u_sex = uSex;
	}
	public String getuID() {
		return u_ID;
	}
	public void setuID(String uID) {
		this.u_ID = uID;
	}
	public String getuLevel() {
		return u_level;
	}
	public void setuLevel(String uLevel) {
		this.u_level = uLevel;
	}
	public String getuAdress() {
		return u_adress;
	}
	public void setuAdress(String uAdress) {
		this.u_adress = uAdress;
	}
	public String getuRegistTime() {
		return u_regDate;
	}
	public void setuRegistTime(String uRegistTime) {
		this.u_regDate = uRegistTime;
	}
	public String getuPassword() {
		return u_password;
	}
	public void setuPassword(String uPassword) {
		this.u_password = uPassword;
	}
	public User() {
		super();
		// TODO 自动生成的构造函数存根
	}
	

	@Override
	public String toString() {
		return "User [u_phone=" + u_phone + ", u_name=" + u_name + ", u_sex=" + u_sex + ", u_ID=" + u_ID + ", u_level="
				+ u_level + ", u_adress=" + u_adress + ", u_regDate=" + u_regDate + ", u_password=" + u_password
				+ ", u_status=" + u_status + "]";
	}
	
//	public String toString() {
//		return "User [u_phone=" + u_phone + ", u_name=" + u_name + ", u_sex=" + u_sex + ", u_ID=" + u_ID + ", u_level="
//				+ u_level + ", u_adress=" + u_adress + ", u_regDate=" + u_regDate + ", u_status=" + u_status + "]";
//	}
	//	@Override
//	public String toString() {
//		return "User [uPhone=" + u_phone + ", uName=" + u_name + ", uSex=" + u_sex + ", uID=" + u_ID + ", uLevel=" + u_level
//				+ ", uAdress=" + u_adress + ", uRegistTime=" + u_regDate + ", uPassword=" + u_password + "]";
//	}
	public String getuStatus() {
		return u_status;
	}
	public void setuStatus(String uStatus) {
		this.u_status = uStatus;
	}
	public User(String u_phone, String u_name, String u_sex, String u_ID, String u_regDate,String u_level, String u_adress, 
			String u_password, String u_status) {
		super();
		this.u_phone = u_phone;
		this.u_name = u_name;
		this.u_sex = u_sex;
		this.u_ID = u_ID;
		this.u_level = u_level;
		this.u_adress = u_adress;
		this.u_regDate = u_regDate;
		this.u_password = u_password;
		this.u_status = u_status;
	}
//	public User(String uPhone, String uName, String uSex, String uID, String uLevel, String uAdress, String uRegistTime,
//			String uPassword) {
//		super();
//		this.u_phone = uPhone;
//		this.u_name = uName;
//		this.u_sex = uSex;
//		this.u_ID = uID;
//		this.u_level = uLevel;
//		this.u_adress = uAdress;
//		this.u_regDate = uRegistTime;
//		this.u_password = uPassword;
//	}
	public User(String u_name, String u_ID) {
		super();
		this.u_name = u_name;
		this.u_ID = u_ID;
	}

	
}

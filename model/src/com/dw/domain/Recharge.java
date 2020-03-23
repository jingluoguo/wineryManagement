package com.dw.domain;

public class Recharge {
	private int id;
	private String r_name;
	private String r_ID;
	private String r_acount;
	private String r_ordernumber;
	private String r_date;
	private String r_status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getR_ID() {
		return r_ID;
	}
	public void setR_ID(String r_ID) {
		this.r_ID = r_ID;
	}
	public String getR_acount() {
		return r_acount;
	}
	public void setR_acount(String r_acount) {
		this.r_acount = r_acount;
	}
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}
	public String getR_status() {
		return r_status;
	}
	public void setR_status(String r_status) {
		this.r_status = r_status;
	}
	public String getR_ordernumber() {
		return r_ordernumber;
	}
	public void setR_ordernumber(String r_ordernumber) {
		this.r_ordernumber = r_ordernumber;
	}
	public Recharge(int id, String r_name, String r_ID, String r_acount, String r_ordernumber, String r_date, String r_status) {
		super();
		this.id = id;
		this.r_name = r_name;
		this.r_ID = r_ID;
		this.r_acount = r_acount;
		this.r_ordernumber = r_ordernumber;
		this.r_date = r_date;
		this.r_status = r_status;
	}
	public Recharge() {
		super();
		// TODO 自动生成的构造函数存根
	}
}

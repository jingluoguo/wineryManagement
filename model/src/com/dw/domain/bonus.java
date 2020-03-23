package com.dw.domain;

public class bonus {
	private Integer id;
	private String b_bID;
	private String b_ID;
	private String b_year;
	private String b_month;
	private double b_money;
	public String getB_bID() {
		return b_bID;
	}
	public void setB_bID(String b_bID) {
		this.b_bID = b_bID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getB_ID() {
		return b_ID;
	}
	public void setB_ID(String b_ID) {
		this.b_ID = b_ID;
	}
	public String getB_year() {
		return b_year;
	}
	public void setB_year(String b_year) {
		this.b_year = b_year;
	}
	public String getB_month() {
		return b_month;
	}
	public void setB_month(String b_month) {
		this.b_month = b_month;
	}
	public double getB_money() {
		return b_money;
	}
	public void setB_money(double b_money) {
		this.b_money = b_money;
	}
	public bonus(Integer id, String b_bID, String b_ID, String b_year, String b_month, double b_money) {
		super();
		this.id = id;
		this.b_bID = b_bID;
		this.b_ID = b_ID;
		this.b_year = b_year;
		this.b_month = b_month;
		this.b_money = b_money;
	}
}

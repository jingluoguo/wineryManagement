package com.dw.domain;

public class Cash {
	private int id;
	private String c_name;
	private String c_ID;
	private String c_card;
	private String c_address;
	private double c_account;
	private double c_total;
	private String c_status;
	private String c_date;
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getC_ID() {
		return c_ID;
	}
	public void setC_ID(String c_ID) {
		this.c_ID = c_ID;
	}
	public String getC_card() {
		return c_card;
	}
	public void setC_card(String c_card) {
		this.c_card = c_card;
	}
	public String getC_address() {
		return c_address;
	}
	public void setC_address(String c_address) {
		this.c_address = c_address;
	}
	public double getC_account() {
		return c_account;
	}
	public void setC_account(double c_account) {
		this.c_account = c_account;
	}
	public String getC_status() {
		return c_status;
	}
	public void setC_status(String c_status) {
		this.c_status = c_status;
	}
	public double getC_total() {
		return c_total;
	}
	public void setC_total(double c_total) {
		this.c_total = c_total;
	}
	public Cash(int id, String c_name, String c_ID, String c_card, String c_address, double c_account,double c_total, String c_status,
			String c_date) {
		super();
		this.id = id;
		this.c_name = c_name;
		this.c_ID = c_ID;
		this.c_card = c_card;
		this.c_address = c_address;
		this.c_account = c_account;
		this.c_status = c_status;
		this.c_date = c_date;
		this.c_total = c_total;
	}
}

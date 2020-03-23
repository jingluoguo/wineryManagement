package com.dw.domain;

public class BonusRecord {
	private Integer id;
	private String r_name;
	private String r_ID;
	private String r_FID;
	private String r_time;
	private double r_money;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getR_FID() {
		return r_FID;
	}
	public void setR_FID(String r_FID) {
		this.r_FID = r_FID;
	}
	public String getR_time() {
		return r_time;
	}
	public void setR_time(String r_time) {
		this.r_time = r_time;
	}
	public double getR_money() {
		return r_money;
	}
	public void setR_money(double r_money) {
		this.r_money = r_money;
	}
	@Override
	public String toString() {
		return "BonusRecord [id=" + id + ", r_name=" + r_name + ", r_ID=" + r_ID + ", r_FID=" + r_FID + ", r_time="
				+ r_time + ", r_money=" + r_money + "]";
	}
	public BonusRecord(Integer id, String r_name, String r_ID, String r_FID, String r_time, double r_money) {
		super();
		this.id = id;
		this.r_name = r_name;
		this.r_ID = r_ID;
		this.r_FID = r_FID;
		this.r_time = r_time;
		this.r_money = r_money;
	}
	
}

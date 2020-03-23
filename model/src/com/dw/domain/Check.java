package com.dw.domain;

public class Check {
	private String id;
	private String FID;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFID() {
		return FID;
	}
	public void setFID(String fID) {
		FID = fID;
	}
	@Override
	public String toString() {
		return "check [id=" + id + ", FID=" + FID + "]";
	}
	public Check() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
}

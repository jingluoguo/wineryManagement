package com.dw.domain;

public class ConsumeExample {
	private String uName;
	private String uID;
	private String uLevel;
	private String cWin;
	private double cPrice;
	private String cDate;
	private double cAgio;
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getuLevel() {
		return uLevel;
	}
	public void setuLevel(String uLevel) {
		this.uLevel = uLevel;
	}
	public String getcWin() {
		return cWin;
	}
	public void setcWin(String cWin) {
		this.cWin = cWin;
	}
	public double getcPrice() {
		return cPrice;
	}
	public void setcPrice(double cPrice) {
		this.cPrice = cPrice;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	@Override
	public String toString() {
		return "ConsumeExample [uName=" + uName + ", uID=" + uID + ", uLevel=" + uLevel + ", cWin=" + cWin + ", cPrice="
				+ cPrice + ", cDate=" + cDate + ", cAgio=" + cAgio + "]";
	}
	public ConsumeExample(String uName, String uID, String uLevel, String cWin, double cPrice, String cDate,
			double cAgio) {
		super();
		this.uName = uName;
		this.uID = uID;
		this.uLevel = uLevel;
		this.cWin = cWin;
		this.cPrice = cPrice;
		this.cDate = cDate;
		this.cAgio = cAgio;
	}
	public ConsumeExample() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getcAgio() {
		return cAgio;
	}
	public void setcAgio(double cAgio) {
		this.cAgio = cAgio;
	}
	
	

}

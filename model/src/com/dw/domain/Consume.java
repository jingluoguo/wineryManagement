package com.dw.domain;

public class Consume {
	
	private Integer id;
	private String cWin;
	private String cID;
	private double cOmoney;
	private double cAgio;
	private double cPrice;
	private String cDate;
	private String cPay;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getcWin() {
		return cWin;
	}
	public void setcWin(String cWin) {
		this.cWin = cWin;
	}
	public String getcID() {
		return cID;
	}
	public void setcID(String cID) {
		this.cID = cID;
	}
	public double getcOmoney() {
		return cOmoney;
	}
	public void setcOmoney(double cOmoney) {
		this.cOmoney = cOmoney;
	}
	public double getcAgio() {
		return cAgio;
	}
	public void setcAgio(double cAgio) {
		this.cAgio = cAgio;
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
	public Consume(Integer id, String cWin, String cID, double cOmoney, double cAgio, double cPrice, String cDate,
			String cPay) {
		super();
		this.id = id;
		this.cWin = cWin;
		this.cID = cID;
		this.cOmoney = cOmoney;
		this.cAgio = cAgio;
		this.cPrice = cPrice;
		this.cDate = cDate;
		this.cPay = cPay;
	}
	@Override
	public String toString() {
		return "Consume [id=" + id + ", cWin=" + cWin + ", cID=" + cID + ", cOmoney=" + cOmoney + ", cAgio=" + cAgio
				+ ", cPrice=" + cPrice + ", cDate=" + cDate + ", cPay=" + cPay + "]";
	}
	public String getcPay() {
		return cPay;
	}
	public void setcPay(String cPay) {
		this.cPay = cPay;
	}
	public Consume() {
		super();
	}
	
	

}

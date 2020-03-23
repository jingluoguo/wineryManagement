package com.dw.domain;

public class ShopCart {
	
	private Integer id;
	private String userId;
	private Integer wineId;
	private Integer amount;
	//商品信息
	private WineInfo wineInfo;
	
	
	public WineInfo getWineInfo() {
		return wineInfo;
	}
	public void setWineInfo(WineInfo wineInfo) {
		this.wineInfo = wineInfo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getWineId() {
		return wineId;
	}
	public void setWineId(Integer wineId) {
		this.wineId = wineId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public ShopCart(Integer id, String userId, Integer wineId, Integer amount) {
		super();
		this.id = id;
		this.userId = userId;
		this.wineId = wineId;
		this.amount = amount;
	}
	public ShopCart() {
		super();
	}
	@Override
	public String toString() {
		return "ShopCart [id=" + id + ", userId=" + userId + ", wineId=" + wineId + ", amount=" + amount + ", wineInfo="
				+ wineInfo + "]";
	}
	
}

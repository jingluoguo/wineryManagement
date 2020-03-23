package com.dw.domain;
/**
 * 目前只添加了三个属性，BLOB再数据库中的存取比较麻烦，而且目前的工作里面没有这一项。
 * @author 葬心不留情
 *
 */
public class WineInfo {
	
	private Integer wineId;
	private String wineName;
	private double winePrice;
	private Integer wineNumber;
	private String wineImg;
	private String wineDec;
	private String wineCategory;
	
	
	public String getWineImg() {
		return wineImg;
	}
	public void setWineImg(String wineImg) {
		this.wineImg = wineImg;
	}
	public String getWineDec() {
		return wineDec;
	}
	public void setWineDec(String wineDec) {
		this.wineDec = wineDec;
	}
	public String getWineCategory() {
		return wineCategory;
	}
	public void setWineCategory(String wineCategory) {
		this.wineCategory = wineCategory;
	}
	public Integer getWineId() {
		return wineId;
	}
	public void setWineId(Integer wineId) {
		this.wineId = wineId;
	}
	public String getWineName() {
		return wineName;
	}
	public void setWineName(String wineName) {
		this.wineName = wineName;
	}
	public double getWinePrice() {
		return winePrice;
	}
	public void setWinePrice(double winePrice) {
		this.winePrice = winePrice;
	}
	public Integer getWineNumber() {
		return wineNumber;
	}
	public void setWineNumber(Integer wineNumber) {
		this.wineNumber = wineNumber;
	}
	
	public WineInfo(Integer wineId, String wineName, double winePrice, Integer wineNumber, String wineImg,
			String wineDec, String wineCategory) {
		super();
		this.wineId = wineId;
		this.wineName = wineName;
		this.winePrice = winePrice;
		this.wineNumber = wineNumber;
		this.wineImg = wineImg;
		this.wineDec = wineDec;
		this.wineCategory = wineCategory;
	}
	public WineInfo() {
		super();
	}
	@Override
	public String toString() {
		return "WineInfo [wineId=" + wineId + ", wineName=" + wineName + ", winePrice=" + winePrice + ", wineNumber="
				+ wineNumber + ", wineImg=" + wineImg + ", wineDec=" + wineDec + ", wineCategory=" + wineCategory + "]";
	}
	
	 
	
	

}

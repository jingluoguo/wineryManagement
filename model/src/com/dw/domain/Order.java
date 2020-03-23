package com.dw.domain;

public class Order {
	
	private Integer orderId;
	private double orderPrice;
	private Integer orderNumber;
	private String orderDate;
	private String orderAddress;
	private String orderPhone;
	private String orderStatus;
	private boolean orderCope;
	private String orderName;
	private String orderWineName;
	private double orderSimple;
	
	public double getOrderSimple() {
		return orderSimple;
	}
	public void setOrderSimple(double orderSimple) {
		this.orderSimple = orderSimple;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderWineName() {
		return orderWineName;
	}
	public void setOrderWineName(String orderWineName) {
		this.orderWineName = orderWineName;
	}
	public boolean getOrderCope() {
		return orderCope;
	}
	public void setOrderCope(boolean orderCope) {
		this.orderCope = orderCope;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getOrderPhone() {
		return orderPhone;
	}
	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Order() {
		super();
	}
	public Order(Integer orderId, double orderPrice, Integer orderNumber, String orderDate, String orderAdress,
			String orderPhone, String orderStatus, boolean orderCope) {
		super();
		this.orderId = orderId;
		this.orderPrice = orderPrice;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.orderAddress = orderAdress;
		this.orderPhone = orderPhone;
		this.orderStatus = orderStatus;
		this.orderCope = orderCope;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderPrice=" + orderPrice + ", orderNumber=" + orderNumber
				+ ", orderDate=" + orderDate + ", orderAdress=" + orderAddress + ", orderPhone=" + orderPhone
				+ ", orderStatus=" + orderStatus + ", orderCope=" + orderCope + "]";
	}
	//非和数据库对应
	public Order(Integer orderId, double orderPrice, Integer orderNumber, String orderDate, String orderAddress,
			String orderPhone, String orderStatus, boolean orderCope, String orderName, String orderWineName, double orderSimple) {
		super();
		this.orderId = orderId;
		this.orderPrice = (double) Math.round(orderPrice*100)/100;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.orderAddress = orderAddress;
		this.orderPhone = orderPhone;
		this.orderStatus = orderStatus;
		this.orderCope = orderCope;
		this.orderName = orderName;
		this.orderWineName = orderWineName;
		this.orderSimple = orderSimple;
	}

	
	

}

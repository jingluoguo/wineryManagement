package com.dw.dao;

import java.util.List;

import com.dw.domain.Order;
import com.dw.domain.Page;

public interface OrderDao {
	
	public void saveOrderRecord(Order order, int wineId);
	public Integer getOrderCount(String winID,String phone,String date);
	public List<Order> getOrderInfo(String winID,String phone,String date,Page page);
	public boolean updateStatus(String userID, String userStatus);
	public List<Order> getOrderInfoAll(String winID, String phone, String date);

}

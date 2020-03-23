package com.dw.service;

import java.util.List;

import com.dw.DaoImpl.OrderDaoImpl;
import com.dw.dao.OrderDao;
import com.dw.domain.Order;
import com.dw.domain.Page;

public class OrderService {
	
	private OrderDao orderDao = new OrderDaoImpl();
	
	/**
	 * 保存订单信息
	 * @param wineId 
	 */
	public void saveOrderRecord(Order order, int wineId) {
		orderDao.saveOrderRecord(order,wineId);
	}
	/**
	 * 查询所有订单
	 */
	public Integer getOrderCount(String winID,String phone,String date){
		return orderDao.getOrderCount(winID,phone,date);
		
	}
	/**
	 * 查询所有订单
	 */
	public List<Order> getOrderInfo(String winID,String phone,String date,Page page){
		return orderDao.getOrderInfo(winID,phone,date,page);
		
	}
	/**
	 * 查询所有订单
	 */
	public List<Order> getOrderInfo(String winID,String phone,String date){
		return orderDao.getOrderInfoAll(winID,phone,date);
		
	}
	/**
	 * 更新订单状态
	 * @param userID
	 * @param userStatus
	 * @return
	 */
	public boolean updateStatus(String userID, String userStatus) {
		// TODO 自动生成的方法存根
		return orderDao.updateStatus(userID, userStatus);
	}
}

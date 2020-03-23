package com.dw.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.dw.dao.OrderDao;
import com.dw.domain.Order;
import com.dw.domain.Page;
import com.dw.tools.JdbcUtil;


public class OrderDaoImpl implements OrderDao {

	QueryRunner queryrunner = new QueryRunner();
	/**
	 * 保存订单记录
	 */
	@Override
	public void saveOrderRecord(Order order, int wineId) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "insert into formal_order(order_price, order_number, order_date, order_address, order_phone, order_status, order_cope, order_wineid) values(?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setDouble(1, order.getOrderPrice());
			pst.setInt(2, order.getOrderNumber());
			pst.setString(3, order.getOrderDate());
			pst.setString(4, order.getOrderAddress());
			pst.setString(5, order.getOrderPhone());
			pst.setString(6, order.getOrderStatus());
			pst.setBoolean(7, order.getOrderCope());
			pst.setInt(8, wineId);
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
	}
	/**
	 * 查询订单数量
	 */
	public Integer getOrderCount(String winID,String phone,String date) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer count = 0;
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			if(("".equals(winID)||winID == null) && ("".equals(phone)||phone == null) && ("".equals(date)||date == null)){
				sql = "select * from formal_order,user,wine_info where order_phone = u_phone and order_wineid = wine_id";
			}
			else
			{
				sql = "select * from formal_order,user,wine_info where order_phone = u_phone and order_wineid = wine_id ";
				if(!"".equals(winID)){
					sql += "and order_id like '%" + winID+ "%'";
				}
				if(!"".equals(phone)){
					sql += "and order_phone like '%" + phone+ "%'";
				}
				if(!"".equals(date)){
					sql += "and order_date like '%" + date+ "%'";
				}
			}
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				count++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return count;
	}
	/**
	 * 查询订单信息
	 */
	public List<Order> getOrderInfo(String winID,String phone,String date,Page page) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Order> order = new ArrayList<Order>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			if(("".equals(winID)||winID == null) && ("".equals(phone)||phone == null) && ("".equals(date)||date == null)){
				sql = "select * from formal_order,user,wine_info where order_phone = u_phone and order_wineid = wine_id";
			}
			else
			{
				sql = "select * from formal_order,user,wine_info where order_phone = u_phone and order_wineid = wine_id ";
				if(!"".equals(winID)){
					sql += "and order_id like '%" + winID+ "%'";
				}
				if(!"".equals(phone)){
					sql += "and order_phone like '%" + phone+ "%'";
				}
				if(!"".equals(date)){
					sql += "and order_date like '%" + date+ "%'";
				}
			}
			sql += " order by order_date desc";
			sql += " limit "+page.getStart() +","+page.getSize();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				int orderId = rs.getInt("order_id");
				float orderPrice =rs.getFloat("order_price");
				int orderNumber = rs.getInt("order_number");
				String orderDate = rs.getString("order_date");
				String orderAdress = rs.getString("order_address");
				String orderPhone = rs.getString("order_phone");
				String orderStatus = rs.getString("order_status");
				boolean orderCope = rs.getBoolean("order_cope");
				String orderName = rs.getString("u_name");
				String orderWineName = rs.getString("wine_name");
				double orderWineprice = rs.getDouble("wine_price");
				order.add(new Order(orderId, orderPrice, orderNumber, orderDate, orderAdress, orderPhone, orderStatus, orderCope,orderName,orderWineName,orderWineprice));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return order;
	}
	/**
	 * 查询订单信息
	 */
	public List<Order> getOrderInfoAll(String winID,String phone,String date) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Order> order = new ArrayList<Order>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			if(("".equals(winID)||winID == null) && ("".equals(phone)||phone == null) && ("".equals(date)||date == null)){
				sql = "select * from formal_order,user,wine_info where order_phone = u_phone and order_wineid = wine_id";
			}
			else
			{
				sql = "select * from formal_order,user,wine_info where order_phone = u_phone and order_wineid = wine_id ";
				if(!"".equals(winID)){
					sql += "and order_id like '%" + winID+ "%'";
				}
				if(!"".equals(phone)){
					sql += "and order_phone like '%" + phone+ "%'";
				}
				if(!"".equals(date)){
					sql += "and order_date like '%" + date+ "%'";
				}
			}
			sql += " order by order_date desc";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				int orderId = rs.getInt("order_id");
				float orderPrice =rs.getFloat("order_price");
				int orderNumber = rs.getInt("order_number");
				String orderDate = rs.getString("order_date");
				String orderAdress = rs.getString("order_address");
				String orderPhone = rs.getString("order_phone");
				String orderStatus = rs.getString("order_status");
				boolean orderCope = rs.getBoolean("order_cope");
				String orderName = rs.getString("u_name");
				String orderWineName = rs.getString("wine_name");
				double orderWineprice = rs.getDouble("wine_price");
				//System.out.println(orderPrice + "    ");
				order.add(new Order(orderId, orderPrice, orderNumber, orderDate, orderAdress, orderPhone, orderStatus, orderCope,orderName,orderWineName,orderWineprice));
				//settlement.add(new User(uPhone, uName, uSex, uID, uRegDate, uLevel, uAdress,uPassword,uStatus));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return order;
	}
	/**
	 * 更新订单状态
	 */
	@Override
	public boolean updateStatus(String wineID, String wineStatus) {
		Connection connection = null;
		String sql1 = "UPDATE formal_order SET order_cope = ? WHERE order_id = ?";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql1, new Object[] { wineStatus,wineID });
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return false;
	}

}

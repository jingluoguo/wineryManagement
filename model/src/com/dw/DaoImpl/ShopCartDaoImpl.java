package com.dw.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dw.dao.ShopCartDao;
import com.dw.dao.WineInfoDao;
import com.dw.domain.ShopCart;
import com.dw.tools.JdbcUtil;


public class ShopCartDaoImpl implements ShopCartDao {
	private WineInfoDao wineInfoDao = new WineInfoDaoImpl();
	/**
	 * 保存购物车信息
	 */
	@Override
	public void addShopCart(ShopCart shopCart) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "insert into shopcart(userId,wineId,amount) values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, shopCart.getUserId());
			pst.setInt(2, shopCart.getWineId());
			pst.setInt(3,shopCart.getAmount());
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
	}

	/**
	 * 返回某件商品在购物车中的数量
	 */
	@Override
	public int getWineCount(Integer wineId, String userId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int amount = 0;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from shopcart where wineId=? and userId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, wineId);
			pst.setString(2, userId);
			rs = pst.executeQuery();
			if(rs.next()) {
				amount = rs.getInt("amount");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return amount;
	}

	/**
	 * 更新某件商品在购物车中的数量
	 */
	@Override
	public void updateShopCart(ShopCart shopCart) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "update  shopcart set amount=? where wineId=? and userId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, shopCart.getAmount());
			pst.setInt(2, shopCart.getWineId());
			pst.setString(3, shopCart.getUserId());
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
	}

	/**
	 * 根据用户Id获得购物车中的所有酒水信息
	 */
	@Override
	public List<ShopCart> getShopCartWineInfo(String userId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<ShopCart> list = new ArrayList<ShopCart>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from shopcart where userId=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int wineId = rs.getInt("wineId");
				int amount = rs.getInt("amount");
				ShopCart shopCart = new ShopCart(id, userId, wineId, amount);
				//根据酒水Id获得酒水信息保存在ShopCart对象中
				shopCart.setWineInfo(wineInfoDao.getwineInfo(wineId));
				list.add(shopCart);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return list;
	}

	/**
	 * 得到购物车中商品的种类数量
	 */
	public int getGoodAmount(String userId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int goodAmount = 0;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from shopcart where userId=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			while(rs.next()) {
				goodAmount++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return goodAmount;
	}

	/**
	 * 修改购物车中的商品数量
	 */
	@Override
	public void modifyWineAmount(int id, int wineAmount) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "update shopcart set amount=? where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, wineAmount);
			pst.setInt(2, id);
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
		
	}

	/**
	 * 根据购物车的Id，得到商品的id号
	 */
	@Override
	public int getWineId(int id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int wineId = 0;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select wineId from shopcart where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				wineId = rs.getInt("wineId");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return wineId;
	}

	/**
	 * 根据id获得酒水数量
	 */
	@Override
	public int getWineNumber(Integer id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int wineNumber = 0;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select amount from shopcart where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				wineNumber = rs.getInt("amount");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return wineNumber;
	}
	/**
	 * 根据Id删除购物车中的某酒品
	 */
	@Override
	public void deleteWine(Integer id) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "delete from shopcart where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
	}

	/**
	 * 检查该酒品是否在购物车中
	 */
	@Override
	public boolean checkWineInCart(int wineId, String userId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean b = false;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from shopcart where wineId=? and userId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, wineId);
			pst.setString(2, userId);
			rs = pst.executeQuery();
			if(rs.next()) {
				b = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return b;
	}

	@Override
	public ShopCart getWineAmount(int id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ShopCart cart = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from shopcart where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				cart = new ShopCart(rs.getInt("id"), rs.getString("userId"), rs.getInt("wineId"), rs.getInt("amount"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return cart;
	}

	@Override
	public void updateWineAmount(Integer amount,Integer id) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "update wine_info set wine_number=wine_number+"+ amount +" where wine_id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
	}

}

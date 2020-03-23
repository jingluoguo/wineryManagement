package com.dw.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dw.dao.WineInfoDao;
import com.dw.domain.WineInfo;
import com.dw.tools.JdbcUtil;


public class WineInfoDaoImpl implements WineInfoDao{
	
	/**
	 * 根据酒水Id(酒水编号)得到酒水的信息。
	 * @param wineId
	 * @return
	 */
	public WineInfo getwineInfo(Integer wineId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		WineInfo wineInfo = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from wine_info where wine_id=" + wineId;
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				String wineName = rs.getString("wine_name");
				double winePrice = rs.getDouble("wine_price");
				int wineNumber = rs.getInt("wine_number");
				String wineImg = rs.getString("wine_img");
				String wineDec = rs.getString("wine_dec");
				String wineCategory = rs.getString("wine_category"); 
				wineInfo = new WineInfo(wineId, wineName, winePrice, wineNumber, wineImg, wineDec, wineCategory);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return wineInfo;
	}

	/**
	 * 返回所有商品信息
	 */
	@Override
	public List<WineInfo> getWineInfos() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<WineInfo> wineInfos = new ArrayList<WineInfo>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from wine_info";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				int wineId = rs.getInt("wine_id");
				String wineName = rs.getString("wine_name");
				double winePrice = rs.getDouble("wine_price");
				int wineNumber = rs.getInt("wine_number");
				String wineImg = rs.getString("wine_img");
				String wineDec = rs.getString("wine_dec");
				String wineCategory = rs.getString("wine_category"); 
				wineInfos.add(new WineInfo(wineId, wineName, winePrice, wineNumber, wineImg, wineDec, wineCategory));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return wineInfos;
		
	}

	/**
	 * 更新酒水信息
	 */
	@Override
	public void updateWineInfo(WineInfo wineInfo) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "update wine_info set wine_number=? where wine_id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, wineInfo.getWineNumber());
			pst.setInt(2, wineInfo.getWineId());
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
		
	}

	/**
	 * 修改库存量
	 */
	@Override
	public boolean modifyWineAmount(int wineId, int i) {
		WineInfo wineInfo = this.getwineInfo(wineId);
		wineInfo.setWineNumber(wineInfo.getWineNumber() + i);
		if(wineInfo.getWineNumber() < 0) {
			return  false;
		}
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "update wine_info set wine_number=? where wine_id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, wineInfo.getWineNumber());
			pst.setInt(2, wineInfo.getWineId());
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
		return true;
	}

	@Override
	public List<WineInfo> selectByCondition(String category, Double maxPrice, Double minPrice) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<WineInfo> wineInfos = new ArrayList<WineInfo>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from wine_info where 1=1";
			if(category != null) {
				sql +=" and wine_category like '%" + category + "%'";
			}
			if(minPrice != null) {
				sql +=" and wine_price>=" + minPrice;
			}
			if(maxPrice != null) {
				sql +=" and wine_price<=" + maxPrice;
			}
			sql += " order by wine_price";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				int wineId = rs.getInt("wine_id");
				String wineName = rs.getString("wine_name");
				double winePrice = rs.getDouble("wine_price");
				int wineNumber = rs.getInt("wine_number");
				String wineImg = rs.getString("wine_img");
				String wineDec = rs.getString("wine_dec");
				String wineCategory = rs.getString("wine_category"); 
				wineInfos.add(new WineInfo(wineId, wineName, winePrice, wineNumber, wineImg, wineDec, wineCategory));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return wineInfos;
	}

}

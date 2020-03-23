package com.dw.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dw.dao.LevelDao;
import com.dw.domain.Level;
import com.dw.domain.Settlement;
import com.dw.domain.User;
import com.dw.service.SettlementService;
import com.dw.tools.JdbcUtil;

public class LevelDaoImpl implements LevelDao {

	QueryRunner queryrunner = new QueryRunner();
	@Override
	public void insertRecord(Level level) {
		Connection connection = null;
		String sql = "INSERT into level(level_phone,level_fID)  VALUES(?,?)";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql, new Object[] {level.getLevel_phone(),level.getLevel_fID() });
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		
	}
	/**
	 * 根据用户身份证号获得下级的数量
	 */
	@Override
	public Integer getNextAmount(String userId) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select  count(*) from level where level_fID like '%" + userId +"%'";
		int i = 0;
		try {
			connection = JdbcUtil.getConnection();
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()){
				i = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, connection);
		}
		return i;
	}
	
	//返回所有下级的身份证号信息
	public List<String> getNextIds(String userId) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> nextIds = new ArrayList<>();
		List<User> users = new ArrayList<User>();
		String sql = "select level_ID from level where level_fID like '%" + userId +"%' order by level_fID";
		try {
			connection = JdbcUtil.getConnection();
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				nextIds.add(rs.getString("level_ID"));
				users.add(new User());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, connection);
		}
		return nextIds;
	}
	
	//返回所有下级的身份证号信息和名称
	public List<User> getNextIdAll(String userId) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		String sql = "select u_name,u_ID from user where u_ID in (select level_ID from level where level_fID like '%" + userId +"%')";
		try {
			connection = JdbcUtil.getConnection();
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				String uName = rs.getString("u_name");
				String uID = rs.getString("u_ID");
				//nextIds.add(rs.getString("level_ID"));
				users.add(new User(uName,uID));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, connection);
		}
		return users;
	}
	
	/**
	 * 根据用户身份证号获得上级的身份证号
	 */
	@Override
	public String getPreUserID(String userID) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String FID = "";
		String sql = "select  level_fID from level where level_ID=?";
		try {
			connection = JdbcUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setString(1, userID);
			rs = pst.executeQuery();
			if(rs.next()){
				FID = rs.getString("level_fID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, connection);
		}
		if(FID.length() > 2) {
			return FID.substring(0, FID.length()-2);
		}
		return FID;
	}
	
	private SettlementService settlementService = new SettlementService();
	@Override
	public void updatePreSale(Settlement settle) {
		Connection connection = null;
		double procedures = 0.25;
		double userTotal = settlementService.acountTotal(settle.getS_ID());
		if(userTotal > 360000){
			procedures = 0.10;
		}
		String sql = "UPDATE  settlement SET s_ID=s_ID ";//s_sale=?,s_total=s_total+s_sale  where s_ID=?";
		if(settle.getS_sale() != 0){
			//sql += ",s_sale=s_sale+" + settle.getS_sale() + ",s_total=s_total+" + settle.getS_sale() + ",s_balance=s_balance+" + settle.getS_sale()  ;
			sql += ",s_sale=s_sale+" + settle.getS_sale() + ",s_total=s_total+" + (settle.getS_sale()*(1-(procedures+0.05))) + ",s_balance=s_balance+" + settle.getS_sale() + ",s_fund=s_fund+" + settle.getS_sale()*procedures + ",s_tax=s_tax+" + settle.getS_sale()*0.05 ;
		}
		if(settle.getS_achievement() != 0){
			//sql +=",s_achievement=s_achievement+" + settle.getS_achievement() + ",s_total=s_total+" + settle.getS_achievement() + ",s_balance=s_balance+" + settle.getS_achievement() ;
			sql +=",s_achievement=s_achievement+" + settle.getS_achievement() + ",s_total=s_total+" + (settle.getS_achievement()*(1-(procedures+0.05))) + ",s_balance=s_balance+" + settle.getS_achievement() + ",s_fund=s_fund+" + settle.getS_achievement()*procedures + ",s_tax=s_tax+" + settle.getS_achievement()*0.05;
		}
		if(settle.getS_Deconsume() != 0){
			sql +=",s_Deconsume=s_Deconsume+ "+ settle.getS_Deconsume() +",s_total=s_total-"+ settle.getS_Deconsume();
		}
		if(settle.getS_Winconsume() != 0){
			sql +=",s_Winconsume = s_Winconsume+ "+ settle.getS_Winconsume() +",s_total=s_total-"+ settle.getS_Winconsume();
		}
		sql += " where s_ID=?";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql, new Object[] {settle.getS_ID()});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		
	}
	@Override
	public boolean updateFID(String fID, String userID) {
		Connection connection = null;
		String sql = "UPDATE level SET level_fID = ? WHERE level_ID = ?";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql, new Object[] { fID,userID  });
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return false;
	}
	@Override
	public Integer checkFIDNextAmount(String userFID) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer amount = 0;
		String sql = "select count(*) from level where level_fID=?";
		try {
			connection = JdbcUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setString(1, userFID);
			rs = pst.executeQuery();
			if(rs.next()){
				amount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, connection);
		}
		return amount;
	}
	@Override
	public String getNextLeftId(String userId) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String Lid = null;
		userId += "&L";
		String sql = "select level_ID from level where level_fID=?";
		try {
			connection = JdbcUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			if(rs.next()){
				Lid = rs.getString("level_ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, connection);
		}
		return Lid;
	}
	@Override
	public String getNextRightId(String userId) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String Rid = null;
		userId += "&R";
		String sql = "select level_ID from level where level_fID=?";
		try {
			connection = JdbcUtil.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			if(rs.next()){
				Rid = rs.getString("level_ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, connection);
		}
		return Rid;
	}
	@Override
	public boolean checkNID(String userID) {
		Connection connection = null;
		String sql = "select * from level WHERE level_fID like '%O%' and level_ID = ?";
		try {
			boolean j = false;
			connection = JdbcUtil.getConnection();
			Object o = queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {userID});
			if(o != null){
				j = true;
			}
			
			return j;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return false;
	}
}

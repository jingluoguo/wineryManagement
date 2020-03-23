package com.dw.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.dw.dao.BonusDao;
import com.dw.domain.BonusRecord;
import com.dw.domain.Cash;
import com.dw.domain.Page;
import com.dw.domain.bonus;
import com.dw.tools.JdbcUtil;


public class BonusDaoImpl implements BonusDao{

	QueryRunner queryrunner = new QueryRunner();
	
	public int updateasettlementach(String userId,double useracount) {
		Connection connection = null;
		useracount = (double)Math.round(useracount*100)/100;
		String sql1 = "UPDATE settlement SET s_achievement = s_achievement +" + useracount + ", s_total = s_total + " + useracount + " WHERE s_ID = ?";
		//System.out.println(sql1);
		try {
			connection = JdbcUtil.getConnection();
			//System.out.println(sql1);
			queryrunner.update(connection, sql1, new Object[] {userId });
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return 1;
	}
	
	
	@Override
	public int insertbonus(bonus bon) {
		Connection connection = null;
		String sql = "INSERT into bonus  VALUES(?,?,?,?,?,?)";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql, new Object[] { null,bon.getB_bID(),bon.getB_ID(),bon.getB_year(),bon.getB_month(),bon.getB_money()});
			//System.out.println(i);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return 0;
	}
	
	@Override
	public int insertBonusRecord(BonusRecord bonusRecord) {
		Connection connection = null;
		String sql = "INSERT into bonus_record  VALUES(?,?,?,?,?,?)";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql, new Object[] { null,bonusRecord.getR_name(),bonusRecord.getR_ID(),bonusRecord.getR_FID(),bonusRecord.getR_time(),bonusRecord.getR_money()});
			//System.out.println(i);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return 0;
	}
	
	@Override
	public List<String> getAll() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> ids = new ArrayList<>();
		try{
			con = JdbcUtil.getConnection();
			String sql = "SELECT DISTINCT(b_ID) FROM bonus";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				ids.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs, pst, con);
		}
		return ids;
	}
	
	
	@Override
	public double getAmountReward(String userId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		double AmountReward = 0.0;
		try{
			con = JdbcUtil.getConnection();
			String sql = "SELECT SUM(b_money) FROM bonus where b_ID=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			while(rs.next()){
				AmountReward += rs.getDouble(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs, pst, con);
		}
		return AmountReward;
	}


	@Override
	public void deleteRecord() {
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = JdbcUtil.getConnection();
			String sql = "delete FROM bonus";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(null, pst, con);
		}
	}


	@Override
	public void updateRecord(String b_bID,String b_ID) {
		Connection connection = null;
		//String sql1 = "UPDATE bonus SET b_ID =" + b_ID + " WHERE b_bID = ?";
		String sql1 = "UPDATE bonus SET b_ID =? WHERE b_bID = ?";
		//System.out.println(sql1);
		try {
			connection = JdbcUtil.getConnection();
			//System.out.println(sql1);
			queryrunner.update(connection, sql1, new Object[] {b_ID,b_bID });
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
	}

	@Override
	public List<BonusRecord> selectBonusRecords(String startTime, String endTime, Page page) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<BonusRecord> bonusRecords = new ArrayList<BonusRecord>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from bonus_record where 1=1";
			if(startTime != "" && !"".equals(startTime) ){
				sql += " and r_time between '" + startTime;
			}
			if(endTime != "" && !"".equals(endTime)){
				sql += "' and '" + endTime + "'";
			}
			sql += " limit "+page.getStart() +","+page.getSize();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String r_name = rs.getString("r_name");
				String r_ID = rs.getString("r_ID");
				String r_FID = rs.getString("r_FID");
				String r_time = rs.getString("r_time");
				double r_money = rs.getDouble("r_money");
				bonusRecords.add(new BonusRecord(id,r_name,r_ID,r_FID,r_time,r_money));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return bonusRecords;
	}
	@Override
	public Integer selectRecords(String startTime, String endTime) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer i = 0;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from bonus_record where 1=1";
			if(startTime != "" && !"".equals(startTime) ){
				sql += " and r_time between '" + startTime;
			}
			if(endTime != "" && !"".equals(endTime)){
				sql += "' and '" + endTime + "'";
			}
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				i++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return i;
	}
}

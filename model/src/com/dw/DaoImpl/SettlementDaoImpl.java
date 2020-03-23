package com.dw.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dw.dao.SettlementDao;
import com.dw.domain.Page;
import com.dw.domain.Settlement;
import com.dw.service.UserService;
import com.dw.tools.JdbcUtil;



public class SettlementDaoImpl implements SettlementDao {

	QueryRunner queryrunner = new QueryRunner();
	UserService userService = new UserService();

	// 根据用户id查询账户id，返回账户id


	// 得到用户账户的余额
	public List<Settlement> acounts(String userId) {
		Connection connection = null;
		String sql = "SELECT * FROM settlement WHERE s_ID=?";
		try {
			connection = JdbcUtil.getConnection();
			return (List<Settlement>) queryrunner.query(connection, sql, new BeanListHandler(Settlement.class),
					new Object[] { userId });
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return null;
	}

	//修改账户等级
	public String selectUserInfor(String level, String userId) {
		Connection connection = null;
		String sql1 = "UPDATE settlement SET s_level = ? WHERE s_ID = ?";
		String sql2 = "UPDATE user SET u_level = ? WHERE u_ID = ?";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql1, new Object[] { level,  userId });
			queryrunner.update(connection, sql2, new Object[] { level,  userId });
			return level;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return null;
	}
	
	/**
	 * 酒水兑换
	 */
	@Override
	public int updateacountsacounts(String userId,double useracount, double acount, String level) {
		Connection connection = null;
		double total = useracount - acount;
		total = (double)Math.round(total*100)/100;
		String sql1 = "UPDATE settlement SET s_deposit = 0, s_total = " + total + ", s_Winconsume = s_Winconsume + " + acount + " WHERE s_ID = ?";
		
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql1, new Object[] {userId });
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return 1;
	}
	/**
	 * 现金提现时更新s_Deconsume
	 */
	@Override
	public int updatecash(String userId, double useracount, double cash) {
		Connection connection = null;
//		double procedures = 0.25;
//		double userTotal = settlementService.acountTotal(userId);
//		if(userTotal > 360000){
//			procedures = 0.10;
//		}
		double total = useracount - cash;
		total = (double)Math.round(total*100)/100;
		String sql1 = "UPDATE settlement SET s_total = " + total +  ", s_Deconsume = s_Deconsume + " + cash + " WHERE s_ID = ?";
		
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql1, new Object[] {userId });
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return 1;
	}

	/**
	 * 遍历所有settlement信息
	 */
	@Override
	public List<Settlement> getsettlementInfo(String userNo, String phone, String userName, String userID, String date) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Settlement> settlement = new ArrayList<Settlement>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			if("".equals(userNo) && "".equals(phone)  && "".equals(userID) && "".equals(date)){
				sql = "select * from settlement";
			}
			else
			{
				sql = "select * from settlement WHERE 1=1 ";
				if(!"".equals(userNo) && userNo != null){
					sql += " and id like '%" + userNo+ "%'";
					
				}
				if(!"".equals(userID) && userID != null){
					sql += " and s_ID like '%" + userID+ "%'";
				}
				if(!"".equals(date) && date != null){
					sql += " and s_date like '%" + date+ "%'";
				}
				if(!"".equals(phone) && phone != null){
					String u_ID = userService.selectUserID(phone);
					sql += " and s_ID like '%" + u_ID+ "%'";
				}
			}
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String s_name = rs.getString("s_name");
				String s_ID = rs.getString("s_ID");
				String s_level = rs.getString("s_level");
				float s_sale = rs.getFloat("s_sale");
				float s_achievement = rs.getFloat("s_achievement");
				float s_Deconsume = rs.getFloat("s_Deconsume");
				float s_Winconsume = rs.getFloat("s_Winconsume");
				float s_deposit = rs.getFloat("s_deposit");
				float s_balance = rs.getFloat("s_balance");
				float s_total = rs.getFloat("s_total");
				float s_big = rs.getFloat("s_big");
				float s_small = rs.getFloat("s_small");
				float s_fund = rs.getFloat("s_fund");
				String s_date = rs.getString("s_date");
				float s_tax = rs.getFloat("s_tax");
				String s_as_deposit = rs.getString("s_as_deposit");
				float s_cba = rs.getFloat("s_cba");
				settlement.add(new Settlement(s_level,s_name, s_sale, s_achievement, s_total, s_Winconsume, s_Deconsume,s_deposit, s_balance,s_date, s_ID, id,s_big,s_small,s_fund,s_tax,s_as_deposit,s_cba));
				
				//settlement.add(new User(uPhone, uName, uSex, uID, uRegDate, uLevel, uAdress,uPassword,uStatus));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return settlement;
	}

	@Override
	public void updateBigAndSmall(String userId, float small, float big, float f) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			String sql = "update settlement set s_big=?,s_small=?,s_cba=? where s_ID=?";
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setDouble(1, big);
			pst.setDouble(2, small);
			pst.setDouble(3, f);
			pst.setString(4, userId);
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
	}
	//获取数据库条数
	@Override
	public Integer getsettlementInfo() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer count = 0;
		String sql = "SELECT * FROM settlement";
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return count;
	}

	@Override
	public List<Settlement> getsettlementInfo(String userNo, String phone, String userName, String userID, String date,
			 Page page) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Settlement> settlement = new ArrayList<Settlement>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			if("".equals(userNo) && "".equals(phone)  && "".equals(userID) && "".equals(date)){
				sql = "select * from settlement";
			}
			else
			{
				sql = "select * from settlement WHERE 1=1 ";
				if(!"".equals(userNo) && userNo != null){
					sql += " and id like '%" + userNo+ "%'";
					
				}
				if(!"".equals(userID) && userID != null){
					sql += " and s_ID like '%" + userID+ "%'";
				}
				if(!"".equals(date) && date != null){
					sql += " and s_date like '%" + date+ "%'";
				}
				if(!"".equals(phone) && phone != null){
					String u_ID = userService.selectUserID(phone);
					sql += " and s_ID like '%" + u_ID+ "%'";
				}
			}
			sql += " limit "+page.getStart() +","+page.getSize();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String s_name = rs.getString("s_name");
				String s_ID = rs.getString("s_ID");
				String s_level = rs.getString("s_level");
				float s_sale = rs.getFloat("s_sale");
				float s_achievement = rs.getFloat("s_achievement");
				float s_Deconsume = rs.getFloat("s_Deconsume");
				float s_Winconsume = rs.getFloat("s_Winconsume");
				float s_deposit = rs.getFloat("s_deposit");
				float s_balance = rs.getFloat("s_balance");
				float s_total = rs.getFloat("s_total");
				float s_big = rs.getFloat("s_big");
				float s_small = rs.getFloat("s_small");
				float s_fund = rs.getFloat("s_fund");
				String s_date = rs.getString("s_date");
				float s_tax = rs.getFloat("s_tax");
				String s_as_deposit = rs.getString("s_as_deposit");
				float s_cba = rs.getFloat("s_cba");
				settlement.add(new Settlement(s_level,s_name, s_sale, s_achievement, s_total, s_Winconsume, s_Deconsume,s_deposit, s_balance,s_date, s_ID, id,s_big,s_small,s_fund,s_tax,s_as_deposit,s_cba));
				
				//settlement.add(new User(uPhone, uName, uSex, uID, uRegDate, uLevel, uAdress,uPassword,uStatus));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return settlement;
	}
}

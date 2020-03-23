package com.dw.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dw.dao.UserDao;
import com.dw.domain.Card;
import com.dw.domain.Cash;
import com.dw.domain.Page;
import com.dw.domain.Settlement;
import com.dw.domain.User;
import com.dw.domain.Recharge;
import com.dw.service.SettlementService;
import com.dw.tools.JdbcUtil;



public class UserDaoImpl implements UserDao {

	QueryRunner queryrunner = new QueryRunner();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

	/**
	 * @author jingluo
	 * @param User
	 * @return id
	 * 校验登录
	 * （非 Javadoc）
	 * @see com.dw.dao.UserDao#login(com.dw.domain.User)
	 */
	public Integer login(User user) {
		Connection connection = null;
		Integer i = null;
		//System.out.println(user.getuPhone());
		//System.out.println(user.getuID());
		String sql = "select id from user where u_phone=? and u_password=? ";
		try {
			connection = JdbcUtil.getConnection();
			i = (Integer) queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] { user.getuPhone(), user.getuID() });
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return i;
	}
	/**
	 * 获取用户名称
	 * @author jingluo
	 * @param u_ID
	 * @return u_name
	 * select user name
	 * @see com.dw.dao.UserDao#selectUserInfor(java.lang.String)
	 */
	public String selectUserInfor(String userphone){
		Connection connection = null;
		String i = "";
		String sql = "select  u_name from user where u_phone=?";
		try {
			connection = JdbcUtil.getConnection();
			i = (String) queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {userphone});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return i;
	}
	
	/**
	 * 获取用户名称
	 * @author jingluo
	 * @param u_ID
	 * @return u_name
	 * select user name
	 * @see com.dw.dao.UserDao#selectUserInfor(java.lang.String)
	 */
	public String selectUserInfors(String userID){
		Connection connection = null;
		String i = "";
		String sql = "select  u_name from user where u_ID=?";
		try {
			connection = JdbcUtil.getConnection();
			i = (String) queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {userID});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return i;
	}
	/**
	 * 获取用户级别
	 * @author jingluo
	 * @param u_ID
	 * @return String u_level
	 * Select user level from u_level where u_ID
	 */
	public String selectUserInforLevel(String userphone){
		Connection connection = null;
		String i = "";
		String sql = "select  u_level from user where u_phone=?";
		try {
			connection = JdbcUtil.getConnection();
			i = (String) queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {userphone});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return i;
	}
	/**
	 * 获取注册时间
	 * @author jingluo
	 * @param u_ID
	 */
	public String selectUserTime(String userphone) {
		Connection connection = null;
		String i = null;
		String sql = "select  u_regDate from user where u_phone=?";
		try {
			connection = JdbcUtil.getConnection();
			i = (String) queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {userphone});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return i;
	}
	/**
	 * 插入新用户
	 */
	@Override
	public int insertUser(User user,String userFID) {
		Connection connection = null;
		String sql = "INSERT into user  VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql, new Object[] { null,user.getuPhone(),user.getuName(),user.getuSex(),user.getuID(),user.getuRegistTime(),user.getuLevel(),user.getuAdress(),user.getuPassword(),0});
			//System.out.println(i);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return 0;
	}
	/**
	 * 为新用户生成settlement表
	 * @param userID
	 * @param level
	 * @param userFID
	 */
	public void insertSettlement(String userID,String userName, String level, String userFID){
		Connection con = null;
		double first = 0.0;
		if("业务员".equals(level)){
			first = 2000;
		}
		if("代理商".equals(level)){
			first = 10000;
		}
		if("销售经理".equals(level)){
			first = 30000;
		}
		if("销售总监".equals(level)){
			first = 50000;
		}
		String sql = "INSERT into settlement  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = JdbcUtil.getConnection();
			queryrunner.update(con, sql, new Object[] {null,userName,userID,level,0,0,0,0,0,first,0,0,0,0,df.format(new Date()),0,0,first,0});
			Settlement settle = new Settlement();
			settle.setS_sale((float)(first*0.2));
			settle.setS_ID(userFID);
			new LevelDaoImpl().updatePreSale(settle);
			//System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(con);
		}
	}
	
	/**
	 * 更新用户身份证信息
	 */
	@Override
	public boolean updateID(String userphone,String username, String userID,String level, String userFID) {
		Connection connection = null;
		String sql1 = "UPDATE user SET u_ID = ? WHERE u_phone = ?";
		String sql2 = "UPDATE level SET level_ID = ? WHERE level_phone = ?";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql1, new Object[] { userID,  userphone });
			queryrunner.update(connection, sql2, new Object[] { userID,  userphone });
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return false;
	}
	/**
	 * 根据手机号查询用户ID
	 */
	@Override
	public String selectUserID(String userphone) {
		Connection connection = null;
		String i = "";
		String sql = "select  u_ID from user where u_phone=?";
		try {
			connection = JdbcUtil.getConnection();
			i = (String) queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {userphone});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return i;
	}
	/**
	 * 根据身份证查询用户级别
	 */
	@Override
	public String selectUserLevel(String userID) {
		Connection connection = null;
		String i = "";
		String sql = "select  u_level from user where u_ID=?";
		try {
			connection = JdbcUtil.getConnection();
			i = (String) queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {userID});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return i;
	}
	/**
	 * 查询用户是否存在
	 */
	@Override
	public boolean checkID(String fID) {
		Connection connection = null;
		String sql = "select * from user WHERE u_ID = ?";
		try {
			boolean j = false;
			connection = JdbcUtil.getConnection();
			Object o = queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {fID});
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
	/**
	 * 查询手机号是否存在
	 */
	@Override
	public boolean checkPhone(String phone) {
		Connection connection = null;
		String sql = "select * from user WHERE u_phone = ?";
		try {
			boolean j = false;
			connection = JdbcUtil.getConnection();
			Object o = queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {phone});
			//System.out.println(o);
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
	//获取所有用户数量
		@Override
		public Integer getuserCount(String status) {
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			Integer count = 0;
			try {
				con = JdbcUtil.getConnection();
				String sql = "select * from user";
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
	//获取所有用户信息
	@Override
	public List<User> getuserInfo(String status, Page page) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			con = JdbcUtil.getConnection();
			//String sql = "select * from user WHERE u_status=" + status;
			String sql = "select * from user";
			sql += " limit "+page.getStart() +","+page.getSize();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				String uPhone = rs.getString("u_phone");
				String uName = rs.getString("u_name");
				String uSex = rs.getString("u_sex");
				String uID = rs.getString("u_ID");
				String uRegDate = rs.getString("u_regDate");
				String uLevel = rs.getString("u_level");
				String uAdress = rs.getString("u_adress");
				String uPassword = rs.getString("u_password"); 
				String uStatus = rs.getString("u_status"); 
				users.add(new User(uPhone, uName, uSex, uID, uRegDate, uLevel, uAdress,uPassword,uStatus));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return users;
		
	}
	/**
	 * 登录校验
	 */
	@Override
	public Integer logins(User user) {
		Connection connection = null;
		Integer i = 0;
		//System.out.println(user.getuPhone());
		//System.out.println(user.getuID());
		String sql = "select id from user where u_ID=? and u_password=?";
		try {
			connection = JdbcUtil.getConnection();
			i = (Integer) queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] { user.getuID(),user.getuPassword() });
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return i;
	}
	/**
	 * 更新用户审核状态
	 */
	@Override
	public boolean updateStatus(String userID, String userStatus) {
		Connection connection = null;
		String sql1 = "UPDATE user SET u_status = ? WHERE u_ID = ?";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql1, new Object[] { userStatus,userID });
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return false;
	}
	/**
	 * 查询银行卡
	 */
	@Override
	public Card selectCard(String userID) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Card card = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from card where card_ID=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, userID);
			rs = pst.executeQuery();
			if(rs.next()) {
				card = new Card( rs.getString("card_ID"), rs.getString("card_number"), rs.getString("card_address"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return card;
	}
	/**
	 * 插入银行卡
	 */
	@Override
	public void insertCard(Card card) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "insert into card(card_ID,card_number,card_address)  values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, card.getCard_ID());
			pst.setString(2, card.getCard_number());
			pst.setString(3, card.getCard_address());
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
	}
	/**
	 * 更新银行卡
	 */
	@Override
	public void updateCard(Card card) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "update card set card_number=?,card_address=? where card_ID=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, card.getCard_number());
			pst.setString(2, card.getCard_address());
			pst.setString(3, card.getCard_ID());
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
	}
	/**
	 * 插入提现记录
	 */
	private SettlementService settlementService = new SettlementService();
	@Override
	public void insertCash(Card card, double total, String userName) {
		double userTotal = settlementService.acountTotal(card.getCard_ID());
		double procedures = 0.25;
		if(userTotal > 360000){
			procedures = 0.10;
		}
		double cash = total*(1-procedures-0.05);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "insert into cash(c_ID,c_name,c_card,c_address,c_acount,c_total,c_status,c_date)  values(?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, card.getCard_ID());
			pst.setString(2, userName);
			pst.setString(3, card.getCard_number());
			pst.setString(4, card.getCard_address());
			pst.setDouble(5, cash);
			pst.setDouble(6, total);
			pst.setString(7, "0");
			pst.setString(8, df.format(new Date()));
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, pst, con);
		}
		
	}
	/**
	 * 获取所有的提现记录
	 */
	@Override
	public List<Cash> getAllCash(String status) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Cash> cash = new ArrayList<Cash>();
		try {
			con = JdbcUtil.getConnection();
			//String sql = "select * from user WHERE u_status=" + status;
			String sql = "select * from cash";
			sql += " order by c_date desc";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String c_ID = rs.getString("c_ID");
				String c_name = rs.getString("c_name");
				String c_card = rs.getString("c_card");
				String c_address = rs.getString("c_address");
				double c_account = rs.getDouble("c_acount");
				double c_total = rs.getDouble("c_total");
				String c_status = rs.getString("c_status");
				String c_date = rs.getString("c_date");
				cash.add(new Cash(id, c_name, c_ID, c_card, c_address, c_account, c_total, c_status, c_date));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return cash;
	}
	@Override
	public void updateCashStatus(String id,String cashID, String status) {
		Connection connection = null;
		String sql1 = "UPDATE cash SET c_status = ? WHERE c_ID = ? and id = ?";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql1, new Object[] { status,cashID,id });
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
	}
	/**
	 * 获取用户地址
	 */
	@Override
	public String selectUserAddress(String userID) {
		Connection connection = null;
		String i = "";
		String sql = "select  u_adress from user where u_ID=?";
		try {
			connection = JdbcUtil.getConnection();
			i = (String) queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {userID});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return i;
	}
	@Override
	public String selectUserPhone(String userID) {
		Connection connection = null;
		String i = "";
		String sql = "select  u_phone from user where u_ID=?";
		try {
			connection = JdbcUtil.getConnection();
			i = (String) queryrunner.query(connection, sql, new ScalarHandler(),
					new Object[] {userID});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return i;
	}
	/**
	 * 根据ID获取用户所有信息
	 */
	public List<User> getUserAll(String userPhone, String userID) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		String sql ="";
		try {
			con = JdbcUtil.getConnection();
			//String sql = "select * from user WHERE u_status=" + status;
			if(!"".equals(userID)){
				sql = "select * from user where u_ID=?";
				pst = con.prepareStatement(sql);
				pst.setString(1, userID);
			}else{
				sql = "select * from user where u_phone=?";
				pst = con.prepareStatement(sql);
				pst.setString(1, userPhone);
			}
			rs = pst.executeQuery();
			while(rs.next()) {
				String uPhone = rs.getString("u_phone");
				String uName = rs.getString("u_name");
				String uSex = rs.getString("u_sex");
				String uID = rs.getString("u_ID");
				String uRegDate = rs.getString("u_regDate");
				String uLevel = rs.getString("u_level");
				String uAdress = rs.getString("u_adress");
				String uPassword = rs.getString("u_password"); 
				String uStatus = rs.getString("u_status"); 
				users.add(new User(uPhone, uName, uSex, uID, uRegDate, uLevel, uAdress,uPassword,uStatus));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return users;
	}
	
	
	@Override
	public void updatePassword(String userId, String newPassword) {
		Connection connection = null;
		String sql1 = "UPDATE user SET u_password = ? WHERE u_ID = ?";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql1, new Object[] { newPassword,userId });
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
	}
	/**
	 * 获取所有的userID
	 */
	@Override
	public List<User> getAllUserId() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		String sql ="";
		try {
			con = JdbcUtil.getConnection();
			sql = "select u_ID,u_level from user";
			sql += " order by u_regDate desc";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setuID(rs.getString("u_ID"));
				user.setuLevel(rs.getString("u_level"));
				users.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return users;
	}
	/**
	 * 实现用户充值
	 */
	@Override
	public void Recharge(String userId, String recharge,String userName, String id, String ordernumber) {
		if(!"".equals(userId)&&!"".equals(recharge)){
			Connection connection = null;
			String sql1 = "UPDATE settlement SET s_total = s_total+" + recharge  +" WHERE s_ID = ?";
			String sql = "INSERT into recharge  VALUES(?,?,?,?,?,?,?)";
			String sql2 = "UPDATE recharge SET r_status = 1 WHERE id = ?";
			try {
				connection = JdbcUtil.getConnection();
				if("".equals(id)){
					queryrunner.update(connection, sql, new Object[] {null,userName,userId,recharge,ordernumber,df.format(new Date()),0});
				}else{
					queryrunner.update(connection, sql1, new Object[] {userId });
					queryrunner.update(connection, sql2, new Object[] {id });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.releaseConnection(connection);
			}
		}
	}
	/**
	 * 筛选Cash的记录
	 */
	@Override
	public List<Cash> selectCash(String phone, String userID, String card,String date,Page page) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Cash> cashs = new ArrayList<Cash>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			if("".equals(phone) && "".equals(userID)  && "".equals(card) && "".equals(date)){
				sql = "select * from cash";
			}
			else
			{
				sql = "select * from cash WHERE 1=1 ";
				if(!"".equals(phone) && phone != null){
					String u_ID = selectUserID(phone);
					sql += "and c_ID like '%" + u_ID+ "%'";
				}
				if(!"".equals(userID) && userID != null){
					sql += "and c_ID like '%" + userID+ "%'";
				}
				if(!"".equals(card) && card != null){
					sql += "and c_card like '%" + card+ "%'";
				}
				if(!"".equals(date) && date != null){
					sql += "and c_date like '%" + date+ "%'";
				}
			}
			sql += " order by c_date desc";
			sql += " limit "+page.getStart() +","+page.getSize();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String c_name = rs.getString("c_name");
				String c_ID = rs.getString("c_ID");
				String c_card = rs.getString("c_card");
				String c_address = rs.getString("c_address");
				float c_acount = rs.getFloat("c_acount");
				float c_total = rs.getFloat("c_total");
				String c_status = rs.getString("c_status");
				String c_date = rs.getString("c_date");
				cashs.add(new Cash(id,c_name, c_ID, c_card, c_address, c_acount, c_total, c_status,c_date));
				//settlement.add(new User(uPhone, uName, uSex, uID, uRegDate, uLevel, uAdress,uPassword,uStatus));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return cashs;
	}
	/**
	 * 筛选Cash的记录
	 */
	@Override
	public List<Cash> selectCash(String phone, String userID, String card,String date) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Cash> cashs = new ArrayList<Cash>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			if("".equals(phone) && "".equals(userID)  && "".equals(card) && "".equals(date)){
				sql = "select * from cash";
			}
			else
			{
				sql = "select * from cash WHERE 1=1 ";
				if(!"".equals(phone) && phone != null){
					String u_ID = selectUserID(phone);
					sql += "and c_ID like '%" + u_ID+ "%'";
				}
				if(!"".equals(userID) && userID != null){
					sql += "and c_ID like '%" + userID+ "%'";
				}
				if(!"".equals(card) && card != null){
					sql += "and c_card like '%" + card+ "%'";
				}
				if(!"".equals(date) && date != null){
					sql += "and c_date like '%" + date+ "%'";
				}
			}
			sql += " order by c_date desc";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String c_name = rs.getString("c_name");
				String c_ID = rs.getString("c_ID");
				String c_card = rs.getString("c_card");
				String c_address = rs.getString("c_address");
				float c_acount = rs.getFloat("c_acount");
				float c_total = rs.getFloat("c_total");
				String c_status = rs.getString("c_status");
				String c_date = rs.getString("c_date");
				cashs.add(new Cash(id,c_name, c_ID, c_card, c_address, c_acount, c_total, c_status,c_date));
				//settlement.add(new User(uPhone, uName, uSex, uID, uRegDate, uLevel, uAdress,uPassword,uStatus));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return cashs;
	}
	/**
	 * 筛选Cash的记录数量
	 */
	@Override
	public Integer selectCash(String phone, String userID, String card) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer count = 0;
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			if("".equals(phone) && "".equals(userID)  && "".equals(card)){
				sql = "select * from cash";
			}
			else
			{
				sql = "select * from cash WHERE 1=1 ";
				if(!"".equals(phone) && phone != null){
					String u_ID = selectUserID(phone);
					sql += "and c_ID like '%" + u_ID+ "%'";
				}
				if(!"".equals(userID) && userID != null){
					sql += "and c_ID like '%" + userID+ "%'";
				}
				if(!"".equals(card) && card != null){
					sql += "and c_card like '%" + card+ "%'";
				}
			}
			sql += " order by c_date desc";
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
	 * 查询充值记录数量
	 */
	@Override
	public Integer selectRechargeCount(String phone, String userID) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer count = 0;
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			if("".equals(phone) && "".equals(userID)){
				sql = "select * from recharge";
			}
			else
			{
				sql = "select * from recharge WHERE 1=1 ";
				if(!"".equals(phone) || phone != null){
					String u_ID = selectUserID(phone);
					sql += "and r_ID like '%" + u_ID+ "%'";
				}
				if(!"".equals(userID) || userID != null){
					sql += "and r_ID like '%" + userID+ "%'";
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
	 * 查询充值记录
	 */
	@Override
	public List<Recharge> selectRecharge(String phone, String userID, Page page) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Recharge> recharges = new ArrayList<Recharge>();
		try {
			con = JdbcUtil.getConnection();
			String sql = "";
			if("".equals(phone) && "".equals(userID)){
				sql = "select * from recharge";
			}
			else
			{
				sql = "select * from recharge WHERE 1=1 ";
				if(!"".equals(phone) || phone != null){
					String u_ID = selectUserID(phone);
					sql += "and r_ID like '%" + u_ID+ "%'";
				}
				if(!"".equals(userID) || userID != null){
					sql += "and r_ID like '%" + userID+ "%'";
				}
			}
			sql += " order by r_date desc";
			sql += " limit "+page.getStart() +","+page.getSize();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String r_name = rs.getString("r_name");
				String r_ID = rs.getString("r_ID");
				String r_acount = rs.getString("r_acount");
				String r_ordernumber = rs.getString("r_ordernumber");
				String r_date = rs.getString("r_date");
				String r_status = rs.getString("r_status");
				recharges.add(new Recharge(id,r_name, r_ID, r_acount, r_ordernumber, r_date, r_status));
				//settlement.add(new User(uPhone, uName, uSex, uID, uRegDate, uLevel, uAdress,uPassword,uStatus));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return recharges;
	}
	/**
	 * 查询用户数量
	 */
	public Integer SerachUserCount(String userNo, String phone, String userName, String userID, String date) {
		Connection connection = null;
		String sql = "select u_phone,u_name,u_sex,u_ID,u_level,u_adress,u_regDate,u_password,u_status from user WHERE 1=1 ";
		Integer count = 0;
		if(!"".equals(userNo)){
			sql += "and id like '%" + userNo+ "%'";
		}
		if(!"".equals(phone)){
			sql += "and u_phone like '%" + phone+ "%'";
		}
		if(!"".equals(userName)){
			sql += "and u_name like '%" + userName+ "%'";
		}
		if(!"".equals(userID)){
			sql += "and u_ID like '%" + userID+ "%'";
		}
		if(!"".equals(date)){
			sql += "and u_regDate like '%" + date+ "%'";
		}
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = JdbcUtil.getConnection();
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				count++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, connection);
		}
		return count;
	}
	/**
	 * 条件检索用户信息
	 */
	public List<User> SerachUserByCondition(String userNo, String phone, String userName, String userID, String date,Page page) {
		Connection connection = null;
		String sql = "select u_phone,u_name,u_sex,u_ID,u_level,u_adress,u_regDate,u_password,u_status from user WHERE 1=1 ";
		List<User> users = new ArrayList<User>();
		if(!"".equals(userNo)){
			sql += "and id like '%" + userNo+ "%'";
		}
		if(!"".equals(phone)){
			sql += "and u_phone like '%" + phone+ "%'";
		}
		if(!"".equals(userName)){
			sql += "and u_name like '%" + userName+ "%'";
		}
		if(!"".equals(userID)){
			sql += "and u_ID like '%" + userID+ "%'";
		}
		if(!"".equals(date)){
			sql += "and u_regDate like '%" + date+ "%'";
		}
		sql += " limit "+page.getStart() +","+page.getSize();
		//System.out.println(sql);
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = JdbcUtil.getConnection();
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				String uPhone = rs.getString("u_phone");
				String uName = rs.getString("u_name");
				String uSex = rs.getString("u_sex");
				String uID = rs.getString("u_ID");
				String uRegDate = rs.getString("u_regDate");
				String uLevel = rs.getString("u_level");
				String uAdress = rs.getString("u_adress");
				String uPassword = rs.getString("u_password"); 
				String uStatus = rs.getString("u_status"); 
				users.add(new User(uPhone, uName, uSex, uID, uRegDate, uLevel, uAdress,uPassword,uStatus));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, connection);
		}
		return users;
	}
}
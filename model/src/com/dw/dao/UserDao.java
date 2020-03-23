package com.dw.dao;

import java.util.Date;
import java.util.List;

import com.dw.domain.Card;
import com.dw.domain.Cash;
import com.dw.domain.Page;
import com.dw.domain.User;
import com.dw.domain.WineInfo;
import com.dw.domain.Recharge;


public interface UserDao {

	public Integer SerachUserCount(String userNo, String phone, String userName, String userID, String date);
	//条件检索用户
	public List<User> SerachUserByCondition(String author, String title, String name, String price1, String price2,Page page);
	//所有用户信息
	public List<User> getuserInfo(String status, Page page);
	//所有用户数量
	public Integer getuserCount(String status);
	
	// 用户登录
	Integer login(User user);

	String selectUserTime(String userphone);

	String selectUserInforLevel(String userphone);
	
	String selectUserInfor(String userphone);
	
	String selectUserInfors(String userID);

	int insertUser(User user, String userFID);

	boolean updateID(String userphone,String username, String userID,String level, String userFID );
	
	void insertSettlement(String userID,String username,String level,String userFID);

	String selectUserID(String userphones);

	boolean checkID(String fID);

	boolean checkPhone(String phone);
	
	public boolean updateStatus(String userID, String userStatus);

	public Integer logins(User user);

	public Card selectCard(String userID);

	public void insertCard(Card card);

	public void updateCard(Card card);

	public void insertCash(Card card, double cash, String userName);

	public List<Cash> getAllCash(String status);

	public void updateCashStatus(String id,String cashID, String status);

	public String selectUserAddress(String userID);

	public String selectUserPhone(String userID);

	public List<User> getUserAll(String userPhone, String userID);

	public void updatePassword(String userId, String newPassword);

	public List<User> getAllUserId();

	public void Recharge(String userId, String recharge, String userName, String status, String r_ordernumber);

	public List<Cash> selectCash(String phone, String userID, String card, String date, Page page);

	public List<Cash> selectCash(String phone, String userID, String card, String date);

	public List<Recharge> selectRecharge(String phone, String userID,Page page);
	
	public Integer selectRechargeCount(String phone, String userID);

	public String selectUserLevel(String userID);

	public Integer selectCash(String phone, String userID, String card);

	

}

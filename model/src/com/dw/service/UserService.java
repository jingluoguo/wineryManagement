package com.dw.service;

import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import com.dw.DaoImpl.LevelDaoImpl;
import com.dw.DaoImpl.UserDaoImpl;
import com.dw.dao.LevelDao;
import com.dw.dao.UserDao;
import com.dw.domain.Card;
import com.dw.domain.Cash;
import com.dw.domain.Page;
import com.dw.domain.User;
import com.dw.domain.Recharge;



public class UserService {

	UserDao userDao = new UserDaoImpl();
	LevelDao levelDao = new LevelDaoImpl();

	/**
	 * 获得所有用户
	 * @param page 
	 * @return
	 */
	public List<User> getUserInfoAll(String status, Page page) {
		return userDao.getuserInfo(status,page);
	}
	/**
	 * 获得所有用户数量
	 * @return
	 */
	public Integer getUserCount(String status) {
		return userDao.getuserCount(status);
	}
	
	/**
	 * 获取用户信息
	 * @param user
	 * @return
	 */
	public List<User> getUserAll(String userPhone,String userID){
		return userDao.getUserAll(userPhone,userID);
	}
	// 登录验证
	public Integer login(User user) {
		Integer login = userDao.login(user);
		return login;
	}
	// 筛选时间
	public String selectUserTime(String userphone){
		String UserInfo=userDao.selectUserTime(userphone);
		return UserInfo;
	}
	// 筛选用户信息通过手机号
	public String selectUserInfor(String userphone) {
		String UserInfo=userDao.selectUserInfor(userphone);
		return UserInfo;
	}
	// 筛选用户信息通过手机号
	public String selectUserLevel(String userID) {
		String UserInfo=userDao.selectUserLevel(userID);
		return UserInfo;
	}
	// 筛选地址通过ID
	public Object selectUserAddress(String userID) {
		String UserInfo=userDao.selectUserAddress(userID);
		return UserInfo;
	}
	
	// 筛选用户信息通过身份证
	public Object selectUserInfors(String userID) {
		String UserInfo=userDao.selectUserInfors(userID);
		return UserInfo;
	}
	
	// 筛选用户身份证
	public String selectUserID(String userphones) {
		String UserInfo=userDao.selectUserID(userphones);
		return UserInfo;
	}
	// 筛选用户级别
	public Object selectUserInforLevel(String userphone) {
		String UserInfo=userDao.selectUserInforLevel(userphone);
		return UserInfo;
	}
	// 插入用户
	public void insertUser(User user, String userFID) {
		userDao.insertUser(user,userFID);
		userDao.updateID(user.getuPhone(),user.getuName(),user.getuID(),user.getuLevel(),userFID);
		userDao.insertSettlement(user.getuID(), user.getuName(), user.getuLevel(), userFID);
	}
	/**
	 * 获取手机号
	 * @param phone
	 * @return
	 */
	public String selectUserPhone(String userID) {
		return userDao.selectUserPhone(userID);
	}
	public boolean checkID(String fID) {
		return userDao.checkID(fID);
	}
	/**
	 * 检查手机号，存在返回true
	 * @param phone
	 * @return
	 */
	public boolean checkPhone(String phone) {
		return userDao.checkPhone(phone);
	}

	//登录验证（身份证）
	public Integer logins(User user) {
		Integer login = userDao.logins(user);
		return login;
	}

	public boolean updateStatus(String userID, String userStatus) {
		// TODO 自动生成的方法存根
		return userDao.updateStatus(userID, userStatus);
	}
	
	/**
	 * 查询银行卡表中是否有该用户的记录
	 * @param userID
	 * @return
	 */
	public Card selectCard(String userID){
		return userDao.selectCard(userID);
	}

	/**
	 * 插入银行卡信息
	 * @param card
	 */
	public void insertCard(Card card) {
		userDao.insertCard(card);
	}
	
	/**
	 * 更新银行卡信息
	 * @param card
	 */
	public void updateCard(Card card) {
		userDao.updateCard(card);
	}
	//获取所有提现记录
	public List<Cash> getAllCash(String status){
		return userDao.getAllCash(status);
		
	}
	//插入提现记录
	public void insertCash(Card card, double cash, String userName) {
		userDao.insertCash(card,cash,userName);
		
	}
	//更新Cash状态
	public void updateCashStatus(String id,String cashID, String status) {
		userDao.updateCashStatus(id,cashID,status);
	}

	public void updatePassword(String userId, String newPassword) {
		userDao.updatePassword(userId,newPassword);
	}

	public List<User> getAllUserId() {
		return userDao.getAllUserId();
	}

	public void Recharge(String userId, String recharge, String userName, String status, String ordernumber) {
		userDao.Recharge(userId,recharge,userName,status,ordernumber);
	}

	public List<Cash> selectCash(String phone, String userID, String card, String date, Page page) {
		// TODO 自动生成的方法存根
		return userDao.selectCash(phone,userID,card,date,page);
	}
	
	public List<Cash> selectCash(String phone, String userID, String card, String date) {
		// TODO 自动生成的方法存根
		return userDao.selectCash(phone,userID,card,date);
	}
	public Integer selectCash(String phone, String userID, String card) {
		// TODO 自动生成的方法存根
		return userDao.selectCash(phone,userID,card);
	}
	// 条件检索用户数量
	public Integer SerachUserCount(String userNo, String phone, String userName, String userID, String date) {
		Integer list = userDao.SerachUserCount(userNo, phone, userName, userID, date);
		return list;
	}
	// 条件检索用户信息
	public List<User> SerachUserByCondition(String userNo, String phone, String userName, String userID, String date,Page page) {
		List<User> list = userDao.SerachUserByCondition(userNo, phone, userName, userID, date,page);
		return list;
	}
	//充值记录
	public List<Recharge> selectRecharge(String phone, String userID, Page page) {
		// TODO 自动生成的方法存根
		return userDao.selectRecharge(phone,userID,page);
	}
	//充值记录数量
	public int selectRechargeCount(String phone, String userID) {
		return userDao.selectRechargeCount(phone,userID);
	}
}

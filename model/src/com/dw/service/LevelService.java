package com.dw.service;

import java.util.List;

import com.dw.DaoImpl.LevelDaoImpl;
import com.dw.dao.LevelDao;
import com.dw.domain.Level;
import com.dw.domain.Settlement;
import com.dw.domain.User;

public class LevelService {
	
	LevelDao levelDao = new LevelDaoImpl();
	UserService service = new UserService();
	
	public void insertRecord(Level level){
		levelDao.insertRecord(level);
	}

	public Integer getNextAmount(String userId) {
		return levelDao.getNextAmount(userId);
	}
	
	/**
	 * 获取上级ID
	 * @param userID
	 * @return
	 */
	public String getPreUserID(String userID) {
		return levelDao.getPreUserID(userID);
	}
	
	public void updatePreSale(Settlement settle){
		levelDao.updatePreSale(settle);
	}
	
	public List<String> getNextIds(String userId){
		return levelDao.getNextIds(userId);
	}
	public List<User> getNextIdAll(String userId){
		return levelDao.getNextIdAll(userId);
	}

	//返回下级个数
	public Integer checkFIDNextAmount(String userFID) {
		return levelDao.checkFIDNextAmount(userFID);
	}

	//找左孩子ID
	public String getNextLeftId(String userId) {
		return levelDao.getNextLeftId(userId);
	}

	//找右孩子ID
	public String getNextRightId(String userId) {
		return levelDao.getNextRightId(userId);
	}
	//查找级别
	public String getLevelById(String userId) {
		return service.selectUserLevel(userId);
	}
//	//找左孩子Level
//	public String getNextLeftLevel(String userId) {
//		return levelDao.getNextLeftLevel(userId);
//	}
//
//	//找右孩子Level
//	public String getNextRightLevel(String userId) {
//		return levelDao.getNextRightLevel(userId);
//	}
//	public String getNextOLevel(String userId) {
//		return levelDao.getNextOLevel(userId);
//	}
	public boolean checkNID(String userID) {
		return levelDao.checkNID(userID);
	}
	public boolean updateNID(String fID, String userID){
		return levelDao.updateFID(fID, userID);
	}
	/*public boolean exchangeFID(String userID, String d, String organID) {
		return levelDao.exchangeFID(userID,d,organID);
	}*/
}

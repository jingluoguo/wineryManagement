package com.dw.service;

import java.util.List;

import com.dw.DaoImpl.BonusDaoImpl;
import com.dw.DaoImpl.ConsumeDaoImpl;
import com.dw.dao.BonusDao;
import com.dw.dao.ConsumeDao;
import com.dw.domain.BonusRecord;
import com.dw.domain.Page;
import com.dw.domain.bonus;
import com.dw.servlet.UserServlet;

public class BonusService {
	BonusDao bonusDao = new BonusDaoImpl();
	ConsumeDao consumeDao = new ConsumeDaoImpl();
	public void insertbonus(bonus bon) {
		/*检查是否是第一次消费
		if(consumeDao.checkRepeatConsume(bon.getB_ID())) {
			bon.setB_money(bon.getB_money()*0.7);
		}*/
		bonusDao.insertbonus(bon);
		//检查是否符合晋级要求
		UserServlet userServlet = new UserServlet();
		userServlet.promotion(bon.getB_ID());
	}
	
	/**
	 * 获取所有的Id，不重复
	 * @return
	 */
	public List<String> getAll() {
		return bonusDao.getAll();
	}
	
	/**
	 * 根据Id获得这个Id一周来积累给上级的奖励总和
	 * @param userName
	 * @return
	 */
	public double getAmountReward(String userId) {
		return bonusDao.getAmountReward(userId);
	}

	/**
	 * 删除指定id的所有记录
	 * @param userId
	 */
	public void deleteRecord() {
		bonusDao.deleteRecord();
	}

	/**
	 * 更改父级
	 * @param userId
	 */
	public void updateRecord(String b_bID,String b_ID) {
		bonusDao.updateRecord(b_bID,b_ID);
	}
	/**
	 * 记录bonu_record值
	 */
	public void insertBonusRecord(BonusRecord bonusRecord){
		bonusDao.insertBonusRecord(bonusRecord);
	}
	/**
	 * 查询bonus_record值
	 * @param page 
	 */
	public List<BonusRecord> selectBonusRecords(String startTime, String endTime, Page page){
		return bonusDao.selectBonusRecords(startTime, endTime,page);
	}
	public Integer selectRecords(String startTime, String endTime){
		return bonusDao.selectRecords(startTime, endTime);
	}
}

package com.dw.dao;

import java.util.List;

import com.dw.domain.BonusRecord;
import com.dw.domain.Page;
import com.dw.domain.User;
import com.dw.domain.bonus;

public interface BonusDao {

	int insertbonus(bonus bon);

	List<String> getAll();

	double getAmountReward(String userId);

	void deleteRecord();

	void updateRecord(String b_bID,String b_ID);

	int insertBonusRecord(BonusRecord bonusRecord);

	List<BonusRecord> selectBonusRecords(String startTime, String endTime, Page page);

	Integer selectRecords(String startTime, String endTime);

}

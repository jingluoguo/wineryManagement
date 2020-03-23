package com.dw.dao;

import java.util.List;

import com.dw.domain.Level;
import com.dw.domain.Settlement;
import com.dw.domain.User;

public interface LevelDao {
	
	public void insertRecord(Level level);

	public Integer getNextAmount(String userId);

	public String getPreUserID(String userID);

	public void updatePreSale(Settlement settle);

//	public void updateNID(String fID, String userID);
	
	public List<String> getNextIds(String userId);

	public Integer checkFIDNextAmount(String userFID);

	public String getNextLeftId(String userId);

	public String getNextRightId(String userId);

	public List<User> getNextIdAll(String userId);

	public boolean checkNID(String userID);

	public boolean updateFID(String fID, String userID);

//	public String getNextLeftLevel(String userId);
//
//	public String getNextRightLevel(String userId);
//
//	public String getNextOLevel(String userId);

	//public boolean exchangeFID(String userID, String d, String organID);

}

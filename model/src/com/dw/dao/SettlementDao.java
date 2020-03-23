package com.dw.dao;

import java.util.List;

import com.dw.domain.Page;
import com.dw.domain.Settlement;
import com.dw.domain.User;

public interface SettlementDao {

	//获取settlement总条数
	public Integer getsettlementInfo();
	//获取所有settlement
	public List<Settlement> getsettlementInfo(String userNo, String phone, String userName, String userID, String date);
	//获取账户余额
	List<Settlement> acounts(String userId);
	//获取所有settlement
	public List<Settlement> getsettlementInfo(String userNo, String phone, String userName, String userID, String date,Page page);
		
	String selectUserInfor(String level, String userId);

	int updateacountsacounts(String userId,double useracount, double acount, String level);

	int updatecash(String userId, double useracount, double cash);
	
	public void updateBigAndSmall(String userId, float small, float big, float f);

}

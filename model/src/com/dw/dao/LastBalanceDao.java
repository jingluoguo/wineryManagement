package com.dw.dao;


import com.dw.domain.LastBalance;

public interface LastBalanceDao {

	int insertlastBalance(LastBalance lastBalance);
	
	LastBalance getLastBalance(String user_ID);
}

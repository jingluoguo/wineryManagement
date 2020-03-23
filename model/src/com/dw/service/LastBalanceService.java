package com.dw.service;

import java.util.List;

import com.dw.DaoImpl.BonusDaoImpl;
import com.dw.DaoImpl.ConsumeDaoImpl;
import com.dw.DaoImpl.LastBalanceDaoImpl;
import com.dw.dao.BonusDao;
import com.dw.dao.ConsumeDao;
import com.dw.dao.LastBalanceDao;
import com.dw.domain.LastBalance;
import com.dw.domain.bonus;
import com.dw.servlet.UserServlet;

public class LastBalanceService {
	LastBalanceDao lastBalanceDao = new LastBalanceDaoImpl();
	public void insertbalance(LastBalance lastBalance) {
		lastBalanceDao.insertlastBalance(lastBalance);
	}
	public LastBalance getLastBalance(String user_ID){
		return lastBalanceDao.getLastBalance(user_ID);
	}
}

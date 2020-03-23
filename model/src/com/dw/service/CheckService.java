package com.dw.service;
import com.dw.DaoImpl.CheckDaoImpl;
import com.dw.dao.CheckDao;
import com.dw.domain.Check;

public class CheckService {
	CheckDao checkDao = new CheckDaoImpl();
	/**
	 * 查询是否有记录
	 * @param check
	 * @return
	 */
	public Integer check(Check check) {
		return checkDao.checkID(check);
	}
	/**
	 * 插入记录
	 * @param check
	 */
	public void insertCheck(Check check) {
		checkDao.insertCheck(check);
	}
}

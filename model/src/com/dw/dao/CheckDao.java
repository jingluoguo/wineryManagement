package com.dw.dao;

import com.dw.domain.Check;

public interface CheckDao {
	
	public int checkID(Check check);
	
	public int insertCheck(Check check);
}

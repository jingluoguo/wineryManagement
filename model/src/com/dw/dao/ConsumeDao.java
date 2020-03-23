package com.dw.dao;

import java.util.List;

import com.dw.domain.Consume;
import com.dw.domain.ConsumeExample;

public interface ConsumeDao {
	
	public void saveConsumeRecord(Consume consume);
 
	public boolean checkRepeatConsume(String cID,String cWine);

	public List<ConsumeExample> selectConsume(StringBuilder sql);
	public Integer ConsumeCount(StringBuilder sql);
}

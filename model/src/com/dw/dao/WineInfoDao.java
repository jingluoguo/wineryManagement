package com.dw.dao;


import java.util.List;

import com.dw.domain.WineInfo;

public interface WineInfoDao {
	
	public WineInfo getwineInfo(Integer wineId);

	public List<WineInfo> getWineInfos();

	public void updateWineInfo(WineInfo wineInfo);

	public boolean modifyWineAmount(int wineId, int i);
	
	public List<WineInfo> selectByCondition(String category, Double maxPrice, Double minPrice);

}

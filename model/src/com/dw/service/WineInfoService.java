package com.dw.service;

import java.util.List;

import com.dw.DaoImpl.WineInfoDaoImpl;
import com.dw.dao.WineInfoDao;
import com.dw.domain.WineInfo;


public class WineInfoService {
	
	private WineInfoDao wineInfoDao = (WineInfoDao) new WineInfoDaoImpl();
	
	/**
	 * 根据主键获得酒水信息
	 * @param wineId
	 * @return
	 */
	public WineInfo getwineInfo(Integer wineId) {
		return wineInfoDao.getwineInfo(wineId);
	}

	/**
	 * 获得所有酒品信息
	 * @return
	 */
	public List<WineInfo> getWineInfoAll() {
		return wineInfoDao.getWineInfos();
	}

	/**
	 * 根据酒水Id获取一个酒水信息
	 * @param wineId
	 * @return
	 */
	public WineInfo getWine(Integer wineId) {
		return wineInfoDao.getwineInfo(wineId);
	}
	
	/**
	 * 根据条件获得酒水信息
	 * @param category
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<WineInfo> selectByCondition(String category, Double minPrice, Double maxPrice) {
		return wineInfoDao.selectByCondition(category,maxPrice,minPrice);
	}

}

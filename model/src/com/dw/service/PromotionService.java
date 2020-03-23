package com.dw.service;

import java.util.List;

import com.dw.DaoImpl.PromotionDaoImpl;
import com.dw.dao.PromotionDao;
import com.dw.domain.Promotion;

public class PromotionService {
	
	static PromotionDao promo = new PromotionDaoImpl();
	
	public boolean update(Promotion promotion) {
	// TODO 自动生成的方法存根		
	boolean acounts = promo.update(promotion);
	return acounts;
}
}

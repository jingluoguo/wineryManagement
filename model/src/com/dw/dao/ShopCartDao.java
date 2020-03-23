package com.dw.dao;

import java.util.List;

import com.dw.domain.ShopCart;
import com.dw.domain.WineInfo;



public interface ShopCartDao {
	
	
	public void addShopCart(ShopCart shopCart);

	public int getWineCount(Integer wineId, String userId);

	public void updateShopCart(ShopCart shopCart);

	public List<ShopCart> getShopCartWineInfo(String userId);

	public int getGoodAmount(String userId);

	public void modifyWineAmount(int id, int wineAmount);

	public int getWineId(int id);

	public int getWineNumber(Integer id);

	public void deleteWine(Integer id);

	public boolean checkWineInCart(int wineId, String userId);

	public ShopCart getWineAmount(int id);

	public void updateWineAmount(Integer amount,Integer id);



}

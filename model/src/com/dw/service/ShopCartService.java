package com.dw.service;

import java.util.List;

import com.dw.DaoImpl.ShopCartDaoImpl;
import com.dw.DaoImpl.WineInfoDaoImpl;
import com.dw.dao.ShopCartDao;
import com.dw.dao.WineInfoDao;
import com.dw.domain.ShopCart;
import com.dw.domain.WineInfo;



public class ShopCartService {
	
	private ShopCartDao shopCartDao = new ShopCartDaoImpl();
	private WineInfoDao wineInfoDao = new WineInfoDaoImpl(); 
	
	public void addShopCart(String userId,Integer wineId) {
		ShopCart shopCart = new ShopCart();
		int amount = shopCartDao.getWineCount(wineId,userId);
		//第一次添加购物车，默认数量是0(amount==0)
		shopCart.setAmount(amount+1);
		shopCart.setUserId(userId);
		shopCart.setWineId(wineId);
		if(amount == 0) {
			shopCartDao.addShopCart(shopCart);
		}else {
			shopCartDao.updateShopCart(shopCart);
		}
		//减少相应的库存量
		//1.找到商品目前的库存信息
		WineInfo wineInfo = wineInfoDao.getwineInfo(wineId);
		wineInfo.setWineNumber(wineInfo.getWineNumber() - 1);
		wineInfoDao.updateWineInfo(wineInfo);
	}

	/**
	 * 根据用户的Id获得购物车中所有酒品信息
	 * @param userId
	 * @return
	 */
	public List<ShopCart> getMyShopCart(String userId) {
		return shopCartDao.getShopCartWineInfo(userId);
	}

	/**
	 * 根据用户Id得到购物车中酒品的种类
	 * @param userId
	 * @return
	 */
	public int getGoodAmount(String userId) {
		
		return shopCartDao.getGoodAmount(userId);
	}

	public boolean modifyWineAmount(int id, int wineAmount, int wineAmount2) {
		//获得购物车中的商品Id
		int wineId = shopCartDao.getWineId(id);
		//更新酒水表中的库存量,参数是酒水Id和数量和原来的差值
		boolean b = wineInfoDao.modifyWineAmount(wineId, wineAmount2 - wineAmount);
		if(!b) {
			return false;
		}
		//更改商品数量
		shopCartDao.modifyWineAmount(id,wineAmount);
		return true;
	}

	public void deleteWine(Integer id) {
		//获得购物车中的商品Id
//		int wineId = shopCartDao.getWineId(id);
		//获得购物车中商品的数量
//		int amount = shopCartDao.getWineNumber(id);
		//删除购物车中的酒品信息
		shopCartDao.deleteWine(id);
		//更新商品的库存量
//		wineInfoDao.modifyWineAmount(wineId, amount);
	}
	
	/**
	 * 根据购物车的id获得酒品的id
	 * @param wineId
	 * @return
	 */
	public Integer getWineIdByCartId(Integer cartId) {
		return shopCartDao.getWineId(cartId);
	}
	
	/**
	 * 根据购物车的Id获得酒品的数量
	 * @param wineAmount
	 * @return
	 */
	public Integer getWineAmountByCartId(Integer cartId) {
		return shopCartDao.getWineNumber(cartId);
	}

	public void deleteUserShopCart(Integer cartId) {
		shopCartDao.deleteWine(cartId);
	}

	/**
	 * 检查该酒品是否已经在购物车中
	 * @param wineId
	 */
	public boolean checkWineInCart(int wineId,String userId) {
		return shopCartDao.checkWineInCart(wineId,userId);
	}
	
	/**
	 * 根据购物车的id获取这个商品在购物车中的信息
	 * @param id
	 * @return
	 */
	public ShopCart getWineAmount(int id) {
		return shopCartDao.getWineAmount(id);
	}

	/**
	 * 更新酒水的数量，原来的数值加上参数的值
	 * @param amount
	 */
	public void updateWineAmount(Integer amount,Integer id) {
		shopCartDao.updateWineAmount(amount,id);
	}

}

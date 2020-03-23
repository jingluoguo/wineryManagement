package com.dw.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dw.DaoImpl.SettlementDaoImpl;
import com.dw.dao.SettlementDao;
import com.dw.domain.Page;
import com.dw.domain.PageBean;
import com.dw.domain.Settlement;
import com.dw.domain.User;


public class SettlementService {
	static SettlementDao acountDao = new SettlementDaoImpl();
	static double userTotal = 0.0;
	//获取奖励总额
	public double acountTotal(String userId) {
		// TODO 自动生成的方法存根		
		List<Settlement> acounts = acountDao.acounts(userId);
		if(!acounts.isEmpty()){
			userTotal = acounts.get(0).getS_balance();
		}
		return userTotal;
	}
	//获取所有settlement
	public List<Settlement> getSettleInfoAll(String userNo, String phone, String userName, String userID, String date) {
		return acountDao.getsettlementInfo(userNo, phone, userName, userID, date);
	}
	public Integer getsettlementCount(){
		return acountDao.getsettlementInfo();
	}
	/*public PageBean<Settlement> getSettleInfoAll(String userNo, String phone, String userName, String userID, String date,Integer iCurrPage) {
		// 每页显示4本书
        int pageSize = 2;
        // 创建一个新的PageBean对象pb
        PageBean<Settlement> pb = new PageBean<Settlement>();
        // 封装当前页数到pb对象
        pb.setCurrPage(iCurrPage);
         // 封装每页显示商品数到pb对象
        pb.setPageSize(pageSize);

        // totalCount: 数据库中商品的总数
        Integer totalCount = null;
        List<Settlement> currPageSettlementList = null;
        totalCount = acountDao.getsettlementInfo();
		currPageSettlementList = acountDao.getsettlementInfo(userNo, phone, userName, userID, date,iCurrPage, pageSize);
         // 封装商品总数数据到pb
        pb.setTotalCount(totalCount);
         // 封装当前页商品List列表数据到pb
        pb.setList(currPageSettlementList);
        // 返回此pb给Servlet
        return pb;
	}*/
	public List<Settlement> getSettleInfoAll(String userNo, String phone, String userName,
			String userID, String date,Page page) {

        List<Settlement> currPageSettlementList = null;
       
		currPageSettlementList = acountDao.getsettlementInfo(userNo, phone, userName, userID, date,page);
   
        return currPageSettlementList;
	}
	//获取账户余额
	public List<Settlement> acounts(String userId) {
		// TODO 自动生成的方法存根		
		List<Settlement> acounts = acountDao.acounts(userId);
		return acounts;
	}
	//修改等级
	public String UpdateLevel(String level, String userId){
		String UserInfo=acountDao.selectUserInfor(level,userId);
		return UserInfo;
	}
	//兑换酒水
	public int updateacounts(String userId, double useracount, double acount, String level) {
		// TODO 自动生成的方法存根		
		return acountDao.updateacountsacounts(userId, useracount, acount, level);
	}
	//修改账户余额
	public int updatecash(String userId, double useracount, double cash) {
		// TODO 自动生成的方法存根		
		return acountDao.updatecash(userId, useracount,cash);
	}
	public void updateBigAndSmall(String userId, float small, float big, float f) {
		acountDao.updateBigAndSmall(userId,small,big,f);
	}

}

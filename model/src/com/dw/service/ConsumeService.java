package com.dw.service;

import java.util.List;

import com.dw.DaoImpl.ConsumeDaoImpl;
import com.dw.DaoImpl.LevelDaoImpl;
import com.dw.DaoImpl.WineInfoDaoImpl;
import com.dw.dao.ConsumeDao;
import com.dw.dao.LevelDao;
import com.dw.dao.WineInfoDao;
import com.dw.domain.Consume;
import com.dw.domain.ConsumeExample;
import com.dw.domain.Settlement;
import com.dw.domain.User;
import com.dw.domain.WineInfo;
import com.dw.servlet.UserServlet;

public class ConsumeService {
	//累计值
	public double count = 0;
	//重复消费的打折
	private static double WINE_AGIO = 0.7;
	
	//级别钱数
	//private static double fund = 2000;
	
	private ConsumeDao consumeDao = new ConsumeDaoImpl();
	private LevelService levelService = new LevelService(); 
	//private UserService userService = new UserService();
	/**
	 * 保存消费信息
	 * @param consume
	 */
	public void saveConsumeRecord(Consume consume,WineInfo wineInfo,boolean x,double deposit){
			String preId = levelService.getPreUserID(consume.getcID());
			//String userLevel = "";
			if(x){
				//给上级分钱
//				List<User> userInfo = userService.getUserAll("",consume.getcID());
//				if(!userInfo.isEmpty()){
//					userLevel = userInfo.get(0).getuLevel();
//				}
//				if("业务员".equals(userLevel)){
//					fund = 2000;
//				}
//				if("代理商".equals(userLevel)){
//					fund = 10000;
//				}
//				if("销售经理".equals(userLevel)){
//					fund = 30000;
//				}
//				if("销售总监".equals(userLevel)){
//					fund = 50000;
//				}
				Settlement settle = new Settlement();
				settle.setS_sale((float)(deposit*0.2));
				settle.setS_ID(preId);
				System.out.println(settle);
				levelService.updatePreSale(settle);
			}else{
				//检查是否是第一次消费
				if(consumeDao.checkRepeatConsume(consume.getcID(),consume.getcWin())) {
					consume.setcPrice(consume.getcOmoney()*WINE_AGIO);
					consume.setcAgio(WINE_AGIO);
				}else {
					consume.setcPrice(consume.getcOmoney());
					consume.setcAgio(1);
				}
				//检查是否符合晋级要求
				UserServlet userServlet = new UserServlet();
				userServlet.promotion(preId);
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				//保存消费记录
				consumeDao.saveConsumeRecord(consume);
				count = consume.getcPrice();
			}
	}
	public List<ConsumeExample> selectConsume(StringBuilder sql) {
		return consumeDao.selectConsume(sql);
	}
	public Integer ConsumeCount(StringBuilder sql) {
		return consumeDao.ConsumeCount(sql);
	}

}

package com.dw.listenrs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.dw.dao.LastBalanceDao;
import com.dw.domain.LastBalance;
import com.dw.domain.Settlement;
import com.dw.domain.User;
import com.dw.service.BonusService;
import com.dw.service.LastBalanceService;
import com.dw.service.LevelService;
import com.dw.service.SettlementService;
import com.dw.service.UserService;
import com.dw.servlet.UserServlet;

/**
 * Application Lifecycle Listener implementation class WeeklySetllement
 *
 */
@WebListener
public class WeeklySettlement implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public WeeklySettlement() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
    BonusService bonusService = new BonusService();
    LevelService levelService = new LevelService();
    LastBalanceService balanceService = new LastBalanceService();
    private UserService userService = new UserService();
    private SettlementService settlementService = new SettlementService();
    private double rate = 0.0;
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	//执行周期
        long zhouqi = 1000*60*60*24*7;
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("小区业绩结算中...");
				//1.获取所有Id
				List<User> listUser = userService.getAllUserId();
				for(User user:listUser){
					rate = selectRate(user.getuLevel());
					float l = 0;
					float r = 0;
					float x = 0;
					float y = 0;
					String Lid = levelService.getNextLeftId(user.getuID());
					String Rid = levelService.getNextRightId(user.getuID());
					if(Lid != null) {
						l = f(Lid);
						x = f(Lid);
					}if(Rid != null) {
						r = f(Rid);
						y = f(Rid);
					}
					if(l != 0.0 && r != 0.0){
						float d = 0;
						float a = 0;
						String branch = "";
						LastBalance lastBalance = balanceService.getLastBalance(user.getuID());
						if(lastBalance.getBranch()=="L"||"L".equals(lastBalance.getBranch())){
							l = l+lastBalance.getBonus_account();
							d = l<r?l:r;
							a = l>r?(l-r):(r-l);
							branch = l>r?"L":"R";
						}
						else if(lastBalance.getBranch()=="R"||"R".equals(lastBalance.getBranch())){
							r = r+lastBalance.getBonus_account();
							d = l<r?l:r;
							a = l>r?(l-r):(r-l);
							branch = l>r?"L":"R";
						}
						else{
							d = l<r?l:r;
							a = l>r?(l-r):(r-l);
							branch = l>r?"L":"R";
						}
						Settlement settle = new Settlement();
						settle.setS_achievement(d);
						settle.setS_ID(user.getuID());
						levelService.updatePreSale(settle);
						settlementService.updateBigAndSmall(user.getuID(),l,r,a);
						//检查是否符合晋级要求
						UserServlet userServlet = new UserServlet();
						userServlet.promotion(user.getuID());
						System.out.println("ID" + user.getuID() + " l:" + l + ", x:" + x + " last:"+lastBalance.getBonus_account() + " branch:"+ lastBalance.getBranch());
						System.out.println("ID" + user.getuID() + " r:" + r + ", y:" + y + " last:"+lastBalance.getBonus_account());
						lastBalance.setUser_ID(user.getuID());
						lastBalance.setBranch(branch);
						lastBalance.setBonus_account(a);
						lastBalance.setAddtime(df.format(new Date()));
						balanceService.insertbalance(lastBalance);
					}
					/*
					 * //2.获取给上上级的奖励 double d = bonusService.getAmountReward(userId); //3.获取上级
					 * String pId = levelService.getPreUserID(userId); if(pId != null){
					 * //3.获取上级的上级ppId String ppId = levelService.getPreUserID(pId); if(ppId !=
					 * null){ do{ //4.总和赋值给上级的上级settlement表中 Settlement settle = new Settlement();
					 * settle.setS_achievement((float)(d)); settle.setS_ID(ppId);
					 * levelService.updatePreSale(settle); }while((ppId =
					 * levelService.getPreUserID(ppId)) != ""); } }
					 */
				}
				//5.清空周结算表（bonus表）中的所有记录
				bonusService.deleteRecord();
			}
		};
		
		//设置开启时间
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 3); // 控制时，24小时制
		calendar.set(Calendar.MINUTE, 0);  // 控制分
		calendar.set(Calendar.SECOND, 0);  // 控制秒
	
		Date time = calendar.getTime(); // 执行任务的时间，可自行修改，此时为12:00:00

		Timer timer = new Timer(true);
		//参数1：需要执行的类，参数2：开始执行的时间，参数3：执行周期
		timer.scheduleAtFixedRate(task, time, zhouqi);  //隔三秒周期性扫描执行方法
    }
    
    //这个递归方法可以让A获得B1或B2以下的区域奖励（不包含B1或者B2）
    float f(String b){
		if(b == null) {
			return 0;
		}
		float t = 0;
		float l = f(levelService.getNextLeftId(b));
		float r = f(levelService.getNextRightId(b));
		t =  (float) (bonusService.getAmountReward(b)*rate);
		return t+l+r;
	}
    
    //根据等级获取比例的
    private double selectRate(String uLevel) {
		double rate = 0.0;
		switch(uLevel) {
			case "业务员":rate=0.1;break;
			case "代理商":rate=0.12;break;
			case "销售经理":rate=0.14;break;
			case "销售总监":rate=0.16;break;
		}
		return rate;
	}
	
}

package com.dw.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.QueryRunner;

import com.dw.dao.LastBalanceDao;
import com.dw.domain.LastBalance;
import com.dw.tools.JdbcUtil;


public class LastBalanceDaoImpl implements LastBalanceDao{

	QueryRunner queryrunner = new QueryRunner();

	@Override
	public int insertlastBalance(LastBalance lastBalance) {
		Connection connection = null;
		String sql = "INSERT into last_balance  VALUES(?,?,?,?,?)";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql, new Object[] { null,lastBalance.getUser_ID(),lastBalance.getBonus_account(),lastBalance.getBranch(),lastBalance.getAddtime()});
			//System.out.println(i);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return 0;
	}

	@Override
	public LastBalance getLastBalance(String user_ID) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		LastBalance lastBalance = new LastBalance();
		try{
			con = JdbcUtil.getConnection();
			String sql = "SELECT * FROM last_balance where user_ID like '%" + user_ID +"%' order by addtime asc";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				lastBalance.setBonus_account(rs.getFloat("bonus_account"));
				System.out.println(rs.getFloat("bonus_account"));
				lastBalance.setBranch(rs.getString("branch"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs, pst, con);
		}
		return lastBalance;
	}
	
	
	
}

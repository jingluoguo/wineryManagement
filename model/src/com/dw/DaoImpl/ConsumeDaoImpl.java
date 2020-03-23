package com.dw.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dw.dao.ConsumeDao;
import com.dw.domain.Consume;
import com.dw.domain.ConsumeExample;
import com.dw.tools.JdbcUtil;


public class ConsumeDaoImpl implements ConsumeDao{
	
	
	/**
	 * 保存消费信息到消费表中
	 * @param consume
	 */
	public void saveConsumeRecord(Consume consume){
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "insert into consume(c_win,c_ID,c_omoney,c_agio,c_price,c_date,c_pay) values(?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, consume.getcWin());
			pst.setString(2, consume.getcID());
			pst.setDouble(3, consume.getcOmoney());
			pst.setDouble(4, consume.getcAgio());
			pst.setDouble(5, consume.getcPrice());
			pst.setString(6, consume.getcDate());
			pst.setString(7, consume.getcPay());
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, null, con);
		}
	}

	@Override
	public boolean checkRepeatConsume(String cID,String cWine) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from consume where c_ID=" + cID + " and c_win='"+ cWine+"'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, null, con);
		}
		return flag;
	}

	/**
	 * 显示全部酒水信息
	 */
	@Override
	public List<ConsumeExample> selectConsume(StringBuilder sql) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<ConsumeExample> list = new ArrayList<ConsumeExample>();
		try {
			con = JdbcUtil.getConnection();
			//System.out.println(sql.toString());
			pst = con.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()) {
				ConsumeExample ce = new ConsumeExample(
						rs.getString(1),
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getDouble(5), 
						rs.getString(6), 
						rs.getDouble(7));
				list.add(ce);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, null, con);
		}
		return list;
	}

	@Override
	public Integer ConsumeCount(StringBuilder sql) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer count = 0;
		try {
			con = JdbcUtil.getConnection();
			//System.out.println(sql.toString());
			pst = con.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()) {
				count++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(null, null, con);
		}
		return count;
	}

}

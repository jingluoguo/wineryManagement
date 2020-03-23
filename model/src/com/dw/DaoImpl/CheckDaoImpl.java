package com.dw.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.QueryRunner;

import com.dw.dao.CheckDao;
import com.dw.domain.Check;
import com.dw.tools.JdbcUtil;


public class CheckDaoImpl implements CheckDao{

	QueryRunner queryrunner = new QueryRunner();
	
	
	@Override
	public int insertCheck(Check check) {
		Connection connection = null;
		String sql = "INSERT into checka  VALUES(?,?)";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql, new Object[] { null,check.getFID()});
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return 0;
	}


	@Override
	public int checkID(Check check) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer i = 0;
		//System.out.println(user.getuPhone());
		//System.out.println(user.getuID());
		String sql = "select * from checka where FID=?";
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, check.getFID());
			rs = pst.executeQuery();
			while(rs.next()) {
				i = 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(rs, pst, con);
		}
		return i;
	}
}

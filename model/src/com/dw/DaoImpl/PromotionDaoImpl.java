package com.dw.DaoImpl;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;

import com.dw.dao.PromotionDao;
import com.dw.domain.Promotion;
import com.dw.tools.JdbcUtil;

public class PromotionDaoImpl implements PromotionDao{
	QueryRunner queryrunner = new QueryRunner();

	public boolean update(Promotion promotion) {
		Connection connection = null;
		String sql = "INSERT INTO promotion VALUES (?, ?, ?, ?, ?)";
		try {
			connection = JdbcUtil.getConnection();
			queryrunner.update(connection, sql, new Object[] { null,promotion.getP_ID(),promotion.getP_olevel(),promotion.getP_level(), promotion.getP_date()});
			//System.out.println(i);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.releaseConnection(connection);
		}
		return false;
	}
}

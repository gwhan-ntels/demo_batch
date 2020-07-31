package com.ntels.ccbs.batch.common.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Value;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.LazyLoaderJdbc;
import com.ntels.ccbs.batch.util.QueryUtil;

public abstract class LazyLoadingDao extends JdbcDao {

	@Value("${fetchSize}")
	private int fetchSize;
	
	protected <R> LazyLoader<R> getLazyLoader(String id, Class<R> resultType, Object parameter) {
		
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			rs = QueryUtil.getListFromOpen(getMapperPath(), getMapperName(), "select", id, parameter, conn, fetchSize);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return new LazyLoaderJdbc<R>(conn, rs, resultType);
	}

	protected <R> LazyLoader<R> getLazyLoader(String dbType, String id, Class<R> resultType, Object parameter) {
		
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			rs = QueryUtil.getListFromOpen(dbType,getMapperPath(), getMapperName(), "select", id, parameter, conn, fetchSize);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return new LazyLoaderJdbc<R>(conn, rs, resultType);
	}
	protected <R> LazyLoader<R> getLazyLoader(String dbType, String id, Class<R> resultType, Object parameter,String Vsql) {
		
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			rs = QueryUtil.getListFromOpen(dbType,getMapperPath(), getMapperName(), "select", id, parameter, conn, fetchSize, Vsql);	

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return new LazyLoaderJdbc<R>(conn, rs, resultType);
	}	
}
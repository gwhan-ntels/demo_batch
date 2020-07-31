package com.ntels.ccbs.batch.ch.standard.dao;

import java.sql.Connection;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ntels.ccbs.batch.ch.standard.entity.StandardCharge;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.sample.vo.SearchParam;

public interface StandardChargeDao {

	/**
	 * mybatis 예제
	 * 
	 * @param param
	 * @return List<StandardCharge>
	 */
//	public List<StandardCharge> listStandardCharge(SearchParam param);

	/**
	 * Spring jdbcTemplate 예제
	 * 
	 * @param param
	 * @return List<StandardCharge>
	 */
//	public List<StandardCharge> listStandardChargeJdbcTemplate(
//			JdbcTemplate jdbcTemplate, SearchParam param);

	/**
	 * jdbc direct 접속 dao 
	 * 
	 * @param conn
	 * @return List<StandardCharge>
	 */
	public List<StandardCharge> listStandardChargeDirect(Connection conn, Common comm);
	public int saveStandardChargeDirect(Connection conn, StandardCharge standardCharge);
	public int saveStandardChargeDirect(Connection conn, List<Object> obj);

}
package com.ntels.ccbs.batch.iv.dao;

import java.sql.Connection;
import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface NBlivb01m04Dao {

	/**
	 * mybatis 예제
	 * 
	 * @param param
	 * @return List<NBliv04m01>
	 */
//	public List<NBliv04m01> listStandardCharge(SearchParam param);

	/**
	 * Spring jdbcTemplate 예제
	 * 
	 * @param param
	 * @return List<NBliv04m01>
	 */
//	public List<NBliv04m01> listStandardChargeJdbcTemplate(
//			JdbcTemplate jdbcTemplate, SearchParam param);

	/**
	 * jdbc direct 접속 dao 
	 * 
	 * @param conn
	 * @return List<NBliv04m01>
	 */
	public LazyLoader<CBillComm>  listInfoDirect(Common comm);
	public LazyLoader<CBillComm>  listDivInfoDirect(Common comm);
	public int saveInfoDirect(List<Object> obj);
	public int saveDivInfoDirect(List<Object> obj);


	public int deleteInfoDirect(List<Object> obj);


	public int saveWrkDirect(List<Object> obj);
}
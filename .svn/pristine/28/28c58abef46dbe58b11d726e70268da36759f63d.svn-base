package com.ntels.ccbs.batch.ch.common.dao;

import java.util.List;

import com.ntels.ccbs.batch.ch.common.entity.DiscExcl;
import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.entity.RateAttr;
import com.ntels.ccbs.batch.ch.common.entity.RateFctr;
import com.ntels.ccbs.batch.ch.common.entity.RateInfo;

public interface RateInfoDao {

	/**
	 * mybatis 예제
	 * 
	 * @param param
	 * @return List<StandardCharge>
	 */
	public List<RateInfo> listRateInfo(String chrgCtgry);
	public Multi listMulti(Multi multi);
	public List<RateInfo> listRateItm(Multi multi);
	public List<RateAttr> listRrateAttr(Multi multi);
	public List<RateAttr> listDrateAttr(Multi multi);
	public Multi listMultiCtrt(Multi multi);
	public List<DiscExcl> listDiscExcl(Multi multi);
	public List<RateFctr> listRateFctr(Multi multi);
	public List<RateAttr> listCrateAttr(Multi multi);

	/**
	 * Spring jdbcTemplate 예제
	 * 
	 * @param param
	 * @return List<StandardCharge>
	 */
//	public List<RateInfo> listRateInfoJdbcTemplate(
//			JdbcTemplate jdbcTemplate, SearchParam param);

	/**
	 * jdbc direct 접속 dao 
	 * 
	 * @param conn
	 * @return List<StandardCharge>
	 */
//	public List<RateInfo> listRateInfoDirect(Connection conn, SearchParam param);

}
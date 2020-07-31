package com.ntels.ccbs.batch.common.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ntels.ccbs.batch.common.entity.Common;

public interface CommonDao {

	/**
	 * mybatis 예제
	 * 
	 * @param param
	 * @return List<Common>
	 */

	public List<Common> commonListExRate();
	public List<Common> commonListBillStp(String billYymm, String billCycl, String soId);
	public List<Common> commonListClcMain(String clcWrkNo);
	public int commonUpdClcMain(String statCd, String clcWrkNo);
	public int commonInsClsInfo(Common common);
	public int commonUpdClsMain(String clsYn, String clsTskCl, String billYymm, String soId);
	public List<Common> commonListBatPgm(String batPgmId);
	public int commonUpdBatPgmLog(Common common);
	public int commonInsBatPgmLog(Common common);
	public int updateNextSequence(String modCd);
	public int getSequence(String modCd);
	public int updateNextSequenceMulti(String modCd, int count);
	int batPgmLogCount(Common common);
	String batProcStat(Common common);
	int updateBatProcStat(Common common);

}
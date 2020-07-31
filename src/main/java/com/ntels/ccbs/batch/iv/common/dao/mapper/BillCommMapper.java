package com.ntels.ccbs.batch.iv.common.dao.mapper;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

public interface BillCommMapper {

	int updateChrgAdjAply(@Param("dcsnProcStat") String dcsnProcStat
							, @Param("adjNo") String adjNo, @Param("chgDate") Timestamp chgDate);
	
}

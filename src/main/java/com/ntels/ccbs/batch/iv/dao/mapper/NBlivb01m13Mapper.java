package com.ntels.ccbs.batch.iv.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface NBlivb01m13Mapper {

	Double getRemainRcptAmtFromBillWrk(@Param("bill") CBillComm bill);
	
}

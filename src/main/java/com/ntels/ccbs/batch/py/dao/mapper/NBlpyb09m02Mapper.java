package com.ntels.ccbs.batch.py.dao.mapper;

import org.apache.ibatis.annotations.Param;

public interface NBlpyb09m02Mapper {

	Double getUnpayAmtFromBillMast(@Param("billSeqNo") String billSeqNo);
	
}

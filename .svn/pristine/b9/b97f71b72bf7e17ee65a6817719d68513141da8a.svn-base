package com.ntels.ccbs.batch.iv.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrepayBillAply;

public interface NBlivb01m12Mapper {

	/**
	 * 남아있는 수납 대상금액을 조회한다.
	 * @param bill
	 * @return
	 */
	Double getRemainRcptAmtFromBillWrk(@Param("bill") CBillComm bill);
	
	/**
	 * 
	 * @param prepayBillAply
	 * @return
	 */
	int insertPrepayBillAply(@Param("prepayBillAply") PrepayBillAply prepayBillAply);
	
	/**
	 * 
	 * @param prepayBillAply
	 * @return
	 */
	int updatePrepayBillAplyPayProc(@Param("prepayBillAply") PrepayBillAply prepayBillAply);
	
}

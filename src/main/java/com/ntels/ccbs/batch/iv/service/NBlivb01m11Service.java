package com.ntels.ccbs.batch.iv.service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface NBlivb01m11Service {

	/**
	 * 미납내역 생성을 위한 청구정보를 조회한다.
	 * @param bill
	 * @return
	 */
	LazyLoader<CBillComm> getBillInfoForUnpayTarget(CBillComm bill);
	
	LazyLoader<BillCust> getBillTargetCust(CBillComm bill);
	
	LazyLoader<BillCust> getBillTargetServiceComposition(CBillComm bill);
	
}

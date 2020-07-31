package com.ntels.ccbs.batch.iv.dao;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface NBlivb01m11Dao {

	/**
	 * 미납내역 생성을 위한 청구정보를 조회한다.
	 * @param bill
	 * @return
	 */
	LazyLoader<CBillComm> getBillInfoForUnpayTarget(CBillComm bill);
	
	LazyLoader<BillCust> getBillTargetCust(CBillComm bill);
	
	/**
	 * 당월 청구내역이 없고 미납내역만 존재하는 데이터를 검색한다.
	 * @param bill
	 * @return
	 */
	LazyLoader<BillCust> getBillTargetServiceComposition(CBillComm bill);
	
}

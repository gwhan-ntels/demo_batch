package com.ntels.ccbs.batch.iv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m11Dao;

@Service
public class NBlivb01m11ServiceImpl extends BaseService implements NBlivb01m11Service {

	@Autowired
	private NBlivb01m11Dao nBlivb01m11Dao;
	
	/**
	 * 미납내역 생성을 위한 청구정보를 조회한다.
	 * @param bill
	 * @return
	 */
	@Override
	public LazyLoader<CBillComm> getBillInfoForUnpayTarget(CBillComm bill) {
		return nBlivb01m11Dao.getBillInfoForUnpayTarget(bill);
	}
	
	@Override
	public LazyLoader<BillCust> getBillTargetCust(CBillComm bill) {
		return nBlivb01m11Dao.getBillTargetCust(bill);
	}
	
	@Override
	public LazyLoader<BillCust> getBillTargetServiceComposition(CBillComm bill) {
		
		checkLength(bill.getBillYymm(), 6, "billYymm");
		checkEmpty(bill.getSoId(), "soId");
		
		return nBlivb01m11Dao.getBillTargetServiceComposition(bill);
	}
	
}

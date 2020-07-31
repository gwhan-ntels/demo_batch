package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.TaxBill;
import com.ntels.ccbs.batch.iv.common.entity.TaxTarget;

public interface NBlivb01m09Dao {
	
	int insertBillWrk(List<CBillComm> billList);
	
	int insertTaxBill(List<TaxBill> taxBillList);
	
	LazyLoader<TaxTarget> getTaxTargetList(CBillComm bill);
	
	LazyLoader<TaxTarget> getTaxTargetListByBillSeqNo(CBillComm bill);
	
	List<TaxTarget> getBillForTaxCalcFromSimulationBillByBillSeqNo(Common bill);
	
	List<TaxBill> getNewTaxBill(CBillComm bill);

	String getRoundingAdjSvcRateItmTypCd(String soId);
	
}

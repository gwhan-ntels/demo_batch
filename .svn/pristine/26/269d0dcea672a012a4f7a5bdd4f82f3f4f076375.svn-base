package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrepayBillAply;
import com.ntels.ccbs.batch.iv.common.entity.PrepayCtrt;

public interface NBlivb01m12Service {

	LazyLoader<PrepayBillAply> getPrepayCtrtContents(CBillComm bill);
	
	PrepayBillAply getNoPayProceedPrepayBillAply(PrepayBillAply prepayBillAply);
	
	Double getRemainRcptAmtFromBillWrk(CBillComm bill);
	
	List<CBillComm> getBillWrkInfoForPrepayByCtrt(CBillComm bill);
	
	List<CBillComm> getBillWrkInfoForPrepayByCust(CBillComm bill);

	int updateBillWrkRcptAmt(List<CBillComm> billList);
	
	int insertPrepayBillAply(List<PrepayBillAply> prepayBillAply);

	int updatePrepayBillAplyPayProc(List<PrepayBillAply> prepayBillAply);
	
	int updatePrepayCtrt(List<PrepayCtrt> prepayCtrtList);
	
}

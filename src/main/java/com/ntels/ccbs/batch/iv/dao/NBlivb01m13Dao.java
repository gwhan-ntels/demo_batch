package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;

public interface NBlivb01m13Dao {

	LazyLoader<PrepayOcc> getPrepayOccForUnpaidBill(CBillComm bill);

	List<CBillComm> getUnpaidBillAmt(CBillComm bill);
	
	int insertOverpayBillAply(OverpayBillAply overpayBillAply);

	int insertOverpayBillAply(List<OverpayBillAply> overpayBillAplyList);
	
	List<OverpayBillAply> getOverpayBillInfo(OverpayBillAply overpayBillAply);
	
	Double getRemainRcptAmtFromBillWrk(CBillComm bill);
	
	List<CBillComm> getBillWrkInfoForPrepay(CBillComm bill);
	
	int updateBillWrkRcptAmt(List<CBillComm> billList);
	
	int updateOverpayBillAply(List<OverpayBillAply> overpayBillAplyList);
	
}

package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.BillTgtCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;

public interface NBlivb01m15Dao {

	LazyLoader<OverpayBillAply> getOverpayBillAplyList(CBillComm bill);
	
	List<CBillComm> getBillList(CBillComm bill);
	
	int updateBill(List<CBillComm> billList);
	
	int updateBillMast(List<CBillComm> billList);
	
	List<String> getSmlAmtYn(CBillComm bill);
	
	int updateSmlAmtYn(List<BillTgtCust> billTgtCustList);
	
	int updateOverpayBillAply(List<OverpayBillAply> overpayBillAplyList);

	int updatePrepayOcc(List<PrepayOcc> prepayOccList);
	
}

package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;

public interface NBlivb01m10Service {
	
	List<BillCust> getBillCustInfoMapper(BillCust billCust);
	
	List<BillCust> getBillCustInfoByBillSeqNo(BillCust billCust);
	
	LazyLoader<BillCust> getBillTgtSvcCmpsTargetInfo(BillCust billCust);

	int updateBillTgtCust(List<BillCust> billCustList);
	
	LazyLoader<BillCust>  getBillCustInfo ( BillCust billCust) ;
	
}

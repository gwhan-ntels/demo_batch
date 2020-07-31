package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;

public interface NBlivb01m10Dao {

	LazyLoader<BillCust> getBillCustInfo(BillCust billCust);
	
	List<BillCust> getBillCustInfoMapper(BillCust billCust);
	
	List<BillCust> getBillCustInfoByBillSeqNo(BillCust billCust);
	
	LazyLoader<BillCust> getBillTgtSvcCmpsTargetInfo(BillCust billCust);

	int updateBillTgtCust(List<BillCust> billCustList);
	
}

package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;

public interface NBlivb01m08Dao {

	LazyLoader<BillWork> getAdjInfoBeforeBillListLazy(BillWork billWork);
	int updateAdjBeforeBill(List<BillWork> billWorkList);
	int updateAdjAply(List<BillWork> billWorkList);
	

	LazyLoader<BillWork> getAdjInfoAfterBillListLazy(BillWork billWork);
	BillWork getBillWorkForAdj(BillWork billWork);
	int insertBillWork(List<BillWork> billWorkList);
	int updateAdjAfterBill(List<BillWork> billWorkList);

//	List<BillWork> getAdjInfoAfterBillList(BillWork billWork);
//	List<BillWork> getChInfoListForBillWork(BillWork billWork);
//	LazyLoader<BillWork> getChInfoListForBillWorkLazy(BillWork billWork);
//	List<BillWork> getAutoTransferDiscountInfoList(BillWork billWork);
//	LazyLoader<BillWork> getAutoTransferDiscountInfoListLazy(BillWork billWork);
//	List<BillWork> getAdjInfoBeforeBillList(BillWork billWork);

}

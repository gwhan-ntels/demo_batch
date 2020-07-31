package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.exception.BatchException;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;

public interface GenerateBillWorkService {

	List<BillWork> getChInfoListForBillWork(BillWork billWork) throws BatchException;

	LazyLoader<BillWork> getChInfoListForBillWorkLazy(BillWork billWork) throws BatchException;
	
	int insertBillWork(List<BillWork> billWorkList);

	LazyLoader<BillWork> getAutoTransferDiscountInfoList(BillWork billWork) throws BatchException;

	LazyLoader<BillWork> getAdjInfoAfterBillList(BillWork billWork) throws BatchException;

	LazyLoader<BillWork> getAdjInfoBeforeBillList(BillWork billWork) throws BatchException;

	int updateAplyAdjBeforeBill(List<BillWork> billWorkList);
	
	int updateAdjAfterBill(List<BillWork> billWorkList);
	
	int updateAdjAply(List<BillWork> billWorkList);

	BillWork getBillWorkForAdj(BillWork billWork);

}
package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.exception.BatchException;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;

public interface NBlivb01m08Service {

	LazyLoader<BillWork> getAdjInfoBeforeBillList(BillWork billWork) throws BatchException;

	int updateAplyAdjBeforeBill(List<BillWork> billWorkList);

	int updateAdjAply(List<BillWork> billWorkList);

	LazyLoader<BillWork> getAdjInfoAfterBillList(BillWork billWork) throws BatchException;

	BillWork getBillWorkForAdj(BillWork billWork);

	int insertBillWork(List<BillWork> billWorkList);

	int updateAdjAfterBill(List<BillWork> billWorkList);

//	List<BillWork> getChInfoListForBillWork(BillWork billWork) throws BatchException;
//
//	LazyLoader<BillWork> getChInfoListForBillWorkLazy(BillWork billWork) throws BatchException;
//
//	LazyLoader<BillWork> getAutoTransferDiscountInfoList(BillWork billWork) throws BatchException;

}
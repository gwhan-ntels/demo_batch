package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.exception.BatchException;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m08Dao;

@Service
public class NBlivb01m08ServiceImpl extends BaseService implements NBlivb01m08Service {
	
	@Autowired
	private NBlivb01m08Dao nBlivb01m08Dao;

	@Override
	public LazyLoader<BillWork> getAdjInfoBeforeBillList(BillWork billWork) throws BatchException {
		
		checkEmptySoId(billWork.getSoId());
		checkEmptyBillYymm(billWork.getBillYymm());
		checkEmptyBillCycl(billWork.getBillCycl());
		checkEmptyBillDt(billWork.getBillDt());
		
		return nBlivb01m08Dao.getAdjInfoBeforeBillListLazy(billWork); 
	}

	@Override
	public int updateAplyAdjBeforeBill(List<BillWork> billWorkList) {
		return nBlivb01m08Dao.updateAdjBeforeBill(billWorkList);
	}

	
	@Override
	public int updateAdjAply(List<BillWork> billWorkList) {
		return nBlivb01m08Dao.updateAdjAply(billWorkList);
	}
	
	
	
	

	@Override
	public LazyLoader<BillWork> getAdjInfoAfterBillList(BillWork billWork) throws BatchException {
		
		checkEmptySoId(billWork.getSoId());
		checkEmptyBillYymm(billWork.getBillYymm());
		checkEmptyBillCycl(billWork.getBillCycl());
		checkEmptyBillDt(billWork.getBillDt());
		
		return nBlivb01m08Dao.getAdjInfoAfterBillListLazy(billWork); 
	}

	@Override
	public BillWork getBillWorkForAdj(BillWork billWork) {
		return nBlivb01m08Dao.getBillWorkForAdj(billWork);
	}
	
	@Override
	public int insertBillWork(List<BillWork> billWorkList) {
		return nBlivb01m08Dao.insertBillWork(billWorkList);
	}
	
	@Override
	public int updateAdjAfterBill(List<BillWork> billWorkList) {
		return nBlivb01m08Dao.updateAdjAfterBill(billWorkList);
	}
	
	
//	private void checkGetChInfoListForBillWork(BillWork billWork) throws BatchException {
//		checkEmptySoId(billWork.getSoId());
//		checkEmptyBillYymm(billWork.getBillYymm());
//	}
//
//	@Override
//	public List<BillWork> getChInfoListForBillWork(BillWork billWork) throws BatchException {
//		checkGetChInfoListForBillWork(billWork);
//		return nBlivb01m08Dao.getChInfoListForBillWork(billWork);
//	}
//
//	@Override
//	public LazyLoader<BillWork> getChInfoListForBillWorkLazy(BillWork billWork) throws BatchException {
//		checkGetChInfoListForBillWork(billWork);
//		return nBlivb01m08Dao.getChInfoListForBillWorkLazy(billWork);
//	}
//
//	@Override
//	public LazyLoader<BillWork> getAutoTransferDiscountInfoList(BillWork billWork) throws BatchException {
//		checkEmptySoId(billWork.getSoId());
//		checkEmptyBillYymm(billWork.getBillYymm());
//		checkEmptyBillCycl(billWork.getBillCycl());
//		checkEmptyBillDt(billWork.getBillDt());
//
//		return nBlivb01m08Dao.getAutoTransferDiscountInfoListLazy(billWork);
//	}

}

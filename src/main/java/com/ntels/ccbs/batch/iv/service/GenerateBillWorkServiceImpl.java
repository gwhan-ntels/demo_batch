package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.exception.BatchException;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.dao.GenerateBillWorkDao;

@Service
public class GenerateBillWorkServiceImpl extends BaseService implements GenerateBillWorkService {
	
	@Autowired
	private GenerateBillWorkDao generateBillWorkDao;
	
	private void checkGetChInfoListForBillWork(BillWork billWork) throws BatchException {
		checkEmptySoId(billWork.getSoId());
		checkEmptyBillYymm(billWork.getBillYymm());
	}
	
	@Override
	public List<BillWork> getChInfoListForBillWork(BillWork billWork) throws BatchException {
		checkGetChInfoListForBillWork(billWork);
		return generateBillWorkDao.getChInfoListForBillWork(billWork);
	}
	
	@Override
	public LazyLoader<BillWork> getChInfoListForBillWorkLazy(BillWork billWork) throws BatchException {
		checkGetChInfoListForBillWork(billWork);
		return generateBillWorkDao.getChInfoListForBillWorkLazy(billWork);
	}
	
	@Override
	public int insertBillWork(List<BillWork> billWorkList) {
		return generateBillWorkDao.insertBillWork(billWorkList);
	}
	
	@Override
	public LazyLoader<BillWork> getAutoTransferDiscountInfoList(BillWork billWork) throws BatchException {
		checkEmptySoId(billWork.getSoId());
		checkEmptyBillYymm(billWork.getBillYymm());
		checkEmptyBillCycl(billWork.getBillCycl());
		checkEmptyBillDt(billWork.getBillDt());
		
		return generateBillWorkDao.getAutoTransferDiscountInfoListLazy(billWork);
	}
	
	@Override
	public LazyLoader<BillWork> getAdjInfoAfterBillList(BillWork billWork) throws BatchException {
		
		checkEmptySoId(billWork.getSoId());
		checkEmptyBillYymm(billWork.getBillYymm());
		checkEmptyBillCycl(billWork.getBillCycl());
		checkEmptyBillDt(billWork.getBillDt());
		
		return generateBillWorkDao.getAdjInfoAfterBillListLazy(billWork); 
	}
	
	@Override
	public LazyLoader<BillWork> getAdjInfoBeforeBillList(BillWork billWork) throws BatchException {
		
		checkEmptySoId(billWork.getSoId());
		checkEmptyBillYymm(billWork.getBillYymm());
		checkEmptyBillCycl(billWork.getBillCycl());
		checkEmptyBillDt(billWork.getBillDt());
		
		return generateBillWorkDao.getAdjInfoBeforeBillListLazy(billWork); 
	}
	
	@Override
	public int updateAplyAdjBeforeBill(List<BillWork> billWorkList) {
		return generateBillWorkDao.updateAdjBeforeBill(billWorkList);
	}
	
	@Override
	public int updateAdjAfterBill(List<BillWork> billWorkList) {
		return generateBillWorkDao.updateAdjAfterBill(billWorkList);
	}
	
	@Override
	public int updateAdjAply(List<BillWork> billWorkList) {
		return generateBillWorkDao.updateAdjAply(billWorkList);
	}
	
	@Override
	public BillWork getBillWorkForAdj(BillWork billWork) {
		return generateBillWorkDao.getBillWorkForAdj(billWork);
	}
	
}

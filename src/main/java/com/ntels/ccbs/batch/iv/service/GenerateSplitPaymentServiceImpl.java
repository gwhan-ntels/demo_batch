package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.exception.BatchException;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPym;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPymProcRslt;
import com.ntels.ccbs.batch.iv.dao.GenerateSplitPaymentDao;

@Service
public class GenerateSplitPaymentServiceImpl extends BaseService implements GenerateSplitPaymentService {

	@Autowired
	private GenerateSplitPaymentDao generateSplitPaymentDao;
	
	@Override
	public LazyLoader<BillSpltPym> getSplitPymCtrtInfoList(BillSpltPym billSpltPym) throws BatchException {
		
		// 필수값 체크
		checkEmptySoId(billSpltPym.getSoId());
		checkEmptyBillYymm(billSpltPym.getBillYymm());
		checkEmptyBillCycl(billSpltPym.getBillCycl());
		checkEmptyBillDt(billSpltPym.getBillDt());
		checkEmptypSeq(billSpltPym.getpSeq());
		
		return generateSplitPaymentDao.getSplitPymCtrtInfoListLazy(billSpltPym);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int insertSpltPymList(List<BillSpltPym> billSpltPymList) {
		return generateSplitPaymentDao.insertSpltPym(billSpltPymList);
	}
	
	@Override
	public LazyLoader<BillSpltPym> getSpltPymResultList(BillSpltPym billSpltPym) throws BatchException {
		
		// 필수값 체크
		checkEmptySoId(billSpltPym.getSoId());
		checkEmptyBillYymm(billSpltPym.getBillYymm());
		checkEmptyBillCycl(billSpltPym.getBillCycl());
		checkEmptyBillDt(billSpltPym.getBillDt());
		checkEmptypSeq(billSpltPym.getpSeq());
		
		return generateSplitPaymentDao.getSpltPymResultListLazy(billSpltPym);
	}
	
	@Override
	public int insertSpltPymProcRstl(List<BillSpltPymProcRslt> billSpltPymProcRsltList) {
		return generateSplitPaymentDao.insertSpltPymProcRstl(billSpltPymProcRsltList);
	}
	
}

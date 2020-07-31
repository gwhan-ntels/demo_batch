package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.exception.BatchException;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.Arrears;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.dao.ArrearsDao;

@Service
public class ArrearsServiceImpl extends BaseService implements ArrearsService {

	@Autowired
	private ArrearsDao arrearsDao;
	
	@Override
	public LazyLoader<Arrears> getArrearsInfoList(Arrears arrears) throws BatchException {
		
		checkEmptySoId(arrears.getSoId());
		checkEmptyBillYymm(arrears.getBillYymm());
		checkEmptyBillCycl(arrears.getBillCycl());
		checkEmptyBillDt(arrears.getBillDt());
		
		return arrearsDao.getArrearsInfoListLazy(arrears);
	}
	
	@Override
	public int insertArrs(List<Arrears> arrearsList) {
		return arrearsDao.insertArrs(arrearsList);
	}
	
	@Override
	public LazyLoader<BillWork> getArrsAplyInfoList(BillWork billWork) throws BatchException {
		
		checkEmptySoId(billWork.getSoId());
		checkEmptyBillYymm(billWork.getBillYymm());
		checkEmptyBillCycl(billWork.getBillCycl());
		checkEmptyBillDt(billWork.getBillDt());
		
		return arrearsDao.getArrsAplyInfoListLazy(billWork);
	}
	
	@Override
	public int updateArrsInfo(List<BillWork> billWorkList) {
		return arrearsDao.updateArrsInfo(billWorkList);
	}
	
}

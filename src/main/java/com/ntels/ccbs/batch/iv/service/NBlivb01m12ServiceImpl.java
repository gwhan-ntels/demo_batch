package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrepayBillAply;
import com.ntels.ccbs.batch.iv.common.entity.PrepayCtrt;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m12Dao;

@Service
public class NBlivb01m12ServiceImpl extends BaseService implements NBlivb01m12Service {

	@Autowired
	private NBlivb01m12Dao nBlivb01m12Dao;
	
	@Override
	public LazyLoader<PrepayBillAply> getPrepayCtrtContents(CBillComm bill) {
		return nBlivb01m12Dao.getPrepayCtrtContents(bill);
	}
	
	@Override
	public PrepayBillAply getNoPayProceedPrepayBillAply(PrepayBillAply prepayBillAply) {
		return nBlivb01m12Dao.getNoPayProceedPrepayBillAply(prepayBillAply);
	}
	
	@Override
	public Double getRemainRcptAmtFromBillWrk(CBillComm bill) {
		return nBlivb01m12Dao.getRemainRcptAmtFromBillWrk(bill);
	}
	
	@Override
	public List<CBillComm> getBillWrkInfoForPrepayByCtrt(CBillComm bill) {
		return nBlivb01m12Dao.getBillWrkInfoForPrepayByCtrt(bill);
	}
	
	@Override
	public List<CBillComm> getBillWrkInfoForPrepayByCust(CBillComm bill) {
		return nBlivb01m12Dao.getBillWrkInfoForPrepayByCust(bill);
	}
	
	@Override
	public int updateBillWrkRcptAmt(List<CBillComm> billList) {
		return nBlivb01m12Dao.updateBillWrkRcptAmt(billList);
	}
	
	@Override
	public int insertPrepayBillAply(List<PrepayBillAply> prepayBillAply) {
		return nBlivb01m12Dao.insertPrepayBillAply(prepayBillAply);
	}

	@Override
	public int updatePrepayBillAplyPayProc(List<PrepayBillAply> prepayBillAply) {
		return nBlivb01m12Dao.updatePrepayBillAplyPayProc(prepayBillAply);
	}
	
	@Override
	public int updatePrepayCtrt(List<PrepayCtrt> prepayCtrtList) {
		return nBlivb01m12Dao.updatePrepayCtrt(prepayCtrtList);
	}
	
}

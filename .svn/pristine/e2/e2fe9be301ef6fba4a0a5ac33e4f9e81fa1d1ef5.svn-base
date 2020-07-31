package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m13Dao;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;

@Service
public class NBlivb01m13ServiceImpl extends BaseService implements NBlivb01m13Service {

	@Autowired
	private NBlivb01m13Dao nBlivb01m13Dao;
	
	@Override
	public LazyLoader<PrepayOcc> getPrepayOccForUnpaidBill(CBillComm bill) {
		return nBlivb01m13Dao.getPrepayOccForUnpaidBill(bill);
	}
	
	@Override
	public List<CBillComm> getUnpaidBillAmt(CBillComm bill) {
		return nBlivb01m13Dao.getUnpaidBillAmt(bill);
	}
	
	@Override
	public int insertOverpayBillAply(OverpayBillAply overpayBillAply) {
		return nBlivb01m13Dao.insertOverpayBillAply(overpayBillAply);
	}

	@Override
	public int insertOverpayBillAply(List<OverpayBillAply> overpayBillAplyList) {
		return nBlivb01m13Dao.insertOverpayBillAply(overpayBillAplyList);
	}

	@Override
	public List<OverpayBillAply> getOverpayBillInfo(OverpayBillAply overpayBillAply) {
		return nBlivb01m13Dao.getOverpayBillInfo(overpayBillAply);
	}
	
	@Override
	public Double getRemainRcptAmtFromBillWrk(CBillComm bill) {
		return nBlivb01m13Dao.getRemainRcptAmtFromBillWrk(bill);
	}
	
	@Override
	public List<CBillComm> getBillWrkInfoForPrepay(CBillComm bill) {
		return nBlivb01m13Dao.getBillWrkInfoForPrepay(bill);
	}
	
	@Override
	public int updateBillWrkRcptAmt(List<CBillComm> billList) {
		return nBlivb01m13Dao.updateBillWrkRcptAmt(billList);
	}
	
	@Override
	public int updateOverpayBillAply(List<OverpayBillAply> overpayBillAplyList) {
		return nBlivb01m13Dao.updateOverpayBillAply(overpayBillAplyList);
	}
	
}

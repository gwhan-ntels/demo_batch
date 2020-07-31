package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.BillTgtCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m15Dao;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;

@Service
public class NBlivb01m15ServiceImpl extends BaseService implements NBlivb01m15Service {
	
	@Autowired
	private NBlivb01m15Dao nBlivb01m15Dao;
	
	@Override
	public LazyLoader<OverpayBillAply> getOverpayBillAplyList(CBillComm bill) {
		return nBlivb01m15Dao.getOverpayBillAplyList(bill);
	}

	@Override
	public List<CBillComm> getBillList(CBillComm bill) {
		return nBlivb01m15Dao.getBillList(bill);
	}

	@Override
	public int updateBill(List<CBillComm> billList) {
		return nBlivb01m15Dao.updateBill(billList);
	}
	
	@Override
	public int updateBillMast(List<CBillComm> billList) {
		return nBlivb01m15Dao.updateBillMast(billList);
	}

	@Override
	public List<String> getSmlAmtYn(CBillComm bill) {
		
		checkEmpty(bill.getBillSeqNo(), "billSeqNo");
		checkEmpty(bill.getSoId(), "soId");
		
		return nBlivb01m15Dao.getSmlAmtYn(bill);
	}

	@Override
	public int updateSmlAmtYn(List<BillTgtCust> billTgtCustList) {
		return nBlivb01m15Dao.updateSmlAmtYn(billTgtCustList);
	}

	@Override
	public int updateOverpayBillAply(List<OverpayBillAply> overpayBillAplyList) {
		return nBlivb01m15Dao.updateOverpayBillAply(overpayBillAplyList);
	}

	@Override
	public int updatePrepayOcc(List<PrepayOcc> prepayOccList) {
		return nBlivb01m15Dao.updatePrepayOcc(prepayOccList);
	}

}

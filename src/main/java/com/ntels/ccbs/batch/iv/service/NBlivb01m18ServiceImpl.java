package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.exception.BatchException;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.BillInvoice;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m18Dao;

@Service
public class NBlivb01m18ServiceImpl extends BaseService implements NBlivb01m18Service {

	@Autowired
	private NBlivb01m18Dao nBlivb01m18Dao;
	
	@Override
	public int deletePrevData(CBillComm cBillComm) {
		return nBlivb01m18Dao.deletePrevData(cBillComm);
	}
	
	@Override
	public LazyLoader<BillInvoice> getBillInvoiceInfo(CBillComm cBillComm) throws BatchException {
		
		checkEmptyBillYymm(cBillComm.getBillYymm());
		checkEmptySoId(cBillComm.getSoId());
		
		return nBlivb01m18Dao.getBillInvoiceInfo(cBillComm);
	}
	
	@Override
	public int insertBillInvoice(List<BillInvoice> billInvoiceList) {
		return nBlivb01m18Dao.insertBillInvoice(billInvoiceList);
	}
	
	@Override
	public LazyLoader<BillInvoice> getBillInvoiceListForPrint(BillInvoice billInvoice) {
		return nBlivb01m18Dao.getBillInvoiceListForPrint(billInvoice);
	}
	
	@Override
	public LazyLoader<BillInvoice> getBillInvoiceListForEml(BillInvoice billInvoice) {
		return nBlivb01m18Dao.getBillInvoiceListForEml(billInvoice);
	}

}
package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.BillInvoice;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface NBlivb01m18Dao {

	int deletePrevData(CBillComm cBillComm);
	
	LazyLoader<BillInvoice> getBillInvoiceInfo(CBillComm cBillComm);
	
	int insertBillInvoice(List<BillInvoice> billInvoiceList);
	
	LazyLoader<BillInvoice> getBillInvoiceListForPrint(BillInvoice billInvoice);

	LazyLoader<BillInvoice> getBillInvoiceListForEml(BillInvoice billInvoice);

}

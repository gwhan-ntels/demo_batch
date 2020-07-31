package com.ntels.ccbs.batch.iv.common.dao;

import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.Invoice;

public interface InvoiceDao {

	Integer getInvcRissSeq(Invoice invoice);
	
	List<Invoice> getM0000DByBillSeqNo(CBillComm bill);
	
	List<Invoice> getD1010DByBillSeqNo(CBillComm bill);
	
	int insertInvoice(List<Invoice> invoiceList);
	
}

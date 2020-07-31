package com.ntels.ccbs.batch.iv.common.service;

import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.Invoice;

public interface InvoiceService {

	Integer getInvcRissSeq(Invoice invoice);
	
	List<Invoice> getM0000DByBillSeqNo(CBillComm bill);
	
	List<Invoice> getD1010DByBillSeqNo(CBillComm bill);
	
	int insertInvoice(List<Invoice> invoiceList);
	
	/**
	 * <pre>
	 * 청구번호로 인쇄데이터를 생성한다.
	 * </pre>
	 * @param bill
	 */
	void printByBillSeqNo(CBillComm bill);
	
	void printEmail();
	
	void printUi(CBillComm bill);
	
}

package com.ntels.ccbs.batch.py.service;

import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.PaymentResult;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

public interface PaymentService {

	public interface ProcessPaymentCallback {
		
		Receipt getReceipt();
		
	}
	
	/**
	 * 수납 처리를 진행한다.
	 * @param billSeqNo
	 * @param pymSeqNo
	 * @param dpstSeqNo
	 * @param dpstAmt
	 * @return
	 */
	PaymentResult processPayment(String billSeqNo, String pymSeqNo, double dpstAmt, ProcessPaymentCallback callback);
	
	Double getBillRcptAmt(String billSeqNo, String useYymm, String prodCmpsId, String svcCmpsId, String chrgItmCd);
	
	int insertRcptDtlSelectBill(List<ReceiptDetail> receiptDetailList);
	
	int updateBillMastRcptAmt(List<CBillComm> billList);
	
	int updateFullPayBill(CBillComm bill);
	
	int updateFullPayBill(List<CBillComm> billList);
	
	int updateBillRcptAmt(CBillComm bill);
	
	int updateBillRcptAmt(List<CBillComm> billList);
	
	int updateDpstProc(Deposit deposit);
	
	int updateDpstProc(List<Deposit> depositList);
	
	/**
	 * 입금취소를 할 때 참조했던 청구내역의 금액과 완납여부를 갱신한다.
	 * @param bill
	 * @return
	 */
	int updateBillCancel(CBillComm bill);
	
	int updateBillMastCancel(CBillComm bill);
	
	int updateBillMastCancel(List<CBillComm> billList);
	
}

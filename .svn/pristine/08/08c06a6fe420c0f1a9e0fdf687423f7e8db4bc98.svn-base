package com.ntels.ccbs.batch.py.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

public interface PaymentDao {

	Double getBillRcptAmt(CBillComm bill);
	
	Double getUnpayAmtFromBillMast(String billSeqNo);
	
	CBillComm getBillMastInfo(String billSeqNo);
	
	List<CBillComm> getBillListByBillSeqNo(String billSeqNo);
	
	int insertRcptDtlSelectBill(List<ReceiptDetail> receiptDetailList);
	
	int insertRcptSelectBill(Receipt receipt);
	
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
	
	/**
	 * 입금취소를 할 때 참조했던 청구내역의 금액과 완납여부를 갱신한다.
	 * @param bill
	 * @return
	 */
	int updateBillCancel(List<CBillComm> bill);
	
	int updateBillMastCancel(CBillComm bill);
	
	int updateBillMastCancel(List<CBillComm> bill);
	
}

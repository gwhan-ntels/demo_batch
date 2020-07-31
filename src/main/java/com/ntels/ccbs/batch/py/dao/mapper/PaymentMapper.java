package com.ntels.ccbs.batch.py.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

public interface PaymentMapper {

	Double getBillRcptAmt(@Param("bill") CBillComm bill);
	
	Double getUnpayAmtFromBillMast(@Param("billSeqNo") String billSeqNo);
	
	CBillComm getBillMastInfo(@Param("billSeqNo") String billSeqNo);
	
	List<CBillComm> getBillListByBillSeqNo(@Param("billSeqNo") String billSeqNo);
	
	int insertRcptDtlSelectBill(@Param("receiptDetail") ReceiptDetail receiptDetail);
	
	int insertRcptSelectBill(@Param("receipt") Receipt receipt);
	
	int updateBillMastRcptAmt(@Param("bill") CBillComm bill);
	
	int updateFullPayBill(@Param("bill") CBillComm bill);
	
	int updateBillRcptAmt(@Param("bill") CBillComm bill);
	
	int updateDpstProc(@Param("deposit") Deposit deposit);
	
	int updateDpstPayProc(@Param("deposit") Deposit deposit);
	
	int updatePrepayTgtYn(@Param("deposit") Deposit deposit);
	
	int updateAmbgTgtYn(@Param("deposit") Deposit deposit);
	
	/**
	 * 입금취소를 할 때 참조했던 청구내역의 금액과 완납여부를 갱신한다.
	 * @param bill
	 * @return
	 */
	int updateBillCancel(@Param("bill") CBillComm bill);
	
	int updateBillMastCancel(@Param("bill") CBillComm bill);
	
}

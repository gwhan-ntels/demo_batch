package com.ntels.ccbs.batch.py.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptCancel;
import com.ntels.ccbs.batch.py.entity.ReceiptCancelAppl;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

@Component
public interface ReceiptMapper {

	/**
	 * 청구내역에 수정된 데이타를 수납상세내역에 등록한다.
	 * @param pyRcptDtl
	 * @return
	 */
	int insertReceiptDetail(@Param("receiptDetail") ReceiptDetail receiptDetail);
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록한다.
	 * @param receipt
	 * @return
	 */
	int insertReceipt(@Param("receipt") Receipt receipt);
	
	/**
	 * 대리점수납정보에 등록하기위해 RcptDtl정보를 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	List<ReceiptDetail> getReceiptDetailforBondRcptTr(@Param("dpstSeqNo") String dpstSeqNo, @Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 해당 수납 시퀀스 번호에 해당하는 납부 금액의 합을 가져온다.
	 * @param pymSeqNo
	 * @return
	 */
	Double getSumRcptAmt(@Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 수납내역을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	Receipt getReceipt(@Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 수납상세내역을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	List<ReceiptDetail> getReceiptDetail(@Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 입금시퀀스에 해당하는 수납 상세내역을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	List<ReceiptDetail> getReceiptDetailList(@Param("dpstSeqNo") String dpstSeqNo, @Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 입금시퀀스에 해당하는 수납을 취소한다.
	 * @param dpstSeqNo
	 * @return
	 */
	int updateReceiptCancel(@Param("dpstSeqNo") String dpstSeqNo, @Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 취소된 입금내역 시퀀스로 입금구분값을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	String getCanceledDpstCl(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 수납대상금액에서 수납적용금액을 뺀 값을 조회
	 * @param dpstSeqNo
	 * @return
	 */
	List<Receipt> getReceiptBillInfo(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 해당 수납번호로 이미 취소 여부를 조회한다.
	 * @return
	 */
	String getCancelYn(@Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * 수납취소결재신청에 등록한다.
	 * @param receiptCancelAppl
	 * @return
	 */
	int insertReceiptCancelAppl(@Param("receiptCancelAppl") ReceiptCancelAppl receiptCancelAppl);
	
	/**
	 * 수납취소내역에 등록한다.
	 * @return
	 */
	int insertReceiptCancel(@Param("receiptCancel") ReceiptCancel receiptCancel);
	
	/**
	 * 수납취소상세내역에 등록한다.
	 * @param receiptDetail
	 * @return
	 */
	int insertReceiptCancelDetail(@Param("receiptDetail") ReceiptDetail receiptDetail);
}

package com.ntels.ccbs.batch.py.service;

import java.util.List;

import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

public interface ReceiptService {

	/**
	 * 청구내역에 수정된 데이타를 수납상세내역에 등록한다.
	 * @param receiptDetail
	 * @return
	 */
	int insertReceiptDetail(ReceiptDetail receiptDetail);
	
	/**
	 * 청구내역에 수정된 데이타를 수납상세내역에 등록한다.
	 * @param receiptDetailList
	 * @return
	 */
	int insertReceiptDetail(List<ReceiptDetail> receiptDetailList);
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록한다.
	 * @param receipt
	 * @return
	 */
	int insertReceipt(Receipt receipt);
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록한다.
	 * @param receiptList
	 * @return
	 */
	int insertReceipt(List<Receipt> receiptList);
	
	/**
	 * 대리점수납정보에 등록하기위해 RcptDtl정보를 조회한다.
	 * dpstSeqNo값이 있으면 dpstSeqNo값으로 조회를 하고
	 * dpstSeqNo값이 없으면 pymSeqNo값으로 조회를 한다.
	 * @param pymSeqNo
	 * @return
	 */
	List<ReceiptDetail> getReceiptDetailforBondRcptTr(String dpstSeqNo, String pymSeqNo);
	
	/**
	 * 해당 수납 시퀀스 번호에 해당하는 납부 금액의 합을 가져온다.
	 * @param pymSeqNo
	 * @return
	 */
	double getSumRcptAmt(String pymSeqNo);
	
	/**
	 * 선수금발생 내역을 저장하기 위해 납부내역을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	Receipt getReceipt(String pymSeqNo);
	
	/**
	 * 입금시퀀스에 해당하는 수납 상세내역을 조회한다.
	 * 입금시퀀스값이 없다면 수납 시퀀스값으로 조회한다.
	 * @param dpstSeqNo
	 * @param pymSeqNo
	 * @return
	 */
	List<ReceiptDetail> getReceiptDetailList(String dpstSeqNo, String pymSeqNo);
	
	/**
	 * 입금시퀀스 또는 수납 시퀀스에 해당하는 수납을 취소한다.
	 * @param dpstSeqNo
	 * @return
	 */
	int updateReceiptCancel(String dpstSeqNo, String pymSeqNo);
	
	/**
	 * 취소된 입금내역 시퀀스로 입금구분값을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	String getCanceledDpstCl(String dpstSeqNo);
	
	/**
	 * 수납대상금액에서 수납적용금액을 뺀 값을 조회
	 * @param dpstSeqNo
	 * @return
	 */
	List<Receipt> getReceiptBillInfo(String dpstSeqNo);
	
	/**
	 * 해당 수납번호로 이미 취소 여부를 조회한다.
	 * @return
	 */
	String getCancelYn(String pymSeqNo);
	
	/**
	 * 수납취소결재신청에 등록한다.
	 * @param pymSeqNo
	 * @param regrId
	 * @return
	 */
	int insertReceiptCancelAppl(String pymSeqNo, String regrId);
	
	/**
	 * 수납취소내역과 수납취소상세내역에 등록한다.
	 * @param cnclResn
	 * @param pymSeqNo
	 * @param receiptId 접수번호
	 * @return
	 */
	int insertReceiptCancel(String cnclResn, String pymSeqNo, String receiptId, String regrId);
	
}

package com.ntels.ccbs.batch.py.service;

import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.entity.EachDeposit;

public interface NBlpyb09m02JdbcService {
	
	/**
	 * 파싱이 완료된 데이터로부터 PyEachDpst객체를 생성하여 반환한다.
	 * @return
	 */
	EachDeposit getEachDpstFromParsedData(String[] data);
	
	/**
	 * 해당 청구번호를 가지고 TBLIV_BILL_MAST테이븚에서 미납금액을 조회한다.
	 * @param billSeqNo
	 * @return
	 */
	Double getUnpayAmtFromBillMast(String billSeqNo);
	
	/**
	 * 청구 마스터 테이블(TBLIV_BILL_MAST)에 수납금액을 갱신시킨다.
	 * 만약 더 이상 수납을 할 금액이 없다면 FULL_PAY_YN = 'Y'로 갱신
	 * @param bill
	 * @return
	 */
	int updateBillMastRcptAmt(CBillComm bill);
	
	/**
	 * 청구 마스터 테이블(TBLIV_BILL_MAST)에 수납금액을 갱신시킨다.
	 * 만약 더 이상 수납을 할 금액이 없다면 FULL_PAY_YN = 'Y'로 갱신
	 * @param bill
	 * @return
	 */
	int updateBillMastRcptAmt(List<CBillComm> billList);
	
	/**
	 * 청구 테이블(TBLIV_BILL)에 수납금액을 갱신시킨다.
	 * 만약 더 이상 수납을 할 금액이 없다면 FULL_PAY_YN = 'Y'로 갱신
	 * 필수 데이터 : billSeqNo, useYymm, prodCmpsId, svcCmpsId
	 * @param bill
	 * @return
	 */
	int updateBillRcptAmt(CBillComm bill);
	
	/**
	 * 청구 테이블(TBLIV_BILL)에 수납금액을 갱신시킨다.
	 * 만약 더 이상 수납을 할 금액이 없다면 FULL_PAY_YN = 'Y'로 갱신
	 * 필수 데이터 : billSeqNo, useYymm, prodCmpsId, svcCmpsId
	 * @param bill
	 * @return
	 */
	int updateBillRcptAmt(List<CBillComm> billList);
	
	/**
	 * 청구 테이블(TBLIV_BILL)에 청구번호에 해당하는 청구내역들을
	 * 완납 처리한다.
	 * @param bill
	 * @return
	 */
	int updateFullPayBill(CBillComm bill);
	
	/**
	 * 청구 테이블(TBLIV_BILL)에 청구번호에 해당하는 청구내역들을
	 * 완납 처리한다.
	 * @param bill
	 * @return
	 */
	int updateFullPayBill(List<CBillComm> billList);
	
	/**
	 * 청구내역의 전월/당월 수납금액을 수정한다.
	 * @param pyEachDpst
	 * @param billSeqNo
	 * @param billYymm
	 * @return
	 */
//	int updateBill(EachDeposit pyEachDpst, String billSeqNo, String billYymm);

}

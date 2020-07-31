package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.TaxBill;
import com.ntels.ccbs.batch.iv.common.entity.TaxTarget;

public interface NBlivb01m09Service {
	
	/**
	 * <pre>
	 * 부가세를 계산한다.
	 * </pre>
	 * @param billAmt 청구금액
	 * @param vatRate 부가세율
	 * @param vatTp 부가세 타입
	 * @return
	 */
	double getVat(double billAmt, double vatRate, String vatTp);
	
	/**
	 * <pre>
	 * 틀별소비세를 계산한다.
	 * </pre>
	 * @param billAmt 청구금액
	 * @param vatAmt 부가세 금액
	 * @param sctRate 틀별소비세율
	 * @param sctTp 특별소비세 타입
	 * @return
	 */
	double getSct(double billAmt, double vatAmt, double sctRate, String sctTp);
	
	/**
	 * <pre>
	 * 세금계산 대상 리스트 조회
	 * 필수 파라미터 : billYymm, billCycl, billDt, soId
	 * </pre>
	 * @param bill
	 * @return
	 */
	LazyLoader<TaxTarget> getTaxTargetList(CBillComm bill);

	/**
	 * <pre>
	 * 세금계산 대상 리스트 조회
	 * 필수 파라미터 : billSeqNo, billYymm, soId
	 * </pre>
	 * @param bill
	 * @return
	 */
	LazyLoader<TaxTarget> getTaxTargetListByBillSeqNo(CBillComm bill);
	
	int insertBillWrk(List<CBillComm> billList);
	
	int insertTaxBill(List<TaxBill> taxBillList);
	
	/**
	 * TBLIV_SIMULATION_BILL테이블의 데이터를 조회하여, 세금계산 후
	 * 부가세, 특소세의 데이터를 다시 동일 테이블에 삽입한다.
	 * 
	 * 필수 파라미터 : billSeqNo, soId, billYymm
	 * @param bill
	 * @return
	 */
	int insertSimulationBillTax(Common bill);
	
	int insertAdjBillTax(CBillComm bill, String remark);

	String getRoundingAdjSvcRateItmTypCd(String soId);
	
}

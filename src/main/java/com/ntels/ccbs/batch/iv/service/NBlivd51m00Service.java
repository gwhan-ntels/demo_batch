package com.ntels.ccbs.batch.iv.service;

import java.sql.Timestamp;
import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.AdjBill;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.ChrgAdjAply;
import com.ntels.ccbs.batch.iv.common.entity.SaleAdj;
import com.ntels.ccbs.batch.iv.common.entity.TaxTarget;
import com.ntels.ccbs.batch.iv.common.service.BillRePbls;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;

public interface NBlivd51m00Service {

	List<AdjBill> getAdjBillList(String adjNo, String soId, String exrateAplyDt);
	
	double getRcptAmt(CBillComm bill);
	
	int updateAdjAplyDcsnProcStat(Timestamp chgDate, String adjNo);
	
	List<Receipt> getPymSeqNo(Receipt receipt);
	
	List<PrepayOcc> getPrepayOccInfo(PrepayOcc prepayOcc);
	
	int deleteRcpt(Receipt receipt);
	
	int deleteRcptDtl(Receipt receipt);
	
	int deletePrepayOcc(PrepayOcc prepayOcc);
	
	int updateBillNotPaid(CBillComm bill);
	
	int updateBillMastNotPaid(CBillComm bill);
	
	List<CBillComm> getAdjBillInfo(CBillComm bill);
	
	int updateBillAdjAmt(CBillComm bill);
	
	int updateBillMastAdjAmt(CBillComm bill);

	void updateVatAdj(CBillComm bill);
	
	/**
	 * <pre>
	 * 부가세 조정을 위한 데이터 조회
	 * 필수 파라미터 값 : billSeqNo, soId
	 * </pre>
	 * @param bill
	 * @return
	 */
	List<TaxTarget> getAdjTaxList(CBillComm bill);
	
	/**
	 * <pre>
	 * 부가세 조정매출
	 * 필수 파라미터 : billSeqNo, useYymm, prodCmpsId, svcCmpsId, chrgItmCd, soId, vat
	 * </pre>
	 * @param bill
	 * @return
	 */
	int updateBillAdjVat(CBillComm bill);
	
	/**
	 * <pre>
	 * 부가세 조정매출
	 * 필수 파라미터 : billSeqNo, soId, vat
	 * </pre>
	 * @param bill
	 * @return
	 */
	int updateBillMastAdjVat(CBillComm bill);
	
	List<SaleAdj> getSaleAdjList(CBillComm bill);
	
	int insertSaleAdj(List<SaleAdj> saleAdjList);

	int deleteTaxBill(CBillComm bill);
	
	List<SaleAdj> getSaleBillAdj(CBillComm bill);
	
	Integer getReIssSeq(String billSeqNo);
	
	int insertBillRePbls(BillRePbls billRePbls);
	
	int updateChrgAdjAply(ChrgAdjAply chrgAdjAply);
	
	int updateCmctRfndAcntInfo(CBillComm bill);
	
	double getTotBillAmtWithoutRounding(CBillComm bill);
	
	boolean existsRoundingAdjustment(CBillComm bill);
	
	int updateRoundingAdjustment(CBillComm bill);
	
}

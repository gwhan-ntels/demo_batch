package com.ntels.ccbs.batch.iv.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.common.service.BillingUtilService;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.AdjBill;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.ChrgAdjAply;
import com.ntels.ccbs.batch.iv.common.entity.SaleAdj;
import com.ntels.ccbs.batch.iv.common.entity.TaxTarget;
import com.ntels.ccbs.batch.iv.common.service.BillRePbls;
import com.ntels.ccbs.batch.iv.dao.NBlivd51m00Dao;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;

@Service
public class NBlivd51m00ServiceImpl extends BaseService implements NBlivd51m00Service {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private NBlivd51m00Dao nBlivd51m00Dao;
	
	@Autowired
	private NBlivb01m09Service nBlivb01m09Service;
	
	@Autowired
	private BillingUtilService billUtilService;
	
	@Value("${sr.cd.tax}")
	private String billItemVat;
	
	@Value("${sr.cd.rounding}")
	private String billItemRounding;
	
	@Override
	public List<AdjBill> getAdjBillList(String adjNo, String soId, String exrateAplyDt) {
		AdjBill adjBill = new AdjBill();
		adjBill.setAdjNo(adjNo);
		adjBill.setSoId(soId);
		adjBill.setExrateAplyDt(exrateAplyDt);
		
		return nBlivd51m00Dao.getAdjBillList(adjBill);
	}
	
	@Override
	public double getRcptAmt(CBillComm bill) {
		Double rcptAmt = nBlivd51m00Dao.getRcptAmt(bill);
		
		if (rcptAmt == null) {
			return 0;
		}
		
		return rcptAmt;
	}
	
	@Override
	public int updateAdjAplyDcsnProcStat(Timestamp chgDate, String adjNo) {
		return nBlivd51m00Dao.updateAdjAplyDcsnProcStat(chgDate, adjNo);
	}
	
	@Override
	public List<Receipt> getPymSeqNo(Receipt receipt) {
		return nBlivd51m00Dao.getPymSeqNo(receipt);
	}
	
	@Override
	public List<PrepayOcc> getPrepayOccInfo(PrepayOcc prepayOcc) {
		return nBlivd51m00Dao.getPrepayOccInfo(prepayOcc);
	}
	
	@Override
	public int deleteRcpt(Receipt receipt) {
		return nBlivd51m00Dao.deleteRcpt(receipt);
	}
	
	@Override
	public int deleteRcptDtl(Receipt receipt) {
		return nBlivd51m00Dao.deleteRcptDtl(receipt);
	}
	
	@Override
	public int deletePrepayOcc(PrepayOcc prepayOcc) {
		return nBlivd51m00Dao.deletePrepayOcc(prepayOcc);
	}
	
	@Override
	public int updateBillNotPaid(CBillComm bill) {
		return nBlivd51m00Dao.updateBillNotPaid(bill);
	}
	
	@Override
	public int updateBillMastNotPaid(CBillComm bill) {
		return nBlivd51m00Dao.updateBillMastNotPaid(bill);
	}
	
	@Override
	public List<CBillComm> getAdjBillInfo(CBillComm bill) {
		return nBlivd51m00Dao.getAdjBillInfo(bill);
	}
	
	@Override
	public int updateBillAdjAmt(CBillComm bill) {
		return nBlivd51m00Dao.updateBillAdjAmt(bill);
	}
	
	@Override
	public int updateBillMastAdjAmt(CBillComm bill) {
		return nBlivd51m00Dao.updateBillMastAdjAmt(bill);
	}
	
	/**
	 * <pre>
	 * 부가세 조정을 위한 데이터 조회
	 * 필수 파라미터 값 : billSeqNo, soId
	 * </pre>
	 * @param bill
	 * @return
	 */
	@Override
	public void updateVatAdj(CBillComm bill) {
		checkEmpty(bill.getBillSeqNo(), "billSeqNo");
		checkEmpty(bill.getSoId(), "soId");
		
		String billYymm = "20" + bill.getBillSeqNo().substring(0, 4);
		bill.setBillYymm(billYymm);
		
		List<TaxTarget> list1 = nBlivd51m00Dao.getAdjTaxList(bill);
		
		double totBillAmtWithVat = 0;
		double sumBillAmt = 0;
		double sumAdjAmt = 0;
		double sumVatAmt = 0;
		double sumSctAmt = 0;
		
		TaxTarget prevItem = null;
		
		// 세금 계산
		for (TaxTarget adjBill : list1) {
			double vatRate = adjBill.getVatRate() == null ? 0 : adjBill.getVatRate() / 100;
			double vat = nBlivb01m09Service.getVat(adjBill.getBillAmt(), vatRate, adjBill.getVatTp());
			
			adjBill.setVat(vat);
			
//			double sctRate = adjBill.getSctRate() == null ? 0 : adjBill.getSctRate() / 100;
//			double sct = nBlivb01m09Service.getSct(adjBill.getBillAmt(), vat, sctRate, adjBill.getSctTp());
//			
//			adjBill.setSct(sct);
			
			if (prevItem == null) {
				prevItem = adjBill;
			}
			
			if (prevItem.getBillSeqNo().equals(adjBill.getBillSeqNo()) == false
					|| prevItem.getUseYymm().equals(adjBill.getUseYymm()) == false
					|| prevItem.getProdCmpsId().equals(adjBill.getProdCmpsId()) == false
					|| prevItem.getSvcCmpsId().equals(adjBill.getSvcCmpsId()) == false) {

				prevItem = adjBill;
				
				// 조정 세금 반영
				CBillComm updateBill = new CBillComm();
				updateBill.setVat(sumVatAmt);
				updateBill.setBillSeqNo(prevItem.getBillSeqNo());
				updateBill.setUseYymm(prevItem.getUseYymm());
				updateBill.setProdCmpsId(prevItem.getProdCmpsId());
				updateBill.setSvcCmpsId(prevItem.getSvcCmpsId());
				updateBill.setChrgItmCd(billItemVat);
				updateBillAdjVat(updateBill);

				sumBillAmt = 0;
				sumAdjAmt = 0;
				sumVatAmt = 0;
				sumSctAmt = 0;
			}
			
			sumBillAmt += adjBill.getBillAmt();
			sumAdjAmt += adjBill.getAdjAmt();
			sumVatAmt += adjBill.getVat();
			sumSctAmt += adjBill.getSct();
			
			totBillAmtWithVat += adjBill.getBillAmt();
			totBillAmtWithVat += adjBill.getVat();
			
			clogService.writeLog("CHRG_ITM_CD : " + adjBill.getChrgItmCd());
			clogService.writeLog("BILL_AMT : " + adjBill.getBillAmt() + ", VAT : " + vat);
//			clogService.writeLog("BILL_AMT : " + adjBill.getBillAmt() + ", SCT : " + sct);
			clogService.writeLog("SUM_BILL_AMT : " + sumBillAmt + ", SUM_ADJ_AMT : " + sumAdjAmt + ", SUM_VAT_AMT : " + sumVatAmt + ", SUM_SCT_AMT : " + sumSctAmt);
		}
		
		prevItem.setBillAmt(sumBillAmt);
		prevItem.setAdjAmt(sumAdjAmt);
		prevItem.setVat(sumVatAmt);
		prevItem.setSct(sumSctAmt);
		
		// 라운딩 처리
		double billAmtAfterRounding = billUtilService.adjustLastDigit(totBillAmtWithVat);
		double roundingAmt = billAmtAfterRounding - totBillAmtWithVat;
		
		CBillComm updateBill = new CBillComm();
		updateBill.setVat(roundingAmt);
		updateBill.setBillSeqNo(prevItem.getBillSeqNo());
		updateBill.setUseYymm(prevItem.getUseYymm());
		updateBill.setProdCmpsId(prevItem.getProdCmpsId());
		updateBill.setSvcCmpsId(prevItem.getSvcCmpsId());
		updateBill.setChrgItmCd(billItemRounding);
		updateBillAdjVat(updateBill);
		updateBillMastAdjVat(bill);
		
//		TaxTarget prvAdjTax = nBlivd51m00Dao.getPrevAdjTax(bill);
//		
//		for (TaxTarget taxTarget : sumList) {
//			taxTarget.setVatAdjAmt(prvAdjTax.getVatAdjAmt());
//			taxTarget.setSctAdjAmt(prvAdjTax.getSctAdjAmt());
//			
//			clogService.writeLog(String.format("BILL_SEQ_NO : %s, USE_YYMM : %s, PROD_CMPS_ID : %s, SVC_CMPS_ID : %s",
//					taxTarget.getBillSeqNo(), taxTarget.getUseYymm(), taxTarget.getProdCmpsId(),
//					taxTarget.getSvcCmpsId()));
//
//			clogService.writeLog("조정 부가세 금액 : " + taxTarget.getVatAdjAmt());
//			clogService.writeLog("조정 특소세 금액 : " + taxTarget.getSctAdjAmt());
//			clogService.writeLog("부가세 금액 : " + taxTarget.getVat());
//			clogService.writeLog("특소세 금액 : " + taxTarget.getSct());
//		}
	}
	
	/**
	 * <pre>
	 * 부가세 조정을 위한 데이터 조회
	 * 필수 파라미터 값 : billSeqNo, soId
	 * </pre>
	 * @param bill
	 * @return
	 */
	@Override
	public List<TaxTarget> getAdjTaxList(CBillComm bill) {
		checkEmpty(bill.getBillSeqNo(), "billSeqNo");
		checkEmpty(bill.getSoId(), "soId");
		
		String billYymm = "20" + bill.getBillSeqNo().substring(0, 4);
		bill.setBillYymm(billYymm);
		
		List<TaxTarget> list1 = nBlivd51m00Dao.getAdjTaxList(bill);
		List<TaxTarget> sumList = new ArrayList<>();
		
		double sumBillAmt = 0;
		double sumAdjAmt = 0;
		double sumVatAmt = 0;
		double sumSctAmt = 0;
		
		TaxTarget prevItem = null;
		
		// 세금 계산
		for (TaxTarget adjBill : list1) {
			double vatRate = adjBill.getVatRate() == null ? 0 : adjBill.getVatRate() / 100;
			double vat = nBlivb01m09Service.getVat(adjBill.getBillAmt(), vatRate, adjBill.getVatTp());
			
			adjBill.setVat(vat);
			
			double sctRate = adjBill.getSctRate() == null ? 0 : adjBill.getSctRate() / 100;
			double sct = nBlivb01m09Service.getSct(adjBill.getBillAmt(), vat, sctRate, adjBill.getSctTp());
			
			adjBill.setSct(sct);
			
			if (prevItem == null) {
				prevItem = adjBill;
			}
			
			if (prevItem.getBillSeqNo().equals(adjBill.getBillSeqNo()) == false
//					|| prevItem.getUseYymm().equals(adjBill.getUseYymm()) == false
//					|| prevItem.getProdCmpsId().equals(adjBill.getProdCmpsId()) == false
//					|| prevItem.getSvcCmpsId().equals(adjBill.getSvcCmpsId()) == false
					) {
				prevItem.setBillAmt(sumBillAmt);
				prevItem.setAdjAmt(sumAdjAmt);
				prevItem.setVat(sumVatAmt);
				prevItem.setSct(sumSctAmt);

				sumList.add(prevItem);
				prevItem = adjBill;

				sumBillAmt = 0;
				sumAdjAmt = 0;
				sumVatAmt = 0;
				sumSctAmt = 0;
			}
			
			sumBillAmt += adjBill.getBillAmt();
			sumAdjAmt += adjBill.getAdjAmt();
			sumVatAmt += adjBill.getVat();
			sumSctAmt += adjBill.getSct();
			
			clogService.writeLog("CHRG_ITM_CD : " + adjBill.getChrgItmCd());
			clogService.writeLog("BILL_AMT : " + adjBill.getBillAmt() + ", VAT : " + vat);
			clogService.writeLog("BILL_AMT : " + adjBill.getBillAmt() + ", SCT : " + sct);
			clogService.writeLog("SUM_BILL_AMT : " + sumBillAmt + ", SUM_ADJ_AMT : " + sumAdjAmt + ", SUM_VAT_AMT : " + sumVatAmt + ", SUM_SCT_AMT : " + sumSctAmt);
		}
		
		prevItem.setBillAmt(sumBillAmt);
		prevItem.setAdjAmt(sumAdjAmt);
		prevItem.setVat(sumVatAmt);
		prevItem.setSct(sumSctAmt);

		sumList.add(prevItem);
		
		TaxTarget prvAdjTax = nBlivd51m00Dao.getPrevAdjTax(bill);
		
		for (TaxTarget taxTarget : sumList) {
			taxTarget.setVatAdjAmt(prvAdjTax.getVatAdjAmt());
			taxTarget.setSctAdjAmt(prvAdjTax.getSctAdjAmt());
			
			clogService.writeLog(String.format("BILL_SEQ_NO : %s, USE_YYMM : %s, PROD_CMPS_ID : %s, SVC_CMPS_ID : %s",
					taxTarget.getBillSeqNo(), taxTarget.getUseYymm(), taxTarget.getProdCmpsId(),
					taxTarget.getSvcCmpsId()));

			clogService.writeLog("조정 부가세 금액 : " + taxTarget.getVatAdjAmt());
			clogService.writeLog("조정 특소세 금액 : " + taxTarget.getSctAdjAmt());
			clogService.writeLog("부가세 금액 : " + taxTarget.getVat());
			clogService.writeLog("특소세 금액 : " + taxTarget.getSct());
		}
		
		return sumList;
	}
	
	/**
	 * <pre>
	 * 부가세 조정매출
	 * 필수 파라미터 : billSeqNo, useYymm, prodCmpsId, svcCmpsId, chrgItmCd, soId, vat
	 * </pre>
	 * @param bill
	 * @return
	 */
	@Override
	public int updateBillAdjVat(CBillComm bill) {
		return nBlivd51m00Dao.updateBillAdjVat(bill);
	}
	
	/**
	 * <pre>
	 * 부가세 조정매출
	 * 필수 파라미터 : billSeqNo, soId, vat
	 * </pre>
	 * @param bill
	 * @return
	 */
	@Override
	public int updateBillMastAdjVat(CBillComm bill) {
		return nBlivd51m00Dao.updateBillMastAdjVat(bill);
	}
	
	@Override
	public int deleteTaxBill(CBillComm bill) {
		return nBlivd51m00Dao.deleteTaxBill(bill);
	}
	
	@Override
	public List<SaleAdj> getSaleAdjList(CBillComm bill) {
		return nBlivd51m00Dao.getSaleAdjList(bill);
	}
	
	@Override
	public int insertSaleAdj(List<SaleAdj> saleAdjList) {
		return nBlivd51m00Dao.insertSaleAdj(saleAdjList);
	}
	
	@Override
	public List<SaleAdj> getSaleBillAdj(CBillComm bill) {
		return nBlivd51m00Dao.getSaleBillAdj(bill);
	}
	
	@Override
	public Integer getReIssSeq(String billSeqNo) {
		return nBlivd51m00Dao.getReIssSeq(billSeqNo);
	}
	
	@Override
	public int insertBillRePbls(BillRePbls billRePbls) {
		return nBlivd51m00Dao.insertBillRePbls(billRePbls);
	}
	
	@Override
	public int updateChrgAdjAply(ChrgAdjAply chrgAdjAply) {
		return nBlivd51m00Dao.updateChrgAdjAply(chrgAdjAply);
	}
	
	@Override
	public int updateCmctRfndAcntInfo(CBillComm bill) {
		return nBlivd51m00Dao.updateCmctRfndAcntInfo(bill);
	}
	
	@Override
	public double getTotBillAmtWithoutRounding(CBillComm bill) {
		return nBlivd51m00Dao.getTotBillAmtWithoutRounding(bill);
	}
	
	@Override
	public boolean existsRoundingAdjustment(CBillComm bill) {
		return nBlivd51m00Dao.existsRoundingAdjustment(bill);
	}
	
	@Override
	public int updateRoundingAdjustment(CBillComm bill) {
		return nBlivd51m00Dao.updateRoundingAdjustment(bill);
	}
	
}

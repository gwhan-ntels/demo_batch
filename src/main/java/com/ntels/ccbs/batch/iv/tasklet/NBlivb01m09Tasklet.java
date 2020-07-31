package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.BillingUtilService;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.TaxBill;
import com.ntels.ccbs.batch.iv.common.entity.TaxBillBuffer;
import com.ntels.ccbs.batch.iv.common.entity.TaxTarget;
import com.ntels.ccbs.batch.iv.entity.NBlivb01m09;
import com.ntels.ccbs.batch.iv.service.NBlivb01m09Service;

@Component("nBlivb01m09Tasklet")
@Scope("step")
public class NBlivb01m09Tasklet extends LazyLoaderLogingTasklet<TaxTarget, NBlivb01m09> {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private NBlivb01m09Service nBlivb01m09Service;
	
	@Autowired
	private BillingUtilService billingUtilService;
	
	@Value("${sr.cd.tax}")
	private String billItemVat;
	
	@Value("${sr.cd.rounding}")
	private String billItemRounding;
	
//	@Value("${bill.item.cut}")
//	private String billItemCut;
//	
//	@Value("${bill.item.sct}")
//	private String billItemSct;
	
	private String beforeBillYymmdd;
	
	private String oldBillSeqNo = null;
	private String oldBillMmTp = null;
	private String oldSoId = null;
	private String oldProdCmpsId = null;
	private String oldSvcCmpsId = null;
	
	// 부가세 아이템 코드
	private String oldVchrgItmCd = "";
	// 특소세 아이템 코드
	private String oldSchrgItmCd = "";
	
	double billAmt = 0;
	double billAmt1 = 0;
	double billAmt3 = 0;
	
	double vatBillAmt = 0;
	double vatBillAmt1 = 0;
	double vatBillAmt3 = 0;
	
	double billAmtOfBillSeqNo = 0;
	double vatOfBillSeqNo = 0;
	
//	double sctBillAmt = 0;
//	double sctBillAmt1 = 0;
//	double sctBillAmt3 = 0;
	
	private TaxBillBuffer taxBillBuffer;
	
	@Override
	protected boolean isInsertPgmLog() {
		return true;
	}
	
	@Override
	protected boolean isUpdatePgmLog() {
		return true;
	}
	
	@Override
	protected LazyLoader<TaxTarget> getLoader() throws Exception {
		
		beforeBillYymmdd = CUtil.addMonths(billYymm, -1);
		beforeBillYymmdd = beforeBillYymmdd + CUtil.getLastDay(beforeBillYymmdd);
		
		CBillComm bill = new CBillComm();
		bill.setBillYymm(billYymm);
		bill.setBillCycl(billCycl);
		bill.setBillDt(getBillDt());
		bill.setSoId(soId);
		bill.setPayDueDt(getPayDueDt());
		bill.setpSeq(pgmSeq);
		return nBlivb01m09Service.getTaxTargetList(bill);
	}

	@Override
	protected NBlivb01m09 process(TaxTarget taxTarget) {
		NBlivb01m09 nBliv01m09 = new NBlivb01m09();
		
		if (oldBillSeqNo == null) {
			oldBillSeqNo = taxTarget.getBillSeqNo();
			oldBillMmTp = taxTarget.getBillMmTp();
			oldSoId = taxTarget.getSoId();
			oldProdCmpsId = taxTarget.getProdCmpsId();
			oldSvcCmpsId = taxTarget.getSvcCmpsId();
		}
		
		// 세금 계산
		if ("Y".equals(taxTarget.getTaxTp()) == true) {
			// 부가세 계산
			double vatRate = taxTarget.getVatRate() == null ? 0 : taxTarget.getVatRate() / 100;
			double vatAmt = nBlivb01m09Service.getVat(taxTarget.getBillAmt(), vatRate, taxTarget.getVatTp());
			
			if ("2".equals(taxTarget.getVatTp()) == true) {
				taxTarget.setBillAmt(taxTarget.getBillAmt() - vatAmt);
			}
			
			taxTarget.setVat(vatAmt);
			
//			double sctRate = taxTarget.getSctRate() == null ? 0 : taxTarget.getSctRate() / 100;
//			double sctAmt = nBlivb01m09Service.getSct(taxTarget.getBillAmt(), vatAmt, sctRate, taxTarget.getSctTp());
//			taxTarget.setSct(sctAmt);
			
			clog.writeLog("==================== CALC TAX ====================");
			clog.writeLog("BILL_SEQ_NO : {}, CHRG_ITM_CD : {}, BILL_AMT : {}, VAT : {}, SCT : {}"
					, taxTarget.getBillSeqNo(), taxTarget.getChrgItmCd(), taxTarget.getBillAmt(), taxTarget.getVat(), taxTarget.getSct());
			clog.writeLog("BILL_MM_TP : {}, PROD_CMPS_ID : {}, SVC_CMPS_ID : {}"
					, taxTarget.getBillMmTp(), taxTarget.getProdCmpsId(), taxTarget.getSvcCmpsId());
//			clog.writeLog("vatBillAmt1 : {}, sctBillAmt1 : {}", vatBillAmt1, sctBillAmt1);
			clog.writeLog("vatBillAmt1 : {}", vatBillAmt1);
			clog.writeLog("==================================================");
			
		}

		// 부가세 아이템 코드
		if (taxTarget.getVchrgItmCd() != null && taxTarget.getVchrgItmCd().trim().length() > 0 
				&& oldVchrgItmCd.equals(taxTarget.getVchrgItmCd()) == false) {
			oldVchrgItmCd = taxTarget.getVchrgItmCd();
		}
		
		// 부가세 아이템 코드
		if (taxTarget.getSchrgItmCd() != null && taxTarget.getSchrgItmCd().trim().length() > 0 
				&& oldSchrgItmCd.equals(taxTarget.getSchrgItmCd()) == false) {
			oldSchrgItmCd = taxTarget.getSchrgItmCd();
		}
		
		if (oldBillSeqNo.equals(taxTarget.getBillSeqNo()) == false) {
				
//			clog.writeLog(String.format("CHANGE BILL_SEQ_NO : %s, VAT : %f, SCT : %f", taxTarget.getBillSeqNo(), vatBillAmt1, sctBillAmt1));
//			clog.writeLog("TAX BILL BUFFER BILL_SEQ_NO : " + taxBillBuffer.getBillSeqNo());
			
//			if (vatBillAmt1 > 0 || sctBillAmt1 > 0) {
//				if (vatBillAmt1 > 0) {
//					CBillComm bill = makeBill(taxBillBuffer, vatBillAmt1, billItemVat);
//					bill.setChrgItmCd(oldVchrgItmCd);
//					nBliv01m09.addBill(bill);
//				}
//
//				if (sctBillAmt1 > 0) {
//					CBillComm bill = makeBill(taxBillBuffer, sctBillAmt1, billItemSct);
//					bill.setChrgItmCd(oldSchrgItmCd);
//					nBliv01m09.addBill(bill);
//				}
//				
//				TaxBill taxBill = makeTaxBill(taxBillBuffer, billAmt3, vatBillAmt3, sctBillAmt3);
//				nBliv01m09.addTaxBill(taxBill);
//			}
			
			// 청구번호가 변경 되었을 때 세금 다시 계산해본다.
			double vatRate = taxTarget.getVatRate() == null ? 0 : taxTarget.getVatRate() / 100;
			double tempVat = nBlivb01m09Service.getVat(billAmtOfBillSeqNo, vatRate, "1");
			
			clog.writeLog("tempVat : {}, vatOfBillSeqNo : {}", tempVat, vatOfBillSeqNo);
			
			CBillComm bill = makeBill(taxBillBuffer, vatBillAmt1, billItemVat);
			
			if (vatBillAmt1 > 0) {
//				bill.setChrgItmCd(oldVchrgItmCd);
				nBliv01m09.addBill(bill);
				
				TaxBill taxBill = makeTaxBill(taxBillBuffer, billAmt3, vatBillAmt3, 0);
				nBliv01m09.addTaxBill(taxBill);
			}
			
			// Rounding Adjustment
			CBillComm ronding = roundingAdjustment(bill, billAmt3, vatOfBillSeqNo);
			nBliv01m09.addBill(ronding);

			oldBillSeqNo = taxTarget.getBillSeqNo();
			oldBillMmTp = taxTarget.getBillMmTp();
			oldSoId = taxTarget.getSoId();
			oldProdCmpsId = taxTarget.getProdCmpsId();
			oldSvcCmpsId = taxTarget.getSvcCmpsId();
			
			billAmt1 = 0;
			billAmt3 = 0;
			
			vatBillAmt1 = 0;
			vatBillAmt3 = 0;
			
			vatOfBillSeqNo = 0;
			billAmtOfBillSeqNo = 0;
			
//			sctBillAmt1 = 0;
//			sctBillAmt3 = 0;
		} else if (oldBillMmTp.equals(taxTarget.getBillMmTp()) == false
				|| oldSoId.equals(taxTarget.getSoId()) == false) {
//			if (vatBillAmt1 > 0 || sctBillAmt1 > 0) {
//				if (vatBillAmt1 > 0) {
//					CBillComm bill = makeBill(taxBillBuffer, vatBillAmt1, billItemVat);
//					bill.setChrgItmCd(oldVchrgItmCd);
//					nBliv01m09.addBill(bill);
//				}
//
//				if (sctBillAmt1 > 0) {
//					CBillComm bill = makeBill(taxBillBuffer, sctBillAmt1, billItemSct);
//					bill.setChrgItmCd(oldSchrgItmCd);
//					nBliv01m09.addBill(bill);
//				}
//				
//				TaxBill taxBill = makeTaxBill(taxBillBuffer, billAmt3, vatBillAmt3, sctBillAmt3);
//				nBliv01m09.addTaxBill(taxBill);
//			}
			
			if (vatBillAmt1 > 0) {
				CBillComm bill = makeBill(taxBillBuffer, vatBillAmt1, billItemVat);
//				bill.setChrgItmCd(oldVchrgItmCd);
				nBliv01m09.addBill(bill);
				
				TaxBill taxBill = makeTaxBill(taxBillBuffer, billAmt3, vatBillAmt3, 0);
				nBliv01m09.addTaxBill(taxBill);
			}

			oldBillMmTp = taxTarget.getBillMmTp();
			oldSoId = taxTarget.getSoId();
			oldProdCmpsId = taxTarget.getProdCmpsId();
			oldSvcCmpsId = taxTarget.getSvcCd();
			
			billAmt1 = 0;
			billAmt3 = 0;
			
			vatBillAmt1 = 0;
			vatBillAmt3 = 0;
			
//			sctBillAmt1 = 0;
//			sctBillAmt3 = 0;

//		} else if (oldProdCmpsId.equals(taxTarget.getProdCmpsId()) == false
//				|| oldSvcCmpsId.equals(taxTarget.getSvcCmpsId()) == false) {
////			if (vatBillAmt1 > 0 || sctBillAmt1 > 0) {
////				if (vatBillAmt1 > 0) {
////					CBillComm bill = makeBill(taxBillBuffer, vatBillAmt1, billItemVat);
////					bill.setChrgItmCd(oldVchrgItmCd);
////					nBliv01m09.addBill(bill);
////				}
////
////				if (sctBillAmt1 > 0) {
////					CBillComm bill = makeBill(taxBillBuffer, sctBillAmt1, billItemSct);
////					bill.setChrgItmCd(oldSchrgItmCd);
////					nBliv01m09.addBill(bill);
////				}
////			}
//			
//			if (vatBillAmt1 > 0) {
//				CBillComm bill = makeBill(taxBillBuffer, vatBillAmt1, billItemVat);
////				bill.setChrgItmCd(oldVchrgItmCd);
//				nBliv01m09.addBill(bill);
//			}
//			
//			oldProdCmpsId = taxTarget.getProdCd();
//			oldSvcCmpsId = taxTarget.getSvcCmpsId();
//			
//			billAmt1 = 0;
//			vatBillAmt1 = 0;
////			sctBillAmt1 = 0;
		}
		
		billAmt = taxTarget.getBillAmt();
		vatBillAmt = taxTarget.getVat();
//		sctBillAmt = taxTarget.getSct();
		
//		if (vatBillAmt > 0 || sctBillAmt > 0) {
//			billAmt3 += billAmt;
//			vatBillAmt3 += vatBillAmt;
//			sctBillAmt3 += sctBillAmt;
//		}
		
		if (vatBillAmt > 0) {
			billAmt3 += billAmt;
			vatBillAmt3 += vatBillAmt;
		}
		
		billAmt1 += billAmt;
		vatBillAmt1 += vatBillAmt;
		vatOfBillSeqNo += vatBillAmt;
		billAmtOfBillSeqNo += billAmt;
//		sctBillAmt1 += sctBillAmt;
		
		taxBillBuffer = getBuffer(taxTarget);
		
		if (isLastItem == true) {
			
			clog.writeLog("==================== LAST ITEM ====================");
			clog.writeLog("BILL_SEQ_NO : {}", taxTarget.getBillSeqNo());
//			clog.writeLog("vatBillAmt1 : {}, sctBillAmt1 : {}", vatBillAmt1, sctBillAmt1);
			clog.writeLog("vatBillAmt1 : {}", vatBillAmt1);
			// 전체의 마지막 처리를 한다.
//			if (vatBillAmt1 > 0 || sctBillAmt1 > 0) {
//				if (vatBillAmt1 > 0) {
//					CBillComm bill = makeBill(taxBillBuffer, vatBillAmt1, billItemVat);
//					bill.setChrgItmCd(oldVchrgItmCd);
//					nBliv01m09.addBill(bill);
//				}
//
//				if (sctBillAmt1 > 0) {
//					CBillComm bill = makeBill(taxBillBuffer, sctBillAmt1, billItemSct);
//					bill.setChrgItmCd(oldSchrgItmCd);
//					nBliv01m09.addBill(bill);
//				}
//
//				TaxBill taxBill = makeTaxBill(taxBillBuffer, billAmt3, vatBillAmt3, sctBillAmt3);
//				nBliv01m09.addTaxBill(taxBill);
//			}
			
			CBillComm bill = makeBill(taxBillBuffer, vatBillAmt1, billItemVat);
			
			if (vatBillAmt1 > 0) {
//				bill.setChrgItmCd(oldVchrgItmCd);
				nBliv01m09.addBill(bill);
				
				TaxBill taxBill = makeTaxBill(taxBillBuffer, billAmt3, vatBillAmt3, 0);
				nBliv01m09.addTaxBill(taxBill);

			}
			
			// Rounding Adjustment
			CBillComm ronding = roundingAdjustment(bill, billAmt3, vatOfBillSeqNo);
			nBliv01m09.addBill(ronding);
		}

		return nBliv01m09;
	}
	
	private TaxBill makeTaxBill(TaxBillBuffer taxBillBuffer, double billAmt, double vatBillAmt, double sctBillAmt) {
		TaxBill taxBill = new TaxBill();
		CUtil.copyObjectValue(taxBillBuffer, taxBill);
		
		if ("1".equals(taxBillBuffer.getBillMmTp()) == true) {
			taxBill.setSaleDt(beforeBillYymmdd);
			
			if ("B".equals(taxBillBuffer.getCustTp()) == true || "D".equals(taxBillBuffer.getCustTp()) == true) {
				taxBill.setTaxBillIssDt(getTaxIssDt());
				taxBill.setTaxBillWrDt(getTaxIssDt());
			} else {
				taxBill.setTaxBillIssDt(beforeBillYymmdd);
				taxBill.setTaxBillWrDt(beforeBillYymmdd);
			}
			
		} else {
			taxBill.setSaleDt(taxBillBuffer.getBillDt());
//			taxBill.setTaxBillIssDt(taxIssDt);
//			taxBill.setTaxBillWrDt(taxIssDt);
		}
		
		taxBill.setRepNm(taxBill.getCustNm());
		taxBill.setTbiFlg("N");
		taxBill.setSplyAmt(billAmt);
		taxBill.setVat(vatBillAmt);
		taxBill.setSct(sctBillAmt);
		
		return taxBill;
	}

	private CBillComm makeBill(TaxBillBuffer taxBillBuffer, double billAmt, String billItemCd) {
		
		clog.writeLog("==================== MAKE BILL ====================");
		clog.writeLog("BILL_SEQ_NO : {}, PROD_CMPS_ID : {}, SVC_CMPS_ID : {}, BILL_AMT(VAT) : {}", taxBillBuffer.getBillSeqNo(), taxBillBuffer.getProdCmpsId(), taxBillBuffer.getSvcCmpsId(), billAmt);
		clog.writeLog("===================================================");
		
		CBillComm bill = new CBillComm();
		CUtil.copyObjectValue(taxBillBuffer, bill);
		
		bill.setBillAmt(billAmt);
		bill.setAdjPrvBillAmt(billAmt);
		bill.setAdjAmt(0.0);
		bill.setWonBillAmt(billAmt);
		bill.setChrgItmCd(billItemCd);
		
		return bill;
	}

	private TaxBillBuffer getBuffer(TaxTarget taxTarget) {
		TaxBillBuffer taxBillBuffer = new TaxBillBuffer();
		CUtil.copyObjectValue(taxTarget, taxBillBuffer);
		
		taxBillBuffer.setAdjAmt(0.0);
		taxBillBuffer.setFullPayYn("N");
		taxBillBuffer.setRcptAmt(0.0);
		taxBillBuffer.setUseCnt(0);
		taxBillBuffer.setUseQty(0);
		
		return taxBillBuffer;
	}
	
	private CBillComm roundingAdjustment(CBillComm billWrk, double billAmt, double taxAmt) {
		
		clog.writeLog("roundingAdjustment \nBILL WRK\n{}\nBILL AMT : {}, TAX AMT : {}", clog.objectToString(billWrk), billAmt, taxAmt);
		
		CBillComm rounding = new CBillComm();
		CUtil.copyObjectValue(billWrk, rounding);
		
		double amount = billAmt + taxAmt;
		
		double roundingAdjustment = billingUtilService.adjustLastDigit(amount);
		rounding.setBillAmt(-(amount - roundingAdjustment));
		rounding.setAdjPrvBillAmt(rounding.getBillAmt());
		rounding.setWonBillAmt(rounding.getBillAmt());
		rounding.setChrgItmCd(billItemRounding);
		
		return rounding;
	}

	@Override
	protected void write(List<NBlivb01m09> itemList) {
		List<CBillComm> billList = new ArrayList<>();
		List<TaxBill> taxBillList = new ArrayList<>();

		for (NBlivb01m09 nBlivb01m09 : itemList) {
			if (nBlivb01m09.getBillList() != null) {
				billList.addAll(nBlivb01m09.getBillList());	
			}

			if (nBlivb01m09.getTaxBillList() != null) {
				taxBillList.addAll(nBlivb01m09.getTaxBillList());	
			}

		}
		
		clog.writeLog("BILL WRK LIST SIZE : {}", billList.size());
		clog.writeLog("TAX BILL LIST SIZE : {}", taxBillList.size());

		Timestamp now = new Timestamp(new Date().getTime());
		
		for (CBillComm bill : billList) {
			bill.setSaleTp("01");
			bill.setUsgCnt(0L);
			bill.setUsgAmt(0L);
			bill.setRcptAmt(bill.getRcptAmt() == null ? 0.0 : bill.getRcptAmt());
			bill.setRegDate(now);
			bill.setChgDate(now);
			bill.setDebtProcYn("N");
			
			// SR00000254
			clog.writeLog("INSERT BILL, BILL_SEQ_NO : {}", bill.getBillSeqNo());
		}
		
		// BL001
		List<Integer> seqList = commonService.createNewSequence("BL001", taxBillList.size());
		int seqIndex = 0;
		
		for (TaxBill taxBill : taxBillList) {
			// TODO 시퀀스 번호 발급처리
			// saleDt + soId + seq + "0"
//			String seqNo = billCommService.getTaxIssNo();
			String taxIssNo = taxBill.getSaleDt() + taxBill.getSoId() + String.format("%08d", seqList.get(seqIndex++)) + "0";
			taxBill.setTaxIssNo(taxIssNo);
			taxBill.setTaxDclrCl("0");
			taxBill.setRegDate(now);
			
			clog.writeLog("INSERT TAX BILL\n{}", clog.objectToString(taxBill));
		}

		nBlivb01m09Service.insertBillWrk(billList);
		nBlivb01m09Service.insertTaxBill(taxBillList);
	}

	@Override
	protected RepeatStatus end() {
		// TODO Auto-generated method stub
		return null;
	}

}

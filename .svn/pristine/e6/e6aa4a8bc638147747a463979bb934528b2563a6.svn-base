package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrepayBillAply;
import com.ntels.ccbs.batch.iv.common.entity.PrepayCtrt;
import com.ntels.ccbs.batch.iv.entity.NBlivb01m12;
import com.ntels.ccbs.batch.iv.service.NBlivb01m12Service;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

@Component("nBlivb01m12JobProcessor")
@Scope("step")
public class NBlivb01m12JobProcessor implements ItemProcessor<PrepayBillAply, NBlivb01m12>, StepExecutionListener {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private NBlivb01m12Service nBlivb01m12Service;
	
	private String oldPymAcntId = "";
	private double billAmt = 0;
	private boolean isSkip = false;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		
	}

	@Override
	public NBlivb01m12 process(PrepayBillAply prepayBillAply) throws Exception {
		
		double mthBillAmt = 0;
		final NBlivb01m12 nBlivb01m12 = new NBlivb01m12();
		
		// 월 선납 할인율 적용
		if (prepayBillAply.getMpdRate() == 0) {
			// 적용개월수가 12개월 이하인 경우 5% 적용
			if (prepayBillAply.getAplyMthCnt() < 0) {
				prepayBillAply.setMpdRate(5);
			} else {
				prepayBillAply.setMpdRate(10);
			}
		}
		
		if (prepayBillAply.getMthBillAmt() == 0) {
            // 선납계약테이블의 월청구금액이 없는 경우 월청구금액을 계산한다.
            // 왜냐하면 금액단위로 선납을 적용할 경우 최대 적용해야 할 금액기준이 월청구금액이기때문에..
			mthBillAmt = CUtil.Round(prepayBillAply.getMpatAmt() / 90 * 100, 0, 1);
		} else {
			mthBillAmt = prepayBillAply.getMthBillAmt();
		}
		
		// 선납적용방법이 금액인 경우
		if ("10".equals(prepayBillAply.getPrepdCl()) == true) {
			if (oldPymAcntId.equals(prepayBillAply.getPymAcntId()) == false) {
				oldPymAcntId = prepayBillAply.getPymAcntId();
				billAmt = 0;
				isSkip = false;
			}
			
			if (isSkip == true) {
				return nBlivb01m12;
			}
			
			billAmt += prepayBillAply.getSumAmt();

			if (billAmt > mthBillAmt) {
                // 청구내역의 청구금액 합계가 선납계약의 월청구금액을 초과하면
                // 1. 선납적용금액 = 월선납적용대상금액
                // 2. 선납할인금액 = 월청구금액에 할인율을 적용한 금액
				prepayBillAply.setPrepdAplyAmt(prepayBillAply.getMpatAmt());
				double prepdDcAmt = CUtil.Round(mthBillAmt * prepayBillAply.getMpdRate() / 100, 0, 1);
				prepayBillAply.setPrepdDcAmt(prepdDcAmt);
				
				// 다음부터는 스킵
				isSkip = true; 
			} else if (billAmt > prepayBillAply.getMpatAmt()) {
                // 청구내역의 청구금액 합계가 선납계약의 월선납적용대상금액을 초과하면
                // 1. 선납적용금액 = 월선납적용대상금액
                // 2. 선납할인금액 = 청구금액합 - 월선납적용대상금액
				prepayBillAply.setPrepdAplyAmt(prepayBillAply.getMpatAmt());
				prepayBillAply.setPrepdDcAmt(prepayBillAply.getSumAmt() - prepayBillAply.getMpatAmt());

				isSkip = true;
			} else {
                // 청구금액의 합계가 월선납적용대상금액보다 적으면
                // 1. 선납적용금액 = 청구금액합
                // 2. 선납할인금액 = 0
				prepayBillAply.setPrepdAplyAmt(prepayBillAply.getSumAmt());
				prepayBillAply.setPrepdDcAmt(0);
			}
		} else if ("20".equals(prepayBillAply.getPrepdCl()) == true) {
			// 선납적용방법이 요율인 경우
			double prepdDcAmt = CUtil.Round(prepayBillAply.getSumAmt() * prepayBillAply.getMpdRate() / 100, 0, 1);
			prepayBillAply.setPrepdDcAmt(prepdDcAmt);
			prepayBillAply.setPrepdAplyAmt(prepayBillAply.getSumAmt() - prepayBillAply.getPrepdDcAmt());
		}
		
		prepayBillAply.setPayProcYn("N");
		prepayBillAply.setCnclYn("N");
		prepayBillAply.setRegDate(new Timestamp(new Date().getTime()));
		prepayBillAply.setCrtDttm(CUtil.utilGetDateTime(1));
		
		nBlivb01m12.setPrepayBillAply(prepayBillAply);
		
		int result = receiveBillAply(prepayBillAply, nBlivb01m12);
		
		if (result == 1) {
			// 이미 완납이 되어있으면
			// 예전에는 어떤 처리를 했는데 주석처리가 되어있음
		}
		
		return nBlivb01m12;
	}
	
	public int receiveBillAply(PrepayBillAply prepayBillAply, NBlivb01m12 returnObj) {
		
		String billSeqNo = prepayBillAply.getBillSeqNo();
		String prepdAplyCl = prepayBillAply.getPrepdAplyCl();
		String soId = prepayBillAply.getSoId();
		
		if ("1".equals(prepdAplyCl) == false && "2".equals(prepdAplyCl) == false) {
			prepdAplyCl = "1";
		}

		prepayBillAply = nBlivb01m12Service.getNoPayProceedPrepayBillAply(prepayBillAply);
		
		Double remainRcptAmt = 0.0;
		
		CBillComm amtSearchBill = new CBillComm();
		amtSearchBill.setBillSeqNo(billSeqNo);
		amtSearchBill.setSoId(soId);
		amtSearchBill.setCustId(prepayBillAply.getCustId());
		
		if ("1".equals(prepdAplyCl) == true) {
			// 계약으로 조회
			amtSearchBill.setCtrtId(prepayBillAply.getCtrtId());
			remainRcptAmt = nBlivb01m12Service.getRemainRcptAmtFromBillWrk(amtSearchBill);	
		} else {
			// 고객으로 조회
			remainRcptAmt = nBlivb01m12Service.getRemainRcptAmtFromBillWrk(amtSearchBill);
		}
		
		if (remainRcptAmt <= 0) {
			clogService.writeLog("Finished data of TBLIV_BILL_WRK.");
			return 1;
		}
		
//		List<ReceiptDetail> receiptDetailList = new ArrayList<>();
//		List<Receipt> receiptList = new ArrayList<>();
//		List<PrepayBillAply> updatePrepayList = new ArrayList<>();
		
		// 선납적용금액
		double savedPrepdAplyAmt = prepayBillAply.getPrepdAplyAmt();
		// 선납할인금액
		double savedPrepdDcAmt = prepayBillAply.getPrepdDcAmt();
		
		double payObjAmt = 0;
		double rcptAmt = 0;
		
		// 2번 반복!
		for (int i = 0; i < 2; i++) {
			boolean isRemainBillAmt = true;
			
			// 선수금적용액(하나의 선수금 발생금액 - 수납적용액)
			double prepayAplyAmt = 0;
			// 수납적용액(청구내역의 청구금액)
	        double sumPayAplyAmt = 0;
	        // 이전수납금액
	        double preRcptAmt = 0;
	        // 수납대상금액(하나의 선수금 발생금액) - 입금액으로 처리됨
	        double sumPayObjAmt  = 0;
			
			if (i == 0) {
				if (savedPrepdAplyAmt == 0) {
					continue;
				}
			} else {
				if (savedPrepdDcAmt == 0) {
					continue;
				}
			}
			
			List<CBillComm> billWrkList = null;
			
			CBillComm searchBill = new CBillComm();
			searchBill.setBillSeqNo(billSeqNo);
			searchBill.setSoId(soId);
			searchBill.setChrgCtgry("N");
			searchBill.setChrgItmCd("SR00000264");
			
			if ("1".equals(prepdAplyCl) == true) {
				searchBill.setCtrtId(prepayBillAply.getCtrtId());
				billWrkList = nBlivb01m12Service.getBillWrkInfoForPrepayByCtrt(searchBill);
			} else {
				searchBill.setCustId(prepayBillAply.getCustId());
				billWrkList = nBlivb01m12Service.getBillWrkInfoForPrepayByCust(searchBill);
			}
			
			String pymSeqNo = pyCommService.getPymSeqNo();
			String dpstSeqNo = pyCommService.getDpstSeqNo();
			String prepayTransSeqNo = pyCommService.getPrepayTransSeqNo();
			
			CBillComm refBill = null;
			
			for (CBillComm cBillComm : billWrkList) {
				
				if (refBill == null) {
					refBill = cBillComm;
				}
				
				if (isRemainBillAmt == false || cBillComm.getBillAmt() < 0					// 남아있는 청구 금액이 없거나 
						|| cBillComm.getCustId().equals(prepayBillAply.getCustId()) == false  // 청구내역의 고객이 선수금내역의 고객과 다르거나
						|| cBillComm.getFullPayYn().equals("N") == false) {					// 완납된 청구내역이라면
					
					ReceiptDetail receiptDetail = new ReceiptDetail();
					CUtil.copyObjectValue(cBillComm, receiptDetail);
					
					receiptDetail.setPymSeqNo(pymSeqNo);
					receiptDetail.setPreSoId(cBillComm.getSoId());
					receiptDetail.setRegDate(new Timestamp(new Date().getTime()));
					
					returnObj.addReceiptDetail(receiptDetail);
//					receiptDetailList.add(receiptDetail);
					
					sumPayAplyAmt += cBillComm.getBillAmt();
					preRcptAmt += cBillComm.getRcptAmt();
					
					continue;
				}
				
				double compValue = 0;
				
				if (i == 0) {
					// 선납적용금액으로 수납처리를 한다.
					compValue = savedPrepdAplyAmt;
				} else {
					// 선납할인금액으로 수납처리를 한다.
					compValue = savedPrepdDcAmt;
				}
				
				if (cBillComm.getBillAmt() - cBillComm.getRcptAmt() >= compValue) {
					// 청구내역의 청구금액과 선납금청구반영내역의 선납적용금액 계산.
					// 더 이상 적용시킬 선납적용금액이 없음
					isRemainBillAmt = false;
					// 청구내역과 수납상세의 입금액
					payObjAmt = compValue;
					// 입금금액이 청구금액보다 적은 경우에는 선수금은 없다.
					prepayAplyAmt = 0;
				} else {
					// 청구내역과 수납상세의 입금액
					payObjAmt = cBillComm.getBillAmt() - cBillComm.getRcptAmt();
				}
				
				// 입금내역의 수납적용액
				sumPayObjAmt += payObjAmt;
				rcptAmt += payObjAmt;
				
				if (i == 0) {
					savedPrepdAplyAmt -= payObjAmt;
				} else {
					savedPrepdDcAmt -= payObjAmt;
				}
				
				// 수납적용액(청구내역의 청구금액) 누적
				sumPayAplyAmt += cBillComm.getBillAmt();
				// 청구내역의 수납액을 이전수납금액 누적
				preRcptAmt += cBillComm.getRcptAmt();
				
				CBillComm updateBill = new CBillComm();
				updateBill.setRcptAmt(payObjAmt);
				updateBill.setBillSeqNo(cBillComm.getBillSeqNo());
				updateBill.setUseYymm(cBillComm.getUseYymm());
				updateBill.setProdCmpsId(cBillComm.getProdCd());
				updateBill.setSvcCmpsId(cBillComm.getSvcCmpsId());
				updateBill.setChrgItmCd(cBillComm.getChrgItmCd());
				updateBill.setSoId(cBillComm.getSoId());
				updateBill.setChgDate(new Timestamp(new Date().getTime()));

				returnObj.addUpdateBill(updateBill);
				
				ReceiptDetail receiptDetail = new ReceiptDetail();
				CUtil.copyObjectValue(cBillComm, receiptDetail);
				
				receiptDetail.setPymSeqNo(pymSeqNo);
				receiptDetail.setPreSoId(cBillComm.getSoId());
				receiptDetail.setRegDate(new Timestamp(new Date().getTime()));
				
				returnObj.addReceiptDetail(receiptDetail);
//				receiptDetailList.add(receiptDetail);
			}
			
			// 처리 된 수납내역이 존재한다면..
			if (refBill != null) {
				
				Receipt receipt = new Receipt();
				CUtil.copyObjectValue(refBill, receipt);
				
				receipt.setPymSeqNo(pymSeqNo);
				receipt.setDpstSeqNo(dpstSeqNo);
				receipt.setPrepayTransSeqNo(prepayTransSeqNo);
				receipt.setPreRcptAmt(preRcptAmt);
				receipt.setPayObjAmt(sumPayObjAmt);
				receipt.setPayAplyAmt(sumPayAplyAmt);
				receipt.setPrepayAplyAmt(prepayAplyAmt);
				receipt.setRcptAmt(rcptAmt);
				
				if (i == 0) {
					// 선납적용수납
					receipt.setPayTp("5");
				} else {
					// 선납할인적용수납
					receipt.setPayTp("6");
				}
				
				receipt.setRegDate(new Timestamp(new Date().getTime()));
				returnObj.addReceipt(receipt);
//				receiptList.add(receipt);
				
				PrepayBillAply updatePrepay = new PrepayBillAply();
				updatePrepay.setPayProcDt(CUtil.utilGetDateTime(2));
				updatePrepay.setBillSeqNo(prepayBillAply.getBillSeqNo());
				updatePrepay.setSoId(prepayBillAply.getSoId());
				updatePrepay.setBillMmTp(prepayBillAply.getBillMmTp());
				updatePrepay.setPrepdCtrtId(prepayBillAply.getPrepdCtrtId());
				
				returnObj.addUpdatePrepay(prepayBillAply);
//				updatePrepayList.add(updatePrepay);
				
			}
		}
		
		// 선납적용금액저장
		savedPrepdAplyAmt = prepayBillAply.getPrepdAplyAmt() - savedPrepdAplyAmt;
		// 선납할인금액저장
		savedPrepdDcAmt = prepayBillAply.getPrepdDcAmt() - savedPrepdDcAmt;
		
		PrepayCtrt prepayCtrt = new PrepayCtrt();
		prepayCtrt.setPrepdCtrtId(prepayBillAply.getPrepdCtrtId());
		prepayCtrt.setSoId(prepayBillAply.getSoId());
		prepayCtrt.setPrepdAplyAmt(savedPrepdAplyAmt);
		prepayCtrt.setPrepdDcAmt(savedPrepdDcAmt);
		prepayCtrt.setChgDate(new Timestamp(new Date().getTime()));
		
		returnObj.addUpdatePrepayCtrt(prepayCtrt);

		return 0;
		
	}

}

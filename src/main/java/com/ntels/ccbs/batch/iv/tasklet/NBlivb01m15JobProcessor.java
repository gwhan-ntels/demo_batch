package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.BillTgtCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.iv.entity.NBlivb01m15;
import com.ntels.ccbs.batch.iv.service.NBlivb01m15Service;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.entity.PaymentResult;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.PrepayTransHistory;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;
import com.ntels.ccbs.batch.py.service.PaymentService;
import com.ntels.ccbs.batch.py.service.PaymentService.ProcessPaymentCallback;

@Component("nBlivb01m15JobProcessor")
@Scope("step")
public class NBlivb01m15JobProcessor implements ItemProcessor<OverpayBillAply, NBlivb01m15>, StepExecutionListener {

	@Autowired
	private PyCommService pyCommService;

	@Autowired
	private NBlivb01m15Service nBlivb01m15Service;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ClogService clogService;
	
	@Value("#{jobParameters['user']}")
	private String regrId;

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {

	}

	@Override
	public NBlivb01m15 process(OverpayBillAply overpayBill) throws Exception {

		clogService.writeLog(getClass().getSimpleName() + ".process");
		NBlivb01m15 nBlivb01m15 = new NBlivb01m15();

		CBillComm searchBill = new CBillComm();
		searchBill.setBillSeqNo(overpayBill.getBillSeqNo());
		searchBill.setSoId(overpayBill.getSoId());
		final List<CBillComm> billList = nBlivb01m15Service.getBillList(searchBill);
		clogService.writeLog("billList : " + billList.size());

		final String pymSeqNo = pyCommService.getPymSeqNo();
		final String dpstSeqNo = pyCommService.getDpstSeqNo();
		final String prepayTransSeqNo = pyCommService.getPrepayTransSeqNo();
		
		PaymentResult paymentResult = paymentService.processPayment(overpayBill.getBillSeqNo(), pymSeqNo, overpayBill.getPrepayBal(), new ProcessPaymentCallback() {
			
			@Override
			public Receipt getReceipt() {
				Receipt receipt = makeReceiptDateFromBill(billList.get(0));
				receipt.setPymSeqNo(pymSeqNo);
				receipt.setDpstSeqNo(dpstSeqNo);
				receipt.setPrepayTransSeqNo(prepayTransSeqNo);
				return receipt;
			}
		});
		
		for (ReceiptDetail receiptDetail : paymentResult.getReceiptDetailList()) {
			nBlivb01m15.addReceiptDetail(receiptDetail);
		}
		
		for (Receipt receipt : paymentResult.getReceiptList()) {
			nBlivb01m15.addReceiptList(receipt);
		}
		
		if ("Y".equals(paymentResult.getFullPayYn()) == true) {
			for (CBillComm bill : paymentResult.getUpdateBillList()) {
				nBlivb01m15.addFullPayBill(bill);
			}
		} else {
			for (CBillComm bill : paymentResult.getUpdateBillList()) {
				nBlivb01m15.addBillList(bill);
			}			
		}
		
		nBlivb01m15.addBillMast(paymentResult.getUpdateBillMast());
		
		List<String> smlAmtYnList = nBlivb01m15Service.getSmlAmtYn(billList.get(0));
		
		for (String smlAmtYn : smlAmtYnList) {
			BillTgtCust billTgtCust = new BillTgtCust();
			billTgtCust.setSmlAmtYn(smlAmtYn);
			billTgtCust.setBillSeqNo(billList.get(0).getBillSeqNo());
			billTgtCust.setSoId(billList.get(0).getSoId());
			nBlivb01m15.addBillTgtCustList(billTgtCust);
		}
		
		overpayBill.setPayProcDt(CUtil.utilGetDateTime(2));
		nBlivb01m15.addOverpayBillAplyList(overpayBill);
		
		PrepayOcc prepayOcc = new PrepayOcc();
		prepayOcc.setPrepayOccSeqNo(overpayBill.getPrepayOccSeqNo());
		prepayOcc.setPrepayTransAmt(paymentResult.getUpdateBillMast().getRcptAmt());
		prepayOcc.setChgrId(regrId);
		prepayOcc.setChgDate(new Timestamp(new Date().getTime()));
		nBlivb01m15.addPrepayOccList(prepayOcc);
		
		PrepayTransHistory prepayTransHistory = new PrepayTransHistory();
		String dttm = CUtil.utilGetDateTime(1);
		
		prepayTransHistory.setPrepayOccSeqNo(overpayBill.getPrepayOccSeqNo());
		prepayTransHistory.setPrepayTransSeqNo(prepayTransSeqNo);
		prepayTransHistory.setTransProcDttm(dttm);
		prepayTransHistory.setPrepayReplTp("01");
		prepayTransHistory.setTransProcAmt(paymentResult.getUpdateBillMast().getRcptAmt());
		prepayTransHistory.setProcMemo("선수금청구대체");
		prepayTransHistory.setWonReplProcAmt(paymentResult.getUpdateBillMast().getRcptAmt());
		prepayTransHistory.setCrncyCd(billList.get(0).getCrncyCd());
		prepayTransHistory.setExrate(billList.get(0).getExrate());
		prepayTransHistory.setExrateAplyDt(billList.get(0).getExrateAplyDt());
		prepayTransHistory.setRegrId(regrId);
		prepayTransHistory.setRegDate(new Timestamp(new Date().getTime()));
		prepayTransHistory.setDcsnProcStat("05");
		prepayTransHistory.setCnclYn("N");
		prepayTransHistory.setCnclDttm(null);
		
		nBlivb01m15.addPrepayTransHistoryList(prepayTransHistory);
		
//		// 선수금을 모두 수납처리했어도 남은 청구내역도 수납상세를 만들어야 함
//		int isContinue = 1;
//		// 처리할 청구내역이 있는지 구분함(1: 없음, 0: 있음)
//		int firstFlag = 1;
//		
//		// 선수금적용액(하나의 선수금 발생금액 - 수납적용액)
//		double prepayAplyAmt = 0;
//		// 수납대상금액(하나의 선수금 발생금액) - 입금액으로 처리됨
//		double sumPayObjAmt = 0;
//		// 수납적용액(청구내역의 청구금액)
//		double sumPayAplyAmt = 0;
//		// 이전수납금액
//		double preRcptAmt = 0;
//		
//		double payObjAmt = 0;
//		
//		double dpstAmt = overpayBill.getPrepayBal();
//		
//		CBillComm buff = new CBillComm();
//		
//		for (int i = 0;i < billList.size(); i++) {
//			CBillComm bill = billList.get(i);
//			
//			if (firstFlag == 1) {
//				CUtil.copyObjectValue(bill, buff);
//			}
//			
//			firstFlag = 0;
//			
//			// 이미 완납이 된 청구이거나 입금금액이 더이상 남아있지 않다면 패스!
//			if ("Y".equals(bill.getFullPayYn()) == true || dpstAmt <= 0) {
//				continue;
//			}
//			
//			// 선수금을 반영하려고 하는 청구내역의 [지점ID], [청구대상월구분], [완납분]은
//			// [청구금액]과 [이전수납액]만 누적하고 Skip 처리함
//			//
//			// nIsContinue == 0 (==> 동일한 청구일련번호로는 더이상 처리할 금액이 없음)
//			if (isContinue == 0 || bill.getBillAmt() < 0
//					|| bill.getSoId().equals(overpayBill.getSoId()) == false
//					|| bill.getBillMmTp().equals(overpayBill.getBillMmTp()) == false
//					|| bill.getFullPayYn().equals("N") == false) {
//				ReceiptDetail receiptDetail = makeReceiptDetailDataFromBill(bill);
//				receiptDetail.setPymSeqNo(pymSeqNo);
//				receiptDetail.setBillAmt(bill.getBillAmt());
//				receiptDetail.setPreRcptAmt(0);
//				receiptDetail.setRcptAmt(bill.getRcptAmt());
//				receiptDetail.setPreSoId(overpayBill.getSoId());
//				
//				nBlivb01m15.addReceiptDetail(receiptDetail);
//
//				// 수납적용액(청구내역의 청구금액) 누적
//				sumPayAplyAmt += bill.getBillAmt();
//				// 이전수납금액 누적
//				preRcptAmt += bill.getRcptAmt();
//				
//				clogService.writeLog("청구금액이 0보다 적은경우 청구금액 : ");
//				
//				continue;
//			}
//			
//			// 청구내역의 청구금액과 선수금청구반영내역의내역의 선수금 계산.
//			if (bill.getBillAmt() - bill.getRcptAmt() >= overpayBill.getBillAplyAmt()) {
//				payObjAmt = overpayBill.getBillAplyAmt();
//				// 청구내역과 수납상세의 입금액
//				sumPayObjAmt += payObjAmt;
//				// 더이상 적용시킬 선수금 적용액이 없으면 청구내역 업데이트 종료.
//				isContinue = 0;
//				// 청구금액보다 입금금액이 적게 들어왔기 땜시 선수금은 없음.
//				prepayAplyAmt = 0;
//			} else {
//				double rcptAmt = bill.getBillAmt() - bill.getRcptAmt();
//				double billAplyAmt = overpayBill.getBillAplyAmt() - rcptAmt;
//				overpayBill.setBillAplyAmt(billAplyAmt);
//				// 청구내역과 수납상세의 입금액
//				payObjAmt = rcptAmt;
//				sumPayObjAmt += payObjAmt;
//			}
//			
//			sumPayAplyAmt += bill.getBillAmt();
//			preRcptAmt += bill.getRcptAmt();
//			
//			// 청구내역 반영
//			CBillComm updateBill = new CBillComm();
//			CUtil.copyObjectValue(bill, updateBill);
//			updateBill.setRcptAmt(payObjAmt);
//			
//			clogService.writeLog("청구내역의 수납 금액 : " + updateBill.getRcptAmt());
//			
//			nBlivb01m15.addBillList(updateBill);
//			
//			// 당월 청구대상 납입자 정보 반영
//			List<String> smlAmtYnList = nBlivb01m15Service.getSmlAmtYn(bill);
//			
//			for (String smlAmtYn : smlAmtYnList) {
//				BillTgtCust billTgtCust = new BillTgtCust();
//				billTgtCust.setSmlAmtYn(smlAmtYn);
//				billTgtCust.setBillSeqNo(bill.getBillSeqNo());
//				billTgtCust.setSoId(bill.getSoId());
//				nBlivb01m15.addBillTgtCustList(billTgtCust);
//			}
//			
//			ReceiptDetail receiptDetail = makeReceiptDetailDataFromBill(bill);
//			receiptDetail.setPymSeqNo(pymSeqNo);
//			receiptDetail.setBillAmt(bill.getBillAmt());
//			receiptDetail.setPreRcptAmt(payObjAmt);
//			receiptDetail.setRcptAmt(bill.getRcptAmt());
//			receiptDetail.setPreSoId(overpayBill.getSoId());
//			
//			nBlivb01m15.addReceiptDetail(receiptDetail);
//		}
		
		// 청구내역 처리내역이 있는 경우
//		if (firstFlag == 0) {
			
//			CBillComm bill = new CBillComm();
//			bill.setBillSeqNo(overpayBill.getBillSeqNo());
//			bill.setRcptAmt(sumPayAplyAmt);
//			bill.setChgDate(new Timestamp(new Date().getTime()));
//			
//			nBlivb01m15.addBillMast(bill);
//			
//			Receipt receipt = makeReceiptDateFromBill(buff);
//			receipt.setPymSeqNo(pymSeqNo);
//			receipt.setPreRcptAmt(preRcptAmt);
//			receipt.setPayObjAmt(sumPayObjAmt);
//			receipt.setPayAplyAmt(sumPayAplyAmt);
//			
//			prepayAplyAmt = overpayBill.getPrepayBal() - sumPayObjAmt;
//			
//			receipt.setPrepayAplyAmt(prepayAplyAmt <= 0 ? 0 : prepayAplyAmt);
//			receipt.setDpstSeqNo(dpstSeqNo);
//			receipt.setPrepayTransSeqNo(prepayTransSeqNo);
//
//			nBlivb01m15.addReceiptList(receipt);
			
//			overpayBill.setPayProcDt(CUtil.utilGetDateTime(2));
//			nBlivb01m15.addOverpayBillAplyList(overpayBill);
//			
//			PrepayOcc prepayOcc = new PrepayOcc();
//			prepayOcc.setPrepayOccSeqNo(overpayBill.getPrepayOccSeqNo());
//			prepayOcc.setPrepayTransAmt(sumPayObjAmt);
//			prepayOcc.setChgrId(regrId);
//			prepayOcc.setChgDate(new Timestamp(new Date().getTime()));
//			nBlivb01m15.addPrepayOccList(prepayOcc);
//			
//			PrepayTransHistory prepayTransHistory = new PrepayTransHistory();
//			String dttm = CUtil.utilGetDateTime(1);
//			
//			prepayTransHistory.setPrepayOccSeqNo(overpayBill.getPrepayOccSeqNo());
//			prepayTransHistory.setPrepayTransSeqNo(prepayTransSeqNo);
//			prepayTransHistory.setTransProcDttm(dttm);
//			prepayTransHistory.setPrepayReplTp("01");
//			prepayTransHistory.setTransProcAmt(sumPayObjAmt);
//			prepayTransHistory.setProcMemo("선수금청구대체");
//			prepayTransHistory.setWonReplProcAmt(sumPayObjAmt);
//			prepayTransHistory.setCrncyCd(buff.getCrncyCd());
//			prepayTransHistory.setExrate(buff.getExrate());
//			prepayTransHistory.setExrateAplyDt(buff.getExrateAplyDt());
//			prepayTransHistory.setRegrId(regrId);
//			prepayTransHistory.setRegDate(new Timestamp(new Date().getTime()));
//			prepayTransHistory.setDcsnProcStat("05");
//			prepayTransHistory.setCnclYn("N");
//			prepayTransHistory.setCnclDttm(null);
//			
//			nBlivb01m15.addPrepayTransHistoryList(prepayTransHistory);
//		}
		
		return nBlivb01m15;
	}
	
//	private ReceiptDetail makeReceiptDetailDataFromBill(CBillComm bill) {
//		ReceiptDetail receiptDetail = new ReceiptDetail();
//		
//		receiptDetail.setUseYymm(bill.getUseYymm());
//		receiptDetail.setProdCmpsId(bill.getProdCmpsId());
//		receiptDetail.setSvcCmpsId(bill.getSvcCmpsId());
//		receiptDetail.setChrgItmCd(bill.getChrgItmCd());
//		receiptDetail.setSoId(bill.getSoId());
//		receiptDetail.setGrpId(bill.getGrpId());
//		receiptDetail.setCustId(bill.getCustId());
//		receiptDetail.setCtrtId(bill.getCtrtId());
//		receiptDetail.setCrncyCd(bill.getCrncyCd());
//		receiptDetail.setExrate(bill.getExrate());
//		receiptDetail.setExrateAplyDt(bill.getExrateAplyDt());
//		receiptDetail.setBillSeqNo(bill.getBillSeqNo());
//		receiptDetail.setRegDate(new Timestamp(new Date().getTime()));
//		
//		return receiptDetail;
//	}
	
	private Receipt makeReceiptDateFromBill(CBillComm bill) {
		
		Receipt receipt = new Receipt();
		
		receipt.setBillSeqNo(bill.getBillSeqNo());
		receipt.setBillYymm(bill.getBillYymm());
		receipt.setBillCycl(bill.getBillCycl());
		receipt.setBillDt(bill.getBillDt());
		receipt.setPymAcntId(bill.getPymAcntId());
		
		String dt = CUtil.utilGetDateTime(2);

		receipt.setPayProcDt(dt);
		receipt.setDpstProcDt(dt);
		receipt.setDpstDt(dt);
		receipt.setDpstCl("16");
		receipt.setPayTp("2");
		receipt.setAmbgTransSeqNo(null);
		receipt.setAssrTransSeqNo(null);
		receipt.setCrncyCd(bill.getCrncyCd());
		receipt.setExrate(bill.getExrate());
		receipt.setExrateAplyDt(bill.getExrateAplyDt());
		receipt.setCnclYn("N");
		receipt.setRegDate(new Timestamp(new Date().getTime()));
		receipt.setSoId(bill.getSoId());
		
		return receipt;
	}

}

package com.ntels.ccbs.batch.py.tasklet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.entity.AmbgOcc;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.PaymentResult;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;
import com.ntels.ccbs.batch.py.service.PaymentService;
import com.ntels.ccbs.batch.py.service.PrepayService;
import com.ntels.ccbs.batch.py.service.ReceiptService;

@Component("nBlpyc01m02JobWriter")
public class NBlpyc01m02JobWriter implements ItemWriter<PaymentResult>, StepExecutionListener {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private PrepayService prepayService;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		
	}

	@Override
	public void write(List<? extends PaymentResult> paymentResultList) throws Exception {
		List<Receipt> receiptList = new ArrayList<>();
		List<ReceiptDetail> receiptDetailList = new ArrayList<>();
		List<ReceiptDetail> fullPayReceiptDetailList = new ArrayList<>();
		List<AmbgOcc> ambgOccList = new ArrayList<>();
		List<PrepayOcc> prepayOccList = new ArrayList<>();
		List<Deposit> updateDepositList = new ArrayList<>();
		
		List<CBillComm> updateBillMastList = new ArrayList<>();
		List<CBillComm> updateFullPayBillList = new ArrayList<>();
		List<CBillComm> updateNotFullPayBillList = new ArrayList<>();
		
		for (PaymentResult paymentResult : paymentResultList) {

			if (paymentResult.getReceiptList() != null) {
				receiptList.addAll(paymentResult.getReceiptList());	
			}

			if (paymentResult.getReceiptDetailList() != null) {
				receiptDetailList.addAll(paymentResult.getReceiptDetailList());	
			}

			if (paymentResult.getAmbgOcc() != null) {
				ambgOccList.add(paymentResult.getAmbgOcc());
			}

			if (paymentResult.getPrepayOcc() != null) {
				prepayOccList.add(paymentResult.getPrepayOcc());
			}

			if (paymentResult.getDeposit() != null) {
				updateDepositList.add(paymentResult.getDeposit());
			}

			updateBillMastList.add(paymentResult.getUpdateBillMast());
			
			List<CBillComm> updateBillList = paymentResult.getUpdateBillList();
			
			for (CBillComm bill : updateBillList) {
				if ("Y".equals(paymentResult.getFullPayYn()) == true) {
					updateFullPayBillList.add(bill);
//					update = nBlpyb09m02Service.updateFullPayBill(bill);
					ReceiptDetail receiptDetail = new ReceiptDetail();
					receiptDetail.setPymSeqNo(paymentResult.getPymSeqNo());
					receiptDetail.setBillSeqNo(bill.getBillSeqNo());
					receiptDetail.setRegDate(new Timestamp(new Date().getTime()));
					
					fullPayReceiptDetailList.add(receiptDetail);
				} else {
					updateNotFullPayBillList.add(bill);
//					update = nBlpyb09m02Service.updateBillRcptAmt(bill);
				}
			}

//			// 불명금이 발생하였는가!!
//			boolean existAmbgOcc = false;
//			// 선수금이 발생하였는가!!
//			boolean existPrepayOcc = false;
//			
//			// 수납내역 등록
//			if (receiptList.isEmpty() == false) {
//				receiptService.insertReceipt(receiptList);
//			}
//			
//			if (receiptDetailList.isEmpty() == false) {
//				// 수납상세내역 등록
//				receiptService.insertReceiptDetail(receiptDetailList);
//			}
//			
//			if (ambgOccList.isEmpty() == false) {
//				// 불명금 발생내역 등록
//				ambgService.insertAmbgOcc(ambgOccList);
//				existAmbgOcc = true;
//			}
//			
//			if (prepayOccList.isEmpty() == false) {
//				// 선수금 발생내역 등록
//				prepayService.insertPrepayOcc(prepayOccList);
//				existPrepayOcc = true;
//			}
//			
//			// 청구내역 수정
//			Bill bill = new Bill();
//			ReceiptDetail refDtl = receiptDetailList.get(receiptDetailList.size() - 1);
//			
//			bill.setRcptAmt(refDtl.getRcptAmt());
//			bill.setBillSeqNo(refDtl.getBillSeqNo());
//			bill.setUseYymm(refDtl.getUseYymm());
//			bill.setProdCmpsId(refDtl.getProdCmpsId());
//			bill.setSvcCmpsId(refDtl.getSvcCmpsId());
//			bill.setChrgItmCd(refDtl.getChrgItmCd()); 
//			
//			
//			billService.updateBillBatchReceipt(bill);
//			
//			String ambgTgtYn = existAmbgOcc == true ? "Y" : "N";
//			String prepayTgtYn = existPrepayOcc == true ? "Y" : "N";
//			
//			depositService.updateDepositBatchReceipt(ambgTgtYn, CUtil.utilGetDateTime(2), prepayTgtYn, paymentResult.getDeposit().getDpstSeqNo());

		}
		
		receiptService.insertReceipt(receiptList);
		paymentService.insertRcptDtlSelectBill(fullPayReceiptDetailList);
		paymentService.updateBillMastRcptAmt(updateBillMastList);
		paymentService.updateFullPayBill(updateFullPayBillList);
		paymentService.updateDpstProc(updateDepositList);
		paymentService.updateBillRcptAmt(updateNotFullPayBillList);
		receiptService.insertReceiptDetail(receiptDetailList);
		prepayService.insertPrepayOcc(prepayOccList);
//		ambgService.insertAmbgOcc(ambgOccList);
		
		
		
	}

}

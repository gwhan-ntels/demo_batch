package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.BillTgtCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.iv.entity.NBlivb01m15;
import com.ntels.ccbs.batch.iv.service.NBlivb01m15Service;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.PrepayTransHistory;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;
import com.ntels.ccbs.batch.py.service.PaymentService;
import com.ntels.ccbs.batch.py.service.PrepayService;
import com.ntels.ccbs.batch.py.service.ReceiptService;

@Component("nBlivb01m15JobWriter")
public class NBlivb01m15JobWriter implements ItemWriter<NBlivb01m15>, StepExecutionListener {

	@Autowired
	private NBlivb01m15Service nBlivb01m15JdbcService;
	
	@Autowired
	private PrepayService prepayService;
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ClogService clogService;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		clogService.writeLog(getClass().getSimpleName() + ".afterStep");
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		clogService.writeLog(getClass().getSimpleName() + ".beforeStep");
	}

	@Override
	public void write(List<? extends NBlivb01m15> blivb01m15List) throws Exception {
		
		clogService.writeLog(getClass().getSimpleName() + " write list size : " + blivb01m15List.size());
		
		List<ReceiptDetail> receiptDetailList = new ArrayList<>();
		List<Receipt> receiptList = new ArrayList<>();
		List<CBillComm> billList = new ArrayList<>();
		List<CBillComm> fullPayBillList = new ArrayList<>();
		List<CBillComm> billMastList = new ArrayList<>();
		List<BillTgtCust> billTgtCustList = new ArrayList<>();
		List<OverpayBillAply> overpayBillAplyList = new ArrayList<>();
		List<PrepayOcc> prepayOccList = new ArrayList<>();
		List<PrepayTransHistory> prepayTransHistoryList = new ArrayList<>();
		
		for (NBlivb01m15 nBlivb01m15 : blivb01m15List) {
			if (nBlivb01m15.getReceiptDetailList() != null) {
				receiptDetailList.addAll(nBlivb01m15.getReceiptDetailList());
			}
			
			if (nBlivb01m15.getReceiptList() != null) {
				receiptList.addAll(nBlivb01m15.getReceiptList());
			}
			
			if (nBlivb01m15.getBillList() != null) {
				billList.addAll(nBlivb01m15.getBillList());
			}
			
			if (nBlivb01m15.getFullPayBillList() != null) {
				fullPayBillList.addAll(nBlivb01m15.getFullPayBillList());
			}
			
			if (nBlivb01m15.getBillMastList() != null) {
				billMastList.addAll(nBlivb01m15.getBillMastList());
			}
			
			if (nBlivb01m15.getBillTgtCustList() != null) {
				billTgtCustList.addAll(nBlivb01m15.getBillTgtCustList());
			}
			
			if (nBlivb01m15.getOverpayBillAplyList() != null) {
				overpayBillAplyList.addAll(nBlivb01m15.getOverpayBillAplyList());
			}
			
			if (nBlivb01m15.getPrepayOccList() != null) {
				prepayOccList.addAll(nBlivb01m15.getPrepayOccList());
			}
			
			if (nBlivb01m15.getPrepayTransHistoryList() != null) {
				prepayTransHistoryList.addAll(nBlivb01m15.getPrepayTransHistoryList());
			}
			
		}
		
		clogService.writeLog("receiptDetailList.size : " + receiptDetailList.size());
		clogService.writeLog("receiptList.size : " + receiptList.size());
		clogService.writeLog("billList.size : " + billList.size());
		clogService.writeLog("billTgtCustList.size : " + billTgtCustList.size());
		clogService.writeLog("overpayBillAplyList.size : " + overpayBillAplyList.size());
		clogService.writeLog("prepayOccList.size : " + prepayOccList.size());
		clogService.writeLog("prepayTransHistoryList.size : " + prepayTransHistoryList.size());
		
		receiptService.insertReceiptDetail(receiptDetailList);
		receiptService.insertReceipt(receiptList);
		paymentService.updateFullPayBill(fullPayBillList);
		nBlivb01m15JdbcService.updateBill(billList);
		nBlivb01m15JdbcService.updateBillMast(billMastList);
		nBlivb01m15JdbcService.updateSmlAmtYn(billTgtCustList);
		nBlivb01m15JdbcService.updateOverpayBillAply(overpayBillAplyList);
		nBlivb01m15JdbcService.updatePrepayOcc(prepayOccList);
		prepayService.insertPrepayTransHistory(prepayTransHistoryList);

	}

}

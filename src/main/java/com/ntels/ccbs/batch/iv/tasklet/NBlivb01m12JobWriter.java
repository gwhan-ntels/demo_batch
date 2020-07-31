package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrepayBillAply;
import com.ntels.ccbs.batch.iv.common.entity.PrepayCtrt;
import com.ntels.ccbs.batch.iv.entity.NBlivb01m12;
import com.ntels.ccbs.batch.iv.service.NBlivb01m12Service;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;
import com.ntels.ccbs.batch.py.service.ReceiptService;

@Component("nBlivb01m12JobWriter")
public class NBlivb01m12JobWriter implements ItemWriter<NBlivb01m12>, StepExecutionListener {

	@Autowired
	private NBlivb01m12Service nBlivb01m12Service;
	
	@Autowired
	private ReceiptService receiptService;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		
	}

	@Override
	public void write(List<? extends NBlivb01m12> blivb01m12List) throws Exception {
		
		List<PrepayBillAply> insertPrepayBillAplyList = new ArrayList<>();
		List<CBillComm> updateBillWrkList = new ArrayList<>();
		List<Receipt> receiptList = new ArrayList<>();
		List<ReceiptDetail> receiptDetailList = new ArrayList<>();
		List<PrepayBillAply> updatePrepayBillAplyList = new ArrayList<>();
		List<PrepayCtrt> updatePrepayCtrtList = new ArrayList<>();
		
		for (NBlivb01m12 nBlivb01m12 : blivb01m12List) {
			
			insertPrepayBillAplyList.add(nBlivb01m12.getPrepayBillAply());
			updateBillWrkList.addAll(nBlivb01m12.getUpdateBillList());
			receiptDetailList.addAll(nBlivb01m12.getReceiptDetailList());
			receiptList.addAll(nBlivb01m12.getReceiptList());
			updatePrepayBillAplyList.addAll(nBlivb01m12.getUpdatePrepayList());
			updatePrepayCtrtList.addAll(nBlivb01m12.getUpdatePrepayCtrtList());
			
		}
		
		nBlivb01m12Service.insertPrepayBillAply(insertPrepayBillAplyList);
		nBlivb01m12Service.updateBillWrkRcptAmt(updateBillWrkList);
		receiptService.insertReceiptDetail(receiptDetailList);
		receiptService.insertReceipt(receiptList);
		nBlivb01m12Service.updatePrepayBillAplyPayProc(updatePrepayBillAplyList);
		nBlivb01m12Service.updatePrepayCtrt(updatePrepayCtrtList);
		
	}

}

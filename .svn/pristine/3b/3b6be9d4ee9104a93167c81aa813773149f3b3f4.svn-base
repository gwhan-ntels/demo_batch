package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillInvoice;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.service.NBlivb01m18Service;

@Component("nBlivb01m18Tasklet01")
@Scope("step")
public class NBlivb01m18Tasklet01 extends LazyLoaderLogingTasklet<BillInvoice, BillInvoice> {

	@Autowired
	private NBlivb01m18Service nBlivb01m18Service;
	
	@Override
	protected boolean isInsertPgmLog() {
		return true;
	}

	@Override
	protected boolean isUpdatePgmLog() {
		return false;
	}

	@Override
	protected LazyLoader<BillInvoice> getLoader() throws Exception {
		
		CBillComm cBillComm = new CBillComm();
		cBillComm.setBillYymm(billYymm);
		cBillComm.setSoId(soId);
		cBillComm.setBillCycl(billCycl);
		
		// 작업 수행 전 남아있을지 모를 데이터를 삭제한다.
		nBlivb01m18Service.deletePrevData(cBillComm);

		return nBlivb01m18Service.getBillInvoiceInfo(cBillComm);
	}

	@Override
	protected BillInvoice process(BillInvoice item) {
		return item;
	}

	@Override
	protected void write(List<BillInvoice> itemList) {
		nBlivb01m18Service.insertBillInvoice(itemList);
	}

	@Override
	protected RepeatStatus end() {
		// TODO Auto-generated method stub
		return null;
	}

}

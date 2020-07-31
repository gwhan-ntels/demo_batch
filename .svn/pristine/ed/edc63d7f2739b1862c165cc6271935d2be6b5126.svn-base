package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrintCustInfo;
import com.ntels.ccbs.batch.iv.service.NBlivb01m17Service;

@Component("nBlivb01m17Tasklet01")
@Scope("step")
public class NBlivb01m17Tasklet01 extends LazyLoaderLogingTasklet<PrintCustInfo, PrintCustInfo> {

	@Autowired
	private NBlivb01m17Service nBlivb01m17Service;
	
	private Timestamp timestamp;
	
	@Override
	protected boolean isInsertPgmLog() {
		return true;
	}

	@Override
	protected boolean isUpdatePgmLog() {
		return false;
	}

	@Override
	protected LazyLoader<PrintCustInfo> getLoader() throws Exception {
		CBillComm bill = new CBillComm();
		bill.setBillYymm(billYymm);
		bill.setSoId(soId);
		bill.setBillCycl(billCycl);
		
		return nBlivb01m17Service.getPrintCustInfo(bill);
	}

	@Override
	protected PrintCustInfo process(PrintCustInfo item) {
		return item;
	}

	@Override
	protected void write(List<PrintCustInfo> itemList) {
		nBlivb01m17Service.insertPrintCustInfo(itemList);
	}

	@Override
	protected RepeatStatus end() {
		return null;
	}

}

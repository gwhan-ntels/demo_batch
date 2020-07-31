package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrintCt;
import com.ntels.ccbs.batch.iv.service.NBlivb01m17Service;

@Component("nBlivb01m17Tasklet02")
@Scope("step")
public class NBlivb01m17Tasklet02 extends LazyLoaderLogingTasklet<PrintCt, PrintCt> {

	@Autowired
	private NBlivb01m17Service nBlivb01m17Service;
	
	private Timestamp timestamp;
	
	@Override
	protected boolean isInsertPgmLog() {
		return false;
	}

	@Override
	protected boolean isUpdatePgmLog() {
		return true;
	}

	@Override
	protected LazyLoader<PrintCt> getLoader() throws Exception {
		
		timestamp = new Timestamp(new Date().getTime());
		
		CBillComm bill = new CBillComm();
		bill.setBillYymm(billYymm);
		bill.setSoId(soId);
		bill.setBillCycl(billCycl);
		bill.setBillDt(getBillDt());
		
		return nBlivb01m17Service.getPrintCt(bill);
	}

	@Override
	protected PrintCt process(PrintCt item) {
		item.setRegDate(timestamp);
		return item;
	}

	@Override
	protected void write(List<PrintCt> itemList) {
		nBlivb01m17Service.insertPrintCt(itemList);	
	}

	@Override
	protected RepeatStatus end() {
		// TODO Auto-generated method stub
		return null;
	}

}

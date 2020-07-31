package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrintCustInfo;
import com.ntels.ccbs.batch.iv.service.NBlivb01m17Service;

@Component("nBlivb01m17JobReader01")
@Scope("step")
public class NBlivb01m17JobReader01 extends CommonItemReader<PrintCustInfo> {

	@Autowired
	private NBlivb01m17Service nBlivb01m17Service;
	
	private Timestamp timestamp;
	
	@Override
	protected boolean isUpdatePgmLog() {
		return false;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		timestamp = new Timestamp(new Date().getTime());
		super.beforeStep(stepExecution);
	}

	@Override
	protected LazyLoader<PrintCustInfo> getLoader() {
		
		CBillComm bill = new CBillComm();
		bill.setBillYymm(billYymm);
		bill.setSoId(soId);
		bill.setBillCycl(billCycl);
		
		return nBlivb01m17Service.getPrintCustInfo(bill);
//		return null;
	}
	
	@Override
	protected void setItemDefaultValue(PrintCustInfo item) {
		item.setRegDate(timestamp);
		item.setRegrId("blivb01m17");
	}

}

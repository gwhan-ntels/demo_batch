package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrintCt;
import com.ntels.ccbs.batch.iv.common.service.CBLService;
import com.ntels.ccbs.batch.iv.service.NBlivb01m17Service;

@Component("nBlivb01m17JobReader02")
@Scope("step")
public class NBlivb01m17JobReader02 extends CommonItemReader<PrintCt> {

	@Autowired
	private CBLService cblService;
	
	@Autowired
	private NBlivb01m17Service nBlivb01m17Service;
	
	private Timestamp timestamp;
	
	private String billDt;
	
	@Override
	protected boolean isInsertPgmLog() {
		return false;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		timestamp = new Timestamp(new Date().getTime());
		
		BillCyclStp billCyclStp = new BillCyclStp();
		billCyclStp.setBillCycl(billCycl);
		billCyclStp.setSoId(soId);
		billCyclStp.setBillYymm(billYymm);
		
		billDt = cblService.getCyclBillDt(billCyclStp);
		
		super.beforeStep(stepExecution);
	}

	@Override
	protected LazyLoader<PrintCt> getLoader() {
		
		CBillComm bill = new CBillComm();
		bill.setBillYymm(billYymm);
		bill.setSoId(soId);
		bill.setBillCycl(billCycl);
		bill.setBillDt(billDt);

		clogService.writeLog("NBlivb01m17JobReader02.getLoader bill\n{}", clogService.objectToString(bill));
		
		return nBlivb01m17Service.getPrintCt(bill);
	}
	
	@Override
	protected void setItemDefaultValue(PrintCt item) {
		item.setRegDate(timestamp);
	}
	
}

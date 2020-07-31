package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrepayBillAply;
import com.ntels.ccbs.batch.iv.common.service.CBLService;
import com.ntels.ccbs.batch.iv.service.NBlivb01m12Service;

@Component("nBlivb01m12JobReader")
@Scope("step")
public class NBlivb01m12JobReader extends CommonItemReader<PrepayBillAply> {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private CBLService cblService;
	
	@Autowired
	private NBlivb01m12Service nBlivb01m12JdbcService;
	
	private String billDt;
	private String payDueDt;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		clogService.writeLog("NBlivb01m12JobReader.beforeStep");
		
		BillCyclStp billCyclStp = new BillCyclStp();
		billCyclStp.setBillCycl(billCycl);
		billCyclStp.setBillYymm(billYymm);
		billCyclStp.setSoId(soId);

		billDt = cblService.getCyclBillDt(billCyclStp);
		payDueDt = cblService.getCyclPayDueDt(billCyclStp);
		
		clogService.writeLog(String.format("billDt : %s, payDueDt : %s", billDt, payDueDt));
		
		super.beforeStep(stepExecution);
	}

	@Override
	protected LazyLoader<PrepayBillAply> getLoader() {
		CBillComm bill = new CBillComm();
		bill.setBillYymm(billYymm);
		bill.setBillCycl(billCycl);
		bill.setBillDt(billDt);
		bill.setSoId(soId);
		
		return nBlivb01m12JdbcService.getPrepayCtrtContents(bill);
	}
	
	@Override
	protected void setItemDefaultValue(PrepayBillAply item) {
		item.setPrepdAplyCl("1");
	}

	/* (non-Javadoc)
	 * @see com.ntels.ccbs.batch.common.tasklet.CommonItemReader#lastItem(java.lang.Object)
	 */
	@Override
	protected void lastItem(PrepayBillAply item) {
		// TODO Auto-generated method stub
		
	}

}

package com.ntels.ccbs.batch.iv.common.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.service.CBLService;

@Scope("step")
public abstract class BillCommonItemReader<T> extends CommonItemReader<T> implements StepExecutionListener {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private BillCyclStp billCyclStp;
	
	@Autowired
	protected CBLService cblService;
	
	@Value("#{jobParameters['soId']}")
	protected String soId;
	@Value("#{jobParameters['billYymm']}")
	protected String billYymm;
	@Value("#{jobParameters['billCycl']}")
	protected String billCycl;
	@Value("#{jobParameters['user']}")
	protected String user;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		
		logger.info("soId : {}", soId);
		logger.info("billYymm : {}", billYymm);
		logger.info("billCycl : {}", billCycl);
		logger.info("user : {}", user);
		
		billCyclStp = new BillCyclStp();
		
		billCyclStp.setBillCycl(billCycl);
		billCyclStp.setBillYymm(billYymm);
		billCyclStp.setRegrId(user);
		billCyclStp.setSoId(soId);
	}
	
	protected BillCyclStp getBillCyclStp() {
		return this.billCyclStp;
	}
	
	protected String getExchRateAplyDt() {
		return cblService.getExrateAplyDt();
	}
	
	protected String getBillDt() {
		return cblService.getCyclBillDt(billCyclStp);
	}
	
	protected String getPayDueDt() {
		return cblService.getCyclPayDueDt(billCyclStp);
	}
	
	protected String getUseYymm() {
		
		String useYymm;
		
		try {
			useYymm = CUtil.addMonths(billYymm, -1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return useYymm;
	}
	
}

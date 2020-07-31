package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;

@Component("nBlivb01m11JobProcessor02")
public class NBlivb01m11JobProcessor02 implements ItemProcessor<BillCust, BillCust>, StepExecutionListener {

	@Autowired
	private ClogService clogService;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		
	}

	@Override
	public BillCust process(BillCust billCust) throws Exception {
		clogService.writeLog("MakeBillTargetServiceCompositionJobProcessor.process Do nothing");
		return billCust;
	}

}

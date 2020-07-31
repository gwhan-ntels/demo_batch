package com.ntels.ccbs.batch.up.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

public abstract class NBlupb01m02CommonReaderWithAfterJob<T> extends NBlupb01m02CommonReader<T> {

	abstract void afterJob();
	
	@Override
	public ExitStatus afterStep(StepExecution execution) {
		afterJob();
		return super.afterStep(execution);
		
	}
	
}

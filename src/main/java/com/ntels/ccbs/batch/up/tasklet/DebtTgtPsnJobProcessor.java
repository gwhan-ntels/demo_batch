package com.ntels.ccbs.batch.up.tasklet;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.entity.DebtTgtPsn;

@Component
@Scope("step")
public class DebtTgtPsnJobProcessor implements ItemProcessor<DebtTgtPsn, DebtTgtPsn>, StepExecutionListener {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("#{jobParameters['regr_id']}") 
	private String regr_id;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DebtTgtPsn process(DebtTgtPsn debtTgtPsn) throws Exception {
		
		debtTgtPsn.setRegDate(new Timestamp(new Date().getTime()));
		debtTgtPsn.setRegrId(regr_id);
		debtTgtPsn.setChgDate(new Timestamp(new Date().getTime()));
		debtTgtPsn.setChgrId(regr_id);
		
		debtTgtPsn.setAcctProcYn("N");
		debtTgtPsn.setPayProcYn("N");
		
		return debtTgtPsn;
	}

}

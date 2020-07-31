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

import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;

@Component("nBlupb01m02JobProcessor05")
@Scope("step")
public class NBlupb01m02JobProcessor05 implements ItemProcessor<AuthChgAppl, AuthChgAppl>, StepExecutionListener {

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
	public AuthChgAppl process(AuthChgAppl authChgAppl) throws Exception {
		
		authChgAppl.setRegDate(new Timestamp(new Date().getTime()));
		authChgAppl.setRegrId(regr_id);
		authChgAppl.setChgrDate(new Timestamp(new Date().getTime()));
		authChgAppl.setChgId(regr_id);
		
		return authChgAppl;
	}

}

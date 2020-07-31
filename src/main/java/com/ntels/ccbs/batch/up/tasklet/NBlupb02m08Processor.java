package com.ntels.ccbs.batch.up.tasklet;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;

@Component(value="nBlupb02m08Processor")
@Scope("step")
public class NBlupb02m08Processor implements ItemProcessor<AuthChgAppl, AuthChgAppl>, StepExecutionListener {

	@Value("#{jobParameters['regrId']}")
	private String regrId;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		
	}

	@Override
	public AuthChgAppl process(AuthChgAppl authChgAppl) throws Exception {
		
		authChgAppl.setRegrId(regrId);
		authChgAppl.setChgId(regrId);
		
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		
		authChgAppl.setRegDate(timestamp);
		authChgAppl.setChgrDate(timestamp);
		
		String yyyymmddhh24miss = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		authChgAppl.setProcDttm(yyyymmddhh24miss);
		
		authChgAppl.setAuthChgCmplYn("N");
		authChgAppl.setProcPsnId("N/A");
		authChgAppl.setProcMemo("N/A");
		
		return null;
	}

}

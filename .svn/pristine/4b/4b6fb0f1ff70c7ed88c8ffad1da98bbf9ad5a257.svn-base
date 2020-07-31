package com.ntels.ccbs.batch.up.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;
import com.ntels.ccbs.batch.up.entity.NBlupb02m08;
import com.ntels.ccbs.batch.up.service.NBlupb02m08Service;

@Component(value="nBlupb02m08Reader")
@Scope("step")
public class NBlupb02m08Reader extends CommonItemReader<AuthChgAppl> implements StepExecutionListener {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private NBlupb02m08Service nBlupb02m08Service;
	
	@Value("#{jobParameters['chDate']}")
	private String chDate;
	
	@Value("#{jobParameters['logFileName']}")
	private String logFileName;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
	}

	@Override
	protected LazyLoader<AuthChgAppl> getLoader() {
		NBlupb02m08 nBlupb02m08 = new NBlupb02m08();
		nBlupb02m08.setChDate(chDate);
		
		return nBlupb02m08Service.getAuthChgApplList(nBlupb02m08);
	}

}

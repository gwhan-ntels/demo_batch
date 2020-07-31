package com.ntels.ccbs.batch.iv.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.service.NBlivb01m27Service;

@Component("nBlivb01m27JobTasklet01")
@Scope("step")
public class NBlivb01m27JobTasklet01 implements Tasklet, StepExecutionListener {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private NBlivb01m27Service nBlivb01m27Service;
	
	@Value("#{jobParameters['workYymm']}")
	private String billYymm;
	
	@Value("#{jobParameters['soId']}")
	private String soId;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		
	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		
		clogService.writeLog("가생성자료 삭제처리");
		nBlivb01m27Service.deletePrevData(billYymm, soId);
		
		return null;
	}

}

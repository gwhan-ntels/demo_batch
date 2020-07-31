package com.ntels.ccbs.batch.py.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.common.tasklet.CommonItemReader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.service.NBlpyc01m02Service;

@Component("nBlpyc01m02JobReader")
@Scope("step")
public class NBlpyc01m02JobReader extends CommonItemReader<Deposit> implements StepExecutionListener {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private NBlpyc01m02Service nBlpyc01m02Service;
	
	@Value("#{jobParameters['soId'}")
	private String soId;
	
	@Value("#{jobParameters['workYymm']}")
	private String workYymm;
	
	private StopWatch stopWatch;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		clogService.writeLog("NBlpyc01m02JobReader.afterStep");
		
		if (stepExecution.getFailureExceptions().isEmpty() == false) {
			clogService.writeLog("일괄수납처리에 실패하였습니다.");
		}

		stopWatch.stop();
		clogService.writeLog("총 수행시간(초)" + stopWatch.getTotalTimeSeconds());
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		clogService.writeLog("NBlpyc01m02JobReader.beforeStep");
		clogService.writeLog("일괄수납처리를 합니다.");
		stopWatch = new StopWatch();
		stopWatch.start();
	}
	
	@Override
	protected LazyLoader<Deposit> getLoader() {
		clogService.writeLog("입금내역을 조회합니다");
		CBillComm bill = new CBillComm();
		bill.setSoId(soId);
		bill.setBillYymm(workYymm);
		return nBlpyc01m02Service.getDepositList(bill);
	}

}

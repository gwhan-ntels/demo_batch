package com.ntels.ccbs.batch.up.tasklet;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.entity.UpymDetail;
import com.ntels.ccbs.batch.up.service.NBlupb01m02Service;

@Component("nBlupb01m02JobProcessor01")
@Scope("step")
public class NBlupb01m02JobProcessor01 implements ItemProcessor<UpymDetail, UpymDetail>, StepExecutionListener {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NBlupb01m02Service nBlupb01m02Service;
	
	@Value("#{jobParameters['regr_id']}") 
	private String regrId;
	
	@Value("#{jobParameters['in_date']}")
	private String in_date;

	@Override
	public void beforeStep(StepExecution arg0) {
		logger.debug("UpymDetailJobProcessor.beforeStep");	
	}
	
	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		logger.debug("UpymDetailJobProcessor.afterStep");
		return null;
	}
	
	@Override
	public UpymDetail process(UpymDetail upymDetail) throws Exception {
		
		logger.debug("UpymDetailJobProcessor.process " + upymDetail.getArrsMthCnt());
		// 등록날짜 현재로 설정
		upymDetail.setRegDate(new Timestamp(new Date().getTime()));
		upymDetail.setRegrId(regrId);
		 
		 // 연체 개월 수 구하기
		int months = nBlupb01m02Service.monthsBetween(upymDetail.getBillYymm(), in_date); 
		upymDetail.setArrsMthCnt(months);
		
		return upymDetail;
	}

}

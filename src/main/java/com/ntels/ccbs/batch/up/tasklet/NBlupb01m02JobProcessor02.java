package com.ntels.ccbs.batch.up.tasklet;

import java.sql.Timestamp;
import java.util.Date;

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

@Component("nBlupb01m02JobProcessor02")
@Scope("step")
public class NBlupb01m02JobProcessor02 implements ItemProcessor<UpymDetail, UpymDetail>, StepExecutionListener {

	@Autowired
	private NBlupb01m02Service nBlupb01m02Service;
	
	@Value("#{jobParameters['in_date']}")
	private String in_date;
	
	@Value("#{jobParameters['regr_id']}")
	private String regr_id;
	
	@Override
	public UpymDetail process(UpymDetail upym) throws Exception {
		
		// 등록날짜 YYYYMM형식으로 등록
		upym.setRegDate(new Timestamp(new Date().getTime()));
		// 등록자 ID설정
		upym.setRegrId(regr_id);
		
		int months = nBlupb01m02Service.monthsBetween(upym.getBillYymm(), upym.getUpymBsYymm());
		upym.setUpymPrgrMthCnt(months);
		
		return upym;
	}

	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		
	}

}

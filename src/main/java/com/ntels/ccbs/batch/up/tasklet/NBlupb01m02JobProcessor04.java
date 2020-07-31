package com.ntels.ccbs.batch.up.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.common.service.UpCommonService;
import com.ntels.ccbs.batch.up.entity.UpymEntrustMast;
import com.ntels.ccbs.batch.up.service.NBlupb01m02Service;

@Component("nBlupb01m02JobProcessor04")
@Scope("step")
public class NBlupb01m02JobProcessor04 implements ItemProcessor<UpymEntrustMast, UpymEntrustMast>, StepExecutionListener {

	@Autowired
	private NBlupb01m02Service nBlupb01m02Service;
	
	@Autowired
	private UpCommonService upCommonService;
	
	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UpymEntrustMast process(UpymEntrustMast upymEntrustMast) throws Exception {

		// term_mth_cnt 계산
		int maxDtMonthsBetween = nBlupb01m02Service.monthsBetween(upymEntrustMast.getUpymBsYymm(), upymEntrustMast.getTermDtMax());
		int minDtMonthsBetween = nBlupb01m02Service.monthsBetween(upymEntrustMast.getUpymBsYymm(), upymEntrustMast.getTermDtMin());
		
		if (maxDtMonthsBetween >= minDtMonthsBetween) {
			upymEntrustMast.setTermMthCnt(Integer.toString(maxDtMonthsBetween));
		} else {
			upymEntrustMast.setTermMthCnt(Integer.toString(minDtMonthsBetween));
		}
		
		// 시퀀스
		upymEntrustMast.setUpymNo(Integer.toString(upCommonService.upymNo()));
		
		return upymEntrustMast;
	}

}

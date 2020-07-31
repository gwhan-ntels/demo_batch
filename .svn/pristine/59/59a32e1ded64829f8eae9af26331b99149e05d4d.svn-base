package com.ntels.ccbs.batch.ch.standard.tasklet;

import java.io.IOException;
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

import com.ntels.ccbs.batch.ch.standard.entity.StandardCharge;
import com.ntels.ccbs.batch.common.CLog;
import com.ntels.ccbs.batch.common.CUtil;

@Component
@Scope("step") //<-- parameter 필요할 경우 선언
public class StandardChargeProcessor implements ItemProcessor<StandardCharge, StandardCharge>, StepExecutionListener{

	/** logger **/
	Logger log = LoggerFactory.getLogger(this.getClass());

	private CLog clslog = new CLog();

	private static int i =0;

	public void beforeStep(StepExecution stepExecution) {
		System.out.println("ItemProcess Before Step");
		clslog.OpenLog(logFileName);		
	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Value("#{jobParameters['logFileName']}") String logFileName;
	public StandardCharge process(StandardCharge standardCharge) throws Exception {
		// TODO Auto-generated method stub
		clslog.WriteLog("STANDARD CHRGE Bef PROCESS"+standardCharge.toString());

		standardCharge.setBillYymm("201604");
		i++;
		standardCharge.setRateItmCd(standardCharge.getRateItmCd()+Integer.toString(i));
//        System.out.println(standardCharge.toString());
		standardCharge.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));

        clslog.WriteLog("STANDARD CHRGE Aft PROCESS"+standardCharge.toString());

        return standardCharge;
	}

}

package com.ntels.ccbs.batch.up.tasklet;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.common.entity.UpymBigBsAmt;
import com.ntels.ccbs.batch.up.entity.NBlupb01m02;
import com.ntels.ccbs.batch.up.service.NBlupb01m02Service;

@Component("nBlupb01m02JobStartTasklet")
@Scope("step")
public class NBlupb01m02JobStartTasklet implements Tasklet {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private NBlupb01m02Service nBlupb01m02Service;
	
	@Value("#{jobParameters['logFileName']}") 
	private String logFileName;
	
	@Value("#{jobParameters['in_date']}")
	private String inDate;
	
	@Value("#{jobParameters['so_id']}")
	private String soId;
	
	@Value("#{jobParameters['regr_id']}")
	private String regrId;
	
	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		
		logger.debug("NBlupb01m02JobStartTasklet.execute");
		
		// 마감정보에서 청구마감정보 확인
		int checkCount = nBlupb01m02Service.checkClaimEnd(inDate, soId);
		
		// 청구마감정보가 없으면 미납내역을 생성할 수 없다.
		if (checkCount == 0) {
			String msg = "[NBlupb01m02]Billing process Not Closed.";
			logger.debug(msg);
//			clsLog.CloseLog();
//			throw new Exception(msg);
		}
		
		NBlupb01m02 nBlupb01m02 = new NBlupb01m02();
		nBlupb01m02.setInDate(inDate);
		nBlupb01m02.setSoId(soId);
		nBlupb01m02.setProcSeqNo(1);
		nBlupb01m02.setProcStat("001");
		nBlupb01m02.setRegrId(regrId);
		nBlupb01m02.setRegDate(new Timestamp(new Date().getTime()));
		nBlupb01m02.setChgrId(regrId);
		nBlupb01m02.setChgDate(new Timestamp(new Date().getTime()));
		
		UpymBigBsAmt upymBigBsAmt = nBlupb01m02Service.getUpymBigBsAtm(nBlupb01m02);
		
		nBlupb01m02.setUpymAmtFrom(upymBigBsAmt.getUpymAmtFrom());
		nBlupb01m02.setUpymAmtTo(upymBigBsAmt.getUpymAmtTo());
		
		nBlupb01m02Service.insertUpymJobMst(nBlupb01m02);
		
		return RepeatStatus.FINISHED;
	}
	

}

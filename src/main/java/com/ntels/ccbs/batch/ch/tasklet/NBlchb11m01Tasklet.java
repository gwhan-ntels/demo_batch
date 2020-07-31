package com.ntels.ccbs.batch.ch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.service.RateInfoService;
import com.ntels.ccbs.batch.ch.service.NBlchb11m01Service;
import com.ntels.ccbs.batch.common.CLog;
import com.ntels.ccbs.batch.common.entity.Common;


/**
 * <PRE>
 * 1. ClassName: NBlchb11m01Reader
 * 2. FileName : NBlchb11m01Reader.java
 * 3. Package  : com.ntels.ccbs.batch.ch.tasklet
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 7. 28. 오후 1:24:55
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 7. 28.	: 신규 개발.
 * </PRE>
 */
@Component
@Scope("step") //<-- parameter 필요할 경우 선언
public class NBlchb11m01Tasklet implements Tasklet, StepExecutionListener {

	/** logger. */
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/** HistoryService Autowired. */
	@Autowired
	private NBlchb11m01Service nBlchb11m01Service;

	@Autowired
	private RateInfoService rateInfoService;

	/** 시작여부를 판단하여 최초 데이터 로딩을 처리함. */
//	private boolean isStarted = false;

	/** 배치 처리 대상 리스트. */
	int times = 0;
	
	private Common comm = new Common();
	private CLog clslog = new CLog();
	private Multi  multi = new Multi();

	/**
	 *
	 * 데이터 로딩 메소드.
	 * @throws Exception 
	 *
	 * @warning	[Optional]함수의 제약사항이나 주의해야 할 점
	 * @see	[Optional]관련 정보(관련 함수, 관련 모듈)
	 */

	/*
	 * 전달 파라미터 필요할 경우 선언
	@Value("#{jobParameters['date']}")
	String date;
	*/ 
	@Value("#{jobParameters['logFileName']}") String logFileName;
	@Value("#{jobParameters['clcWrkNo']}") String clcWrkNo;
	@Value("#{jobParameters['soId']}") String soId;
	@Value("#{jobParameters['billYymm']}") String billYymm;
	@Value("#{jobParameters['billCycl']}") String billCycl;
	@Value("#{jobParameters['pSeq']}") String pSeq;
//	public Multi read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {		
//		clslog.WriteLog("TBLCH_CHRG 편성");
//		int result = nBlchb11m01Service.saveCharge(multi);
//
//		if(result ==0){
//			System.out.println("DATA NOT FOUND");
//		}else{
//			clslog.WriteLog("TBLCH_CHRG 편성 "+result+"건 완료");
//		}

		clslog.WriteLog("TBLCH_CHRG_HIST 편성");
		int result2 = nBlchb11m01Service.saveChargeHist1(multi); 

		if(result2 ==0){
			System.out.println("DATA NOT FOUND");
		}else{
			clslog.WriteLog("TBLCH_CHRG_HIST 편성 "+result2+"건 완료");
		}

		return RepeatStatus.FINISHED;
	}
	
	public void beforeStep(org.springframework.batch.core.StepExecution stepExecution) {
		comm.setLogFile(logFileName);
	    comm.setGrpId("00000");
		comm.setBsYymm("201601");
		comm.setBillYymm(billYymm);
		comm.setBatPgmId("TEST");
		comm.setSoId(soId);
		comm.setClcWrkNo(clcWrkNo);
		comm.setpSeq("1");
		comm.setPgmExeSeqNo("0000000010");
		comm.setBillCycl(billCycl);
		comm.setLogFilePath(comm.LOG_LOCAL);;
		
		log.debug("COMMON :={}",comm.toString());
		clslog.OpenLog(logFileName);

		comm.setReadCnt(stepExecution.getReadCount());
		comm.setWriteCnt(stepExecution.getWriteCount());
		comm.setBatProcStat("1");
		comm.setLogFileNm(logFileName);

//로그 테이블 생성은 임시로 막음
//		commonService.commonInsBatPgmLog(comm);
		
		try {
			System.out.println("ItemReader Before Step");
			System.out.println("Read Count = "+stepExecution.getReadCount() + " Read Skip =" + stepExecution.getReadSkipCount());
			System.out.println("Write ="+ stepExecution.getWriteCount() + " Write Skip ="+ stepExecution.getWriteSkipCount());
			System.out.println("getCommitCount ="+ stepExecution.getCommitCount() + " getRollbackCount ="+ stepExecution.getRollbackCount());
			System.out.println("getStepName ="+ stepExecution.getStepName() + " getSummary ="+ stepExecution.getSummary());
			System.out.println("getId ="+ stepExecution.getId() + " getJobExecutionId ="+ stepExecution.getJobExecutionId());
			System.out.println("getClass ="+ stepExecution.getClass() + " getExitStatus ="+ stepExecution.getExitStatus());
			System.out.println("getStartTime ="+ stepExecution.getStartTime() + " getEndTime ="+ stepExecution.getEndTime());
			System.out.println("getStatus ="+ stepExecution.getStatus() + " getEndTime ="+ stepExecution.getFailureExceptions());

			multi.setSoId(soId);
			multi.setBillYymm(billYymm);	
			multi.setClcWrkNo(clcWrkNo);
	        multi.setBillCycl(billCycl);
			multi.setMultiCycl(billCycl.substring(0, 1));
	        multi.setSeq(billCycl.substring(1, 2));
	        multi.setpSeq(pSeq);
	        multi.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));
	        multi = rateInfoService.listMulti(multi);

//	        Multi multiTmp = rateInfoService.listMultiCtrt(multi);
//	        multi.setStrtNo(multiTmp.getStrtNo());
//	        multi.setEndNo(multiTmp.getEndNo());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExitStatus afterStep(org.springframework.batch.core.StepExecution stepExecution) {
		comm.setReadCnt(stepExecution.getReadCount());			
//로그 업데이트 임시막음
//		int updBatPgm = commonService.commonUpdBatPgmLog(comm);
		System.out.println("ItemReader After Step");
		System.out.println("Read Count = "+comm.getReadCnt() + " Read Skip =" + stepExecution.getReadSkipCount());
		System.out.println("Write ="+ stepExecution.getWriteCount() + " Write Skip ="+ stepExecution.getWriteSkipCount());
		System.out.println("getCommitCount ="+ stepExecution.getCommitCount() + " getRollbackCount ="+ stepExecution.getRollbackCount());
		System.out.println("getStepName ="+ stepExecution.getStepName() + " getSummary ="+ stepExecution.getSummary());
		System.out.println("getId ="+ stepExecution.getId() + " getJobExecutionId ="+ stepExecution.getJobExecutionId());
		System.out.println("getClass ="+ stepExecution.getClass() + " getExitStatus ="+ stepExecution.getExitStatus());
		System.out.println("getStartTime ="+ stepExecution.getStartTime() + " getEndTime ="+ stepExecution.getEndTime());
		System.out.println("getStatus ="+ stepExecution.getStatus() + " getEndTime ="+ stepExecution.getFailureExceptions());

		try {
			comm.setReadCnt(stepExecution.getReadCount());
			comm.setWriteCnt(stepExecution.getWriteCount());
			System.out.println(stepExecution.getStatus());
			
			if( stepExecution.getStatus() .toString() == "COMPLETED"){
				comm.setBatProcStat("9");
				System.out.println("STATUS CHK 정상");
			} else{
				System.out.println("STATUS CHK 비정상");
				comm.setBatProcStat("5");
			}
			comm.setLogFileNm(logFileName);
//임시로 막음			
//			commonService.commonUpdBatPgmLog(comm);
			
			clslog.WriteLog("TBLCH_CHRG 편성 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		clslog.CloseLog();
		return null;
	}
	
}

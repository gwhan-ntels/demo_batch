package com.ntels.ccbs.batch.ch.standard.tasklet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.standard.entity.StandardCharge;
import com.ntels.ccbs.batch.ch.standard.service.StandardChargeService;
import com.ntels.ccbs.batch.common.CLog;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.sample.vo.SearchParam;

/**
 *
 * ItemReader 샘플.
 *
 * @see	 [Optional] 관련정보
 * <PRE>
 * 1. ClassName: SampleReader
 * 2. FileName : SampleReader.java
 * 3. Package  : com.ntels.ccbs.batch.sample.tasklet
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:22:46
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 * </PRE>
 */
@Component
@Scope("step") //<-- parameter 필요할 경우 선언
public class StandardChargeReader implements ItemReader<StandardCharge>, StepExecutionListener {

//	private static final BatchStatus COMPLETED = null;

	/** logger. */
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/** HistoryService Autowired. */
	@Autowired
	private StandardChargeService standardChargeService;

	@Autowired
	private CommonService commonService;	

	/** 시작여부를 판단하여 최초 데이터 로딩을 처리함. */
	private boolean isStarted = false;

	/** 배치 처리 대상 리스트. */
	List<StandardCharge> list;

	private Common comm = new Common();
	private CLog clslog = new CLog();

	int times = 0;
	
	/**
	 *
	 * 데이터 로딩 메소드.
	 * @throws Exception 
	 *
	 * @warning	[Optional]함수의 제약사항이나 주의해야 할 점
	 * @see	[Optional]관련 정보(관련 함수, 관련 모듈)
	 */
//    public StandardChargeReader(List<StandardCharge> list) {
//        this.list = list;
//    }

	private void dataSet() throws Exception{
		SearchParam c = new SearchParam();
		c.setStartNum(0);
		c.setEndNum(30);
		list = standardChargeService.listJdbcDirect(comm);
	}

	/*
	 * 전달 파라미터 필요할 경우 선언
	@Value("#{jobParameters['date']}")
	String date;
	*/ 
	@Value("#{jobParameters['logFileName']}") String logFileName;
	public StandardCharge read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if(isStarted == false){
			clslog.WriteLog("STANDARD CHRGE READER START");
			isStarted = true;
			dataSet();
		}

//		System.out.println("LIST = " + list);

		if (!list.isEmpty()) {
			return list.remove(0);
		}

		System.out.println(logFileName);
		return null;
	}
	
	public void beforeStep(org.springframework.batch.core.StepExecution stepExecution) {
		comm.setLogFile(logFileName);
	    comm.setGrpId("00000");
		comm.setBsYymm("201603");
		comm.setBillYymm("201603");
		comm.setBatPgmId("TEST");
		comm.setSoId("00");
		comm.setClcWrkNo("0000000001");
		comm.setpSeq("1");
		comm.setPgmExeSeqNo("0000000010");
		comm.setBillCycl("0");
		comm.setLogFilePath(comm.LOG_LOCAL);;
		
		log.debug("COMMON :={}",comm.toString());
		clslog.OpenLog(logFileName);

		comm.setReadCnt(stepExecution.getReadCount());
		comm.setWriteCnt(stepExecution.getWriteCount());
		comm.setBatProcStat("1");
		comm.setLogFileNm(logFileName);

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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExitStatus afterStep(org.springframework.batch.core.StepExecution stepExecution) {
		comm.setReadCnt(stepExecution.getReadCount());			
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
			
//			commonService.commonUpdBatPgmLog(comm);
			
			clslog.WriteLog("STANDARD CHRGE END");
		} catch (Exception e) {
			e.printStackTrace();
		}
		clslog.CloseLog();
		return null;
	}
	
}

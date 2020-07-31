package com.ntels.ccbs.batch.common.tasklet;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.sample.vo.SearchParam;

/**
 * Tasklet 샘플.
 *
 * @see	 [Optional] 관련정보
 * <PRE>
 * 1. ClassName: SampleTasklet
 * 2. FileName : SampleTasklet.java
 * 3. Package  : com.ntels.ccbs.batch.sample.tasklet
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:23:34
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 * </PRE>
 */
//@Component
public class SampleTasklet implements Tasklet {

	/** logger. */
	Logger log = LoggerFactory.getLogger(this.getClass());

	/** StandardChargeService Autowired. */
	@Autowired
	private CommonService commonService;
	
	/**
	 * config.properties 파일의 propertie 사용 예제
	 */
	@Value("${paging.perPage}")
	String perPage;	
	
	@Autowired
	MessageSource messageSource;

	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {

		//config.properties 파일 propertie 
		log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%% config.properties paging.perPage := {}",perPage);
		
		
		//파라미터 전달
		String parameterDate = (String) arg1.getStepContext().getJobParameters().get("date");
		String billYymm = (String) arg1.getStepContext().getJobParameters().get("billYymm");
		String billCycl = (String) arg1.getStepContext().getJobParameters().get("billCycl");
		String soId = (String) arg1.getStepContext().getJobParameters().get("soId");
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>> parameterDate := {}{}{}{}",parameterDate,billYymm,billCycl,soId);

		//errorMessages.properties 파일 (다국어)
		log.debug("************************** message 0000 korea := {}", messageSource.getMessage("0000", new String[]{"철수"}, Locale.KOREA));
		log.debug("************************** message 0000 english := {}", messageSource.getMessage("0000", new String[]{"sam"}, Locale.ENGLISH));

		Common c = new Common();
//		c.setBillYymm("201508") ;
//		c.setBillCycl("01");
//		c.setSoId("02");
//		@Param("readCnt") long readCnt,@Param("procCnt") long procCnt,@Param("errCnt") long errCnt,@Param("writeCnt") long writeCnt,
//		@Param("batProcStat") String batProcStat,@Param("grpId") String grpId,@Param("workYymm") String workYymm,@Param("pgmId") String pgmId,
//		@Param("soId") String soId,@Param("batchSeq") String batchSeq

//		@Param("grpId") String grpId,@Param("bsYyMm") String bsYyMm,@Param("batPgmId") String batPgmId,@Param("soId") String soId,
//		@Param("pgmExeSeqNo") String pgmExeSeqNo,@Param("billCycl") String billCycl,@Param("batProcStat") String batProcStat,@Param("logFilePath") String logFilePath,
//		@Param("logFileNm") String logFileNm	

		c.setGrpId("00000");
		c.setBsYymm("201603");
		c.setBatPgmId("TEST");
		c.setSoId("00");
		c.setPgmExeSeqNo("0000000001");
		c.setBillCycl("01");
		c.setBatProcStat("1");
		c.setLogFilePath("LOG_LOCAL");
		c.setLogFileNm("TEST");
		c.setReadCnt(5);
		c.setProcCnt(5);
		c.setErrCnt(0);
		c.setWriteCnt(5);
		c.setBatProcStat("9");
		log.debug("COMMON :={}",c.toString());
		
		//mybatis 사용 예제
		List<Common> list1 = commonService.commonListExRate();
				
		for(Common cc : list1){
			log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet list1 := {}", cc);
			
			//batch job의 특성상 아래의 interval time을 주어 서버 CPU usage 100% 를 대비함
			Thread.sleep(100);
		}

		List<Common> list2 = commonService.commonListBillStp(billYymm, billCycl, soId);
		for(Common cc : list2){
			log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet list2 := {}", cc);
			Thread.sleep(100);
		}
//		int insBatPgm = commonService.commonInsBatPgmLog(c);
//		if (insBatPgm == 0){
//			log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet insert Zero");
//		}
//		log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet insert data := {}", insBatPgm);
		
		List<Common> list3 = commonService.commonListBatPgm(c.getBatPgmId());
		for(Common cc : list3){
			log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet list3 := {}", cc);
			Thread.sleep(100);
		}
		int updBatPgm = commonService.commonUpdBatPgmLog(c);
		if(updBatPgm ==0){
			log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet update Zero");
		}
		log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet updBatPgmLog := {}", updBatPgm);

		return RepeatStatus.FINISHED;
	}

}

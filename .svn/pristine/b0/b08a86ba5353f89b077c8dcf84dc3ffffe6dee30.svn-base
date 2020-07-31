package com.ntels.ccbs.batch.ch.standard.tasklet;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.standard.entity.StandardCharge;
import com.ntels.ccbs.batch.ch.standard.service.StandardChargeService;
import com.ntels.ccbs.batch.common.*;
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
@Component
public class StandardChargeTasklet implements Tasklet {

	/** logger. */
	Logger log = LoggerFactory.getLogger(this.getClass());

	/** StandardChargeService Autowired. */
	@Autowired
	private StandardChargeService standardChargeService;

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
			StepExecution se = arg1.getStepContext().getStepExecution();
			
			//파라미터 전달
			String parameterDate = (String) arg1.getStepContext().getJobParameters().get("date");
			log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>> parameterDate := {}",parameterDate);
			
			//errorMessages.properties 파일 (다국어)
			log.debug("************************** message 0000 korea := {}", messageSource.getMessage("0000", new String[]{"철수"}, Locale.KOREA));
			log.debug("************************** message 0000 english := {}", messageSource.getMessage("0000", new String[]{"sam"}, Locale.ENGLISH));

			Common comm = new Common();
			comm.setGrpId("00000");
			comm.setBsYymm("201603");
			comm.setBatPgmId("TEST");
			comm.setSoId("00");
			comm.setPgmExeSeqNo("0000000003");
			comm.setBillCycl("01");
			comm.setBatProcStat("1");
			comm.setLogFilePath("LOG_LOCAL");
			comm.setLogFileNm("TEST");
			comm.setReadCnt(5);
			comm.setProcCnt(5);
			comm.setErrCnt(0);
			comm.setWriteCnt(5);
			comm.setBatProcStat("9");
			log.debug("COMMON :={}",comm.toString());
			
			CLog clslog = new CLog();
			clslog.MakeLog(comm, comm.LOG_LOCAL);
			clslog.OpenLog();
			clslog.WriteLog("Standard Charge Start");

			//조회조건 예제
			SearchParam c = new SearchParam();
			c.setStartNum(0);
			c.setEndNum(30);

			int insBatPgm =  commonService.commonInsBatPgmLog(comm);
			if (insBatPgm == 0){
			log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet insert Zero");
			}
			log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet insert data := {}", insBatPgm);

			//mybatis 사용 예제
//			List<StandardCharge> list1 = standardChargeService.list(c);
//			for(StandardCharge cc : list1){
//				log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet list1 := {}", cc);
				
				//batch job의 특성상 아래의 interval time을 주어
				//서버 CPU usage 100% 를 대비함
//				Thread.sleep(100);
//			}

			int updBatPgm = commonService.commonUpdBatPgmLog(comm);
			if(updBatPgm ==0){
				log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet update Zero");
			}
			log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet updBatPgmLog := {}", updBatPgm);

			//jdbcTemplate 사용 예제
			/*
			List<StandardCharge> list2 = StandardChargeService.listJdbcTemplate(c);
			for(StandardCharge cc : list2){
				log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@ SampleTasklet list2 := {}", cc);
				
				//batch job의 특성상 아래의 interval time을 주어
				//서버 CPU usage 100% 를 대비함
				Thread.sleep(100);
			}
			*/

			//jdbcDirect (pure java) 사용 예제
			/*
			List<StandardCharge> list3 = StandardChargeService.listJdbcDirect(c);
			for(StandardCharge cc : list3){
				log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%% SampleTasklet list3 := {}", cc);
				
				//batch job의 특성상 아래의 interval time을 주어
				//서버 CPU usage 100% 를 대비함
				Thread.sleep(100);
			}
			*/
			
			// STEP (job) Failed 처리 실행해 보기 위하여 아래 if 절 주석 해제 필요
			/*		
			if(list != null){
				throw new Exception("강제 Batch 종료");
			}*/

			clslog.CloseLog();
			return RepeatStatus.FINISHED;
	}	
}

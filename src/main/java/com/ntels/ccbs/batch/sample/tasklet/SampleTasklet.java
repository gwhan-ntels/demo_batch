package com.ntels.ccbs.batch.sample.tasklet;

import java.util.List;
import java.util.Locale;

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

import com.ntels.ccbs.batch.entity.CommonCode;
import com.ntels.ccbs.batch.sample.service.CommonCodeService;
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
public class SampleTasklet implements Tasklet {

	/** logger. */
	Logger log = LoggerFactory.getLogger(this.getClass());

	/** commonCodeService Autowired. */
	@Autowired
	private CommonCodeService commonCodeService;
	
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
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>> parameterDate := {}",parameterDate);
		
		//errorMessages.properties 파일 (다국어)
		log.debug("************************** message 0000 korea := {}", messageSource.getMessage("0000", new String[]{"철수"}, Locale.KOREA));
		log.debug("************************** message 0000 english := {}", messageSource.getMessage("0000", new String[]{"sam"}, Locale.ENGLISH));

		//조회조건 예제
		SearchParam c = new SearchParam();
		c.setStartNum(0);
		c.setEndNum(30);
		
		//mybatis 사용 예제
		List<CommonCode> list1 = commonCodeService.list(c);
		for(CommonCode cc : list1){
			log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<< SampleTasklet list1 := {}", cc);
			
			//batch job의 특성상 아래의 interval time을 주어
			//서버 CPU usage 100% 를 대비함
			Thread.sleep(100);
		}
		
		//jdbcTemplate 사용 예제
		/*
		List<CommonCode> list2 = commonCodeService.listJdbcTemplate(c);
		for(CommonCode cc : list2){
			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@ SampleTasklet list2 := {}", cc);
			
			//batch job의 특성상 아래의 interval time을 주어
			//서버 CPU usage 100% 를 대비함
			Thread.sleep(100);
		}
		*/

		//jdbcDirect (pure java) 사용 예제
		/*
		List<CommonCode> list3 = commonCodeService.listJdbcDirect(c);
		for(CommonCode cc : list3){
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

		return RepeatStatus.FINISHED;
	}

}

package com.ntels.ccbs.batch.sample.tasklet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.entity.CommonCode;

/**
 *
 * ItemWriter 샘플.
 *
 * @see	 [Optional] 관련정보
 * <PRE>
 * 1. ClassName: SampleWriter
 * 2. FileName : SampleWriter.java
 * 3. Package  : com.ntels.ccbs.batch.sample.tasklet
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:24:00
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 * </PRE>
 */
@Component
public class SampleWriter implements ItemWriter<CommonCode> {

	/** logger. */
	Logger log = LoggerFactory.getLogger(this.getClass());

	String stepName;

	/**
	 * job step이 시작 되기 전 실행
	 * 
	 * @param stepExecution void
	 */
	@BeforeStep
	public void getInterStepData(StepExecution stepExecution) {
		stepName = stepExecution.getStepName();
	}

	public void write(List<? extends CommonCode> list) throws Exception {

		//동시 실행에 대한 확인을 위한 코드
		for(int i = 0; i < 10; i++){
			log.debug("################### SampleWriter {} := {}", stepName, i);

			Thread.sleep(100);
		}

		log.debug("################### SampleWriter := title={}", list.get(0).getCodeName());
	}
	
	//void write(List<? extends T> items) throws Exception;
}

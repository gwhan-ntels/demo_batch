package com.ntels.ccbs.batch.sample.launcher;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.NDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * job launcher 샘플.
 * 
 * 로그 파일 분리 예제
 *
 * @see	 [Optional] 관련정보
 * <PRE>
 * 1. ClassName: SampleJobLauncher
 * 2. FileName : SampleJobLauncher.java
 * 3. Package  : com.ntels.ccbs.batch.sample.launcher
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:17:44
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 * </PRE>
 */
public class SampleJob4Launcher {

	/** logger. */
	private static final Logger log = LoggerFactory.getLogger(SampleJob4Launcher.class);

	/**
	 *
	 * entry method.
	 *
	 * @warning	[Optional]함수의 제약사항이나 주의해야 할 점
	 * @param args String[]
	 * @see	[Optional]관련 정보(관련 함수, 관련 모듈)
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-sample-1-reader.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job)context.getBean("batch-sample-1-reader");

		String dateParam = new Date().toString();
		JobParameters jobParameters = new JobParametersBuilder().addString("date", dateParam).toJobParameters();

    	String expectedPattern = "yyyyMMddHHmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(expectedPattern);
		String dateseq 	= sdf.format(new Date());
		
		//로그 화일 분리 시작
		NDC.push(dateseq + "_batch-sample-1-reader" );
		
		try {
			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
			log.debug("Exit Status : {}", jobExecution.getStatus());
		} catch (Exception e) {
			log.error("\n", e);
		}
		log.info("Done");

		//로그 화일 분리 종료
		NDC.clear();		
		
		//정상종료
		System.exit(1);
	}
}

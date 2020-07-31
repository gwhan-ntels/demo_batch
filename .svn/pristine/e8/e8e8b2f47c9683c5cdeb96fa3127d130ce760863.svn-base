package com.ntels.ccbs.batch.sample.launcher;

import java.util.Date;

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
 * multi task 예제
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
public class SampleJob3Launcher {

	/** logger. */
	private static final Logger log = LoggerFactory.getLogger(SampleJob3Launcher.class);

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
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-sample-3-multi.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job)context.getBean("batch-sample-3-multi");

		String dateParam = new Date().toString();
		JobParameters jobParameters = new JobParametersBuilder().addString("date", dateParam).toJobParameters();

		try {
			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
			log.debug("Exit Status : {}", jobExecution.getStatus());
		} catch (Exception e) {
			log.error("\n", e);
		}
		log.info("Done");

		//정상종료
		System.exit(1);
	}
}

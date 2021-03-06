package com.ntels.ccbs.batch.common.launcher;

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

import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.CUtil;

/**
 *
 * job launcher 샘플.
 * 
 * tasket 예제
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
public class CommonJobLauncher {

	/** logger. */
	private static final Logger log = LoggerFactory.getLogger(CommonJobLauncher.class);

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
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-common-tasklet.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job)context.getBean("batch-common-tasklet");

		Common c = new Common();
		CUtil cUtil = new CUtil();
		c.setBillYymm("201508") ;
		c.setBillCycl("01");
		c.setSoId("02");

		System.out.println("Day of Month : "+ cUtil.getLastDay("201604"));
		System.out.println("Compare MIN DT :" + cUtil.getCompDate("20150201", "20160201", ""));
		System.out.println("Compare MAX DT :" + cUtil.getCompDate("20150201", "20160201", "MAX"));
		System.out.println("DIFF DT :" + cUtil.getUseDay("20160331", "20160302", 1));
		
		String dateParam = new Date().toString();
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("date", dateParam)//.toJobParameters();
				.addString("billYymm", c.getBillYymm())
				.addString("billCycl", c.getBillCycl())
				.addString("soId", c.getSoId())
				.toJobParameters();
		
		System.out.println("JOBPARAMETER"+jobParameters);
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

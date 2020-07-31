package com.ntels.ccbs.batch.ch.standard.launcher;

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

import com.ntels.ccbs.batch.common.CLog;
import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.entity.Common;

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
public class StandardChargeJobLauncher {

	/** logger. */
	private static final Logger log = LoggerFactory.getLogger(StandardChargeJobLauncher.class);

	private static CLog clog = new CLog();
	private static Common comm = new Common();
	
	/**
	 *
	 * entry method.
	 *
	 * @warning	[Optional]함수의 제약사항이나 주의해야 할 점
	 * @param args String[]
	 * @throws Exception 
	 * @see	[Optional]관련 정보(관련 함수, 관련 모듈)
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-standardCharge-reader.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job)context.getBean("batch-standardCharge-reader");
		
//		String logFileName = "c:\\temp\\TEST_00_00000_0000000001_"+CUtil.utilGetDateTime(1)+".log";

		comm.setGrpId("00000");
		comm.setBsYymm("201603");
		comm.setBatPgmId("TEST");
		comm.setSoId("00");
		comm.setClcWrkNo("00000000001");
		comm.setpSeq("1");
		comm.setPgmExeSeqNo("0000000005");
		log.debug("COMMON :={}",comm.toString());
		String logFileName = clog.MakeLog(comm, comm.LOG_LOCAL);
		System.out.println("Standard Luncher log "+logFileName);
		
//		clog.OpenLog(logFileName);
		String dateParam = new Date().toString();
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("date", dateParam)
				.addString("logFileName", logFileName)
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

package com.ntels.ccbs.batch.ch.launcher;

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
import com.ntels.ccbs.batch.common.entity.Common;

/**
 * <PRE>
 * 1. ClassName: NBlchb01m01JobLauncher
 * 2. FileName : NBlchb01m01JobLauncher.java
 * 3. Package  : com.ntels.ccbs.batch.ch.launcher
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:11:21
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */
public class NBlchb01m01JobLauncher_test {

	/** logger. */
	private static final Logger log = LoggerFactory.getLogger(NBlchb01m01JobLauncher_test.class);

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
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-nBlchb01m01-reader-test.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job)context.getBean("batch-nBlchb01m01-reader-test");
		
//		String logFileName = "c:\\temp\\TEST_00_00000_0000000001_"+CUtil.utilGetDateTime(1)+".log";

		comm.setGrpId("00000");
		comm.setBsYymm("201606");
		comm.setBatPgmId("TEST");
		comm.setSoId("00");
		comm.setClcWrkNo("0000000001");
		comm.setpSeq("2");
		comm.setBillCycl("02");		
		comm.setPgmExeSeqNo("0000000005");
		log.debug("COMMON :={}",comm.toString());
		String logFileName = clog.MakeLog(comm, comm.LOG_LOCAL);
		String soId = comm.getSoId();
		String clcWrkNo = comm.getClcWrkNo();
		String billYymm ="201607";
		String billCycl = comm.getBillCycl();
		String pSeq = "1";
		
		System.out.println("Standard Luncher log "+logFileName);
		
//		clog.OpenLog(logFileName);
		String dateParam = new Date().toString();
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("date", dateParam)
				.addString("logFileName", logFileName)
				.addString("soId", soId)
				.addString("clcWrkNo", clcWrkNo)
				.addString("billYymm", billYymm)
				.addString("billCycl", billCycl)
				.addString("pSeq", pSeq)
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

package com.ntels.ccbs.batch.iv.launcher;

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
import com.ntels.ccbs.batch.common.service.ClogService;

/**
 *
 * job launcher 샘플.
 * 
 * tasket 예제
 *
 * @see	 [Optional] 관련정보
 * <PRE>
 * 1. ClassName: NBliv02m01JobLauncher
 * 2. FileName : NBliv02m01JobLauncher.java
 * 3. Package  : com.ntels.ccbs.batch.sample.launcher
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:17:44
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 * </PRE>
 */
public class NBlivb01m00JobLauncher {

	/** logger. */
	private static final Logger log = LoggerFactory.getLogger(NBlivb01m00JobLauncher.class);

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
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-nBlivb01m00-reader.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job)context.getBean("batch-nBlivb01m00-reader");
		
  	    String clcWrkNo    = "1000000002";
		String soId        = "00";
		String billYymm    = "201607";
		String billCycl    = "01";
		String pgmId       = "NBLIV01M00";
		String pgmSeq      = "0";
		String grpId       = "001" ;
		String pgmExeSeqNo = "0000000001";
		String logFileName = null;
		
		
		
		 comm.setGrpId(grpId);
		 comm.setBsYymm(billYymm);
		 comm.setBillYymm(billYymm);
		 comm.setBatPgmId(pgmId);
		 comm.setSoId(soId);
		 comm.setClcWrkNo(clcWrkNo);
		 comm.setpSeq(pgmSeq);
		 comm.setPgmExeSeqNo(pgmExeSeqNo);
		 comm.setBillCycl(billCycl);
		 comm.setLogFilePath(comm.LOG_LOCAL);;
		 logFileName = clog.MakeLog(comm, comm.LOG_LOCAL);		 
		 comm.setLogFileNm(logFileName);
		
		 
         ClogService  clslog = context.getBean(ClogService.class);
		 clslog.setLogFileName(logFileName);
		 
		 
	  	 JobParameters jobParameters = new JobParametersBuilder()
				.addString("clcWrkNo"   , clcWrkNo)
				.addString("soId"       , soId)
				.addString("billYymm"   , billYymm)
				.addString("billCycl"   , billCycl)
				.addString("pgmId"      , pgmId)
				.addString("pgmSeq"     , pgmSeq)
				.addString("grpId"      , grpId)
				.addString("logFileName", logFileName)
				.addString("pgmExeSeqNo", pgmExeSeqNo)
				.toJobParameters();

		
		
		System.out.println("================================================================");
		System.out.println("JOBPARAMETER"+jobParameters);
		System.out.println("================================================================");
		
		try {
			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
			System.out.println("================================================================");
			System.out.println("Exit Status"+jobExecution.getStatus());
			System.out.println("================================================================");
			log.debug("Exit Status : {}", jobExecution.getStatus());
		} catch (Exception e) {
			log.error("\n", e);
		}
		log.info("Done");

		clslog.close();
		
		//정상종료
		System.exit(1);
	}
}

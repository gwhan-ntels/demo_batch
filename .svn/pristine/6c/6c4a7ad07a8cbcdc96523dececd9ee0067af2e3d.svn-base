package com.ntels.ccbs.batch.iv.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.py.launcher.NBlpyb10m02JobLauncher;

public class NBlivd51m00JobLauncher {

	private static final Logger log = LoggerFactory.getLogger(NBlpyb10m02JobLauncher.class);
	
	public static final int IX_CLASS_NAME = 1;
	public static final int IX_EXEC_DATE = 2;
	public static final int IX_PGM_ID = 3;
	public static final int IX_RECORD_DATA = 4;
	public static final int IX_GRP_ID = 5;
	public static final int IX_PGM_EXE_SEQ_NO = 6;
	public static final int IX_WORK_YYMM = 7;
	public static final int IX_BILL_CYCL = 8;
	public static final int IX_USER = 9;
	public static final int IX_SO_ID = 10;
	public static final int IX_ADJ_NO = 11;
	public static final int IX_PYM_YN = 12;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-nBlivd51m00-tasklet.xml");
		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
		Job job = context.getBean(Job.class);
		
		String pgmId = args[IX_PGM_ID].equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : args[IX_PGM_ID];
		String grpId = args[IX_GRP_ID].equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : args[IX_GRP_ID];
		String pgmExeSeqNo = args[IX_PGM_EXE_SEQ_NO].equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : args[IX_PGM_EXE_SEQ_NO];
		String workYymm = args[IX_WORK_YYMM].equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : args[IX_WORK_YYMM];
		String billCycl = args[IX_BILL_CYCL].equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : args[IX_BILL_CYCL];
		String user = args[IX_USER].equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : args[IX_USER];
		String soId = args[IX_SO_ID].equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : args[IX_SO_ID];
		String adjNo = args[IX_ADJ_NO].equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : args[IX_ADJ_NO];
		String pymYn = args[IX_PYM_YN].equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : args[IX_PYM_YN];
		
		StringBuffer sbuffer = new StringBuffer();

		sbuffer.append(pgmId);
		sbuffer.append("_");
		sbuffer.append(soId);
		sbuffer.append("_");
		sbuffer.append(grpId);
		sbuffer.append("_");
		sbuffer.append(CUtil.utilGetDateTime(1));
		sbuffer.append(".log");
		
		String logFileName = sbuffer.toString();
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pgmId", pgmId)
				.addString("logFileName", logFileName)
				.addString("grpId", grpId)
				.addString("pgmExeSeqNo", pgmExeSeqNo)
				.addString("workYymm", workYymm)
				.addString("billCycl", billCycl)
				.addString("user", user)
				.addString("soId", soId)
				.addString("adjNo", adjNo)
				.addString("pymYn", pymYn)
				.toJobParameters();

		ClogService clogService = context.getBean(ClogService.class);
		clogService.setLogFileName(logFileName);
		clogService.setProcessName("[NBlpyb10m02]");
		
		clogService.writeLog("JOBPARAMETER " + jobParameters);
		try {
			clogService.writeLog("[Run]Delinquent Account Generation");
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

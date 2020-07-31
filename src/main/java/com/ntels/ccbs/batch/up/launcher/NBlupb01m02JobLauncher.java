package com.ntels.ccbs.batch.up.launcher;

import java.text.SimpleDateFormat;
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

public class NBlupb01m02JobLauncher {

	/** logger. */
	private static final Logger log = LoggerFactory.getLogger(NBlupb01m02JobLauncher.class);

	private static CLog clog = new CLog();
	private static Common comm = new Common();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-nBlupb01m02-reader.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job)context.getBean("batch-nBlupb01m02-reader");

		log.debug("Start NBlupb01m02");

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

		clog.OpenLog(logFileName);
		String dateParam = new Date().toString();

		String in_date = new SimpleDateFormat("YYYYMM").format(new Date());;

		JobParameters jobParameters = new JobParametersBuilder()
				.addString("date", dateParam)
				.addString("logFileName", logFileName)
				.addString("so_id", "001")
				.addString("regr_id", "Tester")
				.addString("in_date", in_date)
				.addString("pSeq", "2")
				.toJobParameters();

		log.debug("JOBPARAMETER"+jobParameters);

		try {
			clog.WriteLog("[Run]Delinquent Account Generation");
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

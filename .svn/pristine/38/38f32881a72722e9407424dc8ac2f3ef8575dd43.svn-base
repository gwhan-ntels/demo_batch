package com.ntels.ccbs.batch.py.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.py.entity.PyJobParameter;

/**
 * 건별입금
 * @author Cashyalla
 *
 */
public class NBlpyb09m02JobLauncher {
	
	/** logger. */
	private static final Logger log = LoggerFactory.getLogger(NBlpyb09m02JobLauncher.class);
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-nBlpyb09m02-tasklet.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job)context.getBean("batch-nBlpyb09m02-tasklet");

		PyJobParameter pyJobParameter = new PyJobParameter(args);
		
		ClogService clogService = context.getBean(ClogService.class);
		clogService.setLogFileName("DpstTest");
		clogService.setProcessName("[NBlpyb09m02]");
		
		clogService.writeLog("==================================");
		clogService.writeLog("Add single deposit START");
		clogService.writeLog("==================================");
		try {
			clogService.writeLog("New single payment");
			JobExecution jobExecution = jobLauncher.run(job, pyJobParameter.getJobParameters());
			log.debug("Exit Status : {}", jobExecution.getStatus());
		} catch (Exception e) {
			log.error("\n", e);
		}
		log.info("Done");
		clogService.close();

		//정상종료
		System.exit(1);
	}
	
}

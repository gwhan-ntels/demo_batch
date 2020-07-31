package com.ntels.ccbs.batch.common.launcher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ntels.ccbs.batch.common.entity.JobParameter;
import com.ntels.ccbs.batch.common.service.ClogService;

public class CommonJobExecuter {

	private ApplicationContext context;
	private JobLauncher jobLauncher;
	private Job job;
	
	private ClogService clogService;
	
	public CommonJobExecuter(String configLocation) {
		context = new ClassPathXmlApplicationContext(configLocation);
		jobLauncher = context.getBean(JobLauncher.class);
		job = context.getBean(Job.class);
		clogService = context.getBean(ClogService.class);
	}
	
	public void executeJob(String[] args) {
		JobParameter ivJobParameter = new JobParameter(args);
		executeJob(ivJobParameter);
	}
	
	public void executeJob(JobParameter parameter) {
		clogService.setLogFileName(parameter.getLogFileName());

		JobParameters jobParameters = parameter.getJobParameters();

		try {
			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
			clogService.writeLog("================================================================");
			clogService.writeLog("Exit Status" + jobExecution.getStatus());
			clogService.writeLog("================================================================");
			clogService.writeLog("Exit Status : " + jobExecution.getStatus());
			clogService.writeLog("Done");
		} catch (Exception e) {
			clogService.error(e);
		} finally {
			clogService.close();
		}
	}

}

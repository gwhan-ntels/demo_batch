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

public class SampleFileLauncher {

	/** logger. */
	private static final Logger log = LoggerFactory.getLogger(SampleFileLauncher.class);

	
	/**
	 * entry method.
	 * 
	 * @param args void
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-sampleFile-reader.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job)context.getBean("batch-sampleFile-reader");

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

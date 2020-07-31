package com.ntels.ccbs.batch.sample.launcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SampleFtpLauncher {

	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("spring/ftp-sample.xml");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
}

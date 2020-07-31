package com.ntels.ccbs.batch.iv.launcher;

import com.ntels.ccbs.batch.common.launcher.CommonJobExecuter;

public class NBlivs02m02JobLauncher {

	public static void main(String[] args) {
		CommonJobExecuter jobLauncher = new CommonJobExecuter("spring/batch-nBlivs02m02-reader.xml");
		jobLauncher.executeJob(args);
	}
	
}

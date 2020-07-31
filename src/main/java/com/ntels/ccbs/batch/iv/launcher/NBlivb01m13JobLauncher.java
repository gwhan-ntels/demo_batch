package com.ntels.ccbs.batch.iv.launcher;

import com.ntels.ccbs.batch.common.launcher.CommonJobExecuter;

public class NBlivb01m13JobLauncher {

	public static void main(String[] args) {
		CommonJobExecuter jobLauncher = new CommonJobExecuter("/spring/batch-nBlivb01m13-reader.xml");
		jobLauncher.executeJob(args);
	}
	
}

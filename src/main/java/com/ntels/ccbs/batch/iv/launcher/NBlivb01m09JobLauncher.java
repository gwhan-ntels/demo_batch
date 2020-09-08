package com.ntels.ccbs.batch.iv.launcher;

import com.ntels.ccbs.batch.common.launcher.CommonJobExecuter;

public class NBlivb01m09JobLauncher {

	public static void main(String[] args) {

		CommonJobExecuter jobLauncher = new CommonJobExecuter("spring/batch-nBlivb01m09-tasklet.xml");
		jobLauncher.executeJob(args);

	}

}

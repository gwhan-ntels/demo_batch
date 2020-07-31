package com.ntels.ccbs.batch.ch.launcher;

import com.ntels.ccbs.batch.common.launcher.CommonJobExecuter;

/**
 * <PRE>
 * 1. ClassName: NBlchb10m01JobLauncher
 * 2. FileName : NBlchb10m01JobLauncher.java
 * 3. Package  : com.ntels.ccbs.batch.ch.launcher
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 7. 28. 오전 11:23:29
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 7. 28.	: 신규 개발.
 * </PRE>
 */
public class NBlchb10m01JobLauncher {
	public static void main(String[] args) throws Exception {
		CommonJobExecuter jobLauncher = new CommonJobExecuter("spring/batch-nBlchb10m01-reader.xml");
		jobLauncher.executeJob(args);
	}
}

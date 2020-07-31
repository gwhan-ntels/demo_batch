package com.ntels.ccbs.batch.ch.launcher;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.entity.JobParameter;
import com.ntels.ccbs.batch.common.launcher.CommonJobExecuter;

/**
 * <PRE>
 * 1. ClassName: NBlchb01m02JobLauncher
 * 2. FileName : NBlchb01m02JobLauncher.java
 * 3. Package  : com.ntels.ccbs.batch.ch.launcher
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 2:52:44
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */

public class NBlchb05m01JobLauncher {
	public static void main(String[] args) throws Exception {
		CommonJobExecuter jobLauncher = new CommonJobExecuter("spring/batch-nBlchb05m01-reader.xml");

		JobParameter jobParameter = new JobParameter(args);
		jobParameter.setBillYymm(CUtil.addMonths(args[JobParameter.IX_BILL_YYMM], -1));

		jobLauncher.executeJob(jobParameter);
	}
}

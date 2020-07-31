package com.ntels.ccbs.batch.up.tasklet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.entity.UpymDetail;

/**
 * BLUPB01M02 Step 1
 * @author 
 *
 */
@Component("nBlupb01m02JobReader01")
@Scope("step")
public class NBlupb01m02JobReader01 extends NBlupb01m02CommonReader<UpymDetail> {

	@Value("${upym.dtl.procStat}")
	private String procStat;
	
	@Value("${upym.dtl.jobName}")
	private String jobName;
	
	@Override
	protected LazyLoader<UpymDetail> getLoader() {
		return getService().getUpymDetailList(getNBlupb01m02());
	}
	
	@Override
	String getJobName() {
		return jobName;
	}
	
	@Override
	int getProcSeqNo() {
		return 1;
	}
	@Override
	String getProcStat() {
		return procStat;
	}
	
	@Override
	boolean isHaveToSaveJobDtl() {
		return true;
	}
	
	@Override
	String getJobErrorMessage() {
		return "An error occurred during Registration for job detail(insertUpymDtl).";
	}
	
	@Override
	String getJobStartMessage() {
		return "2. Registration for job detail.(insertUpymDtl)";
	}

}

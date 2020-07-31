package com.ntels.ccbs.batch.up.tasklet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.entity.UpymDetail;

@Component("nBlupb01m02JobReader02")
@Scope("step")
public class NBlupb01m02JobReader02 extends NBlupb01m02CommonReader<UpymDetail> {
	
	@Value("${upym.procStat}")
	private String procStat;
	
	@Value("${upym.jobName}")
	private String jobName;
	
	@Override
	protected LazyLoader<UpymDetail> getLoader() {
		return getService().getUpymList(getNBlupb01m02());
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
		return "An error occurred during Registration for deliquent history of payers.(insertUpym)";
	}
	
	@Override
	String getJobStartMessage() {
		return "3. Registration for deliquent history of payers.(insertUpym)";
	}

}

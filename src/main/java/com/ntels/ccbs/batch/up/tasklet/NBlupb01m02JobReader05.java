package com.ntels.ccbs.batch.up.tasklet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;

@Component("nBlupb01m02JobReader05")
@Scope("step")
public class NBlupb01m02JobReader05 extends NBlupb01m02CommonReader<AuthChgAppl> {
	
	@Value("${upym.auth.chg.appl.procStat}")
	private String procStat;
	
	@Value("${upym.auth.chg.appl.jobName}")
	private String jobName;
	
	@Override
	protected LazyLoader<AuthChgAppl> getLoader() {
		return getService().getAuthChgApplList(getNBlupb01m02());
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
		return "An error occurred during Registration for Force Change Request Details(Suspension, Force Terminate).(insertAuthChgAppl)";
	}
	
	@Override
	String getJobStartMessage() {
		return "6.  Registration for Force Change Request Details(Suspension, Force Terminate)(insertAuthChgAppl).";
	}

}

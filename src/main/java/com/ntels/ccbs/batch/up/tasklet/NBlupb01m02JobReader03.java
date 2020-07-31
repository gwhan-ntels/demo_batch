package com.ntels.ccbs.batch.up.tasklet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.entity.CtrtDetail;

@Component("nBlupb01m02JobReader03")
@Scope("step")
public class NBlupb01m02JobReader03 extends NBlupb01m02CommonReader<CtrtDetail> {
	
	@Value("${upym.ctrt.procStat}")
	private String procStat;
	
	@Value("${upym.ctrt.jobName}")
	private String jobName;
	
	@Override
	protected LazyLoader<CtrtDetail> getLoader() {
		return getService().getUpymCtrtDetail(getNBlupb01m02());
	}
	
	@Override
	protected void setItemDefaultValue(CtrtDetail item) {
		item.setRegrId(regr_id);
		item.setRegDate(now);
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
		return "An error occurred during Registration for deliquent history of Contractor.(insertUpymCtrt)";
	}
	
	@Override
	String getJobStartMessage() {
		return "4. Registration for deliquent history of Contractor.(insertUpymCtrt)";
	}

}

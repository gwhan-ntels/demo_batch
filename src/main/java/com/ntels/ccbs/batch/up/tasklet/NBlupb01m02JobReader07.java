package com.ntels.ccbs.batch.up.tasklet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.entity.UpymCtrtMngTp;

@Component("nBlupb01m02JobReader07")
@Scope("step")
public class NBlupb01m02JobReader07 extends NBlupb01m02CommonReader<UpymCtrtMngTp> {

	@Value("${upym.ctrt.mng.tp.jobName}")
	private String jobName;
	
	private String upymMngTp;
	
	@Override
	protected LazyLoader<UpymCtrtMngTp> getLoader() {
		upymMngTp = getService().getUpymMngTp(getNBlupb01m02());
		getNBlupb01m02().setUpymMngTp(upymMngTp);
		return getService().getUpymCtrtMngTpList(getNBlupb01m02());
	}
	
	@Override
	protected void setItemDefaultValue(UpymCtrtMngTp item) {
		item.setUpymMngTp(upymMngTp);
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
		return null;
	}
	
	@Override
	boolean isHaveToSaveJobDtl() {
		return false;
	}
	
	@Override
	String getJobErrorMessage() {
		return "An error occurred during insertUpymCtrtMngTp FUNCTION processed";
	}
	
	@Override
	String getJobStartMessage() {
		return "8.  insertUpymCtrtMngTp FUNCTION START).";
	}

}

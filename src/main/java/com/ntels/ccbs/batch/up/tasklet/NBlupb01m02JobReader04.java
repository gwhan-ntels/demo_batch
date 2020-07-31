package com.ntels.ccbs.batch.up.tasklet;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.entity.UpymEntrustMast;

@Component("nBlupb01m02JobReader04")
@Scope("step")
public class NBlupb01m02JobReader04 extends NBlupb01m02CommonReaderWithAfterJob<UpymEntrustMast> {
	
	@Value("${upym.entrust.mast.procStat}")
	private String procStat;
	
	@Value("${upym.entrust.mast.jobName}")
	private String jobName;
	
	@Override
	protected LazyLoader<UpymEntrustMast> getLoader() {
		return getService().getUpymEntrustMastList(getNBlupb01m02());
	}
	
	@Override
	void afterJob() {
		// 제외대상자 처리
		getService().updateEntrustMast("10", new Timestamp(new Date().getTime()), getNBlupb01m02().getSoId(), getNBlupb01m02().getInDate());
		getService().updateEntrustMast("11", new Timestamp(new Date().getTime()), getNBlupb01m02().getSoId(), getNBlupb01m02().getInDate());
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
		return "An error occurred during Registration for deliquent detail of Receipt center.(insertUpymEntrustMast)";
	}
	
	@Override
	String getJobStartMessage() {
		return "5. Registration for deliquent detail of Receipt center.(insertUpymEntrustMast)";
	}

}

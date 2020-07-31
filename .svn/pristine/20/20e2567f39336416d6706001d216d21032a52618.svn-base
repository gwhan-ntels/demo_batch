package com.ntels.ccbs.batch.up.tasklet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.entity.DebtTgtPsn;

@Component
@Scope("step")
public class DebtTgtPsnJobReader extends NBlupb01m02CommonReader<DebtTgtPsn> {
	
	@Value("${upym.debt.tgt.psn.procStat}")
	private String procStat;
	
	@Value("${upym.debt.tgt.psn.jobName}")
	private String jobName;
	
	@Override
	protected LazyLoader<DebtTgtPsn> getLoader() {
		return getService().getDebtTgtPsnList(getNBlupb01m02());
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
		return "대손제각처리신청 내역 등록중 오류가 발생하였습니다. 확인 바랍니다.";
	}
	
	@Override
	String getJobStartMessage() {
		return "7. 대손제각처리신청 내역 등록합니다.";
	}

}

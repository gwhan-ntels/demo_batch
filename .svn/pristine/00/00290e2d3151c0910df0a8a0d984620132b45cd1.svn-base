package com.ntels.ccbs.batch.common.entity;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import com.ntels.ccbs.batch.common.CUtil;

public class JobParameter {

	public static final int IX_PACKAGE_NAME = 0;
	public static final int IX_CLASS_NAME = 1;
	public static final int IX_EXEC_DATE = 2;
	public static final int IX_CLC_WRK_NO = 3;
	public static final int IX_SO_ID = 4;
	public static final int IX_BILL_YYMM = 5;
	public static final int IX_BILL_CYCL = 6;
	public static final int IX_PGM_ID = 7;
	public static final int IX_PGM_SEQ = 8;
	public static final int IX_GRP_ID = 9;
	public static final int IX_PGM_EXE_SEQ_NO = 10;
	public static final int IX_USER = 11;

	private String clcWrkNo;

	private String soId;

	private String billYymm;

	private String billCycl;

	private String pgmId;

	private String pgmSeq;

	private String grpId;

	private String pgmExeSeqNo;

	private String user;
	
	/**
	 * <pre>
	 * TBLIV_BAT_PGM_LOG 의 BILL_YYMM값으로 설정 됨.
	 * billYymm과 같은 값을 가지게 되지만 billYymm은 각 로직에서 수정될 수 있음.
	 * 변경된 값이 로그 테이블에 들어갈 경우 문제가 발생할 수 있음.
	 * </pre>
	 */
	private String workYymm;

	public JobParameter(String[] args) {
		setClcWrkNo(args[IX_CLC_WRK_NO]);
		setSoId(args[IX_SO_ID]);
		setBillYymm(args[IX_BILL_YYMM]);
		setBillCycl(args[IX_BILL_CYCL]);
		setPgmId(args[IX_PGM_ID]);
		setPgmSeq(args[IX_PGM_SEQ]);
		setGrpId(args[IX_GRP_ID]);
		setPgmExeSeqNo(args[IX_PGM_EXE_SEQ_NO]);
		setUser(args[IX_USER]);
		setWorkYymm(getBillYymm());
	}
	
	public JobParameters getJobParameters() {
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("clcWrkNo", getClcWrkNo())
				.addString("soId", getSoId())
				.addString("billYymm", getBillYymm())
				.addString("billCycl", getBillCycl())
				.addString("pgmId", getPgmId())
				.addString("pgmSeq", getPgmSeq())
				.addString("grpId", getGrpId())
				.addString("pgmExeSeqNo", getPgmExeSeqNo())
				.addString("user", getUser())
				.addString("logFileName", getLogFileName())
				.addString("workYymm", getWorkYymm())
				.toJobParameters();
		
		return jobParameters;
	}
	
	public String getLogFileName() {
//		Common common = new Common();
//		common.setGrpId(getGrpId());
//		common.setSoId(getSoId());
//		common.setBillYymm(getBillYymm());
//		common.setBsYymm(getBillYymm());
//		common.setBatPgmId(getPgmId());
//		common.setClcWrkNo(getClcWrkNo());
//		common.setpSeq(getPgmSeq());
//		common.setPgmExeSeqNo(getPgmExeSeqNo());
//		common.setBillCycl(getBillCycl());
//		common.setLogFilePath(common.LOG_LOCAL);

		StringBuffer sbuffer = new StringBuffer();

		sbuffer.append(getPgmId());
		sbuffer.append("_");
		sbuffer.append(getSoId());
		sbuffer.append("_");
		sbuffer.append(getGrpId());
		sbuffer.append("_");
		sbuffer.append(getClcWrkNo());
		sbuffer.append("_");
		sbuffer.append(getPgmSeq());
		sbuffer.append("_");
		sbuffer.append(CUtil.utilGetDateTime(1));
		sbuffer.append(".log");
		return sbuffer.toString();
	}

	public String getClcWrkNo() {
		return clcWrkNo == null ? "" : clcWrkNo.trim();
	}

	public String getSoId() {
		return soId == null ? "" : soId.trim();
	}

	public String getBillYymm() {
		return billYymm == null ? "" : billYymm.trim();
	}

	public String getBillCycl() {
		return billCycl == null ? "" : billCycl.trim();
	}

	public String getPgmId() {
		return pgmId == null ? "" : pgmId.trim();
	}

	public String getPgmSeq() {
		return pgmSeq == null ? "" : pgmSeq.trim();
	}

	public String getGrpId() {
		return grpId == null ? "" : grpId.trim();
	}

	public String getPgmExeSeqNo() {
		return pgmExeSeqNo == null ? "" : pgmExeSeqNo.trim();
	}

	public String getUser() {
		return user == null ? "" : user.trim();
	}
	
	public String getWorkYymm() {
		return workYymm;
	}

	public void setClcWrkNo(String clcWrkNo) {
		this.clcWrkNo = clcWrkNo.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : clcWrkNo;
	}

	public void setSoId(String soId) {
		this.soId = soId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : soId;
	}

	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : billYymm;
	}

	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : billCycl;
	}

	public void setPgmId(String pgmId) {
		this.pgmId = pgmId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : pgmId;
	}

	public void setPgmSeq(String pgmSeq) {
		this.pgmSeq = pgmSeq.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : pgmSeq;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : grpId;
	}

	public void setPgmExeSeqNo(String pgmExeSeqNo) {
		this.pgmExeSeqNo = pgmExeSeqNo.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : pgmExeSeqNo;
	}

	public void setUser(String user) {
		this.user = user.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : user;
	}

	public void setWorkYymm(String workYymm) {
		this.workYymm = workYymm.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : workYymm;
	}
	
}

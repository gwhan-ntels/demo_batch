package com.ntels.ccbs.batch.py.entity;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.entity.Common;

public class PyJobParameter {

	public static final int IX_CLASS_NAME = 1;
	public static final int IX_EXEC_DATE = 2;
	public static final int IX_PGM_ID = 3;
	public static final int IX_RECORD_DATA = 4;
	public static final int IX_GRP_ID = 5;
	public static final int IX_PGM_EXE_SEQ_NO = 6;
	public static final int IX_WORK_YYMM = 7;
	public static final int IX_BILL_CYCL = 8;
	public static final int IX_USER = 9;
	public static final int IX_SO_ID = 10;
	public static final int IX_EACH_DPST_SEQ = 11;
	public static final int IX_DPST_CL = 12;
	public static final int IX_CASH_DPST_CL = 13;
	public static final int IX_DPST_DT = 14;
	public static final int IX_DPST_AMT = 15;
	public static final int IX_DPST_FEE_TP = 16;
	public static final int IX_PYM_ACNT_ID = 18;
	public static final int IX_SMRY = 19;
	public static final int IX_DPST_BNK_ACNT_CD = 20;
	public static final int IX_TRANS_DT = 21;
	public static final int IX_ACNT_CARD_NO = 22;
	public static final int IX_RCPT_EMP_ID = 23;
	public static final int IX_RCPT_BILL_EMP_ID = 24;
	public static final int IX_IF_CHK = 25;
	public static final int IX_ORG_ID = 26;

	private String pakcageName;
	private String className;
	private String execDate;
	private String pgmId;
	private String recordData;
	private String grpId;
	private String pgmExeSeqNo;
	private String workYymm;
	private String billCycl;
	private String user;
	private String soId;
	private String eachDpstSeq;
	private String dpstCl;
	private String cashDpstCl;
	private String dpstDt;
	private Double dpstAmt;
	private String dpstFeeTp;
	private String pymAcntId;
	private String smry;
	private String dpstBnkAcntId;
	private String transDt;
	private String acntCardNo;
	private String rcptEmpId;
	private String rcptBillEmpId;
	private String ifChk;
	private String orgId;
	private String billInfoArray;

	public PyJobParameter(String[] args) {
		setClassName(args[IX_CLASS_NAME]);
		setExecDate(args[IX_EXEC_DATE]);
		setPgmId(args[IX_PGM_ID]);
		setRecordData(args[IX_RECORD_DATA]);
		setGrpId(args[IX_GRP_ID]);
		setPgmExeSeqNo(args[IX_PGM_EXE_SEQ_NO]);
		setWorkYymm(args[IX_WORK_YYMM]);
		setBillCycl(args[IX_BILL_CYCL]);
		setUser(args[IX_USER]);
		setSoId(args[IX_SO_ID]);
		setEachDpstSeq(args[IX_EACH_DPST_SEQ]);
		setDpstCl(args[IX_DPST_CL]);
		setCashDpstCl(args[IX_CASH_DPST_CL]);
		setDpstDt(args[IX_DPST_DT]);
		setDpstAmt(args[IX_DPST_AMT]);
		setDpstFeeTp(args[IX_DPST_FEE_TP]);
		setPymAcntId(args[IX_PYM_ACNT_ID]);
		setSmry(args[IX_SMRY]);
		setDpstBnkAcntId(args[IX_DPST_BNK_ACNT_CD]);
		setTransDt(args[IX_TRANS_DT]);
		setAcntCardNo(args[IX_ACNT_CARD_NO]);
		setRcptEmpId(args[IX_RCPT_EMP_ID]);
		setRcptBillEmpId(args[IX_RCPT_BILL_EMP_ID]);
		setIfChk(args[IX_IF_CHK]);
		setOrgId(args[IX_ORG_ID]);

		int billInfoPairCnt = 2;
		int billInfoPariIndex = 0;
		
		StringBuffer billInfoBuffer = new StringBuffer();
		
		for (int i = IX_ORG_ID + 1; i < args.length; i++) {
			
			billInfoBuffer.append(args[i]);
			
			billInfoPariIndex++;

			if (billInfoPariIndex == billInfoPairCnt) {
				billInfoPairCnt = 0;
				billInfoBuffer.append("|");
			} else {
				billInfoBuffer.append("@");
			}
		}
		
		setBillInfoArray(billInfoBuffer.toString());
	}
	
	public JobParameters getJobParameters() {
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("pakcageName", getPakcageName())
				.addString("className", getClassName())
				.addString("execDate", getExecDate())
				.addString("pgmId", getPgmId())
				.addString("recordData", getRecordData())
				.addString("grpId", getGrpId())
				.addString("pgmExeSeqNo", getPgmExeSeqNo())
				.addString("workYymm", getWorkYymm())
				.addString("billCycl", getBillCycl())
				.addString("user", getUser())
				.addString("soId", getSoId())
				.addString("eachDpstSeq", getEachDpstSeq())
				.addString("dpstCl", getDpstCl())
				.addString("cashDpstCl", getCashDpstCl())
				.addString("dpstDt", getDpstDt())
				.addDouble("dpstAmt", getDpstAmt())
				.addString("dpstFeeTp", getDpstFeeTp())
				.addString("pymAcntId", getPymAcntId())
				.addString("smry", getSmry())
				.addString("dpstBnkAcntId", getDpstBnkAcntId())
				.addString("transDt", getTransDt())
				.addString("acntCardNo", getAcntCardNo())
				.addString("rcptEmpId", getRcptEmpId())
				.addString("rcptBillEmpId", getRcptBillEmpId())
				.addString("ifChk", getIfChk())
				.addString("orgId", getOrgId())
				.addString("billInfoArray", getBillInfoArray())
				.addString("logFileName", getLogFileName())
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
		sbuffer.append(CUtil.utilGetDateTime(1));
		sbuffer.append(".log");
		return sbuffer.toString();
	}

	public String getPakcageName() {
		return pakcageName;
	}

	public String getClassName() {
		return className;
	}

	public String getExecDate() {
		return execDate;
	}

	public String getPgmId() {
		return pgmId;
	}

	public String getRecordData() {
		return recordData;
	}

	public String getGrpId() {
		return grpId;
	}

	public String getPgmExeSeqNo() {
		return pgmExeSeqNo;
	}

	public String getWorkYymm() {
		return workYymm;
	}

	public String getBillCycl() {
		return billCycl;
	}

	public String getUser() {
		return user;
	}

	public String getSoId() {
		return soId;
	}

	public String getEachDpstSeq() {
		return eachDpstSeq;
	}

	public String getDpstCl() {
		return dpstCl;
	}

	public String getCashDpstCl() {
		return cashDpstCl;
	}

	public String getDpstDt() {
		return dpstDt;
	}

	public Double getDpstAmt() {
		return dpstAmt;
	}

	public String getDpstFeeTp() {
		return dpstFeeTp;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public String getSmry() {
		return smry;
	}

	public String getDpstBnkAcntId() {
		return dpstBnkAcntId;
	}

	public String getTransDt() {
		return transDt;
	}

	public String getAcntCardNo() {
		return acntCardNo;
	}

	public String getRcptEmpId() {
		return rcptEmpId;
	}

	public String getRcptBillEmpId() {
		return rcptBillEmpId;
	}

	public String getIfChk() {
		return ifChk;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setPakcageName(String pakcageName) {
		this.pakcageName = pakcageName == null ? null : (pakcageName.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : pakcageName);
	}

	public void setClassName(String className) {
		this.className = className == null ? null : (className.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : className);
	}

	public void setExecDate(String execDate) {
		this.execDate = execDate == null ? null : (execDate.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : execDate);
	}

	public void setPgmId(String pgmId) {
		this.pgmId = pgmId == null ? null : (pgmId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : pgmId);
	}

	public void setRecordData(String recordData) {
		this.recordData = recordData == null ? null : (recordData.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : recordData);
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId == null ? null : (grpId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : grpId);
	}

	public void setPgmExeSeqNo(String pgmExeSeqNo) {
		this.pgmExeSeqNo = pgmExeSeqNo == null ? null : (pgmExeSeqNo.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : pgmExeSeqNo);
	}

	public void setWorkYymm(String workYymm) {
		this.workYymm = workYymm == null ? null : (workYymm.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : workYymm);
	}

	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl == null ? null : (billCycl.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : billCycl);
	}

	public void setUser(String user) {
		this.user = user == null ? null : (user.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : user);
	}

	public void setSoId(String soId) {
		this.soId = soId == null ? null : (soId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : soId);
	}

	public void setEachDpstSeq(String eachDpstSeq) {
		this.eachDpstSeq = eachDpstSeq == null ? null : (eachDpstSeq.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : eachDpstSeq);
	}

	public void setDpstCl(String dpstCl) {
		this.dpstCl = dpstCl == null ? null : (dpstCl.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : dpstCl);
	}

	public void setCashDpstCl(String cashDpstCl) {
		this.cashDpstCl = cashDpstCl == null ? null : (cashDpstCl.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : cashDpstCl);
	}

	public void setDpstDt(String dpstDt) {
		this.dpstDt = dpstDt == null ? null : (dpstDt.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : dpstDt);
	}

	public void setDpstAmt(String dpstAmt) {
		this.dpstAmt = dpstAmt == null ? null : (dpstAmt.equals(Common.NULL_PARAMETER_REPLACEMENT) ? null : Double.parseDouble(dpstAmt));
	}

	public void setDpstFeeTp(String dpstFeeTp) {
		this.dpstFeeTp = dpstFeeTp == null ? null : (dpstFeeTp.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : dpstFeeTp);
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId == null ? null : (pymAcntId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : pymAcntId);
	}

	public void setSmry(String smry) {
		this.smry = smry == null ? null : (smry.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : smry);
	}

	public void setDpstBnkAcntId(String dpstBnkAcntId) {
		this.dpstBnkAcntId = dpstBnkAcntId == null ? null : (dpstBnkAcntId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : dpstBnkAcntId);
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt == null ? null : (transDt.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : transDt);
	}

	public void setAcntCardNo(String acntCardNo) {
		this.acntCardNo = acntCardNo == null ? null : (acntCardNo.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : acntCardNo);
	}

	public void setRcptEmpId(String rcptEmpId) {
		this.rcptEmpId = rcptEmpId == null ? null : (rcptEmpId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : rcptEmpId);
	}

	public void setRcptBillEmpId(String rcptBillEmpId) {
		this.rcptBillEmpId = rcptBillEmpId == null ? null : (rcptBillEmpId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : rcptBillEmpId);
	}

	public void setIfChk(String ifChk) {
		this.ifChk = ifChk == null ? null : (ifChk.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : ifChk);
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId == null ? null : (orgId.equals(Common.NULL_PARAMETER_REPLACEMENT) == true ? null : orgId);
	}

	public String getBillInfoArray() {
		return billInfoArray;
	}

	public void setBillInfoArray(String billInfoArray) {
		this.billInfoArray = billInfoArray;
	}

}

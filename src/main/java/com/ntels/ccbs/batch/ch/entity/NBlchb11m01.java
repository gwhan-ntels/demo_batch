package com.ntels.ccbs.batch.ch.entity;

import java.sql.Timestamp;

public class NBlchb11m01 {
	
	private String soId;
	private String billYymm;
	private String ctrtId;
	private String hotBillYn;
	private String multiCycl;
	private String multiSeq;
	private String useStDate;
	private String useEdDate;
	private String processStat;
	private String billProcessDt;
	private Timestamp regDate;
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getHotBillYn() {
		return hotBillYn;
	}
	public void setHotBillYn(String hotBillYn) {
		this.hotBillYn = hotBillYn;
	}
	public String getMultiCycl() {
		return multiCycl;
	}
	public void setMultiCycl(String multiCycl) {
		this.multiCycl = multiCycl;
	}
	public String getMultiSeq() {
		return multiSeq;
	}
	public void setMultiSeq(String multiSeq) {
		this.multiSeq = multiSeq;
	}
	public String getUseStDate() {
		return useStDate;
	}
	public void setUseStDate(String useStDate) {
		this.useStDate = useStDate;
	}
	public String getUseEdDate() {
		return useEdDate;
	}
	public void setUseEdDate(String useEdDate) {
		this.useEdDate = useEdDate;
	}
	public String getProcessStat() {
		return processStat;
	}
	public void setProcessStat(String processStat) {
		this.processStat = processStat;
	}
	public String getBillProcessDt() {
		return billProcessDt;
	}
	public void setBillProcessDt(String billProcessDt) {
		this.billProcessDt = billProcessDt;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "NBlchb11m01 [soId=" + soId + ", billYymm=" + billYymm + ", ctrtId=" + ctrtId
				+ ", hotBillYn=" + hotBillYn + ", multiCycl=" + multiCycl + ", multiSeq=" + multiSeq
				+ ", useStDate=" + useStDate + ", useEdDate=" + useEdDate + ", processStat=" + processStat
				+ ", billProcessDt=" + billProcessDt + ", regDate=" + regDate + "]";
	}
}

package com.ntels.ccbs.batch.up.entity;

import java.sql.Timestamp;

public class DebtTgtPsn {

	private String upymBsYymm;
	private String ctrtId;
	private String billYymm;
	private String pymAcntId;
	private String custId;
	private Integer upymPrgrMthCnt;
	private Integer upymMthCnt;
	private Double billAmt;
	private Double rcptAmt;
	private Double upymAmt;
	private String acctProcYn;
	private String payProcYn;
	private String regrId;
	private Timestamp regDate;
	private String chgrId;
	private Timestamp chgDate;
	public String getUpymBsYymm() {
		return upymBsYymm;
	}
	public void setUpymBsYymm(String upymBsYymm) {
		this.upymBsYymm = upymBsYymm;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public Integer getUpymPrgrMthCnt() {
		return upymPrgrMthCnt;
	}
	public void setUpymPrgrMthCnt(Integer upymPrgrMthCnt) {
		this.upymPrgrMthCnt = upymPrgrMthCnt;
	}
	public Integer getUpymMthCnt() {
		return upymMthCnt;
	}
	public void setUpymMthCnt(Integer upymMthCnt) {
		this.upymMthCnt = upymMthCnt;
	}
	public Double getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(Double billAmt) {
		this.billAmt = billAmt;
	}
	public Double getRcptAmt() {
		return rcptAmt;
	}
	public void setRcptAmt(Double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}
	public Double getUpymAmt() {
		return upymAmt;
	}
	public void setUpymAmt(Double upymAmt) {
		this.upymAmt = upymAmt;
	}
	public String getAcctProcYn() {
		return acctProcYn;
	}
	public void setAcctProcYn(String acctProcYn) {
		this.acctProcYn = acctProcYn;
	}
	public String getPayProcYn() {
		return payProcYn;
	}
	public void setPayProcYn(String payProcYn) {
		this.payProcYn = payProcYn;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getChgrId() {
		return chgrId;
	}
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	public Timestamp getChgDate() {
		return chgDate;
	}
	public void setChgDate(Timestamp chgDate) {
		this.chgDate = chgDate;
	}
	
	
	
}

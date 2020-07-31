package com.ntels.ccbs.batch.ch.common.entity;

import java.sql.Timestamp;

public class Multi implements Cloneable {
	private String soId;
	private String multiCycl; /* 멀티주기ID */
	private String seq; /* 멀티주기시퀀스 */
    private Integer billMm;
    private Integer billSt;
    private Integer billUnit;
	private Integer useStMm; /* 멀티주기 시작월구분(-2: 전전월 -1:전월, 0: 당월) */
	private String useStDt; /* 멀티주기 시작일자 */
	private Integer useEdMm; /* 멀티주기 종료월구분(-2: 전전월 -1:전월, 0: 당월) */
	private String useEdDt; /* 멀티주기 종료일자 */
	private Timestamp regDate; /*  */
	private String startDate; /* 시작일자 yyyymmdd */
	private String endDate;  /* 종료일자 yyyymmdd */
	private String chrgCtgry; /* 과금 카테고리 R, D */
	private String billYymm; /* 청구년월 */
	private String clcWrkNo;
	private String billCycl;
	private String pSeq; /* SEQUENCE */
	private String strtNo; /* SEQ 시작계약*/
	private String endNo; /* SEQ 종료계약*/
	private String today;
	private String billDt;
	private String realUseEdDt; /* 월말 데이터가 00, 0 처리용 */
	private String ctrtID; 
	private String funcID;
	private String  svcCmpID;
	private String  inActDttm;                                
	private String  setVal;
	
	private String colId;
	
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getMultiCycl() {
		return multiCycl;
	}
	public void setMultiCycl(String multiCycl) {
		this.multiCycl = multiCycl;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public Integer getBillMm() {
		return billMm;
	}
	public void setBillMm(Integer billMm) {
		this.billMm = billMm;
	}
	public Integer getBillSt() {
		return billSt;
	}
	public void setBillSt(Integer billSt) {
		this.billSt = billSt;
	}
	public Integer getBillUnit() {
		return billUnit;
	}
	public void setBillUnit(Integer billUnit) {
		this.billUnit = billUnit;
	}
	public Integer getUseStMm() {
		return useStMm;
	}
	public void setUseStMm(Integer useStMm) {
		this.useStMm = useStMm;
	}
	public String getUseStDt() {
		return useStDt;
	}
	public void setUseStDt(String useStDt) {
		this.useStDt = useStDt;
	}
	public Integer getUseEdMm() {
		return useEdMm;
	}
	public void setUseEdMm(Integer useEdMm) {
		this.useEdMm = useEdMm;
	}
	public String getUseEdDt() {
		return useEdDt;
	}
	public void setUseEdDt(String useEdDt) {
		this.useEdDt = useEdDt;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getChrgCtgry() {
		return chrgCtgry;
	}
	public void setChrgCtgry(String chrgCtgry) {
		this.chrgCtgry = chrgCtgry;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getClcWrkNo() {
		return clcWrkNo;
	}
	public void setClcWrkNo(String clcWrkNo) {
		this.clcWrkNo = clcWrkNo;
	}
	public String getBillCycl() {
		return billCycl;
	}
	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl;
	}
	public String getpSeq() {
		return pSeq;
	}
	public void setpSeq(String pSeq) {
		this.pSeq = pSeq;
	}
	public String getStrtNo() {
		return strtNo;
	}
	public void setStrtNo(String strtNo) {
		this.strtNo = strtNo;
	}
	public String getEndNo() {
		return endNo;
	}
	public void setEndNo(String endNo) {
		this.endNo = endNo;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getBillDt() {
		return billDt;
	}
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}
	public String getRealUseEdDt() {
		return realUseEdDt;
	}
	public void setRealUseEdDt(String realUseEdDt) {
		this.realUseEdDt = realUseEdDt;
	}
	public String getColId() {
		return colId;
	}
	public void setColId(String colId) {
		this.colId = colId;
	}
	public String getCtrtID() {
		return ctrtID;
	}
	public void setCtrtID(String ctrtID) {
		this.ctrtID = ctrtID;
	}
	public String getFuncID() {
		return funcID;
	}
	public void setFuncID(String funcID) {
		this.funcID = funcID;
	}
	public String getSvcCmpID() {
		return svcCmpID;
	}
	public void setSvcCmpID(String svcCmpID) {
		this.svcCmpID = svcCmpID;
	}
	public String getInActDttm() {
		return inActDttm;
	}
	public void setInActDttm(String inActDttm) {
		this.inActDttm = inActDttm;
	}
	public String getSetVal() {
		return setVal;
	}
	public void setSetVal(String setVal) {
		this.setVal = setVal;
	}
	@Override
	public String toString() {
		return "Multi [soId=" + soId + ", multiCycl=" + multiCycl + ", seq=" + seq + ", billMm=" + billMm + ", billSt="
				+ billSt + ", billUnit=" + billUnit + ", useStMm=" + useStMm + ", useStDt=" + useStDt + ", useEdMm="
				+ useEdMm + ", useEdDt=" + useEdDt + ", regDate=" + regDate + ", startDate=" + startDate + ", endDate="
				+ endDate + ", chrgCtgry=" + chrgCtgry + ", billYymm=" + billYymm + ", clcWrkNo=" + clcWrkNo
				+ ", billCycl=" + billCycl + ", pSeq=" + pSeq + ", strtNo=" + strtNo + ", endNo=" + endNo + ", today="
				+ today + ", billDt=" + billDt + ", realUseEdDt=" + realUseEdDt + "]";
	}

//	public Object clone() throws CloneNotSupportedException{  
//		return super.clone();  
//	}
	
}
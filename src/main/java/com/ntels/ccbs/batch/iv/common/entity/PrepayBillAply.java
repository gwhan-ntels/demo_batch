package com.ntels.ccbs.batch.iv.common.entity;

import java.sql.Timestamp;

public class PrepayBillAply {

	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) +
	 * 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
	 */
	private String billSeqNo;

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 0: 당월 1: 전월 2: 전전월
	 */
	private String billMmTp;

	/**
	 * 선납계약ID
	 */
	private String prepdCtrtId;

	/**
	 * 청구년월(YYYYMM)
	 */
	private String billYymm;

	/**
	 * 청구주기
	 */
	private String billCycl;

	/**
	 * 청구일자(YYYYMMDD)
	 */
	private String billDt;

	/**
	 * 단체ID
	 */
	private String grpId;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 고객ID
	 */
	private String custId;

	/**
	 * 계약ID
	 */
	private String ctrtId;

	/**
	 * 선납 적용된 총금액
	 */
	private double prepdAplyAmt;

	/**
	 * 선납 할인된 총금액
	 */
	private double prepdDcAmt;

	/**
	 * 수납처리여부 N: 수납미처리 Y: 수납처리완료
	 */
	private String payProcYn;

	/**
	 * 수납처리일자(YYYYMMDD)
	 */
	private String payProcDt;

	/**
	 * 취소여부 N: 미취소(Default) Y: 취소
	 */
	private String cnclYn;

	/**
	 * 등록일
	 */
	private Timestamp regDate;

	/**
	 * 생성일시
	 */
	private String crtDttm;

	private String prepdCtrtDt;
	private String prepdCl;
	private double prepdBal;
	private double mthBillAmt;
	private int aplyMthCnt;
	private double mpdRate;
	private double mpatAmt;
	private double sumAmt;
	private String prepdAplyCl;

	public String getBillSeqNo() {
		return billSeqNo;
	}

	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getBillMmTp() {
		return billMmTp;
	}

	public void setBillMmTp(String billMmTp) {
		this.billMmTp = billMmTp;
	}

	public String getPrepdCtrtId() {
		return prepdCtrtId;
	}

	public void setPrepdCtrtId(String prepdCtrtId) {
		this.prepdCtrtId = prepdCtrtId;
	}

	public String getBillYymm() {
		return billYymm;
	}

	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}

	public String getBillCycl() {
		return billCycl;
	}

	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl;
	}

	public String getBillDt() {
		return billDt;
	}

	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
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

	public String getCtrtId() {
		return ctrtId;
	}

	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}

	public double getPrepdAplyAmt() {
		return prepdAplyAmt;
	}

	public void setPrepdAplyAmt(double prepdAplyAmt) {
		this.prepdAplyAmt = prepdAplyAmt;
	}

	public double getPrepdDcAmt() {
		return prepdDcAmt;
	}

	public void setPrepdDcAmt(double prepdDcAmt) {
		this.prepdDcAmt = prepdDcAmt;
	}

	public String getPayProcYn() {
		return payProcYn;
	}

	public void setPayProcYn(String payProcYn) {
		this.payProcYn = payProcYn;
	}

	public String getPayProcDt() {
		return payProcDt;
	}

	public void setPayProcDt(String payProcDt) {
		this.payProcDt = payProcDt;
	}

	public String getCnclYn() {
		return cnclYn;
	}

	public void setCnclYn(String cnclYn) {
		this.cnclYn = cnclYn;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getCrtDttm() {
		return crtDttm;
	}

	public void setCrtDttm(String crtDttm) {
		this.crtDttm = crtDttm;
	}

	public String getPrepdCtrtDt() {
		return prepdCtrtDt;
	}

	public void setPrepdCtrtDt(String prepdCtrtDt) {
		this.prepdCtrtDt = prepdCtrtDt;
	}

	public String getPrepdCl() {
		return prepdCl;
	}

	public void setPrepdCl(String prepdCl) {
		this.prepdCl = prepdCl;
	}

	public double getPrepdBal() {
		return prepdBal;
	}

	public void setPrepdBal(double prepdBal) {
		this.prepdBal = prepdBal;
	}

	public double getMthBillAmt() {
		return mthBillAmt;
	}

	public void setMthBillAmt(double mthBillAmt) {
		this.mthBillAmt = mthBillAmt;
	}

	public int getAplyMthCnt() {
		return aplyMthCnt;
	}

	public void setAplyMthCnt(int aplyMthCnt) {
		this.aplyMthCnt = aplyMthCnt;
	}

	public double getMpdRate() {
		return mpdRate;
	}

	public void setMpdRate(double mpdRate) {
		this.mpdRate = mpdRate;
	}

	public double getMpatAmt() {
		return mpatAmt;
	}

	public void setMpatAmt(double mpatAmt) {
		this.mpatAmt = mpatAmt;
	}

	public double getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(double sumAmt) {
		this.sumAmt = sumAmt;
	}

	public String getPrepdAplyCl() {
		return prepdAplyCl;
	}

	public void setPrepdAplyCl(String prepdAplyCl) {
		this.prepdAplyCl = prepdAplyCl;
	}

}

/**
 * @FileName
 * NBliv01m05.java
 * @Project
 * ccbs_batch
 * @Date
 * 2016. 6. 28.
 * @Writter
 * ntels_shlee
 * @EditHistory
 *
 * @Discript
 */
package com.ntels.ccbs.batch.iv.entity;

public class NBlivb01m08 {

	private String billCycl; /* 청구주기 */	
	private String billDt; /* 청구일자 */
	private String billYymm; /* 청구년월 */
	private String soId; /* 사업구분 */
	private String payDueDt; /* 납부일자 */
	private String useYymm; /* 사용년월 */
	private String pSeq;
	/**
	 * @return the billCycl
	 */
	public String getBillCycl() {
		return billCycl;
	}
	/**
	 * @param billCycl the billCycl to set
	 */
	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl;
	}
	/**
	 * @return the billDt
	 */
	public String getBillDt() {
		return billDt;
	}
	/**
	 * @param billDt the billDt to set
	 */
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}
	/**
	 * @return the billYymm
	 */
	public String getBillYymm() {
		return billYymm;
	}
	/**
	 * @param billYymm the billYymm to set
	 */
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	/**
	 * @return the soId
	 */
	public String getSoId() {
		return soId;
	}
	/**
	 * @param soId the soId to set
	 */
	public void setSoId(String soId) {
		this.soId = soId;
	}
	/**
	 * @return the payDueDt
	 */
	public String getPayDueDt() {
		return payDueDt;
	}
	/**
	 * @param payDueDt the payDueDt to set
	 */
	public void setPayDueDt(String payDueDt) {
		this.payDueDt = payDueDt;
	}
	/**
	 * @return the useYymm
	 */
	public String getUseYymm() {
		return useYymm;
	}
	/**
	 * @param useYymm the useYymm to set
	 */
	public void setUseYymm(String useYymm) {
		this.useYymm = useYymm;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NBlivb01m08 [billCycl=" + billCycl + ", billDt=" + billDt + ", billYymm=" + billYymm + ", soId=" + soId
				+ ", payDueDt=" + payDueDt + ", useYymm=" + useYymm + ", pSeq=" + pSeq + ", getBillCycl()="
				+ getBillCycl() + ", getBillDt()=" + getBillDt() + ", getBillYymm()=" + getBillYymm() + ", getSoId()="
				+ getSoId() + ", getPayDueDt()=" + getPayDueDt() + ", getUseYymm()=" + getUseYymm() + ", getpSeq()="
				+ getpSeq() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	/**
	 * @return the pSeq
	 */
	public String getpSeq() {
		return pSeq;
	}
	/**
	 * @param pSeq the pSeq to set
	 */
	public void setpSeq(String pSeq) {
		this.pSeq = pSeq;
	}
	
    	
}

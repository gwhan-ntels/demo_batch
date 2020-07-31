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

import java.sql.Timestamp;

public class NBlivb01m00 {

	private String billCycl; /* 청구주기 */	
	private String billDt; /* 청구일자 */
	private String billYymm; /* 청구년월 */
	private String soId; /* 사업구분 */
	private String useYymm; /* 사용년월 */
	private String clcWrkNo;
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
	/**
	 * @return the clcWrkNo
	 */
	public String getClcWrkNo() {
		return clcWrkNo;
	}
	/**
	 * @param clcWrkNo the clcWrkNo to set
	 */
	public void setClcWrkNo(String clcWrkNo) {
		this.clcWrkNo = clcWrkNo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NBlivb01m00 [billCycl=" + billCycl + ", billDt=" + billDt + ", billYymm=" + billYymm + ", soId=" + soId
				+ ", useYymm=" + useYymm + ", clcWrkNo=" + clcWrkNo + "]";
	}
   	
	
	
    	
}

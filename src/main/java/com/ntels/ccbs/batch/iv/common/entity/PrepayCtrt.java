package com.ntels.ccbs.batch.iv.common.entity;

import java.sql.Timestamp;

public class PrepayCtrt {

	private String prepdCtrtId;
	
	private String soId;
	
	private Double prepdAplyAmt;
	
	private Double prepdDcAmt;
	
	private Double prepdBal;
	
	private Long aplyDgr;
	
	private Timestamp regDate;
	
	private Timestamp chgDate;

	/**
	 * @return the prepdCtrtId
	 */
	public String getPrepdCtrtId() {
		return prepdCtrtId;
	}

	/**
	 * @param prepdCtrtId the prepdCtrtId to set
	 */
	public void setPrepdCtrtId(String prepdCtrtId) {
		this.prepdCtrtId = prepdCtrtId;
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
	 * @return the prepdAplyAmt
	 */
	public Double getPrepdAplyAmt() {
		return prepdAplyAmt;
	}

	/**
	 * @param prepdAplyAmt the prepdAplyAmt to set
	 */
	public void setPrepdAplyAmt(Double prepdAplyAmt) {
		this.prepdAplyAmt = prepdAplyAmt;
	}

	/**
	 * @return the prepdDcAmt
	 */
	public Double getPrepdDcAmt() {
		return prepdDcAmt;
	}

	/**
	 * @param prepdDcAmt the prepdDcAmt to set
	 */
	public void setPrepdDcAmt(Double prepdDcAmt) {
		this.prepdDcAmt = prepdDcAmt;
	}

	/**
	 * @return the prepdBal
	 */
	public Double getPrepdBal() {
		return prepdBal;
	}

	/**
	 * @param prepdBal the prepdBal to set
	 */
	public void setPrepdBal(Double prepdBal) {
		this.prepdBal = prepdBal;
	}

	/**
	 * @return the aplyDgr
	 */
	public Long getAplyDgr() {
		return aplyDgr;
	}

	/**
	 * @param aplyDgr the aplyDgr to set
	 */
	public void setAplyDgr(Long aplyDgr) {
		this.aplyDgr = aplyDgr;
	}

	/**
	 * @return the regDate
	 */
	public Timestamp getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the chgDate
	 */
	public Timestamp getChgDate() {
		return chgDate;
	}

	/**
	 * @param chgDate the chgDate to set
	 */
	public void setChgDate(Timestamp chgDate) {
		this.chgDate = chgDate;
	}
	
}

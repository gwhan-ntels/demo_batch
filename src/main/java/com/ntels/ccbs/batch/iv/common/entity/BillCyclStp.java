package com.ntels.ccbs.batch.iv.common.entity;

import java.sql.Timestamp;

public class BillCyclStp {

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 설정항목ID
	 */
	private String setItmId;

	/**
	 * YYYYMM
	 */
	private String eftBgnYymm;

	/**
	 * YYYYMM
	 */
	private String billYymm;

	/**
	 * BLIV002 00: 수시청구 05: 5일
	 */
	private String billCycl;

	/**
	 * YYYYMM
	 */
	private String eftEndYymm;

	/**
	 * 설정값
	 */
	private String setVal;

	/**
	 * 등록자ID
	 */
	private String regrId;

	/**
	 * 등록일
	 */
	private Timestamp regDate;

	/**
	 * 변경자ID
	 */
	private String chgrId;

	/**
	 * 변경일
	 */
	private Timestamp chgDate;

	/**
	 * 기준설정값
	 */
	private String bsSetVal;
	
	private String setLoc;

	private String currentDate;
	
	private String exrateAplyDt;
	
	public String getExrateAplyDt() {
		return exrateAplyDt;
	}

	public void setExrateAplyDt(String exrateAplyDt) {
		this.exrateAplyDt = exrateAplyDt;
	}


	
	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getSetItmId() {
		return setItmId;
	}

	public void setSetItmId(String setItmId) {
		this.setItmId = setItmId;
	}

	public String getEftBgnYymm() {
		return eftBgnYymm;
	}

	public void setEftBgnYymm(String eftBgnYymm) {
		this.eftBgnYymm = eftBgnYymm;
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

	public String getEftEndYymm() {
		return eftEndYymm;
	}

	public void setEftEndYymm(String eftEndYymm) {
		this.eftEndYymm = eftEndYymm;
	}

	public String getSetVal() {
		return setVal;
	}

	public void setSetVal(String setVal) {
		this.setVal = setVal;
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

	public String getBsSetVal() {
		return bsSetVal;
	}

	public void setBsSetVal(String bsSetVal) {
		this.bsSetVal = bsSetVal;
	}

	public String getSetLoc() {
		return setLoc;
	}

	public void setSetLoc(String setLoc) {
		this.setLoc = setLoc;
	}

}

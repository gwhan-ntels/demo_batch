package com.ntels.ccbs.batch.py.entity;

import java.sql.Timestamp;

public class AmbgTransHistory {

	/**
	 * Table Sequence 번호
	 */
	private String ambgOccSeqNo;

	/**
	 * Table Sequence 번호
	 */
	private String ambgTransSeqNo;

	/**
	 * 대체처리일시(YYYYMMDDHHMMSS)
	 */
	private String transProcDttm;

	/**
	 * 미확인입금대체구분(BLPY011) 01: 수납대체 02: 선수금대체 03: 잡이익대체 04: 회계대체 05: 환불대체
	 */
	private String ambgReplTp;

	/**
	 * 대체처리금액
	 */
	private double transProcAmt;

	/**
	 * 처리메모
	 */
	private String procMemo;

	/**
	 * 원화대체처리금액
	 */
	private double wonReplProcAmt;

	/**
	 * BLIV023 DEM: Germany (Mark)-Deutch Mark EUR: Europe (Euro) GBP: Great
	 * Britain (Pound) HKD: Hong Kong (Dollar) JPY: Japan (Yen) KRW: South Korea
	 * (Won) RMB: China (Renminbi)-CNY SDR: Special Drawing Rights SGD:
	 * Singapore (Dollar) USD: United States (Dollar)
	 */
	private String crncyCd;

	/**
	 * 환율
	 */
	private double exrate;

	/**
	 * 환불적용일자(YYYYMMDD)
	 */
	private String exrateAplyDt;

	/**
	 * 등록자ID
	 */
	private String regrId;

	/**
	 * 등록일
	 */
	private Timestamp regDate;

	/**
	 * 결재요청자ID
	 */
	private String apprReqrId;

	/**
	 * YYYYMMDDHHMMSS
	 */
	private String apprReqDttm;

	/**
	 * 결재처리상태(BLIV013) 01: 신청 02: 결재요청중 03: 결재완료 04: 반송 05: 처리완료
	 */
	private String dcsnProcStat;

	/**
	 * 결재자ID
	 */
	private String apprrId;

	/**
	 * 결재일시(YYYYMMDDHHMMSS)
	 */
	private String apprDttm;

	/**
	 * 취소여부 N: 미취소(Default) Y: 취소
	 */
	private String cnclYn;

	/**
	 * 취소일시(YYYYMMDDHHMMSS)
	 */
	private String cnclDttm;

	/**
	 * 
	 */
	private double balAmt;

	public String getAmbgOccSeqNo() {
		return ambgOccSeqNo;
	}

	public void setAmbgOccSeqNo(String ambgOccSeqNo) {
		this.ambgOccSeqNo = ambgOccSeqNo;
	}

	public String getAmbgTransSeqNo() {
		return ambgTransSeqNo;
	}

	public void setAmbgTransSeqNo(String ambgTransSeqNo) {
		this.ambgTransSeqNo = ambgTransSeqNo;
	}

	public String getTransProcDttm() {
		return transProcDttm;
	}

	public void setTransProcDttm(String transProcDttm) {
		this.transProcDttm = transProcDttm;
	}

	public String getAmbgReplTp() {
		return ambgReplTp;
	}

	public void setAmbgReplTp(String ambgReplTp) {
		this.ambgReplTp = ambgReplTp;
	}

	public double getTransProcAmt() {
		return transProcAmt;
	}

	public void setTransProcAmt(double transProcAmt) {
		this.transProcAmt = transProcAmt;
	}

	public String getProcMemo() {
		return procMemo;
	}

	public void setProcMemo(String procMemo) {
		this.procMemo = procMemo;
	}

	public double getWonReplProcAmt() {
		return wonReplProcAmt;
	}

	public void setWonReplProcAmt(double wonReplProcAmt) {
		this.wonReplProcAmt = wonReplProcAmt;
	}

	public String getCrncyCd() {
		return crncyCd;
	}

	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}

	public double getExrate() {
		return exrate;
	}

	public void setExrate(double exrate) {
		this.exrate = exrate;
	}

	public String getExrateAplyDt() {
		return exrateAplyDt;
	}

	public void setExrateAplyDt(String exrateAplyDt) {
		this.exrateAplyDt = exrateAplyDt;
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

	public String getApprReqrId() {
		return apprReqrId;
	}

	public void setApprReqrId(String apprReqrId) {
		this.apprReqrId = apprReqrId;
	}

	public String getApprReqDttm() {
		return apprReqDttm;
	}

	public void setApprReqDttm(String apprReqDttm) {
		this.apprReqDttm = apprReqDttm;
	}

	public String getDcsnProcStat() {
		return dcsnProcStat;
	}

	public void setDcsnProcStat(String dcsnProcStat) {
		this.dcsnProcStat = dcsnProcStat;
	}

	public String getApprrId() {
		return apprrId;
	}

	public void setApprrId(String apprrId) {
		this.apprrId = apprrId;
	}

	public String getApprDttm() {
		return apprDttm;
	}

	public void setApprDttm(String apprDttm) {
		this.apprDttm = apprDttm;
	}

	public String getCnclYn() {
		return cnclYn;
	}

	public void setCnclYn(String cnclYn) {
		this.cnclYn = cnclYn;
	}

	public String getCnclDttm() {
		return cnclDttm;
	}

	public void setCnclDttm(String cnclDttm) {
		this.cnclDttm = cnclDttm;
	}

	public double getBalAmt() {
		return balAmt;
	}

	public void setBalAmt(double balAmt) {
		this.balAmt = balAmt;
	}

}

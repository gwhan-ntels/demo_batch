package com.ntels.ccbs.batch.common.entity;

import java.sql.Timestamp;

/**
 * 일괄 작업 수행 시 분산 실행을 위한 멀티 시퀀스 정보
 * SO_ID			: 사업자ID
 * P_SEQ			: 다중SEQUENCE
 * GUBUN			: 구분 1:계산 2:청구
 * STRT_NO			: SEQ 시작번호
 * END_NO			: SEQ 종료번호
 * REG_DATE			: 등록날짜 
 * @author Cashyalla
 *
 */
public class MultiSeq {

	/**
	 * 사업자ID
	 */
	private String soId;

	/**
	 * 다중SEQUENCE
	 */
	private String pSeq;

	/**
	 * 구분 1:계산 2:청구
	 */
	private String gubun;

	/**
	 * SEQ 시작번호
	 */
	private String strtNo;

	/**
	 * SEQ 종료번호
	 */
	private String endNo;

	/**
	 * 등록날짜
	 */
	private Timestamp regDate;

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getpSeq() {
		return pSeq;
	}

	public void setpSeq(String pSeq) {
		this.pSeq = pSeq;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
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

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}

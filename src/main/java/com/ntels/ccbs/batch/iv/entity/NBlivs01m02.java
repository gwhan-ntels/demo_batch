package com.ntels.ccbs.batch.iv.entity;

public class NBlivs01m02 {

	/**
	 * YYYYMM
	 */
	private String billYymm;

	/**
	 * YYYYMMDD
	 */
	private String billDt;

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 지역할당부서코드
	 */
	private String areaDeptCd;

	/**
	 * 납부방법(BLIV005) 01: 지로 02: 자동이체 03: CMS 04: 신용카드 05: KT합산청구 06: 휴대폰합산청구
	 */
	private String payMthd;

	/**
	 * 해당 상품이 속하는 상품군 정보를 관리 A: ATV상품군 D: DTV상품군 I: iTV상품군 C: 복합상품군
	 */
	private String prodGrp;

	/**
	 * 고객에게 제공하는 논리적인 상품의 코드 Sequence : PD99999999 공통상품코드 : PZ00000000
	 */
	private String prodCd;

	/**
	 * 사업구분
	 */
	private String bizCl;

	/**
	 * 사업자가 제공하는 물리적인 서비스 Sequence : SV99999999
	 */
	private String svcCd;

	/**
	 * 청구Process단위에서 관리되는 항목으로 약관에서 정의하는 기준의 요금을 관리 Sequence : CI99999999
	 */
	private String chrgItmCd;

	/**
	 * 납부계정수
	 */
	private Long pymAcntCnt;

	/**
	 * 고객수
	 */
	private Long custCnt;

	/**
	 * 계약건수
	 */
	private Long ctrtCnt;

	/**
	 * 계약상품건수
	 */
	private Long prodCmpsCnt;

	/**
	 * 계약서비스건수
	 */
	private Long svcCmpsCnt;

	/**
	 * 건수
	 */
	private Long cnt;

	/**
	 * 조정전청구금액
	 */
	private Double adjPrvBillAmt;

	/**
	 * 조정금액
	 */
	private Double adjAmt;

	/**
	 * 청구금액
	 */
	private Double billAmt;

	/**
	 * 처리일자(YYYYMMDD)
	 */
	private String procDt;

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
	 * @return the areaDeptCd
	 */
	public String getAreaDeptCd() {
		return areaDeptCd;
	}

	/**
	 * @param areaDeptCd the areaDeptCd to set
	 */
	public void setAreaDeptCd(String areaDeptCd) {
		this.areaDeptCd = areaDeptCd;
	}

	/**
	 * @return the payMthd
	 */
	public String getPayMthd() {
		return payMthd;
	}

	/**
	 * @param payMthd the payMthd to set
	 */
	public void setPayMthd(String payMthd) {
		this.payMthd = payMthd;
	}

	/**
	 * @return the prodGrp
	 */
	public String getProdGrp() {
		return prodGrp;
	}

	/**
	 * @param prodGrp the prodGrp to set
	 */
	public void setProdGrp(String prodGrp) {
		this.prodGrp = prodGrp;
	}

	/**
	 * @return the prodCd
	 */
	public String getProdCd() {
		return prodCd;
	}

	/**
	 * @param prodCd the prodCd to set
	 */
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}

	/**
	 * @return the bizCl
	 */
	public String getBizCl() {
		return bizCl;
	}

	/**
	 * @param bizCl the bizCl to set
	 */
	public void setBizCl(String bizCl) {
		this.bizCl = bizCl;
	}

	/**
	 * @return the svcCd
	 */
	public String getSvcCd() {
		return svcCd;
	}

	/**
	 * @param svcCd the svcCd to set
	 */
	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}

	/**
	 * @return the chrgItmCd
	 */
	public String getChrgItmCd() {
		return chrgItmCd;
	}

	/**
	 * @param chrgItmCd the chrgItmCd to set
	 */
	public void setChrgItmCd(String chrgItmCd) {
		this.chrgItmCd = chrgItmCd;
	}

	/**
	 * @return the pymAcntCnt
	 */
	public Long getPymAcntCnt() {
		return pymAcntCnt;
	}

	/**
	 * @param pymAcntCnt the pymAcntCnt to set
	 */
	public void setPymAcntCnt(Long pymAcntCnt) {
		this.pymAcntCnt = pymAcntCnt;
	}

	/**
	 * @return the custCnt
	 */
	public Long getCustCnt() {
		return custCnt;
	}

	/**
	 * @param custCnt the custCnt to set
	 */
	public void setCustCnt(Long custCnt) {
		this.custCnt = custCnt;
	}

	/**
	 * @return the ctrtCnt
	 */
	public Long getCtrtCnt() {
		return ctrtCnt;
	}

	/**
	 * @param ctrtCnt the ctrtCnt to set
	 */
	public void setCtrtCnt(Long ctrtCnt) {
		this.ctrtCnt = ctrtCnt;
	}

	/**
	 * @return the prodCmpsCnt
	 */
	public Long getProdCmpsCnt() {
		return prodCmpsCnt;
	}

	/**
	 * @param prodCmpsCnt the prodCmpsCnt to set
	 */
	public void setProdCmpsCnt(Long prodCmpsCnt) {
		this.prodCmpsCnt = prodCmpsCnt;
	}

	/**
	 * @return the svcCmpsCnt
	 */
	public Long getSvcCmpsCnt() {
		return svcCmpsCnt;
	}

	/**
	 * @param svcCmpsCnt the svcCmpsCnt to set
	 */
	public void setSvcCmpsCnt(Long svcCmpsCnt) {
		this.svcCmpsCnt = svcCmpsCnt;
	}

	/**
	 * @return the cnt
	 */
	public Long getCnt() {
		return cnt;
	}

	/**
	 * @param cnt the cnt to set
	 */
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}

	/**
	 * @return the adjPrvBillAmt
	 */
	public Double getAdjPrvBillAmt() {
		return adjPrvBillAmt;
	}

	/**
	 * @param adjPrvBillAmt the adjPrvBillAmt to set
	 */
	public void setAdjPrvBillAmt(Double adjPrvBillAmt) {
		this.adjPrvBillAmt = adjPrvBillAmt;
	}

	/**
	 * @return the adjAmt
	 */
	public Double getAdjAmt() {
		return adjAmt;
	}

	/**
	 * @param adjAmt the adjAmt to set
	 */
	public void setAdjAmt(Double adjAmt) {
		this.adjAmt = adjAmt;
	}

	/**
	 * @return the billAmt
	 */
	public Double getBillAmt() {
		return billAmt;
	}

	/**
	 * @param billAmt the billAmt to set
	 */
	public void setBillAmt(Double billAmt) {
		this.billAmt = billAmt;
	}

	/**
	 * @return the procDt
	 */
	public String getProcDt() {
		return procDt;
	}

	/**
	 * @param procDt the procDt to set
	 */
	public void setProcDt(String procDt) {
		this.procDt = procDt;
	}

}

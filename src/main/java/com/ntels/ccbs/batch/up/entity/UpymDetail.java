package com.ntels.ccbs.batch.up.entity;

import java.sql.Timestamp;

public class UpymDetail {

	/**
	 * 미납기준년월(YYYYMM)
	 */
	private String upymBsYymm;

	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) + 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
	 */
	private String billSeqNo;

	/**
	 * 사용년월(YYYYMM)
	 */
	private String useYymm;

	/**
	 * 상품구성ID
	 */
	private String prodCmpsId;

	/**
	 * 서비스구성ID
	 */
	private String svcCmpsId;

	/**
	 * 청구Process단위에서 관리되는 항목으로 약관에서 정의하는 기준의 요금을 관리 Sequence : CI99999999
	 */
	private String chrgItmCd;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

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
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 고객ID
	 */
	private String custId;

	/**
	 * 납부고객ID
	 */
	private String pymCustId;

	/**
	 * 계약ID
	 */
	private String ctrtId;

	/**
	 * 사업구분(PP00921) A:냉방 C:수도 E:전기 G:가스 H:난방 W:온수 Z:공통
	 */
	private String bizCl;

	/**
	 * 납기마감일자(YYYYMMDD)
	 */
	private String payDueDt;

	/**
	 * 청구금액
	 */
	private Double billAmt;

	/**
	 * 수납금액
	 */
	private Double rcptAmt;

	/**
	 * 미납금액
	 */
	private Double upymAmt;
	private Integer upymMthCnt;
	private Integer upymPrgrMthCnt;

	/**
	 * 고액대상여부
	 */
	private String upymBigAmtYn;
	private String upymDueDt;

	/**
	 * 해당 미납의 연체 경과 월 수
	 */
	private Integer arrsMthCnt;

	/**
	 * MSO, SO, RO, 협력업체의 ID
	 */
	private String atrtCorpId;

	/**
	 * MSO, SO, RO, 협력업체의 사원ID
	 */
	private String atrtEmpId;

	/**
	 * BLIV023 DEM: Germany (Mark)-Deutch Mark EUR: Europe (Euro) GBP: Great Britain (Pound) 
	 * HKD: Hong Kong (Dollar) JPY: Japan (Yen) KRW: South Korea (Won) RMB: China (Renminbi)-CNY 
	 * SDR: Special Drawing Rights SGD: Singapore (Dollar) USD: United States (Dollar)
	 */
	private String crncyCd;

	/**
	 * 환율
	 */
	private Double exrate;

	/**
	 * 환불적용일자(YYYYMMDD)
	 */
	private String exrateAplyDt;

	/**
	 * 등록일
	 */
	private Timestamp regDate;

	/**
	 * 등록자ID
	 */
	private String regrId;
	
	public String getUpymBsYymm() {
		return upymBsYymm;
	}
	public void setUpymBsYymm(String upymBsYymm) {
		this.upymBsYymm = upymBsYymm;
	}
	public String getBillSeqNo() {
		return billSeqNo;
	}
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}
	public String getUseYymm() {
		return useYymm;
	}
	public void setUseYymm(String useYymm) {
		this.useYymm = useYymm;
	}
	public String getProdCmpsId() {
		return prodCmpsId;
	}
	public void setProdCmpsId(String prodCmpsId) {
		this.prodCmpsId = prodCmpsId;
	}
	public String getSvcCmpsId() {
		return svcCmpsId;
	}
	public void setSvcCmpsId(String svcCmpsId) {
		this.svcCmpsId = svcCmpsId;
	}
	public String getChrgItmCd() {
		return chrgItmCd;
	}
	public void setChrgItmCd(String chrgItmCd) {
		this.chrgItmCd = chrgItmCd;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
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
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPymCustId() {
		return pymCustId;
	}
	public void setPymCustId(String pymCustId) {
		this.pymCustId = pymCustId;
	}
	public String getCtrtId() {
		return ctrtId;
	}
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}
	public String getBizCl() {
		return bizCl;
	}
	public void setBizCl(String bizCl) {
		this.bizCl = bizCl;
	}
	public String getPayDueDt() {
		return payDueDt;
	}
	public void setPayDueDt(String payDueDt) {
		this.payDueDt = payDueDt;
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
	public Integer getUpymMthCnt() {
		return upymMthCnt;
	}
	public void setUpymMthCnt(Integer upymMthCnt) {
		this.upymMthCnt = upymMthCnt;
	}
	public Integer getUpymPrgrMthCnt() {
		return upymPrgrMthCnt;
	}
	public void setUpymPrgrMthCnt(Integer upymPrgrMthCnt) {
		this.upymPrgrMthCnt = upymPrgrMthCnt;
	}
	public String getUpymBigAmtYn() {
		return upymBigAmtYn;
	}
	public void setUpymBigAmtYn(String upymBigAmtYn) {
		this.upymBigAmtYn = upymBigAmtYn;
	}
	public String getUpymDueDt() {
		return upymDueDt;
	}
	public void setUpymDueDt(String upymDueDt) {
		this.upymDueDt = upymDueDt;
	}
	public Integer getArrsMthCnt() {
		return arrsMthCnt;
	}
	public void setArrsMthCnt(Integer arrsMthCnt) {
		this.arrsMthCnt = arrsMthCnt;
	}
	public String getAtrtCorpId() {
		return atrtCorpId;
	}
	public void setAtrtCorpId(String atrtCorpId) {
		this.atrtCorpId = atrtCorpId;
	}
	public String getAtrtEmpId() {
		return atrtEmpId;
	}
	public void setAtrtEmpId(String atrtEmpId) {
		this.atrtEmpId = atrtEmpId;
	}
	public String getCrncyCd() {
		return crncyCd;
	}
	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}
	public Double getExrate() {
		return exrate;
	}
	public void setExrate(Double exrate) {
		this.exrate = exrate;
	}
	public String getExrateAplyDt() {
		return exrateAplyDt;
	}
	public void setExrateAplyDt(String exrateAplyDt) {
		this.exrateAplyDt = exrateAplyDt;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	
}

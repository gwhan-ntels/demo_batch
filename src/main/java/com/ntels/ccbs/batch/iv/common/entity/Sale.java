package com.ntels.ccbs.batch.iv.common.entity;

import java.sql.Timestamp;

public class Sale {

	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) +
	 * 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
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
	 * 회계와 연계되어 매출을 관리하는 단위의 항목 Sequence : SI99999999
	 */
	private String saleItmCd;

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
	 * 고객에게 제공하는 논리적인 상품의 코드 Sequence : PD99999999 공통상품코드 : PZ00000000
	 */
	private String prodCd;

	/**
	 * 사업자가 제공하는 물리적인 서비스 Sequence : SV99999999
	 */
	private String svcCd;

	/**
	 * 사업구분
	 */
	private String bizCl;

	/**
	 * 해당 상품이 속하는 상품군 정보를 관리 A: ATV상품군 D: DTV상품군 I: iTV상품군 C: 복합상품군
	 */
	private String prodGrp;

	/**
	 * 서비스군
	 */
	private String svcGrp;

	/**
	 * 0: 당월 1: 전월 2: 전전월
	 */
	private String billMmTp;

	/**
	 * BLIV016 01: 정상매출(+) 02: 정상매출(-) 03: 재과금매출 04: 선납청구 05: 선납대상매출 06: 재과금수수료
	 * 07: 선수금대상매출 08: 정산매출
	 */
	private String saleTp;

	/**
	 * 매출금액
	 */
	private Double saleAmt;

	/**
	 * 부가세
	 */
	private Double vat;

	/**
	 * 특별소비세
	 */
	private Double sct;

	/**
	 * 납기마감일자(YYYYMMDD)
	 */
	private String payDueDt;

	/**
	 * BLIV006 1: 과세 2: 면세 3: 영세
	 */
	private String taxTp;

	/**
	 * MSO, SO, RO, 협력업체의 ID
	 */
	private String atrtCorpId;

	/**
	 * MSO, SO, RO, 협력업체의 사원ID
	 */
	private String atrtEmpId;

	/**
	 * 회계이관여부 Y: 회계이관완료 N: 회계미관미처리
	 */
	private String acctTrnsYn;

	/**
	 * 원화매출금액
	 */
	private Double wonSaleAmt;

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
	 * 조정금액
	 */
	private Double adjAmt;

	/**
	 * BLIV016 01: 정상매출(+) 02: 정상매출(-) 03: 재과금매출 04: 선납청구 05: 선납대상매출 06: 재과금수수료
	 * 07: 선수금대상매출 08: 정산매출
	 */
	private String saleCl;

	/**
	 * @return the billSeqNo
	 */
	public String getBillSeqNo() {
		return billSeqNo;
	}

	/**
	 * @param billSeqNo the billSeqNo to set
	 */
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
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
	 * @return the prodCmpsId
	 */
	public String getProdCmpsId() {
		return prodCmpsId;
	}

	/**
	 * @param prodCmpsId the prodCmpsId to set
	 */
	public void setProdCmpsId(String prodCmpsId) {
		this.prodCmpsId = prodCmpsId;
	}

	/**
	 * @return the svcCmpsId
	 */
	public String getSvcCmpsId() {
		return svcCmpsId;
	}

	/**
	 * @param svcCmpsId the svcCmpsId to set
	 */
	public void setSvcCmpsId(String svcCmpsId) {
		this.svcCmpsId = svcCmpsId;
	}

	/**
	 * @return the saleItmCd
	 */
	public String getSaleItmCd() {
		return saleItmCd;
	}

	/**
	 * @param saleItmCd the saleItmCd to set
	 */
	public void setSaleItmCd(String saleItmCd) {
		this.saleItmCd = saleItmCd;
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
	 * @return the grpId
	 */
	public String getGrpId() {
		return grpId;
	}

	/**
	 * @param grpId the grpId to set
	 */
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	/**
	 * @return the pymAcntId
	 */
	public String getPymAcntId() {
		return pymAcntId;
	}

	/**
	 * @param pymAcntId the pymAcntId to set
	 */
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * @return the ctrtId
	 */
	public String getCtrtId() {
		return ctrtId;
	}

	/**
	 * @param ctrtId the ctrtId to set
	 */
	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
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
	 * @return the svcGrp
	 */
	public String getSvcGrp() {
		return svcGrp;
	}

	/**
	 * @param svcGrp the svcGrp to set
	 */
	public void setSvcGrp(String svcGrp) {
		this.svcGrp = svcGrp;
	}

	/**
	 * @return the billMmTp
	 */
	public String getBillMmTp() {
		return billMmTp;
	}

	/**
	 * @param billMmTp the billMmTp to set
	 */
	public void setBillMmTp(String billMmTp) {
		this.billMmTp = billMmTp;
	}

	/**
	 * @return the saleTp
	 */
	public String getSaleTp() {
		return saleTp;
	}

	/**
	 * @param saleTp the saleTp to set
	 */
	public void setSaleTp(String saleTp) {
		this.saleTp = saleTp;
	}

	/**
	 * @return the saleAmt
	 */
	public Double getSaleAmt() {
		return saleAmt;
	}

	/**
	 * @param saleAmt the saleAmt to set
	 */
	public void setSaleAmt(Double saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * @return the vat
	 */
	public Double getVat() {
		return vat;
	}

	/**
	 * @param vat the vat to set
	 */
	public void setVat(Double vat) {
		this.vat = vat;
	}

	/**
	 * @return the sct
	 */
	public Double getSct() {
		return sct;
	}

	/**
	 * @param sct the sct to set
	 */
	public void setSct(Double sct) {
		this.sct = sct;
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
	 * @return the taxTp
	 */
	public String getTaxTp() {
		return taxTp;
	}

	/**
	 * @param taxTp the taxTp to set
	 */
	public void setTaxTp(String taxTp) {
		this.taxTp = taxTp;
	}

	/**
	 * @return the atrtCorpId
	 */
	public String getAtrtCorpId() {
		return atrtCorpId;
	}

	/**
	 * @param atrtCorpId the atrtCorpId to set
	 */
	public void setAtrtCorpId(String atrtCorpId) {
		this.atrtCorpId = atrtCorpId;
	}

	/**
	 * @return the atrtEmpId
	 */
	public String getAtrtEmpId() {
		return atrtEmpId;
	}

	/**
	 * @param atrtEmpId the atrtEmpId to set
	 */
	public void setAtrtEmpId(String atrtEmpId) {
		this.atrtEmpId = atrtEmpId;
	}

	/**
	 * @return the acctTrnsYn
	 */
	public String getAcctTrnsYn() {
		return acctTrnsYn;
	}

	/**
	 * @param acctTrnsYn the acctTrnsYn to set
	 */
	public void setAcctTrnsYn(String acctTrnsYn) {
		this.acctTrnsYn = acctTrnsYn;
	}

	/**
	 * @return the wonSaleAmt
	 */
	public Double getWonSaleAmt() {
		return wonSaleAmt;
	}

	/**
	 * @param wonSaleAmt the wonSaleAmt to set
	 */
	public void setWonSaleAmt(Double wonSaleAmt) {
		this.wonSaleAmt = wonSaleAmt;
	}

	/**
	 * @return the crncyCd
	 */
	public String getCrncyCd() {
		return crncyCd;
	}

	/**
	 * @param crncyCd the crncyCd to set
	 */
	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}

	/**
	 * @return the exrate
	 */
	public Double getExrate() {
		return exrate;
	}

	/**
	 * @param exrate the exrate to set
	 */
	public void setExrate(Double exrate) {
		this.exrate = exrate;
	}

	/**
	 * @return the exrateAplyDt
	 */
	public String getExrateAplyDt() {
		return exrateAplyDt;
	}

	/**
	 * @param exrateAplyDt the exrateAplyDt to set
	 */
	public void setExrateAplyDt(String exrateAplyDt) {
		this.exrateAplyDt = exrateAplyDt;
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
	 * @return the saleCl
	 */
	public String getSaleCl() {
		return saleCl;
	}

	/**
	 * @param saleCl the saleCl to set
	 */
	public void setSaleCl(String saleCl) {
		this.saleCl = saleCl;
	}

}

package com.ntels.ccbs.batch.iv.common.entity;

import java.sql.Timestamp;

public class TaxBill {

	/**
	 * 세금계산서발행번호
	 */
	private String taxIssNo;

	/**
	 * 해당 청구월, 청구주기에 청구대상이 되는 납부계정 정보에 대한 청구서 번호(20자리) 청구년월(4) + 주기(2) +
	 * 청구일자DD(2) + 납부계정ID(10) + 일련번호(2)
	 */
	private String billSeqNo;

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
	 * 매출일자(YYYYMMDD)
	 */
	private String saleDt;

	/**
	 * 세금계산서발행일자
	 */
	private String taxBillIssDt;

	/**
	 * 세금계산서작성일자
	 */
	private String taxBillWrDt;

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 납부계정ID
	 */
	private String pymAcntId;

	/**
	 * 사업구분
	 */
	private String bizCl;

	/**
	 * 0: 당월 1: 전월 2: 전전월
	 */
	private String billMmTp;

	/**
	 * 고객유형코드
	 */
	private String custTp;

	/**
	 * 공급자사업번호
	 */
	private String splyBizNo;

	/**
	 * 사업자등록번호
	 */
	private String bizRegNo;

	/**
	 * 고객명
	 */
	private String custNm;

	/**
	 * 대표자명
	 */
	private String repNm;

	/**
	 * 업종
	 */
	private String busiTp;

	/**
	 * 업태
	 */
	private String busiCndt;

	/**
	 * BLIV006 1: 과세 2: 면세 3: 영세
	 */
	private String taxTp;

	/**
	 * 우편번호
	 */
	private String zipCd;

	/**
	 * 주소
	 */
	private String addr;

	/**
	 * 비고
	 */
	private String rmrk;

	/**
	 * 공급가액
	 */
	private Double splyAmt;

	/**
	 * 부가세
	 */
	private Double vat;

	/**
	 * 특별소비세
	 */
	private Double sct;

	/**
	 * 계산서발행여부
	 */
	private String tbiFlg;

	/**
	 * 세금신고구분
	 */
	private String taxDclrCl;

	/**
	 * 등록일
	 */
	private Timestamp regDate;

	/**
	 * @return the taxIssNo
	 */
	public String getTaxIssNo() {
		return taxIssNo;
	}

	/**
	 * @param taxIssNo
	 *            the taxIssNo to set
	 */
	public void setTaxIssNo(String taxIssNo) {
		this.taxIssNo = taxIssNo;
	}

	/**
	 * @return the billSeqNo
	 */
	public String getBillSeqNo() {
		return billSeqNo;
	}

	/**
	 * @param billSeqNo
	 *            the billSeqNo to set
	 */
	public void setBillSeqNo(String billSeqNo) {
		this.billSeqNo = billSeqNo;
	}

	/**
	 * @return the billYymm
	 */
	public String getBillYymm() {
		return billYymm;
	}

	/**
	 * @param billYymm
	 *            the billYymm to set
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
	 * @param billCycl
	 *            the billCycl to set
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
	 * @param billDt
	 *            the billDt to set
	 */
	public void setBillDt(String billDt) {
		this.billDt = billDt;
	}

	/**
	 * @return the saleDt
	 */
	public String getSaleDt() {
		return saleDt;
	}

	/**
	 * @param saleDt
	 *            the saleDt to set
	 */
	public void setSaleDt(String saleDt) {
		this.saleDt = saleDt;
	}

	/**
	 * @return the taxBillIssDt
	 */
	public String getTaxBillIssDt() {
		return taxBillIssDt;
	}

	/**
	 * @param taxBillIssDt
	 *            the taxBillIssDt to set
	 */
	public void setTaxBillIssDt(String taxBillIssDt) {
		this.taxBillIssDt = taxBillIssDt;
	}

	/**
	 * @return the taxBillWrDt
	 */
	public String getTaxBillWrDt() {
		return taxBillWrDt;
	}

	/**
	 * @param taxBillWrDt
	 *            the taxBillWrDt to set
	 */
	public void setTaxBillWrDt(String taxBillWrDt) {
		this.taxBillWrDt = taxBillWrDt;
	}

	/**
	 * @return the soId
	 */
	public String getSoId() {
		return soId;
	}

	/**
	 * @param soId
	 *            the soId to set
	 */
	public void setSoId(String soId) {
		this.soId = soId;
	}

	/**
	 * @return the pymAcntId
	 */
	public String getPymAcntId() {
		return pymAcntId;
	}

	/**
	 * @param pymAcntId
	 *            the pymAcntId to set
	 */
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	/**
	 * @return the bizCl
	 */
	public String getBizCl() {
		return bizCl;
	}

	/**
	 * @param bizCl
	 *            the bizCl to set
	 */
	public void setBizCl(String bizCl) {
		this.bizCl = bizCl;
	}

	/**
	 * @return the billMmTp
	 */
	public String getBillMmTp() {
		return billMmTp;
	}

	/**
	 * @param billMmTp
	 *            the billMmTp to set
	 */
	public void setBillMmTp(String billMmTp) {
		this.billMmTp = billMmTp;
	}

	/**
	 * @return the custTp
	 */
	public String getCustTp() {
		return custTp;
	}

	/**
	 * @param custTp
	 *            the custTp to set
	 */
	public void setCustTp(String custTp) {
		this.custTp = custTp;
	}

	/**
	 * @return the splyBizNo
	 */
	public String getSplyBizNo() {
		return splyBizNo;
	}

	/**
	 * @param splyBizNo
	 *            the splyBizNo to set
	 */
	public void setSplyBizNo(String splyBizNo) {
		this.splyBizNo = splyBizNo;
	}

	/**
	 * @return the bizRegNo
	 */
	public String getBizRegNo() {
		return bizRegNo;
	}

	/**
	 * @param bizRegNo
	 *            the bizRegNo to set
	 */
	public void setBizRegNo(String bizRegNo) {
		this.bizRegNo = bizRegNo;
	}

	/**
	 * @return the custNm
	 */
	public String getCustNm() {
		return custNm;
	}

	/**
	 * @param custNm
	 *            the custNm to set
	 */
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	/**
	 * @return the repNm
	 */
	public String getRepNm() {
		return repNm;
	}

	/**
	 * @param repNm
	 *            the repNm to set
	 */
	public void setRepNm(String repNm) {
		this.repNm = repNm;
	}

	/**
	 * @return the busiTp
	 */
	public String getBusiTp() {
		return busiTp;
	}

	/**
	 * @param busiTp
	 *            the busiTp to set
	 */
	public void setBusiTp(String busiTp) {
		this.busiTp = busiTp;
	}

	/**
	 * @return the busiCndt
	 */
	public String getBusiCndt() {
		return busiCndt;
	}

	/**
	 * @param busiCndt
	 *            the busiCndt to set
	 */
	public void setBusiCndt(String busiCndt) {
		this.busiCndt = busiCndt;
	}

	/**
	 * @return the taxTp
	 */
	public String getTaxTp() {
		return taxTp;
	}

	/**
	 * @param taxTp
	 *            the taxTp to set
	 */
	public void setTaxTp(String taxTp) {
		this.taxTp = taxTp;
	}

	/**
	 * @return the zipCd
	 */
	public String getZipCd() {
		return zipCd;
	}

	/**
	 * @param zipCd
	 *            the zipCd to set
	 */
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the rmrk
	 */
	public String getRmrk() {
		return rmrk;
	}

	/**
	 * @param rmrk
	 *            the rmrk to set
	 */
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}

	/**
	 * @return the splyAmt
	 */
	public Double getSplyAmt() {
		return splyAmt;
	}

	/**
	 * @param splyAmt
	 *            the splyAmt to set
	 */
	public void setSplyAmt(Double splyAmt) {
		this.splyAmt = splyAmt;
	}

	/**
	 * @return the vat
	 */
	public Double getVat() {
		return vat;
	}

	/**
	 * @param vat
	 *            the vat to set
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
	 * @param sct
	 *            the sct to set
	 */
	public void setSct(Double sct) {
		this.sct = sct;
	}

	/**
	 * @return the tbiFlg
	 */
	public String getTbiFlg() {
		return tbiFlg;
	}

	/**
	 * @param tbiFlg
	 *            the tbiFlg to set
	 */
	public void setTbiFlg(String tbiFlg) {
		this.tbiFlg = tbiFlg;
	}

	/**
	 * @return the taxDclrCl
	 */
	public String getTaxDclrCl() {
		return taxDclrCl;
	}

	/**
	 * @param taxDclrCl
	 *            the taxDclrCl to set
	 */
	public void setTaxDclrCl(String taxDclrCl) {
		this.taxDclrCl = taxDclrCl;
	}

	/**
	 * @return the regDate
	 */
	public Timestamp getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}

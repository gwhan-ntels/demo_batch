/**
 * @FileName
 * CBillComm.java
 * @Project
 * ccbs_batch
 * @Date
 * 2016. 5. 10.
 * @Writter
 * ntels_shlee
 * @EditHistory
 *
 * @Discript
 */
package com.ntels.ccbs.batch.iv.common.entity;
import java.sql.Timestamp;
import java.util.Date;
public class CBillWrkCom {
	
	
	private String billSeqNo; /* 청구일련번호 */
    private String chrgItmCd; /* 청구Process단위에서 관리되는 항목으로 약관에서 정의하는 기준의 요금을 관리 Sequence : CI99999999 */
    private String useYymm; /* 사용년월(YYYYMM) */
    private String prodCmpsId; /* 상품구성ID */
    private String svcCmpsId; /* 서비스구성ID */
    private String soId; /* 사업구분 */
	private Double wonBillAmt; /* 원화청구금액 */
	private Double adjAmt; /* 조정금액 */
	private Double adjPrvBillAmt; /* 조정전청구금액 */
	private Double billAmt; /* 청구금액 */
	private Double exrate; /* 환율 */
	
   
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
	 * @return the wonBillAmt
	 */
	public Double getWonBillAmt() {
		return wonBillAmt;
	}
	/**
	 * @param wonBillAmt the wonBillAmt to set
	 */
	public void setWonBillAmt(Double wonBillAmt) {
		this.wonBillAmt = wonBillAmt;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CBillWrkCom [billSeqNo=" + billSeqNo + ", chrgItmCd=" + chrgItmCd + ", useYymm=" + useYymm
				+ ", prodCmpsId=" + prodCmpsId + ", svcCmpsId=" + svcCmpsId + ", soId=" + soId + ", wonBillAmt="
				+ wonBillAmt + ", adjAmt=" + adjAmt + ", adjPrvBillAmt=" + adjPrvBillAmt + ", billAmt=" + billAmt
				+ ", exrate=" + exrate + "]";
	}
	
    
		
}

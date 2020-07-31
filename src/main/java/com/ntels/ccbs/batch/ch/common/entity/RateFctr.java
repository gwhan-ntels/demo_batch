package com.ntels.ccbs.batch.ch.common.entity;

public class RateFctr extends Multi{
	private String prodCd;
	private String fctrCd; /* 요소코드 */
	private String fctrRefTyp; /* 요소참조유형 */
	private String tableId; /* 테이블ID */
	private String colmnId; /* 컬럼ID */
	private String refCd; /* 공통참조코드 */
	private String fctrIn; 
	private String fctrOut; 

	public String getProdCd() {
		return prodCd;
	}
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	public String getFctrCd() {
		return fctrCd;
	}
	public void setFctrCd(String fctrCd) {
		this.fctrCd = fctrCd;
	}
	public String getFctrRefTyp() {
		return fctrRefTyp;
	}
	public void setFctrRefTyp(String fctrRefTyp) {
		this.fctrRefTyp = fctrRefTyp;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getColmnId() {
		return colmnId;
	}
	public void setColmnId(String colmnId) {
		this.colmnId = colmnId;
	}
	public String getRefCd() {
		return refCd;
	}
	public void setRefCd(String refCd) {
		this.refCd = refCd;
	}
	public String getFctrIn() {
		return fctrIn;
	}
	public void setFctrIn(String fctrIn) {
		this.fctrIn = fctrIn;
	}
	public String getFctrOut() {
		return fctrOut;
	}
	public void setFctrOut(String fctrOut) {
		this.fctrOut = fctrOut;
	}
	@Override
	public String toString() {
		return "RateFctr [prodCd=" + prodCd + ", fctrCd=" + fctrCd + ", fctrRefTyp=" + fctrRefTyp + ", tableId="
				+ tableId + ", colmnId=" + colmnId + ", refCd=" + refCd + "]";
	}

	public Object Clone() throws CloneNotSupportedException{
		return super.clone();
	}
}	
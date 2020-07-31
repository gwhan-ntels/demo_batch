package com.ntels.ccbs.batch.ch.common.entity;

public class RateInfo extends Multi{
	private String svcRateItmTypCd;
	private String prodCd;
	private String rateItmCd; /* 과금항목코드 */
	private String inactDt; /* 유효종료일자 */
	private String actDt; /* 유효시작일자 */
	private String fctrCd; /* 요소코드 */
	private Long fctrNo; /* 요소번호 */
	private String rateInfoExeFl;
	private String fctrRefTyp; /* 요소참조유형 */
	private String tableId; /* 테이블ID */
	private String colmnId; /* 컬럼ID */
	private String condOperator; /* 조건비교연산자 */
	private String oprndRefFl; /* 비교값참조여부 */
	//private String oprnd1; /* 비교값1 */
	//private String oprnd2; /*  */
	private String oprndFctrCd1;
	private String oprndFctrCd2;
	private String fctrVal1; /* 요소값1 */
	private String fctrVal2; /* 요소값2 */
	private String fctrVal3; /* 요소값3 */
	private String fctrVal4; /* 요소값4 */
	private String fctrVal5; /* 요소값5 */
	private String fctrVal6; /* 요소값6 */
	private String fctrVal7; /* 요소값7 */
	private String fctrVal8; /* 요소값8 */
	private String fctrVal9; /* 요소값9 */
	private String rateRefFl; /* 과금단가참조여부 */
	private Double rateVal; /* 과금단가 */
	private String crncyCd; /* 화폐코드 */
	private Long fctrCnt;
	private String disSvcRateItmTypCd;
	private Long ratePriNo;
	private String rateFctrCd;
	private Long diviRateAmt;
	private String qtyRefFl;
	private Long qty;
	private String qtyFctrCd;
	private String oprndCdA;
	private String oprndCdB;
	

	private String soId;
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}

	
	
	public String getChrgCtgry() {
		return chrgCtgry;
	}
	public void setChrgCtgry(String chrgCtgry) {
		this.chrgCtgry = chrgCtgry;
	}
	public String getOprndFctrCdA() {
		return oprndFctrCdA;
	}
	public void setOprndFctrCdA(String oprndFctrCdA) {
		this.oprndFctrCdA = oprndFctrCdA;
	}
	public String getOprndFctrCdB() {
		return oprndFctrCdB;
	}
	public void setOprndFctrCdB(String oprndFctrCdB) {
		this.oprndFctrCdB = oprndFctrCdB;
	}
	private String chrgCtgry;
	private String oprndFctrCdA;
	private String oprndFctrCdB;
	
	public String getSvcRateItmTypCd() {
		return svcRateItmTypCd;
	}
	public void setSvcRateItmTypCd(String svcRateItmTypCd) {
		this.svcRateItmTypCd = svcRateItmTypCd;
	}
	public String getProdCd() {
		return prodCd;
	}
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	public String getRateItmCd() {
		return rateItmCd;
	}
	public void setRateItmCd(String rateItmCd) {
		this.rateItmCd = rateItmCd;
	}
	public String getInactDt() {
		return inactDt;
	}
	public void setInactDt(String inactDt) {
		this.inactDt = inactDt;
	}
	public String getActDt() {
		return actDt;
	}
	public void setActDt(String actDt) {
		this.actDt = actDt;
	}
	public String getFctrCd() {
		return fctrCd;
	}
	public void setFctrCd(String fctrCd) {
		this.fctrCd = fctrCd;
	}
	public Long getFctrNo() {
		return fctrNo;
	}
	public void setFctrNo(Long fctrNo) {
		this.fctrNo = fctrNo;
	}
	public String getRateInfoExeFl() {
		return rateInfoExeFl;
	}
	public void setRateInfoExeFl(String rateInfoExeFl) {
		this.rateInfoExeFl = rateInfoExeFl;
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
	public String getCondOperator() {
		return condOperator;
	}
	public void setCondOperator(String condOperator) {
		this.condOperator = condOperator;
	}
	public String getOprndRefFl() {
		return oprndRefFl;
	}
	public void setOprndRefFl(String oprndRefFl) {
		this.oprndRefFl = oprndRefFl;
	}
/*	public String getOprnd1() {
		return oprnd1;
	}
	public void setOprnd1(String oprnd1) {
		this.oprnd1 = oprnd1;
	}
	public String getOprnd2() {
		return oprnd2;
	}
	public void setOprnd2(String oprnd2) {
		this.oprnd2 = oprnd2;
	}*/
	public String getOprndFctrCd1() {
		return oprndFctrCd1;
	}
	public void setOprndFctrCd1(String oprndFctrCd1) {
		this.oprndFctrCd1 = oprndFctrCd1;
	}
	public String getOprndFctrCd2() {
		return oprndFctrCd2;
	}
	public void setOprndFctrCd2(String oprndFctrCd2) {
		this.oprndFctrCd2 = oprndFctrCd2;
	}
	public String getFctrVal1() {
		return fctrVal1;
	}
	public void setFctrVal1(String fctrVal1) {
		this.fctrVal1 = fctrVal1;
	}
	public String getFctrVal2() {
		return fctrVal2;
	}
	public void setFctrVal2(String fctrVal2) {
		this.fctrVal2 = fctrVal2;
	}
	public String getFctrVal3() {
		return fctrVal3;
	}
	public void setFctrVal3(String fctrVal3) {
		this.fctrVal3 = fctrVal3;
	}
	public String getFctrVal4() {
		return fctrVal4;
	}
	public void setFctrVal4(String fctrVal4) {
		this.fctrVal4 = fctrVal4;
	}
	public String getFctrVal5() {
		return fctrVal5;
	}
	public void setFctrVal5(String fctrVal5) {
		this.fctrVal5 = fctrVal5;
	}
	public String getFctrVal6() {
		return fctrVal6;
	}
	public void setFctrVal6(String fctrVal6) {
		this.fctrVal6 = fctrVal6;
	}
	public String getFctrVal7() {
		return fctrVal7;
	}
	public void setFctrVal7(String fctrVal7) {
		this.fctrVal7 = fctrVal7;
	}
	public String getFctrVal8() {
		return fctrVal8;
	}
	public void setFctrVal8(String fctrVal8) {
		this.fctrVal8 = fctrVal8;
	}
	public String getFctrVal9() {
		return fctrVal9;
	}
	public void setFctrVal9(String fctrVal9) {
		this.fctrVal9 = fctrVal9;
	}
	public String getRateRefFl() {
		return rateRefFl;
	}
	public void setRateRefFl(String rateRefFl) {
		this.rateRefFl = rateRefFl;
	}
	public Double getRateVal() {
		return rateVal;
	}
	public void setRateVal(Double rateVal) {
		this.rateVal = rateVal;
	}
	public String getCrncyCd() {
		return crncyCd;
	}
	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}
	public Long getFctrCnt() {
		return fctrCnt;
	}
	public void setFctrCnt(Long fctrCnt) {
		this.fctrCnt = fctrCnt;
	}
	public String getDisSvcRateItmTypCd() {
		return disSvcRateItmTypCd;
	}
	public void setDisSvcRateItmTypCd(String disSvcRateItmTypCd) {
		this.disSvcRateItmTypCd = disSvcRateItmTypCd;
	}
	public Long getRatePriNo() {
		return ratePriNo;
	}
	public void setRatePriNo(Long ratePriNo) {
		this.ratePriNo = ratePriNo;
	}
	public String getRateFctrCd() {
		return rateFctrCd;
	}
	public void setRateFctrCd(String rateFctrCd) {
		this.rateFctrCd = rateFctrCd;
	}
	public Long getDiviRateAmt() {
		return diviRateAmt;
	}
	public void setDiviRateAmt(Long diviRateAmt) {
		this.diviRateAmt = diviRateAmt;
	}
	public String getQtyRefFl() {
		return qtyRefFl;
	}
	public void setQtyRefFl(String qtyRefFl) {
		this.qtyRefFl = qtyRefFl;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public String getQtyFctrCd() {
		return qtyFctrCd;
	}
	public void setQtyFctrCd(String qtyFctrCd) {
		this.qtyFctrCd = qtyFctrCd;
	}
	public String getOprndCdA() {
		return oprndCdA;
	}
	public void setOprndCdA(String oprndCdA) {
		this.oprndCdA = oprndCdA;
	}
	public String getOprndCdB() {
		return oprndCdB;
	}
	public void setOprndCdB(String oprndCdB) {
		this.oprndCdB = oprndCdB;
	}
	@Override
	public String toString() {
		return "RateInfo [svcRateItmTypCd=" + svcRateItmTypCd + ", prodCd="
				+ prodCd + ", rateItmCd=" + rateItmCd + ", inactDt=" + inactDt
				+ ", actDt=" + actDt + ", fctrCd=" + fctrCd + ", fctrNo="
				+ fctrNo + ", rateInfoExeFl=" + rateInfoExeFl + ", fctrRefTyp="
				+ fctrRefTyp + ", tableId=" + tableId + ", colmnId=" + colmnId
				+ ", condOperator=" + condOperator + ", oprndRefFl="
				+ oprndRefFl + ", oprndFctrCd1=" + oprndFctrCd1
				+ ", oprndFctrCd2=" + oprndFctrCd2 + ", fctrVal1=" + fctrVal1
				+ ", fctrVal2=" + fctrVal2 + ", fctrVal3=" + fctrVal3
				+ ", fctrVal4=" + fctrVal4 + ", fctrVal5=" + fctrVal5
				+ ", fctrVal6=" + fctrVal6 + ", fctrVal7=" + fctrVal7
				+ ", fctrVal8=" + fctrVal8 + ", fctrVal9=" + fctrVal9
				+ ", rateRefFl=" + rateRefFl + ", rateVal=" + rateVal
				+ ", crncyCd=" + crncyCd + ", fctrCnt=" + fctrCnt
				+ ", disSvcRateItmTypCd=" + disSvcRateItmTypCd + ", ratePriNo="
				+ ratePriNo + ", rateFctrCd=" + rateFctrCd + ", diviRateAmt="
				+ diviRateAmt + ", qtyRefFl=" + qtyRefFl + ", qty=" + qty
				+ ", qtyFctrCd=" + qtyFctrCd + ", oprndCdA=" + oprndCdA
				+ ", oprndCdB=" + oprndCdB + ", soId=" + soId + ", chrgCtgry="
				+ chrgCtgry + ", oprndFctrCdA=" + oprndFctrCdA
				+ ", oprndFctrCdB=" + oprndFctrCdB + "]";
	}
}	
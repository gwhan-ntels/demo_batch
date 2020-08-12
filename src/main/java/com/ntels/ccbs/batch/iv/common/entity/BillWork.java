package com.ntels.ccbs.batch.iv.common.entity;

import java.sql.Timestamp;

import com.ntels.ccbs.batch.common.entity.MultiSeq;

/**
 * TBLIV_BILL_WRK 대응 도메인 클래스
 * 
 * @author Cashyalla
 *
 */
public class BillWork extends MultiSeq {

	/* 테이블 컬럼 대응 데이터 필드 시작 */
	/**
	 * BILL_SEQ_NO
	 */
	private String billSeqNo;

	/**
	 * USE_YYMM
	 */
	private String useYymm;

	/**
	 * PROD_CMPS_ID
	 */
	private String prodCmpsId;

	/**
	 * SVC_CMPS_ID
	 */
	private String svcCmpsId;

	/**
	 * CHRG_ITM_CD
	 */
	private String chrgItmCd;

	/**
	 * BILL_YYMM
	 */
	private String billYymm;

	/**
	 * BILL_CYCL
	 */
	private String billCycl;

	/**
	 * BILL_DT
	 */
	private String billDt;

	/**
	 * SO_ID
	 */
	private String soId;

	/**
	 * GRP_ID
	 */
	private String grpId;

	/**
	 * PYM_ACNT_ID
	 */
	private String pymAcntId;

	/**
	 * CUST_ID
	 */
	private String custId;

	/**
	 * CTRT_ID
	 */
	private String ctrtId;

	/**
	 * PROD_CD
	 */
	private String prodCd;

	/**
	 * SVC_CD
	 */
	private String svcCd;

	/**
	 * BIZ_CL
	 */
	private String bizCl;

	/**
	 * PROD_GRP
	 */
	private String prodGrp;

	/**
	 * SVC_GRP
	 */
	private String svcGrp;

	/**
	 * BILL_MM_TP
	 */
	private String billMmTp;

	/**
	 * SALE_TP
	 */
	private String saleTp;

	/**
	 * USG_CNT
	 */
	private Integer usgCnt;

	private Integer usgQty;

	/**
	 * 사용량
	 */
	private Long usgAmt;

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
	 * PAY_DUE_DT
	 */
	private String payDueDt;

	/**
	 * 수납금액
	 */
	private Double rcptAmt;

	/**
	 * FULL_PAY_YN
	 */
	private String fullPayYn;

	/**
	 * ATRT_CORP_ID
	 */
	private String atrtCorpId;

	/**
	 * ATRT_EMP_ID
	 */
	private String atrtEmpId;

	/**
	 * 원화청구금액
	 */
	private Double wonBillAmt;

	/**
	 * CRNCY_CD
	 */
	private String crncyCd;

	/**
	 * EXRATE
	 */
	private Double exrate;

	/**
	 * EXRATE_APLY_DT
	 */
	private String exrateAplyDt;

	/**
	 * SALE_ITM_CD
	 */
	private String saleItmCd;

	/**
	 * PREPAY_OCC_SEQ_NO
	 */
	private String prepayOccSeqNo;

	/**
	 * PREPD_CTRT_ID
	 */
	private String prepdCtrtId;

	/**
	 * REG_DATE
	 */
	private Timestamp regDate;

	/**
	 * CHG_DATE
	 */
	private Timestamp chgDate;

	/**
	 * DEBT_PROC_YN
	 */
	private String debtProcYn;

	/* 테이블 컬럼 대응 데이터 필드 끝 */

	/* 테이블 컬럼 외 데이터 필드 시작 */
	private String clcWrkNo;
	private String exchRateAppDt;
	private String oldBillSeqNo;
	private String regrId;
	private String chgrId;
	private String adjNo;
	/* 테이블 컬럼 외 데이터 필드 끝 */

	private Double adjApplAmt;

	private String inptMenuId;

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

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getPymAcntId() {
		return pymAcntId;
	}

	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCtrtId() {
		return ctrtId;
	}

	public void setCtrtId(String ctrtId) {
		this.ctrtId = ctrtId;
	}

	public String getProdCd() {
		return prodCd;
	}

	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}

	public String getSvcCd() {
		return svcCd;
	}

	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}

	public String getBizCl() {
		return bizCl;
	}

	public void setBizCl(String bizCl) {
		this.bizCl = bizCl;
	}

	public String getProdGrp() {
		return prodGrp;
	}

	public void setProdGrp(String prodGrp) {
		this.prodGrp = prodGrp;
	}

	public String getSvcGrp() {
		return svcGrp;
	}

	public void setSvcGrp(String svcGrp) {
		this.svcGrp = svcGrp;
	}

	public String getBillMmTp() {
		return billMmTp;
	}

	public void setBillMmTp(String billMmTp) {
		this.billMmTp = billMmTp;
	}

	public String getSaleTp() {
		return saleTp;
	}

	public void setSaleTp(String saleTp) {
		this.saleTp = saleTp;
	}

	public Integer getUsgCnt() {
		return usgCnt;
	}

	public void setUsgCnt(Integer usgCnt) {
		this.usgCnt = usgCnt;
	}

	public Integer getUsgQty() {
		return usgQty;
	}

	public void setUsgQty(Integer usgQty) {
		this.usgQty = usgQty;
	}

	public Long getUsgAmt() {
		return usgAmt;
	}

	public void setUsgAmt(Long usgAmt) {
		this.usgAmt = usgAmt;
	}

	public Double getAdjPrvBillAmt() {
		return adjPrvBillAmt;
	}

	public void setAdjPrvBillAmt(Double adjPrvBillAmt) {
		this.adjPrvBillAmt = adjPrvBillAmt;
	}

	public Double getAdjAmt() {
		return adjAmt;
	}

	public void setAdjAmt(Double adjAmt) {
		this.adjAmt = adjAmt;
	}

	public Double getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(Double billAmt) {
		this.billAmt = billAmt;
	}

	public String getPayDueDt() {
		return payDueDt;
	}

	public void setPayDueDt(String payDueDt) {
		this.payDueDt = payDueDt;
	}

	public Double getRcptAmt() {
		return rcptAmt;
	}

	public void setRcptAmt(Double rcptAmt) {
		this.rcptAmt = rcptAmt;
	}

	public String getFullPayYn() {
		return fullPayYn;
	}

	public void setFullPayYn(String fullPayYn) {
		this.fullPayYn = fullPayYn;
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

	public Double getWonBillAmt() {
		return wonBillAmt;
	}

	public void setWonBillAmt(Double wonBillAmt) {
		this.wonBillAmt = wonBillAmt;
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

	public String getSaleItmCd() {
		return saleItmCd;
	}

	public void setSaleItmCd(String saleItmCd) {
		this.saleItmCd = saleItmCd;
	}

	public String getPrepayOccSeqNo() {
		return prepayOccSeqNo;
	}

	public void setPrepayOccSeqNo(String prepayOccSeqNo) {
		this.prepayOccSeqNo = prepayOccSeqNo;
	}

	public String getPrepdCtrtId() {
		return prepdCtrtId;
	}

	public void setPrepdCtrtId(String prepdCtrtId) {
		this.prepdCtrtId = prepdCtrtId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Timestamp getChgDate() {
		return chgDate;
	}

	public void setChgDate(Timestamp chgDate) {
		this.chgDate = chgDate;
	}

	public String getDebtProcYn() {
		return debtProcYn;
	}

	public void setDebtProcYn(String debtProcYn) {
		this.debtProcYn = debtProcYn;
	}

	public String getClcWrkNo() {
		return clcWrkNo;
	}

	public void setClcWrkNo(String clcWrkNo) {
		this.clcWrkNo = clcWrkNo;
	}

	public String getExchRateAppDt() {
		return exchRateAppDt;
	}

	public void setExchRateAppDt(String exchRateAppDt) {
		this.exchRateAppDt = exchRateAppDt;
	}

	public String getOldBillSeqNo() {
		return oldBillSeqNo;
	}

	public void setOldBillSeqNo(String oldBillSeqNo) {
		this.oldBillSeqNo = oldBillSeqNo;
	}

	public String getRegrId() {
		return regrId;
	}

	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}

	public String getChgrId() {
		return chgrId;
	}

	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}

	public String getAdjNo() {
		return adjNo;
	}

	public void setAdjNo(String adjNo) {
		this.adjNo = adjNo;
	}

	public String getInptMenuId() {
		return inptMenuId;
	}

	public void setInptMenuId(String inptMenuId) {
		this.inptMenuId = inptMenuId;
	}

	public Double getAdjApplAmt() {
		return adjApplAmt;
	}

	public void setAdjApplAmt(Double adjApplAmt) {
		this.adjApplAmt = adjApplAmt;
	}

}

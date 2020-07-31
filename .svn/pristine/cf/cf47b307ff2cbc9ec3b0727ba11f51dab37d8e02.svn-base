package com.ntels.ccbs.batch.iv.common.entity;

import java.sql.Timestamp;

import com.ntels.ccbs.batch.common.entity.MultiSeq;

public class BillSpltPym extends MultiSeq {

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
	 * 청구Process단위에서 관리되는 항목으로 약관에서 정의하는 기준의 요금을 관리 Sequence : CI99999999
	 */
	private String chrgItmCd;

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
	 * 사용건수
	 */
	private long usgCnt;

	/**
	 * 사용량
	 */
	private long usgAmt;

	/**
	 * 조정전청구금액
	 */
	private double adjPrvBillAmt;

	/**
	 * 조정금액
	 */
	private double adjAmt;

	/**
	 * 청구금액
	 */
	private double billAmt;

	/**
	 * 납기마감일자(YYYYMMDD)
	 */
	private String payDueDt;

	/**
	 * 1: 금액분리 2: %분리 3: 항목유형별 분리 4: 항목분리
	 */
	private String spltPymTp;

	/**
	 * 요금영역(PP00201) N: 일회성 R: 정액 U: 종량 A: 공제 M: 감면 D: 할인
	 */
	private String chrgCtgry;

	/**
	 * 청구Process단위에서 관리되는 항목으로 약관에서 정의하는 기준의 요금을 관리 Sequence : CI99999999
	 */
	private String spltChrgItmCd;

	/**
	 * MSO, SO, RO, 협력업체의 ID
	 */
	private String atrtCorpId;

	/**
	 * MSO, SO, RO, 협력업체의 사원ID
	 */
	private String atrtEmpId;

	/**
	 * 원화청구금액
	 */
	private double wonBillAmt;

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
	 * 등록일
	 */
	private Timestamp regDate;

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

	public long getUsgCnt() {
		return usgCnt;
	}

	public void setUsgCnt(long usgCnt) {
		this.usgCnt = usgCnt;
	}

	public long getUsgAmt() {
		return usgAmt;
	}

	public void setUsgAmt(long usgAmt) {
		this.usgAmt = usgAmt;
	}

	public double getAdjPrvBillAmt() {
		return adjPrvBillAmt;
	}

	public void setAdjPrvBillAmt(double adjPrvBillAmt) {
		this.adjPrvBillAmt = adjPrvBillAmt;
	}

	public double getAdjAmt() {
		return adjAmt;
	}

	public void setAdjAmt(double adjAmt) {
		this.adjAmt = adjAmt;
	}

	public double getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(double billAmt) {
		this.billAmt = billAmt;
	}

	public String getPayDueDt() {
		return payDueDt;
	}

	public void setPayDueDt(String payDueDt) {
		this.payDueDt = payDueDt;
	}

	public String getSpltPymTp() {
		return spltPymTp;
	}

	public void setSpltPymTp(String spltPymTp) {
		this.spltPymTp = spltPymTp;
	}

	public String getChrgCtgry() {
		return chrgCtgry;
	}

	public void setChrgCtgry(String chrgCtgry) {
		this.chrgCtgry = chrgCtgry;
	}

	public String getSpltChrgItmCd() {
		return spltChrgItmCd;
	}

	public void setSpltChrgItmCd(String spltChrgItmCd) {
		this.spltChrgItmCd = spltChrgItmCd;
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

	public double getWonBillAmt() {
		return wonBillAmt;
	}

	public void setWonBillAmt(double wonBillAmt) {
		this.wonBillAmt = wonBillAmt;
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

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}

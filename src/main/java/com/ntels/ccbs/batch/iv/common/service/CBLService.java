package com.ntels.ccbs.batch.iv.common.service;

import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;

public interface CBLService {

	/**
	 * 청구일자를 조회한다.
	 * @return
	 */
	String getCyclBillDt(BillCyclStp billCyclStp);
	
	/**
	 * 납기일자를 조회한다.
	 * @return
	 */
	String getCyclPayDueDt(BillCyclStp billCyclStp);
	
	/**
	 * 공급자사업자번호를 조회한다.
	 * @param billCyclStp
	 * @return
	 */
	String getSupplyBizNo(BillCyclStp billCyclStp);
	
	/**
	 * 부가세율을 조회한다.
	 * @param billCyclStp
	 * @return
	 */
	String getVatRate(BillCyclStp billCyclStp);
	
	/**
	 * 세금계산서 품목(적요)을 조회한다.
	 * @param billCyclStp
	 * @return
	 */
	String getRemark(BillCyclStp billCyclStp);

	String getTaxIssDt(BillCyclStp billCyclStp);
	
	/**
	 * ISP정보 Tim no를 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	String getIspInfoTimNo(BillCyclStp billCyclStp);
	
	/**
	 * ISP정보 사업자 명을 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	String getIspInfoName(BillCyclStp billCyclStp);
	
	/**
	 * ISP정보 주소를 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	String getIspInfoAddress(BillCyclStp billCyclStp);
	
	/**
	 * ISP정보 url을 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	String getIspInfoUrl(BillCyclStp billCyclStp);
	
	/**
	 * ISP정보 사업자 전화번호를 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	String getIspInfoTel(BillCyclStp billCyclStp);
	
	/**
	 * ISP정보 Fax를 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	String getIspInfoFax(BillCyclStp billCyclStp);
	
	/**
	 * ISP정보 Email을 검색한다.
	 * @param billCyclStp
	 * @return
	 */
	String getIspInfoEmail(BillCyclStp billCyclStp);
	
	/**
	 * 환율 적용일자 조회
	 * @return
	 */
	String getExrateAplyDt();
	
	String getCyclExrateAplyDt(BillCyclStp billCyclStp) ;
	
	
}

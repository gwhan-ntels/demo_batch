package com.ntels.ccbs.batch.iv.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.TaxBill;
import com.ntels.ccbs.batch.iv.common.entity.TaxTarget;

@Component
public interface NBlivb01m09Mapper {

	/**
	 * 
	 * @param billSeqNo
	 * @param soId
	 * @return
	 */
	int getTaxCount(@Param("billSeqNo") String billSeqNo, @Param("soId") String soId);
	
	/**
	 * 
	 * @param billYymm
	 * @param billCycl
	 * @param billDt
	 * @param soId
	 * @param payDueDt
	 * @return
	 */
	List<TaxTarget> getBillInfoForCalcTax(@Param("billYymm") String billYymm
											, @Param("billCycl") String billCycl
											, @Param("billDt") String billDt
											, @Param("soId") String soId
											, @Param("payDueDt") String payDueDt);
	
	
	List<TaxTarget> getBillInfoForCalcTax2(@Param("billYymm") String billYymm
											, @Param("useYymm") String useYymm
											, @Param("billCycl") String billCycl
											, @Param("billDt") String billDt
											, @Param("soId") String soId
											, @Param("payDueDt") String payDueDt);

	/**
	 * 
	 * @param billSeqNo
	 * @param soId
	 * @return
	 */
	List<TaxTarget> getBillInfoForCalcTaxByBillSeqNo(@Param("billSeqNo") String billSeqNo
														,@Param("soId") String soId);
	
	/**
	 * 
	 * @param billSeqNo
	 * @param billYymm
	 * @param soId
	 * @return
	 */
	List<TaxTarget> getBillInfoForCalcTaxByBillSeqNo2(@Param("billSeqNo") String billSeqNo
														,@Param("billYymm") String billYymm
														,@Param("soId") String soId);
	
	int insertBillWrk(CBillComm bill);
	
	int insertTaxBill(TaxBill taxBillList);
	
	List<TaxTarget> getBillForTaxCalcFromSimulationBillByBillSeqNo(@Param("bill") CBillComm bill);
	
	String getRoundingAdjSvcRateItmTypCd(String soId);
	
}

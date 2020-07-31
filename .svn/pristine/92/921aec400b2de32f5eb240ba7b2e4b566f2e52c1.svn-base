package com.ntels.ccbs.batch.iv.dao.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;

public interface NBlivd51m00Mapper {

	Double getRcptAmt(@Param("bill") CBillComm bill);
	
	int updateAdjAplyDcsnProcStat(@Param("chgDate") Timestamp chgDate, @Param("adjNo") String adjNo);
	
	List<Receipt> getPymSeqNo(@Param("receipt") Receipt receipt);
	
	List<PrepayOcc> getPrepayOccInfo(@Param("prepayOcc") PrepayOcc prepayOcc);
	
	int deleteRcpt(@Param("receipt") Receipt receipt);
	
	int deleteRcptDtl(@Param("receipt") Receipt receipt);
	
	int deletePrepayOcc(@Param("prepayOcc") PrepayOcc prepayOcc);
	
	int updateBillNotPaid(@Param("bill") CBillComm bill);
	
	int updateBillMastNotPaid(@Param("bill") CBillComm bill);
	
	List<CBillComm> getAdjBillInfo(@Param("bill") CBillComm bill);
	
	int updateBillAdjAmt(@Param("bill") CBillComm bill);
	
	int updateBillMastAdjAmt(@Param("bill") CBillComm bill);
	
	List<CBillComm> getAdjVatList1(@Param("bill") CBillComm bill);
	
	List<CBillComm> getAdjVatList2(@Param("bill") CBillComm bill);

	int updateBillAdjVat(@Param("bill") CBillComm bill);

	int updateBillMastAdjVat(@Param("bill") CBillComm bill);
	
	Integer getReIssSeq(@Param("billSeqNo") String billSeqNo);
	
	double getTotBillAmtWithoutRounding(CBillComm bill);
	
	boolean existsRoundingAdjustment(CBillComm bill);
	
	int updateRoundingAdjustment(CBillComm bill);
	
}

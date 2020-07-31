package com.ntels.ccbs.batch.iv.dao;

import java.sql.Timestamp;
import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.AdjBill;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.ChrgAdjAply;
import com.ntels.ccbs.batch.iv.common.entity.SaleAdj;
import com.ntels.ccbs.batch.iv.common.entity.TaxTarget;
import com.ntels.ccbs.batch.iv.common.service.BillRePbls;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;

public interface NBlivd51m00Dao {

	List<AdjBill> getAdjBillList(AdjBill adjBill);
	
	Double getRcptAmt(CBillComm bill);
	
	int updateAdjAplyDcsnProcStat(Timestamp chgDate, String adjNo);
	
	List<Receipt> getPymSeqNo(Receipt receipt);
	
	List<PrepayOcc> getPrepayOccInfo(PrepayOcc prepayOcc);
	
	int deleteRcpt(Receipt receipt);
	
	int deleteRcptDtl(Receipt receipt);
	
	int deletePrepayOcc(PrepayOcc prepayOcc);
	
	int updateBillNotPaid(CBillComm bill);
	
	int updateBillMastNotPaid(CBillComm bill);
	
	List<CBillComm> getAdjBillInfo(CBillComm bill);
	
	int updateBillAdjAmt(CBillComm bill);
	
	int updateBillMastAdjAmt(CBillComm bill);
	
	List<TaxTarget> getAdjTaxList(CBillComm bill);
	
	TaxTarget getPrevAdjTax(CBillComm bill);
	
	int updateBillAdjVat(CBillComm bill);
	
	int updateBillMastAdjVat(CBillComm bill);
	
	int deleteTaxBill(CBillComm bill);
	
	List<SaleAdj> getSaleAdjList(CBillComm bill);
	
	int insertSaleAdj(List<SaleAdj> saleAdjList);
	
	List<SaleAdj> getSaleBillAdj(CBillComm bill);
	
	Integer getReIssSeq(String billSeqNo);
	
	int insertBillRePbls(BillRePbls billRePbls);
	
	int updateChrgAdjAply(ChrgAdjAply chrgAdjAply);
	
	int updateCmctRfndAcntInfo(CBillComm bill);
	
	double getTotBillAmtWithoutRounding(CBillComm bill);
	
	boolean existsRoundingAdjustment(CBillComm bill);
	
	int updateRoundingAdjustment(CBillComm bill);
	
}

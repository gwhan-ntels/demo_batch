package com.ntels.ccbs.batch.iv.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.JdbcDao;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.AdjBill;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.ChrgAdjAply;
import com.ntels.ccbs.batch.iv.common.entity.SaleAdj;
import com.ntels.ccbs.batch.iv.common.entity.TaxTarget;
import com.ntels.ccbs.batch.iv.common.service.BillRePbls;
import com.ntels.ccbs.batch.iv.dao.mapper.NBlivd51m00Mapper;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;

@Repository
public class NBlivd51m00DaoImpl extends JdbcDao implements NBlivd51m00Dao {

	@Autowired
	private NBlivd51m00Mapper nBlivd51m00Mapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}
	
	@Value("${dbms.kind}")
	String dbKind;	

	@Override
	protected String getMapperName() {
		return "NBlivd51m00Mapper";
	}
	
	@Override
	public List<AdjBill> getAdjBillList(AdjBill adjBill) {
		return getList(dbKind, "getAdjBillList", AdjBill.class, adjBill);
	}
	
	@Override
	public Double getRcptAmt(CBillComm bill) {
		CBillComm cbc; 
		cbc = getOne(dbKind, "getRcptAmt", CBillComm.class , bill);	
		return cbc.getRcptAmt();
//		return nBlivd51m00Mapper.getRcptAmt(bill);
	}
	
	@Override
	public int updateAdjAplyDcsnProcStat(Timestamp chgDate, String adjNo) {
		return nBlivd51m00Mapper.updateAdjAplyDcsnProcStat(chgDate, adjNo);
	}
	
	@Override
	public List<Receipt> getPymSeqNo(Receipt receipt) {
		return getList(dbKind, "getPymSeqNo", Receipt.class, receipt);
	}
	
	@Override
	public List<PrepayOcc> getPrepayOccInfo(PrepayOcc prepayOcc) {
		return getList(dbKind, "getPrepayOccInfo", PrepayOcc.class, prepayOcc);
	}
	
	@Override
	public int deleteRcpt(Receipt receipt) {
		return deleteOne(dbKind,"deleteRcpt", receipt);
	}
	
	@Override
	public int deleteRcptDtl(Receipt receipt) {
		return deleteOne(dbKind,"deleteRcptDtl", receipt);
	}
	
	@Override
	public int deletePrepayOcc(PrepayOcc prepayOcc) {
		return deleteOne(dbKind,"deletePrepayOcc", prepayOcc);
	}
	
	@Override
	public int updateBillNotPaid(CBillComm bill) {
		return updateOne(dbKind,"updateBillNotPaid", bill);
	}
	
	@Override
	public int updateBillMastNotPaid(CBillComm bill) {
		return updateOne(dbKind,"updateBillMastNotPaid", bill);
	}
	
	@Override
	public List<CBillComm> getAdjBillInfo(CBillComm bill) {
		return getList(dbKind,"getAdjBillInfo", CBillComm.class, bill);
	}
	
	@Override
	public int updateBillAdjAmt(CBillComm bill) {
		return updateOne(dbKind,"updateBillAdjAmt", bill);
	}
	
	@Override
	public int updateBillMastAdjAmt(CBillComm bill) {
		return updateOne(dbKind,"updateBillMastAdjAmt", bill);
	}
	
	@Override
	public List<TaxTarget> getAdjTaxList(CBillComm bill) {
		return getList(dbKind,"getAdjTaxList", TaxTarget.class, bill);
	}
	
	@Override
	public TaxTarget getPrevAdjTax(CBillComm bill) {
		return getOne(dbKind,"getPrevAdjTax", TaxTarget.class, bill);
	}
	
	@Override
	public int updateBillAdjVat(CBillComm bill) {
		return updateOne(dbKind, "updateBillAdjVat", bill);
	}
	
	@Override
	public int updateBillMastAdjVat(CBillComm bill) {
		return updateOne(dbKind, "updateBillMastAdjVat", bill);
	}
	
	@Override
	public int deleteTaxBill(CBillComm bill) {
		return deleteOne(dbKind, "deleteTaxBill", bill);
	}
	
	@Override
	public List<SaleAdj> getSaleAdjList(CBillComm bill) {
		return getList(dbKind, "getSaleAdjList", SaleAdj.class, bill);
	}
	
	@Override
	public int insertSaleAdj(List<SaleAdj> saleAdjList) {
		return insert(dbKind, "insertSaleAdj", SaleAdj.class, saleAdjList);
	}
	
	@Override
	public List<SaleAdj> getSaleBillAdj(CBillComm bill) {
		return getList(dbKind, "getSaleBillAdj", SaleAdj.class, bill);
	}
	
	@Override
	public Integer getReIssSeq(String billSeqNo) {
		return nBlivd51m00Mapper.getReIssSeq(billSeqNo);
	}
	
	@Override
	public int insertBillRePbls(BillRePbls billRePbls) {
		return insertOne(dbKind, "insertBillRePbls", billRePbls);
	}
	
	@Override
	public int updateChrgAdjAply(ChrgAdjAply chrgAdjAply) {
		return updateOne(dbKind, "updateChrgAdjAply", chrgAdjAply);
	}
	
	@Override
	public int updateCmctRfndAcntInfo(CBillComm bill) {
		return updateOne(dbKind, "updateCmctRfndAcntInfo", bill);
	}
	
	@Override
	public double getTotBillAmtWithoutRounding(CBillComm bill) {
//		return nBlivd51m00Mapper.getTotBillAmtWithoutRounding(bill);

		CBillComm cbc =  getOne(dbKind, "getTotBillAmtWithoutRounding", CBillComm.class, bill);
		return cbc.getBillAmt();
//		return getOne(dbKind, "getTotBillAmtWithoutRounding", Double.class, bill);
	}
	
	@Override
	public boolean existsRoundingAdjustment(CBillComm bill) {
//		return nBlivd51m00Mapper.existsRoundingAdjustment(bill);

		return getOne(dbKind, "existsRoundingAdjustment", CBillComm.class, bill);
	}
	
	@Override
	public int updateRoundingAdjustment(CBillComm bill) {
//		return nBlivd51m00Mapper.updateRoundingAdjustment(bill);

		return updateOne(dbKind, "updateRoundingAdjustment", bill);
	}
	
}

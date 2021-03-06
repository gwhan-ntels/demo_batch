package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.dao.mapper.NBlivb01m08Mapper;

@Repository
public class NBlivb01m08DaoImpl extends LazyLoadingDao implements NBlivb01m08Dao {

	@Autowired
	private NBlivb01m08Mapper nBlivb01m08Mapper;

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Value("${dbms.kind}")
	String dbKind;

	@Override
	protected String getMapperName() {
		return "NBlivb01m08Mapper";
	}
	
	@Override
	public LazyLoader<BillWork> getAdjInfoBeforeBillListLazy(BillWork billWork) {
		return getLazyLoader(dbKind, "getAdjInfoBeforeBillList", BillWork.class, billWork);
	}	

	@Override
	public int updateAdjBeforeBill(List<BillWork> billWorkList) {
		return update(dbKind, "updateAdjBeforeBill", BillWork.class, billWorkList);
//		return update("updateAdjBeforeBill", BillWork.class, billWorkList);
	}

	@Override
	public int updateAdjAply(List<BillWork> billWorkList) {
		return update(dbKind, "updateAdjAply", BillWork.class, billWorkList);
//		return update("updateAdjAply", BillWork.class, billWorkList);
	}
	


	@Override
	public LazyLoader<BillWork> getAdjInfoAfterBillListLazy(BillWork billWork) {
		return getLazyLoader(dbKind, "getAdjInfoAfterBillList", BillWork.class, billWork);
	}
	

	@Override
	public BillWork getBillWorkForAdj(BillWork billWork) {
		return getOne(dbKind, "getBillWorkForAdj", BillWork.class, billWork);
//		return nBlivb01m08Mapper.getBillWorkForAdj(billWork);
	}
	
	@Override
	public int insertBillWork(List<BillWork> billWorkList) {
		return insert(dbKind, "insertBillWork", BillWork.class, billWorkList);
//		return insert("insertBillWork", BillWork.class, billWorkList); // 원래원형
	}
	
	@Override
	public int updateAdjAfterBill(List<BillWork> billWorkList) {
		return update(dbKind, "updateAdjAfterBill", BillWork.class, billWorkList);
//		return update("updateAdjAfterBill", BillWork.class, billWorkList);
	}
	

//	@Override
//	public List<BillWork> getChInfoListForBillWork(BillWork billWork) {
//		return getList(dbKind, "getChInfoListForBillWork", BillWork.class, billWork);
////		return nBlivb01m08Mapper.getChInfoListForBillWork(billWork);
//	}
//
//	@Override
//	public LazyLoader<BillWork> getChInfoListForBillWorkLazy(BillWork billWork) {
//		return getLazyLoader(dbKind, "getChInfoListForBillWork", BillWork.class, billWork);
//	}
//
//
//	@Override
//	public List<BillWork> getAutoTransferDiscountInfoList(BillWork billWork) {
//		return getList(dbKind, "getAutoTransferDiscountInfoList", BillWork.class, billWork);
////		return nBlivb01m08Mapper.getAutoTransferDiscountInfoList(billWork);
//	}
//
//	@Override
//	public LazyLoader<BillWork> getAutoTransferDiscountInfoListLazy(BillWork billWork) {
//		return getLazyLoader(dbKind, "getAutoTransferDiscountInfoList", BillWork.class, billWork);
//	}
//

//	@Override
//	public List<BillWork> getAdjInfoBeforeBillList(BillWork billWork) {
//		return getList(dbKind, "getAdjInfoAfterBillList", BillWork.class, billWork);
//		// return nBlivb01m08Mapper.getAdjInfoAfterBillList(billWork);
//	}
//	@Override
//	public List<BillWork> getAdjInfoAfterBillList(BillWork billWork) {
//		return getList(dbKind, "getAdjInfoAfterBillList", BillWork.class, billWork);
////		return nBlivb01m08Mapper.getAdjInfoAfterBillList(billWork);
//	}

}

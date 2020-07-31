package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.BillInvoice;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

@Repository
public class NBlivb01m18DaoImpl extends LazyLoadingDao implements NBlivb01m18Dao {

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlivb01m18Mapper";
	}
	
	@Value("${dbms.kind}")
	String dbKind;
	
	@Override
	public int deletePrevData(CBillComm cBillComm) {
		return deleteOne(dbKind, "deletePrevData", cBillComm);
	}
	
	@Override
	public LazyLoader<BillInvoice> getBillInvoiceInfo(CBillComm cBillComm) {
		return getLazyLoader(dbKind, "getBillInvoiceInfo", BillInvoice.class, cBillComm);
	}
	
	@Override
	public int insertBillInvoice(List<BillInvoice> billInvoiceList) {
		return insert(dbKind, "insertBillInvoice", BillInvoice.class, billInvoiceList);
	}
	
	@Override
	public LazyLoader<BillInvoice> getBillInvoiceListForPrint(BillInvoice billInvoice) {
		return getLazyLoader(dbKind, "getBillInvoiceListForPrint", BillInvoice.class, billInvoice);
	}
	
	@Override
	public LazyLoader<BillInvoice> getBillInvoiceListForEml(BillInvoice billInvoice) {
		return getLazyLoader(dbKind, "getBillInvoiceListForEml", BillInvoice.class, billInvoice);
	}

}

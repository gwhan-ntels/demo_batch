package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrintCt;
import com.ntels.ccbs.batch.iv.common.entity.PrintCustInfo;

@Repository
public class NBlivb01m17DaoImpl extends LazyLoadingDao implements NBlivb01m17Dao {

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlivb01m17Mapper";
	}
	
	@Value("${dbms.kind}")
	String dbKind;
	
	@Override
	public LazyLoader<PrintCustInfo> getPrintCustInfo(CBillComm bill) {
		return getLazyLoader(dbKind, "getPrintCustInfo", PrintCustInfo.class, bill);
	}

	@Override
	public LazyLoader<PrintCt> getPrintCt(CBillComm bill) {
		return getLazyLoader(dbKind, "getPrintCt", PrintCt.class, bill);
	}
	
	@Override
	public int insertPrintCustInfo(List<PrintCustInfo> printCustInfoList) {
		return insert(dbKind, "insertPrintCustInfo", PrintCustInfo.class, printCustInfoList);
	}
	
	@Override
	public int insertPrintCt(List<PrintCt> printCtList) {
		return insert(dbKind, "insertPrintCt", PrintCt.class, printCtList);
	}

}

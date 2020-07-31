package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrintCt;
import com.ntels.ccbs.batch.iv.common.entity.PrintCustInfo;

public interface NBlivb01m17Dao {

	LazyLoader<PrintCustInfo> getPrintCustInfo(CBillComm bill);
	
	LazyLoader<PrintCt> getPrintCt(CBillComm bill);
	
	int insertPrintCustInfo(List<PrintCustInfo> printCustInfoList);
	
	int insertPrintCt(List<PrintCt> printCtList);
	
}

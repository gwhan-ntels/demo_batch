package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.Invoice;
import com.ntels.ccbs.batch.iv.common.entity.PrintCt;
import com.ntels.ccbs.batch.iv.common.entity.PrintCustInfo;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface NBlivb01m17Service {

	LazyLoader<PrintCustInfo> getPrintCustInfo(CBillComm bill);
	
	LazyLoader<PrintCt> getPrintCt(CBillComm bill);
	
	int insertPrintCustInfo(List<PrintCustInfo> printCustInfoList);
	
	int insertPrintCt(List<PrintCt> printCtList);

}

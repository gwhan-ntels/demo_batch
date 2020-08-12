package com.ntels.ccbs.batch.iv.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.common.entity.BillWork;

@Component
public interface NBlivb01m08Mapper {
	List<BillWork> getAdjInfoBeforeBillList(BillWork billWork);

	List<BillWork> getAdjInfoAfterBillList(BillWork billWork);
	BillWork getBillWorkForAdj(BillWork billWork);
	int insertBillWork(BillWork billWork);
	

//	List<BillWork> getChInfoListForBillWork(BillWork billWork);
//	List<BillWork> getAutoTransferDiscountInfoList(BillWork billWork);

}

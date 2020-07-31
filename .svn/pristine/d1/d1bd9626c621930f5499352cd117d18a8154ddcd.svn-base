package com.ntels.ccbs.batch.iv.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.common.entity.BillWork;

@Component
public interface GenerateBillWorkMapper {

	List<BillWork> getChInfoListForBillWork(BillWork billWork);
	
	int insertBillWork(BillWork billWork);
	
	List<BillWork> getAutoTransferDiscountInfoList(BillWork billWork);
	
	List<BillWork> getAdjInfoAfterBillList(BillWork billWork);
	
	List<BillWork> getAdjInfoBeforeBillList(BillWork billWork);
	
	BillWork getBillWorkForAdj(BillWork billWork);

}

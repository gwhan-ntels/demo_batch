package com.ntels.ccbs.batch.iv.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.common.entity.BillTgtCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;

@Component
public interface NBlivb01m15Mapper {

	List<OverpayBillAply> getOverpayBillAplyList(@Param("bill") CBillComm bill);
	
	List<CBillComm> getBillList(@Param("bill") CBillComm bill);
	
	int updateBill(@Param("bill") CBillComm bill);
	
	List<String> getSmlAmtYn(@Param("bill") CBillComm bill);
	
	int updateSmlAmtYn(@Param("billTgtCust") BillTgtCust billTgtCust);
	
	int updateOverpayBillAply(@Param("overpayBillAply") OverpayBillAply overpayBillAply);

	void updatePrepayOcc(@Param("prepayOcc") PrepayOcc prepayOcc);
	
}

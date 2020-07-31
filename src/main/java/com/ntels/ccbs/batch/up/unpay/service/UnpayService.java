package com.ntels.ccbs.batch.up.unpay.service;

import java.util.List;

import com.ntels.ccbs.batch.up.entity.UpymObj;

public interface UnpayService {

	/**
	 * 미수금 내역을 저장한다.
	 * @param upymObjList
	 * @return
	 */
	int insertUnpaymentObj(List<UpymObj> upymObjList);
	
}

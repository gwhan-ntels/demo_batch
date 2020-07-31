package com.ntels.ccbs.batch.up.unpay.dao;

import java.util.List;

import com.ntels.ccbs.batch.up.entity.UpymObj;

public interface UnpayDao {

	/**
	 * 미수금 내역을 저장한다.
	 * @param upymObjList
	 * @return
	 */
	int insertUnpaymentObj(List<UpymObj> upymObjList);
	
}

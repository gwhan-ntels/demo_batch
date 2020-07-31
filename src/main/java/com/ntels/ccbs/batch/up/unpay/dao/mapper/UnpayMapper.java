package com.ntels.ccbs.batch.up.unpay.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.entity.UpymObj;

@Component
public interface UnpayMapper {

	/**
	 * 미수금 내역을 저장한다.
	 * @param upymObj
	 * @return
	 */
	int insertUnpaymentObj(@Param("upymObj") UpymObj upymObj);
	
}

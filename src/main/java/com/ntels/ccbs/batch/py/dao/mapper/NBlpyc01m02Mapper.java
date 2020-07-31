package com.ntels.ccbs.batch.py.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.batch.py.entity.Deposit;

public interface NBlpyc01m02Mapper {

	List<Deposit> getDepositList(@Param("billYymm") String billYymm);
	
}

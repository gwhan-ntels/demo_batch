package com.ntels.ccbs.batch.py.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NBlpyf02m02Mapper {

	List<String> getBillSeqByPymAcntId(@Param("pymAcntId") String pymAcntId);
	
}

package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface NBlivb01m04Service {

	public LazyLoader<CBillComm> listJdbcDirect(Common comm);
	public LazyLoader<CBillComm> listDivJdbcDirect(Common comm);
	public int saveJdbcDirect(List<Object> obj);
	public int saveDivJdbcDirect(List<Object> obj);
	public int saveWrkDirect(List<Object> obj);

	
	
}
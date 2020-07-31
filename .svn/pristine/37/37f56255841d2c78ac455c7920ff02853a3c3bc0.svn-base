package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface NBlivb01m32Service {

	public LazyLoader<CBillComm> listJdbcDirect(Common comm);
	public int saveJdbcDirect(List<Object> obj);
	public LazyLoader<CBillComm> listMastJdbcDirect(Common comm);
	public int saveMastJdbcDirect(List<Object> obj);
	
	
	public LazyLoader<CBillComm> listHotBillJdbcDirect(Common comm);
	public LazyLoader<CBillComm> listHotBillMastJdbcDirect(Common comm);
	public int saveHotBillJdbcDirect(List<Object> obj);
	public int saveHotBillJdbcAllDirect(Common comm);
	
	
}
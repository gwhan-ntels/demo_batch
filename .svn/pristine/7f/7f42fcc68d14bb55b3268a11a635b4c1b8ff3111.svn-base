package com.ntels.ccbs.batch.iv.dao;

import java.sql.Connection;
import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface NBlivb01m32Dao {
	
	public LazyLoader<CBillComm> listInfoDirect( Common comm);
	public int saveInfoDirect( List<Object> obj);
	public LazyLoader<CBillComm> listMastInfoDirect(Common comm);
	public int saveMastInfoDirect( List<Object> obj);

	public LazyLoader<CBillComm> listHotBillInfo(Common comm);
	public LazyLoader<CBillComm> listHotBillMastInfo(Common comm);
	public int saveHotBillInfo( List<Object> obj);
	
	public List<CBillComm> listHotBillAllInfo(Common comm);
	public List<CBillComm> listHotBillMastAllInfo(Common comm);
	
	
	
	
}
package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.exception.BatchException;
import com.ntels.ccbs.batch.iv.common.entity.Arrears;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;

public interface ArrearsService {

	LazyLoader<Arrears> getArrearsInfoList(Arrears arrears) throws BatchException;

	int insertArrs(List<Arrears> arrearsList);
	
	LazyLoader<BillWork> getArrsAplyInfoList(BillWork billWork) throws BatchException;
	
	int updateArrsInfo(List<BillWork> billWorkList);

}

package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.Arrears;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;

public interface ArrearsDao {

	List<Arrears> getArrearsInfoList(Arrears arrears);

	LazyLoader<Arrears> getArrearsInfoListLazy(Arrears arrears);

	int insertArrs(Arrears arrears);

	int insertArrs(List<Arrears> arrearList);

	List<BillWork> getArrsAplyInfoList(BillWork billWork);

	LazyLoader<BillWork> getArrsAplyInfoListLazy(BillWork billWork);

	int updateArrsInfo(BillWork billWork);

	int updateArrsInfo(List<BillWork> billWorkList);

}

package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPym;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPymProcRslt;

public interface GenerateSplitPaymentDao {

	List<BillSpltPym> getSplitPymCtrtInfoList(BillSpltPym billSpltPym);

	LazyLoader<BillSpltPym> getSplitPymCtrtInfoListLazy(BillSpltPym billSpltPym);

	int insertSpltPym(BillSpltPym billSpltPym);

	int insertSpltPymListMapper(List<BillSpltPym> billSpltPymList);

	int insertSpltPym(List<BillSpltPym> billSpltPymList);
	
	List<BillSpltPym> getSpltPymResultList(BillSpltPym billSpltPym);
	
	LazyLoader<BillSpltPym> getSpltPymResultListLazy(BillSpltPym billSpltPym);
	
	int insertSpltPymProcRstl(BillSpltPymProcRslt billSpltPymProcRslt);

	int insertSpltPymProcRstlListMapper(List<BillSpltPymProcRslt> billSpltPymProcRsltList);

	int insertSpltPymProcRstl(List<BillSpltPymProcRslt> billSpltPymProcRsltList);

}

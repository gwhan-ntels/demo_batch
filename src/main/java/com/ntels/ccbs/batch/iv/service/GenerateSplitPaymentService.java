package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.exception.BatchException;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPym;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPymProcRslt;

public interface GenerateSplitPaymentService {

	LazyLoader<BillSpltPym> getSplitPymCtrtInfoList(BillSpltPym billSpltPym) throws BatchException;

	int insertSpltPymList(List<BillSpltPym> billSpltPymList);

	LazyLoader<BillSpltPym> getSpltPymResultList(BillSpltPym billSpltPym) throws BatchException;

	int insertSpltPymProcRstl(List<BillSpltPymProcRslt> billSpltPymProcRsltList);

}

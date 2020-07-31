package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPym;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPymProcRslt;
import com.ntels.ccbs.batch.iv.dao.mapper.GenerateSplitPaymentMapper;

@Repository
public class GenerateSplitPaymentDaoImpl extends LazyLoadingDao implements GenerateSplitPaymentDao {

	@Autowired
	private GenerateSplitPaymentMapper generateSplitPaymentMapper;

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Value("${dbms.kind}")
	String dbKind;	
	
	@Override
	protected String getMapperName() {
		return "GenerateSplitPaymentMapper";
	}

	@Override
	public List<BillSpltPym> getSplitPymCtrtInfoList(BillSpltPym billSpltPym) {
		return generateSplitPaymentMapper.getSplitPymCtrtInfoList(billSpltPym);
	}

	@Override
	public LazyLoader<BillSpltPym> getSplitPymCtrtInfoListLazy(BillSpltPym billSpltPym) {
		return getLazyLoader(dbKind,"getSplitPymCtrtInfoList", BillSpltPym.class, billSpltPym);
	}

	@Override
	public int insertSpltPym(BillSpltPym billSpltPym) {
		return generateSplitPaymentMapper.insertSpltPym(billSpltPym);
	}

	@Override
	public int insertSpltPymListMapper(final List<BillSpltPym> billSpltPymList) {
		return batchJob(GenerateSplitPaymentMapper.class, new BatchJob<GenerateSplitPaymentMapper>() {

			@Override
			public int job(GenerateSplitPaymentMapper mapper) {
				int insertCnt = 0;
				for (BillSpltPym billSpltPym : billSpltPymList) {
					mapper.insertSpltPym(billSpltPym);
					insertCnt++;
				}
				return insertCnt;
			}
		});
	}

	@Override
	public int insertSpltPym(List<BillSpltPym> billSpltPymList) {
		return insert(dbKind,"insertSpltPym", BillSpltPym.class, billSpltPymList);
	}
	
	@Override
	public List<BillSpltPym> getSpltPymResultList(BillSpltPym billSpltPym) {
		return generateSplitPaymentMapper.getSpltPymResultList(billSpltPym);
	}
	
	@Override
	public LazyLoader<BillSpltPym> getSpltPymResultListLazy(BillSpltPym billSpltPym) {
		return getLazyLoader(dbKind,"getSpltPymResultList", BillSpltPym.class, billSpltPym);
	}

	@Override
	public int insertSpltPymProcRstl(BillSpltPymProcRslt billSpltPymProcRslt) {
		return generateSplitPaymentMapper.insertSpltPymProcRstl(billSpltPymProcRslt);
	}

	@Override
	public int insertSpltPymProcRstlListMapper(final List<BillSpltPymProcRslt> billSpltPymProcRsltList) {
		return batchJob(GenerateSplitPaymentMapper.class, new BatchJob<GenerateSplitPaymentMapper>() {

			@Override
			public int job(GenerateSplitPaymentMapper mapper) {
				int insertCnt = 0;
				for (BillSpltPymProcRslt billSpltPymProcRslt : billSpltPymProcRsltList) {
					mapper.insertSpltPymProcRstl(billSpltPymProcRslt);
					insertCnt++;
				}
				return insertCnt;
			}
		});
	}

	@Override
	public int insertSpltPymProcRstl(List<BillSpltPymProcRslt> billSpltPymProcRsltList) {
		return insert(dbKind,"insertSpltPymProcRstl", BillSpltPymProcRslt.class, billSpltPymProcRsltList);
	}

}

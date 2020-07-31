package com.ntels.ccbs.batch.iv.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

@Repository
public class NBlivb01m11DaoImpl extends LazyLoadingDao implements NBlivb01m11Dao {
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}
	
	@Override
	protected String getMapperName() {
		return "NBlivb01m11Mapper";
	}
	@Value("${dbms.kind}")
	String dbKind;
	
	/**
	 * 미납내역 생성을 위한 청구정보를 조회한다.
	 * @param bill
	 * @return
	 */
	@Override
	public LazyLoader<CBillComm> getBillInfoForUnpayTarget(CBillComm bill) {
		return getLazyLoader(dbKind,"getBillInfoForUnpayTarget", CBillComm.class, bill);
	}
	
	@Override
	public LazyLoader<BillCust> getBillTargetCust(CBillComm bill) {
		return getLazyLoader(dbKind,"getBillTargetCust", BillCust.class, bill);
	}
	
	@Override
	public LazyLoader<BillCust> getBillTargetServiceComposition(CBillComm bill) {
		return getLazyLoader(dbKind,"getBillTargetServiceComposition", BillCust.class, bill);
	}
	
}
